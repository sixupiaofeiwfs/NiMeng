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
    private Button btn_add;
    private ListView listView;
    private EditText editName,editUnitTime,editHumWave,editHum1,editHum2,editHum3,editHum4,editHum5;
    private HumPlanAdapter adapter;
    private HumPlanDBHelper humplanDBHelper;
    private List<HumPlanBean> list;
    GlobalVariable globalVariable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        globalVariable=(GlobalVariable)getApplicationContext();

        setContentView(R.layout.activity_humplan);
        btn_add=findViewById(R.id.humplan_add);
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
        builder.setTitle("????????????????????????")
                .setView(dialogView);
        builder.setPositiveButton("??????", new DialogInterface.OnClickListener() {
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
                    showToast("??????????????????????????????");
                    return;
                }



                if(editUnitTime.getText().toString()==" "){
                    showToast("????????????????????????");
                    return;
                }

                int unitTime=Integer.valueOf(editUnitTime.getText().toString());


                if(editHumWave.getText().toString()==""){
                    showToast("???????????????????????????");
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

                Log.d("????????????", "onClick: "+humplanBean.getName());
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
                        Log.d("????????????", "onClick: ");
                        showToast("????????????");
                        updateListView();
                    }else{
                        Log.d("????????????", "onClick: ");
                        showToast("????????????");
                    }
                }else{
                    showToast("?????????????????????");
                }


            }
        })
                .setNegativeButton("??????", new DialogInterface.OnClickListener() {
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
        builder.setTitle("??????")
                .setMessage("?????????????????????")
                .setPositiveButton("??????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        HumPlanBean humplanBean=(HumPlanBean) adapter.getItem(position);

                        Log.d("?????????", "onClick:"+humplanBean.getID()+"   "+globalVariable.getHumID());

                        if(humplanBean.getID()== globalVariable.getHumID()){
                            showToast("???????????????????????????????????????");
                        }else{
                            String deleteID=String.valueOf( humplanBean.getID());
                            if(humplanDBHelper.delete(deleteID)){
                                updateListView();
                                showToast("????????????");
                            }else{
                                showToast("????????????");
                            }
                        }


                    }
                })
                .setNegativeButton("??????", new DialogInterface.OnClickListener() {
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
        builder.setTitle("??????????????????")
                .setPositiveButton("??????", new DialogInterface.OnClickListener() {
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



                            showToast("?????????"+humplanBean.getName());
                        }
                    }
                })
                .setNegativeButton("??????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

        AlertDialog alertDialog=builder.create();
        alertDialog.show();






    }

}
