package ch.heia.mobiledev.thierry;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.util.Log;

import ch.heia.mobiledev.thierry.database.Entry;
import ch.heia.mobiledev.thierry.network.FetchAsyncTask;
import ch.heia.mobiledev.thierry.network.Response;
import ch.heia.mobiledev.thierry.ui.MainActivity;

public class MainViewModel extends ViewModel {

  // used for logging
  private static final String TAG = MainActivity.class.getSimpleName();

  // for observation by the UI component
  private final MutableLiveData<Response> mResponse = new MutableLiveData<>();

  // constructor
  public MainViewModel() {

  }

      // method called for fetching the data
  public void fetchData(LifecycleOwner lifecycleOwner, String country) {
    // async task used for fetching the data
    FetchAsyncTask fetchAsyncTask = new FetchAsyncTask(country);
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
  public Entry getEntry(int index) {
    return mResponse.getValue().getEntry(index);
  }

}
