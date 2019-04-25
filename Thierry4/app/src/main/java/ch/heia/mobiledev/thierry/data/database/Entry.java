package ch.heia.mobiledev.thierry.data.database;


public class Entry {
  // data members/fields
  // each data member/field represents a field in each piece of data
  // received from the web API
  // choose the appropriate modifiers for each field
  float temp;
  float tempMin;
  float tempMax;
  float press;
  float wind;
  float hum;
  String main;
  String desc;
  String icon;
  String date;


  // constructor
  // each data field must be initialized correctly upon construction
  // based on data received from the web API
  public Entry(float temp, float tempMin, float tempMax, float press, float wind, float hum, String main, String desc, String icon, String date) {
    this.temp = temp;
    this.tempMin = tempMin;
    this.tempMax = tempMax;
    this.press = press;
    this.wind = wind;
    this.hum = hum;
    this.main = main;
    this.desc = desc;
    this.icon = icon;
    this.date = date;
  }

  // accessor methods used for accessing specific data fields
  public float getTemp() {return temp;}
  
  public void setTemp(float temp) {this.temp = temp;}
  
  public float getTempMin() {return tempMin;}
  
  public void setTempMin(float tempMin) {this.tempMin = tempMin;}
  
  public float getTempMax() {return tempMax;}
  
  public void setTempMax(float tempMax) {this.tempMax = tempMax;}
  
  public float getPress() {return press;}
  
  public void setPress(float press) {this.press = press;}
  
  public float getWind() {return wind;}
  
  public void setWind(float hum) {this.hum = hum;}

  public float getHum() {return wind;}

  public void setHum(float hum) {this.hum = hum;}
  
  public String getMain() {return main;}
  
  public void setMain(String main) {this.main = main;}
  
  public String getDesc() {return desc;}
  
  public void setDesc(String desc) {this.desc = desc;}
  
  public String getIcon() {return icon;}
  
  public void setIcon(String icon) {this.icon = icon;}
  
  public String getDate() {return date;}
  
  public void setDate(String date) {this.date = date;}
}
