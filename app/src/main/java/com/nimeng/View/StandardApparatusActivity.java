package com.nimeng.View;

import static com.nimeng.View.MainActivity.DATABASE_NAME;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import com.nimeng.Adapter.StandardApparatusAdapter;
import com.nimeng.bean.StandardApparatusBean;
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
public class StandardApparatusActivity  extends BaseAvtivity{
    private Button btn_add;
    private ListView listView;
    private StandardApparatusAdapter adapter;
    private StandardApparatusDBHelper standardApparatusDBHelper;
    private List<StandardApparatusBean> list;
    private EditText editName,editPort,editFormat,editRate,editType,editModel,editAgreement,editNumber,editValue,editTraceabilityUnit,editTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standardapparatus);
        btn_add=findViewById(R.id.standardapparatus_add);
        listView=findViewById(R.id.standardapparatus_list);
        if(list!=null){
            list.clear();
        }
        standardApparatusDBHelper =new StandardApparatusDBHelper(StandardApparatusActivity.this,DATABASE_NAME,null,1);
        //planModel=new PlanModel();
        updateListView();

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addData();
            }
        });






    }

    private void deleteData(int position){
        AlertDialog.Builder builder=new AlertDialog.Builder(StandardApparatusActivity.this);
        builder.setTitle("提示")
                .setMessage("是否删除该标准器")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        StandardApparatusBean standardApparatusBean=(StandardApparatusBean) adapter.getItem(position);
                        String deleteID=String.valueOf( standardApparatusBean.getID());
                        if(standardApparatusDBHelper.delete(deleteID)){
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
        View editView=View.inflate(StandardApparatusActivity.this,R.layout.standardapparatus_edit,null);
        StandardApparatusBean standardApparatusBean=(StandardApparatusBean) adapter.getItem(position);
        editName=editView.findViewById(R.id.edit_s_name);
        editPort=editView.findViewById(R.id.edit_s_port);
        editFormat=editView.findViewById(R.id.edit_s_format);
        editRate=editView.findViewById(R.id.edit_s_rate);
        editType=editView.findViewById(R.id.edit_s_type);
        editModel=editView.findViewById(R.id.edit_s_model);
        editAgreement=editView.findViewById(R.id.edit_s_agreement);
        editNumber=editView.findViewById(R.id.edit_s_number);
        editValue=editView.findViewById(R.id.edit_s_value);
        editTraceabilityUnit=editView.findViewById(R.id.edit_s_traceabilityUnit);
        editTime=editView.findViewById(R.id.edit_s_time);

        editName.setText(standardApparatusBean.getName());
        editPort.setText(String.valueOf( standardApparatusBean.getPort()));
        editFormat.setText( String.valueOf(standardApparatusBean.getFormat()));
        editRate.setText(String.valueOf( standardApparatusBean.getRate()));
        editType.setText(String.valueOf(standardApparatusBean.getType()));
        editModel.setText(String.valueOf(standardApparatusBean.getModel()));
        editAgreement.setText(String.valueOf(standardApparatusBean.getAgreement()));
        editNumber.setText(String.valueOf(standardApparatusBean.getNumber()));
        editValue.setText(String.valueOf(standardApparatusBean.getValue()));
        editTraceabilityUnit.setText(String.valueOf(standardApparatusBean.getTraceabilityUnit()));
        editTime.setText(standardApparatusBean.getTime());

        // String findID= String.valueOf( standardApparatusBean.getID());




        AlertDialog.Builder builder=new AlertDialog.Builder(StandardApparatusActivity.this);
        builder.setTitle("修改标准器")
                .setView(editView)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String name=editName.getText().toString();
                        int port =Integer.valueOf( editPort.getText().toString());

                        String format=editFormat.getText().toString();
                        int rate=Integer.valueOf(editRate.getText().toString());



                        String type=editType.getText().toString();
                        String model=editModel.getText().toString();
                        String agreement=editAgreement.getText().toString();
                        String number=editNumber.getText().toString();
                        String value=editValue.getText().toString();
                        String traceabilityUnit=editTraceabilityUnit.getText().toString();
                        String time=editTime.getText().toString();


                        if(name==""){
                            showToast("名称不能为空");
                        }if(port==0){
                            showToast("端口不能为0");
                        }if(rate==0){
                            showToast("速率不能为0");
                        }
                        else {
                            StandardApparatusBean standardApparatusBean1=standardApparatusDBHelper.findByName(name);
                            if(standardApparatusBean1.getName()==null){
                                StandardApparatusBean standardApparatusBean2=new StandardApparatusBean();
                                standardApparatusBean2.setName(name);
                                standardApparatusBean2.setPort(port);
                                standardApparatusBean2.setFormat(format);
                                standardApparatusBean2.setRate(rate);
                                standardApparatusBean2.setType(type);
                                standardApparatusBean2.setModel(model);
                                standardApparatusBean2.setAgreement(agreement);
                                standardApparatusBean2.setNumber(number);
                                standardApparatusBean2.setValue(value);
                                standardApparatusBean2.setTraceabilityUnit(traceabilityUnit);
                                standardApparatusBean2.setTime(time);
                                if( standardApparatusDBHelper.update(standardApparatusBean2)){
                                    showToast("标准器修改成功");
                                }else{
                                    showToast("标准器修改失败");
                                }
                            }else{
                                showToast("该标准器已经存在，不能修改");
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
        list=standardApparatusDBHelper.query();
        adapter=new StandardApparatusAdapter(list,StandardApparatusActivity.this);
        listView.setAdapter(adapter);
    }

    public void showToast(String msg){
        Toast.makeText(StandardApparatusActivity.this,msg,Toast.LENGTH_SHORT).show();
    }




    private void addData(){
        AlertDialog.Builder builder=new AlertDialog.Builder(StandardApparatusActivity.this);
        View dialogView =View.inflate(StandardApparatusActivity.this,R.layout.standardapparatus_edit,null);
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



                StandardApparatusBean standardApparatusBean=standardApparatusDBHelper.findByName(name);


                if(standardApparatusBean.getName()==null){
                    StandardApparatusBean standardApparatusBean2=new StandardApparatusBean();
                    standardApparatusBean2.setName(name);
                    standardApparatusBean2.setPort(port);
                    standardApparatusBean2.setFormat(format);
                    standardApparatusBean2.setRate(rate);
                    standardApparatusBean2.setType(type);
                    standardApparatusBean2.setModel(model);
                    standardApparatusBean2.setAgreement(agreement);
                    standardApparatusBean2.setNumber(number);
                    standardApparatusBean2.setValue(value);
                    standardApparatusBean2.setTraceabilityUnit(traceabilityUnit);
                    standardApparatusBean2.setTime(time);

                    if(standardApparatusDBHelper.add(standardApparatusBean2)){
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


}