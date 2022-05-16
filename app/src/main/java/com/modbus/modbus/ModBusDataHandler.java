package com.modbus.modbus;

import android.util.Log;

import com.ghgande.j2mod.modbus.Modbus;
import com.ghgande.j2mod.modbus.ModbusException;
import com.ghgande.j2mod.modbus.io.ModbusSerialTransaction;
import com.ghgande.j2mod.modbus.msg.ModbusResponse;
import com.ghgande.j2mod.modbus.msg.ReadInputRegistersRequest;
import com.ghgande.j2mod.modbus.msg.ReadInputRegistersResponse;
import com.ghgande.j2mod.modbus.msg.ReadMultipleRegistersRequest;
import com.ghgande.j2mod.modbus.msg.WriteCoilRequest;
import com.ghgande.j2mod.modbus.msg.WriteMultipleRegistersRequest;
import com.ghgande.j2mod.modbus.net.SerialConnection;
import com.ghgande.j2mod.modbus.procimg.Register;
import com.ghgande.j2mod.modbus.util.SerialParameters;
import com.modbus.SerialPortParams;
import com.modbus.queue.DataHandler;

import java.io.IOException;

/**
 * Author: wfs
 * <p>
 * Create: 2022/6/8 15:21
 * <p>
 * Changes (from 2022/6/8)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/6/8 : Create ModBusDataHandler.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class ModBusDataHandler implements DataHandler {


    private final SerialConnection connection;
    private final ModbusSerialTransaction transaction;

    public ModBusDataHandler(SerialPortParams serialPortParams) throws IOException {
        SerialParameters parameters = new SerialParameters();
        parameters.setBaudRate(serialPortParams.getSerialPortBaudRate());
        parameters.setEncoding(Modbus.SERIAL_ENCODING_RTU);
        parameters.setDatabits(serialPortParams.getSerialPortDataBits());
        parameters.setStopbits(serialPortParams.getSerialPortStopBits());
        parameters.setParity(serialPortParams.getSerialPortParity());
        parameters.setEcho(false);
        parameters.setPortName(serialPortParams.getSerialPortPath());
        connection = new SerialConnection(parameters);
        connection.open();

        transaction = new ModbusSerialTransaction(connection);
    }

    @Override
    public void handlerData(ModBusData data) {
        try {
            switch (data.fcode) {
                case 0x04:
                    ReadInputRegistersRequest req = new ReadInputRegistersRequest(data.ref, data.count);
                    req.setUnitID(data.unitId);
                    req.setHeadless();
                    transaction.setRequest(req);
                    transaction.execute();
                    ReadInputRegistersResponse response = (ReadInputRegistersResponse) transaction.getResponse();


                    Log.d("返回数据", "handlerData: "+response);

                    data.listener.onSucceed(/*data.unitId,
                        data.fcode,
                        data.ref,
                        data.count,*/
                            response.getHexMessage(),
                            response.getMessage());

                    break;
                case 0x03:
                    ReadMultipleRegistersRequest registersRequest = new ReadMultipleRegistersRequest(data.ref, data.count);
                    registersRequest.setUnitID(data.unitId);
                    registersRequest.setHeadless();


                    transaction.setRequest(registersRequest);
                    transaction.execute();
                    ModbusResponse response1 = transaction.getResponse();
                    data.listener.onSucceed(response1.getHexMessage(),response1.getMessage());

                    break;
                case 0x05:
                    WriteCoilRequest writeCoilRequest = new WriteCoilRequest(data.ref, data.coliB);
                    writeCoilRequest.setUnitID(data.unitId);
                    writeCoilRequest.setHeadless();

                    transaction.setRequest(writeCoilRequest);
                    transaction.execute();
                    ModbusResponse response2 = transaction.getResponse();
                    data.listener.onSucceed(response2.getHexMessage(),response2.getMessage());
                    break;
                case 0x10:

                    Register[] registers = new Register[data.writeData.size()];
                    for (int i = 0; i < registers.length; i++) {
                        registers[i].setValue((byte[]) data.writeData.get(i));
                    }
                    WriteMultipleRegistersRequest multipleRegistersRequest = new WriteMultipleRegistersRequest(data.ref, registers);
                    multipleRegistersRequest.setUnitID(data.unitId);

                    transaction.setRequest(multipleRegistersRequest);
                    transaction.execute();
                    ModbusResponse response3 = transaction.getResponse();
                    data.listener.onSucceed(response3.getHexMessage(), response3.getMessage());

                    break;
            }
        } catch (ModbusException e) {
            e.printStackTrace();
            data.listener.onFailed("操作失败失败");
        }
    }
}
