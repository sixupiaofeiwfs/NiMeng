package com.nimeng.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nimeng.bean.Password;
import com.nimeng.bean.SystemData;
import com.nimeng.util.CommonUtil;
import com.nimeng.util.SystemDBHelper;

public class PasswordActivity  extends CommonUtil {



    EditText editText;
    String edit1;
    Button btn1;

    SystemDBHelper systemDBHelper;
    SystemData systemData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_password);

        editText=findViewById(R.id.password_edit1);
        btn1= findViewById(R.id.password_btn1);

        int excutingNumber=checkTime();
        //systemDBHelper=new SystemDBHelper(PasswordActivity.this,"NIMENG.db",null,1);
        systemDBHelper=new SystemDBHelper(PasswordActivity.this);
        systemData=systemDBHelper.getSystemData();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPassword(excutingNumber);

            }
        });



    }






    public void checkPassword(int excutingNumber){

        //获取当前正在执行的密码
        String executingPassword=systemDBHelper.getPassword().get(excutingNumber-1).getPassword();

        String superPassword=systemData.getSuperPassword();

        edit1=editText.getText().toString();

        System.out.println("判断-----------"+excutingNumber+"---------------->"+executingPassword+"-------"+superPassword+"_________"+edit1);


        int errorNumber=systemDBHelper.getPassword().get(excutingNumber-1).getErrorNumbers();

        if(errorNumber>=3){

            Toast.makeText(PasswordActivity.this,"警告！密码已达错误上限，请联系官方解决",Toast.LENGTH_SHORT).show();
           editText.setEnabled(false);
           btn1.setEnabled(false);
           return;
        }


                if(!executingPassword.equals(edit1) && !superPassword.equals(edit1)){

                    errorNumber=errorNumber+1;

                    Password password=new Password();
                    password.setId(excutingNumber-1);
                    password.setPassword(executingPassword);
                    password.setErrorNumbers(errorNumber);
                    password.setTimes(systemDBHelper.getPassword().get(excutingNumber-1).getTimes());
                    password.setMatchs(false);

                    systemDBHelper.updatePassword(password);

                    Toast.makeText(PasswordActivity.this,"密码错误，请重新输入",Toast.LENGTH_SHORT).show();

                }else{

                    Password password=new Password();
                    password.setId(excutingNumber-1);
                    password.setPassword(executingPassword);
                    password.setErrorNumbers(errorNumber);
                    password.setTimes(systemDBHelper.getPassword().get(excutingNumber-1).getTimes());
                    password.setMatchs(true);

                    systemDBHelper.updatePassword(password);

                    Intent intent=new Intent(PasswordActivity.this,MainActivity.class);
                    startActivity(intent);
                }










    }

}