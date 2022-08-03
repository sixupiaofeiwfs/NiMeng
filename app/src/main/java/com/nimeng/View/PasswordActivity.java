package com.nimeng.View;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.nimeng.bean.GlobalVariable;

import java.util.ArrayList;
import java.util.List;

public class PasswordActivity  extends BaseAvtivity {


    GlobalVariable globalVariable;
    EditText editText;
    String edit1;
    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        globalVariable=(GlobalVariable) getApplicationContext();
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_password);

        editText=findViewById(R.id.password_edit1);
        btn1= findViewById(R.id.password_btn1);

        int excutingNumber=1;

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPassword(excutingNumber);

            }
        });



    }






    public void checkPassword(int excutingNumber){

        //获取当前正在执行的密码
        String executingPassword=globalVariable.getPasswords().get(excutingNumber-1);
        String superPassword=globalVariable.getSuperPassword();

        edit1=editText.getText().toString();

        System.out.println("判断-----------"+executingPassword+"-------"+superPassword+"_________"+edit1);


        int errorNumber=globalVariable.getErrorNumbers().get(excutingNumber-1);

        if(errorNumber>=3){
            System.out.println(0);
            Toast.makeText(PasswordActivity.this,"警告！密码已达错误上限，请联系官方解决",Toast.LENGTH_SHORT).show();
           editText.setEnabled(false);
           btn1.setEnabled(false);
           return;
        }


                if(!executingPassword.equals(edit1) && !superPassword.equals(edit1)){
                    System.out.println(1);
                    errorNumber=errorNumber+1;
                    List<Integer> errorNumbers=globalVariable.getErrorNumbers();
                    errorNumbers.set(excutingNumber-1,errorNumber);
                    globalVariable.setErrorNumbers(errorNumbers);
                    Toast.makeText(PasswordActivity.this,"密码错误，请重新输入",Toast.LENGTH_SHORT).show();

                }else{

                    List<Boolean> matchs=new ArrayList<Boolean>();
                    matchs=globalVariable.getMatchs();
                    matchs.set(excutingNumber-1,true);
                    globalVariable.setMatchs(matchs);
                    Intent intent=new Intent(PasswordActivity.this,MainActivity.class);
                    startActivity(intent);
                }










    }

}