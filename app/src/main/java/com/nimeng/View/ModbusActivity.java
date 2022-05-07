package com.nimeng.View;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.modbus.ModbusMaster;
import com.modbus.serial.SerialPort;

import java.io.File;
import java.util.Arrays;

/**
 * Author: wfs
 * <p>
 * Create: 2022/5/16 11:38
 * <p>
 * Changes (from 2022/5/16)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/5/16 : Create ModbusActivity.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class ModbusActivity extends BaseAvtivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            int[] result = new int[6];
            SerialPort serialPort = new SerialPort(new File("/dev/ttyS0"));
            ModbusMaster modbusMaster = new ModbusMaster(serialPort);
            while (true){
                result = modbusMaster.readHoldingRegisters(1,0,6);
                System.out.println("result: "+ Arrays.toString(result));
                Thread.sleep(1000L);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
