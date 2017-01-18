package com.cst.growthprocess.receiver;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyBroadcastReceiver extends BroadcastReceiver {

    public static final String TAG = "MyBroadcastReceiver";

    public static int m = 1;

    @Override
    public void onReceive(final Context context, final Intent intent) {
        Log.w(TAG, "intent:" + intent);
        String name = intent.getStringExtra("name");
        Log.w(TAG, "name:" + name + " m=" + m);
        Log.v(TAG, BluetoothAdapter.EXTRA_STATE+"  --------->EXTRA_STATE");
        Log.v(TAG,BluetoothAdapter.EXTRA_PREVIOUS_STATE+"  --------->EXTRA_PREVIOUS_STATE");
        if (intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, 0) == BluetoothAdapter.STATE_OFF){//关闭状态
            intent.setAction(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            context.startActivity(intent);
        }


    }
}
