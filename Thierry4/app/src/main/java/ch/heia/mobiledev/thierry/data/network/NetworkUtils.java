package ch.heia.mobiledev.thierry.data.network;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

class NetworkUtils {
	// data members
	// for logging
	private static final String TAG = NetworkUtils.class.getSimpleName();

	// constant strings that are URL related
	// the defininition depends on each web API

	/* example query with correct key
	 * [http://api.openweathermap.org/data/2.5/forecast?q=Fribourg,ch&APPID=daeecc1588e9812e4eedc9644d47ee8e]
	 * TODO: build URL with correct data source type and location
	 */

  private static final String XXX_PARAM = "q";
  private static final String XXX_APIID = "APPID";

	private static final String API_HOST = "api.openweathermap.org";
	private static final String API_PATH0 = "data";
	private static final String API_PATH1 = "2.5";
	private static final String API_PATH2 = "forecast";
  private static final String API_KEY = "daeecc1588e9812e4eedc9644d47ee8e";


  // Retrieves the proper URL to query the web API
	// if you received query parameters from other parts of the application
	// you must pass these parameters as arguments to this method
	public static URL getUrl(String city) {
		return buildUrlWithQueryParameters(city);
	}

	// Build the url for specific query parameters
	private static URL buildUrlWithQueryParameters(String location) {
		// use the Uri class for building the uri to be used
	  // (using parse()/buildUpon()/appendXXX() methods)
		try {
		// build the URL instance based on the Uri instance
		// and return it to the caller

			Uri.Builder builder = new Uri.Builder();
			builder.scheme("http")
							.authority(API_HOST)
							.appendPath(API_PATH0)
							.appendPath(API_PATH1)
							.appendPath(API_PATH2)
							.appendQueryParameter(XXX_PARAM, location)
							.appendQueryParameter(XXX_APIID, API_KEY);
			String myUrl = builder.build().toString();

      URL builtUrl = new URL(myUrl);

			Log.d("BuiltURL: ", myUrl);

			return builtUrl;
		}
		catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
	}

	// called for getting the response from a specific URL
	static String getResponseFromHttpUrl(URL url) throws IOException {
		// use a HttpURLConnection instance for getting the response from the URL
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		try {
			urlConnection.setRequestMethod("GET");
			urlConnection.setRequestProperty("Accept", "application/json");
			InputStream in = urlConnection.getInputStream();

			Scanner scanner = new Scanner(in);
			scanner.useDelimiter("\\A");

			boolean hasInput = scanner.hasNext();
			String response = null;
			if (hasInput) {
				response = scanner.next();
			}
			scanner.close();
			return response;
		}
		finally {
			urlConnection.disconnect();
		}
	}
}
