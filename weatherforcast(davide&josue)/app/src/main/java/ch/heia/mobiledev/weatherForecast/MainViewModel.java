package ch.heia.mobiledev.weatherForecast;

import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Objects;

import ch.heia.mobiledev.weatherForecast.data.database.Entry;
import ch.heia.mobiledev.weatherForecast.data.network.FetchAsyncTask;
import ch.heia.mobiledev.weatherForecast.data.City.FetchAsyncTaskCity;
import ch.heia.mobiledev.weatherForecast.data.network.Response;
import ch.heia.mobiledev.weatherForecast.data.City.ResponseCity;
import ch.heia.mobiledev.weatherForecast.ui.MainActivity;
import ch.heia.mobiledev.weatherForecast.ui.StartFragment;

public class MainViewModel extends ViewModel {
    // data members

    // used for logging
    private static final String TAG = MainActivity.class.getSimpleName();

    // for observation by the UI component
    private final MutableLiveData<Response> mResponse = new MutableLiveData<>();
    private final MutableLiveData<ResponseCity> mResponseCity = new MutableLiveData<>();

    // constructor
    public MainViewModel() {
    }

    // method called for fetching the data
    public void fetchData(LifecycleOwner lifecycleOwner, int city) {
        // async task used for fetching the data
        FetchAsyncTask fetchAsyncTask = new FetchAsyncTask(city);
        // observe results from the async task
        fetchAsyncTask.getResponse().observe(lifecycleOwner, response -> {
            Log.d(TAG, "Response received");
            mResponse.postValue(response);
        });
        fetchAsyncTask.execute();
    }

    // method called for fetching the data
    public void fetchCity(LifecycleOwner lifecycleOwner) {
        // async task used for fetching the data
        FetchAsyncTaskCity fetchAsyncTask = new FetchAsyncTaskCity(MainActivity.getAppContext());

        // observe results from the async task
        fetchAsyncTask.getResponse().observe(lifecycleOwner, response -> {
            Log.d(TAG, "Response received for city");
            mResponseCity.postValue(response);
        });
        fetchAsyncTask.execute();
    }

    // list of news entry to be observed by UI
    public LiveData<Response> getResponse() {
        return mResponse;

    }// list of news entry to be observed by UI
    public LiveData<ResponseCity> getResponseCity() {
        return mResponseCity;
    }

    // for accessing a specific entry
    public Entry getEntry(int index, Response.EntryFilter filter) {
        return Objects.requireNonNull(mResponse.getValue()).getEntry(index, filter);
    }

    public String getCity(String name) {
        return Objects.requireNonNull(mResponseCity.getValue()).getCityName(name);
    }

    public int getCityId(String name) {
        return Objects.requireNonNull(mResponseCity.getValue()).getIdFromName(name);
    }

    public void setCityId(int id){
        StartFragment start = new StartFragment();
        start.setCityId(id);
    }
}
