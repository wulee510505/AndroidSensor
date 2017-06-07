package com.linc.androidsensor.activity.sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

import com.linc.androidsensor.R;
import com.linc.androidsensor.base.BaseSensorActivity;

public class MagneticFleldActivity extends BaseSensorActivity {
    private TextView mTvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magnetic_fleld);
        mTvInfo = (TextView)findViewById(R.id.tv_info);
        if(mSensor == null) {
            mTvInfo.setText("No Magnetic Fleld senor!");
            Log.d("linc","no this sensor.");
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Log.d("linc", "value size: " + sensorEvent.values.length);
        float accuracy = sensorEvent.accuracy;

        mTvInfo.setText("当前磁场强度：" + sensorEvent.values[0] );
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected int getDefaultSensor() {
        return Sensor.TYPE_MAGNETIC_FIELD;
    }

}
