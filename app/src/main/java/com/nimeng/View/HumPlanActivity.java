package com.nimeng.View;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.nimeng.Adapter.HumPlanAdapter;
import com.nimeng.bean.GlobalVariable;
import com.nimeng.bean.HumPlanBean;
import com.nimeng.util.HumPlanDBHelper;


import java.util.Date;
import java.util.List;

/**
 * Author: wfs
 * <p>
 * Create: 2022/5/6 16:35
 * <p>
 * Changes (from 2022/5/6)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/5/6 : Create HumPlanActivity.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class HumPlanActivity extends BaseAvtivity{
    private Button btn_add,btn_totemplan;
    private ListView listView;
    private EditText editName,editUnitTime,editHumWave,editHum1,editHum2,editHum3,editHum4,editHum5;
    private HumPlanAdapter adapter;
    private HumPlanDBHelper humplanDBHelper;
    private List<HumPlanBean> list;
    GlobalVariable globalVariable;
    Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        globalVariable=(GlobalVariable)getApplicationContext();

        setContentView(R.layout.activity_humplan);
        btn_add=findViewById(R.id.humplan_add);
        btn_totemplan=findViewById(R.id.humplan_totemplan);
        listView=findViewById(R.id.humplan_list);
        if(list!=null){
            list.clear();
        }
        humplanDBHelper=new HumPlanDBHelper(HumPlanActivity.this,"NIMENG.db",null,1);
        updateListView();

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addData();
            }
        });

        btn_totemplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent=new Intent(HumPlanActivity.this,TemPlanActivity.class);
                startActivity(intent);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                updateCheck(i);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                deleteData(i);
                return true;
            }
        });
    }


    private void addData(){
        AlertDialog.Builder builder=new AlertDialog.Builder(HumPlanActivity.this);
        View dialogView =View.inflate(HumPlanActivity.this,R.layout.humplan_edit,null);
        builder.setTitle("添加温度预设方案")
                .setView(dialogView);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                editName=dialogView.findViewById(R.id.edit_humplan_name);
                editUnitTime=dialogView.findViewById(R.id.edit_humplan_unitTime);
                editHumWave=dialogView.findViewById(R.id.edit_humplan_humWave);
                editHum1=dialogView.findViewById(R.id.edit_humplan_hum1);
                editHum2=dialogView.findViewById(R.id.edit_humplan_hum2);
                editHum3=dialogView.findViewById(R.id.edit_humplan_hum3);
                editHum4=dialogView.findViewById(R.id.edit_humplan_hum4);
                editHum5=dialogView.findViewById(R.id.edit_humplan_hum5);


                String name=editName.getText().toString();
                if(name==""){
                    showToast("预设方案名称不能为空");
                    return;
                }



                if(editUnitTime.getText().toString()==" "){
                    showToast("单位时间不能为空");
                    return;
                }

                int unitTime=Integer.valueOf(editUnitTime.getText().toString());


                if(editHumWave.getText().toString()==""){
                    showToast("温度波动值不能为空");
                    return;
                }
                float humWave=Float.parseFloat( editHumWave.getText().toString());


                String Shum1=editHum1.getText().toString();
                String Shum2=editHum2.getText().toString();
                String Shum3=editHum3.getText().toString();
                String Shum4=editHum4.getText().toString();
                String Shum5=editHum5.getText().toString();

                Float hum1,hum2,hum3,hum4,hum5;
               if(Shum1.equals("")){
                   hum1=0f;
               }else{
                   hum1=Float.valueOf(Shum1);
               }

                if(Shum2.equals("")){
                    hum2=0f;
                }else{
                    hum2=Float.valueOf(Shum2);
                }

                if(Shum3.equals("")){
                    hum3=0f;
                }else{
                    hum3=Float.valueOf(Shum3);
                }

                if(Shum4.equals("")){
                    hum4=0f;
                }else{
                    hum4=Float.valueOf(Shum4);
                }

                if(Shum5.equals("")){
                    hum5=0f;
                }else{
                    hum5=Float.valueOf(Shum5);
                }




                HumPlanBean humplanBean=humplanDBHelper.findHumPlanByName(name);

                Log.d("查询信息", "onClick: "+humplanBean.getName());
                if(humplanBean.getName()==null){
                    HumPlanBean humplanBean2=new HumPlanBean();
                    humplanBean2.setName(name);
                    humplanBean2.setUnitTime(unitTime);
                    humplanBean2.setHumWave(humWave);
                    humplanBean2.setHum1(hum1);
                    humplanBean2.setHum2(hum2);
                    humplanBean2.setHum3(hum3);
                    humplanBean2.setHum4(hum4);
                    humplanBean2.setHum5(hum5);
                    humplanBean2.setIsCheck(0);

                    if(humplanDBHelper.add(humplanBean2)){
                        Log.d("添加成功", "onClick: ");
                        showToast("添加成功");
                        updateListView();
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



    private void deleteData(int position){
        AlertDialog.Builder builder=new AlertDialog.Builder(HumPlanActivity.this);
        builder.setTitle("提示")
                .setMessage("是否删除该方案")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        HumPlanBean humplanBean=(HumPlanBean) adapter.getItem(position);

                        Log.d("删除时", "onClick:"+humplanBean.getID()+"   "+globalVariable.getHumID());

                        if(humplanBean.getID()== globalVariable.getHumID()){
                            showToast("当前方案正在使用，不能删除");
                        }else{
                            String deleteID=String.valueOf( humplanBean.getID());
                            if(humplanDBHelper.delete(deleteID)){
                                updateListView();
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



    public void updateListView(){
        list=humplanDBHelper.query();
        adapter=new HumPlanAdapter(list,HumPlanActivity.this);
        listView.setAdapter(adapter);
    }

    public void showToast(String msg){
        Toast.makeText(HumPlanActivity.this,msg,Toast.LENGTH_SHORT).show();
    }



    private void updateCheck(int position){
        HumPlanBean humplanBean=(HumPlanBean) adapter.getItem(position);

        AlertDialog.Builder builder=new AlertDialog.Builder(HumPlanActivity.this);
        builder.setTitle("选择该方案？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(humplanDBHelper.updateCheck(humplanBean.getID(),globalVariable.getHumID())){

                            globalVariable.setHumID(humplanBean.getID());
                            globalVariable.setStartTime(new Date());
                            globalVariable.setHumUnitTime(humplanBean.getUnitTime());
                            globalVariable.setHumWave(humplanBean.getHumWave());
                            globalVariable.setStable(false);
                            globalVariable.setHumPlanName(humplanBean.getName());


                            globalVariable.setExecutingHumID(1);
                            globalVariable.setHumIsSystem(false);



                            showToast("已选择"+humplanBean.getName());
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

}
