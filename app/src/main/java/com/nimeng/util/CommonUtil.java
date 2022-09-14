package com.nimeng.util;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Environment;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.nimeng.Adapter.StandardApparatusAdapter;
import com.nimeng.View.R;
import com.nimeng.bean.StandardApparatus;
import com.nimeng.bean.SystemData;

import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CommonUtil extends Activity {


    //用于获取存储权限
    private static final int REQUEST_CODE =1024 ;

    /**
     *
     * 用于监测手指滑动
     */
    //手指上下滑动时的最小速度
    public static final int YSPEED_MIN=1000;
    //手指向右滑动的最小距离
    public static  final int XDISTANCE_MIN=50;
    //手指向上滑动或向下滑动的最小距离
    public static  final int YDISTANCE_MIN=66;
    public static final String TAG="BaseActivity";
    //记录手指按下时的横坐标
    public float xDown;
    //记录手指按下时的纵坐标
    public float yDown;
    //记录手指移动时的横坐标;
    public float xMove;
    //记录手指移动时的纵坐标
    public float yMove;
    //用于计算手指滑动的速度
    public VelocityTracker velocityTracker;




    public List<StandardApparatus> list;

    LinearLayout linearLayout1,linearLayout2,linearLayout3,linearLayout4, linearLayout5,linearLayout6;
    Spinner spinner;
    private EditText editName, editPort,editFormat,editRate,editType,editModel,editAgreement,editNumber,editTraceabilityUnit;

    private EditText edit_jzd1,edit_xzz1,edit_jzd2,edit_xzz2,edit_jzd3,edit_xzz3,edit_jzd4,edit_xzz4,edit_jzd5,edit_xzz5,edit_jzd6,edit_xzz6;


    private int quantity;


    private TextView btn1,editTime;
    private DatePicker datePicker;
    private int year,month,day;

    private  Intent intent;




    SystemDBHelper systemDBHelper;
    SystemData systemData;









    public  int  checkTime() {

        //systemDBHelper=new SystemDBHelper(CommonUtil.this,"NIMENG.db",null,1);
        systemDBHelper=new SystemDBHelper(CommonUtil.this);

        Date newDate=new Date();

        String stringDate=newDate.toString();

        System.out.println("newDate--->toString--->"+stringDate);
        int stages=systemDBHelper.getSystemData().getNumberOfStages();

        for(int i=0;i<stages;i++){


            System.out.println("总"+stages+"期");
            System.out.println("当前分期："+systemDBHelper.getPassword().get(i).getTimes().getTime());
            System.out.println("当前日期："+getTimeToDate(stringDate).getTime());





            if( systemDBHelper.getPassword().get(i).getTimes().getTime()>getTimeToDate(stringDate).getTime()){
                System.out.println("当前处于第"+(i+1)+"期");

                return i+1;
            }


        }

        return stages;
    }



    //获取系统时间
    public String gettime() {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH)+1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        int hour=c.get(Calendar.HOUR);
        int minute=c.get(Calendar.MINUTE);
        int second=c.get(Calendar.SECOND);

        String time=year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second;
        return time;
    }


    public Date getTimeToDate(String time){
        Date date =new Date();
        try {
            date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }



    public void deleteData(String tableName, StandardApparatusDBHelper standardApparatusDBHelper, StandardApparatusAdapter standardApparatusAdapter, Context context, int position, ListView listView){
        //systemDBHelper=new SystemDBHelper(CommonUtil.this,"NIMENG.db",null,1);
        systemDBHelper=new SystemDBHelper(CommonUtil.this);
        systemData=systemDBHelper.getSystemData();
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle("提示")
                .setMessage("是否删除该标准器")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        StandardApparatus standardApparatus1=(StandardApparatus) standardApparatusAdapter.getItem(position);
                        int deleteID= standardApparatus1.getID();

                        if(deleteID==systemData.getHumStandardID()){
                            showToast(context,"当前标准器正在使用，不能删除");
                        }else{
                            if(standardApparatusDBHelper.delete(tableName,deleteID)){
                                updateListView(standardApparatusDBHelper,tableName,standardApparatusAdapter,listView);
                                showToast(context,"删除成功");
                            }else{
                                showToast(context,"删除失败");
                            }
                        }



                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }


    public void updateListView(StandardApparatusDBHelper standardApparatusDBHelper,String tableName,StandardApparatusAdapter standardApparatusAdapter, ListView listView){
        List<StandardApparatus> list=standardApparatusDBHelper.query(tableName,0);
        standardApparatusAdapter.setList(list);
        listView.setAdapter(standardApparatusAdapter);
    }





    public void updateCheck(Context context,StandardApparatusAdapter standardApparatusAdapter ,StandardApparatusDBHelper standardApparatusDBHelper,String tableName,int position,ListView listView){

       // systemDBHelper=new SystemDBHelper(CommonUtil.this,"NIMENG.db",null,1);
        systemDBHelper=new SystemDBHelper(CommonUtil.this);
        systemData=systemDBHelper.getSystemData();

        StandardApparatus standardApparatus=(StandardApparatus) standardApparatusAdapter.getItem(position);

        AlertDialog.Builder builder=new AlertDialog.Builder(context);

        final Map<String,String> map=new LinkedHashMap<>(standardApparatus.getQuantity());

        for(int i=0;i<standardApparatus.getQuantity();i++){
            map.put("温度校准点："+standardApparatus.getList1().get(i)+"  温度修正值："+standardApparatus.getList2().get(i),String.valueOf(i+1));
            map.put("湿度校准点："+standardApparatus.getList3().get(i)+"  湿度修正值："+standardApparatus.getList4().get(i),String.valueOf(i+1));
        }

        final String[] keysTemp=new String[standardApparatus.getQuantity()];
        final String[] keys=map.keySet().toArray(keysTemp);


        builder.setTitle("选择该标准器？")
                .setItems(keys,null)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(standardApparatusDBHelper.updateCheck(tableName,standardApparatus.getID(),systemData.getHumStandardID()));
                        systemData.setHumStandardID(standardApparatus.getID());
                        systemDBHelper.updateSystemData(systemData);
                        Toast.makeText(context,"已选择"+standardApparatus.getName(),Toast.LENGTH_SHORT).show();
                        updateListView(standardApparatusDBHelper,tableName,standardApparatusAdapter,listView);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }


    public Date transferStringToDate(String s){
        Date date=new Date();
        try{
            date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(s);
        }catch(ParseException e){

        }
        return date;
    }

    public void showToast(Context context,String msg){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }

    public String getDateTimeToString(Date date){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=simpleDateFormat.format(date);
        return time;
    }



    //创建VelocityTracker对象,并将触摸界面的滑动事件添加到VelocityTracker中
    public void createVelocityTracker(MotionEvent event){
        if (velocityTracker==null){
            velocityTracker= VelocityTracker.obtain();
        }
        velocityTracker.addMovement(event);
    }
    //回收VelocityTracker对象
    public void recycleVelocityTracker(){
        velocityTracker.recycle();
        velocityTracker=null;
    }
    //滑动速度,以每秒钟移动了多少像素值为单位
    public int getScrollVelocity(){
        velocityTracker.computeCurrentVelocity(1000);
        int velocity=(int)velocityTracker.getYVelocity();
        return Math.abs(velocity);
    }


    //读温度功率


    public String floatToString(float f){
        DecimalFormat decimalFormat=new DecimalFormat("##0.00");
        String s=decimalFormat.format(f);
        return s;
    }








    //判断当前设备运行到了第几分钟（用于显示变化速率）
    public  long getDatePoor() {

        Date date=new Date();
        //systemDBHelper=new SystemDBHelper(CommonUtil.this,"NIMENG.db",null,1);
        systemDBHelper=new SystemDBHelper(CommonUtil.this);
        SystemData systemData1=systemDBHelper.getSystemData();
        if(systemData1.getStartTime()==null){
            return 1;
        }

        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = date.getTime() - getTimeToDate( systemData1.getStartTime()).getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;

        if(min==0){
            min=1;
        }
      return min;
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

    public String getNextTime(){
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
         c.add(Calendar.SECOND, -5);//前一秒
       // c.add(Calendar.SECOND, 1);//后一秒
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss" );
        String strTime = sdf.format(c.getTime());
        System.out.println(strTime);
        return strTime;
    }


}
