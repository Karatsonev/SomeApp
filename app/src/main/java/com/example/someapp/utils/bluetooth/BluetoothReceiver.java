package com.example.someapp.utils.bluetooth;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public class BluetoothReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if(BluetoothDevice.ACTION_FOUND.equals(action)){
            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            assert device != null;
            String deviceName = device.getName();
            String deviceMAC = device.getAddress();

        }
    }

        public void register(Activity activity){
            IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
            activity.registerReceiver(this,filter);
        }

        public void unregister(Activity activity){
            activity.unregisterReceiver(this);
        }
}
