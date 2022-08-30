package com.nimeng.View;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.nimeng.bean.TemPlanBean;
import com.nimeng.util.CommonUtil;
import com.nimeng.util.SystemDBHelper;
import com.nimeng.util.TemPlanDBHelper;

public class TemPlanEditActivity extends CommonUtil {

    private EditText editName,editUnitTime,editTemWave,editTem1,editTem2,editTem3,editTem4,editTem5,editTem6,editTem7,editTem8,editTem9,editTem10;

    private Spinner spinner;
    private LinearLayout linearLayout4,linearLayout5,linearLayout6,linearLayout7,linearLayout8,linearLayout9,linearLayout10;

    private Button button1,button2;
    private TemPlanDBHelper templanDBHelper;

    private int tem1,tem2,tem3,tem4,tem5,tem6,tem7,tem8,tem9,tem10;
    private int temPoints;

    private TextView textView1,textView2,textView3,textView4,textView5,textView6,textView7,textView8,textView9,textView10;

    private SystemDBHelper systemDBHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.templan_edit);
        button1=findViewById(R.id.btn_true);
        button2=findViewById(R.id.btn_false);
        templanDBHelper=new TemPlanDBHelper(TemPlanEditActivity.this,"NIMENG.db",null,1);
        systemDBHelper=new SystemDBHelper(TemPlanEditActivity.this,"NIMENG.db",null,1);
        editName=findViewById(R.id.edit_templan_name);
        editUnitTime=findViewById(R.id.edit_templan_unitTime);
        editTemWave=findViewById(R.id.edit_templan_temWave);
        editTem1=findViewById(R.id.edit_templan_tem1);
        editTem2=findViewById(R.id.edit_templan_tem2);
        editTem3=findViewById(R.id.edit_templan_tem3);
        editTem4=findViewById(R.id.edit_templan_tem4);
        editTem5=findViewById(R.id.edit_templan_tem5);
        editTem6=findViewById(R.id.edit_templan_tem6);
        editTem7=findViewById(R.id.edit_templan_tem7);
        editTem8=findViewById(R.id.edit_templan_tem8);
        editTem9=findViewById(R.id.edit_templan_tem9);
        editTem10=findViewById(R.id.edit_templan_tem10);
        spinner=findViewById(R.id.spinner1);

        linearLayout4=findViewById(R.id.LinearLayout4);
        linearLayout5=findViewById(R.id.LinearLayout5);
        linearLayout6=findViewById(R.id.LinearLayout6);
        linearLayout7=findViewById(R.id.LinearLayout7);
        linearLayout8=findViewById(R.id.LinearLayout8);
        linearLayout9=findViewById(R.id.LinearLayout9);
       // linearLayout10=findViewById(R.id.LinearLayout10);


        textView1=findViewById(R.id.text1);
        textView2=findViewById(R.id.text2);
        textView3=findViewById(R.id.text3);
        textView4=findViewById(R.id.text4);
        textView5=findViewById(R.id.text5);
        textView6=findViewById(R.id.text6);
        textView7=findViewById(R.id.text7);
        textView8=findViewById(R.id.text8);
        textView9=findViewById(R.id.text9);
        textView10=findViewById(R.id.text10);



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                System.out.println("i------>"+i);

                switch(i){
                    case 9:
                        linearLayout8.setVisibility(View.VISIBLE);
                        linearLayout7.setVisibility(View.VISIBLE);
                        linearLayout6.setVisibility(View.VISIBLE);
                        linearLayout5.setVisibility(View.VISIBLE);
                        linearLayout4.setVisibility(View.VISIBLE);
                        textView10.setVisibility(View.VISIBLE);
                        editTem10.setVisibility(View.VISIBLE);
                        textView8.setVisibility(View.VISIBLE);
                        editTem8.setVisibility(View.VISIBLE);
                        textView6.setVisibility(View.VISIBLE);
                        editTem6.setVisibility(View.VISIBLE);
                        textView4.setVisibility(View.VISIBLE);
                        editTem4.setVisibility(View.VISIBLE);
                        textView2.setVisibility(View.VISIBLE);
                        editTem2.setVisibility(View.VISIBLE);
                        break;
                    case 8:
                        linearLayout8.setVisibility(View.VISIBLE);
                        linearLayout7.setVisibility(View.VISIBLE);
                        linearLayout6.setVisibility(View.VISIBLE);
                        linearLayout5.setVisibility(View.VISIBLE);
                        linearLayout4.setVisibility(View.VISIBLE);
                        textView10.setVisibility(View.INVISIBLE);
                        editTem10.setVisibility(View.INVISIBLE);
                        textView8.setVisibility(View.VISIBLE);
                        editTem8.setVisibility(View.VISIBLE);
                        textView6.setVisibility(View.VISIBLE);
                        editTem6.setVisibility(View.VISIBLE);
                        textView4.setVisibility(View.VISIBLE);
                        editTem4.setVisibility(View.VISIBLE);
                        textView2.setVisibility(View.VISIBLE);
                        editTem2.setVisibility(View.VISIBLE);
                        break;
                    case 7:

                        linearLayout8.setVisibility(View.GONE);
                        linearLayout7.setVisibility(View.VISIBLE);
                        linearLayout6.setVisibility(View.VISIBLE);
                        linearLayout5.setVisibility(View.VISIBLE);
                        linearLayout4.setVisibility(View.VISIBLE);
                        textView8.setVisibility(View.VISIBLE);
                        editTem8.setVisibility(View.VISIBLE);
                        textView6.setVisibility(View.VISIBLE);
                        editTem6.setVisibility(View.VISIBLE);
                        textView4.setVisibility(View.VISIBLE);
                        editTem4.setVisibility(View.VISIBLE);
                        textView2.setVisibility(View.VISIBLE);
                        editTem2.setVisibility(View.VISIBLE);
                        break;
                    case 6:

                        linearLayout8.setVisibility(View.GONE);
                        linearLayout7.setVisibility(View.VISIBLE);
                        linearLayout6.setVisibility(View.VISIBLE);
                        linearLayout5.setVisibility(View.VISIBLE);
                        linearLayout4.setVisibility(View.VISIBLE);
                        textView8.setVisibility(View.INVISIBLE);
                        editTem8.setVisibility(View.INVISIBLE);
                        textView6.setVisibility(View.VISIBLE);
                        editTem6.setVisibility(View.VISIBLE);
                        textView4.setVisibility(View.VISIBLE);
                        editTem4.setVisibility(View.VISIBLE);
                        textView2.setVisibility(View.VISIBLE);
                        editTem2.setVisibility(View.VISIBLE);
                        break;
                    case 5:

                        linearLayout8.setVisibility(View.GONE);
                        linearLayout7.setVisibility(View.GONE);
                        linearLayout6.setVisibility(View.VISIBLE);
                        linearLayout5.setVisibility(View.VISIBLE);
                        linearLayout4.setVisibility(View.VISIBLE);
                        textView6.setVisibility(View.VISIBLE);
                        editTem6.setVisibility(View.VISIBLE);
                        textView4.setVisibility(View.VISIBLE);
                        editTem4.setVisibility(View.VISIBLE);
                        textView2.setVisibility(View.VISIBLE);
                        editTem2.setVisibility(View.VISIBLE);
                        break;
                    case 4:

                        linearLayout8.setVisibility(View.GONE);
                        linearLayout7.setVisibility(View.GONE);
                        linearLayout6.setVisibility(View.VISIBLE);
                        linearLayout5.setVisibility(View.VISIBLE);
                        linearLayout4.setVisibility(View.VISIBLE);
                        textView6.setVisibility(View.INVISIBLE);
                        editTem6.setVisibility(View.INVISIBLE);
                        textView4.setVisibility(View.VISIBLE);
                        editTem4.setVisibility(View.VISIBLE);
                        textView2.setVisibility(View.VISIBLE);
                        editTem2.setVisibility(View.VISIBLE);
                        break;
                    case 3:

                        linearLayout8.setVisibility(View.GONE);
                        linearLayout7.setVisibility(View.GONE);
                        linearLayout6.setVisibility(View.GONE);
                        linearLayout5.setVisibility(View.VISIBLE);
                        linearLayout4.setVisibility(View.VISIBLE);
                        textView4.setVisibility(View.VISIBLE);
                        editTem4.setVisibility(View.VISIBLE);
                        textView2.setVisibility(View.VISIBLE);
                        editTem2.setVisibility(View.VISIBLE);
                        break;
                    case 2:

                        linearLayout8.setVisibility(View.GONE);
                        linearLayout7.setVisibility(View.GONE);
                        linearLayout6.setVisibility(View.GONE);
                        linearLayout5.setVisibility(View.VISIBLE);
                        linearLayout4.setVisibility(View.VISIBLE);
                        textView4.setVisibility(View.INVISIBLE);
                        editTem4.setVisibility(View.INVISIBLE);
                        textView2.setVisibility(View.VISIBLE);
                        editTem2.setVisibility(View.VISIBLE);
                        break;
                    case 1:

                        linearLayout8.setVisibility(View.GONE);
                        linearLayout7.setVisibility(View.GONE);
                        linearLayout6.setVisibility(View.GONE);
                        linearLayout5.setVisibility(View.GONE);
                        linearLayout4.setVisibility(View.VISIBLE);
                        textView2.setVisibility(View.VISIBLE);
                        editTem2.setVisibility(View.VISIBLE);
                        break;
                    case 0:

                        linearLayout8.setVisibility(View.GONE);
                        linearLayout7.setVisibility(View.GONE);
                        linearLayout6.setVisibility(View.GONE);
                        linearLayout5.setVisibility(View.GONE);
                        linearLayout4.setVisibility(View.VISIBLE);
                        textView2.setVisibility(View.INVISIBLE);
                        editTem2.setVisibility(View.INVISIBLE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name=editName.getText().toString();
                if(name==""){
                    showToast(TemPlanEditActivity.this,"预设方案名称不能为空");
                    return;
                }



                if(editUnitTime.getText().toString()==" "){
                    showToast(TemPlanEditActivity.this,"单位时间不能为空");
                    return;
                }

                int unitTime=Integer.valueOf(editUnitTime.getText().toString());


                if(editTemWave.getText().toString()==""){
                    showToast(TemPlanEditActivity.this,"温度波动值不能为空");
                    return;
                }
                float temWave=Float.parseFloat( editTemWave.getText().toString());


                long points=spinner.getSelectedItemId();


                String STem1=editTem1.getText().toString();
                String STem2=editTem2.getText().toString();
                String STem3=editTem3.getText().toString();
                String STem4=editTem4.getText().toString();
                String STem5=editTem5.getText().toString();
                String STem6=editTem6.getText().toString();
                String STem7=editTem7.getText().toString();
                String STem8=editTem8.getText().toString();
                String STem9=editTem9.getText().toString();
                String STem10=editTem10.getText().toString();


                System.out.println("要添加的信息："+STem6+"--->"+STem7+"--->"+STem8+"--->"+STem9+"--->"+STem10);


                temPoints=Integer.parseInt(String.valueOf(points));
                switch(temPoints){
                    case 0:
                        if(STem1.equals("")){
                            showToast(TemPlanEditActivity.this,"温度一不能为空");
                            return;
                        }else{
                            tem1=Integer.valueOf(STem1);
                        }
                        break;
                    case 1:
                        if(STem1.equals("") || STem2.equals("")){
                            showToast(TemPlanEditActivity.this,"温度一、温度二不能为空");
                            return;
                        }else{
                            tem1=Integer.valueOf(STem1);
                            tem2=Integer.valueOf(STem2);
                        }
                        break;
                    case 2:
                        if(STem1.equals("") || STem2.equals("") || STem3.equals("")){
                            showToast(TemPlanEditActivity.this,"温度一、温度二、温度三不能为空");
                            return;
                        }else{
                            tem1=Integer.valueOf(STem1);
                            tem2=Integer.valueOf(STem2);
                            tem3=Integer.valueOf(STem3);
                        }
                        break;

                    case 3:
                        if(STem1.equals("") || STem2.equals("") || STem3.equals("") || STem4.equals("") ){
                            showToast(TemPlanEditActivity.this,"温度一、温度二、温度三、温度四不能为空");
                            return;
                        }else{
                            tem1=Integer.valueOf(STem1);
                            tem2=Integer.valueOf(STem2);
                            tem3=Integer.valueOf(STem3);
                            tem4=Integer.valueOf(STem4);
                        }
                        break;

                    case 4:
                        if(STem1.equals("") || STem2.equals("") || STem3.equals("") || STem4.equals("") || STem5.equals("")){
                            showToast(TemPlanEditActivity.this,"温度一、温度二、温度三、温度四、温度五不能为空");
                            return;
                        }else{
                            tem1=Integer.valueOf(STem1);
                            tem2=Integer.valueOf(STem2);
                            tem3=Integer.valueOf(STem3);
                            tem4=Integer.valueOf(STem4);
                            tem5=Integer.valueOf(STem5);
                        }
                        break;

                    case 5:
                        if(STem1.equals("") || STem2.equals("") || STem3.equals("") || STem4.equals("") || STem5.equals("") || STem6.equals("")){
                            showToast(TemPlanEditActivity.this,"温度一、温度二、温度三、温度四、温度五、温度六不能为空");
                            return;
                        }else{
                            tem1=Integer.valueOf(STem1);
                            tem2=Integer.valueOf(STem2);
                            tem3=Integer.valueOf(STem3);
                            tem4=Integer.valueOf(STem4);
                            tem5=Integer.valueOf(STem5);
                            tem6=Integer.valueOf(STem6);
                        }
                        break;
                    case 6:
                        if(STem1.equals("") || STem2.equals("") || STem3.equals("") || STem4.equals("") || STem5.equals("") || STem6.equals("") || STem7.equals("")){
                            showToast(TemPlanEditActivity.this,"温度一、温度二、温度三、温度四、温度五、温度六、温度七不能为空");
                            return;
                        }else{
                            tem1=Integer.valueOf(STem1);
                            tem2=Integer.valueOf(STem2);
                            tem3=Integer.valueOf(STem3);
                            tem4=Integer.valueOf(STem4);
                            tem5=Integer.valueOf(STem5);
                            tem6=Integer.valueOf(STem6);
                            tem7=Integer.valueOf(STem7);
                        }
                        break;
                    case 7:
                        if(STem1.equals("") || STem2.equals("") || STem3.equals("") || STem4.equals("") || STem5.equals("") || STem6.equals("") || STem7.equals("")|| STem8.equals("")){
                            showToast(TemPlanEditActivity.this,"温度一、温度二、温度三、温度四、温度五、温度六不能为空");
                            return;
                        }else{
                            tem1=Integer.valueOf(STem1);
                            tem2=Integer.valueOf(STem2);
                            tem3=Integer.valueOf(STem3);
                            tem4=Integer.valueOf(STem4);
                            tem5=Integer.valueOf(STem5);
                            tem6=Integer.valueOf(STem6);
                            tem7=Integer.valueOf(STem7);
                            tem8=Integer.valueOf(STem8);
                        }
                        break;
                    case 8:
                        if(STem1.equals("") || STem2.equals("") || STem3.equals("") || STem4.equals("") || STem5.equals("") || STem6.equals("") || STem7.equals("") || STem8.equals("") || STem9.equals("")){
                            showToast(TemPlanEditActivity.this,"温度一、温度二、温度三、温度四、温度五、温度六、温度七、温度八、温度九不能为空");
                            return;
                        }else{
                            tem1=Integer.valueOf(STem1);
                            tem2=Integer.valueOf(STem2);
                            tem3=Integer.valueOf(STem3);
                            tem4=Integer.valueOf(STem4);
                            tem5=Integer.valueOf(STem5);
                            tem6=Integer.valueOf(STem6);
                            tem7=Integer.valueOf(STem7);
                            tem8=Integer.valueOf(STem8);
                            tem9=Integer.valueOf(STem9);
                        }
                        break;
                    case 9:
                        if(STem1.equals("") || STem2.equals("") || STem3.equals("") || STem4.equals("") || STem5.equals("") || STem6.equals("")|| STem7.equals("") || STem8.equals("") || STem9.equals("") || STem10.equals("")){
                            showToast(TemPlanEditActivity.this,"温度一、温度二、温度三、温度四、温度五、温度六、温度七、温度八、温度九、温度十不能为空");
                            return;
                        }else{
                            tem1=Integer.valueOf(STem1);
                            tem2=Integer.valueOf(STem2);
                            tem3=Integer.valueOf(STem3);
                            tem4=Integer.valueOf(STem4);
                            tem5=Integer.valueOf(STem5);
                            tem6=Integer.valueOf(STem6);
                            tem7=Integer.valueOf(STem7);
                            tem8=Integer.valueOf(STem8);
                            tem9=Integer.valueOf(STem9);
                            tem10=Integer.valueOf(STem10);
                        }
                        break;

                }


                if(tem1>100||tem1<-20||tem2>100||tem2<-20||tem3>100||tem3<-20||tem4>100||tem4<-20||tem5>100||tem5<-20||tem6>100||tem6<-20||tem7>100||tem7<-20||tem8>100||tem8<-20||tem9>100||tem9<-20||tem10>100||tem10<-20){
                    showToast(TemPlanEditActivity.this,"温度设定值不能大于100或小于-20");
                    return;
                }
                if(unitTime>30){
                    showToast(TemPlanEditActivity.this,"稳定条件的时间不能超过30分钟");
                    return;
                }



                int result= templanDBHelper.findTemPlanByName(name);
                if(result<=0){
                    TemPlanBean templanBean2=new TemPlanBean();
                    templanBean2.setName(name);
                    templanBean2.setUnitTime(unitTime);
                    templanBean2.setTemWave(temWave);
                    templanBean2.setTemPoints(temPoints+1);
                    templanBean2.setTem1(tem1);
                    templanBean2.setTem2(tem2);
                    templanBean2.setTem3(tem3);
                    templanBean2.setTem4(tem4);
                    templanBean2.setTem5(tem5);
                    templanBean2.setTem6(tem6);
                    templanBean2.setTem7(tem7);
                    templanBean2.setTem8(tem8);
                    templanBean2.setTem9(tem9);
                    templanBean2.setTem10(tem10);
                    templanBean2.setIsCheck(0);

                    System.out.println("马上添加----->"+templanBean2);

                    if(templanDBHelper.add(templanBean2)){
                        Log.d("添加成功", "onClick: ");
                        showToast(TemPlanEditActivity.this,"添加成功");




                        startActivity(new Intent(TemPlanEditActivity.this,TemPlanActivity.class));


                    }else{
                        Log.d("添加失败", "onClick: ");
                        showToast(TemPlanEditActivity.this,"添加失败");
                    }
                }else{
                    showToast(TemPlanEditActivity.this,"该方案已经存在");
                }





            }
        });


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TemPlanEditActivity.this,TemPlanActivity.class));
            }
        });




    }



}
