package com.nimeng.View;

import android.content.DialogInterface;
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
import androidx.appcompat.app.AppCompatActivity;

import com.nimeng.Adapter.PlanAdapter;
import com.nimeng.bean.PlanBean;
import com.nimeng.util.PlanDBHelper;

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
 * 2022/5/6 : Create PlanActivity.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class PlanActivity extends AppCompatActivity {
    private Button btn_add;
    private ListView listView;
    private EditText editName,editUnitTime,editTemWave,editHumWave,editTem1,editTem2,editTem3,editHum1,editHum2,editHum3;
    private PlanAdapter adapter;
    private PlanDBHelper planDBHelper;
    private List<PlanBean> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);
        btn_add=findViewById(R.id.plan_add);
        listView=findViewById(R.id.plan_list);
        if(list!=null){
            list.clear();
        }
       planDBHelper=new PlanDBHelper(PlanActivity.this,"NIMENG.db",null,1);
        //planModel=new PlanModel();
        updateListView();

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addData();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                updateData(i);
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
        AlertDialog.Builder builder=new AlertDialog.Builder(PlanActivity.this);
        View dialogView =View.inflate(PlanActivity.this,R.layout.plan_edit,null);
        builder.setTitle("添加预设方案")
                .setView(dialogView);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                editName=dialogView.findViewById(R.id.edit_name);
                editUnitTime=dialogView.findViewById(R.id.edit_unitTime);
                editTemWave=dialogView.findViewById(R.id.edit_temWave);
                editHumWave=dialogView.findViewById(R.id.edit_humWave);
                editTem1=dialogView.findViewById(R.id.edit_tem1);
                editTem2=dialogView.findViewById(R.id.edit_tem2);
                editTem3=dialogView.findViewById(R.id.edit_tem3);
                editHum1=dialogView.findViewById(R.id.edit_hum1);
                editHum2=dialogView.findViewById(R.id.edit_hum2);
                editHum3=dialogView.findViewById(R.id.edit_hum3);

                String name=editName.getText().toString();
                if(name==""){
                    showToast("预设方案名称不能为空");
                    return;
                }


                Log.d("提前判断", "onClick: "+editUnitTime.getText());

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
                if(editHumWave.getText().toString()==""){
                    showToast("湿度波动值不能为空");
                }
                float humWave=Float.parseFloat(editHumWave.getText().toString());




                String tem1=editTem1.getText().toString();
                String tem2=editTem2.getText().toString();
                String tem3=editTem3.getText().toString();
                String hum1=editHum1.getText().toString();
                String hum2=editHum2.getText().toString();
                String hum3=editHum3.getText().toString();


                Log.d("点击添加按钮", "onClick: "+name+"  "+unitTime+"  "+temWave+"   "+humWave);
                PlanBean planBean=planDBHelper.findPlanByName(name);

                Log.d("查询信息", "onClick: "+planBean.getName());
                if(planBean.getName()==null){
                    PlanBean planBean2=new PlanBean();
                    planBean2.setName(name);
                    planBean2.setUnitTime(unitTime);
                    planBean2.setTemWave(temWave);
                    planBean2.setHumWave(humWave);
                    planBean2.setTem1(tem1);
                    planBean2.setTem2(tem2);
                    planBean2.setTem3(tem3);
                    planBean2.setHum1(hum1);
                    planBean2.setHum2(hum2);
                    planBean2.setHum3(hum3);

                    if(planDBHelper.add(planBean2)){
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
        AlertDialog.Builder builder=new AlertDialog.Builder(PlanActivity.this);
        builder.setTitle("提示")
                .setMessage("是否删除该方案")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        PlanBean planBean=(PlanBean) adapter.getItem(position);
                        String deleteID=String.valueOf( planBean.getID());
                        if(planDBHelper.delete(deleteID)){
                            updateListView();
                            showToast("删除成功");
                        }else{
                            showToast("删除失败");
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



    private void updateData(int position){
        View editView=View.inflate(PlanActivity.this,R.layout.plan_edit,null);
        PlanBean planBean=(PlanBean) adapter.getItem(position);
        editName=editView.findViewById(R.id.edit_name);
        editUnitTime=editView.findViewById(R.id.edit_unitTime);
        editTemWave=editView.findViewById(R.id.edit_temWave);
        editHumWave=editView.findViewById(R.id.edit_humWave);
        editTem1=editView.findViewById(R.id.edit_tem1);
        editTem2=editView.findViewById(R.id.edit_tem2);
        editTem3=editView.findViewById(R.id.edit_tem3);
        editHum1=editView.findViewById(R.id.edit_hum1);
        editHum2=editView.findViewById(R.id.edit_hum2);
        editHum3=editView.findViewById(R.id.edit_hum3);

        editName.setText(planBean.getName());
        editUnitTime.setText(String.valueOf( planBean.getUnitTime()));
        editTemWave.setText(String.valueOf( planBean.getTemWave()));
        editHumWave.setText(String.valueOf( planBean.getHumWave()));
        editTem1.setText(String.valueOf(planBean.getTem1()));
        editTem2.setText(String.valueOf(planBean.getTem2()));
        editTem3.setText(String.valueOf(planBean.getTem3()));
        editHum1.setText(String.valueOf(planBean.getHum1()));
        editHum2.setText(String.valueOf(planBean.getHum2()));
        editHum3.setText(String.valueOf(planBean.getHum3()));
        String findID= String.valueOf( planBean.getID());




        AlertDialog.Builder builder=new AlertDialog.Builder(PlanActivity.this);
        builder.setTitle("修改方案")
                .setView(editView)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String name=editName.getText().toString();
                        int unitTime=Integer.valueOf( editUnitTime.getText().toString());
                        float temWave=Float.parseFloat( editTemWave.getText().toString());
                        float humWave=Float.parseFloat(editHumWave.getText().toString());
                        String tem1=editTem1.getText().toString();
                        String tem2=editTem2.getText().toString();
                        String tem3=editTem3.getText().toString();
                        String hum1=editHum1.getText().toString();
                        String hum2=editHum2.getText().toString();
                        String hum3=editHum3.getText().toString();

                        if(name==""){
                            showToast("预设方案名称不能为空");
                        }if(unitTime==0){
                            showToast("单位时间不能为0");
                        }if(temWave==0){
                            showToast("温度波动为0");
                        }if(humWave==0){
                            showToast("湿度波动不能为0");
                        }
                        else {
                            PlanBean planBean1=planDBHelper.findPlanByName(name);
                            if(planBean1.getName()==null){
                                PlanBean planBean2=new PlanBean();
                                planBean2.setName(name);
                                planBean2.setUnitTime(unitTime);
                                planBean2.setTemWave(temWave);
                                planBean2.setHumWave(humWave);
                                planBean2.setTem1(tem1);
                                planBean2.setTem2(tem2);
                                planBean2.setTem3(tem3);
                                planBean2.setHum1(hum1);
                                planBean2.setHum2(hum2);
                                planBean2.setHum3(hum3);
                               if( planDBHelper.update(planBean2)){
                                   showToast("预设方案修改成功");
                               }else{
                                   showToast("预设方案修改失败");
                               }
                            }else{
                                showToast("该方案已经存在，不能修改");
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
        list=planDBHelper.query();
        adapter=new PlanAdapter(list,PlanActivity.this);
        listView.setAdapter(adapter);
    }

    public void showToast(String msg){
        Toast.makeText(PlanActivity.this,msg,Toast.LENGTH_SHORT).show();
    }


}
