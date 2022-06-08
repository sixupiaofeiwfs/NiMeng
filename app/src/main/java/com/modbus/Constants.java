package com.modbus;

/**
 * Author: wfs
 * <p>
 * Create: 2022/6/8 15:24
 * <p>
 * Changes (from 2022/6/8)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/6/8 : Create Constants.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class Constants {
    /**
     * 串口波特率：4800,9600,14400...
     */
    public static final int SERIAL_BAUD_RATE_4800 = 4800;
    public static final int SERIAL_BAUD_RATE_9600 = 9600;
    public static final int SERIAL_BAUD_RATE_14400 = 14400;
    public static final int SERIAL_BAUD_RATE_19200 = 19200;
    public static final int SERIAL_BAUD_RATE_38400 = 38400;
    public static final int SERIAL_BAUD_RATE_56000 = 56000;
    public static final int SERIAL_BAUD_RATE_57600 = 57600;
    public static final int SERIAL_BAUD_RATE_115200 = 115200;
    public static final int SERIAL_BAUD_RATE_128000 = 128000;
    public static final int SERIAL_BAUD_RATE_256000 = 256000;

    public static final int DATABITS_8 = 8;
    public static final int DATABITS_7 = 7;
    public static final int STOPBITS_2 = 2;
    public static final int STOPBITS_1 = 1;
//    public static final int PARITY_NONE = 'n';
//    public static final int PARITY_ODD = 'o';
//    public static final int PARITY_EVEN = 'e';

    public static final int NO_PARITY = 0;
    public static final int ODD_PARITY = 1;
    public static final int EVEN_PARITY = 2;
    public static final int MARK_PARITY = 3;
    public static final int SPACE_PARITY = 4;
}
