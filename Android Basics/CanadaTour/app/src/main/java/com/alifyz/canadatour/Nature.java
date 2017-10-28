package com.alifyz.canadatour;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Nature extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_container);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new NatureFragment())
                .commit();


       /* ArrayList<Place> nature = new ArrayList<Place>();


        nature.add(new Place(R.string.firstNatureTitle,R.string.firstNatureDescription,
                R.drawable.alberta));


        PlaceAdapter adapter = new PlaceAdapter(this, nature);

        ListView listView = (ListView) findViewById(R.id.nature);
        listView.setAdapter(adapter);
*/


    }
}
