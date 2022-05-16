package com.modbus;

import com.modbus.log.Logger;
import com.modbus.log.LoggerFactory;
import com.modbus.modbus.ModBusDataHandler;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Author: wfs
 * <p>
 * Create: 2022/6/8 15:26
 * <p>
 * Changes (from 2022/6/8)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/6/8 : Create SerialUtils.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class SerialUtils {

    private Logger logger = LoggerFactory.getLog(SerialUtils.class);

    private static SerialUtils instance;

    Map<String, SerialClient> serialClientMap = new ConcurrentHashMap<>();

    private SerialUtils(){

    }

    public static SerialUtils getInstance() {
        if (instance == null) {
            synchronized (SerialUtils.class) {
                if (instance == null) {
                    instance = new SerialUtils();
                }
            }
        }
        return instance;
    }

    public SerialClient getSerialClient(String address) {
        SerialClient client = null;
        if (serialClientMap.containsKey(address)) {
            client = serialClientMap.get(address);
        }

        if (client == null) {
            try {
                client = initSerialClient(address);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
            serialClientMap.put(address, client);
        }

        return client;
    }

    private SerialClient initSerialClient(String address) throws IOException {
        SerialClient client = new SerialClient(address);

        SerialPortParams serialPortParams = new SerialPortParams.Builder()
                .serialPortBaudRate(Constants.SERIAL_BAUD_RATE_9600)
                .serialPortDataBits(Constants.DATABITS_8)
                .serialPortParity(Constants.NO_PARITY)
                .serialPortStopBits(Constants.STOPBITS_1)
//                .serialPortReaderIntervalTimeInMillis(100)  //set the read interval time
//                .inputStreamSizeInByte(1024)  //set the read buffer size
                .serialPortPath(address).build(); //set the device port path

        ModBusDataHandler modBusDataHandler = new ModBusDataHandler(serialPortParams);
        client.init(modBusDataHandler);

        return client;
    }
}
