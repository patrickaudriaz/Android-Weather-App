package ch.heia.mobiledev.thierry.data.network;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {
	// data members
	// for logging
	private static final String TAG = NetworkUtils.class.getSimpleName();

	// constant strings that are URL related
	// the defininition depends on each web API

	/* example query with correct key
	 * [http://api.openweathermap.org/data/2.5/forecast?q=Fribourg,ch&APPID=daeecc1588e9812e4eedc9644d47ee8e]
	 * TODO: build URL with correct data source type and location
	 */

  private static final String XXX_PARAM = "q=";
  private static final String XXX_APIID = "&APPID=";
  
	private static final String API_HOST = "http://api.openweathermap.org";
	private static final String API_PATH = "/data/2.5/forecast";
  private static final String API_KEY = "daeecc1588e9812e4eedc9644d47ee8e";
  
  
  // Retrieves the proper URL to query the web API
	// if you received query parameters from other parts of the application
	// you must pass these parameters as arguments to this method
	public static URL getUrl() {
		return buildUrlWithQueryParameters("Fribourg,ch");
	}

	// Build the url for specific query parameters
	private static URL buildUrlWithQueryParameters(String location) {
		// use the Uri class for building the uri to be used
	  // (using parse()/buildUpon()/appendXXX() methods)
		try {
		// build the URL instance based on the Uri instance
		// and return it to the caller
      
//      Uri builtUri = Uri.parse(API_HOST);
//
//      builtUri.buildUpon()
//              .appendPath(API_PATH)
//              .appendQueryParameter(XXX_PARAM, location)
//              .appendQueryParameter(XXX_APIID, API_KEY)
//              .build();
			
			Uri.Builder builder = new Uri.Builder();
			builder.scheme("http")
							.authority("api.openweathermap.org")
							.appendPath("data")
							.appendPath("2.5")
							.appendPath("forecast")
							.appendQueryParameter("q", location)
							.appendQueryParameter("APPID", "daeecc1588e9812e4eedc9644d47ee8e");
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
