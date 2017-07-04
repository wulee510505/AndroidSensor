package com.linc.androidsensor.activity.sensor;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.WindowManager;
import android.widget.TextView;

import com.linc.androidsensor.Pedometer;
import com.linc.androidsensor.R;

public class StepActivity extends Activity {
    public static  final String ACTION_ON_STEP_COUNT_CHANGE = "action_on_step_count_change";

    private TextView mTvInfo;

    private Pedometer pedometer;


    private OnStepCountChangeReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);
        mTvInfo = (TextView)findViewById(R.id.tv_info);


        mReceiver = new OnStepCountChangeReceiver();
        IntentFilter filter = new IntentFilter(ACTION_ON_STEP_COUNT_CHANGE);
        registerReceiver(mReceiver,filter);

        pedometer = new Pedometer(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        pedometer.register();
    }

    class OnStepCountChangeReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            if(TextUtils.equals(ACTION_ON_STEP_COUNT_CHANGE,intent.getAction())){
                mTvInfo.setText("今日步数："+ pedometer.getStepCount()); // 支付宝步数统计就是依据了此原理
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        pedometer.unRegister();
    }
}