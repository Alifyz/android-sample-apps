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
public class MainFragment extends Fragment {

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_main, container, false);

        final ArrayList<Place> places = new ArrayList<Place>();

        places.add(new Place(R.string.firstMuseaumTitle, R.string.firstMuseaumDesc,
                R.drawable.webredone));
        places.add(new Place(R.string.secondMuseaumTitle, R.string.secondMuseaumDesc,
                R.drawable.british));
        places.add(new Place(R.string.thirdMuseaumTitle, R.string.thirdMuseaumDesc,
                R.drawable.ottwa));

        PlaceAdapter adapter = new PlaceAdapter(getActivity(), places);

        ListView listView = (ListView) rootView.findViewById(R.id.main);
        listView.setAdapter(adapter);

        return rootView;
    }
}
