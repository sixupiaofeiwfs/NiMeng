package com.nimeng.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HumPidActivity extends BaseAvtivity{
    Button temmpid_btn,humpid_toSettingSwitch;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_humpid);


        temmpid_btn=findViewById(R.id.tempid);
        humpid_toSettingSwitch=findViewById(R.id.humpid_toSettingSwitch);

        temmpid_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent=new Intent(HumPidActivity.this,TemPidActivity.class);
                startActivity(intent);
            }
        });


        humpid_toSettingSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent=new Intent(HumPidActivity.this,SettingSwitchActivity.class);
                startActivity(intent);
            }
        });

    }

}
