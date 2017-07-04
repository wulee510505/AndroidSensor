package com.linc.androidsensor.activity.sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

import com.linc.androidsensor.R;
import com.linc.androidsensor.base.BaseSensorActivity;

public class LightActivity extends BaseSensorActivity {
    private TextView mTvInfo;
    private  boolean isBright ;//true 代表亮      false 代表暗
    private final float criticalValue = 40.0f;  //  40.0f 代表人视觉的亮暗临界值

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light);
        mTvInfo = (TextView)findViewById(R.id.tv_info);
        if(mSensor == null) {
            mTvInfo.setText("No Light senor!");
            Log.d("linc","no this sensor.");
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Log.d("linc", "value size: " + sensorEvent.values.length);
        float accuracy = sensorEvent.accuracy;

        isBright = sensorEvent.values[0] > criticalValue ? true : false ;//  定义 > 40.0f 为亮，<= 40.0f 为暗

        String brightStr = isBright ? "亮 ":"暗 ";

        mTvInfo.setText("当前光照强度：" + sensorEvent.values[0] + " lux \n" + brightStr);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected int getDefaultSensor() {
        return Sensor.TYPE_LIGHT;
    }

    public boolean getBright() {
        return  isBright;
    }
}
