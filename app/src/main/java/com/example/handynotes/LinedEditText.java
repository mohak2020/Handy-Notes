package com.example.handynotes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class LinedEditText extends AppCompatEditText {

    private static final String TAG = "LinedEditText";


    private Rect mRect;
    private Paint mPaint;


    public LinedEditText(Context context, AttributeSet attrs) {
        super(context, attrs);

        mRect = new Rect();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(2);
        mPaint.setColor(0xFFFFD966); // Color of the lines on paper
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // get the height of the view
        int height = ((View)this.getParent()).getHeight();

        int lineHeight = getLineHeight();
        int numberOfLines = height / lineHeight;

        Rect r = mRect;
        Paint paint = mPaint;

        int baseline = getLineBounds(0, r);

        Log.d(TAG, "onDraw: "+baseline);

        for (int i = 0; i < numberOfLines; i++) {

            canvas.drawLine(r.left, baseline + 1, r.right, baseline + 1, paint);

            baseline += lineHeight;
            Log.d(TAG, "onDraw: "+baseline);
        }
        super.onDraw(canvas);


    }
}
