package ch.heia.mobiledev.weatherForecast.data.network;

import android.os.AsyncTask;

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
    private final int city;

    // constructor
    public FetchAsyncTask(int city) {
        super();
        this.city = city;
    }

    // for observation by the view model
    public LiveData<Response> getResponse() {
        return mResponse;
    }

    @Override
    protected Response doInBackground(Void... voids) {
        // To be implemented
        Response response = null;
        try {
            String APIResponse = NetworkUtils.getResponseFromHttpUrl(NetworkUtils.getUrl(city));
            response = JsonParser.parse(APIResponse);
        } catch (Exception e) {
            e.printStackTrace();
            //mErrorString = e.toString();
        }
        return response;
    }

    @Override
    protected void onPostExecute(Response response) {
        mResponse.setValue(response);
    }
}
