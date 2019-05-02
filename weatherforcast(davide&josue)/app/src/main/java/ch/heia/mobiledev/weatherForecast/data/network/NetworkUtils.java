package ch.heia.mobiledev.weatherForecast.data.network;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

class NetworkUtils {
    // data members

    // constant strings that are URL related
    // the definition depends on each web API
    private static final String API_HOST = "https://api.openweathermap.org";
    private static final String API_PATH = "/data/2.5/forecast";

    private static final String CITY_PARAM = "id";
    private static final String API_KEY = "appid";


    // Retrieves the proper URL to query the web API
    // if you received query parameters from other parts of the application
    // you must pass these parameters as arguments to this method
    static URL getUrl(int city) {
        return buildUrlWithQueryParameters(city);
    }

    // Build the url for specific query parameters
    private static URL buildUrlWithQueryParameters(int city) {
        // use the Uri class for building the uri to be used
        // (using parse()/buildUpon()/appendXXX() methods)

        Uri uri = Uri.parse(API_HOST)
                .buildUpon()
                .path(API_PATH)
                .appendQueryParameter(CITY_PARAM, Integer.toString(city))
                .appendQueryParameter(API_KEY, "c6f45040a15c88e03a0a7638bb1ccaef")
                .build();


        try {
            // build the URL instance based on the Uri instance
            // and return it to the caller
            return new URL(uri.toString());
        } catch (MalformedURLException e) {
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
        } finally {

            urlConnection.disconnect();
        }
    }
}
