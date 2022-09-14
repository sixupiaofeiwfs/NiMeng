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

import com.nimeng.Adapter.StandardApparatusAdapter;
import com.nimeng.bean.StandardApparatus;
import com.nimeng.util.CommonUtil;
import com.nimeng.util.StandardApparatusDBHelper;
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
public class TemStandardApparatusActivity extends CommonUtil {
    private Button btn1,btn2;
    private ListView listView;
    private StandardApparatusDBHelper standardApparatusDBHelper;
    private StandardApparatusAdapter standardApparatusAdapter;
    private List<StandardApparatus> list;

    Intent intent;
    String tableName="temstandardapparatus";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temstandardapparatus);
        btn1=findViewById(R.id.standardapparatus_btn1);
        btn2=findViewById(R.id.standardapparatus_btn2);
        listView=findViewById(R.id.temstandardapparatus_list);



        if(list!=null){
            list.clear();
        }
        standardApparatusDBHelper =new StandardApparatusDBHelper(TemStandardApparatusActivity.this,DATABASE_NAME,null,1);
        list=standardApparatusDBHelper.query(tableName,0);
        standardApparatusAdapter=new StandardApparatusAdapter(list,TemStandardApparatusActivity.this);
        listView.setAdapter(standardApparatusAdapter);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(TemStandardApparatusActivity.this,StandardappratusEditActivity.class);
                intent.putExtra("tableName",tableName);
                startActivity(intent);
               // addData(TemStandardApparatusActivity.this,standardApparatusDBHelper,tableName,standardApparatusAdapter,listView);
            }
        });


        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent=new Intent(TemStandardApparatusActivity.this,HumStandardApparatusActivity.class);
                startActivity(intent);
            }
        });



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                System.out.println("adapter------>"+standardApparatusAdapter);

                updateCheck(TemStandardApparatusActivity.this,standardApparatusAdapter,standardApparatusDBHelper,tableName,i,listView);
            }
        });


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                deleteData(tableName,standardApparatusDBHelper,standardApparatusAdapter,TemStandardApparatusActivity.this,i,listView);
                return true;
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        standardApparatusDBHelper =new StandardApparatusDBHelper(TemStandardApparatusActivity.this,DATABASE_NAME,null,1);
        list=standardApparatusDBHelper.query(tableName,0);
        standardApparatusAdapter=new StandardApparatusAdapter(list,TemStandardApparatusActivity.this);
        listView.setAdapter(standardApparatusAdapter);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(TemStandardApparatusActivity.this,StandardappratusEditActivity.class);
                intent.putExtra("tableName",tableName);
                startActivity(intent);
                // addData(TemStandardApparatusActivity.this,standardApparatusDBHelper,tableName,standardApparatusAdapter,listView);
            }
        });


        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent=new Intent(TemStandardApparatusActivity.this,HumStandardApparatusActivity.class);
                startActivity(intent);
            }
        });



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                System.out.println("adapter------>"+standardApparatusAdapter);

                updateCheck(TemStandardApparatusActivity.this,standardApparatusAdapter,standardApparatusDBHelper,tableName,i,listView);
            }
        });


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                deleteData(tableName,standardApparatusDBHelper,standardApparatusAdapter,TemStandardApparatusActivity.this,i,listView);
                return true;
            }
        });

    }


    //
