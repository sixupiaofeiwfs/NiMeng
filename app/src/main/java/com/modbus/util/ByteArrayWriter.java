package com.modbus.util;

import java.io.ByteArrayOutputStream;

/**
 * Author: wfs
 * <p>
 * Create: 2022/5/16 11:06
 * <p>
 * Changes (from 2022/5/16)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/5/16 : Create ByteArrayWriter.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class ByteArrayWriter extends ByteArrayOutputStream {
    public ByteArrayWriter() {
        super();
    }

    public void writeInt8(byte b)
    {
        this.write(b);
    }

    public void writeInt8(int b)
    {
        this.write((byte)b);
    }

    public void writeInt16(int n) {
        byte[] bytes = ByteUtil.fromInt16(n);
        this.write(bytes, 0, bytes.length);
    }

    public void writeInt16Reversal(int n){
        byte[] bytes=ByteUtil.fromInt16Reversal(n);
        this.write(bytes,0,bytes.length);
    }

    public void writeInt32(int n) {
        byte[] bytes = ByteUtil.fromInt32(n);
        this.write(bytes, 0, bytes.length);
    }

    public void writeBytes(byte[] bs,int len){
        this.write(bs,0,len);
    }

}