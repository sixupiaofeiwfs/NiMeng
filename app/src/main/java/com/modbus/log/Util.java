package com.modbus.log;

import com.modbus.exception.SerialException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Author: wfs
 * <p>
 * Create: 2022/6/8 15:20
 * <p>
 * Changes (from 2022/6/8)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/6/8 : Create Util.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class Util {
    private static Logger log = LoggerFactory.getLog(Util.class);

    /**
     * write the log
     *
     * @param path the log path
     * @param content the log content
     */
    public static void writeFile(String path, String content) {
        writeFile(path, content, true);
    }

    /**
     * sleep
     * @param secondInMillis sleep time in millisecond
     */
    public static void sleep(long secondInMillis){
        try {
            Thread.sleep(secondInMillis);
        } catch (InterruptedException e) {
            log.error(new SerialException(e));
        }
    }

    private static void writeFile(String path, String content, boolean append) {
        try {
            File file = new File(path);
            if (!file.exists()) {
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
            }
            FileWriter fw = new FileWriter(path, append);
            String c = content + "\r\n";
            fw.write(c);
            fw.close();
        } catch (IOException e) {
            log.error(new SerialException(e));
        }
    }

}
