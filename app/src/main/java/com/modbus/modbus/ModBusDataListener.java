package com.modbus.modbus;

/**
 * Author: wfs
 * <p>
 * Create: 2022/6/8 15:22
 * <p>
 * Changes (from 2022/6/8)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/6/8 : Create ModBusDataListener.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public interface ModBusDataListener {

    void onSucceed(/*int unit,int fcode,int ref,int count,*/String hexValue,byte[] bytes);
    void onFailed(String str);

}