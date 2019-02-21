package ch.heia.mobiledev.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.widget.TextView;
import android.widget.EditText;
import android.view.View;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "HelloWorld ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getName(View view) {
        EditText name = findViewById(R.id.name);
        if (name.getText().toString().trim().length() == 0)
            return;
        updateTitle(name.getText().toString());
    }


    private void  updateTitle(String text) {
        TextView title = findViewById(R.id.title);
        Log.d(TAG, "New text : " + text);
        String displayedText = "Hello " + text + " !";
        title.setText(displayedText);
    }

    public void handleClear(View view) {
        TextView title = findViewById(R.id.title);
        title.setText(R.string.title);
    }
}
