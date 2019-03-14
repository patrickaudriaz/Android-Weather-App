package ch.heia.mobiledev.launchactivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

public class PeerActivity extends AppCompatActivity {

    // int peerCount = 0;

    // used for logging
    private static final String TAG = "PeerActivity";

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

    // called when clicking on "launch_new_peer_button" button
    private void onLaunchActivity() {
        Intent intent = new Intent(this, PeerActivity.class);
        Bundle b = new Bundle();
        TextView countText = findViewById(R.id.count);
        int count = -1;
        try{
            count = Integer.parseInt(countText.getText().toString());
        }
        catch(Exception e){
            Log.d(TAG, "Couldn't parse counter");
        }
        b.putInt("count", count+1); //Your id
        intent.putExtras(b); //Put your id to your next Intent
        startActivity(intent);
        Log.d(TAG, "New Activity created");
    }

    // called at application startup or after the app has been killed, must be overridden
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "------------------------------------");
        Log.d(TAG, "onCreate called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peer);

        final Button button = findViewById(R.id.launch_new_peer_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                onLaunchActivity();
            }
        });

        TextView counter = findViewById(R.id.count);
        Bundle b = getIntent().getExtras();
        int count = -1;
        if(b != null)
            count = b.getInt("count");
        counter.setText(String.valueOf(count));
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        Log.d(TAG, "onSaveInstanceState called");
        super.onSaveInstanceState(outState);
        // outState.putInt("peerCounter", peerCount);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.d(TAG, "onRestoreInstanceState called");
        super.onRestoreInstanceState(savedInstanceState);
        // peerCount = savedInstanceState.getInt("peerCounter");
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
