package alifyz.com.customviewpager;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rbrooks.indefinitepagerindicator.IndefinitePagerIndicator;

public class MainActivity extends AppCompatActivity {

    ViewPager mViewPager;
    ViewPagerAdapter mAdapter;
    IndefinitePagerIndicator mIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = findViewById(R.id.viewpager);
        mIndicator = findViewById(R.id.viewpager_indicator);

        mAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mIndicator.attachToViewPager(mViewPager);
    }
}
