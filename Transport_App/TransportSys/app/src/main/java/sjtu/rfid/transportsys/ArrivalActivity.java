package sjtu.rfid.transportsys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.TextView;

import tools.ConfirmExpandableListAdapter;

public class ArrivalActivity extends AppCompatActivity {

    ExpandableListView mListView;
    ConfirmExpandableListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrival);
        iniActivity();
        iniListView();
    }

    public void iniActivity()
    {
        Intent intent = getIntent();
        TextView title = (TextView) findViewById(R.id.text_title_title);
        int func = intent.getIntExtra("function",0);
        switch (func) {
            case 0:
                title.setText("暂存点收货");
                break;
            case 1:
                title.setText("施工点收货");
                break;
        }
    }

    public void iniListView()
    {
        mListView = (ExpandableListView) findViewById(R.id.listview_confirm_box);
        mAdapter = new ConfirmExpandableListAdapter(this,null,null);
        mListView.setAdapter(mAdapter);
    }
}
