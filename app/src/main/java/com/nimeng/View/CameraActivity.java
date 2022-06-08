package com.nimeng.View;

import static com.nimeng.View.MainActivity.DATABASE_NAME;

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

import com.nimeng.Adapter.CameraAdapter;

import com.nimeng.bean.CameraBean;
import com.nimeng.util.CameraDBHelper;


import java.util.List;

/**
 * Author: wfs
 * <p>
 * Create: 2022/6/21 16:12
 * <p>
 * Changes (from 2022/6/21)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/6/21 : Create CameraActivity.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class CameraActivity extends BaseAvtivity{
    private Button btn_add;
    private ListView listView;
    private EditText editName,editPort;
    private CameraAdapter cameraAdapter;
    private CameraDBHelper cameraDBHelper;
    private List<CameraBean> list;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        btn_add=findViewById(R.id.camera_add);
        listView=findViewById(R.id.camera_list);
        if(list!=null){
            list.clear();
        }
        cameraDBHelper=new CameraDBHelper(CameraActivity.this,DATABASE_NAME,null,1);
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
        AlertDialog.Builder builder=new AlertDialog.Builder(CameraActivity.this);
        View dialogView =View.inflate(CameraActivity.this,R.layout.camera_edit,null);
        builder.setTitle("添加相机")
                .setView(dialogView);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                editName=dialogView.findViewById(R.id.edit_c_name);
                editPort=dialogView.findViewById(R.id.edit_c_port);


                String name=editName.getText().toString();


                if(name.equals("")){
                    Log.d("这里的name不是空格吗", "onClick: ");
                    showToast("相机名称不能为空");
                    return;
                }



                if(editPort.getText().toString().equals("")){
                    showToast("端口不能为空");
                    return;
                }

                String port=editPort.getText().toString();

                CameraBean cameraBean=cameraDBHelper.findByName(name);


                if(cameraBean.getName()==""){
                    CameraBean cameraBean2=new CameraBean();
                    cameraBean2.setName(name);
                    cameraBean2.setPort(port);


                    if(cameraDBHelper.add(cameraBean2)){
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
        AlertDialog.Builder builder=new AlertDialog.Builder(CameraActivity.this);
        builder.setTitle("提示")
                .setMessage("是否删除该方案")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        CameraBean cameraBean=(CameraBean) cameraAdapter.getItem(position);
                        String deleteID=String.valueOf( cameraBean.getID());
                        if(cameraDBHelper.delete(deleteID)){
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
        View editView=View.inflate(CameraActivity.this,R.layout.camera_edit,null);
        CameraBean cameraBean=(CameraBean) cameraAdapter.getItem(position);
        editName=editView.findViewById(R.id.edit_c_name);
        editPort=editView.findViewById(R.id.edit_c_port);


        editName.setText(cameraBean.getName());
        editPort.setText(String.valueOf( cameraBean.getPort()));
        String findID= String.valueOf( cameraBean.getID());




        AlertDialog.Builder builder=new AlertDialog.Builder(CameraActivity.this);
        builder.setTitle("修改方案")
                .setView(editView)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String name=editName.getText().toString();
                        String port=editPort.getText().toString();

                        if(name==""){
                            showToast("名称不能为空");
                        }if(port==""){
                            showToast("端口不能为空");
                        }
                        else {
                            CameraBean cameraBean1=cameraDBHelper.findByName(name);
                            if(cameraBean1.getName()==""){
                                CameraBean cameraBean2=new CameraBean();
                                cameraBean2.setName(name);
                                cameraBean2.setPort(port);

                                if( cameraDBHelper.update(cameraBean2)){
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
        list=cameraDBHelper.query();
        cameraAdapter=new CameraAdapter(list,CameraActivity.this);
        listView.setAdapter(cameraAdapter);
    }

    public void showToast(String msg){
        Toast.makeText(CameraActivity.this,msg,Toast.LENGTH_SHORT).show();
    }



}

