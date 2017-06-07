package com.linc.androidsensor.base;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Toast;

public abstract class BaseSensorActivity extends Activity implements SensorEventListener {
    private static final String TAG = "BaseSensorActivity";
    protected SensorManager mSensorManager;
    protected Sensor mSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
            mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
            mSensor = mSensorManager.getDefaultSensor(getDefaultSensor());//sensor type
        } catch (Exception ex) {
            Toast.makeText(this, "检测失败！", Toast.LENGTH_SHORT).show();
        } catch (Error error) {
            Toast.makeText(this,"检测失败！",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        try{
            mSensorManager.registerListener(this, mSensor,
                    SensorManager.SENSOR_DELAY_NORMAL);
        } catch (Exception ex) {
            Toast.makeText(this, "检测失败！", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        try{
            mSensorManager.unregisterListener(this);
        } catch (Exception ex) {
            Toast.makeText(this,"检测失败！",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public abstract void onSensorChanged(SensorEvent sensorEvent);

    @Override
    public abstract void onAccuracyChanged(Sensor sensor, int i);

    protected abstract int getDefaultSensor();
}
