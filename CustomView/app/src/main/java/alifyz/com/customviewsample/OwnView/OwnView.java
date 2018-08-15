package alifyz.com.customviewsample.OwnView;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class OwnView extends View {

    private final int DEFAULT_SIZE = 100;

    public OwnView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.d("Width", MeasureSpec.toString(widthMeasureSpec));
        Log.d("Heigh", MeasureSpec.toString(heightMeasureSpec));

        int width = getMeasurentSize(widthMeasureSpec, DEFAULT_SIZE);
        int heigh = getMeasurentSize(heightMeasureSpec, DEFAULT_SIZE);

        setMeasuredDimension(width, heigh);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    //Common way to defined the Size of a Bound View
    private static int getMeasurentSize(int measure, int defaultSize) {

        int mode = MeasureSpec.getMode(measure);
        int size = MeasureSpec.getSize(measure);

        switch (mode) {
            case MeasureSpec.EXACTLY:
                return size;
            case MeasureSpec.AT_MOST:
                return Math.min(size, defaultSize);
            case MeasureSpec.UNSPECIFIED:
                return defaultSize;
            default:
                return defaultSize;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
