package com.nimeng.View;

import static com.nimeng.View.MainActivity.DATABASE_NAME;

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

import com.nimeng.Adapter.HumStandardApparatusAdapter;
import com.nimeng.bean.HumStandardApparatus;
import com.nimeng.util.HumStandardApparatusDBHelper;

import java.util.List;

/**
 * Author: wfs
 * <p>
 * Create: 2022/6/20 15:51
 * <p>
 * Changes (from 2022/6/20)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/6/20 : Create StandardApparatusActivity.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class HumStandardApparatusActivity extends BaseAvtivity{
    private Button btn1,btn2;
    private ListView listView;
    private HumStandardApparatusAdapter adapter;
    private HumStandardApparatusDBHelper humStandardApparatusDBHelper;
    private List<HumStandardApparatus> list;
    private EditText editName,editPort,editFormat,editRate,editType,editModel,editAgreement,editNumber,editValue,editTraceabilityUnit,editTime;

    Intent intent;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_humstandardapparatus);
        btn1=findViewById(R.id.standardapparatus_btn1);
        btn2=findViewById(R.id.standardapparatus_btn2);
        listView=findViewById(R.id.humstandardapparatus_list);
        if(list!=null){
            list.clear();
        }
        humStandardApparatusDBHelper =new HumStandardApparatusDBHelper(HumStandardApparatusActivity.this,DATABASE_NAME,null,1);
        //planModel=new PlanModel();
        updateListView();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addData();
            }
        });


        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent=new Intent(HumStandardApparatusActivity.this,TemStandardApparatusActivity.class);
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
                deleteData(i );
                return true;
            }
        });




    }

    private void deleteData(int position){
        AlertDialog.Builder builder=new AlertDialog.Builder(HumStandardApparatusActivity.this);
        builder.setTitle("提示")
                .setMessage("是否删除该标准器")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        HumStandardApparatus humStandardApparatus=(HumStandardApparatus) adapter.getItem(position);
                        int deleteID= humStandardApparatus.getID();

                        if(deleteID==globalVariable.getHumStandardID()){
                            showToast("当前标准器正在使用，不能删除");
                        }else{
                            if(humStandardApparatusDBHelper.delete(deleteID)){
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



//    private void updateData(int position){
//        View editView=View.inflate(HumStandardApparatusActivity.this,R.layout.standardapparatus_edit,null);
//        HumStandardApparatus humStandardApparatus=(HumStandardApparatus) adapter.getItem(position);
//        editName=editView.findViewById(R.id.edit_s_name);
//        editPort=editView.findViewById(R.id.edit_s_port);
//        editFormat=editView.findViewById(R.id.edit_s_format);
//        editRate=editView.findViewById(R.id.edit_s_rate);
//        editType=editView.findViewById(R.id.edit_s_type);
//        editModel=editView.findViewById(R.id.edit_s_model);
//        editAgreement=editView.findViewById(R.id.edit_s_agreement);
//        editNumber=editView.findViewById(R.id.edit_s_number);
//        editValue=editView.findViewById(R.id.edit_s_value);
//        editTraceabilityUnit=editView.findViewById(R.id.edit_s_traceabilityUnit);
//        editTime=editView.findViewById(R.id.edit_s_time);
//
//        editName.setText(humStandardApparatus.getName());
//        editPort.setText(String.valueOf( humStandardApparatus.getPort()));
//        editFormat.setText( String.valueOf(humStandardApparatus.getFormat()));
//        editRate.setText(String.valueOf( humStandardApparatus.getRate()));
//        editType.setText(String.valueOf(humStandardApparatus.getType()));
//        editModel.setText(String.valueOf(humStandardApparatus.getModel()));
//        editAgreement.setText(String.valueOf(humStandardApparatus.getAgreement()));
//        editNumber.setText(String.valueOf(humStandardApparatus.getNumber()));
//        editValue.setText(String.valueOf(humStandardApparatus.getValue()));
//        editTraceabilityUnit.setText(String.valueOf(humStandardApparatus.getTraceabilityUnit()));
//        editTime.setText(humStandardApparatus.getTime());
//
//        // String findID= String.valueOf( humStandardApparatus.getID());
//
//
//
//
//        AlertDialog.Builder builder=new AlertDialog.Builder(HumStandardApparatusActivity.this);
//        builder.setTitle("修改标准器")
//                .setView(editView)
//                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        String name=editName.getText().toString();
//                        int port =Integer.valueOf( editPort.getText().toString());
//
//                        String format=editFormat.getText().toString();
//                        int rate=Integer.valueOf(editRate.getText().toString());
//
//
//
//                        String type=editType.getText().toString();
//                        String model=editModel.getText().toString();
//                        String agreement=editAgreement.getText().toString();
//                        String number=editNumber.getText().toString();
//                        String value=editValue.getText().toString();
//                        String traceabilityUnit=editTraceabilityUnit.getText().toString();
//                        String time=editTime.getText().toString();
//
//
//                        if(name==""){
//                            showToast("名称不能为空");
//                        }if(port==0){
//                            showToast("端口不能为0");
//                        }if(rate==0){
//                            showToast("速率不能为0");
//                        }
//                        else {
//                            HumStandardApparatus humStandardApparatus1=standardApparatusDBHelper.findByName(name);
//                            if(humStandardApparatus1.getName()==null){
//                                HumStandardApparatus humStandardApparatus2=new HumStandardApparatus();
//                                humStandardApparatus2.setName(name);
//                                humStandardApparatus2.setPort(port);
//                                humStandardApparatus2.setFormat(format);
//                                humStandardApparatus2.setRate(rate);
//                                humStandardApparatus2.setType(type);
//                                humStandardApparatus2.setModel(model);
//                                humStandardApparatus2.setAgreement(agreement);
//                                humStandardApparatus2.setNumber(number);
//                                humStandardApparatus2.setValue(value);
//                                humStandardApparatus2.setTraceabilityUnit(traceabilityUnit);
//                                humStandardApparatus2.setTime(time);
//                                if( standardApparatusDBHelper.update(humStandardApparatus2)){
//                                    showToast("标准器修改成功");
//                                }else{
//                                    showToast("标准器修改失败");
//                                }
//                            }else{
//                                showToast("该标准器已经存在，不能修改");
//                            }
//                        }
//
//                    }
//                })
//                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        dialogInterface.dismiss();
//                    }
//                });
//        AlertDialog alertDialog=builder.create();
//        alertDialog.show();
//
//    }


    private void updateListView(){
        list=humStandardApparatusDBHelper.query();
        adapter=new HumStandardApparatusAdapter(list, HumStandardApparatusActivity.this);
        listView.setAdapter(adapter);

    }

    public void showToast(String msg){
        Toast.makeText(HumStandardApparatusActivity.this,msg,Toast.LENGTH_SHORT).show();
    }




    private void addData(){
        AlertDialog.Builder builder=new AlertDialog.Builder(HumStandardApparatusActivity.this);
        View dialogView =View.inflate(HumStandardApparatusActivity.this,R.layout.standardapparatus_edit,null);
        builder.setTitle("添加标准器")
                .setView(dialogView);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                editName=dialogView.findViewById(R.id.edit_s_name);
                editPort=dialogView.findViewById(R.id.edit_s_port);
                editFormat=dialogView.findViewById(R.id.edit_s_format);
                editRate=dialogView.findViewById(R.id.edit_s_rate);
                editType=dialogView.findViewById(R.id.edit_s_type);
                editModel=dialogView.findViewById(R.id.edit_s_model);
                editAgreement=dialogView.findViewById(R.id.edit_s_agreement);
                editNumber=dialogView.findViewById(R.id.edit_s_number);
                editValue=dialogView.findViewById(R.id.edit_s_value);
                editTraceabilityUnit=dialogView.findViewById(R.id.edit_s_traceabilityUnit);
                editTime=dialogView.findViewById(R.id.edit_s_time);
                String name=editName.getText().toString();


                if(name.equals("")){
                    Log.d("这里的name不是空格吗", "onClick: ");
                    showToast("标准器名称不能为空");
                    return;
                }



                if(editPort.getText().toString().equals("")){
                    showToast("通讯串口不能为空");
                    return;
                }

                int port=Integer.valueOf(editPort.getText().toString());

                if(editFormat.getText().toString().equals("")){
                    showToast("通讯格式不能为空");
                    return;
                }


                if(editRate.getText().toString().equals("")){
                    showToast("通讯速率不能为空");
                    return;
                }
                int rate=Integer.valueOf(editRate.getText().toString());






                String format=editFormat.getText().toString();
                String type=editType.getText().toString();
                String model=editModel.getText().toString();
                String agreement=editAgreement.getText().toString();
                String number=editNumber.getText().toString();
                String value=editValue.getText().toString();
                String traceabilityUnit=editTraceabilityUnit.getText().toString();
                String time=editTime.getText().toString();



                HumStandardApparatus humStandardApparatus=humStandardApparatusDBHelper.findByName(name);


                if(humStandardApparatus.getName()==null){
                    HumStandardApparatus humStandardApparatus2=new HumStandardApparatus();
                    humStandardApparatus2.setName(name);
                    humStandardApparatus2.setPort(port);
                    humStandardApparatus2.setFormat(format);
                    humStandardApparatus2.setRate(rate);
                    humStandardApparatus2.setType(type);
                    humStandardApparatus2.setModel(model);
                    humStandardApparatus2.setAgreement(agreement);
                    humStandardApparatus2.setNumber(number);
                    humStandardApparatus2.setValue(value);
                    humStandardApparatus2.setTraceabilityUnit(traceabilityUnit);
                    humStandardApparatus2.setTime(time);

                    if(humStandardApparatusDBHelper.add(humStandardApparatus2)){
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


    private void updateCheck(int position){
        HumStandardApparatus humStandardApparatus=(HumStandardApparatus) adapter.getItem(position);

        AlertDialog.Builder builder=new AlertDialog.Builder(HumStandardApparatusActivity.this);

        builder.setTitle("选择该标准器？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(humStandardApparatusDBHelper.updateCheck(humStandardApparatus.getID(),globalVariable.getHumStandardID()));
                        globalVariable.setHumStandardID(humStandardApparatus.getID());
                        Toast.makeText(HumStandardApparatusActivity.this,"已选择"+humStandardApparatus.getName(),Toast.LENGTH_SHORT).show();
                        updateListView();
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