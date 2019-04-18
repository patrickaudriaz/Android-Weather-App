package ch.heia.mobiledev.thierry.data.network;

import android.os.AsyncTask;
import org.json.JSONException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
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
    private Response response;

    // constructor
  public FetchAsyncTask() {

      // ???????????????????????????????????????????????????

  }

  // for observation by the view model
  public LiveData<Response> getResponse() {
    return mResponse;
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
  protected void onPostExecute(Response response) {
    mResponse.setValue(response);
  }
}