//    private void deleteData(int position){
//        AlertDialog.Builder builder=new AlertDialog.Builder(TemStandardApparatusActivity.this);
//        builder.setTitle("提示")
//                .setMessage("是否删除该标准器")
//                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        TemStandarApparatus temStandarApparatus=(TemStandarApparatus) adapter.getItem(position);
//                        int deleteID=temStandarApparatus.getID();
//
//                        if(deleteID==globalVariable.getTemStandardID()){
//                            showToast("当前标准器正在使用，不能删除");
//                        }else{
//                            if(temStandardApparatusDBHelper.delete(deleteID)){
//                                updateListView();
//                                showToast("删除成功");
//                            }else{
//                                showToast("删除失败");
//                            }
//                        }
//
//
//
//
//                    }
//                })
//                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        dialogInterface.dismiss();
//                    }
//                });
//
//        AlertDialog alertDialog=builder.create();
//        alertDialog.show();
//    }
//
//
//
////    private void updateData(int position){
////        View editView=View.inflate(TemStandardApparatusActivity.this,R.layout.standardapparatus_edit,null);
////        TemStandarApparatus temStandarApparatus=(TemStandarApparatus) adapter.getItem(position);
////        editName=editView.findViewById(R.id.edit_s_name);
////        editPort=editView.findViewById(R.id.edit_s_port);
////        editFormat=editView.findViewById(R.id.edit_s_format);
////        editRate=editView.findViewById(R.id.edit_s_rate);
////        editType=editView.findViewById(R.id.edit_s_type);
////        editModel=editView.findViewById(R.id.edit_s_model);
////        editAgreement=editView.findViewById(R.id.edit_s_agreement);
////        editNumber=editView.findViewById(R.id.edit_s_number);
////        editValue=editView.findViewById(R.id.edit_s_value);
////        editTraceabilityUnit=editView.findViewById(R.id.edit_s_traceabilityUnit);
////        editTime=editView.findViewById(R.id.edit_s_time);
////
////        editName.setText(temStandarApparatus.getName());
////        editPort.setText(String.valueOf( temStandarApparatus.getPort()));
////        editFormat.setText( String.valueOf(temStandarApparatus.getFormat()));
////        editRate.setText(String.valueOf( temStandarApparatus.getRate()));
////        editType.setText(String.valueOf(temStandarApparatus.getType()));
////        editModel.setText(String.valueOf(temStandarApparatus.getModel()));
////        editAgreement.setText(String.valueOf(temStandarApparatus.getAgreement()));
////        editNumber.setText(String.valueOf(temStandarApparatus.getNumber()));
////        editValue.setText(String.valueOf(temStandarApparatus.getValue()));
////        editTraceabilityUnit.setText(String.valueOf(temStandarApparatus.getTraceabilityUnit()));
////        editTime.setText(temStandarApparatus.getTime());
////
////        // String findID= String.valueOf( standardApparatusBean.getID());
////
////
////
////
////        AlertDialog.Builder builder=new AlertDialog.Builder(TemStandardApparatusActivity.this);
////        builder.setTitle("修改标准器")
////                .setView(editView)
////                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
////                    @Override
////                    public void onClick(DialogInterface dialogInterface, int i) {
////                        String name=editName.getText().toString();
////                        int port =Integer.valueOf( editPort.getText().toString());
////
////                        String format=editFormat.getText().toString();
////                        int rate=Integer.valueOf(editRate.getText().toString());
////
////
////
////                        String type=editType.getText().toString();
////                        String model=editModel.getText().toString();
////                        String agreement=editAgreement.getText().toString();
////                        String number=editNumber.getText().toString();
////                        String value=editValue.getText().toString();
////                        String traceabilityUnit=editTraceabilityUnit.getText().toString();
////                        String time=editTime.getText().toString();
////
////
////                        if(name==""){
////                            showToast("名称不能为空");
////                        }if(port==0){
////                            showToast("端口不能为0");
////                        }if(rate==0){
////                            showToast("速率不能为0");
////                        }
////                        else {
////                            TemStandarApparatus temStandarApparatus1=temStandardApparatusDBHelper.findByName(name);
////                            if(temStandarApparatus1.getName()==null){
////                                TemStandarApparatus temStandarApparatus2=new TemStandarApparatus();
////                                temStandarApparatus2.setName(name);
////                                temStandarApparatus2.setPort(port);
////                                temStandarApparatus2.setFormat(format);
////                                temStandarApparatus2.setRate(rate);
////                                temStandarApparatus2.setType(type);
////                                temStandarApparatus2.setModel(model);
////                                temStandarApparatus2.setAgreement(agreement);
////                                temStandarApparatus2.setNumber(number);
////                                temStandarApparatus2.setValue(value);
////                                temStandarApparatus2.setTraceabilityUnit(traceabilityUnit);
////                                temStandarApparatus2.setTime(time);
////                                if( temStandardApparatusDBHelper.update(temStandarApparatus2)){
////                                    showToast("标准器修改成功");
////                                }else{
////                                    showToast("标准器修改失败");
////                                }
////                            }else{
////                                showToast("该标准器已经存在，不能修改");
////                            }
////                        }
////
////                    }
////                })
////                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
////                    @Override
////                    public void onClick(DialogInterface dialogInterface, int i) {
////                        dialogInterface.dismiss();
////                    }
////                });
////        AlertDialog alertDialog=builder.create();
////        alertDialog.show();
////
////    }
//
//
//    private void updateListView(){
//        System.out.println("listview开始改变--->");
//        list=temStandardApparatusDBHelper.query();
//        adapter=new TemStandardApparatusAdapter(list, TemStandardApparatusActivity.this);
//        listView.setAdapter(adapter);
//
//    }
//
//    public void showToast(String msg){
//        Toast.makeText(TemStandardApparatusActivity.this,msg,Toast.LENGTH_SHORT).show();
//    }
//
//
//
//
//    private void addData(){
//        AlertDialog.Builder builder=new AlertDialog.Builder(TemStandardApparatusActivity.this);
//        View dialogView =View.inflate(TemStandardApparatusActivity.this,R.layout.standardapparatus_edit,null);
//        builder.setTitle("添加标准器")
//                .setView(dialogView);
//        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//
//                editName=dialogView.findViewById(R.id.edit_s_name);
//                editPort=dialogView.findViewById(R.id.edit_s_port);
//                editFormat=dialogView.findViewById(R.id.edit_s_format);
//                editRate=dialogView.findViewById(R.id.edit_s_rate);
//                editType=dialogView.findViewById(R.id.edit_s_type);
//                editModel=dialogView.findViewById(R.id.edit_s_model);
//                editAgreement=dialogView.findViewById(R.id.edit_s_agreement);
//                editNumber=dialogView.findViewById(R.id.edit_s_number);
//              //  editValue=dialogView.findViewById(R.id.edit_s_value);
//                editTraceabilityUnit=dialogView.findViewById(R.id.edit_s_traceabilityUnit);
//                editTime=dialogView.findViewById(R.id.edit_s_time);
//                String name=editName.getText().toString();
//
//
//                if(name.equals("")){
//                    Log.d("这里的name不是空格吗", "onClick: ");
//                    showToast("标准器名称不能为空");
//                    return;
//                }
//
//
//
//                if(editPort.getText().toString().equals("")){
//                    showToast("通讯串口不能为空");
//                    return;
//                }
//
//                int port=Integer.valueOf(editPort.getText().toString());
//
//                if(editFormat.getText().toString().equals("")){
//                    showToast("通讯格式不能为空");
//                    return;
//                }
//
//
//                if(editRate.getText().toString().equals("")){
//                    showToast("通讯速率不能为空");
//                    return;
//                }
//                int rate=Integer.valueOf(editRate.getText().toString());
//
//
//
//
//
//
//                String format=editFormat.getText().toString();
//                String type=editType.getText().toString();
//                String model=editModel.getText().toString();
//                String agreement=editAgreement.getText().toString();
//                String number=editNumber.getText().toString();
//                String value=editValue.getText().toString();
//                String traceabilityUnit=editTraceabilityUnit.getText().toString();
//                String time=editTime.getText().toString();
//
//
//
//                TemStandarApparatus temStandarApparatus1=temStandardApparatusDBHelper.findByName(name);
//
//
//                if(temStandarApparatus1.getName()==null){
//                    TemStandarApparatus temStandarApparatus2=new TemStandarApparatus();
//                    temStandarApparatus2.setName(name);
//                    temStandarApparatus2.setPort(port);
//                    temStandarApparatus2.setFormat(format);
//                    temStandarApparatus2.setRate(rate);
//                    temStandarApparatus2.setType(type);
//                    temStandarApparatus2.setModel(model);
//                    temStandarApparatus2.setAgreement(agreement);
//                    temStandarApparatus2.setNumber(number);
//                    temStandarApparatus2.setValue(value);
//                    temStandarApparatus2.setTraceabilityUnit(traceabilityUnit);
//                    temStandarApparatus2.setTime(time);
//
//                    if(temStandardApparatusDBHelper.add(temStandarApparatus2)){
//                        Log.d("添加成功", "onClick: ");
//                        showToast("添加成功");
//                        updateListView();
//                    }else{
//                        Log.d("添加失败", "onClick: ");
//                        showToast("添加失败");
//                    }
//                }else{
//                    showToast("该方案已经存在");
//                }
//
//
//            }
//        })
//                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        dialogInterface.dismiss();
//                    }
//                });
//
//        AlertDialog alertDialog=builder.create();
//        alertDialog.show();
//    }
//
//
//
//    private void updateCheck(int position){
//        TemStandarApparatus temStandarApparatus=(TemStandarApparatus) adapter.getItem(position);
//        AlertDialog.Builder builder=new AlertDialog.Builder(TemStandardApparatusActivity.this);
//        builder.setTitle("选择该标准器？")
//                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        if(temStandardApparatusDBHelper.updateCheck(temStandarApparatus.getID(), globalVariable.getTemStandardID()));
//                        globalVariable.setTemStandardID(temStandarApparatus.getID());
//                        Toast.makeText(TemStandardApparatusActivity.this,"已选择"+temStandarApparatus.getName(),Toast.LENGTH_SHORT).show();
//                        updateListView();
//                    }
//                })
//                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        dialogInterface.dismiss();;
//                    }
//                });
//        AlertDialog alertDialog=builder.create();
//        alertDialog.show();
//
//
//
//
//    }



}