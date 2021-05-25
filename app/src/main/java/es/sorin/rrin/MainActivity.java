package es.sorin.rrin;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity
{
    private RadarView _radarView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _radarView = findViewById(R.id.Radar);

        new BluetoothDataReader(this);
    }

    public void updateRadar(float angle, float objectDistance)
    {
        _radarView.update(angle, objectDistance);
    }
}