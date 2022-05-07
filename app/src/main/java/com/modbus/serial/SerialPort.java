package com.modbus.serial;

import android.util.Log;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Author: wfs
 * <p>
 * Create: 2022/5/16 11:27
 * <p>
 * Changes (from 2022/5/16)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/5/16 : Create SerialPort.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class SerialPort {

    private static final String TAG = "SerialPort";

    /*
     * Do not remove or rename the field mFd: it is used by native method close();
     */
    private FileDescriptor mFd;
    private FileInputStream mFileInputStream;
    private FileOutputStream mFileOutputStream;

    public SerialPort(File device) throws SecurityException, IOException {
        this(device, 9600, 0, 8, 1);
    }
    /**
     * 打开串口
     *@param device 串口设备文件
     *@param baudrate 波特率，一般是9600
     *@param parity 奇偶校验，0 None, 1 Odd, 2 Even
     *@param dataBits 数据位，5 - 8
     *@param stopBit 停止位，1 或 2
     */
    public SerialPort(File device, int baudrate, int parity, int dataBits, int stopBit) throws SecurityException, IOException {
        /* Check access permission */
        if (!device.canRead() || !device.canWrite()) {
            try {
                /* Missing read/write permission, trying to chmod the file */
                Process su;
                su = Runtime.getRuntime().exec("/system/bin/su");
                String cmd = "chmod 666 " + device.getAbsolutePath() + "\n"
                        + "exit\n";
                su.getOutputStream().write(cmd.getBytes());
                if ((su.waitFor() != 0) || !device.canRead()
                        || !device.canWrite()) {
                    throw new SecurityException();
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new SecurityException();
            }
        }

        mFd = open(device.getAbsolutePath(), baudrate, parity, dataBits, stopBit);
        if (mFd == null) {
            Log.e(TAG, "native open returns null");
            throw new IOException();
        }
        mFileInputStream = new FileInputStream(mFd);
        mFileOutputStream = new FileOutputStream(mFd);
    }

    // Getters and setters
    public InputStream getInputStream() {
        return mFileInputStream;
    }

    public OutputStream getOutputStream() {
        return mFileOutputStream;
    }

    // JNI
    private native static FileDescriptor open(String path, int baudrate, int parity, int dataBits, int stopBit);
    public native void close();
    static {
        try {
            System.loadLibrary("serial_port");
        }
        catch ( Exception ex){
            ex.printStackTrace();
        }
    }
}