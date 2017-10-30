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
public class RestaurantsFragment extends Fragment {


    public RestaurantsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_main, container, false);

        final ArrayList<Place> restaurants = new ArrayList<Place>();

        restaurants.add(new Place(R.string.firstRestaurantTitle, R.string.firstRestaurantDescription,
                R.drawable.cntower));
        restaurants.add(new Place(R.string.secondRestaurantTitle, R.string.secondRestaurantDescription,
                R.drawable.luma));


        PlaceAdapter adapter = new PlaceAdapter(getActivity(), restaurants);


        ListView listView = (ListView) rootView.findViewById(R.id.main);
        listView.setAdapter(adapter);


        return rootView;


    }

}
