package com.modbus.exception;

/**
 * Author: wfs
 * <p>
 * Create: 2022/5/16 11:19
 * <p>
 * Changes (from 2022/5/16)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/5/16 : Create ModbusErrorType.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public enum ModbusErrorType {
    ModbusError,
    ModbusFunctionNotSupportedError,
    ModbusDuplicatedKeyError,
    ModbusMissingKeyError,
    ModbusInvalidBlockError,
    ModbusInvalidArgumentError,
    ModbusOverlapBlockError,
    ModbusOutOfBlockError,
    ModbusInvalidResponseError,
    ModbusInvalidRequestError,

    ModbusTimeoutError
}
