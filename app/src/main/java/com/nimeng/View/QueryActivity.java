package com.nimeng.View;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.annotation.Nullable;


import com.nimeng.Adapter.QueryAdapter;
import com.nimeng.bean.QueryBean;
import com.nimeng.util.QueryDBHelper;

import java.util.List;

/**
 * Author: wfs
 * <p>
 * Create: 2022/7/1 15:40
 * <p>
 * Changes (from 2022/7/1)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/7/1 : Create QueryActivity.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class QueryActivity  extends BaseAvtivity{
    private ListView listView;
    private QueryAdapter queryAdapter;
    private QueryDBHelper queryDBHelper;
    private List<QueryBean> list;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);
        listView=findViewById(R.id.query_list);
        if(list!=null){
            list.clear();
        }
        queryDBHelper=new QueryDBHelper(QueryActivity.this,"NIMENG.db",null,1);
        updateListView();



    }


    public void updateListView(){
        list=queryDBHelper.query();

        Log.d("list中是否有数据", "updateListView: "+list.size());

        queryAdapter=new QueryAdapter(list,QueryActivity.this);
        listView.setAdapter(queryAdapter);
    }


}
