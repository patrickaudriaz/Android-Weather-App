package ch.heia.mobiledev.thierry.network;

import android.os.AsyncTask;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

// the generic types of your own FetchAsyncTask may vary based on your implementation
public class FetchAsyncTask extends AsyncTask<Void, Void, Response> {
  // data members/fields initialized upon construction
  // data fields may store specific parameters for building the appropriate 
  // URL depending on the web API
  
  // for observation of the results by the owner of the instance of FetchAsyncTask
  // the observation mechanism uses LiveData
  private final MutableLiveData<Response> mResponse = new MutableLiveData<>();
  
  // constructor
  public FetchAsyncTask() {
    
  }
  
  // for observation by the view model
  public LiveData<Response> getResponse() {
    return mResponse;
  }

  @Override
  protected Response doInBackground(Void... voids) {
    // To be implemented
    return null;
  }

  @Override
  protected void onPostExecute(Response response) {
    mResponse.setValue(response);
  }
}
