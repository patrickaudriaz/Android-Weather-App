package ch.heia.mobiledev.weatherForecast.ui;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.jakewharton.threetenabp.AndroidThreeTen;

import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import ch.heia.mobiledev.weatherForecast.R;

public class MainActivity extends AppCompatActivity {
    private AppBarConfiguration mAppBarConfig = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavHostFragment host = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.host_fragment);
        NavController navController = Objects.requireNonNull(host).getNavController();

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);

        mAppBarConfig = new AppBarConfiguration.Builder(R.id.launch_dest).
                setDrawerLayout(drawerLayout).
                build();
        setupNavigationMenu(navController);
        setupActionBar(mAppBarConfig, navController);

        AndroidThreeTen.init(this);
    }

    private void setupNavigationMenu(NavController navController) {
        NavigationView view = findViewById(R.id.nav_view);
        view.getMenu().add("Test");
        NavigationUI.setupWithNavController(view, navController);
    }

    private void setupActionBar(AppBarConfiguration appBarConfig, NavController navController) {
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfig);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavHostFragment host = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.host_fragment);
        NavController navController = Objects.requireNonNull(host).getNavController();
        boolean supported = NavigationUI.navigateUp(navController, mAppBarConfig);
        if (supported) {
            return true;
        }
        return super.onSupportNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        NavHostFragment host = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.host_fragment);
        NavController navController = Objects.requireNonNull(host).getNavController();

        return NavigationUI.onNavDestinationSelected(item, navController) || super.onOptionsItemSelected(item);
    }
}
