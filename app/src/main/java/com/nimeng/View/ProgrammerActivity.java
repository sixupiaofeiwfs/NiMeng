package com.nimeng.View;

/**
 * Author: wfs
 * <p>
 * Create: 2022/4/27 10:56
 * <p>
 * Changes (from 2022/4/27)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/4/27 : Create ListTableActivity.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.nimeng.Adapter.ProgrammerTableAdapter;
import com.nimeng.Presenter.ProgrammePresenter;
import com.nimeng.bean.ProgrammeBean;
import com.nimeng.contacts.EditProgrammeContacts;

public class ProgrammerActivity extends BaseAvtivity<EditProgrammeContacts.EditPresenter> implements EditProgrammeContacts.EditProgrammeUI,  ProgrammerTableAdapter.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programmer);
        //设置表格标题的背景颜色
        ViewGroup tableTitle = (ViewGroup) findViewById(R.id.table_title);
        tableTitle.setBackgroundColor(Color.rgb(177, 173, 172));

        List<ProgrammeBean> list = new ArrayList<ProgrammeBean>();
        list.add(new ProgrammeBean("01", "方案1",10, 0.01f,0.5f,20.0f,60.0f,80.0f,20.0f,40.0f,60.0f));
        list.add(new ProgrammeBean("02", "方案2",10, 0.01f,0.5f,20.0f,60.0f,80.0f,20.0f,40.0f,60.0f));
        list.add(new ProgrammeBean("03", "方案3",10, 0.01f,0.5f,20.0f,60.0f,80.0f,20.0f,40.0f,60.0f));
        list.add(new ProgrammeBean("04", "方案4",10, 0.01f,0.5f,20.0f,60.0f,80.0f,20.0f,40.0f,60.0f));
        list.add(new ProgrammeBean("05", "方案5",10, 0.01f,0.5f,20.0f,60.0f,80.0f,20.0f,40.0f,60.0f));
        list.add(new ProgrammeBean("06", "方案6",10, 0.01f,0.5f,20.0f,60.0f,80.0f,20.0f,40.0f,60.0f));
        list.add(new ProgrammeBean("07", "方案7",10, 0.01f,0.5f,20.0f,60.0f,80.0f,20.0f,40.0f,60.0f));

        ListView tableListView = (ListView) findViewById(R.id.list);

        ProgrammerTableAdapter adapter = new ProgrammerTableAdapter(this, list,this);

        tableListView.setAdapter(adapter);


      //  getmPresenter().deleteProgrammePrsenter();
    }


    @Override
    public void addSuccess() {


        Toast.makeText(this,"方案添加成功",Toast.LENGTH_LONG).show();
    }

    @Override
    public void addError() {
        Toast.makeText(this,"方案添加失败",Toast.LENGTH_LONG).show();
    }


    @Override
    public void click(String ID) {
        Log.d(ID, "click:----------------- ");
      //  getmPresenter().deleteProgrammePrsenter(ID);
    }
}
