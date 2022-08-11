package com.nimeng.View;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
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

    private TemPlanAdapter adapter;
    private TemPlanDBHelper templanDBHelper;
    private List<TemPlanBean> list;
    GlobalVariable globalVariable;
    Intent intent;
    int i;

    private Spinner spinner;


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


    private void addData() {
        startActivity(new Intent(TemPlanActivity.this, TemPlanEditActivity.class));
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
                                List<String> list=globalVariable.getTemPlanList();

                                System.out.println("删除前----->"+list);

                                for( i=0;i<=list.size();i++){
                                    if(list.get(i).equals(templanBean.getName())){
                                        list.remove(i);
                                    }
                                }

                                System.out.println("删除后----->"+list);


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
