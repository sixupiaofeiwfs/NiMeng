package com.nimeng.util;

import androidx.core.os.ParcelableCompatCreatorCallbacks;

import com.modbus.SerialClient;
import com.modbus.SerialPortParams;
import com.modbus.SerialUtils;
import com.modbus.modbus.ModBusData;
import com.modbus.modbus.ModBusDataListener;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ModbusUtil {

    public  final String ADDRESS="/dev/ttyS0";
    float temPower,humPower;
    public boolean levelWarning,TemWarning,temSwitchState,humSwitchState,temAdjustState,humAdjustState;

    public String temProtectTime,humProtectTime;

    SerialPortParams build=new SerialPortParams.Builder().serialPortPath(ADDRESS).build();
    final SerialClient serialClient= SerialUtils.getInstance().getSerialClient(ADDRESS);







    //获取温度功率
    public float getTemPower(){


        serialClient.sendData(new ModBusData<Object>(01, 03, 0x000F, 2, new ModBusDataListener() {
            @Override
            public void onSucceed(String hexValue, byte[] bytes) {
               temPower =sixteentofloat(hexValue,0);
                return ;
            }

            @Override
            public void onFailed(String str) {
                temPower=0f;
                return;
            }
        }));

        return temPower;

    }


    //获取湿度功率
    public float getHumPower(){
        serialClient.sendData(new ModBusData<Object>(01, 03, 0x0015, 2, new ModBusDataListener() {
            @Override
            public void onSucceed(String hexValue, byte[] bytes) {
                humPower=sixteentofloat(hexValue,0);
            }

            @Override
            public void onFailed(String str) {

                humPower=0;
            }
        }));

        return humPower;
    }


    //获取温度开关状态
    public boolean getTemSwitchState(){
        serialClient.sendData(new ModBusData<Object>(01, 01, 0000, 2, new ModBusDataListener() {
            @Override
            public void onSucceed(String hexValue, byte[] bytes) {
                String s=deleteSpace(hexValue);
                int number=getAccountOfMessage(s);
                String result=getSubMessage(s,number);
                if(result.equals("00")){
                    temSwitchState=false;
                }else{
                    temSwitchState=true;
                }
            }

            @Override
            public void onFailed(String str) {
                temSwitchState=false;
            }
        }));
        return temSwitchState;
    }
    //获取湿度开关状态
    public boolean getHumSwitchState(){
        serialClient.sendData(new ModBusData<Object>(01, 01, 0002, 2, new ModBusDataListener() {
            @Override
            public void onSucceed(String hexValue, byte[] bytes) {
                String s=deleteSpace(hexValue);
                int number=getAccountOfMessage(s);
                String result=getSubMessage(s,number);
                if(result.equals("00")){
                    humSwitchState=false;
                }else{
                    humSwitchState=true;
                }
            }

            @Override
            public void onFailed(String str) {
                humSwitchState=false;
            }
        }));
        return humSwitchState;
    }

    //获取温度自整定开关状态
    public boolean getTemAdjustState(){
        serialClient.sendData(new ModBusData<Object>(01, 01, 0004, 2, new ModBusDataListener() {
            @Override
            public void onSucceed(String hexValue, byte[] bytes) {
                String s=deleteSpace(hexValue);
                int number=getAccountOfMessage(s);
                String result=getSubMessage(s,number);
                if(result.equals("00")){
                    temAdjustState=false;
                }else{
                    temAdjustState=true;
                }
            }

            @Override
            public void onFailed(String str) {
                temAdjustState=false;
            }
        }));
        return temAdjustState;
    }

    //获取湿度自整定开关状态
    public boolean getHumAdjustState(){
        serialClient.sendData(new ModBusData<Object>(01, 01, 0006, 2, new ModBusDataListener() {
            @Override
            public void onSucceed(String hexValue, byte[] bytes) {
                String s=deleteSpace(hexValue);
                int number=getAccountOfMessage(s);
                String result=getSubMessage(s,number);
                if(result.equals("00")){
                    humAdjustState=false;
                }else{
                    humAdjustState=true;
                }
            }

            @Override
            public void onFailed(String str) {
                humAdjustState=false;
            }
        }));
        return humAdjustState;
    }
    //开启温度
    //关闭温度
    public void setTemOnOrOff(boolean b){


        List<byte[]> list=new ArrayList<>();
        byte[] bytes;
        if(b){
            bytes=new byte[]{00,01};
        }else{
            bytes=new byte[]{00,00};
        }

        list.add(bytes);
        serialClient.sendData(new ModBusData<Object>(01,05,0000,list));
    }


    //开启湿度
    //关闭湿度
    public void setHumOnOrOff(boolean b){
        List<byte[]> list=new ArrayList<>();
        byte[] bytes;
        if(b){
            bytes=new byte[]{00,01};
        }else{
            bytes=new byte[]{00,00};
        }

        list.add(bytes);
        serialClient.sendData(new ModBusData<Object>(01,05,0002,list));
    }

    //开启灯控开关
    public void setLightOn(){

        List<byte[]> list=new ArrayList<>();
        byte[] bytes={00,01};
        list.add(bytes);

        System.out.println("1----------------");

        serialClient.sendData(new ModBusData<Object>(01,05,0xFC07,list));
    }
    //关闭灯控开关
    public void setLightOff(){

        List<byte[]> list=new ArrayList<>();
        byte[] bytes={00,00};
        list.add(bytes);
        serialClient.sendData(new ModBusData<Object>(01,05,0xFC07,list));
    }


    public  byte[] hex2Byte(String hex) {
        String digital = "0123456789ABCDEF";
        char[] hex2char = hex.toCharArray();
        byte[] bytes = new byte[hex.length() / 2];
        int temp;
        for (int i = 0; i < bytes.length; i++) {
            // 其实和上面的函数是一样的 multiple 16 就是右移4位 这样就成了高4位了
            // 然后和低四位相加， 相当于 位操作"|"
            //相加后的数字 进行 位 "&" 操作 防止负数的自动扩展. {0xff byte最大表示数}
            temp = digital.indexOf(hex2char[2 * i]) * 16;
            temp += digital.indexOf(hex2char[2 * i + 1]);
            bytes[i] = (byte) (temp & 0xff);
        }
        return bytes;
    }

    public  byte[] mergeBytes(byte[] data1, byte[] data2) {
        byte[] data3 = new byte[data1.length + data2.length];
        System.arraycopy(data1, 0, data3, 0, data1.length);
        System.arraycopy(data2, 0, data3, data1.length, data2.length);
        return data3;
    }

    //设定温度
    public void setTem(int tem){


        String data= Integer.toHexString(tem).toUpperCase(Locale.ROOT);

        byte[] newByte=hex2Byte(data);
        System.out.println("设定温度时："+newByte.toString());
        byte[] bytes={00,02,04,00,00};
        byte[] bytesAndBytes=mergeBytes(bytes,newByte);
        List<byte[]> list=new ArrayList<>();
        list.add(bytesAndBytes);
        serialClient.sendData(new ModBusData<Object>(01,10,0x001F,list));

    }
    //设定湿度
    public void setHum(int hum){

        String data= Integer.toHexString(hum).toUpperCase(Locale.ROOT);
        byte[] newByte=hex2Byte(data);
        byte[] bytes={00,02,04,00,00};
        byte[] bytesAndBytes=mergeBytes(bytes,newByte);
        List<byte[]> list=new ArrayList<>();
        list.add(bytesAndBytes);
        serialClient.sendData(new ModBusData<Object>(01,10,0x0021,list));
    }

    //获取液位报警
    public boolean getLevelWarning(){

        serialClient.sendData(new ModBusData<Object>(01, 03, 0xF801, 1, new ModBusDataListener() {
            @Override
            public void onSucceed(String hexValue, byte[] bytes) {

                String s=deleteSpace(hexValue);
                int number=getAccountOfMessage(s);
                String result=getSubMessage(s,number);
                if(result.equals("00")){
                    levelWarning=false;
                }else{
                    levelWarning=true;
                }
            }

            @Override
            public void onFailed(String str) {

                levelWarning=false;
            }
        }));

        return levelWarning;
    }
    //获取超温报警
    public boolean getTemWarning(){
        serialClient.sendData(new ModBusData<Object>(01, 03, 0xF800, 1, new ModBusDataListener() {
            @Override
            public void onSucceed(String hexValue, byte[] bytes) {
                String s=deleteSpace(hexValue);
                int number=getAccountOfMessage(s);
                String result=getSubMessage(s,number);
                if(result.equals("00")){
                    TemWarning=false;
                }else{
                    TemWarning=true;
                }
            }

            @Override
            public void onFailed(String str) {
                TemWarning=false;
            }
        }));

        return TemWarning;
    }


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





}
