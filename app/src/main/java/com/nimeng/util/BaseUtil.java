package com.nimeng.util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.nimeng.View.FileActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;

/**
 * Author: wfs
 * <p>
 * Create: 2022/6/21 15:55
 * <p>
 * Changes (from 2022/6/21)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/6/21 : Create BaseUtil.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class BaseUtil extends SQLiteOpenHelper {

    private  SQLiteDatabase db ;

    public  boolean tableIsExist(String tableName){
        boolean result=false;
        if(tableName==null){
            return  result;
        }
        Cursor cursor=null;
        try{
            String sql="select count(*) as c from sqlite_master where type='table' and name='"+tableName.trim()+"'";
            cursor=db.rawQuery(sql,null);
            if(cursor.moveToNext()){
                int count=cursor.getInt(0);
                if(count>0){
                    result=true;
                }
            }
        }catch (Exception e){

        }
        return result;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public BaseUtil(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        db=this.getWritableDatabase();
    }


    public void write5(String filePathName,String str,String fileName) {
        String filePath = Environment.getExternalStorageDirectory().toString()+ File.separator + "nimeng"+File.separator+filePathName;



        Log.d("filePath", "write5: "+filePath);
        /*
        *   D/filePath: write5: /storage/emulated/0/test
            D/File参数: write5:/storage/emulated/0/test/test5
        * */

        File file = new File(filePath + File.separator +fileName); // 定义File类对象

        Log.d("File参数", "write5:"+filePath+File.separator+fileName);




        if(!file.getParentFile().exists()) { // 父文件夹不存在
            file.getParentFile().mkdirs(); // 创建文件夹
        }
        try {
            FileOutputStream out = new FileOutputStream(file);
            out.write(str.getBytes());
            out.close();
        } catch(Exception e) {
            // TODOAuto-generated catch block
            e.printStackTrace();
        }
    }


//追加的方式
    public  void write6(String filePathName,String str, String fileName) {
        String filePath = Environment.getExternalStorageDirectory().toString()+ File.separator + "nimeng"+File.separator+filePathName;

        File file = new File(filePath + File.separator +fileName); // 定义File类对象
        if(!file.getParentFile().exists()) { // 父文件夹不存在
            file.getParentFile().mkdirs(); // 创建文件夹
        }
        RandomAccessFile randomFile;


        try {
            randomFile = new RandomAccessFile(filePath + File.separator + fileName,"rw");

            long length= randomFile.length();
            randomFile.seek(length);

            randomFile.write(str.getBytes());
            randomFile.close();

        } catch(Exception e) {
            // TODOAuto-generated catch block
            e.printStackTrace();
        }

    }

}
