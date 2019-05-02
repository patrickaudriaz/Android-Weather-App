package ch.heia.mobiledev.weatherForecast.data.City;


import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import ch.heia.mobiledev.weatherForecast.R;

// the generic types of your own FetchAsyncTask may vary based on your implementation
public class FetchAsyncTaskCity extends AsyncTask<Void, Void, ResponseCity> {
    // data members/fields initialized upon construction
    // data fields may store specific parameters for building the appropriate
    // URL depending on the web API

    // for observation of the results by the owner of the instance of FetchAsyncTask
    // the observation mechanism uses LiveData
    private final MutableLiveData<ResponseCity> mResponse = new MutableLiveData<>();
    private final Context context;

    // constructor
    public FetchAsyncTaskCity(Context context) {
        super();
        this.context = context;
    }

    // for observation by the view model
    public LiveData<ResponseCity> getResponse() {
        return mResponse;
    }

    @Override
    protected ResponseCity doInBackground(Void... voids) {
        // To be implemented
        //InputStream inputStream = context.getResources().openRawResource(R.raw.city_ch);
        String jsonString = readFileFromRawDirectory();
        //String jsonString = new Scanner(inputStream).useDelimiter("\\A").next();
        ResponseCity response = null;
        try {
            response = JsonParser.parse(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return response;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private String readFileFromRawDirectory(){

        int CITY = R.raw.city_ch;
        InputStream iStream = context.getResources().openRawResource(CITY);
        ByteArrayOutputStream byteStream = null;
        try {
            byte[] buffer = new byte[iStream.available()];
            iStream.read(buffer);
            byteStream = new ByteArrayOutputStream();
            byteStream.write(buffer);
            byteStream.close();
            iStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Objects.requireNonNull(byteStream).toString();
    }


    @Override
    protected void onPostExecute(ResponseCity response) {
        mResponse.setValue(response);
    }
}
