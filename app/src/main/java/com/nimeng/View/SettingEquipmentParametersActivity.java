package com.nimeng.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

/**
 * Author: wfs
 * <p>
 * Create: 2022/6/27 16:55
 * <p>
 * Changes (from 2022/6/27)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/6/27 : Create SettingEquipmentParametersActivity.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class SettingEquipmentParametersActivity extends BaseAvtivity{

    private TextView textView1,textView2;
    private Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_equipment_parameters);


        textView1=findViewById(R.id.textview_xtsz);
        textView2=findViewById(R.id.textview_fqfk);

        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent=new Intent(SettingEquipmentParametersActivity.this,SettingSwitchActivity.class);
                startActivity(intent);
            }
        });


        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent=new Intent(SettingEquipmentParametersActivity.this,InstallermentActivity.class);
                startActivity(intent);
            }
        });

    }
}
