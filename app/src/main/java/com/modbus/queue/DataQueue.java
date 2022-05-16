package com.modbus.queue;

import com.modbus.modbus.ModBusData;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * Author: wfs
 * <p>
 * Create: 2022/6/8 15:23
 * <p>
 * Changes (from 2022/6/8)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/6/8 : Create DataQueue.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class DataQueue {

    private String mTag = "";

    private final PriorityBlockingQueue<ModBusData<?>> mSignalRequestQueue = new PriorityBlockingQueue<>();
    private DispatchThread mDispatcher;
    private DataHandler mMessageHandler;

    public DataQueue(DataHandler messageHandler, String tag) {
        mMessageHandler = messageHandler;
        mTag = tag;
    }

    public void start() {
        stop();
        mDispatcher = new DispatchThread(mSignalRequestQueue, mMessageHandler, mTag);
        mDispatcher.start();
    }

    public void stop() {
        if (mDispatcher != null) {
            mDispatcher.quit();
        }
    }

    public <T> ModBusData<T> add(ModBusData<T> request) {
        synchronized (mSignalRequestQueue) {
            mSignalRequestQueue.add(request);
        }
        return request;
    }
}
