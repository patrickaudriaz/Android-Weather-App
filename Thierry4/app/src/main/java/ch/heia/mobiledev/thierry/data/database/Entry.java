package ch.heia.mobiledev.thierry.data.database;


public class Entry {
  // data members/fields
  // each data member/field represents a field in each piece of data
  // received from the web API
  // choose the appropriate modifiers for each field
   String local;
   String temp;
   String time;
   String hum;
   String pres;
   String wind;

  // constructor
  // each data field must be initialized correctly upon construction
  // based on data received from the web API
  public Entry(String temp, String time, String hum, String pres, String wind, String local) {
    this.temp = temp;
    this.time = time;
    this.hum = hum;
    this.pres = pres;
    this.wind = wind;
  }

  // accessor methods used for accessing specific data fields
    public String getTemp() {
        return temp;
    }
    public String getHum() {
        return time;
    }
    public String getPres() {
        return hum;
    }
    public String getWind() {
        return pres;
    }
    public String getTime() {
        return wind;
    }
  
}
