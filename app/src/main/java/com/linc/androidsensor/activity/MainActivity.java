package com.linc.androidsensor.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.linc.androidsensor.R;
import com.linc.androidsensor.activity.sensor.AccelerometerActivity;
import com.linc.androidsensor.activity.sensor.GyroscopeActivity;
import com.linc.androidsensor.activity.sensor.LightActivity;
import com.linc.androidsensor.activity.sensor.MagneticFleldActivity;
import com.linc.androidsensor.activity.sensor.OrientationActivity;
import com.linc.androidsensor.activity.sensor.ProximityActivity;
import com.linc.androidsensor.activity.sensor.StepActivity;

public class MainActivity extends Activity {
    private ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView)findViewById(R.id.main_list);
        String[] features = getResources().getStringArray(R.array.sensors);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,features);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent();
                switch (position) {
                    case 0://sensor info
                        intent.setClass(MainActivity.this, DeviceSensorInfoActivity.class);
                        startActivity(intent);
                        break;
                    case 1://orientation
                        intent.setClass(MainActivity.this, OrientationActivity.class);
                        startActivity(intent);
                        break;
                    case 2://gyroscope
                        intent.setClass(MainActivity.this, GyroscopeActivity.class);
                        startActivity(intent);
                        break;
                    case 3://accelerometer
                        intent.setClass(MainActivity.this, AccelerometerActivity.class);
                        startActivity(intent);
                        break;
                    case 4://light
                        intent.setClass(MainActivity.this, LightActivity.class);
                        startActivity(intent);
                        break;
                    case 5://magnetic_fleld
                        intent.setClass(MainActivity.this, MagneticFleldActivity.class);
                        startActivity(intent);
                        break;
                    case 6://proximity
                        intent.setClass(MainActivity.this, ProximityActivity.class);
                        startActivity(intent);
                        break;
                    case 7://step
                        intent.setClass(MainActivity.this, StepActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });
    }
}
