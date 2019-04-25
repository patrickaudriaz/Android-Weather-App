package ch.heia.mobiledev.thierry.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import ch.heia.mobiledev.thierry.R;
import ch.heia.mobiledev.thierry.data.network.FetchAsyncTask;

public class MainActivity extends AppCompatActivity
				implements NavigationView.OnNavigationItemSelectedListener {

    private String search = "";
    private Toolbar myToolbar;
    // used for logging
    private static final String TAG = "MainActivity";
    String city = "Fribourg";

	private ListView listView;
    private ArrayAdapter<String> listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main);
			Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
			setSupportActionBar(toolbar);

			myToolbar = (Toolbar) findViewById(R.id.toolbar);
			setSupportActionBar(myToolbar);
			Objects.requireNonNull(getSupportActionBar()).setTitle(city);

			if (savedInstanceState != null) {
				toolbar.setTitle(savedInstanceState.getString("location"));
				city = savedInstanceState.getString("location");
			}
	
	 		ListView mainContent = findViewById(R.id.listview_body);

			FetchAsyncTask asyncTask = new FetchAsyncTask(this, mainContent, city);
			asyncTask.execute();

			DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
			ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
							this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
			drawer.addDrawerListener(toggle);
			toggle.syncState();

			NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
			navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void reload(){
			this.recreate();
		}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.nav_bar_search) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Rechercher une ville");

            // Set up the input
            final EditText input = new EditText(this);
            // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            builder.setView(input);

            // Set up the buttons
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    search = input.getText().toString();
                    city = search;
                    Log.d(TAG, "----> " + search);
                    myToolbar = (Toolbar) findViewById(R.id.toolbar);
                    setSupportActionBar(myToolbar);
                    Objects.requireNonNull(getSupportActionBar()).setTitle(search);
										reload();
                }
            });
            builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

					builder.show();

				}

			if (id == R.id.menu_refresh) {
				reload();
			}

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString("location", city);
        super.onSaveInstanceState(savedInstanceState);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_settings) {
            Log.d(TAG, "---> Button \"nav_settings\" clicked");
            Intent intent = new Intent(this, ch.heia.mobiledev.thierry.ui.SettingsActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_about) {
            Log.d(TAG, "---> Button \"nav_about\" clicked");
            Intent intent = new Intent(this, ch.heia.mobiledev.thierry.ui.AboutActivity.class);
            startActivity(intent);
        } else if(id == R.id.nav_home){
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
