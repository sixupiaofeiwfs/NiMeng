package com.nimeng.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.nimeng.flash.HwChargingView;
import com.nimeng.flash.VirtualBarUtil;

public class MainActivity extends BaseAvtivity{

    private static final int MIN_DISTANCE=100;//最小滑动距离
    private GestureDetector gestureDetector;
    private Button btn_xsdh;

    private TextView mProgressTv;
    private SeekBar mProgressSb;
    private HwChargingView mHwChargingView;
    private HwChargingView mHwChargingView2;

    @Override
    public  void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //判断是否需要隐藏底部的虚拟按键
        if(VirtualBarUtil.hasNavBar(this)){
            VirtualBarUtil.hideBottomUIMenu(this);
        }
        setContentView(R.layout.activity_main);

        mHwChargingView=findViewById(R.id.hw_chargingwd);
       //mHwChargingView2=findViewById(R.id.hw_chargingsd);
        mProgressTv=findViewById(R.id.seek_tv);
        mProgressSb=findViewById(R.id.seekBar);
        mProgressSb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mProgressTv.setText(i+"%");
                mHwChargingView.setProgress(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



    }





}





