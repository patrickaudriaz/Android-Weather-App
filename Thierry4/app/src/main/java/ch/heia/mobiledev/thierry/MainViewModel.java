/*package ch.heia.mobiledev.thierry;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.util.Log;

import ch.heia.mobiledev.thierry.data.database.Entry;
import ch.heia.mobiledev.thierry.data.network.FetchAsyncTask;
import ch.heia.mobiledev.thierry.data.network.Response;
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
*/