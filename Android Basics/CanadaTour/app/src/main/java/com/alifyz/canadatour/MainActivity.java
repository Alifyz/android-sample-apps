package com.alifyz.canadatour;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.viewpager_root, new MainFragment())
                .commit();

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager_root);
        PlacePageAdapter pagerAdapter = new PlacePageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);


    }
}
