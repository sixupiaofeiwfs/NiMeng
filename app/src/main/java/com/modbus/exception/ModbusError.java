package com.modbus.exception;
import android.text.TextUtils;
/**
 * Author: wfs
 * <p>
 * Create: 2022/5/16 11:15
 * <p>
 * Changes (from 2022/5/16)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/5/16 : Create ModbusError.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class ModbusError extends Exception {
    private int code;

    public ModbusError(int code, String message) {
        super(!TextUtils.isEmpty(message) ? message : "Modbus Error: Exception code = " + code);
        this.code = code;
    }

    public ModbusError(int code) {
        this(code, null);
    }

    public ModbusError(ModbusErrorType type, String message) {
        super(type.name() + ": " + message);
    }

    public int getCode() {
        return this.code;
    }
}