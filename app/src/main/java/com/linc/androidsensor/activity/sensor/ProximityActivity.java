package com.linc.androidsensor.activity.sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

import com.linc.androidsensor.R;
import com.linc.androidsensor.base.BaseSensorActivity;

public class ProximityActivity extends BaseSensorActivity {
    private TextView mTvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximity);
        mTvInfo = (TextView)findViewById(R.id.tv_info);
        if(mSensor == null) {
            mTvInfo.setText("No Proximity senor!");
            Log.d("linc","no this sensor.");
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float[] its = sensorEvent.values;
        Log.d("linc","its array:"+its+"sensor type :"+sensorEvent.sensor.getType()+" proximity type:"+Sensor.TYPE_PROXIMITY);
        if (its != null) {
            //经过测试，当手贴近距离感应器的时候its[0]返回值为0.0，当手离开时返回4.0
            mTvInfo .setText(its[0] + "");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected int getDefaultSensor() {
        return Sensor.TYPE_PROXIMITY;
    }

}
