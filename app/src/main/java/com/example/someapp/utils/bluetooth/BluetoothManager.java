package com.example.someapp.utils.bluetooth;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BluetoothManager {

    private final String TAG = getClass().getSimpleName();
    private BluetoothAdapter bluetoothAdapter;

    public BluetoothManager() {
    }

    /**
     * Check if Bluetooth is supported on the current device
     * @return
     */
    private boolean isSupported() {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        return bluetoothAdapter != null;
    }

    /**
     * Enables bluetooth on the current device
     * @param activity
     */
    public void enableBluetooth(Activity activity) {
        try {
            if (isSupported()) {
                if (ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 0);
                }
                getVisible(activity);
                Intent enableBT = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                activity.startActivityForResult(enableBT, 0);
            }
        } catch (Exception ex) {
            Log.d(TAG, "getVisible: " + ex.getMessage());
        }
    }

    /**
     * Disables bluetooth on the current device
     */
    public void disableBluetooth() {
        try {
            if (bluetoothAdapter.isEnabled()) {
                bluetoothAdapter.disable();
            }
        } catch (Exception ex) {
            Log.d(TAG, "enableBluetooth: " + ex.getMessage());
        }
    }

    /**
     * Set current device to be visible to other devices
     * @param activity
     */
    public void getVisible(Activity activity) {
        try {
            if (isSupported()) {
                //bluetoothAdapter.enable();
                Intent getVisible = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                //getVisible.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 8);
                activity.startActivityForResult(getVisible, 0);
                bluetoothAdapter.startDiscovery();
            }
        } catch (Exception ex) {
            Log.d(TAG, "getVisible: " + ex.getMessage());
        }
    }

    /**
     * List through all paired devices
     * @return
     * @throws Exception
     */
    public List<String> listBluetoothDevices() throws Exception {
        try {
            if (isSupported()) {
                List<String> list = new ArrayList<>();
                Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
                for (BluetoothDevice device : pairedDevices) {
                    list.add(device.getName());
                }
                return list;
            } else {
                return null;
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }
}
