package ch.heia.mobiledev.thierry;

public class WeatherMeasure {
    private String local;
    private String temp;
    private String time;
    private String hum;
    private String pres;
    private String wind;


    // Constructor that is used to create an instance of the Movie object
    public WeatherMeasure(String temp, String time, String hum, String pres, String wind, String local) {
        this.temp = temp;
        this.time = time;
        this.hum = hum;
        this.local = local;
        this.pres = pres;
        this.wind = wind;
    }


}
