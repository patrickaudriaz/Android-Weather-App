package ch.heia.mobiledev.launchactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import android.view.MenuItem;
import android.os.Bundle;
import android.util.Log;

public class SimpleUpActivity extends AppCompatActivity {

    // used for logging
    private static final String TAG = "SimpleUpActivity";

    // Navigate Up to Parent Activity
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "--> Back...");
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // called at application startup or after the app has been killed, must be overridden
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "------------------------------------");
        Log.d(TAG, "onCreate called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_up);

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        Log.d(TAG, "onSaveInstanceState called");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.d(TAG, "onRestoreInstanceState called");
        super.onRestoreInstanceState(savedInstanceState);
    }

    // tests to visualize Android lifecycle, override all lifecycle callback methods
    @Override
    protected void onStart()  {
        Log.d(TAG, "onStart called");
        super.onStart();

    }

    @Override
    protected void onResume()  {
        Log.d(TAG, "onResume called");
        super.onResume();

    }

    @Override
    protected void onPause()  {
        Log.d(TAG, "onPause called");
        super.onPause();
    }

    @Override
    protected void onStop()  {
        Log.d(TAG, "onStop called");
        super.onStop();
    }

    @Override
    protected void onDestroy()  {
        Log.d(TAG, "onDestroy called");
        super.onDestroy();
    }

    @Override
    protected void onRestart()  {
        Log.d(TAG, "onRestart called");
        super.onRestart();
    }
}
