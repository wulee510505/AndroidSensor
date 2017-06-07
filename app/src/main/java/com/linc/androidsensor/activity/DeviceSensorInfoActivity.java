package com.linc.androidsensor.activity;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.linc.androidsensor.R;

import java.util.List;

public class DeviceSensorInfoActivity extends Activity {
    private static final String TAG = "DeviceSensorInfo";
    private TextView mTvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_sensor_info);
        mTvInfo = (TextView)findViewById(R.id.tv_info);
        SensorManager sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        mTvInfo.setText("sensors: " + sensors.size());
        Log.d(TAG, "sensors: " + sensors.size());
        for (int i = 0;i < sensors.size();++i) {
            Log.d(TAG,"sensor name: "+sensors.get(i).getName());
            Log.d(TAG,"sensor vendor: "+sensors.get(i).getVendor());
            Log.d(TAG,"sensor power: "+sensors.get(i).getPower());
            Log.d(TAG, "sensor resolution: " + sensors.get(i).getResolution());
            mTvInfo.append("\n"+i+": sensor name: " + sensors.get(i).getName());
            mTvInfo.append("\n"+i+": sensor type: " + sensors.get(i).getType());
            mTvInfo.append("\n"+i+": sensor vendor: "+sensors.get(i).getVendor());
            mTvInfo.append("\n"+i+": sensor power: "+sensors.get(i).getPower());
            mTvInfo.append("\n"+i+": sensor resolution: "+ sensors.get(i).getResolution());
            mTvInfo.append("\n\n");
        }
    }

}
