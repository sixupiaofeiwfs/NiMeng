package com.modbus;

import com.modbus.modbus.ModBusData;
import com.modbus.queue.DataHandler;
import com.modbus.queue.DataQueue;

/**
 * Author: wfs
 * <p>
 * Create: 2022/6/8 15:25
 * <p>
 * Changes (from 2022/6/8)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/6/8 : Create SerialClient.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class SerialClient {

    private final String tag;

    public SerialClient(String tag) {
        this.tag = tag;
    }

    private DataQueue mDataQueue;

    public void init(DataHandler dataHandler) {
        if (null == mDataQueue) {
            synchronized (SerialClient.class) {
                if (null == mDataQueue) {
                    mDataQueue = new DataQueue(dataHandler,tag);
                    mDataQueue.start();
                }
            }
        }
    }

    public void sendData(ModBusData<?> request) {
        if (mDataQueue == null) {
            return;
        }
        mDataQueue.add(request);



    }


    public void stop() {
        if (mDataQueue != null) {
            mDataQueue.stop();
        }
    }

}

