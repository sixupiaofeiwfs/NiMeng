package com.nimeng.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.nimeng.Presenter.ProgrammePresenter;
import com.nimeng.View.R;
import com.nimeng.bean.ProgrammeBean;
import com.nimeng.contacts.EditProgrammeContacts;

import java.util.List;

import javax.security.auth.callback.Callback;

/**
 * Author: wfs
 * <p>
 * Create: 2022/4/27 10:58
 * <p>
 * Changes (from 2022/4/27)
 * <p>
 * -----------------------------------------------------------------
 * <p>
 * 2022/4/27 : Create TableAdapter.java (wfs);
 * <p>
 * -----------------------------------------------------------------
 */
public class ProgrammerTableAdapter extends BaseAdapter {

    private List<ProgrammeBean> list;
    private LayoutInflater inflater;


    private ProgrammePresenter programmePresenter;


    public ProgrammerTableAdapter(Context context, List<ProgrammeBean> list){
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        int ret = 0;
        if(list!=null){
            ret = list.size();
        }
        return ret;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ProgrammeBean programmeBean=(ProgrammeBean) this.getItem(position);

        ViewHolder viewHolder;

        if(convertView == null){

            viewHolder = new ViewHolder();

            convertView = inflater.inflate(R.layout.list_programmer, null);
            viewHolder.programmerCode = (TextView) convertView.findViewById(R.id.programmerCode);
            viewHolder.ProgrammerName = (TextView) convertView.findViewById(R.id.ProgrammerName);
            viewHolder.ProgrammerTime = (TextView) convertView.findViewById(R.id.ProgrammerTime);
            viewHolder.ProgrammerTemWave = (TextView) convertView.findViewById(R.id.ProgrammerTemWave);
            viewHolder.programmerHumWave = (TextView) convertView.findViewById(R.id.programmerHumWave);
            viewHolder.programmerTem1 = (TextView) convertView.findViewById(R.id.programmerTem1);
            viewHolder.programmerTem2 = (TextView) convertView.findViewById(R.id.programmerTem2);
            viewHolder.programmerTem3 = (TextView) convertView.findViewById(R.id.programmerTem3);
            viewHolder.programmerHum1 = (TextView) convertView.findViewById(R.id.programmerHum1);
            viewHolder.proGrammerHum2 = (TextView) convertView.findViewById(R.id.proGrammerHum2);
            viewHolder.programmerHum3 = (TextView) convertView.findViewById(R.id.programmerHum3);
            viewHolder.programmerMenu=(Button)convertView.findViewById(R.id.programmerMenu);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.programmerCode.setText(programmeBean.getId());
        viewHolder.programmerCode.setTextSize(13);
        viewHolder.ProgrammerName.setText(programmeBean.getName());
        viewHolder.ProgrammerName.setTextSize(13);
        viewHolder.ProgrammerTime.setText(programmeBean.getTime()+"");
        viewHolder.ProgrammerTime.setTextSize(13);
        viewHolder.ProgrammerTemWave.setText(programmeBean.getTem_wave()+"");
        viewHolder.ProgrammerTemWave.setTextSize(13);
        viewHolder.programmerHumWave.setText(programmeBean.getHum_wave()+"");
        viewHolder.programmerHumWave.setTextSize(13);
        viewHolder.programmerTem1.setText(programmeBean.getTemperature1()+"");
        viewHolder.programmerTem1.setTextSize(13);
        viewHolder.programmerTem2.setText(programmeBean.getTemperature2()+"");
        viewHolder.programmerTem2.setTextSize(13);
        viewHolder.programmerTem3.setText(programmeBean.getTemperature3()+"");
        viewHolder.programmerTem3.setTextSize(13);
        viewHolder.programmerHum1.setText(programmeBean.getHumidity1()+"");
        viewHolder.programmerHum1.setTextSize(13);
        viewHolder.proGrammerHum2.setText(programmeBean.getHumidity2()+"");
        viewHolder.proGrammerHum2.setTextSize(13);
        viewHolder.programmerHum3.setText(programmeBean.getHumidity3()+"");
        viewHolder.programmerHum3.setTextSize(13);

        viewHolder.programmerMenu.setText("删除");
        viewHolder.programmerHum3.setTextSize(13);
        viewHolder.programmerMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                programmePresenter=new ProgrammePresenter((EditProgrammeContacts.EditProgrammeUI) view);
                programmePresenter.deleteProgrammePrsenter(programmeBean.getId());

            }
        });




        return convertView;
    }

    public static class ViewHolder{
        public TextView programmerCode;
        public TextView ProgrammerName;
        public TextView ProgrammerTime;
        public TextView ProgrammerTemWave;
        public TextView programmerHumWave;
        public TextView programmerTem1;
        public TextView programmerTem2;
        public TextView programmerTem3;
        public TextView programmerHum1;
        public TextView proGrammerHum2;
        public TextView programmerHum3;
        public Button   programmerMenu;
    }

}