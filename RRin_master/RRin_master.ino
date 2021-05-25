#include <Servo.h>
#include <Wire.h>

Servo servo;
const int SERVO_PIN = 8;
const int TRIG_PIN = 9;
const int ECHO_PIN = 10;
const int I2C_ADDRESS = 15;

void setup()
{
  servo.attach(SERVO_PIN);
  servo.write(0);

  pinMode(TRIG_PIN, OUTPUT);
  pinMode(ECHO_PIN, INPUT);

  Serial.begin(9600);
  Wire.begin();
}

int calculateDistance()
{
  digitalWrite(TRIG_PIN, LOW);
  delayMicroseconds(2);
  digitalWrite(TRIG_PIN, HIGH);
  delayMicroseconds(10);
  digitalWrite(TRIG_PIN, LOW);

  long duration = pulseIn(ECHO_PIN, HIGH);

  return duration * 0.017;
}

void sendI2C(int angle, int distance)
{
  Wire.beginTransmission(I2C_ADDRESS);
  Wire.write(angle);
  Wire.write(distance);
  Wire.endTransmission();
}

void loop()
{
  for(int servoAngle = 0; servoAngle <= 180; servoAngle += 10)
  {
    servo.write(servoAngle);
    int distance = calculateDistance();
    sendI2C(servoAngle, distance);
    delay(500);
  }

  for(int servoAngle = 180; servoAngle >= 0; servoAngle -= 10)
  {
    servo.write(servoAngle);
    int distance = calculateDistance();
    sendI2C(servoAngle, distance);
    delay(500);
  }
}
