package alifyz.com.imageslider;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    ViewPager mViewPager;
    ImagePageAdapter mAdapter;
    int[] mResourceId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mResourceId = new int[] {
                R.drawable.image1,
                R.drawable.image2,
                R.drawable.image3,
                R.drawable.image4,
                R.drawable.image5,
                R.drawable.image6};

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mAdapter = new ImagePageAdapter(this, mResourceId);
        mViewPager.setAdapter(mAdapter);

    }
}
