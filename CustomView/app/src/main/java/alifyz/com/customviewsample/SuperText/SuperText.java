package alifyz.com.customviewsample.SuperText;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatTextView;

public class SuperText extends AppCompatTextView {

    private Paint backgroundColor;

    public SuperText(Context context) {
        super(context);

        backgroundColor = new Paint();
        backgroundColor.setColor(0xffff0000);
        backgroundColor.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(0,0, getWidth(), getHeight(), backgroundColor);
        super.onDraw(canvas);
    }
}
