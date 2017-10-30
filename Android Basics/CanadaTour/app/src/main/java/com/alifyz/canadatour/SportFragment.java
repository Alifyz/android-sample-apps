package com.alifyz.canadatour;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SportFragment extends Fragment {

    public SportFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_main, container,false);

        final ArrayList<Place> sports = new ArrayList<Place>();

        sports.add(new Place(R.string.firstSportTitle, R.string.firstSportDescription,
                R.drawable.ski1));
        sports.add(new Place(R.string.secondSportTitle, R.string.secondSportDescription,
                R.drawable.ski2));


        PlaceAdapter adapter = new PlaceAdapter(getActivity(), sports);


        ListView listView = (ListView) rootView.findViewById(R.id.main);
        listView.setAdapter(adapter);


        return rootView;
    }

}
