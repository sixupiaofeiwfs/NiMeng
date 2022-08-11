package com.nimeng.View;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.nimeng.bean.GlobalVariable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class InstallermentActivity extends BaseAvtivity {
    int number=0;
    GlobalVariable globalVariable;
    int year,month,day,hour,minute;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        globalVariable=(GlobalVariable) getApplicationContext();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_installment);
        Spinner spinner = (Spinner) findViewById(R.id.spinner2);

        LinearLayout turntable_linearLayout5, turntable_linearLayout6, turntable_linearLayout7, turntable_linearLayout4, turntable_linearLayout3;
        turntable_linearLayout5 = findViewById(R.id.turntable_linearLayout5);
        turntable_linearLayout6 = findViewById(R.id.turntable_linearLayout6);
        turntable_linearLayout7 = findViewById(R.id.turntable_linearLayout7);
        turntable_linearLayout4 = findViewById(R.id.turntable_linearLayout8);
        turntable_linearLayout3 = findViewById(R.id.turntable_linearLayout9);


        Button btn1=findViewById(R.id.btn_true);
        EditText editText1=findViewById(R.id.miao4);//超级密码
        EditText editText2=findViewById(R.id.mima1);//分期密码一
        TextView textView1=findViewById(R.id.daoqishijian1);//到期时间一
        EditText editText4=findViewById(R.id.mima2);//分期密码二
        TextView textView2=findViewById(R.id.daoqishijian2);//到期时间二
        EditText editText6=findViewById(R.id.mima3);//分期密码三
        TextView textView3=findViewById(R.id.daoqishijian3);//到期时间三
        EditText editText8=findViewById(R.id.mima4);//分期密码四
        TextView textView4=findViewById(R.id.daoqishijian4);//到期时间四
        EditText editText10=findViewById(R.id.mima5);//分期密码五
        TextView textView5=findViewById(R.id.daoqishijian5);//到期时间五
        EditText editText12=findViewById(R.id.mima6);//分期密码六
        TextView textView6=findViewById(R.id.daoqishijian6);//到期时间六


        DatePicker datePicker1=findViewById(R.id.installment_datepicker1);
        DatePicker datePicker2=findViewById(R.id.installment_datepicker2);
        DatePicker datePicker3=findViewById(R.id.installment_datepicker3);
        DatePicker datePicker4=findViewById(R.id.installment_datepicker4);
        DatePicker datePicker5=findViewById(R.id.installment_datepicker5);
        DatePicker datePicker6=findViewById(R.id.installment_datepicker6);

        //获取当前日期和时间
        Calendar calendar=Calendar.getInstance();
        year=calendar.get(Calendar.YEAR);
        month=calendar.get(Calendar.MONTH);
        day=calendar.get(Calendar.DAY_OF_MONTH);
        hour=calendar.get(Calendar.HOUR);
        minute=calendar.get(Calendar.MINUTE);
        //为DatePicker添加监听时间
        init(datePicker1,textView1);
        init(datePicker2,textView2);
        init(datePicker3,textView3);
        init(datePicker4,textView4);
        init(datePicker5,textView5);
        init(datePicker6,textView6);


        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker1.setVisibility(View.VISIBLE);
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker2.setVisibility(View.VISIBLE);
            }
        });
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker3.setVisibility(View.VISIBLE);
            }
        });
        textView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker4.setVisibility(View.VISIBLE);
            }
        });
        textView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker5.setVisibility(View.VISIBLE);
            }
        });
        textView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker6.setVisibility(View.VISIBLE);
            }
        });



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String select = spinner.getSelectedItem().toString();
               number=i;
                Log.d("获取一下选中的值", "onItemSelected: " + (number+1));
                if (select.equals("1")) {
                    turntable_linearLayout5.setVisibility(View.INVISIBLE);//隐藏但是占据位置
                    turntable_linearLayout6.setVisibility(View.GONE);//隐藏不占据位置
                    turntable_linearLayout7.setVisibility(View.GONE);
                    turntable_linearLayout4.setVisibility(View.GONE);
                    turntable_linearLayout3.setVisibility(View.GONE);


                } else if (select.equals("2")) {
                    turntable_linearLayout5.setVisibility(View.VISIBLE);
                    turntable_linearLayout6.setVisibility(View.GONE);
                    turntable_linearLayout7.setVisibility(View.GONE);
                    turntable_linearLayout4.setVisibility(View.GONE);
                    turntable_linearLayout3.setVisibility(View.GONE);

                }else if (select.equals("3")){
                    turntable_linearLayout5.setVisibility(View.VISIBLE);
                    turntable_linearLayout6.setVisibility(View.VISIBLE);
                    turntable_linearLayout7.setVisibility(View.GONE);
                    turntable_linearLayout4.setVisibility(View.GONE);
                    turntable_linearLayout3.setVisibility(View.GONE);

                }else if(select.equals("4")){
                    turntable_linearLayout5.setVisibility(View.VISIBLE);
                    turntable_linearLayout6.setVisibility(View.VISIBLE);
                    turntable_linearLayout7.setVisibility(View.VISIBLE);
                    turntable_linearLayout4.setVisibility(View.GONE);
                    turntable_linearLayout3.setVisibility(View.GONE);
                }else if (select.equals("5")){
                    turntable_linearLayout5.setVisibility(View.VISIBLE);
                    turntable_linearLayout6.setVisibility(View.VISIBLE);
                    turntable_linearLayout7.setVisibility(View.VISIBLE);
                    turntable_linearLayout4.setVisibility(View.VISIBLE);
                    turntable_linearLayout3.setVisibility(View.GONE);
                }else if(select.equals("6")){
                    turntable_linearLayout5.setVisibility(View.VISIBLE);
                    turntable_linearLayout6.setVisibility(View.VISIBLE);
                    turntable_linearLayout7.setVisibility(View.VISIBLE);
                    turntable_linearLayout4.setVisibility(View.VISIBLE);
                    turntable_linearLayout3.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //设置是否需要分期
                globalVariable.setInstallmentPayment(true);
                //设置分期数
                globalVariable.setNumberOfStages(number+1);
                //设置超级密码
                globalVariable.setSuperPassword(editText1.getText().toString());
                //设置密码集合
                List<String> passwords=new ArrayList<String>();
                passwords.add(editText2.getText().toString());
                passwords.add(editText4.getText().toString());
                passwords.add(editText6.getText().toString());
                passwords.add(editText8.getText().toString());
                passwords.add(editText10.getText().toString());
                passwords.add(editText12.getText().toString());
                globalVariable.setPasswords(passwords);
                //设置时间组
                List<String> times=new ArrayList<String>();
                times.add(textView1.getText().toString());
                times.add(textView2.getText().toString());
                times.add(textView3.getText().toString());
                times.add(textView4.getText().toString());
                times.add(textView5.getText().toString());
                times.add(textView6.getText().toString());

                globalVariable.setTimes(times);


                //设置是否匹配集合
                List<Boolean> matchs=new ArrayList<Boolean>();
                matchs.add(false);
                matchs.add(false);
                matchs.add(false);
                matchs.add(false);
                matchs.add(false);
                matchs.add(false);
                globalVariable.setMatchs(matchs);



                //设置错误次数集合
                List<Integer> errorNumbers=new ArrayList<Integer>();
                errorNumbers.add(0);
                errorNumbers.add(0);
                errorNumbers.add(0);
                errorNumbers.add(0);
                errorNumbers.add(0);
                errorNumbers.add(0);
                globalVariable.setErrorNumbers(errorNumbers);


                success();


            }
        });

    }

    private void success(){
        Toast.makeText(this,"保存成功,即将返回系统设置页...",Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(InstallermentActivity.this,SettingSwitchActivity.class);
        startActivity(intent);
    }

    private void showDate(TextView textView,int y,int mo,int d, int h,int m){
        textView.setText(y+"-"+(mo+1)+"-"+d+" "+h+":"+m+":"+"00");
    }


    private void init(DatePicker datePicker,TextView textView){
        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                InstallermentActivity.this.year=i;
                InstallermentActivity.this.month=i1;
                InstallermentActivity.this.day=i2;
                showDate(textView,i,i1,i2,hour,minute);
                datePicker.setVisibility(View.GONE);
            }
        });
    }

}
