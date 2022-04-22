package com.nimeng.modbus;

import com.serotonin.modbus4j.ModbusFactory;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.serial.rtu.RtuMaster;

/**
 * Author: wfs
 * <p>
 * Create: 2022/4/15 14:05
 * <p>
 * Changes (from 2022/4/15)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/4/15 : Create Rtu.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class Rtu {
    String commPortId="";
    int baudRate=9600;
    int flowControIn=0;
    int flowControOut=0;
    int dataBits=8;
    int stopBits=2;
    int parity=0;

    TestSerialPortWrapper wrapper=new TestSerialPortWrapper(commPortId,baudRate,flowControIn,flowControOut,dataBits,stopBits,parity);

    ModbusMaster master=new ModbusFactory().createRtuMaster(wrapper);

    //master.init();

}
