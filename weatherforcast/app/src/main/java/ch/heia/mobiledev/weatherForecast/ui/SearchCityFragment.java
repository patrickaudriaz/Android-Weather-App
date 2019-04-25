package ch.heia.mobiledev.weatherForecast.ui;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import ch.heia.mobiledev.weatherForecast.R;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SearchCityFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SearchCityFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchCityFragment extends Fragment {

    public SearchCityFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SearchCityFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchCityFragment newInstance() {
        SearchCityFragment fragment = new SearchCityFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_city, container, false);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        OnFragmentInteractionListener mListener = null;
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
