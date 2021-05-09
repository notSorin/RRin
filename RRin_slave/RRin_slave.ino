#include <SoftwareSerial.h>
#include <Wire.h>

constexpr int I2C_ADDRESS = 15;
constexpr int RX_PIN = 2;
constexpr int TX_PIN = 3;

SoftwareSerial HC06(RX_PIN, TX_PIN);

void setup()
{
  Serial.begin(9600);
  HC06.begin(9600);
  Wire.begin(I2C_ADDRESS); //Start the I2C Bus as Slave on address 9.
  Wire.onReceive(receiveEvent); //Attach a function to trigger when something is received.
}

//Called whenever data is received through I2C.
void receiveEvent(int bytes)
{
  int angle = Wire.read();
  int distance = Wire.read();

  //Create a string with the data, and send it through bluetooth.
  String toSend = String(angle) + " " + String(distance);

  //Serial.println(toSend);
  HC06.println(toSend);
}

void loop()
{
  
}
