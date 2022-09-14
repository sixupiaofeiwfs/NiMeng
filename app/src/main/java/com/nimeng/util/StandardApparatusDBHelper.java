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
                "port varchar not null,"+
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
                "jzd1_1 int ,"+
                "xzz1_1 float ,"+
                "jzd2_1 int ,"+
                "xzz2_1 float ,"+
                "jzd3_1 int ,"+
                "xzz3_1 float ,"+
                "jzd4_1 int ,"+
                "xzz4_1 float ,"+
                "jzd5_1 int ,"+
                "xzz5_1 float ,"+
                "jzd6_1 int ,"+
                "xzz6_1 float ,"+
                "traceabilityUnit varchar not null,"+
                "time varchar not null,"+
                "isCheck tinyint(2),"+
                "slave int, "+
                "state int,"+
                "temStartAddress varchar,"+
                "humStartAddress varchar,"+
                "count int "+
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

            contentValues.put("jzd1_1",standardApparatus.getList3().get(0));
            contentValues.put("xzz1_1",standardApparatus.getList4().get(0));


        }else if(standardApparatus.getQuantity()==2){
            contentValues.put("jzd1",standardApparatus.getList1().get(0));
            contentValues.put("xzz1",standardApparatus.getList2().get(0));
            contentValues.put("jzd2",standardApparatus.getList1().get(1));
            contentValues.put("xzz2",standardApparatus.getList2().get(1));


            contentValues.put("jzd1_1",standardApparatus.getList3().get(0));
            contentValues.put("xzz1_1",standardApparatus.getList4().get(0));
            contentValues.put("jzd2_1",standardApparatus.getList3().get(1));
            contentValues.put("xzz2_1",standardApparatus.getList4().get(1));
        }else if(standardApparatus.getQuantity()==3){
            contentValues.put("jzd1",standardApparatus.getList1().get(0));
            contentValues.put("xzz1",standardApparatus.getList2().get(0));
            contentValues.put("jzd2",standardApparatus.getList1().get(1));
            contentValues.put("xzz2",standardApparatus.getList2().get(1));
            contentValues.put("jzd3",standardApparatus.getList1().get(2));
            contentValues.put("xzz3",standardApparatus.getList2().get(2));


            contentValues.put("jzd1_1",standardApparatus.getList3().get(0));
            contentValues.put("xzz1_1",standardApparatus.getList4().get(0));
            contentValues.put("jzd2_1",standardApparatus.getList3().get(1));
            contentValues.put("xzz2_1",standardApparatus.getList4().get(1));
            contentValues.put("jzd3_1",standardApparatus.getList3().get(2));
            contentValues.put("xzz3_1",standardApparatus.getList4().get(2));
        }else if(standardApparatus.getQuantity()==4){
            contentValues.put("jzd1",standardApparatus.getList1().get(0));
            contentValues.put("xzz1",standardApparatus.getList2().get(0));
            contentValues.put("jzd2",standardApparatus.getList1().get(1));
            contentValues.put("xzz2",standardApparatus.getList2().get(1));
            contentValues.put("jzd3",standardApparatus.getList1().get(2));
            contentValues.put("xzz3",standardApparatus.getList2().get(2));
            contentValues.put("jzd4",standardApparatus.getList1().get(3));
            contentValues.put("xzz4",standardApparatus.getList2().get(3));


            contentValues.put("jzd1_1",standardApparatus.getList3().get(0));
            contentValues.put("xzz1_1",standardApparatus.getList4().get(0));
            contentValues.put("jzd2_1",standardApparatus.getList3().get(1));
            contentValues.put("xzz2_1",standardApparatus.getList4().get(1));
            contentValues.put("jzd3_1",standardApparatus.getList3().get(2));
            contentValues.put("xzz3_1",standardApparatus.getList4().get(2));
            contentValues.put("jzd4_1",standardApparatus.getList3().get(3));
            contentValues.put("xzz4_1",standardApparatus.getList4().get(3));
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



            contentValues.put("jzd1_1",standardApparatus.getList3().get(0));
            contentValues.put("xzz1_1",standardApparatus.getList4().get(0));
            contentValues.put("jzd2_1",standardApparatus.getList3().get(1));
            contentValues.put("xzz2_1",standardApparatus.getList4().get(1));
            contentValues.put("jzd3_1",standardApparatus.getList3().get(2));
            contentValues.put("xzz3_1",standardApparatus.getList4().get(2));
            contentValues.put("jzd4_1",standardApparatus.getList3().get(3));
            contentValues.put("xzz4_1",standardApparatus.getList4().get(3));
            contentValues.put("jzd5_1",standardApparatus.getList3().get(4));
            contentValues.put("xzz5_1",standardApparatus.getList4().get(4));
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



            contentValues.put("jzd1_1",standardApparatus.getList3().get(0));
            contentValues.put("xzz1_1",standardApparatus.getList4().get(0));
            contentValues.put("jzd2_1",standardApparatus.getList3().get(1));
            contentValues.put("xzz2_1",standardApparatus.getList4().get(1));
            contentValues.put("jzd3_1",standardApparatus.getList3().get(2));
            contentValues.put("xzz3_1",standardApparatus.getList4().get(2));
            contentValues.put("jzd4_1",standardApparatus.getList3().get(3));
            contentValues.put("xzz4_1",standardApparatus.getList4().get(3));
            contentValues.put("jzd5_1",standardApparatus.getList3().get(4));
            contentValues.put("xzz5_1",standardApparatus.getList4().get(4));
            contentValues.put("jzd6_1",standardApparatus.getList3().get(5));
            contentValues.put("xzz6_1",standardApparatus.getList4().get(5));
        }
        contentValues.put("traceabilityUnit",standardApparatus.getTraceabilityUnit());
        contentValues.put("time",standardApparatus.getTime());
        contentValues.put("isCheck",standardApparatus.getIsCheck());
        contentValues.put("slave",standardApparatus.getSlave());
        contentValues.put("state",standardApparatus.getState());
        contentValues.put("temStartAddress",standardApparatus.getTemStartAddress());
        contentValues.put("humStartAddress",standardApparatus.getHumStartAddress());
        contentValues.put("count",standardApparatus.getCount());



        long result=db.insert(tableName,null,contentValues);
        return result>0?true:false;


    }


    public boolean delete(String tableName,int ID){
        int result=db.delete(tableName,"id=?",new String[]{String.valueOf(ID)});
        return result>0?true:false;
    }


    /**
     * 查询温湿度标准器
     * @param tableName  温湿度表名
     * @param isCheck    是否查询被选中的标准器 0查询所有标准器 1查询被选中的标准器
     * @return
     */
    public List<StandardApparatus> query(String tableName,@Nullable int isCheck){
        List<StandardApparatus> list =new ArrayList<>();

        if(!tableIsExist(tableName)){
            return list;
        }

        Log.d("判断数据表是否已打开", "query: "+db.isOpen());

        Cursor result=null;
        if(isCheck!=1){
             result=db.query(tableName,null,null,null,null,null,null);
        }else{
             result=db.query(tableName,null,"isCheck=",new String[]{isCheck+""},null,null,null);
        }


        System.out.println("result--->"+result.getCount());

        if(result!=null){
            while (result.moveToNext()){
                StandardApparatus standardApparatus=new StandardApparatus();
                standardApparatus.setID(result.getInt(0));
                standardApparatus.setName(result.getString(1));
                standardApparatus.setPort(result.getString(2));
                standardApparatus.setFormat(result.getString(3));
                standardApparatus.setRate(result.getInt(4));
                standardApparatus.setType(result.getString(5));
                standardApparatus.setModel(result.getString(6));
                standardApparatus.setAgreement(result.getString(7));
                standardApparatus.setNumber(result.getString(8));
                standardApparatus.setQuantity(result.getInt(9));

                List<Integer> list1=new ArrayList<>();
                List<Float>list2=new ArrayList<>();
                List<Integer> list3=new ArrayList<>();
                List<Float>list4=new ArrayList<>();
                if(result.getInt(9)==1){
                    list1.add(result.getInt(10));
                    list2.add(result.getFloat(11));


                    list3.add(result.getInt(22));
                    list4.add(result.getFloat(23));


                } if(result.getInt(9)==2){
                    list1.add(result.getInt(10));
                    list2.add(result.getFloat(11));
                    list1.add(result.getInt(12));
                    list2.add(result.getFloat(13));



                    list3.add(result.getInt(22));
                    list4.add(result.getFloat(23));
                    list3.add(result.getInt(24));
                    list4.add(result.getFloat(25));


                } if(result.getInt(9)==3){
                    list1.add(result.getInt(10));
                    list2.add(result.getFloat(11));
                    list1.add(result.getInt(12));
                    list2.add(result.getFloat(13));
                    list1.add(result.getInt(14));
                    list2.add(result.getFloat(15));




                    list3.add(result.getInt(22));
                    list4.add(result.getFloat(23));
                    list3.add(result.getInt(24));
                    list4.add(result.getFloat(25));
                    list3.add(result.getInt(26));
                    list4.add(result.getFloat(27));


                } if(result.getInt(9)==4){
                    list1.add(result.getInt(10));
                    list2.add(result.getFloat(11));
                    list1.add(result.getInt(12));
                    list2.add(result.getFloat(13));
                    list1.add(result.getInt(14));
                    list2.add(result.getFloat(15));
                    list1.add(result.getInt(16));
                    list2.add(result.getFloat(17));


                    list3.add(result.getInt(22));
                    list4.add(result.getFloat(23));
                    list3.add(result.getInt(24));
                    list4.add(result.getFloat(25));
                    list3.add(result.getInt(26));
                    list4.add(result.getFloat(27));
                    list3.add(result.getInt(28));
                    list4.add(result.getFloat(29));


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



                    list3.add(result.getInt(22));
                    list4.add(result.getFloat(23));
                    list3.add(result.getInt(24));
                    list4.add(result.getFloat(25));
                    list3.add(result.getInt(26));
                    list4.add(result.getFloat(27));
                    list3.add(result.getInt(28));
                    list4.add(result.getFloat(29));
                    list3.add(result.getInt(30));
                    list4.add(result.getFloat(31));


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



                    list3.add(result.getInt(22));
                    list4.add(result.getFloat(23));
                    list3.add(result.getInt(24));
                    list4.add(result.getFloat(25));
                    list3.add(result.getInt(26));
                    list4.add(result.getFloat(27));
                    list3.add(result.getInt(28));
                    list4.add(result.getFloat(29));
                    list3.add(result.getInt(30));
                    list4.add(result.getFloat(31));
                    list3.add(result.getInt(32));
                    list4.add(result.getFloat(33));

                }

                standardApparatus.setList1(list1);
                standardApparatus.setList2(list2);
                standardApparatus.setList3(list3);
                standardApparatus.setList4(list4);
                standardApparatus.setTraceabilityUnit(result.getString(34));
                standardApparatus.setTime(result.getString(35));
                standardApparatus.setIsCheck(result.getInt(36));
                standardApparatus.setSlave(result.getInt(37));
                standardApparatus.setState(result.getInt(38));
                standardApparatus.setTemStartAddress(result.getString(39));
                standardApparatus.setHumStartAddress(result.getString(40));
                standardApparatus.setCount(result.getInt(41));



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

        int number=result.getCount();
        result.close();
        return number;


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
