package ch.heia.mobiledev.weatherForecast.data.City;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ch.heia.mobiledev.weatherForecast.data.database.City;

class JsonParser {

    // data members
    // define constant strings used for parsing the JSON response received from the web API

    // method called for parsing the response
    // responseStr is the JSON response stored in a String
    public static ResponseCity parse(final String responseStr) throws JSONException {
        // first get a JSON object from the received string
        JSONArray array = new JSONArray(responseStr);

        // then get all entries from the jsonObject
        City[] response = citiesFromJson(array);

        return new ResponseCity(response);
    }

    // this method receives the JSON object representing the response received from the web API
    private static City[] citiesFromJson(final JSONArray json) throws JSONException {

        // allocate the array for all news entries
        City[] city = new City[json.length()];

        // get all entries from the JSON array
        for (int i = 0; i < json.length(); i++) {
            // Get the JSON object representing the specific entry
            JSONObject elt = json.getJSONObject(i);
            city[i] = cityFromJson(elt);
        }
        return city;
    }

    // this method returns one entry instance for a specific JSON object
    private static City cityFromJson(final JSONObject jsonCity) throws JSONException {

        return new City(
                jsonCity.getInt("id"),
                jsonCity.getString("name"));
    }
}
