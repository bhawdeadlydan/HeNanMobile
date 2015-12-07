package sjtu.rfid.transportsys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.TextView;

import tools.ConfirmExpandableListAdapter;

public class ConfirmActivity extends AppCompatActivity {

    ExpandableListView sheetListView;
    ConfirmExpandableListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        iniListView();
        iniActivity();
    }

    public void iniActivity()
    {
        TextView tmpTitle = (TextView) findViewById(R.id.text_title_title);
        TextView tmpcurPos = (TextView) findViewById(R.id.text_confirm_cur_pos);
        Intent intent = getIntent();
        int func = intent.getIntExtra("function",0);
        switch (func) {
            case 1:
                tmpTitle.setText("申领物料");
                break;
            case 2:
                tmpTitle.setText("货物抵达暂存点");
                break;
            case 3:
                tmpTitle.setText("货物抵达施工点");
                break;
        }
        tmpcurPos.setText("当前位置：某个地点");
    }

    public void iniListView() {

        sheetListView = (ExpandableListView) findViewById(R.id.listview_confirm_box);
        mAdapter = new ConfirmExpandableListAdapter(this,null,null);
        sheetListView.setAdapter(mAdapter);

    }
}
