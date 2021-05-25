package es.sorin.rrin;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;

public class RadarView extends View
{
    private final Paint _color;
    private final Paint _red;
    private float _sweepAngle;
    private ArrayList<RadarPoint> _points;

    public RadarView(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        _color = new Paint();
        _red = new Paint();

        _color.setColor(0xFF17d801);
        _red.setColor(0xFFea3019);
        _color.setStrokeWidth(10.0f);
        _color.setAntiAlias(true);
        _red.setAntiAlias(true);
        _color.setTextSize(25.0f);
        _red.setStyle(Paint.Style.FILL);

        _sweepAngle = 0;
        _points = new ArrayList<>();
    }

    /*@Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN)
        {
            update((float)Math.random() * 360.0f, (float)Math.random() * 40.0f);
        }

        return true;
    }*/

    @Override
    protected void onDraw(Canvas canvas)
    {
        final float width = getWidth();
        final float halfWidth = width / 2.0f;
        final float height = getHeight();
        final float halfHeight = height / 2.0f;
        final float maxRadius = halfWidth - 20;
        final float radius = maxRadius / 3.0f;

        //Solid background.
        canvas.drawARGB(255, 5, 4, 4);

        //Radar.
        _color.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(halfWidth, halfHeight, radius, _color);
        canvas.drawCircle(halfWidth, halfHeight, radius * 2, _color);
        canvas.drawCircle(halfWidth, halfHeight, radius * 3, _color);
        _color.setAlpha(80);
        canvas.drawLine(halfWidth, 100, halfWidth, halfHeight - 100, _color);
        canvas.drawLine(halfWidth, halfHeight + 100, halfWidth, height - 100, _color);
        canvas.drawLine(0, halfHeight, halfWidth - 100, halfHeight, _color);
        canvas.drawLine(halfWidth + 100, halfHeight, width, halfHeight, _color);
        _color.setAlpha(255);
        _color.setStyle(Paint.Style.FILL);
        canvas.drawCircle(halfWidth, halfHeight, 20, _color);

        //Sweep line.
        final float x = halfWidth + (float)Math.cos(Math.toRadians(_sweepAngle)) * halfWidth;
        final float y = halfHeight - (float)Math.sin(Math.toRadians(_sweepAngle)) * halfWidth;

        canvas.drawLine(halfWidth, halfHeight, x, y, _color);

        //Text.
        canvas.drawText("0cm", halfWidth + 20, halfHeight + 30, _color);
        canvas.drawText("20cm", halfWidth + radius + 10, halfHeight + 30, _color);
        canvas.drawText("40cm", halfWidth + radius * 2 + 10, halfHeight + 30, _color);

        //Check if any available points need to be removed.
        for(int i = 0; i < _points.size(); )
        {
            if(_points.get(i).getLifeMs() > 5000)
            {
                _points.remove(i);
            }
            else
            {
                i++;
            }
        }

        //Draw detected points.
        for(RadarPoint p : _points)
        {
            final float length = (p.getDistance() / 40.0f) * maxRadius;
            final float px = halfWidth + (float)Math.cos(Math.toRadians(p.getAngle())) * length;
            final float py = halfHeight - (float)Math.sin(Math.toRadians(p.getAngle())) * length;

            canvas.drawCircle(px, py, 20, _red);
        }
    }

    public void update(float angle, float objectDistance)
    {
        _sweepAngle = angle;

        if(objectDistance <= 40)
        {
            //Remove the oldest point from the array if the max size has been reached.
            if(_points.size() > 10)
            {
                _points.remove(0);
            }

            _points.add(new RadarPoint(angle, objectDistance));
        }

        postInvalidate();
    }
}