package ch.heia.mobiledev.weatherForecast.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.threeten.bp.LocalDate;

import ch.heia.mobiledev.weatherForecast.MainViewModel;
import ch.heia.mobiledev.weatherForecast.R;
import ch.heia.mobiledev.weatherForecast.data.database.Entry;
import ch.heia.mobiledev.weatherForecast.data.network.Response;


/**
 * A simple {@link Fragment} subclass.
 */
@SuppressWarnings("WeakerAccess")
public class StartFragment extends Fragment {

    private AdapterForRecyclerView mAdapterHour;
    private AdapterForRecyclerView mAdapterDays;
    private int selectedDayWeather;
    private int daysToShow = 0;
    private static int cityId = 7285870; // id of fribourg

    public StartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_start, container, false);

        // Setup adapter and recyclerview
        RecyclerView scrollViewHour = view.findViewById(R.id.scrollViewHour);
        RecyclerView scrollViewDay = view.findViewById(R.id.scrollViewDay);

        // 2. set layoutManger
        scrollViewHour.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        scrollViewDay.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        // 3. create an adapter
        mAdapterHour = new AdapterForRecyclerView(Objects.requireNonNull(getContext()), this::selectHourWeather, this::hoursFilter, AdapterForRecyclerView.ListType.HOURS);
        mAdapterDays = new AdapterForRecyclerView(getContext(), this::selectDayWeather, this::daysFilter, AdapterForRecyclerView.ListType.DAYS);
        // 4. set adapter
        scrollViewHour.setAdapter(mAdapterHour);
        scrollViewDay.setAdapter(mAdapterDays);

        MainViewModel model = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(MainViewModel.class);
        model.fetchData(this, cityId);
        model.getResponse().observe(this, this::updateWeatherView);
        return view;
    }

    private void updateWeatherView(Response result) {
        mAdapterHour.swapResponse(result);
        mAdapterDays.swapResponse(result);
        // Manage if there are no connexion
        if (result != null) {
            selectHourWeather(selectedDayWeather);
        }
    }

    private void selectHourWeather(int i) {
        selectedDayWeather = i;
        ImageView image = Objects.requireNonNull(this.getView()).findViewById(R.id.main_weather);
        TextView title = this.getView().findViewById(R.id.main_title);
        TextView temperature = this.getView().findViewById(R.id.main_temp);
        TextView humidity = this.getView().findViewById(R.id.main_humidity);
        TextView wind = this.getView().findViewById(R.id.main_wind);
        TextView pressure = this.getView().findViewById(R.id.main_pressure);
        TextView tempMin = this.getView().findViewById(R.id.main_temp_min);
        TextView tempMax = this.getView().findViewById(R.id.main_temp_max);

        MainViewModel model = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(MainViewModel.class);

        Entry entry = model.getEntry(i, this::hoursFilter);

        title.setText(entry.getDateTime());
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        if (prefs.getBoolean("pref_show_temp", false)) {
            temperature.setText(String.format(getString(R.string.main_info_temperature), entry.getTemp()));
        } else {
            temperature.setText("");
        }
        if (prefs.getBoolean("pref_show_humidity", false)) {
            humidity.setText(String.format(getString(R.string.main_info_humidity), entry.getHumidity()));
        } else {
            humidity.setText("");
        }
        if (prefs.getBoolean("pref_show_wind", false)) {
            wind.setText(String.format(getString(R.string.main_info_wind), entry.getWind_speed()));
        } else {
            wind.setText("");
        }
        if (prefs.getBoolean("pref_show_pressure", false)) {
            pressure.setText(String.format(getString(R.string.main_info_pressure), entry.getPressure()));
        } else {
            pressure.setText("");
        }
        if (prefs.getBoolean("pref_show_temp_min_max", false)) {
            tempMin.setText(String.format(getString(R.string.main_info_temp_min), entry.getTemp_min()));
            tempMax.setText(String.format(getString(R.string.main_info_temp_max), entry.getTemp_max()));
        } else {
            tempMin.setText("");
            tempMax.setText("");
        }

        StartFragment.setWeatherPicture(entry.getIcon(), image, Objects.requireNonNull(getContext()));
    }

    private void selectDayWeather(int i) {
        daysToShow = i;
        mAdapterHour.notifyDataSetChanged();
    }

    public static void setWeatherPicture(String weather, ImageView image, Context cont) {
        int pictureID = cont.getResources().getIdentifier("w" + weather, "drawable", cont.getPackageName());
        if (pictureID == 0) {
            pictureID = R.drawable.w50d;
        }
        image.setImageDrawable(cont.getDrawable(pictureID));
    }

    private boolean daysFilter(Entry entry, boolean isFirst, boolean isLast) {
        if (isFirst && entry.getHour() > 12) {
            return true;
        }
        if (isLast && entry.getHour() < 12) {
            return true;
        }
        return entry.getHour() == 12;
    }

    private boolean hoursFilter(Entry entry, @SuppressWarnings("unused") boolean isFirst, @SuppressWarnings("unused") boolean isLast) {
        LocalDate today = LocalDate.now();
        today = today.plusDays(daysToShow);
        Log.d("StartFragment", today.toString());
        return entry.getDate().contains(today.toString());
    }

    public void setCityId(int id){
        cityId = id;
    }
}
