package ch.heia.mobiledev.weatherForecast;

import android.util.Log;

import java.util.Objects;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ch.heia.mobiledev.weatherForecast.data.database.Entry;
import ch.heia.mobiledev.weatherForecast.data.network.FetchAsyncTask;
import ch.heia.mobiledev.weatherForecast.data.network.Response;
import ch.heia.mobiledev.weatherForecast.ui.MainActivity;

public class MainViewModel extends ViewModel {
    // data members

    // used for logging
    private static final String TAG = MainActivity.class.getSimpleName();

    // for observation by the UI component
    private final MutableLiveData<Response> mResponse = new MutableLiveData<>();

    // constructor
    public MainViewModel() {
    }

    // method called for fetching the data
    public void fetchData(LifecycleOwner lifecycleOwner, String city) {
        // async task used for fetching the data
        FetchAsyncTask fetchAsyncTask = new FetchAsyncTask(city);
        // observe results from the async task
        fetchAsyncTask.getResponse().observe(lifecycleOwner, response -> {
            Log.d(TAG, "Response received");
            mResponse.postValue(response);
        });
        fetchAsyncTask.execute();
    }

    // list of news entry to be observed by UI
    public LiveData<Response> getResponse() {
        return mResponse;
    }

    // for accessing a specific entry
    public Entry getEntry(int index, Response.EntryFilter filter) {
        return Objects.requireNonNull(mResponse.getValue()).getEntry(index, filter);
    }
}
