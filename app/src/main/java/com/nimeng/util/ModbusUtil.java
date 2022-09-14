package com.nimeng.util;

import androidx.core.os.ParcelableCompatCreatorCallbacks;

import com.modbus.SerialClient;
import com.modbus.SerialPortParams;
import com.modbus.SerialUtils;
import com.modbus.modbus.ModBusData;
import com.modbus.modbus.ModBusDataListener;
import com.serotonin.modbus4j.ModbusFactory;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.msg.ReadHoldingRegistersRequest;
import com.serotonin.modbus4j.msg.ReadHoldingRegistersResponse;
import com.serotonin.modbus4j.msg.WriteCoilResponse;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import gnu.io.SerialPort;

public class ModbusUtil {

    public  final String ADDRESS="/dev/ttyS0";
    float temPower,humPower;
    public boolean levelWarning,TemWarning,temSwitchState,humSwitchState,temAdjustState,humAdjustState;

    public String temProtectTime,humProtectTime;
    float settingTem=0;

    SerialPortParams build=new SerialPortParams.Builder().serialPortPath(ADDRESS).build();
    final SerialClient serialClient= SerialUtils.getInstance().getSerialClient(ADDRESS);




    //获取制冷压缩机保护延时
    public String getTemProtectTime(){
        serialClient.sendData(new ModBusData<Object>(01, 03, 0x00C8, 1, new ModBusDataListener() {
            @Override
            public void onSucceed(String hexValue, byte[] bytes) {
                String s=deleteSpace(hexValue);
                int number=getAccountOfMessage(s);
                 temProtectTime=getSubMessage(s,number);

            }

            @Override
            public void onFailed(String str) {
                temProtectTime="300";
            }
        }));
        return temProtectTime;
    }
    //获取除湿压缩机保护延时
    public String getHumProtectTime(){
        serialClient.sendData(new ModBusData<Object>(01, 03, 0x00C9, 1, new ModBusDataListener() {
            @Override
            public void onSucceed(String hexValue, byte[] bytes) {
                String s=deleteSpace(hexValue);
                int number=getAccountOfMessage(s);
                humProtectTime=getSubMessage(s,number);

            }

            @Override
            public void onFailed(String str) {
                humProtectTime="300";
            }
        }));
        return humProtectTime;
    }



    //温度pid参数相关
    //湿度pid参数相关








    //十六进制转浮点型
    private  float  sixteentofloat(String s,int code){

        float result=0;
        String resultString="";

        //第一步：删除字符串中的空格
        String h=s.replace(" ","");

        //第二步：提取报文中有效信息
        String h1,q1;
        if(code==0){
            q1=h.substring(6,10);
            h1=h.substring(10,14);
        }else{
            q1=h.substring(14,18);
            h1=h.substring(18,22);
        }

        String s1=h1+q1;

        //第三步：将string类型的数据转化为float
        BigInteger bigInteger=new BigInteger(s1,16);
        float f=Float.intBitsToFloat(bigInteger.intValue());
        BigDecimal bigDecimal=new BigDecimal(f);
        String t=bigDecimal.toPlainString();


        if(t.length()<5){
            resultString=t;
        }else{
            resultString=t.substring(0,5);
        }




        if(resultString!="" && resultString!=null){

            result=Float.valueOf( resultString);


        }


        return result;
    }




    //删除报文中的空格
    public String deleteSpace(String s){
        return s.replace(" ","");
    }

    //得到返回的字节数
    public int getAccountOfMessage(String s){
        String stringAccount=s.substring(4,6);

        int account=covert(stringAccount);
        return account;
    }

    //获取指定字节数的文本型数据
    public String getSubMessage(String s,int account){
        String result=s.substring(6,6+account*2);
        return result;
    }


    //根据字节数获取浮点型数据
    public float getFloatMessage(String s,int count){

        String resultString="";
        float result=0;

        String preString=s.substring(10,14);
        String postString=s.substring(6,10);
        System.out.println(preString+"  "+postString);
        String s1=preString+postString;
        BigInteger bigInteger=new BigInteger(s1,16);
        float f=Float.intBitsToFloat(bigInteger.intValue());
        BigDecimal bigDecimal=new BigDecimal(f);
        String t=bigDecimal.toPlainString();

        if(t.length()<5){
            resultString=t;
        }else{
            resultString=t.substring(0,5);
        }

        if(resultString!="" && resultString!=null){

            result=Float.valueOf( resultString);


        }

        return result;

    }


    //十六进制转十进制
    public  int covert(String content){
        int number=0;
        String [] HighLetter = {"A","B","C","D","E","F"};
        Map<String,Integer> map = new HashMap<>();
        for(int i = 0;i <= 9;i++){
            map.put(i+"",i);
        }
        for(int j= 10;j<HighLetter.length+10;j++){
            map.put(HighLetter[j-10],j);
        }
        String[]str = new String[content.length()];
        for(int i = 0; i < str.length; i++){
            str[i] = content.substring(i,i+1);
        }
        for(int i = 0; i < str.length; i++){
            number += map.get(str[i])*Math.pow(16,str.length-1-i);
        }
        return number;
    }




    /** 十六进制转换成字节数组 */
    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase(); // 十六进制转大写字母
        int length = hexString.length() / 2; // 获取十六进制的长度，2个字符为一个十六进制
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    /** char转byte */
    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }


    public static final char[] HEX = "0123456789ABCDEF".toCharArray();
    public static String bytesToHex(byte[] bytes, int length) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < Math.min(length, bytes.length); j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX[v >>> 4];
            hexChars[j * 2 + 1] = HEX[v & 0x0F];
        }
        return new String(hexChars);
    }



    public static byte[] hex2Byte(String hex) {
        String[] parts = hex.split(" ");
        byte[] bytes = new byte[parts.length];
        for (int i = 0; i < parts.length; i++) {
            bytes[i] = (byte) Integer.parseInt(parts[i], 16);
        }
        return bytes;
    }



}


