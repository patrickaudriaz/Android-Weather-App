package ch.heia.mobiledev.weatherForecast.data.database;


public class Entry {
    private static final int KELVIN_TO_CELSIUS = -273;
    private static final double M_S_TO_KM_H = 3.6;

    private static final int STRING_DATE_START = 0;
    private static final int STRING_DATE_STOP = 10;
    private static final int STRING_TIME_START = 11;
    private static final int STRING_TIME_STOP = 16;
    private static final int STRING_HOURS_STOP = 13;

    private final int temp;
    private final int temp_min;
    private final int temp_max;
    private final int humidity;
    private final int pressure;
    private final int wind_speed;
    private final String icon;
    private final String weather;
    private final String dateTime;

    public Entry(String icon, int temp, int temp_min, int temp_max, int humidity, int pressure, int wind_speed, String weather, String dateTime) {
        // For for day because in the night it return something like 01n and we have 01d
        this.icon = icon.replace('n', 'd');
        this.temp = temp + KELVIN_TO_CELSIUS;
        this.temp_min = temp_min + KELVIN_TO_CELSIUS;
        this.temp_max = temp_max + KELVIN_TO_CELSIUS;
        this.humidity = humidity;
        this.pressure = pressure;
        this.wind_speed = (int) (wind_speed * M_S_TO_KM_H);
        this.weather = weather;
        this.dateTime = dateTime;
    }

    public String getIcon() {
        return icon;
    }

    public int getTemp() {
        return temp;
    }

    public int getTemp_min() {
        return temp_min;
    }

    public int getTemp_max() {
        return temp_max;
    }

    public int getHumidity() {
        return humidity;
    }

    public int getPressure() {
        return pressure;
    }

    public int getWind_speed() {
        return wind_speed;
    }

    public String getWeather() {
        return weather;
    }

    public String getDateTime() {
        return dateTime;
    }

    public int getHour() {
        return Integer.parseInt(dateTime.substring(STRING_TIME_START, STRING_HOURS_STOP));
    }

    public String getDate() {
        return dateTime.substring(STRING_DATE_START, STRING_DATE_STOP);
    }

    public String getTime() {
        return dateTime.substring(STRING_TIME_START, STRING_TIME_STOP);
    }
}
