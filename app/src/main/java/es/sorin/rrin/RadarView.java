package es.sorin.rrin;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class RadarView extends View
{
    private final Paint _color;
    private float _sweepAngle;

    public RadarView(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        _color = new Paint();

        _color.setColor(0xFF17d801);
        _color.setStrokeWidth(10.0f);
        _color.setAntiAlias(true);

        _sweepAngle = 45;
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        final float width = getWidth();
        final float halfWidth = width / 2.0f;
        final float height = getHeight();
        final float halfHeight = height / 2.0f;

        //Solid background.
        canvas.drawARGB(255, 5, 4, 4);

        //Radar.
        _color.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(halfWidth, halfHeight, halfWidth - 20, _color);
        canvas.drawCircle(halfWidth, halfHeight, halfWidth - 120, _color);
        canvas.drawCircle(halfWidth, halfHeight, halfWidth - 220, _color);
        canvas.drawLine(halfWidth, 100, halfWidth, halfHeight - 100, _color);
        canvas.drawLine(halfWidth, halfHeight + 100, halfWidth, height - 100, _color);
        canvas.drawLine(0, halfHeight, halfWidth - 100, halfHeight, _color);
        canvas.drawLine(halfWidth + 100, halfHeight, width, halfHeight, _color);
        _color.setStyle(Paint.Style.FILL);
        canvas.drawCircle(halfWidth, halfHeight, 20, _color);

        //Sweep line.
        final float x = halfWidth + (float)Math.cos(_sweepAngle) * halfWidth;
        final float y = halfHeight - (float)Math.sin(_sweepAngle) * halfWidth;

        canvas.drawLine(halfWidth, halfHeight, x, y, _color);

        //Text.
        //TODO
    }
}