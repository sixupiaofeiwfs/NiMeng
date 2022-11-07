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

import com.nimeng.Adapter.HumPlanAdapter;
import com.nimeng.bean.HumPlanBean;
import com.nimeng.bean.SystemData;
import com.nimeng.util.CommonUtil;
import com.nimeng.util.HumPlanDBHelper;
import com.nimeng.util.SystemDBHelper;


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
public class HumPlanActivity extends CommonUtil {
    private Button btn_add,btn_totemplan;
    private ListView listView;
    private HumPlanAdapter adapter;
    private HumPlanDBHelper humplanDBHelper;
    private List<HumPlanBean> list;
    Intent intent;
    int i;

    private Spinner spinner;
    private SystemDBHelper systemDBHelper;
    SystemData systemData;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_humplan);
        btn_add=findViewById(R.id.humplan_add);
        btn_totemplan=findViewById(R.id.humplan_totemplan);
        listView=findViewById(R.id.humplan_list);
        if(list!=null){
            list.clear();
        }
        humplanDBHelper=HumPlanDBHelper.getInstance(HumPlanActivity.this);
       // systemDBHelper=new SystemDBHelper(HumPlanActivity.this,"NIMENG.db",null,1);
        systemDBHelper=new SystemDBHelper(HumPlanActivity.this);
        systemData=systemDBHelper.getSystemData();
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
                //updateCheck(i);
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
    }


    private void addData(){
       startActivity(new Intent(HumPlanActivity.this,HumPlanEditActivity.class));
    }



    private void deleteData(int position){
        AlertDialog.Builder builder=new AlertDialog.Builder(HumPlanActivity.this);
        builder.setTitle("提示")
                .setMessage("是否删除该方案")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        HumPlanBean humplanBean=(HumPlanBean) adapter.getItem(position);



                        if(humplanBean.getID()== systemData.getHumPlanID()){
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

        System.out.println(list);

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
                        if(humplanDBHelper.updateCheck(humplanBean.getID(),systemData.getHumPlanID())){

                            systemData.setHumPlanID(humplanBean.getID());
                            systemData.setStartTime(getDateTimeToString(new Date()));
                            systemData.setHumUnitTime(humplanBean.getUnitTime());
                            systemData.setHumWave(humplanBean.getHumWave());
                            systemData.setStable(false);


                            systemData.setExecutingHumID(1);
                            systemDBHelper.updateSystemData(systemData);


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
