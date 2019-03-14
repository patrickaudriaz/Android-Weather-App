package ch.heia.mobiledev.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.widget.TextView;
import android.widget.EditText;
import android.view.View;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    // used for logging
    private static final String TAG = "Main ";

    // called at application startup or after the app has been killed, must be overridden
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // logging for question 1, check that onCreate() is called at application startup
        Log.d(TAG, "onCreate called");

        super.onCreate(savedInstanceState);
        // load the activity layout (XML)
        setContentView(R.layout.activity_main);
    }

    // called when clicking on "DISPLAY" button
    // (View view) as parameters to make it accessible from activity_main.xml
    public void getName(View view) {
        // get entered text from EditText
        EditText name = findViewById(R.id.name);
        // skip if empty
        if (name.getText().toString().trim().length() == 0)
            return;
        // send the name to be displayed
        updateTitle(name.getText().toString());
    }

    // to display the name
    private void  updateTitle(String text) {
        // get where the name will be displayed
        TextView title = findViewById(R.id.title);
        // logging test
        Log.d(TAG, "New text : " + text);
        // make a String before set it the label
        String displayedText = "Hello " + text + " !";
        // set the new name
        title.setText(displayedText);
    }

    // to reset title
    // same logic as "updateTitle"
    public void handleClear(View view) {
        TextView title = findViewById(R.id.title);
        title.setText(R.string.title);
    }

    // tests to visualize Android lifecycle, Override all lifecycle callback methods
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
