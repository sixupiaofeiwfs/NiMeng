package com.modbus.queue;

import com.modbus.modbus.ModBusData;

/**
 * Author: wfs
 * <p>
 * Create: 2022/6/8 15:23
 * <p>
 * Changes (from 2022/6/8)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/6/8 : Create DataHandler.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public interface DataHandler {
    public void handlerData(ModBusData data);
}

