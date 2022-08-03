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

import com.nimeng.Adapter.TemPlanAdapter;

import com.nimeng.bean.GlobalVariable;
import com.nimeng.bean.TemPlanBean;
import com.nimeng.util.TemPlanDBHelper;


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
 * 2022/5/6 : Create TemPlanActivity.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class TemPlanActivity extends BaseAvtivity{
    private Button btn_add,btn_tohumplan;
    private ListView listView;
    private EditText editName,editUnitTime,editTemWave,editTem1,editTem2,editTem3,editTem4,editTem5;
    private TemPlanAdapter adapter;
    private TemPlanDBHelper templanDBHelper;
    private List<TemPlanBean> list;
    GlobalVariable globalVariable;
    Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        globalVariable=(GlobalVariable)getApplicationContext();

        setContentView(R.layout.activity_templan);
        btn_add=findViewById(R.id.templan_add);
        btn_tohumplan=findViewById(R.id.templan_tohumplan);
        listView=findViewById(R.id.templan_list);
        if(list!=null){
            list.clear();
        }
        templanDBHelper=new TemPlanDBHelper(TemPlanActivity.this,"NIMENG.db",null,1);
        updateListView();

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addData();
            }
        });


        btn_tohumplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent=new Intent(TemPlanActivity.this,HumPlanActivity.class);
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
        AlertDialog.Builder builder=new AlertDialog.Builder(TemPlanActivity.this);
        View dialogView =View.inflate(TemPlanActivity.this,R.layout.templan_edit,null);
        builder.setTitle("添加温度预设方案")
                .setView(dialogView);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                editName=dialogView.findViewById(R.id.edit_templan_name);
                editUnitTime=dialogView.findViewById(R.id.edit_templan_unitTime);
                editTemWave=dialogView.findViewById(R.id.edit_templan_temWave);
                editTem1=dialogView.findViewById(R.id.edit_templan_tem1);
                editTem2=dialogView.findViewById(R.id.edit_templan_tem2);
                editTem3=dialogView.findViewById(R.id.edit_templan_tem3);
                editTem4=dialogView.findViewById(R.id.edit_templan_tem4);
                editTem5=dialogView.findViewById(R.id.edit_templan_tem5);


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


                if(editTemWave.getText().toString()==""){
                    showToast("温度波动值不能为空");
                    return;
                }
                float temWave=Float.parseFloat( editTemWave.getText().toString());


                String STem1=editTem1.getText().toString();
                String STem2=editTem2.getText().toString();
                String STem3=editTem3.getText().toString();
                String STem4=editTem4.getText().toString();
                String STem5=editTem5.getText().toString();

                Float tem1,tem2,tem3,tem4,tem5;
                if(STem1.equals("")){
                    tem1=0f;
                }else{
                    tem1=Float.valueOf(STem1);
                }

                if(STem2.equals("")){
                    tem2=0f;
                }else{
                    tem2=Float.valueOf(STem2);
                }

                if(STem3.equals("")){
                    tem3=0f;
                }else{
                    tem3=Float.valueOf(STem3);
                }

                if(STem4.equals("")){
                    tem4=0f;
                }else{
                    tem4=Float.valueOf(STem4);
                }

                if(STem5.equals("")){
                    tem5=0f;
                }else{
                    tem5=Float.valueOf(STem5);
                }







                TemPlanBean templanBean=templanDBHelper.findTemPlanByName(name);

                Log.d("查询信息", "onClick: "+templanBean.getName());
                if(templanBean.getName()==null){
                    TemPlanBean templanBean2=new TemPlanBean();
                    templanBean2.setName(name);
                    templanBean2.setUnitTime(unitTime);
                    templanBean2.setTemWave(temWave);
                    templanBean2.setTem1(tem1);
                    templanBean2.setTem2(tem2);
                    templanBean2.setTem3(tem3);
                    templanBean2.setTem4(tem4);
                    templanBean2.setTem5(tem5);
                    templanBean2.setIsCheck(0);

                    if(templanDBHelper.add(templanBean2)){
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
        AlertDialog.Builder builder=new AlertDialog.Builder(TemPlanActivity.this);
        builder.setTitle("提示")
                .setMessage("是否删除该方案")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        TemPlanBean templanBean=(TemPlanBean) adapter.getItem(position);

                        Log.d("删除时", "onClick:"+templanBean.getID()+"   "+globalVariable.getTemID());

                        if(templanBean.getID()== globalVariable.getTemID()){
                            showToast("当前方案正在使用，不能删除");
                        }else{
                            String deleteID=String.valueOf( templanBean.getID());
                            if(templanDBHelper.delete(deleteID)){
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
        list=templanDBHelper.query();
        adapter=new TemPlanAdapter(list,TemPlanActivity.this);
        listView.setAdapter(adapter);
    }

    public void showToast(String msg){
        Toast.makeText(TemPlanActivity.this,msg,Toast.LENGTH_SHORT).show();
    }



    private void updateCheck(int position){
        TemPlanBean templanBean=(TemPlanBean) adapter.getItem(position);

        AlertDialog.Builder builder=new AlertDialog.Builder(TemPlanActivity.this);
        builder.setTitle("选择该方案？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(templanDBHelper.updateCheck(templanBean.getID(),globalVariable.getTemID())){

                            globalVariable.setTemID(templanBean.getID());

                            globalVariable.setStartTime(new Date());

                            globalVariable.setTemUnitTime(templanBean.getUnitTime());
                            globalVariable.setTemWave(templanBean.getTemWave());
                            globalVariable.setStable(false);
                            globalVariable.setTemPlanName(templanBean.getName());



                            globalVariable.setExecutingTemID(1);
                            globalVariable.setTemIsSystem(false);


                            showToast("已选择"+templanBean.getName());
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
