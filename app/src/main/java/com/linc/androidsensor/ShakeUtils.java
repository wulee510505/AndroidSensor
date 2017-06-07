package com.linc.androidsensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;

import com.liangmayong.text2speech.Text2Speech;

/**
 * Created by wulee on 2017/6/7 15:53
 */

public class ShakeUtils implements SensorEventListener {

    // 两次检测的时间间隔
    private static final int UPTATE_INTERVAL_TIME = 100;
    // 加速度变化阈值，当摇晃速度达到这值后产生作用
    private static final int SPEED_THRESHOLD = 2000;

    private Context mContext;
    private SensorManager mSensorManager = null;
    private Vibrator mVibrator = null;


    public ShakeUtils(Context context) {
        mContext = context;
        mSensorManager = (SensorManager) mContext
                .getSystemService(Context.SENSOR_SERVICE);
        mVibrator = (Vibrator) mContext.getSystemService(Context.VIBRATOR_SERVICE);
    }

    private long lastUpdateTime;
    private float lastX;
    private float lastY;
    private float lastZ;

    @Override
    public void onSensorChanged(SensorEvent event) {
        long currentUpdateTime = System.currentTimeMillis();

        long timeInterval = currentUpdateTime - lastUpdateTime;

        if (timeInterval < UPTATE_INTERVAL_TIME) {
            return;
        }

        lastUpdateTime = currentUpdateTime;
        float[] values = event.values;

        // 获得x,y,z加速度
        float x = values[0];
        float y = values[1];
        float z = values[2];

        // 获得x,y,z加速度的变化值
        float deltaX = x - lastX;
        float deltaY = y - lastY;
        float deltaZ = z - lastZ;

        // 将现在的坐标变成last坐标
        lastX = x;
        lastY = y;
        lastZ = z;

        Log.i("linc","values[0] = " + values[0]);
        Log.i("linc","values[1] = " + values[1]);
        Log.i("linc","values[2] = " + values[2]);

        double speed = Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ
                * deltaZ)
                / timeInterval * 10000;
        Log.i("linc","speed:" + speed);
        if (speed > SPEED_THRESHOLD) {
            //在这里可以提供一个回调
            mVibrator.vibrate(500);
            doshake();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void registerSensor() {
        Sensor sensor = mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (null != sensor)
            mSensorManager.registerListener(this, sensor,
                    SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void unRegisterSensor() {
        lastX = 0;
        lastY = 0;
        lastZ = 0;
        mSensorManager.unregisterListener(this);
    }


    private void doshake() {
       //在这实现具体操作
        Text2Speech.speech(mContext,"让你摇你就摇啊",false);
        Toast.makeText(mContext, "触发摇一摇操作...", Toast.LENGTH_SHORT).show();
    }
}
