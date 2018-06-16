package alifyz.com.facerec;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.FaceDetector;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetectorOptions;

public class MainActivity extends AppCompatActivity {

    Button mProcessImage;
    ImageView mImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProcessImage = findViewById(R.id.button);
        mImage = findViewById(R.id.imgview);
    }


    public void startProcess(View view) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inMutable = true;

        Bitmap myDrawable = BitmapFactory.decodeResource(
                getApplicationContext().getResources(), R.drawable.default_photo, options);

        Paint myRectPaint = new Paint();
        myRectPaint.setStrokeWidth(5);
        myRectPaint.setColor(Color.BLUE);
        myRectPaint.setStyle(Paint.Style.STROKE);

        Bitmap tempBitmap = Bitmap.createBitmap(
                myDrawable.getWidth(), myDrawable.getHeight(), Bitmap.Config.RGB_565);

        Canvas canvas = new Canvas(tempBitmap);
        canvas.drawBitmap(myDrawable,0,0,null);

        FirebaseVisionFaceDetectorOptions mFaceOptions =
                new FirebaseVisionFaceDetectorOptions.Builder()
                        .setModeType(FirebaseVisionFaceDetectorOptions.ACCURATE_MODE)
                        .setLandmarkType(FirebaseVisionFaceDetectorOptions.ALL_LANDMARKS)
                        .setClassificationType(FirebaseVisionFaceDetectorOptions.ALL_CLASSIFICATIONS)
                        .setMinFaceSize(0.15f)
                        .setTrackingEnabled(true)
                        .build();

    }
}
