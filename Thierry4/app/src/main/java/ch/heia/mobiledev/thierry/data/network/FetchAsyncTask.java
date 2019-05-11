package ch.heia.mobiledev.thierry.data.network;

import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import ch.heia.mobiledev.thierry.data.database.Entry;


// the generic types of your own FetchAsyncTask may vary based on your implementation
public class FetchAsyncTask extends AsyncTask<Void, Void, Response> {
	// data members/fields initialized upon construction
  // data fields may store specific parameters for building the appropriate 
  // URL depending on the web API
  
  // for observation of the results by the owner of the instance of FetchAsyncTask
  // the observation mechanism uses LiveData
	
	private final AppCompatActivity context;
	private final ListView contentList;
	private final String city;

	private final MutableLiveData<Response> mResponse = new MutableLiveData<>();
  private Response response;

    // constructor
  public FetchAsyncTask(AppCompatActivity context, ListView outList, String city) {
		this.city = city;
  	this.context = context;
  	this.contentList = outList;
  }

  // for observation by the view model
  public LiveData<Response> getResponse() {
    return mResponse;
  }
  
  public String getJSONasString(){
    try {
      return NetworkUtils.getResponseFromHttpUrl(NetworkUtils.getUrl(city));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
  
  @Override
  public Response doInBackground(Void... voids) {
    try {
        String APIResponse = NetworkUtils.getResponseFromHttpUrl(NetworkUtils.getUrl(city));
        response = JsonParser.parse(this.context, APIResponse);

    } catch (IOException e) {
        e.printStackTrace();
    } catch (JSONException e) {
        e.printStackTrace();
    }

    return response;
  }

	// When the AsyncTask is done fetching the data from the API, store the data in a list and
	// display it through the list adapter
  @Override
  protected void onPostExecute(Response response){
    mResponse.setValue(response);
		String[] measures = new String[1];
		
		try {
			measures = new String[response.getEntries().length];
			
			for (int i = 0; i < response.getEntries().length; i++ ) {
				Entry data = response.getEntry(i);
				String measure;
				
				// TEMPERATURE
				measure = "\n" + (int) data.getTemp() + " °C\n\n";
				
				// TIME
				measure  = measure + data.getDate() + "\n" ;
				
				// DESCRIPTION
				measure  = measure + data.getDesc() + "\n\n";
				
				// WIND
				measure  = measure + "Wind : " + (int) data.getWind() + " m/s\n";
				
				// PRESSURE
				measure  = measure + "Pressure : " + (int) data.getPress() + " hpa\n";
				
				// HUMIDITY
				measure  = measure + "Humidity : " + (int) data.getHum() + " %\n";
				
				measures[i] = measure;
			}
		}
		catch (NullPointerException e){
			measures[0] = "ERROR : City not found.";
		}
  
  
		ArrayList<String> weatherList = new ArrayList<>(Arrays.asList(measures));

		ArrayAdapter<String> listAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, weatherList);
	
		// Add more planets. If you passed a String[] instead of a List<String>
		// into the ArrayAdapter constructor, you must not add more items.
		// Otherwise an exception will occur.
	
		// Set the ArrayAdapter as the ListView's adapter.
		this.contentList.setAdapter(listAdapter);

  }
}
