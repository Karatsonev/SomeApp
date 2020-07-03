package com.example.someapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.someapp.R;
import com.example.someapp.SomeRunnable;
import com.example.someapp.utils.bluetooth.BluetoothManager;
import com.example.someapp.viewmodels.ViewModelFactory;
import com.example.someapp.data.local.models.User;
import com.example.someapp.viewmodels.MainActivityViewModel;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private  final String TAG = getClass().getSimpleName();
    private TextView tvResult;
    private TextView tvUserInfo;
    private EditText etEmail;
    private EditText etPassword;
    private Button btn;
    private MainActivityViewModel viewModel;
    private BluetoothManager bluetoothManager = new BluetoothManager();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mReceiver, filter);

        ViewModelFactory viewModelFactory = new ViewModelFactory(getApplication());
        viewModel = new ViewModelProvider(this,viewModelFactory).get(MainActivityViewModel.class);
        viewModel.observeUsers().observe(this, users -> tvResult.setText(getString(R.string.users_number, users.size())));

        new Thread(new SomeRunnable(btn)).start();

    }

    public void registerUser(View view){
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        if(!email.equals("") && !password.equals("")){
           viewModel.insertUser(new User(null,email,password));
           etEmail.setText("");
           Toast.makeText(this, "User successfully created!", Toast.LENGTH_SHORT).show();
       }else
           Toast.makeText(this, "Email and Password are Required!", Toast.LENGTH_SHORT).show();
    }

    public void deleteUsers(View view){
        viewModel.deleteAllUsers();
        Toast.makeText(this, "Users successfully deleted!", Toast.LENGTH_SHORT).show();
    }

    public void getUsers(View view){
        viewModel.observeUsersList().observe(this,users -> {
            if(users != null && users.size() > 0){
                tvUserInfo.setText(users.get(users.size() - 1).getEmail());
            }else
                tvUserInfo.setText(R.string.noUsers);
        });
    }

    private void initViews() {
        tvResult = findViewById(R.id.text);
        tvUserInfo = findViewById(R.id.text2);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btn = findViewById(R.id.btnAdd);
    }

    public void getPairedDevices(View view) {
        try {
          List<String> listOfDevices =  bluetoothManager.listBluetoothDevices();
          if(listOfDevices != null && listOfDevices.size() > 0 ){
           for(String listItem : listOfDevices){
               Toast.makeText(this, listItem, Toast.LENGTH_SHORT).show();
           }
          }
        } catch (Exception e) {
            Log.d(TAG, "getPairedDevices: " + e.getMessage());
        }
    }

    public void turnOffBluetooth(View view) {
        bluetoothManager.disableBluetooth();
    }

    public void turnOnBluetooth(View view) {
        bluetoothManager.enableBluetooth(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                assert device != null;
                String deviceName = device.getName();
                String deviceHardwareAddress = device.getAddress(); // MAC address
                Log.d(TAG, "onReceive: Device Name: " + deviceName);
                Log.d(TAG, "onReceive: Device MAC Address: " +deviceHardwareAddress);
            }
        }
    };
}
