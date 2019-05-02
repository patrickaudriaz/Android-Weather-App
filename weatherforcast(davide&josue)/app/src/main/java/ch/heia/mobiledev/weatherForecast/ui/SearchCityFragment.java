package ch.heia.mobiledev.weatherForecast.ui;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.preference.PreferenceManager;

import ch.heia.mobiledev.weatherForecast.MainViewModel;
import ch.heia.mobiledev.weatherForecast.R;
import ch.heia.mobiledev.weatherForecast.data.City.ResponseCity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.SearchView;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SearchCityFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the  factory method to
 * create an instance of this fragment.
 */
@SuppressWarnings("unused")
public class SearchCityFragment extends Fragment implements SearchView.OnQueryTextListener {

    private static final String TAG = SearchCityFragment.class.getSimpleName();
    private View view;


    public SearchCityFragment() {
        // Required empty public constructor
    }

// --Commented out by Inspection START (25.04.2019 22:18):
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @return A new instance of fragment SearchCityFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static SearchCityFragment newInstance() {
//        SearchCityFragment fragment = new SearchCityFragment();
//        Bundle args = new Bundle();
//        fragment.setArguments(args);
//        return fragment;
//    }
// --Commented out by Inspection STOP (25.04.2019 22:18)

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_search_city, container, false);

        MainViewModel model = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(MainViewModel.class);

        model.fetchCity(this);
        model.getResponseCity().observe(this, this::getCityName);

        SearchView simpleSearchView = view.findViewById(R.id.searchCity); // inititate a search view
        simpleSearchView.setOnQueryTextListener(this);

        return view;
    }

    private void getCityName(ResponseCity result) {
        Log.d(TAG, "Observe City");

    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        CheckedTextView checkedTextView = view.findViewById(R.id.checkedTextView); // inititate a search view

        MainViewModel model = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(MainViewModel.class);

        checkedTextView.setText(model.getCity(newText));
        checkedTextView.setOnClickListener(v -> {
            model.setCityId(model.getCityId(newText));
            SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getActivity());
            SharedPreferences.Editor editor = settings.edit();
            editor.clear();
            editor.putString("city_displayed", newText);
            editor.apply();
            // Commit the edits!
            editor.commit();
        });
        return false;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    private interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
