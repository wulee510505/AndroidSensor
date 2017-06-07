package com.linc.androidsensor.activity.sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.linc.androidsensor.R;
import com.linc.androidsensor.base.BaseSensorActivity;

//see also http://www.leiphone.com/news/201504/rDi2229VWBuqBlyQ.html
public class GyroscopeActivity extends BaseSensorActivity {
    private static final String TAG = "GyroscopeActivity";
    private TextView mTvInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyroscope);
        mTvInfo = (TextView)findViewById(R.id.tv_info);
        if(mSensor == null) {
            mTvInfo.setText("No Orientation senor!");
            Log.d("linc","no this sensor.");
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float xValue = sensorEvent.values[0];// Angular speed around the x-axis
        float yValue = sensorEvent.values[1];// Angular speed around the y-axis
        float zValue = sensorEvent.values[2];// Angular speed around the z-axis
        mTvInfo.setText("xValue: "+xValue+"\nyValue: "+yValue+"\nzValue: "+zValue);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected int getDefaultSensor() {
        return Sensor.TYPE_GYROSCOPE;
    }
}
