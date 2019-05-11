package ch.heia.mobiledev.thierry.data.network;

import android.content.Context;
import android.content.res.Resources;
import android.view.ViewParent;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ch.heia.mobiledev.thierry.R;
import ch.heia.mobiledev.thierry.data.database.Entry;
import ch.heia.mobiledev.thierry.ui.MainActivity;
import ch.heia.mobiledev.thierry.ui.SettingsActivity;

class JsonParser extends AppCompatActivity {
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
	public static Response parse(Context context, final String responseStr) throws JSONException {
		// first get a JSON object from the received string
		JSONObject jsonObject = new JSONObject(responseStr);
		
		// then get all entries from the jsonObject
		Entry[] entries = entriesFromJson(context, jsonObject);


		// return the response that contains all entries
		return new Response(entries);
	}

	// this method receives the JSON object representing the response received from the web API
	private static Entry[] entriesFromJson(Context context, final JSONObject json) throws JSONException {
		// get the JSON array representing all entries (e.g. one entry represents one weather forecast
		// entry among all weather forecasts)

		JSONArray forecast = json.getJSONArray("list");

		// allocate the array for all news entries
		Entry[] entries = new Entry[forecast.length()];
		int offset = context.getResources().getString(R.string.use_kelvin) == "Yes" ? 0 : -273;
		
		// get all entries from the JSON array
		for (int i = 0; i < forecast.length(); i++) {
			// Get the JSON object representing the specific entry
			JSONObject day = forecast.getJSONObject(i); // forecast data for one day
			JSONObject mainData = day.getJSONObject("main"); // container for temperature, pressure and humidity data for the day
			JSONObject windSpeed = day.getJSONObject("wind"); // wind data for the day
			JSONArray weatherArr = day.getJSONArray("weather"); // weather data for the day
			JSONObject currentWeather = weatherArr.getJSONObject(0);
			
			// from this JSON object get the entry
			float temp = Float.parseFloat(mainData.getString("temp")) + offset;
			float tempMin = Float.parseFloat(mainData.getString("temp_min")) + offset;
			float tempMax = Float.parseFloat(mainData.getString("temp_max")) + offset;
			float press = Float.parseFloat(mainData.getString("pressure"));
			float wind = Float.parseFloat(windSpeed.getString("speed"));
			
			int humid = Integer.parseInt(mainData.getString("humidity"));

			String main = currentWeather.getString("main");
			String desc = currentWeather.getString("description");
			String icon = currentWeather.getString("icon");
			String date = day.getString("dt_txt");

			Entry dayEntry = new Entry(temp,tempMin,tempMax,press,wind,humid,main,desc,icon,date);
			entries[i] = dayEntry;
		}

		// return the array of entries
		return entries;
	}

}
