package com.nimeng.View;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.annotation.Nullable;

import com.nimeng.Adapter.ComprehensiveDataAdapter;
import com.nimeng.bean.ComprehensiveDataBean;
import com.nimeng.util.ComprehensiveDataDBHelper;

import java.util.List;

/**
 * Author: wfs
 * <p>
 * Create: 2022/6/24 10:01
 * <p>
 * Changes (from 2022/6/24)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/6/24 : Create ComprehensiveDataActivity.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class ComprehensiveDataActivity extends BaseAvtivity{
    private ListView listView;
    private ComprehensiveDataAdapter adapter;
    private ComprehensiveDataDBHelper comprehensiveDataDBHelper;
    private List<ComprehensiveDataBean> list;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprehensive_data_redord);
        listView=findViewById(R.id.comprehensivedata_list);
        if(list!=null){
            list.clear();
        }
        comprehensiveDataDBHelper=new ComprehensiveDataDBHelper(ComprehensiveDataActivity.this,"NIMENG.db",null,1);
        updateListView();



    }


    public void updateListView(){
        list=comprehensiveDataDBHelper.query();

        Log.d("list中是否有数据", "updateListView: "+list.size());

        adapter=new ComprehensiveDataAdapter(list,ComprehensiveDataActivity.this);
        listView.setAdapter(adapter);
    }







}
