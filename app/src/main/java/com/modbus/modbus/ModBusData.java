package com.modbus.modbus;

import java.util.List;

/**
 * Author: wfs
 * <p>
 * Create: 2022/6/8 15:21
 * <p>
 * Changes (from 2022/6/8)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/6/8 : Create ModBusData.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class ModBusData<T> implements Comparable<ModBusData<T>> {

    private final String TAG = this.getClass().getSimpleName();
    protected T data;

    public int unitId;

    public int fcode;

    public int ref;

    public int count;

    public boolean coliB;

    public List<byte[]> writeData;

    public ModBusData(int unitId, int fcode, int ref, int count, ModBusDataListener listener) {
        this.unitId = unitId;
        this.fcode = fcode;
        this.ref = ref;
        this.count = count;
        this.listener = listener;
    }

    public ModBusData(int unitId, int fcode, int ref, boolean coliB, ModBusDataListener listener) {
        this.unitId = unitId;
        this.fcode = fcode;
        this.ref = ref;
        this.coliB = coliB;
        this.listener = listener;
    }


    public ModBusData(int unitId, int fcode, int ref, List<byte[]> writeData) {
        this.unitId = unitId;
        this.fcode = fcode;
        this.ref = ref;
        this.writeData = writeData;
    }

    public ModBusDataListener listener;

    public ModBusDataListener getListener() {
        return listener;
    }

    @Override
    public int compareTo(ModBusData<T> o) {
        if (o == null) {
            return -1;
        }
        return 0;
    }
}
