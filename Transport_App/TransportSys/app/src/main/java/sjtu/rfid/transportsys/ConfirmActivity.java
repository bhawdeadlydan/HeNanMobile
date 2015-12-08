package sjtu.rfid.transportsys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.TextView;

import tools.ConfirmExpandableListAdapter;

public class ConfirmActivity extends AppCompatActivity {

    ExpandableListView mListView;
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
        tmpTitle.setText("申领物料");
    }

    public void iniListView() {

        mListView = (ExpandableListView) findViewById(R.id.listview_confirm_box);
        mAdapter = new ConfirmExpandableListAdapter(this,null,null);
        mListView.setAdapter(mAdapter);

    }
}
