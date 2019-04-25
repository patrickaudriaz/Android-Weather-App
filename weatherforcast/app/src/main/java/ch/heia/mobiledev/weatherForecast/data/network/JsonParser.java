package ch.heia.mobiledev.weatherForecast.data.network;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ch.heia.mobiledev.weatherForecast.data.database.Entry;

class JsonParser {
    // data members
    // define constant strings used for parsing the JSON response received from the web API

    // method called for parsing the response
    // responseStr is the JSON response stored in a String
    public static Response parse(final String responseStr) throws JSONException {
        // first get a JSON object from the received string
        JSONObject json = new JSONObject(responseStr);

        // then get all entries from the jsonObject
        Entry[] response = entriesFromJson(json);

        return new Response(response);
    }

    // this method receives the JSON object representing the response received from the web API
    private static Entry[] entriesFromJson(final JSONObject json) throws JSONException {
        // get the JSON array representing all entries (e.g. one entry represents one weather forecast
        // entry among all weather forecasts)
        JSONArray jsonArray = json.getJSONArray("list");

        // allocate the array for all news entries
        Entry[] entry = new Entry[jsonArray.length()];

        // get all entries from the JSON array
        for (int i = 0; i < jsonArray.length(); i++) {
            // Get the JSON object representing the specific entry
            JSONObject elt = jsonArray.getJSONObject(i);
            entry[i] = entryFromJson(elt);
        }
        return entry;
    }

    // this method returns one entry instance for a specific JSON object
    private static Entry entryFromJson(final JSONObject jsonEntry) throws JSONException {
        JSONObject main = jsonEntry.getJSONObject("main");
        return new Entry(
                jsonEntry.getJSONArray("weather").getJSONObject(0).getString("icon"),
                main.getInt("temp"),
                main.getInt("temp_min"),
                main.getInt("temp_max"),
                main.getInt("humidity"),
                main.getInt("pressure"),
                jsonEntry.getJSONObject("wind").getInt("speed"),
                jsonEntry.getJSONArray("weather").getJSONObject(0).getString("main"),
                jsonEntry.getString("dt_txt"));
    }
}
