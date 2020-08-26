#include <TimeLib.h>
#include <SoftwareSerial.h>
#include <TinyGPS.h>

const int TX = 4;
const int RX = 3;

TinyGPS gps;
SoftwareSerial softSerial(TX, RX);
SoftwareSerial gsm_gprs(7, 8);

// Offset hours from gps time (UTC)
const int offset = -3;   // UTC-3

// ** Weft size **
const int SIZE = 5;

// ** Without datetime **
const char EXAMPLE[36] = "00004,-31.4109,-64.1897,0.00,4,246;";

// ** With datetime **
//const char EXAMPLE[66] = "00004,-31.4109,-64.1897,0.00,4,246,10-09-2018T20:51:09:000-03:00;";

char DEVICE_ID[6] = "00002";
const int MEMORY_SIZE = (strlen(EXAMPLE)) * SIZE * sizeof(char) + 1;

time_t prevDisplay = 0; // when the digital clock was displayed

void setup() {
  Serial.begin(9600);
  gsm_gprs.begin(19200);
  delay(1000);
  Serial.write("iniciando\n");
  init_gprs_module();
  delay(500);
  softSerial.begin(9600); //9600
}

void loop() {
  bool newData = false;
  buildWeft();
  Serial.write("finishhhh\n");
}

void init_gprs_module() {
  gsm_gprs.println("AT+SAPBR=3,1,\"Contype\",\"GPRS\"");
  delay(1000);
  printSerialData();
  gsm_gprs.println("AT+SAPBR=3,1,\"APN\",\"datos.personal.com\"");
  delay(1000);
  printSerialData();
  gsm_gprs.println("AT+SAPBR=3,1,\"USER\",\"datos\"");
  delay(1000);
  printSerialData();
  gsm_gprs.println("AT+SAPBR=3,1,\"PWD\",\"datos\"");
  delay(1000);
  printSerialData();
  gsm_gprs.println("AT+SAPBR=0,1");
  delay(1000);
  printSerialData();
  gsm_gprs.println("AT+SAPBR=1,1");
  delay(1000);
  printSerialData();
}

void send_http_post(char *coordinate_data) { 
  gsm_gprs.println("AT+HTTPINIT");
  delay(100);
  printSerialData();
  gsm_gprs.println("AT+HTTPPARA=\"CID\",1");
  delay(200);
  printSerialData();
  gsm_gprs.println("AT+HTTPPARA=\"URL\",\"http://gps-core.herokuapp.com/trackings\"");
  delay(200);
  printSerialData();
  gsm_gprs.println("AT+HTTPPARA=\"CONTENT\",\"text/plain\"");
  delay(200);
  printSerialData();
  gsm_gprs.println("AT+HTTPDATA=" + String(MEMORY_SIZE) + ",10000");
  delay(200);
  printSerialData();
  gsm_gprs.println(coordinate_data);
  delay(100);
  printSerialData();
  gsm_gprs.println("AT+HTTPACTION=1");
  delay(100);
  printSerialData();
  gsm_gprs.println("AT+HTTPTERM");
  delay(100);
  printSerialData();
  delay(5000);
 
  Serial.println(coordinate_data);
}


void parseCoordinates(float flat, float flon, float speed, int sat, int hdop, char device_id[], char *coordinate_data) {
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
  dtostrf(speed, 4, 2, value);
  strcat(coordinate_data, value);
  strcat(coordinate_data, ",");
  itoa(sat, value, 10);
  strcat(coordinate_data, value);
  strcat(coordinate_data, ",");
  itoa(hdop, value, 10);
  strcat(coordinate_data, value);
  strcat(coordinate_data, ";");
  
// ** datetime with timezone mocked **
//  strcat(coordinate_data, "10-09-2018T20:51:09:000-03:00;");

// ** datetime with timezone **
//  sprintf(sz, "%02d-%02d-%02dT%02d:%02d:%02d:000-03:00;",
//    day(), month(), year(), hour(), minute(), second());
//  strcat(coordinate_data, sz);

}

void buildWeft() {
  bool newData = false;
  float flat, flon;
  unsigned long age;
  int Year;
  byte Month, Day, Hour, Minute, Second;
  char *coordinate_data;
  coordinate_data = (char*)malloc(MEMORY_SIZE);
  if (coordinate_data == NULL) {
    Serial.println("La aplicacion no pudo reservar memoria y se va a cerrar!");
    exit(EXIT_FAILURE);
  }
//  ** data mocked **
//  for (unsigned int start = 0; start <= 9; start ++) {
//    parseCoordinates(-31.4109, -64.1897, 8, 246, DEVICE_ID, coordinate_data);
//  }
  
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
      //float flat, flon;
      //unsigned long age;
      //int Year;
      //byte Month, Day, Hour, Minute, Second;
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
      parseCoordinates(flat, flon, gps.f_speed_kmph(), gps.satellites(), gps.hdop(), DEVICE_ID, coordinate_data);
     }
    }

  send_http_post(coordinate_data);
  free(coordinate_data);
}

void logData(float flat, float flon) {
  Serial.print("LAT=");
  Serial.print(flat == TinyGPS::GPS_INVALID_F_ANGLE ? 0.0 : flat, 6);
  Serial.print(" LON=");
  Serial.print(flon == TinyGPS::GPS_INVALID_F_ANGLE ? 0.0 : flon, 6);
  Serial.print(" SPEED=");
  Serial.print(gps.f_speed_kmph() == TinyGPS::GPS_INVALID_F_SPEED ? 0.0 : gps.f_speed_kmph());
  Serial.print(" LONG_SPEED=");
  Serial.print(gps.speed() == TinyGPS::GPS_INVALID_F_SPEED ? 0.0 : gps.speed()*0.0185);
  Serial.print(" SAT=");
  Serial.print(gps.satellites() == TinyGPS::GPS_INVALID_SATELLITES ? 0 : gps.satellites());
  Serial.print(" HDOP=");
  Serial.print(gps.hdop() == TinyGPS::GPS_INVALID_HDOP ? 0 : gps.hdop());
  Serial.print(" TIME=");

  char sz[30];
  sprintf(sz, "%02d-%02d-%02dT%02d:%02d:%02d:000-03:00",
          day(), month(), year(), hour(), minute(), second());
  Serial.println(sz);
}

void printSerialData() {
  while (gsm_gprs.available() != 0)
    Serial.write(gsm_gprs.read());
}
