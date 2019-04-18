package ch.heia.mobiledev.thierry.data.network;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.Instant;
import java.util.Date;

import ch.heia.mobiledev.thierry.data.database.Entry;
import ch.heia.mobiledev.thierry.data.network.Response;

public class JsonParser {
    // data members
    // define constant strings used for parsing the JSON response received from the web API
    String LOCAL;
    String TEMP;
    String HUM;
    String TIME;
    String PRES;
    String WIND;


    // method called for parsing the response
    // responseStr is the JSON response stored in a String
    public static Response parse(final String responseStr) throws JSONException {
        // first get a JSON object from the received string
        JSONObject jsonObject = new JSONObject(responseStr);

        // then get all entries from the jsonObject
        Entry[] entries = entriesFromJson(jsonObject);


        // return the response that contains all entries
        return new Response(entries);
    }

    // this method receives the JSON object representing the response received from the web API
    private static Entry[] entriesFromJson(final JSONObject json) throws JSONException {
        // get the JSON array representing all entries (e.g. one entry represents one weather forecast
				// entry among all weather forecasts)

        JSONArray jsonArray = (JSONArray) json.get("list");


        // allocate the array for all news entries

        // get all entries from the JSON array
        for (int i = 0; i < jsonArray.length(); i++) {
            // Get the JSON object representing the specific entry

            // from this JSON object get the entry

        }

        // return the array of entries
        return null;
    }

    // this method returns one entry instance for a specific JSON object
    private static Entry entryFromJson(final JSONObject jsonEntry) throws JSONException {
        return null;
    }
}
