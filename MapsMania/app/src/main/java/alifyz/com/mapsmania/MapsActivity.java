package alifyz.com.mapsmania;

import android.content.Intent;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MapsActivity extends AppCompatActivity {


    private final int PLACE_PICKER_ID = 1;
    private PlacePicker.IntentBuilder mPickLocation;

    @BindView(R.id.btn_permission)
    Button mGetLocation;

    @BindView(R.id.txt_lastLocation)
    TextView mLocationName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        ButterKnife.bind(this);

        mGetLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPickLocation = new PlacePicker.IntentBuilder();
                try {
                    startActivityForResult(mPickLocation.build(MapsActivity.this), PLACE_PICKER_ID);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == PLACE_PICKER_ID){
            if(resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, this);
                mLocationName.setText(place.getAddress());
            }
        }
    }
}
