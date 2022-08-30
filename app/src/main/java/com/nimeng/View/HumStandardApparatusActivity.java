package com.nimeng.View;

import static com.nimeng.View.MainActivity.DATABASE_NAME;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.nimeng.Adapter.StandardApparatusAdapter;
import com.nimeng.bean.StandardApparatus;
import com.nimeng.util.CommonUtil;
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
public class HumStandardApparatusActivity extends CommonUtil {
    private Button btn1,btn2;
    private ListView listView;

    private StandardApparatusDBHelper standardApparatusDBHelper;
    private StandardApparatusAdapter standardApparatusAdapter;
    private List<StandardApparatus> list;
    Intent intent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_humstandardapparatus);
        btn1=findViewById(R.id.standardapparatus_btn1);
        btn2=findViewById(R.id.standardapparatus_btn2);
        listView=findViewById(R.id.humstandardapparatus_list);
        String tableName="humstandardapparatus";
        if(list!=null){
            list.clear();
        }

        standardApparatusDBHelper =new StandardApparatusDBHelper(HumStandardApparatusActivity.this,DATABASE_NAME,null,1);
        list=standardApparatusDBHelper.query(tableName);
        standardApparatusAdapter =new StandardApparatusAdapter(list,HumStandardApparatusActivity.this);
        listView.setAdapter(standardApparatusAdapter);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HumStandardApparatusActivity.this,StandardappratusEditActivity.class);
                intent.putExtra("tableName",tableName);
                startActivity(intent);
              //  addData(HumStandardApparatusActivity.this,standardApparatusDBHelper,tableName,standardApparatusAdapter,listView);
            }
        });


        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent=new Intent(HumStandardApparatusActivity.this,TemStandardApparatusActivity.class);
                startActivity(intent);
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                updateCheck(HumStandardApparatusActivity.this,standardApparatusAdapter,standardApparatusDBHelper,tableName,i,listView);
            }
        });


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                deleteData(tableName,standardApparatusDBHelper,standardApparatusAdapter,HumStandardApparatusActivity.this,i,listView);
                return true;
            }
        });




    }


}