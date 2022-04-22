package com.nimeng.View;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.nimeng.Adapter.EquipmentAdapter;
import com.nimeng.Presenter.EquipmentPresenter;
import com.nimeng.bean.EquipmentBean;

import java.util.Date;
import java.util.List;

/**
 * Author: wfs
 * <p>
 * Create: 2022/4/7 15:23
 * <p>
 * Changes (from 2022/4/7)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/4/7 : Create EquipmentActivity.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class EquipmentActivity  extends BaseAvtivity implements IEquipmentView {
    private EquipmentPresenter equipmentPresenter;
    private EditText mEquipmentName;//设备名称控件
    private EditText mEquipmentType;//设备类型控件
    private EditText mEquipmentIP;//设备IP控件
    private EditText mEquipmentTime;//设备出厂时间控件
    private Button mBtnAdd;//添加按钮控件
    private Button mBtnDelete;//删除按钮控件
    private Button mBtnBack;//返回按钮控件
    private ProgressBar mProBar;//进度条




    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment);


        //初始化控件
        initview();

        Log.d("0", "onCreate: ");

    }










    private void initevent() {
        //添加按钮响应事件  当点击添加按钮时触发该事件
        mBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //给P层发送指令,执行添加操作
                equipmentPresenter.add();
            }
        });


        //删除响应事件
        mBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //equipmentPresenter.deleteEquipment();//给P层发送指令,执行删除操作
            }
        });



    }

    private void initview() {
        mBtnAdd=(Button) findViewById(R.id.bt_add);
        mBtnDelete=(Button) findViewById(R.id.bt_delete);
    }

    @Override
    public void success(EquipmentBean equipmentBean,String tag){
        Toast.makeText(this,"设备"+equipmentBean.getName()+tag+"成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void error(EquipmentBean equipmentBean,String tag){
        Toast.makeText(this,"设备"+equipmentBean.getName()+tag+"失败",Toast.LENGTH_SHORT).show();
    }


    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getID() {
        return null;
    }

    @Override
    public String getIP() {
        return null;
    }

    @Override
    public String getType() {
        return null;
    }

    @Override
    public Date getTime() {
        return null;
    }

    @Override
    public boolean getSwitch1() {
        return false;
    }

    @Override
    public boolean getSwitch2() {
        return false;
    }

    @Override
    public boolean getSwitch3() {
        return false;
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
