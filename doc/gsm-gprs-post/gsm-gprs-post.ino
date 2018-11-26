
#include <SoftwareSerial.h>

SoftwareSerial gsm_gprs(7,8);
String reading="12,1234.1235,567.5675,8,246,1-1-1970T0:0:1:000-03:00;12,1234.1235,567.5675,8,246,1-1-1970T0:0:1:000-03:00;12,1234.1235,567.5675,8,246,1-1-1970T0:0:1:000-03:00;";

void setup()
{
  gsm_gprs.begin(19200);   // Setting the baud rate of GSM Module  
  Serial.begin(9600);// Setting the baud rate of Serial Monitor (Arduino)
  delay(1000);

  gsm_gprs.println("AT+SAPBR=3,1,\"Contype\",\"GPRS\"");
  delay(100);
  printSerialData();
  gsm_gprs.println("AT+SAPBR=3,1,\"APN\",\"datos.personal.com\"");
  delay(100);
  printSerialData();
  gsm_gprs.println("AT+SAPBR=3,1,\"USER\",\"datos\"");
  delay(100);
  printSerialData();
  gsm_gprs.println("AT+SAPBR=3,1,\"PWD\",\"datos\"");
  delay(100);
  printSerialData();
  gsm_gprs.println("AT+SAPBR=0,1");
  delay(100);
  printSerialData();
  gsm_gprs.println("AT+SAPBR=1,1");
  delay(100);
  printSerialData();
  
  Serial.write("Waiting");
}

void loop()
{
  if(Serial.available()) {
      switch(Serial.read()){
        case 'p':
          SendHttpPost();
          break;
      }
  }
  if (gsm_gprs.available()) {
      Serial.write(gsm_gprs.read());
  }
}

void SendHttpPost() {
  Serial.write("Sending HTTP POST\n");
  /*gsm_gprs.println("AT+SAPBR=3,1,\"Contype\",\"GPRS\"");
  //delay(100);
  printSerialData();
  gsm_gprs.println("AT+SAPBR=3,1,\"APN\",\"datos.personal.com\"");
  //delay(100);
  printSerialData();
  gsm_gprs.println("AT+SAPBR=3,1,\"USER\",\"datos\"");
  //delay(100);
  printSerialData();
  gsm_gprs.println("AT+SAPBR=3,1,\"PWD\",\"datos\"");
  //delay(100);
  printSerialData();
  gsm_gprs.println("AT+SAPBR=0,1");
  //delay(100);
  printSerialData();
  gsm_gprs.println("AT+SAPBR=1,1");
  //delay(100);
  printSerialData();
  /*gsm_gprs.println("AT+SAPBR=2,1");
  delay(2000);
  printSerialData();
  gsm_gprs.println("AT+SAPBR=4,1");
  delay(2000);
  printSerialData();
  gsm_gprs.println("AT+CGATT?");
  delay(2000);
  printSerialData();*/
  gsm_gprs.println("AT+HTTPINIT");
  delay(100);
  printSerialData();
  gsm_gprs.println("AT+HTTPPARA=\"CID\",1");
  delay(200);
  printSerialData();
  gsm_gprs.println("AT+HTTPPARA=\"URL\",\"http://gps-locations-api.herokuapp.com/locations\"");
  delay(200);
  printSerialData();
  gsm_gprs.println("AT+HTTPPARA=\"CONTENT\",\"text/plain\"");
  delay(200);
  printSerialData();
  gsm_gprs.println("AT+HTTPDATA=" + String(reading.length()) + ",10000");
  delay(200);
  printSerialData();
  gsm_gprs.println(reading);
  delay(100);
  printSerialData();
  gsm_gprs.println("AT+HTTPACTION=1");
  delay(100);
  printSerialData();
  gsm_gprs.println("AT+HTTPTERM");
  delay(100);
  printSerialData();
  Serial.write("Waiting");
}

void printSerialData()
{
 while(gsm_gprs.available()!=0)
 Serial.write(gsm_gprs.read());
}
