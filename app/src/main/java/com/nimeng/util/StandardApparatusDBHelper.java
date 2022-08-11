package com.nimeng.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

import com.nimeng.bean.StandardApparatus;


import java.util.ArrayList;
import java.util.List;

public class StandardApparatusDBHelper extends BaseUtil{

    private SQLiteDatabase db;
    ContentValues contentValues=new ContentValues();


    public StandardApparatusDBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        db=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        super.onCreate(sqLiteDatabase);
    }



    public void createTable(SQLiteDatabase sqLiteDatabase,String tableName){

        String sql="create table "
                +tableName+
                "("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "name varchar(30) not null,"+
                "port int not null,"+
                "format varchar not null,"+
                "rate int not null,"+
                "type varchar not null,"+
                "model varchar not null,"+
                "agreement varchar not null,"+
                "number varchar not null,"+
                "quantity int not null,"+
                "jzd1 int ,"+
                "xzz1 float ,"+
                "jzd2 int ,"+
                "xzz2 float ,"+
                "jzd3 int ,"+
                "xzz3 float ,"+
                "jzd4 int ,"+
                "xzz4 float ,"+
                "jzd5 int ,"+
                "xzz5 float ,"+
                "jzd6 int ,"+
                "xzz6 float ,"+
                "traceabilityUnit varchar not null,"+
                "time varchar not null,"+
                "isCheck tinyint(2)"+
                ")";
        sqLiteDatabase.execSQL(sql);
    }



    public boolean add(StandardApparatus standardApparatus,String tableName){
        if(!tableIsExist(tableName)){
            createTable(db,tableName);
        }

        contentValues.put("name",standardApparatus.getName());
        contentValues.put("port",standardApparatus.getPort());
        contentValues.put("format",standardApparatus.getFormat());
        contentValues.put("rate",standardApparatus.getRate());
        contentValues.put("type",standardApparatus.getType());
        contentValues.put("model",standardApparatus.getModel());
        contentValues.put("agreement",standardApparatus.getAgreement());
        contentValues.put("number",standardApparatus.getNumber());
        contentValues.put("quantity",standardApparatus.getQuantity());
        if(standardApparatus.getQuantity()==1){
            contentValues.put("jzd1",standardApparatus.getList1().get(0));
            contentValues.put("xzz1",standardApparatus.getList2().get(0));
        }else if(standardApparatus.getQuantity()==2){
            contentValues.put("jzd1",standardApparatus.getList1().get(0));
            contentValues.put("xzz1",standardApparatus.getList2().get(0));
            contentValues.put("jzd2",standardApparatus.getList1().get(1));
            contentValues.put("xzz2",standardApparatus.getList2().get(1));
        }else if(standardApparatus.getQuantity()==3){
            contentValues.put("jzd1",standardApparatus.getList1().get(0));
            contentValues.put("xzz1",standardApparatus.getList2().get(0));
            contentValues.put("jzd2",standardApparatus.getList1().get(1));
            contentValues.put("xzz2",standardApparatus.getList2().get(1));
            contentValues.put("jzd3",standardApparatus.getList1().get(2));
            contentValues.put("xzz3",standardApparatus.getList2().get(2));
        }else if(standardApparatus.getQuantity()==4){
            contentValues.put("jzd1",standardApparatus.getList1().get(0));
            contentValues.put("xzz1",standardApparatus.getList2().get(0));
            contentValues.put("jzd2",standardApparatus.getList1().get(1));
            contentValues.put("xzz2",standardApparatus.getList2().get(1));
            contentValues.put("jzd3",standardApparatus.getList1().get(2));
            contentValues.put("xzz3",standardApparatus.getList2().get(2));
            contentValues.put("jzd4",standardApparatus.getList1().get(3));
            contentValues.put("xzz4",standardApparatus.getList2().get(3));
        }else if(standardApparatus.getQuantity()==5){
            contentValues.put("jzd1",standardApparatus.getList1().get(0));
            contentValues.put("xzz1",standardApparatus.getList2().get(0));
            contentValues.put("jzd2",standardApparatus.getList1().get(1));
            contentValues.put("xzz2",standardApparatus.getList2().get(1));
            contentValues.put("jzd3",standardApparatus.getList1().get(2));
            contentValues.put("xzz3",standardApparatus.getList2().get(2));
            contentValues.put("jzd4",standardApparatus.getList1().get(3));
            contentValues.put("xzz4",standardApparatus.getList2().get(3));
            contentValues.put("jzd5",standardApparatus.getList1().get(4));
            contentValues.put("xzz5",standardApparatus.getList2().get(4));
        }else if(standardApparatus.getQuantity()==6){
            contentValues.put("jzd1",standardApparatus.getList1().get(0));
            contentValues.put("xzz1",standardApparatus.getList2().get(0));
            contentValues.put("jzd2",standardApparatus.getList1().get(1));
            contentValues.put("xzz2",standardApparatus.getList2().get(1));
            contentValues.put("jzd3",standardApparatus.getList1().get(2));
            contentValues.put("xzz3",standardApparatus.getList2().get(2));
            contentValues.put("jzd4",standardApparatus.getList1().get(3));
            contentValues.put("xzz4",standardApparatus.getList2().get(3));
            contentValues.put("jzd5",standardApparatus.getList1().get(4));
            contentValues.put("xzz5",standardApparatus.getList2().get(4));
            contentValues.put("jzd6",standardApparatus.getList1().get(5));
            contentValues.put("xzz6",standardApparatus.getList2().get(5));
        }
        contentValues.put("traceabilityUnit",standardApparatus.getTraceabilityUnit());
        contentValues.put("time",standardApparatus.getTime());
        contentValues.put("isCheck",standardApparatus.getIsCheck());

        long result=db.insert(tableName,null,contentValues);
        return result>0?true:false;


    }


    public boolean delete(String tableName,int ID){
        int result=db.delete(tableName,"id=?",new String[]{String.valueOf(ID)});
        return result>0?true:false;
    }



    //查询
    public List<StandardApparatus> query(String tableName){
        List<StandardApparatus> list =new ArrayList<>();

        if(!tableIsExist(tableName)){
            return list;
        }

        Log.d("判断数据表是否已打开", "query: "+db.isOpen());


        Cursor result=db.query(tableName,null,null,null,null,null,null);

        System.out.println("result--->"+result.getCount());

        if(result!=null){
            while (result.moveToNext()){
                StandardApparatus standardApparatus=new StandardApparatus();
                standardApparatus.setID(result.getInt(0));
                standardApparatus.setName(result.getString(1));
                standardApparatus.setPort(result.getInt(2));
                standardApparatus.setFormat(result.getString(3));
                standardApparatus.setRate(result.getInt(4));
                standardApparatus.setType(result.getString(5));
                standardApparatus.setModel(result.getString(6));
                standardApparatus.setAgreement(result.getString(7));
                standardApparatus.setNumber(result.getString(8));
                standardApparatus.setQuantity(result.getInt(9));

                List<Integer> list1=new ArrayList<>();
                List<Float>list2=new ArrayList<>();
                if(result.getInt(9)==1){
                    list1.add(result.getInt(10));
                    list2.add(result.getFloat(11));
                } if(result.getInt(9)==2){
                    list1.add(result.getInt(10));
                    list2.add(result.getFloat(11));
                    list1.add(result.getInt(12));
                    list2.add(result.getFloat(13));
                } if(result.getInt(9)==3){
                    list1.add(result.getInt(10));
                    list2.add(result.getFloat(11));
                    list1.add(result.getInt(12));
                    list2.add(result.getFloat(13));
                    list1.add(result.getInt(14));
                    list2.add(result.getFloat(15));
                } if(result.getInt(9)==4){
                    list1.add(result.getInt(10));
                    list2.add(result.getFloat(11));
                    list1.add(result.getInt(12));
                    list2.add(result.getFloat(13));
                    list1.add(result.getInt(14));
                    list2.add(result.getFloat(15));
                    list1.add(result.getInt(16));
                    list2.add(result.getFloat(17));
                } if(result.getInt(9)==5){
                    list1.add(result.getInt(10));
                    list2.add(result.getFloat(11));
                    list1.add(result.getInt(12));
                    list2.add(result.getFloat(13));
                    list1.add(result.getInt(14));
                    list2.add(result.getFloat(15));
                    list1.add(result.getInt(16));
                    list2.add(result.getFloat(17));
                    list1.add(result.getInt(18));
                    list2.add(result.getFloat(19));
                } if(result.getInt(9)==6){
                    list1.add(result.getInt(10));
                    list2.add(result.getFloat(11));
                    list1.add(result.getInt(12));
                    list2.add(result.getFloat(13));
                    list1.add(result.getInt(14));
                    list2.add(result.getFloat(15));
                    list1.add(result.getInt(16));
                    list2.add(result.getFloat(17));
                    list1.add(result.getInt(18));
                    list2.add(result.getFloat(19));
                    list1.add(result.getInt(20));
                    list2.add(result.getFloat(21));
                }

                standardApparatus.setList1(list1);
                standardApparatus.setList2(list2);
                standardApparatus.setTraceabilityUnit(result.getString(22));
                standardApparatus.setTime(result.getString(23));
                standardApparatus.setIsCheck(result.getInt(24));

                list.add(standardApparatus);

            }result.close();
        }

        return list;
    }


    //通过名称查询
    public int findByName(String tableName,String name){
        if(!tableIsExist(tableName)){
            return 0;
        }


        Cursor result =db.query(tableName,null,"name=?",new String[]{name},null,null,null,null);

        return result.getCount();


    }

    public boolean updateCheck(String tableName,int id,int isCheckID){
        System.out.println("选中时----》》》"+id+"------>>>"+isCheckID);
        if(isCheckID!=0){
            String sql="update "+tableName+" set isCheck=0 where id="+isCheckID;
            db.execSQL(sql);
        }

        String sql="update "+tableName+" set isCheck=1 where id="+id;
        db.execSQL(sql);
        return true;
    }



}
