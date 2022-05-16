package com.nimeng.View;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.InputType;
import android.text.method.KeyListener;
import android.text.method.NumberKeyListener;
import android.text.method.TextKeyListener;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.modbus.SerialClient;
import com.modbus.SerialPortParams;
import com.modbus.SerialUtils;

import com.modbus.modbus.ModBusData;
import com.modbus.modbus.ModBusDataListener;



/**
 * Author: wfs
 * <p>
 * Create: 2022/6/7 16:28
 * <p>
 * Changes (from 2022/6/7)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/6/7 : Create ComAssistantActivity.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class ModbusActivity extends BaseAvtivity {

    private static final String TAG = "ModbusActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modbus);



        init("/dev/ttyS0");

    }

    private void init(final String address) {
        SerialPortParams build = new SerialPortParams.Builder().serialPortPath(address).build();

        Log.d(TAG, "init: "+build.getSerialPortPath()+" "+build.getSerialPortParity());

        final SerialClient serialClient = SerialUtils.getInstance().getSerialClient(address);

        Log.d(TAG, "init: "+serialClient);

//        if (serialClient == null) {
//            return;
//        }
//
//        serialClient.sendData(new ModBusData<Object>(1, 1, 0, 4, new ModBusDataListener() {
//            @Override
//            public void onSucceed(String hexValue, byte[] bytes) {
//                Log.d(TAG, "onSucceed: " + hexValue);
//            }
//
//            @Override
//            public void onFailed(String str) {
//                Log.d(TAG, "onFailed111111111: "+str);
//            }
//        }));

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    SystemClock.sleep(500);
                    serialClient.sendData(new ModBusData<Object>(1, 0x04, 0, 8, new ModBusDataListener() {
                        @Override
                        public void onSucceed(String hexValue, byte[] bytes) {
                            Log.d(TAG, "读8位: " + hexValue);
                        }

                        @Override
                        public void onFailed(String str) {
                            Log.d(TAG, "onFailed22222222: "+str);
                        }
                    }));
                }
            }
        }).start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true) {
////                    Log.d(TAG, "run: "+Thread.currentThread().getId());
//                    SystemClock.sleep(1000);
//                    serialClient.sendData(new ModBusData<Object>(1, 0x04, 0, 3, new ModBusDataListener() {
//                        @Override
//                        public void onSucceed(String hexValue, byte[] bytes) {
//                            Log.d(TAG, "读三位: " + hexValue);
//                        }
//
//                        @Override
//                        public void onFailed(String str) {
//
//                        }
//                    }));
//                }
//            }
//        }).start();

    }
}
