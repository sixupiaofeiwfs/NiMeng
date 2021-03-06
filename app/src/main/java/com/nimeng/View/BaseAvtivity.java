package com.nimeng.View;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.nimeng.Presenter.IBasePresenter;

/**
 * Author: wfs
 * <p>
 * Create: 2022/4/13 14:04
 * <p>
 * Changes (from 2022/4/13)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/4/13 : Create BaseAvtivity.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class BaseAvtivity <P extends IBasePresenter> extends BaseXActivity<P> implements IBaseView{

    private static final int REQUEST_CODE =1024 ;
    //加载进度框
    private ProgressDialog mProgressDialog;

    //手指上下滑动时的最小速度
    private static final int YSPEED_MIN=1000;
    //手指向右滑动的最小距离
    private static  final int XDISTANCE_MIN=50;
    //手指向上滑动或向下滑动的最小距离
    private static  final int YDISTANCE_MIN=66;
    private static final String TAG="BaseActivity";
    //记录手指按下时的横坐标
    private float xDown;
    //记录手指按下时的纵坐标
    private float yDown;
    //记录手指移动时的横坐标;
    private float xMove;
    //记录手指移动时的纵坐标
    private float yMove;
    //用于计算手指滑动的速度
    private VelocityTracker velocityTracker;


    private String activityName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: BaseActivity-onCreate");
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        createVelocityTracker(ev);
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                xDown=ev.getRawX();
                yDown=ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                xMove=ev.getRawX();
                yMove=ev.getRawY();
                //滑动的距离
                int distanceX=(int)(xMove-xDown);
                int distanceY=(int)(yMove-yDown);
                //获取瞬时速度
                int ySpeed=getScrollVelocity();
                //关闭activity需要满足一下条件
                //x轴滑动的距离大于XDISTANCE_MIN
                //y轴滑动的距离大于YDISTANCE_MIN
                //y轴上(即上下滑动的速度)<YSPEED_MIN,如果大于,则认为用户意图时上下滑动而非左右滑动



                if(distanceX>XDISTANCE_MIN && (distanceY<YDISTANCE_MIN && distanceY >-YDISTANCE_MIN) && ySpeed<YSPEED_MIN && distanceX>0){
                    if(this.getClass().getName().equals("com.nimeng.View.PlanActivity")){
                        startActivity(new Intent(this,MainActivity.class));
                    }else if(this.getClass().getName().equals("com.nimeng.View.DataRecordActivity")){
                        startActivity(new Intent(this,LineChartActivity.class));
                    }else if(this.getClass().getName().equals("com.nimeng.View.StandardApparatusActivity")){
                        startActivity(new Intent(this,DataRecordActivity.class));
                    }else if(this.getClass().getName().equals("com.nimeng.View.CameraActivity")){
                        startActivity(new Intent(this,StandardApparatusActivity.class));
                    }else if(this.getClass().getName().equals("com.nimeng.View.SettingSwitchActivity")){
                        startActivity(new Intent(this,CameraActivity.class));
                    }else if(this.getClass().getName().equals("com.nimeng.View.MainActivity")){
                        startActivity(new Intent(this,ComprehensiveDataActivity.class));
                    }else if(this.getClass().getName().equals("com.nimeng.View.TurntableActivity")){
                        startActivity(new Intent(this,SettingSwitchActivity.class));
                    }else if(this.getClass().getName().equals("com.nimeng.View.ComprehensiveDataActivity")){
                        startActivity(new Intent(this,TurntableActivity.class));
                    }

                }else if (-distanceX>XDISTANCE_MIN && (distanceY<YDISTANCE_MIN && distanceY >-YDISTANCE_MIN) && ySpeed<YSPEED_MIN && -distanceX>0){

                    if(this.getClass().getName().equals("com.nimeng.View.MainActivity")){
                        startActivity(new Intent(this, LineChartActivity.class));
                    }else if(this.getClass().getName().equals("com.nimeng.View.DataRecordActivity")){
                        startActivity(new Intent(this,StandardApparatusActivity.class));
                    }else if(this.getClass().getName().equals("com.nimeng.View.StandardApparatusActivity")){
                        startActivity(new Intent(this,CameraActivity.class));
                    }else if(this.getClass().getName().equals("com.nimeng.View.CameraActivity")){
                        startActivity(new Intent(this,SettingSwitchActivity.class));
                    }else if(this.getClass().getName().equals("com.nimeng.View.SettingSwitchActivity")) {
                        startActivity(new Intent(this, TurntableActivity.class));
                    }

                }
                break;
            case MotionEvent.ACTION_UP:
                recycleVelocityTracker();
                break;
            default:
                break;

        }
        return super.dispatchTouchEvent(ev);
    }

    //创建VelocityTracker对象,并将触摸界面的滑动事件添加到VelocityTracker中
    private void createVelocityTracker(MotionEvent event){
        if (velocityTracker==null){
            velocityTracker=VelocityTracker.obtain();
        }
        velocityTracker.addMovement(event);
    }
    //回收VelocityTracker对象
    private void recycleVelocityTracker(){
        velocityTracker.recycle();
        velocityTracker=null;
    }
    //滑动速度,以每秒钟移动了多少像素值为单位
    private int getScrollVelocity(){
        velocityTracker.computeCurrentVelocity(1000);
        int velocity=(int)velocityTracker.getYVelocity();
        return Math.abs(velocity);
    }




    @Override
    public void showLoading(){

    }

    @Override
    public void hideLoading(){

    }

    @Override
    public void showToast(String msg){

    }

    @Override
    public P onBindPresenter() {
        return null;
    }

    @Override
    public void onDestroy(){
        hideLoading();
        super.onDestroy();
    }



    // 获取存储权限
    public void onPermission(View v){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // 先判断有没有权限
            if (Environment.isExternalStorageManager()) {
                requestSuccess();
            } else {
                Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                intent.setData(Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, REQUEST_CODE);
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // 先判断有没有权限
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                requestSuccess();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
            }
        } else {
            requestSuccess();
        }
    }



    /**
     * 模拟文件写入
     */
    private void requestSuccess() {
        Toast.makeText(this, "存储权限获取成功", Toast.LENGTH_SHORT).show();
    }

}
