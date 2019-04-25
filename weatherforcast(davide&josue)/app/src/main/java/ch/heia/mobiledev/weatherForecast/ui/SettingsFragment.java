package ch.heia.mobiledev.weatherForecast.ui;

import androidx.preference.PreferenceFragmentCompat;
import ch.heia.mobiledev.weatherForecast.R;

import android.os.Bundle;

@SuppressWarnings("WeakerAccess")
public class SettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
    }
}
