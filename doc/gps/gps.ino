#include <TimeLib.h>
#include <SoftwareSerial.h>
#include <TinyGPS.h>

const int TX = 4;
const int RX = 3;

TinyGPS gps;
SoftwareSerial softSerial(TX, RX);

// Offset hours from gps time (UTC)
const int offset = -3;   // UTC-3

// Array size
const int SIZE = 10;
const char EXAMPLE[93] = "000000000000000000000000000000000001,-31.4109,-64.1897,4,246,10-09-2018T20:51:09:000-03:00;";

time_t prevDisplay = 0; // when the digital clock was displayed

void setup() {
  Serial.begin(19200); //115200
  softSerial.begin(9600); //9600
} 

void parseCoordinates(float flat, float flon, int sat, int hdop, char device_id[], char *coordinate_data) {
  char value[9];
  char sz[30];
  strcat(coordinate_data, device_id);
  strcat(coordinate_data, ",");
  dtostrf(flat, 8, 4, value);
  strcat(coordinate_data, value);
  strcat(coordinate_data, ",");
  dtostrf(flon, 8, 4, value);
  strcat(coordinate_data, value);
  strcat(coordinate_data, ",");
  itoa(sat, value, 10);
  strcat(coordinate_data, value);
  strcat(coordinate_data, ",");
  itoa(hdop, value, 10);
  strcat(coordinate_data, value);
  strcat(coordinate_data, ",");

  sprintf(sz, "%02d-%02d-%02dT%02d:%02d:%02d:000-03:00", 
    day(), month(), year(), hour(), minute(), second());
  strcat(coordinate_data, sz);
  return 0;
}

void buildWeft() {
  bool newData = false;
  float flat, flon;
  unsigned long age;
  int Year;
  byte Month, Day, Hour, Minute, Second;
  char *coordinate_data;
  coordinate_data = malloc((strlen(EXAMPLE)) * SIZE * sizeof(char) + 1);
  if (coordinate_data == NULL) {
    Serial.println("La aplicacion no pudo reservar memoria y se va a cerrar!"); 
    exit(EXIT_FAILURE);
  }
  
  for(int i=0; i<SIZE; i++) {

    for (unsigned long start = millis(); millis() - start < 1000;) {
      while (softSerial.available()) {
        char c = softSerial.read();
        //Serial.write(c); // uncomment this line to see the GPS data
        if (gps.encode(c)) // validating the sentence
          newData = true;
      }
    }
    if (newData) {
    float flat, flon;
    unsigned long age;
    int Year;
    byte Month, Day, Hour, Minute, Second;
    gps.crack_datetime(&Year, &Month, &Day, &Hour, &Minute, &Second, NULL, &age);
    gps.f_get_position(&flat, &flon, &age);
    if (age < 500) {
      // set the Time to the latest GPS reading
      setTime(Hour, Minute, Second, Day, Month, Year);
      adjustTime(offset * SECS_PER_HOUR);
    }
    if (timeStatus() != timeNotSet) {
      if (now() != prevDisplay){ 
        //update the display only if the time has changed
        prevDisplay = now();
      }
    }
    //logData(flat, flon);
    parseCoordinates(flat, flon, gps.satellites(), gps.hdop(), "000000000000000000000000000000000001", coordinate_data);
   }
  }
  sendData(coordinate_data);
  //delay(500);
  free(coordinate_data);
  return 0;
}

void logData(float flat, float flon) {
  Serial.print("LAT=");
  Serial.print(flat == TinyGPS::GPS_INVALID_F_ANGLE ? 0.0 : flat, 6);
  Serial.print(" LON=");
  Serial.print(flon == TinyGPS::GPS_INVALID_F_ANGLE ? 0.0 : flon, 6);
  Serial.print(" SAT=");
  Serial.print(gps.satellites() == TinyGPS::GPS_INVALID_SATELLITES ? 0 : gps.satellites());
  Serial.print(" HDOP=");
  Serial.print(gps.hdop() == TinyGPS::GPS_INVALID_HDOP ? 0 : gps.hdop());
  Serial.print(" TIME=");
  // digital clock display of the time
  char sz[30];
  sprintf(sz, "%02d-%02d-%02dT%02d:%02d:%02d:000-03:00", 
    day(), month(), year(), hour(), minute(), second());
  Serial.print(sz);
}

void sendData(char *coordinate_data) {
 // post to the service
 // ...
 Serial.println(coordinate_data);
}

void loop() {
  bool newData = false;
  unsigned long chars;
  unsigned short sentences, failed;
  float flat, flon;
  flat = -31.4109;
  flon = -64.1897;
  buildWeft();
  
  gps.stats(&chars, &sentences, &failed);
  Serial.print(" CHARS=");
  Serial.print(chars);
  Serial.print(" SENTENCES=");
  Serial.print(sentences);
  Serial.print(" CSUM ERR=");
  Serial.println(failed);
  
  if (chars == 0)
    Serial.println("** No characters received from GPS: check wiring **");
}
