package es.sorin.rrin;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class RadarView extends View
{
    private Paint _red;

    public RadarView(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        _red = new Paint();

        _red.setColor(0xFFDD0000);
        _red.setStyle(Paint.Style.STROKE);
        _red.setStrokeWidth(10.0f);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        final float width = getWidth();
        final float halfWidth = width / 2.0f;
        final float height = getHeight();
        final float halfHeight = height / 2.0f;

        canvas.drawARGB(255, 5, 4, 4);
        canvas.drawCircle(halfWidth, halfHeight, halfWidth - 20, _red);
        canvas.drawCircle(halfWidth, halfHeight, halfWidth - 120, _red);
        canvas.drawCircle(halfWidth, halfHeight, halfWidth - 220, _red);
        canvas.drawPoint(halfWidth, halfHeight, _red);
        canvas.drawLine(halfWidth, 100, halfWidth, height - 100, _red);
        canvas.drawLine(0, halfHeight, width, halfHeight, _red);
    }
}