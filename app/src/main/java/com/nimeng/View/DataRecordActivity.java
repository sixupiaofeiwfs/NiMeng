package com.nimeng.View;

import static com.nimeng.View.MainActivity.DATABASE_NAME;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.nimeng.Adapter.DataRecordAdapter;
import com.nimeng.bean.DataRecodeBean;
import com.nimeng.util.DataRecordDBHelper;

import java.util.List;

/**
 * Author: wfs
 * <p>
 * Create: 2022/6/14 9:18
 * <p>
 * Changes (from 2022/6/14)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/6/14 : Create DataRecordActivity.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class DataRecordActivity extends BaseAvtivity {


    private ListView listView;
    private DataRecordAdapter adapter;
    private DataRecordDBHelper dataRecordDBHelper;
    private List<DataRecodeBean> list;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datarecord);
        listView=findViewById(R.id.datarecord_list);
        if(list!=null){
            list.clear();
        }
        dataRecordDBHelper=new DataRecordDBHelper(DataRecordActivity.this,DATABASE_NAME,null,1);
        //planModel=new PlanModel();
        updateListView();

    }




    public void updateListView(){
        list=dataRecordDBHelper.query();

        Log.d("查询到的数据——————————", "updateListView: "+list.size());

        adapter=new DataRecordAdapter(list,DataRecordActivity.this);
        listView.setAdapter(adapter);
    }

    public void showToast(String msg){
        Toast.makeText(DataRecordActivity.this,msg,Toast.LENGTH_SHORT).show();
    }



}
