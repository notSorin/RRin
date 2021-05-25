package es.sorin.rrin;

public class RadarPoint
{
    private float _angle, _distance;
    private long _startTime;

    public RadarPoint(float angle, float distance)
    {
        _angle = angle;
        _distance = distance;
        _startTime = System.currentTimeMillis();
    }

    public long getLifeMs()
    {
        return System.currentTimeMillis() - _startTime;
    }

    public float getAngle()
    {
        return _angle;
    }

    public float getDistance()
    {
        return _distance;
    }
}