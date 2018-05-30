package alifyz.com.horizontalviewpager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.omega_r.libs.omegarecyclerview.viewpager.OmegaPagerRecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerViewAdapter mAdapter;
    OmegaPagerRecyclerView mRecyclerView;
    ArrayList<Integer> mResourceId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mResourceId = new ArrayList<>();

        mResourceId.add(R.drawable.image1);
        mResourceId.add(R.drawable.image2);
        mResourceId.add(R.drawable.image3);
        mResourceId.add(R.drawable.image4);
        mResourceId.add(R.drawable.image5);
        mResourceId.add(R.drawable.image6);

        mAdapter = new RecyclerViewAdapter(this, mResourceId);

        mRecyclerView = findViewById(R.id.recyclerview);

        mRecyclerView.setAdapter(mAdapter);
    }
}
