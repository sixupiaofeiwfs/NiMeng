package com.modbus.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Author: wfs
 * <p>
 * Create: 2022/5/16 10:58
 * <p>
 * Changes (from 2022/5/16)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/5/16 : Create ByteArrayReader.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class ByteArrayReader extends ByteArrayInputStream {
    public ByteArrayReader(byte[] buf) {
        super(buf);
    }

    public int readInt8() {
        return super.read();
    }

    public int readInt16() throws IOException {
        byte[] d = new byte[2];
        if (super.read(d, 0, 2) < 2) {
            throw new IOException();
        }
        return ByteUtil.toInt(d);
    }

    public int readInt32() throws IOException {
        byte[] d = new byte[4];
        if (super.read(d, 0, 4) < 4) {
            throw new IOException();
        }
        return ByteUtil.toInt(d);
    }
}