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


// the generic types of your own FetchAsyncTask may vary based on your implementation
public class FetchAsyncTask extends AsyncTask<Void, Void, Response> {
  // data members/fields initialized upon construction
  // data fields may store specific parameters for building the appropriate 
  // URL depending on the web API
  
  // for observation of the results by the owner of the instance of FetchAsyncTask
  // the observation mechanism uses LiveData
	
	AppCompatActivity context;
	ListView contentList;
	private ArrayAdapter<String> listAdapter;
  
  private final MutableLiveData<Response> mResponse = new MutableLiveData<>();
  private Response response;

    // constructor
  public FetchAsyncTask(AppCompatActivity context, ListView outList) {
  	this.context = context;
  	this.contentList = outList;
  }

  // for observation by the view model
  public LiveData<Response> getResponse() {
    return mResponse;
  }
  
  public String getJSONasString(){
    try {
      return NetworkUtils.getResponseFromHttpUrl(NetworkUtils.getUrl());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
  
  @Override
  public Response doInBackground(Void... voids) {
    try {
        String APIResponse = NetworkUtils.getResponseFromHttpUrl(NetworkUtils.getUrl());
        response = JsonParser.parse(APIResponse);

    } catch (IOException e) {
        e.printStackTrace();
    } catch (JSONException e) {
        e.printStackTrace();
    }

    return response;
  }

  @Override
  protected void onPostExecute(Response response){
    mResponse.setValue(response);
	
		// Create and populate a List of planet names.
		String[] planets = new String[] { "Mercury", "Venus", "Earth", "Mars",
						"Jupiter", "Saturn", "Uranus", "Neptune"};
		ArrayList<String> planetList = new ArrayList<String>();
		planetList.addAll( Arrays.asList(planets) );
	
		// Create ArrayAdapter using the planet list.
		listAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, planetList);
	
		// Add more planets. If you passed a String[] instead of a List<String>
		// into the ArrayAdapter constructor, you must not add more items.
		// Otherwise an exception will occur.
	
		// Set the ArrayAdapter as the ListView's adapter.
		this.contentList.setAdapter( listAdapter );
  }
}
