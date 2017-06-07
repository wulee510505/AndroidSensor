package com.linc.androidsensor;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.linc.androidsensor.activity.sensor.StepActivity;

/**
 * Created by wulee on 2017/6/7 14:54
 */

public class Pedometer implements SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor mStepCount;
    private Sensor mStepDetector;
    private static float mCount;//步行总数
    private float mDetector;//步行探测器
    private Context context;
    private static final int sensorTypeD = Sensor.TYPE_STEP_DETECTOR;
    private static final int sensorTypeC = Sensor.TYPE_STEP_COUNTER;

    public Pedometer(Context context) {
        this.context = context;
        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        mStepCount = mSensorManager.getDefaultSensor(sensorTypeC);
        mStepDetector = mSensorManager.getDefaultSensor(sensorTypeD);
    }

    public void register() {
        register(mStepCount, SensorManager.SENSOR_DELAY_FASTEST);
        register(mStepDetector, SensorManager.SENSOR_DELAY_FASTEST);
    }

    public void unRegister() {
        mSensorManager.unregisterListener(this);
    }

    private void register(Sensor sensor, int rateUs) {
        mSensorManager.registerListener(this, sensor, rateUs);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == sensorTypeC) {
            setStepCount(event.values[0]);
            context.sendBroadcast(new Intent(StepActivity.ACTION_ON_STEP_COUNT_CHANGE));
        }
        if (event.sensor.getType() == sensorTypeD) {
            if (event.values[0] == 1.0) {
                mDetector++;
            }
        }
    }

    public float getStepCount() {
        return mCount;
    }

    private void setStepCount(float count) {
        this.mCount = count;
    }

    public float getmDetector() {
        return mDetector;
    }
}

