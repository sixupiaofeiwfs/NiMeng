package com.nimeng.View;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.nimeng.Adapter.StandardApparatusAdapter;
import com.nimeng.Presenter.IBasePresenter;
import com.nimeng.bean.GlobalVariable;

import com.nimeng.bean.StandardApparatus;
import com.nimeng.util.StandardApparatusDBHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: wfs
 * <p>
 * Create: 2022/4/13 14:04
 * <p>
 * Changes (from 2022/4/13)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/4/13 : Create BaseAvtivity.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class BaseAvtivity <P extends IBasePresenter> extends BaseXActivity<P> implements IBaseView{

    private static final int REQUEST_CODE =1024 ;
    //加载进度框
    private ProgressDialog mProgressDialog;

    //手指上下滑动时的最小速度
    private static final int YSPEED_MIN=1000;
    //手指向右滑动的最小距离
    private static  final int XDISTANCE_MIN=50;
    //手指向上滑动或向下滑动的最小距离
    private static  final int YDISTANCE_MIN=66;
    private static final String TAG="BaseActivity";
    //记录手指按下时的横坐标
    private float xDown;
    //记录手指按下时的纵坐标
    private float yDown;
    //记录手指移动时的横坐标;
    private float xMove;
    //记录手指移动时的纵坐标
    private float yMove;
    //用于计算手指滑动的速度
    private VelocityTracker velocityTracker;


    private String activityName;


    public GlobalVariable globalVariable;

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
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: BaseActivity-onCreate");
        globalVariable=(GlobalVariable) getApplicationContext();
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        createVelocityTracker(ev);
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                xDown=ev.getRawX();
                yDown=ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                xMove=ev.getRawX();
                yMove=ev.getRawY();
                //滑动的距离
                int distanceX=(int)(xMove-xDown);
                int distanceY=(int)(yMove-yDown);
                //获取瞬时速度
                int ySpeed=getScrollVelocity();
                //关闭activity需要满足一下条件
                //x轴滑动的距离大于XDISTANCE_MIN
                //y轴滑动的距离大于YDISTANCE_MIN
                //y轴上(即上下滑动的速度)<YSPEED_MIN,如果大于,则认为用户意图时上下滑动而非左右滑动



                if(distanceX>XDISTANCE_MIN && (distanceY<YDISTANCE_MIN && distanceY >-YDISTANCE_MIN) && ySpeed<YSPEED_MIN && distanceX>0){
                   if(this.getClass().getName().equals("com.nimeng.View.MainActivity")){
                        startActivity(new Intent(this,SettingSwitchActivity.class));
                    }else if(this.getClass().getName().equals("com.nimeng.View.SettingSwitchActivity")){
                        startActivity(new Intent(this,DataRecordActivity.class));
                    }else if(this.getClass().getName().equals("com.nimeng.View.DataRecordActivity")){
                        startActivity(new Intent(this, LineChartActivity.class));
                    }else if(this.getClass().getName().equals("com.nimeng.View.TemPlanActivity")){
                       startActivity(new Intent(this,HumPlanActivity.class));
                   }else if(this.getClass().getName().equals("com.nimeng.View.HumPlanActivity")){
                       startActivity(new Intent(this,TemPlanActivity.class));
                   }else if(this.getClass().getName().equals("com.nimeng.View.LineChartActivity")){
                       intent=new Intent(this,MainActivity.class);
                       intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                       startActivity(intent);
                   }



                }else if (-distanceX>XDISTANCE_MIN && (distanceY<YDISTANCE_MIN && distanceY >-YDISTANCE_MIN) && ySpeed<YSPEED_MIN && -distanceX>0){

                    if(this.getClass().getName().equals("com.nimeng.View.MainActivity")){
                        startActivity(new Intent(this, LineChartActivity.class));
                    }else if(this.getClass().getName().equals("com.nimeng.View.DataRecordActivity") || this.getClass().getName().equals("com.nimeng.View.TemPlanActivity") || this.getClass().getName().equals("com.nimeng.View.HumPlanActivity")){
                        startActivity(new Intent(this,SettingSwitchActivity.class));
                    }else if(this.getClass().getName().equals("com.nimeng.View.SettingSwitchActivity")) {
                        intent=new Intent(this,MainActivity.class);
                        startActivity(intent);
                    }

                }
                break;
            case MotionEvent.ACTION_UP:
                recycleVelocityTracker();
                break;
            default:
                break;

        }
        return super.dispatchTouchEvent(ev);
    }

    //创建VelocityTracker对象,并将触摸界面的滑动事件添加到VelocityTracker中
    private void createVelocityTracker(MotionEvent event){
        if (velocityTracker==null){
            velocityTracker=VelocityTracker.obtain();
        }
        velocityTracker.addMovement(event);
    }
    //回收VelocityTracker对象
    private void recycleVelocityTracker(){
        velocityTracker.recycle();
        velocityTracker=null;
    }
    //滑动速度,以每秒钟移动了多少像素值为单位
    private int getScrollVelocity(){
        velocityTracker.computeCurrentVelocity(1000);
        int velocity=(int)velocityTracker.getYVelocity();
        return Math.abs(velocity);
    }




    @Override
    public void showLoading(){

    }

    @Override
    public void hideLoading(){

    }

    @Override
    public void showToast(String msg){

    }

    @Override
    public P onBindPresenter() {
        return null;
    }

    @Override
    public void onDestroy(){
        hideLoading();
        super.onDestroy();
    }



    // 获取存储权限
    public void onPermission(GlobalVariable globalVariable){
        if(globalVariable.isHaveJurisdiction()){
            return ;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // 先判断有没有权限
            if (Environment.isExternalStorageManager()) {
                requestSuccess(globalVariable);
            } else {
                Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                intent.setData(Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, REQUEST_CODE);
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // 先判断有没有权限
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                requestSuccess(globalVariable);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
            }
        } else {
            requestSuccess(globalVariable);
        }
    }



    /**
     * 模拟文件写入
     */
    private void requestSuccess(GlobalVariable globalVariable) {
        Toast.makeText(this, "存储权限获取成功", Toast.LENGTH_SHORT).show();
        globalVariable.setHaveJurisdiction(true);

    }


    public  int  checkTime() {


        Date newDate=new Date();

        String stringDate=newDate.toString();

        System.out.println("newDate--->toString--->"+stringDate);

        for(int i=0;i<globalVariable.getNumberOfStages();i++){


            System.out.println("总"+globalVariable.getNumberOfStages()+"期");
            System.out.println("当前分期："+getTimeToDate( globalVariable.getTimes().get(i)).getTime());
            System.out.println("当前日期："+getTimeToDate(stringDate).getTime());

            if(getTimeToDate( globalVariable.getTimes().get(i)).getTime()>getTimeToDate(stringDate).getTime()){
                System.out.println("当前处于第"+(i+1)+"期");

                return i+1;
            }


        }

        return globalVariable.getNumberOfStages();
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


    public String getDateTimeToString(Date date){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String time=simpleDateFormat.format(date);
        return time;
    }

    public void deleteData(String tableName, StandardApparatusDBHelper standardApparatusDBHelper, StandardApparatusAdapter standardApparatusAdapter,Context context, int position,ListView listView){

        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle("提示")
                .setMessage("是否删除该标准器")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        StandardApparatus standardApparatus1=(StandardApparatus) standardApparatusAdapter.getItem(position);
                        int deleteID= standardApparatus1.getID();

                        if(deleteID==globalVariable.getHumStandardID()){
                            showToast("当前标准器正在使用，不能删除");
                        }else{
                            if(standardApparatusDBHelper.delete(tableName,deleteID)){
                                updateListView(standardApparatusDBHelper,tableName,standardApparatusAdapter,listView);
                                showToast("删除成功");
                            }else{
                                showToast("删除失败");
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
        List<StandardApparatus>  list=standardApparatusDBHelper.query(tableName);
        standardApparatusAdapter.setList(list);
        listView.setAdapter(standardApparatusAdapter);
    }


    public void addData(Context context,StandardApparatusDBHelper standardApparatusDBHelper,String tableName,StandardApparatusAdapter standardApparatusAdapter,ListView listView){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        View dialogView =View.inflate(context,R.layout.standardapparatus_edit,null);

        btn1=dialogView.findViewById(R.id.btn_1);
        datePicker=dialogView.findViewById(R.id.datepick1);
        spinner=dialogView.findViewById(R.id.standard_spinner1);
        editTime=dialogView.findViewById(R.id.edit_s_time);
        linearLayout1=dialogView.findViewById(R.id.standard_LinearLayout7);
        linearLayout2=dialogView.findViewById(R.id.standard_LinearLayout8);
        linearLayout3=dialogView.findViewById(R.id.standard_LinearLayout9);
        linearLayout4=dialogView.findViewById(R.id.standard_LinearLayout10);
        linearLayout5=dialogView.findViewById(R.id.standard_LinearLayout11);
        linearLayout6=dialogView.findViewById(R.id.standard_LinearLayout12);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                System.out.println(i+"---------------"+l);
                if(i==0){
                    linearLayout1.setVisibility(View.VISIBLE);
                    linearLayout2.setVisibility(View.GONE);
                    linearLayout3.setVisibility(View.GONE);
                    linearLayout4.setVisibility(View.GONE);
                    linearLayout5.setVisibility(View.GONE);
                    linearLayout6.setVisibility(View.GONE);

                } if(i==1){
                    linearLayout1.setVisibility(View.VISIBLE);
                    linearLayout2.setVisibility(View.VISIBLE);
                    linearLayout3.setVisibility(View.GONE);
                    linearLayout4.setVisibility(View.GONE);
                    linearLayout5.setVisibility(View.GONE);
                    linearLayout6.setVisibility(View.GONE);

                } if(i==2){
                    linearLayout1.setVisibility(View.VISIBLE);
                    linearLayout2.setVisibility(View.VISIBLE);
                    linearLayout3.setVisibility(View.VISIBLE);
                    linearLayout4.setVisibility(View.GONE);
                    linearLayout5.setVisibility(View.GONE);
                    linearLayout6.setVisibility(View.GONE);

                } if(i==3){
                    linearLayout1.setVisibility(View.VISIBLE);
                    linearLayout2.setVisibility(View.VISIBLE);
                    linearLayout3.setVisibility(View.VISIBLE);
                    linearLayout4.setVisibility(View.VISIBLE);
                    linearLayout5.setVisibility(View.GONE);
                    linearLayout6.setVisibility(View.GONE);

                } if(i==4){
                    linearLayout1.setVisibility(View.VISIBLE);
                    linearLayout2.setVisibility(View.VISIBLE);
                    linearLayout3.setVisibility(View.VISIBLE);
                    linearLayout4.setVisibility(View.VISIBLE);
                    linearLayout5.setVisibility(View.VISIBLE);
                    linearLayout6.setVisibility(View.GONE);

                } if(i==5){
                    linearLayout1.setVisibility(View.VISIBLE);
                    linearLayout2.setVisibility(View.VISIBLE);
                    linearLayout3.setVisibility(View.VISIBLE);
                    linearLayout4.setVisibility(View.VISIBLE);
                    linearLayout5.setVisibility(View.VISIBLE);
                    linearLayout6.setVisibility(View.VISIBLE);

                }

                quantity=i+1;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker.setVisibility(View.VISIBLE);
            }
        });


        Calendar calendar=Calendar.getInstance();
        year=calendar.get(Calendar.YEAR);
        month=calendar.get(Calendar.MONTH);
        day=calendar.get(Calendar.DAY_OF_MONTH);


       datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
           @Override
           public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
               BaseAvtivity.this.year=i;
               BaseAvtivity.this.month=i1;
               BaseAvtivity.this.day=i2;
               editTime.setText(i+"-"+(i1+1)+"-"+i2);
               datePicker.setVisibility(View.GONE);
               editTime.setVisibility(View.VISIBLE);
           }
       });





        builder.setTitle("添加标准器")
                .setView(dialogView);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                editName=dialogView.findViewById(R.id.edit_s_name);
                editPort=dialogView.findViewById(R.id.edit_s_port);
                editFormat=dialogView.findViewById(R.id.edit_s_format);
                editRate=dialogView.findViewById(R.id.edit_s_rate);
                editType=dialogView.findViewById(R.id.edit_s_type);
                editModel=dialogView.findViewById(R.id.edit_s_model);
                editAgreement=dialogView.findViewById(R.id.edit_s_agreement);
                editNumber=dialogView.findViewById(R.id.edit_s_number);
                editTraceabilityUnit=dialogView.findViewById(R.id.edit_s_traceabilityUnit);


                edit_jzd1=dialogView.findViewById(R.id.edit1);
                edit_xzz1=dialogView.findViewById(R.id.edit2);
                edit_jzd2=dialogView.findViewById(R.id.edit3);
                edit_xzz2=dialogView.findViewById(R.id.edit4);
                edit_jzd3=dialogView.findViewById(R.id.edit5);
                edit_xzz3=dialogView.findViewById(R.id.edit6);
                edit_jzd4=dialogView.findViewById(R.id.edit7);
                edit_xzz4=dialogView.findViewById(R.id.edit8);
                edit_jzd5=dialogView.findViewById(R.id.edit9);
                edit_xzz5=dialogView.findViewById(R.id.edit10);
                edit_jzd6=dialogView.findViewById(R.id.edit11);
                edit_xzz6=dialogView.findViewById(R.id.edit12);


                String name=editName.getText().toString();


                if(name.equals("")){
                    Log.d("这里的name不是空格吗", "onClick: ");
                    showToast("标准器名称不能为空");
                    return;
                }



                if(editPort.getText().toString().equals("")){
                    showToast("通讯串口不能为空");
                    return;
                }

                int port=Integer.valueOf(editPort.getText().toString());

                if(editFormat.getText().toString().equals("")){
                    showToast("通讯格式不能为空");
                    return;
                }


                if(editRate.getText().toString().equals("")){
                    showToast("通讯速率不能为空");
                    return;
                }
                int rate=Integer.valueOf(editRate.getText().toString());






                String format=editFormat.getText().toString();
                String type=editType.getText().toString();
                String model=editModel.getText().toString();
                String agreement=editAgreement.getText().toString();
                String number=editNumber.getText().toString();
                String traceabilityUnit=editTraceabilityUnit.getText().toString();
                String time=editTime.getText().toString();

                System.out.println("时间------"+time);


                int result=standardApparatusDBHelper.findByName(tableName,name);
                List<Integer> list1=new ArrayList<>();
                List<Float> list2=new ArrayList<>();

                if(result<=0){
                    StandardApparatus standardApparatus=new StandardApparatus();
                    standardApparatus.setName(name);
                    standardApparatus.setPort(port);
                    standardApparatus.setFormat(format);
                    standardApparatus.setRate(rate);
                    standardApparatus.setType(type);
                    standardApparatus.setModel(model);
                    standardApparatus.setAgreement(agreement);
                    standardApparatus.setNumber(number);
                    standardApparatus.setQuantity(quantity);
                    if(quantity==1){
                        list1.add(Integer.valueOf(edit_jzd1.getText().toString()));
                        list2.add(Float.valueOf(edit_xzz1.getText().toString()));
                    } if(quantity==2){
                        list1.add(Integer.valueOf(edit_jzd1.getText().toString()));
                        list2.add(Float.valueOf(edit_xzz1.getText().toString()));
                        list1.add(Integer.valueOf(edit_jzd2.getText().toString()));
                        list2.add(Float.valueOf(edit_xzz2.getText().toString()));
                    } if(quantity==3){
                        list1.add(Integer.valueOf(edit_jzd1.getText().toString()));
                        list2.add(Float.valueOf(edit_xzz1.getText().toString()));
                        list1.add(Integer.valueOf(edit_jzd2.getText().toString()));
                        list2.add(Float.valueOf(edit_xzz2.getText().toString()));
                        list1.add(Integer.valueOf(edit_jzd3.getText().toString()));
                        list2.add(Float.valueOf(edit_xzz3.getText().toString()));
                    } if(quantity==4){
                        list1.add(Integer.valueOf(edit_jzd1.getText().toString()));
                        list2.add(Float.valueOf(edit_xzz1.getText().toString()));
                        list1.add(Integer.valueOf(edit_jzd2.getText().toString()));
                        list2.add(Float.valueOf(edit_xzz2.getText().toString()));
                        list1.add(Integer.valueOf(edit_jzd3.getText().toString()));
                        list2.add(Float.valueOf(edit_xzz3.getText().toString()));
                        list1.add(Integer.valueOf(edit_jzd4.getText().toString()));
                        list2.add(Float.valueOf(edit_xzz4.getText().toString()));
                    } if(quantity==5){
                        list1.add(Integer.valueOf(edit_jzd1.getText().toString()));
                        list2.add(Float.valueOf(edit_xzz1.getText().toString()));
                        list1.add(Integer.valueOf(edit_jzd2.getText().toString()));
                        list2.add(Float.valueOf(edit_xzz2.getText().toString()));
                        list1.add(Integer.valueOf(edit_jzd3.getText().toString()));
                        list2.add(Float.valueOf(edit_xzz3.getText().toString()));
                        list1.add(Integer.valueOf(edit_jzd4.getText().toString()));
                        list2.add(Float.valueOf(edit_xzz4.getText().toString()));
                        list1.add(Integer.valueOf(edit_jzd5.getText().toString()));
                        list2.add(Float.valueOf(edit_xzz5.getText().toString()));
                    } if(quantity==6){
                        list1.add(Integer.valueOf(edit_jzd1.getText().toString()));
                        list2.add(Float.valueOf(edit_xzz1.getText().toString()));
                        list1.add(Integer.valueOf(edit_jzd2.getText().toString()));
                        list2.add(Float.valueOf(edit_xzz2.getText().toString()));
                        list1.add(Integer.valueOf(edit_jzd3.getText().toString()));
                        list2.add(Float.valueOf(edit_xzz3.getText().toString()));
                        list1.add(Integer.valueOf(edit_jzd4.getText().toString()));
                        list2.add(Float.valueOf(edit_xzz4.getText().toString()));
                        list1.add(Integer.valueOf(edit_jzd5.getText().toString()));
                        list2.add(Float.valueOf(edit_xzz5.getText().toString()));
                        list1.add(Integer.valueOf(edit_jzd6.getText().toString()));
                        list2.add(Float.valueOf(edit_xzz6.getText().toString()));
                    }


                    standardApparatus.setList1(list1);
                    standardApparatus.setList2(list2);
                    standardApparatus.setTraceabilityUnit(traceabilityUnit);
                    standardApparatus.setTime(time);

                    if(standardApparatusDBHelper.add(standardApparatus,tableName)){
                        Log.d("添加成功", "onClick: ");
                        showToast("添加成功");
                        updateListView(standardApparatusDBHelper,tableName,standardApparatusAdapter,listView);
                    }else{
                        Log.d("添加失败", "onClick: ");
                        showToast("添加失败");
                    }
                }else{
                    showToast("该方案已经存在");
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


    public void updateCheck(Context context,StandardApparatusAdapter standardApparatusAdapter ,StandardApparatusDBHelper standardApparatusDBHelper,String tableName,int position,ListView listView){


        StandardApparatus standardApparatus=(StandardApparatus) standardApparatusAdapter.getItem(position);

        AlertDialog.Builder builder=new AlertDialog.Builder(context);

        final Map<String,String> map=new LinkedHashMap<>(standardApparatus.getQuantity());

        for(int i=0;i<standardApparatus.getQuantity();i++){
            map.put("校准点："+standardApparatus.getList1().get(i)+"  修正值："+standardApparatus.getList2().get(i),String.valueOf(i+1));
        }

        final String[] keysTemp=new String[standardApparatus.getQuantity()];
        final String[] keys=map.keySet().toArray(keysTemp);


        builder.setTitle("选择该标准器？")
                .setItems(keys,null)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(standardApparatusDBHelper.updateCheck(tableName,standardApparatus.getID(),globalVariable.getHumStandardID()));
                        globalVariable.setHumStandardID(standardApparatus.getID());
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

}
