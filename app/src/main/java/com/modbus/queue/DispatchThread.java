package com.modbus.queue;

import android.util.Log;

import com.modbus.log.Logger;
import com.modbus.log.LoggerFactory;
import com.modbus.modbus.ModBusData;

import java.util.concurrent.BlockingQueue;

/**
 * Author: wfs
 * <p>
 * Create: 2022/6/8 15:23
 * <p>
 * Changes (from 2022/6/8)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/6/8 : Create DispatchThread.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class DispatchThread extends Thread {

    private String tag = "";

    private final BlockingQueue<ModBusData<?>> queue;

    private volatile boolean quit = false;
    private DataHandler dataHandler;

    private Logger logger = LoggerFactory.getLog(this.getClass());

    public DispatchThread(BlockingQueue<ModBusData<?>> queue, DataHandler messageHandler, String tag) {
        this.queue = queue;
        dataHandler = messageHandler;
        this.tag = tag;
    }

    public void quit() {
        quit = true;
        interrupt();
    }

    @Override
    public void run() {
        log(tag + "start run");
        while (true) {
            if (quit) {
                log(tag+"stop run");
                return;
            }
            ModBusData<?> request = null;

            try {
                log(tag + "take request");
                request = queue.take();
                dataHandler.handlerData(request);
            } catch (InterruptedException e) {
                e.printStackTrace();
                log(tag+"stop run");
            }

        }
    }

    private void log(String str) {
        Log.i(tag, str);
        logger.info(str);
    }
}

