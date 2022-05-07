package com.modbus;

/**
 * Author: wfs
 * <p>
 * Create: 2022/5/16 11:19
 * <p>
 * Changes (from 2022/5/16)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/5/16 : Create ModbusFunction.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class ModbusFunction {
    public static final int READ_COILS = 1;
    public static final int READ_DISCRETE_INPUTS = 2;
    public static final int READ_HOLDING_REGISTERS = 3;
    public static final int READ_INPUT_REGISTERS = 4;
    public static final int WRITE_SINGLE_COIL = 5;
    public static final int WRITE_SINGLE_REGISTER = 6;
}