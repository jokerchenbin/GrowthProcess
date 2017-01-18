package com.cst.growthprocess.bluetoothDemo;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.cst.growthprocess.R;
import com.cst.growthprocess.receiver.MyBroadcastReceiver;

import java.util.Set;

/**
 * android 蓝牙使用相关技巧
 */
public class BlueToothActivity extends AppCompatActivity {

    private final String TAG = BlueToothActivity.class.getSimpleName();

    private BluetoothAdapter mAdapter;

    private final static int REQUEST_ENABLE_BT = 1;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bule_tooth);
        mContext = this;
       // mContext.registerReceiver(mReceiver, makeFilter());
        mAdapter = BluetoothAdapter.getDefaultAdapter();
        if (null == mAdapter){
            // Device does not support Bluetooth
            return;
        }

        if (!mAdapter.isEnabled()){
            //BluetoothAdapter 未打开  通过Intent 打开蓝牙
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent, REQUEST_ENABLE_BT);
        }

        Set<BluetoothDevice> pairedDevices = mAdapter.getBondedDevices();
        if (pairedDevices.size() > 0) {
            // Loop through paired devices
            for (BluetoothDevice device : pairedDevices) {
                // Add the name and address to an array adapter to show in a ListView
                Log.v(TAG,device.getName() + "\n" + device.getAddress());
            }
        }
        registerReceiver(mReceiver,filter);
        boolean b = mAdapter.startDiscovery();
    }


    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            // When discovery finds a device
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Get the BluetoothDevice object from the Intent
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                // Add the name and address to an array adapter to show in a ListView
//                mArrayAdapter.add(device.getName() + "\n" + device.getAddress());
                Log.v(TAG,device.getName() + "\n" + device.getAddress());
            }
        }
    };
    // Register the BroadcastReceiver
    IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);


    @Override
    protected void onDestroy() {
        registerReceiver(mReceiver, filter); // Don't forget to unregister during onDestroy
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.v(TAG,requestCode+"  --------->requestCode");
        Log.v(TAG,resultCode+"  --------->resultCode");
        Log.v(TAG,BluetoothAdapter.EXTRA_STATE+"  --------->EXTRA_STATE");
        Log.v(TAG,BluetoothAdapter.EXTRA_PREVIOUS_STATE+"  --------->EXTRA_PREVIOUS_STATE");
        super.onActivityResult(requestCode, resultCode, data);
    }

}
