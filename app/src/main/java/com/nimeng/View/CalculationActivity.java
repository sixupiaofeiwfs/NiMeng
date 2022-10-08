package com.nimeng.View;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.nimeng.util.CommonUtil;

public class CalculationActivity extends CommonUtil {


    private double c0=0.99999683;
    private double c1=-0.90826951E-02;
    private double c2=0.78736169E-04;
    private double c3=-0.61117958E-06;
    private double c4=0.43884187E-08;
    private double c5=-0.29883885E-10;
    private double c6=0.21874425E-12;
    private double c7=-0.17892321E-14;
    private double c8=0.11112018E-16;
    private double c9=-0.30994571E-19;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_jisuan);
        EditText editText1,editText2;
        editText1=findViewById(R.id.sj_tem);
        editText2=findViewById(R.id.ld_tem);
        TextView textView=findViewById(R.id.xd_hum);
        Button button =findViewById(R.id.queding);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                float f1=0f,f2=0f;

                if(editText1.getText()!=null && editText1.getText().toString()!=""){
                    String text1=editText1.getText().toString();
                    f1=Float.valueOf(text1);
                    if(f1<-20 || f1>100){
                        showToast(CalculationActivity.this,"实际温度必须在-20至100度之间");
                        return;
                    }
                }

                if(editText2.getText()!=null && editText2.getText().toString()!=""){
                    String text2=editText2.getText().toString();
                    f2=Float.valueOf(text2);
                    if(f2<-20 || f2>100){
                        showToast(CalculationActivity.this,"露点温度必须在-20至100度之间");
                        return;
                    }else if(f2>f1){
                        showToast(CalculationActivity.this,"露点温度不能大于实际温度");
                        return;
                    }
                }


                double r1=c0+f1*(c1+f1*(c2+f1*(c3+f1*(c4+f1*(c5+f1*(c6+f1*(c7+f1*(c8+f1*(c9)))))))));
                double p1=6.1078/Math.pow(r1,8);

                double r2=c0+f2*(c1+f2*(c2+f2*(c3+f2*(c4+f2*(c5+f2*(c6+f2*(c7+f2*(c8+f2*(c9)))))))));
                double p2=6.1078/Math.pow(r2,8);


                double xd_sd=(double)Math.round(p2/p1*10000)/100;

                textView.setText(String.valueOf(xd_sd)+"%");

            }
        });


        super.onCreate(savedInstanceState);


    }
}
