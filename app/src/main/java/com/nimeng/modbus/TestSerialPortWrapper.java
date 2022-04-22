package com.nimeng.modbus;

import com.serotonin.modbus4j.serial.SerialPortWrapper;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Author: wfs
 * <p>
 * Create: 2022/4/15 14:54
 * <p>
 * Changes (from 2022/4/15)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/4/15 : Create TestSerialPortWrapper.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class TestSerialPortWrapper implements SerialPortWrapper {
    private String commPortId;//从站地址
    private int baudRate;//波特率
    private int flowControlIn;
    private int flowControlOut;
    private int dataBits;
    private int stopBits;
    private int parity;

    public TestSerialPortWrapper(String commPortId, int baudRate, int flowControlIn, int flowControlOut, int dataBits, int stopBits, int parity) {
        this.commPortId = commPortId;
        this.baudRate = baudRate;
        this.flowControlIn = flowControlIn;
        this.flowControlOut = flowControlOut;
        this.dataBits = dataBits;
        this.stopBits = stopBits;
        this.parity = parity;
    }


    @Override
    public void close() throws Exception {

    }

    @Override
    public void open() throws Exception {

    }

    @Override
    public InputStream getInputStream() {
        return null;
    }

    @Override
    public OutputStream getOutputStream() {
        return null;
    }

    @Override
    public int getBaudRate() {
        return 0;
    }

    @Override
    public int getFlowControlIn() {
        return 0;
    }

    @Override
    public int getFlowControlOut() {
        return 0;
    }

    @Override
    public int getDataBits() {
        return 0;
    }

    @Override
    public int getStopBits() {
        return 0;
    }

    @Override
    public int getParity() {
        return 0;
    }
}
