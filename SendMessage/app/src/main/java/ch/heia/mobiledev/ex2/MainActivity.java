package ch.heia.mobiledev.ex2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.util.Log;
import android.widget.EditText;
import android.view.View;
import android.os.Bundle;
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onSendMessage(View view) {
        EditText email = findViewById(R.id.email);
        if (email.getText().toString().trim().length() == 0)
            return;

        String emailTo = email.getText().toString();
        Log.d(TAG, "Email to : " + emailTo);

        // create the implicit intent
        Intent intent = new Intent();

        // set the action and type
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/html");

        // set parameters as extra
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] { emailTo});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Important");
        intent.putExtra(Intent.EXTRA_TEXT, "Message de mon application");

        // launch the activity
        // startActivity(intent);
        startActivity(Intent.createChooser(intent, "Send Email"));
    }

}
