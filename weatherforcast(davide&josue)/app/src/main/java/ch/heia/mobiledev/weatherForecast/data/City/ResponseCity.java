package ch.heia.mobiledev.weatherForecast.data.City;

import androidx.annotation.NonNull;
import ch.heia.mobiledev.weatherForecast.data.database.City;

public class ResponseCity {
    // data members
    // array of entries
    @NonNull
    private final City[] mCities;

    public ResponseCity(@NonNull final City[] cities) {
        mCities = cities;
    }

    public int getIdFromName(String name){
        for (City mCity : mCities) {
            String city = mCity.getName();
            if (city.compareToIgnoreCase(name) == 0) {
                return mCity.getId();
            }
        }
        return -1;
    }
    public String getCityName(String name){
        for (City mCity : mCities) {
            String city = mCity.getName();
            if (city.compareToIgnoreCase(name) == 0) {
                return mCity.getName();
            }
        }
        return "City not found\n";
    }
/*  usefull if database is implemented.. but useless yet

    public boolean getCityChecked(String name){
        for(int i = 0; i < mCities.length; i++){
            String city = mCities[i].getName();
            if(city.compareToIgnoreCase(name)==0){
                return mCities[i].isChecked();
            }
        }
        return false;
    }

    public void setCityChecked(String name, boolean bool){
        for(int i = 0; i < mCities.length; i++){
            String city = mCities[i].getName();
            if(city.compareToIgnoreCase(name)==0){
                mCities[i].setChecked(bool);
            }
        }
    }*/
}