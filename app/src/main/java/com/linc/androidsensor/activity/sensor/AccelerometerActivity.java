package com.linc.androidsensor.activity.sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

import com.linc.androidsensor.R;
import com.linc.androidsensor.ShakeUtils;
import com.linc.androidsensor.base.BaseSensorActivity;

public class AccelerometerActivity extends BaseSensorActivity {
    private TextView mTvInfo;
    private float mGravity = SensorManager.STANDARD_GRAVITY-0.8f;

    private ShakeUtils shakeUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_accelerometer);

        shakeUtils = new ShakeUtils(this);

        mTvInfo = (TextView)findViewById(R.id.tv_info);
        if(mSensor == null) {
            mTvInfo.setText("No Accelerometer senor!");
            Log.d("linc","no this sensor.");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        shakeUtils.registerSensor();
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Log.d("linc", "value size: " + sensorEvent.values.length);
        float xValue = sensorEvent.values[0];// Acceleration minus Gx on the x-axis
        float yValue = sensorEvent.values[1];//Acceleration minus Gy on the y-axis
        float zValue = sensorEvent.values[2];//values[2]: Acceleration minus Gz on the z-axis
        mTvInfo.setText("x轴： "+xValue+"  y轴： "+yValue+"  z轴： "+zValue);
        if(xValue > mGravity) {
            mTvInfo.append("\n重力指向设备左边");
        } else if(xValue < -mGravity) {
            mTvInfo.append("\n重力指向设备右边");
        } else if(yValue > mGravity) {
            mTvInfo.append("\n重力指向设备下边");
        } else if(yValue < -mGravity) {
            mTvInfo.append("\n重力指向设备上边");
        } else if(zValue > mGravity) {
            mTvInfo.append("\n屏幕朝上");
        } else if(zValue < -mGravity) {
            mTvInfo.append("\n屏幕朝下");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected int getDefaultSensor() {
        return Sensor.TYPE_ACCELEROMETER;
    }


    @Override
    protected void onPause() {
        super.onPause();
        shakeUtils.unRegisterSensor();
    }
}
