package com.nimeng.View;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.nimeng.contacts.EditProgrammeContacts;

/**
 * Author: wfs
 * <p>
 * Create: 2022/4/13 16:10
 * <p>
 * Changes (from 2022/4/13)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/4/13 : Create EditProgrammerActivity.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class EditProgrammerActivity extends BaseAvtivity <EditProgrammeContacts.EditPresenter> implements EditProgrammeContacts.EditProgrammeUI {
  private Button btn_add;

  @Override
  protected void onCreate(Bundle savedInstanceState){
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_equipment);
      btn_add=findViewById(R.id.bt_add);
      btn_add.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              getmPresenter().addProgrammepresenter("123","123",1,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3);
          }
      });
  }





    @Override
    public void addSuccess() {
        Toast.makeText(this,"方案添加成功",Toast.LENGTH_LONG).show();
    }

    @Override
    public void addError() {
        Toast.makeText(this,"方案添加失败",Toast.LENGTH_LONG).show();
    }
}
