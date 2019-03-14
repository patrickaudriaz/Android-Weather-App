package ch.heia.mobiledev.launchactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.os.Bundle;
import android.widget.Button;

public class LaunchActivity extends AppCompatActivity {

    // used for logging
    private static final String TAG = "LaunchActivity";

    // called when clicking on "launch_simple_up_button" button
    // (View view) as parameters to make it accessible from activity_main.xml
    private void onLaunchUpActivity() {
        Log.d(TAG, "----> Button \"launch_simple_up_button\" clicked");
        Intent intent = new Intent(this, SimpleUpActivity.class);
        startActivity(intent);
    }

    // called when clicking on "launch_simple_up_button" button
    // (View view) as parameters to make it accessible from activity_main.xml
    private void onLaunchPeerActivity() {
        Log.d(TAG, "----> Button \"launch_peer_button\" clicked");
        Intent intent = new Intent(this, PeerActivity.class);
        Bundle b = new Bundle();
        b.putInt("EXTRA_PEER_COUNT", 1); //Your id
        intent.putExtras(b); //Put your id to your next Intent
        startActivity(intent);
    }


    // called at application startup or after the app has been killed, must be overridden
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "-----------------LaunchActivity-------------------");
        Log.d(TAG, "onCreate called");
        super.onCreate(savedInstanceState);

        // load the activity layout (XML)
        setContentView(R.layout.activity_launch);

        final Button buttonSimpleUp = findViewById(R.id.launch_simple_up_button);
        buttonSimpleUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                onLaunchUpActivity();
            }
        });

        final Button buttonPeer = findViewById(R.id.launch_peer_button);
        buttonPeer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                onLaunchPeerActivity();
            }
        });
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
