package com.modbus.log;

/**
 * Author: wfs
 * <p>
 * Create: 2022/6/8 15:20
 * <p>
 * Changes (from 2022/6/8)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/6/8 : Create LoggerFactory.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class LoggerFactory {
    private static Logger log = new Logger();
    public static Logger getLog(Class tagClass){
        return log;
    }
}
