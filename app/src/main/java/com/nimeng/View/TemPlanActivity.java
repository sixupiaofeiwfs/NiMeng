package com.nimeng.View;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.nimeng.Adapter.TemPlanAdapter;

import com.nimeng.bean.SystemData;
import com.nimeng.bean.TemPlanBean;
import com.nimeng.util.CommonUtil;
import com.nimeng.util.SystemDBHelper;
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
public class TemPlanActivity extends CommonUtil {
    private Button btn_add,btn_tohumplan;
    private ListView listView;

    private TemPlanAdapter adapter;
    private TemPlanDBHelper templanDBHelper;
    private List<TemPlanBean> list;

    Intent intent;
    int i;

    private Spinner spinner;

    private SystemDBHelper systemDBHelper;
    SystemData systemData;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_templan);
        btn_add=findViewById(R.id.templan_add);
        btn_tohumplan=findViewById(R.id.templan_tohumplan);
        listView=findViewById(R.id.templan_list);


    }


    @Override
    protected void onRestart() {
        super.onRestart();
    }


    @Override
    protected void onStart() {
       // systemDBHelper=new SystemDBHelper(TemPlanActivity.this,"NIMENG.db",null,1);
        systemDBHelper=new SystemDBHelper(TemPlanActivity.this);

        systemData=systemDBHelper.getSystemData();
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
               // updateCheck(i);
                deleteData(i);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                deleteData(i);
                return true;
            }
        });
        super.onStart();
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


                        if(templanBean.getID()== systemData.getTemPlanID()){
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
        System.out.println("所有的温度预设方案------");
        System.out.println(list);
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
                        if(templanDBHelper.updateCheck(templanBean.getID(),systemData.getTemPlanID())){


                            systemData.setTemPlanID(templanBean.getID());
                            systemData.setStartTime(getDateTimeToString(new Date()));
                            systemData.setTemUnitTime(templanBean.getUnitTime());
                            systemData.setTemWave(templanBean.getTemWave());
                            systemData.setStable(false);





                            systemData.setExecutingTemID(1);
                            systemDBHelper.updateSystemData(systemData);



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
