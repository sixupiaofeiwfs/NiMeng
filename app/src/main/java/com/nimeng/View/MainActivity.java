package com.nimeng.View;

import android.os.Bundle;
import android.view.GestureDetector;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.nimeng.flash.FlashView;
import com.nimeng.flash.VirtualBarUtil;

public class MainActivity extends BaseAvtivity{

    private static final int MIN_DISTANCE=100;//最小滑动距离
    private GestureDetector gestureDetector;
    private Button btn_xsdh;

    private TextView mProgressTv;
    private SeekBar mTemSeekBar;
    private SeekBar mHumSeekBar;
    private FlashView mTemView;
    private FlashView mHumView;


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

        mTemView=findViewById(R.id.wd);
        mHumView=findViewById(R.id.sd);
     //   mProgressTv=findViewById(R.id.seek_tv);
        mTemSeekBar=findViewById(R.id.TemSeekBar);
        mTemSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
              //  mProgressTv.setText(i+"%");
                mTemView.setProgress(i,"tem");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        mHumSeekBar=findViewById(R.id.HumSeekBar);
        mHumSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mHumView.setProgress(i,"hum");
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





