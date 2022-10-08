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

import com.nimeng.bean.HumPlanBean;
import com.nimeng.util.CommonUtil;
import com.nimeng.util.HumPlanDBHelper;
import com.nimeng.util.SystemDBHelper;

public class HumPlanEditActivity extends CommonUtil {

    private EditText editName,editUnitTime,editHumWave,editHum1,editHum2,editHum3,editHum4,editHum5,editHum6,editHum7,editHum8,editHum9,editHum10;

    private Spinner spinner;
    private LinearLayout linearLayout4,linearLayout5,linearLayout6,linearLayout7,linearLayout8,linearLayout9,linearLayout10;

    private Button button1,button2;
    private HumPlanDBHelper humplanDBHelper;

    private int hum1,hum2,hum3,hum4,hum5,hum6,hum7,hum8,hum9,hum10;
    private int humPoints;

    private TextView textView1,textView2,textView3,textView4,textView5,textView6,textView7,textView8,textView9,textView10;

    private SystemDBHelper systemDBHelper;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.humplan_edit);
        button1=findViewById(R.id.btn_true);
        button2=findViewById(R.id.btn_false);
        humplanDBHelper=new HumPlanDBHelper(HumPlanEditActivity.this,"NIMENG.db",null,1);
        //systemDBHelper=new SystemDBHelper(HumPlanEditActivity.this,"NIMENG.db",null,1);
        systemDBHelper=new SystemDBHelper(HumPlanEditActivity.this);
        editName=findViewById(R.id.edit_humplan_name);
        editUnitTime=findViewById(R.id.edit_humplan_unitTime);
        editHumWave=findViewById(R.id.edit_humplan_humWave);
        editHum1=findViewById(R.id.edit_humplan_hum1);
        editHum2=findViewById(R.id.edit_humplan_hum2);
        editHum3=findViewById(R.id.edit_humplan_hum3);
        editHum4=findViewById(R.id.edit_humplan_hum4);
        editHum5=findViewById(R.id.edit_humplan_hum5);
        editHum6=findViewById(R.id.edit_humplan_hum6);
        editHum7=findViewById(R.id.edit_humplan_hum7);
        editHum8=findViewById(R.id.edit_humplan_hum8);
        editHum9=findViewById(R.id.edit_humplan_hum9);
        editHum10=findViewById(R.id.edit_humplan_hum10);
        spinner=findViewById(R.id.spinner1);

        linearLayout4=findViewById(R.id.LinearLayout4);
        linearLayout5=findViewById(R.id.LinearLayout5);
        linearLayout6=findViewById(R.id.LinearLayout6);
        linearLayout7=findViewById(R.id.LinearLayout7);
        linearLayout8=findViewById(R.id.LinearLayout8);
        linearLayout9=findViewById(R.id.LinearLayout9);



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

                switch(i){
                    case 9:
                        linearLayout8.setVisibility(View.VISIBLE);
                        linearLayout7.setVisibility(View.VISIBLE);
                        linearLayout6.setVisibility(View.VISIBLE);
                        linearLayout5.setVisibility(View.VISIBLE);
                        linearLayout4.setVisibility(View.VISIBLE);
                        textView10.setVisibility(View.VISIBLE);
                        editHum10.setVisibility(View.VISIBLE);
                        textView8.setVisibility(View.VISIBLE);
                        editHum8.setVisibility(View.VISIBLE);
                        textView6.setVisibility(View.VISIBLE);
                        editHum6.setVisibility(View.VISIBLE);
                        textView4.setVisibility(View.VISIBLE);
                        editHum4.setVisibility(View.VISIBLE);
                        textView2.setVisibility(View.VISIBLE);
                        editHum2.setVisibility(View.VISIBLE);
                        break;
                    case 8:
                        linearLayout8.setVisibility(View.VISIBLE);
                        linearLayout7.setVisibility(View.VISIBLE);
                        linearLayout6.setVisibility(View.VISIBLE);
                        linearLayout5.setVisibility(View.VISIBLE);
                        linearLayout4.setVisibility(View.VISIBLE);
                        textView10.setVisibility(View.INVISIBLE);
                        editHum10.setVisibility(View.INVISIBLE);
                        textView8.setVisibility(View.VISIBLE);
                        editHum8.setVisibility(View.VISIBLE);
                        textView6.setVisibility(View.VISIBLE);
                        editHum6.setVisibility(View.VISIBLE);
                        textView4.setVisibility(View.VISIBLE);
                        editHum4.setVisibility(View.VISIBLE);
                        textView2.setVisibility(View.VISIBLE);
                        editHum2.setVisibility(View.VISIBLE);
                        break;
                    case 7:

                        linearLayout8.setVisibility(View.GONE);
                        linearLayout7.setVisibility(View.VISIBLE);
                        linearLayout6.setVisibility(View.VISIBLE);
                        linearLayout5.setVisibility(View.VISIBLE);
                        linearLayout4.setVisibility(View.VISIBLE);
                        textView8.setVisibility(View.VISIBLE);
                        editHum8.setVisibility(View.VISIBLE);
                        textView6.setVisibility(View.VISIBLE);
                        editHum6.setVisibility(View.VISIBLE);
                        textView4.setVisibility(View.VISIBLE);
                        editHum4.setVisibility(View.VISIBLE);
                        textView2.setVisibility(View.VISIBLE);
                        editHum2.setVisibility(View.VISIBLE);
                        break;
                    case 6:

                        linearLayout8.setVisibility(View.GONE);
                        linearLayout7.setVisibility(View.VISIBLE);
                        linearLayout6.setVisibility(View.VISIBLE);
                        linearLayout5.setVisibility(View.VISIBLE);
                        linearLayout4.setVisibility(View.VISIBLE);
                        textView8.setVisibility(View.INVISIBLE);
                        editHum8.setVisibility(View.INVISIBLE);
                        textView6.setVisibility(View.VISIBLE);
                        editHum6.setVisibility(View.VISIBLE);
                        textView4.setVisibility(View.VISIBLE);
                        editHum4.setVisibility(View.VISIBLE);
                        textView2.setVisibility(View.VISIBLE);
                        editHum2.setVisibility(View.VISIBLE);
                        break;
                    case 5:

                        linearLayout8.setVisibility(View.GONE);
                        linearLayout7.setVisibility(View.GONE);
                        linearLayout6.setVisibility(View.VISIBLE);
                        linearLayout5.setVisibility(View.VISIBLE);
                        linearLayout4.setVisibility(View.VISIBLE);
                        textView6.setVisibility(View.VISIBLE);
                        editHum6.setVisibility(View.VISIBLE);
                        textView4.setVisibility(View.VISIBLE);
                        editHum4.setVisibility(View.VISIBLE);
                        textView2.setVisibility(View.VISIBLE);
                        editHum2.setVisibility(View.VISIBLE);
                        break;
                    case 4:

                        linearLayout8.setVisibility(View.GONE);
                        linearLayout7.setVisibility(View.GONE);
                        linearLayout6.setVisibility(View.VISIBLE);
                        linearLayout5.setVisibility(View.VISIBLE);
                        linearLayout4.setVisibility(View.VISIBLE);
                        textView6.setVisibility(View.INVISIBLE);
                        editHum6.setVisibility(View.INVISIBLE);
                        textView4.setVisibility(View.VISIBLE);
                        editHum4.setVisibility(View.VISIBLE);
                        textView2.setVisibility(View.VISIBLE);
                        editHum2.setVisibility(View.VISIBLE);
                        break;
                    case 3:

                        linearLayout8.setVisibility(View.GONE);
                        linearLayout7.setVisibility(View.GONE);
                        linearLayout6.setVisibility(View.GONE);
                        linearLayout5.setVisibility(View.VISIBLE);
                        linearLayout4.setVisibility(View.VISIBLE);
                        textView4.setVisibility(View.VISIBLE);
                        editHum4.setVisibility(View.VISIBLE);
                        textView2.setVisibility(View.VISIBLE);
                        editHum2.setVisibility(View.VISIBLE);
                        break;
                    case 2:

                        linearLayout8.setVisibility(View.GONE);
                        linearLayout7.setVisibility(View.GONE);
                        linearLayout6.setVisibility(View.GONE);
                        linearLayout5.setVisibility(View.VISIBLE);
                        linearLayout4.setVisibility(View.VISIBLE);
                        textView4.setVisibility(View.INVISIBLE);
                        editHum4.setVisibility(View.INVISIBLE);
                        textView2.setVisibility(View.VISIBLE);
                        editHum2.setVisibility(View.VISIBLE);
                        break;
                    case 1:

                        linearLayout8.setVisibility(View.GONE);
                        linearLayout7.setVisibility(View.GONE);
                        linearLayout6.setVisibility(View.GONE);
                        linearLayout5.setVisibility(View.GONE);
                        linearLayout4.setVisibility(View.VISIBLE);
                        textView2.setVisibility(View.VISIBLE);
                        editHum2.setVisibility(View.VISIBLE);
                        break;
                    case 0:

                        linearLayout8.setVisibility(View.GONE);
                        linearLayout7.setVisibility(View.GONE);
                        linearLayout6.setVisibility(View.GONE);
                        linearLayout5.setVisibility(View.GONE);
                        linearLayout4.setVisibility(View.VISIBLE);
                        textView2.setVisibility(View.INVISIBLE);
                        editHum2.setVisibility(View.INVISIBLE);
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
                if(name.equals("")){
                    showToast(HumPlanEditActivity.this,"预设方案名称不能为空");
                    return;
                }



                if(editUnitTime.getText().toString().equals("")){
                    showToast(HumPlanEditActivity.this,"单位时间不能为空");
                    return;
                }

                int unitTime=Integer.valueOf(editUnitTime.getText().toString());


                if(editHumWave.getText().toString().equals("")){
                    showToast(HumPlanEditActivity.this,"湿度波动值不能为空");
                    return;
                }
                float humWave=Float.parseFloat( editHumWave.getText().toString());


                long points=spinner.getSelectedItemId();


                String SHum1=editHum1.getText().toString();
                String SHum2=editHum2.getText().toString();
                String SHum3=editHum3.getText().toString();
                String SHum4=editHum4.getText().toString();
                String SHum5=editHum5.getText().toString();
                String SHum6=editHum6.getText().toString();
                String SHum7=editHum7.getText().toString();
                String SHum8=editHum8.getText().toString();
                String SHum9=editHum9.getText().toString();
                String SHum10=editHum10.getText().toString();





                humPoints=Integer.parseInt(String.valueOf(points));
                switch(humPoints){
                    case 0:
                        if(SHum1.equals("")){
                            showToast(HumPlanEditActivity.this,"湿度一不能为空");
                            return;
                        }else{
                            hum1=Integer.valueOf(SHum1);
                        }
                        break;
                    case 1:
                        if(SHum1.equals("") || SHum2.equals("")){
                            showToast(HumPlanEditActivity.this,"湿度一、湿度二不能为空");
                            return;
                        }else{
                            hum1=Integer.valueOf(SHum1);
                            hum2=Integer.valueOf(SHum2);
                        }
                        break;
                    case 2:
                        if(SHum1.equals("") || SHum2.equals("") || SHum3.equals("")){
                            showToast(HumPlanEditActivity.this,"湿度一、湿度二、湿度三不能为空");
                            return;
                        }else{
                            hum1=Integer.valueOf(SHum1);
                            hum2=Integer.valueOf(SHum2);
                            hum3=Integer.valueOf(SHum3);
                        }
                        break;

                    case 3:
                        if(SHum1.equals("") || SHum2.equals("") || SHum3.equals("") || SHum4.equals("") ){
                            showToast(HumPlanEditActivity.this,"湿度一、湿度二、湿度三、湿度四不能为空");
                            return;
                        }else{
                            hum1=Integer.valueOf(SHum1);
                            hum2=Integer.valueOf(SHum2);
                            hum3=Integer.valueOf(SHum3);
                            hum4=Integer.valueOf(SHum4);
                        }
                        break;

                    case 4:
                        if(SHum1.equals("") || SHum2.equals("") || SHum3.equals("") || SHum4.equals("") || SHum5.equals("")){
                            showToast(HumPlanEditActivity.this,"湿度一、湿度二、湿度三、湿度四、湿度五不能为空");
                            return;
                        }else{
                            hum1=Integer.valueOf(SHum1);
                            hum2=Integer.valueOf(SHum2);
                            hum3=Integer.valueOf(SHum3);
                            hum4=Integer.valueOf(SHum4);
                            hum5=Integer.valueOf(SHum5);
                        }
                        break;

                    case 5:
                        if(SHum1.equals("") || SHum2.equals("") || SHum3.equals("") || SHum4.equals("") || SHum5.equals("") || SHum6.equals("")){
                            showToast(HumPlanEditActivity.this,"湿度一、湿度二、湿度三、湿度四、湿度五、湿度六不能为空");
                            return;
                        }else{
                            hum1=Integer.valueOf(SHum1);
                            hum2=Integer.valueOf(SHum2);
                            hum3=Integer.valueOf(SHum3);
                            hum4=Integer.valueOf(SHum4);
                            hum5=Integer.valueOf(SHum5);
                            hum6=Integer.valueOf(SHum6);
                        }
                        break;
                    case 6:
                        if(SHum1.equals("") || SHum2.equals("") || SHum3.equals("") || SHum4.equals("") || SHum5.equals("") || SHum6.equals("") || SHum7.equals("")){
                            showToast(HumPlanEditActivity.this,"湿度一、湿度二、湿度三、湿度四、湿度五、湿度六、湿度七不能为空");
                            return;
                        }else{
                            hum1=Integer.valueOf(SHum1);
                            hum2=Integer.valueOf(SHum2);
                            hum3=Integer.valueOf(SHum3);
                            hum4=Integer.valueOf(SHum4);
                            hum5=Integer.valueOf(SHum5);
                            hum6=Integer.valueOf(SHum6);
                            hum7=Integer.valueOf(SHum7);
                        }
                        break;
                    case 7:
                        if(SHum1.equals("") || SHum2.equals("") || SHum3.equals("") || SHum4.equals("") || SHum5.equals("") || SHum6.equals("") || SHum7.equals("")|| SHum8.equals("")){
                            showToast(HumPlanEditActivity.this,"湿度一、湿度二、湿度三、湿度四、湿度五、湿度六不能为空");
                            return;
                        }else{
                            hum1=Integer.valueOf(SHum1);
                            hum2=Integer.valueOf(SHum2);
                            hum3=Integer.valueOf(SHum3);
                            hum4=Integer.valueOf(SHum4);
                            hum5=Integer.valueOf(SHum5);
                            hum6=Integer.valueOf(SHum6);
                            hum7=Integer.valueOf(SHum7);
                            hum8=Integer.valueOf(SHum8);
                        }
                        break;
                    case 8:
                        if(SHum1.equals("") || SHum2.equals("") || SHum3.equals("") || SHum4.equals("") || SHum5.equals("") || SHum6.equals("") || SHum7.equals("") || SHum8.equals("") || SHum9.equals("")){
                            showToast(HumPlanEditActivity.this,"湿度一、湿度二、湿度三、湿度四、湿度五、湿度六、湿度七、湿度八、湿度九不能为空");
                            return;
                        }else{
                            hum1=Integer.valueOf(SHum1);
                            hum2=Integer.valueOf(SHum2);
                            hum3=Integer.valueOf(SHum3);
                            hum4=Integer.valueOf(SHum4);
                            hum5=Integer.valueOf(SHum5);
                            hum6=Integer.valueOf(SHum6);
                            hum7=Integer.valueOf(SHum7);
                            hum8=Integer.valueOf(SHum8);
                            hum9=Integer.valueOf(SHum9);
                        }
                        break;
                    case 9:
                        if(SHum1.equals("") || SHum2.equals("") || SHum3.equals("") || SHum4.equals("") || SHum5.equals("") || SHum6.equals("")|| SHum7.equals("") || SHum8.equals("") || SHum9.equals("") || SHum10.equals("")){
                            showToast(HumPlanEditActivity.this,"湿度一、湿度二、湿度三、湿度四、湿度五、湿度六、湿度七、湿度八、湿度九、湿度十不能为空");
                            return;
                        }else{
                            hum1=Integer.valueOf(SHum1);
                            hum2=Integer.valueOf(SHum2);
                            hum3=Integer.valueOf(SHum3);
                            hum4=Integer.valueOf(SHum4);
                            hum5=Integer.valueOf(SHum5);
                            hum6=Integer.valueOf(SHum6);
                            hum7=Integer.valueOf(SHum7);
                            hum8=Integer.valueOf(SHum8);
                            hum9=Integer.valueOf(SHum9);
                            hum10=Integer.valueOf(SHum10);
                        }
                        break;

                }


                if(hum1>100 || hum1<0||hum2>100 || hum2<0||hum3>100 || hum3<0||hum4>100 || hum4<0||hum5>100 || hum5<0||hum6>100 || hum6<0||hum7>100 || hum7<0||hum8>100 || hum8<0||hum9>100 || hum9<0||hum10>100 || hum10<0){
                    showToast(HumPlanEditActivity.this,"湿度不能大于100且不能小于0");
                    return;
                }

                if(unitTime>30){
                    showToast(HumPlanEditActivity.this,"稳定条件的时间不能超过30分钟");
                       return;
                }
                int result= humplanDBHelper.findHumPlanByName(name);
                if(result<=0){
                    HumPlanBean humplanBean2=new HumPlanBean();
                    humplanBean2.setName(name);
                    humplanBean2.setUnitTime(unitTime);
                    humplanBean2.setHumWave(humWave);
                    humplanBean2.setHumPoints(humPoints+1);
                    humplanBean2.setHum1(hum1);
                    humplanBean2.setHum2(hum2);
                    humplanBean2.setHum3(hum3);
                    humplanBean2.setHum4(hum4);
                    humplanBean2.setHum5(hum5);
                    humplanBean2.setHum6(hum6);
                    humplanBean2.setHum7(hum7);
                    humplanBean2.setHum8(hum8);
                    humplanBean2.setHum9(hum9);
                    humplanBean2.setHum10(hum10);
                    humplanBean2.setIsCheck(0);



                    if(humplanDBHelper.add(humplanBean2)){
                        Log.d("添加成功", "onClick: ");
                        showToast(HumPlanEditActivity.this,"添加成功");



                        startActivity(new Intent(HumPlanEditActivity.this,HumPlanActivity.class));


                    }else{
                        Log.d("添加失败", "onClick: ");
                        showToast(HumPlanEditActivity.this,"添加失败");
                    }
                }else{
                    showToast(HumPlanEditActivity.this,"该方案已经存在");
                }





            }
        });


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HumPlanEditActivity.this,HumPlanActivity.class));
            }
        });




    }



}
