package com.alifyz.canadatour;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayList<Place> places = new ArrayList<Place>();

        places.add(new Place(R.string.firstMuseaumTitle, R.string.firstMuseaumDesc,
                R.drawable.standrew));
        places.add(new Place(R.string.secondMuseaumTitle, R.string.secondMuseaumDesc,
                R.drawable.space));
        places.add(new Place(R.string.thirdMuseaumTitle, R.string.thirdMuseaumDesc,
                R.drawable.single));

        PlaceAdapter adapter = new PlaceAdapter(this, places);


        ListView listView = (ListView) findViewById(R.id.main);
        listView.setAdapter(adapter);
    }
}
