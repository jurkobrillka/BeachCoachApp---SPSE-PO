package sk.upjs.ics.android.beachcoachapp.testovania.servis;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sk.upjs.ics.android.beachcoachapp.R;


public class TestServisTestovanieFragment extends Fragment {



    public TestServisTestovanieFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_test_servis_testovanie, container, false);
    }
}