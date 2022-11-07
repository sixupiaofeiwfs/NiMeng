package com.nimeng.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.nimeng.bean.StandardApparatus;
import com.nimeng.util.CommonUtil;
import com.nimeng.util.StandardApparatusDBHelper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class StandardappratusEditActivity extends CommonUtil {

    //控制证书值个数的显示
    LinearLayout linearLayout1,linearLayout2,linearLayout3,linearLayout4,linearLayout5,linearLayout6;
    Spinner  spinner, spinner0, spinner1,spinner2,spinner3,spinner4;

    //证书有效期的时间日期选择器
    DatePicker datePicker;

    //选中的证书值数量
    int quantity;


    //点击有效期
    TextView btn1,editTime;
    private int year,month,day;

    StandardApparatusDBHelper standardApparatusDBHelper;
    String tableName;

    private EditText editTemAddress, editHumAddress,editCount, editSlave,editName,editType,editModel,editNumber,editTraceabilityUnit;
    private EditText edit_jzd1,edit_xzz1,edit_jzd2,edit_xzz2,edit_jzd3,edit_xzz3,edit_jzd4,edit_xzz4,edit_jzd5,edit_xzz5,edit_jzd6,edit_xzz6;
    private EditText edit_jzd1_1,edit_xzz1_1,edit_jzd2_1,edit_xzz2_1,edit_jzd3_1,edit_xzz3_1,edit_jzd4_1,edit_xzz4_1,edit_jzd5_1,edit_xzz5_1,edit_jzd6_1,edit_xzz6_1;


   Button button1,button2;
    String slave, name,type,number,model,traceabilityUnit,time,temAddress,humAddress,count;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.standardapparatus_edit);
        tableName=getIntent().getStringExtra("tableName");


        standardApparatusDBHelper=StandardApparatusDBHelper.getInstance(StandardappratusEditActivity.this);
        linearLayout1=findViewById(R.id.standard_LinearLayout7);
        linearLayout2=findViewById(R.id.standard_LinearLayout8);
        linearLayout3=findViewById(R.id.standard_LinearLayout9);
        linearLayout4=findViewById(R.id.standard_LinearLayout10);
        linearLayout5=findViewById(R.id.standard_LinearLayout11);
        linearLayout6=findViewById(R.id.standard_LinearLayout12);


        spinner=findViewById(R.id.standard_spinner1);
        spinner0=findViewById(R.id.spinner0);
        spinner1=findViewById(R.id.spinner1);
        spinner2=findViewById(R.id.spinner2);
        spinner3=findViewById(R.id.spinner3);
        spinner4=findViewById(R.id.spinner4);


        datePicker=findViewById(R.id.datepick1);


        btn1=findViewById(R.id.btn_1);
        editTime=findViewById(R.id.edit_s_time);


        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);


        //控制显示
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
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
                StandardappratusEditActivity.this.year=i;
                StandardappratusEditActivity.this.month=i1;
                StandardappratusEditActivity.this.day=i2;
                editTime.setText(i+"-"+(i1+1)+"-"+i2);
                datePicker.setVisibility(View.GONE);
                editTime.setVisibility(View.VISIBLE);
            }
        });



        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editSlave=findViewById(R.id.edit_s_slave);
                editName=findViewById(R.id.edit_s_name);
                editType=findViewById(R.id.edit_s_type);
                editModel=findViewById(R.id.edit_s_model);
                editNumber=findViewById(R.id.edit_s_number);
                editTraceabilityUnit=findViewById(R.id.edit_s_traceabilityUnit);
                editTemAddress=findViewById(R.id.temAddress);
                editHumAddress=findViewById(R.id.humAddress);
                editCount=findViewById(R.id.count);


                edit_jzd1=findViewById(R.id.edit1);
                edit_xzz1=findViewById(R.id.edit2);
                edit_jzd2=findViewById(R.id.edit3);
                edit_xzz2=findViewById(R.id.edit4);
                edit_jzd3=findViewById(R.id.edit5);
                edit_xzz3=findViewById(R.id.edit6);
                edit_jzd4=findViewById(R.id.edit7);
                edit_xzz4=findViewById(R.id.edit8);
                edit_jzd5=findViewById(R.id.edit9);
                edit_xzz5=findViewById(R.id.edit10);
                edit_jzd6=findViewById(R.id.edit11);
                edit_xzz6=findViewById(R.id.edit12);


                edit_jzd1_1=findViewById(R.id.edit1_1);
                edit_xzz1_1=findViewById(R.id.edit2_1);
                edit_jzd2_1=findViewById(R.id.edit3_1);
                edit_xzz2_1=findViewById(R.id.edit4_1);
                edit_jzd3_1=findViewById(R.id.edit5_1);
                edit_xzz3_1=findViewById(R.id.edit6_1);
                edit_jzd4_1=findViewById(R.id.edit7_1);
                edit_xzz4_1=findViewById(R.id.edit8_1);
                edit_jzd5_1=findViewById(R.id.edit9_1);
                edit_xzz5_1=findViewById(R.id.edit10_1);
                edit_jzd6_1=findViewById(R.id.edit11_1);
                edit_xzz6_1=findViewById(R.id.edit12_1);


                slave=editSlave.getText().toString();
                name=editName.getText().toString();
                type=editType.getText().toString();
                model=editModel.getText().toString();
                number=editNumber.getText().toString();
                traceabilityUnit=editTraceabilityUnit.getText().toString();
                time=editTime.getText().toString();
                temAddress=editTemAddress.getText().toString();
                humAddress=editHumAddress.getText().toString();
                count=editCount.getText().toString();




                System.out.println(name+"  "+type+" "+model+" "+number+" "+traceabilityUnit+" "+time);


                if(name.equals("")){
                    showToast(StandardappratusEditActivity.this,"标准器名称不能为空");
                    return;
                }

                if(slave.equals("")){
                    showToast(StandardappratusEditActivity.this,"从机地址不能为空");
                    return;
                }
                if(slave.equals("1")){
                    showToast(StandardappratusEditActivity.this,"该从机已被使用,请输入其他值");
                    return;
                }
                if(name.equals("")){
                    showToast(StandardappratusEditActivity.this,"名称不能为空");
                    return;
                }
                if(type.equals("")){
                    showToast(StandardappratusEditActivity.this,"设备类型不能为空");
                    return;
                }
                if(model.equals("")){
                    showToast(StandardappratusEditActivity.this,"设备型号不能为空");
                    return;
                }
                if(number.equals("")){
                    showToast(StandardappratusEditActivity.this,"证书编号不能为空");
                    return;
                }
                if(traceabilityUnit.equals("")){
                    showToast(StandardappratusEditActivity.this,"溯源单位不能为空");
                    return;
                }
                if(time==""){
                    showToast(StandardappratusEditActivity.this,"有效期不能为空");
                    return;
                }
                if(temAddress.equals("")){
                    showToast(StandardappratusEditActivity.this,"温度读取地址不能为空");
                    return;
                }
                if(humAddress.equals("")){
                    showToast(StandardappratusEditActivity.this,"湿度读取地址不能为空");
                    return;
                }
                if(count.equals("")){
                    showToast(StandardappratusEditActivity.this,"读取字节数不能为空");
                }




                int result=standardApparatusDBHelper.findByName(tableName,name);
                List<Integer> list1=new ArrayList<>();
                List<Float> list2=new ArrayList<>();
                List<Integer>list3=new ArrayList<>();
                List<Float> list4=new ArrayList<>();
                if(result<=0){
                    StandardApparatus standardApparatus=new StandardApparatus();
                    standardApparatus.setName(name);
                    standardApparatus.setPort(spinner1.getSelectedItem().toString());
                    standardApparatus.setFormat(spinner2.getSelectedItem().toString());
                    standardApparatus.setRate(Integer.valueOf(spinner3.getSelectedItem().toString()));
                    standardApparatus.setType(type);
                    standardApparatus.setModel(model);
                    standardApparatus.setAgreement(spinner4.getSelectedItem().toString());
                    standardApparatus.setNumber(number);
                    standardApparatus.setQuantity(quantity);



                    if(quantity==1){
                        list1.add(Integer.valueOf(edit_jzd1.getText().toString()));
                        list2.add(Float.valueOf(edit_xzz1.getText().toString()));

                        list3.add(Integer.valueOf(edit_jzd1_1.getText().toString()));
                        list4.add(Float.valueOf(edit_xzz1_1.getText().toString()));
                    } if(quantity==2){
                        list1.add(Integer.valueOf(edit_jzd1.getText().toString()));
                        list2.add(Float.valueOf(edit_xzz1.getText().toString()));
                        list1.add(Integer.valueOf(edit_jzd2.getText().toString()));
                        list2.add(Float.valueOf(edit_xzz2.getText().toString()));

                        list3.add(Integer.valueOf(edit_jzd1_1.getText().toString()));
                        list4.add(Float.valueOf(edit_xzz1_1.getText().toString()));
                        list3.add(Integer.valueOf(edit_jzd2_1.getText().toString()));
                        list4.add(Float.valueOf(edit_xzz2_1.getText().toString()));
                    } if(quantity==3){
                        list1.add(Integer.valueOf(edit_jzd1.getText().toString()));
                        list2.add(Float.valueOf(edit_xzz1.getText().toString()));
                        list1.add(Integer.valueOf(edit_jzd2.getText().toString()));
                        list2.add(Float.valueOf(edit_xzz2.getText().toString()));
                        list1.add(Integer.valueOf(edit_jzd3.getText().toString()));
                        list2.add(Float.valueOf(edit_xzz3.getText().toString()));

                        list3.add(Integer.valueOf(edit_jzd1_1.getText().toString()));
                        list4.add(Float.valueOf(edit_xzz1_1.getText().toString()));
                        list3.add(Integer.valueOf(edit_jzd2_1.getText().toString()));
                        list4.add(Float.valueOf(edit_xzz2_1.getText().toString()));
                        list3.add(Integer.valueOf(edit_jzd3_1.getText().toString()));
                        list4.add(Float.valueOf(edit_xzz3_1.getText().toString()));
                    } if(quantity==4){
                        list1.add(Integer.valueOf(edit_jzd1.getText().toString()));
                        list2.add(Float.valueOf(edit_xzz1.getText().toString()));
                        list1.add(Integer.valueOf(edit_jzd2.getText().toString()));
                        list2.add(Float.valueOf(edit_xzz2.getText().toString()));
                        list1.add(Integer.valueOf(edit_jzd3.getText().toString()));
                        list2.add(Float.valueOf(edit_xzz3.getText().toString()));
                        list1.add(Integer.valueOf(edit_jzd4.getText().toString()));
                        list2.add(Float.valueOf(edit_xzz4.getText().toString()));

                        list3.add(Integer.valueOf(edit_jzd1_1.getText().toString()));
                        list4.add(Float.valueOf(edit_xzz1_1.getText().toString()));
                        list3.add(Integer.valueOf(edit_jzd2_1.getText().toString()));
                        list4.add(Float.valueOf(edit_xzz2_1.getText().toString()));
                        list3.add(Integer.valueOf(edit_jzd3_1.getText().toString()));
                        list4.add(Float.valueOf(edit_xzz3_1.getText().toString()));
                        list3.add(Integer.valueOf(edit_jzd4_1.getText().toString()));
                        list4.add(Float.valueOf(edit_xzz4_1.getText().toString()));
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

                        list3.add(Integer.valueOf(edit_jzd1_1.getText().toString()));
                        list4.add(Float.valueOf(edit_xzz1_1.getText().toString()));
                        list3.add(Integer.valueOf(edit_jzd2_1.getText().toString()));
                        list4.add(Float.valueOf(edit_xzz2_1.getText().toString()));
                        list3.add(Integer.valueOf(edit_jzd3_1.getText().toString()));
                        list4.add(Float.valueOf(edit_xzz3_1.getText().toString()));
                        list3.add(Integer.valueOf(edit_jzd4_1.getText().toString()));
                        list4.add(Float.valueOf(edit_xzz4_1.getText().toString()));
                        list3.add(Integer.valueOf(edit_jzd5_1.getText().toString()));
                        list4.add(Float.valueOf(edit_xzz5_1.getText().toString()));
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

                        list3.add(Integer.valueOf(edit_jzd1_1.getText().toString()));
                        list4.add(Float.valueOf(edit_xzz1_1.getText().toString()));
                        list3.add(Integer.valueOf(edit_jzd2_1.getText().toString()));
                        list4.add(Float.valueOf(edit_xzz2_1.getText().toString()));
                        list3.add(Integer.valueOf(edit_jzd3_1.getText().toString()));
                        list4.add(Float.valueOf(edit_xzz3_1.getText().toString()));
                        list3.add(Integer.valueOf(edit_jzd4_1.getText().toString()));
                        list4.add(Float.valueOf(edit_xzz4_1.getText().toString()));
                        list3.add(Integer.valueOf(edit_jzd5_1.getText().toString()));
                        list4.add(Float.valueOf(edit_xzz5_1.getText().toString()));
                        list3.add(Integer.valueOf(edit_jzd6_1.getText().toString()));
                        list4.add(Float.valueOf(edit_xzz6_1.getText().toString()));
                    }

                    standardApparatus.setList1(list1);
                    standardApparatus.setList2(list2);
                    standardApparatus.setList3(list3);
                    standardApparatus.setList4(list4);
                    standardApparatus.setTraceabilityUnit(traceabilityUnit);
                    standardApparatus.setTime(time);
                    standardApparatus.setSlave(Integer.valueOf(slave));
                    standardApparatus.setState((int)spinner0.getSelectedItemId()+1);
                    standardApparatus.setTemStartAddress(temAddress);
                    standardApparatus.setHumStartAddress(humAddress);
                    standardApparatus.setCount(Integer.valueOf(count));






                    System.out.println("添加标准器---"+standardApparatus);
                    System.out.println(tableName);

                    if(standardApparatusDBHelper.add(standardApparatus,tableName)){
                        showToast(StandardappratusEditActivity.this,"添加成功");
                        startActivity(new Intent(StandardappratusEditActivity.this,TemStandardApparatusActivity.class));
                    }


                }else{
                    showToast(StandardappratusEditActivity.this,"该标准器已经存在");
                }


            }
        });


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StandardappratusEditActivity.this,TemStandardApparatusActivity.class));
            }
        });



    }
}
