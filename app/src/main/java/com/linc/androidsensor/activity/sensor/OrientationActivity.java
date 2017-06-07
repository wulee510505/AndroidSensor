package com.linc.androidsensor.activity.sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

import com.linc.androidsensor.R;
import com.linc.androidsensor.base.BaseSensorActivity;

public class OrientationActivity extends BaseSensorActivity {
    private TextView mTvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orientation);
        mTvInfo = (TextView)findViewById(R.id.tv_info);
        if(mSensor == null) {
            mTvInfo.setText("No Orientation senor!");
            Log.d("linc","no this sensor.");
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Log.d("linc", "value size: " + sensorEvent.values.length);
        float accuracy = sensorEvent.accuracy;
        float xValue = sensorEvent.values[0];// Azimuth, angle between the magnetic
        // north direction and the y-axis, around the z-axis (0 to 359).
        // 0=North, 90=East, 180=South, 270=West
        float yValue = sensorEvent.values[1];//Pitch, rotation around x-axis (-180 to 180),
        // with positive values when the z-axis moves toward the y-axis.
        float zValue = sensorEvent.values[2];//Roll, rotation around the y-axis (-90 to 90)
        // increasing as the device moves clockwise.
        mTvInfo.setText("x轴： "+xValue+"  y轴： "+yValue+"  z轴： "+zValue);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected int getDefaultSensor() {
        return Sensor.TYPE_ORIENTATION;
    }

}
