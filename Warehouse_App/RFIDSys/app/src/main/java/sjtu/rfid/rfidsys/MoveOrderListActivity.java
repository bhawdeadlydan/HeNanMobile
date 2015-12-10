package sjtu.rfid.rfidsys;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import sjtu.rfid.tools.MoveOrderAdapter;
import sjtu.rfid.tools.TitleBar;

public class MoveOrderListActivity extends Activity {

    private TitleBar mTitleBar;
    private MoveOrderAdapter mAdapter;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_order_list);
        iniActivity();
        iniListView();
    }

    public void iniActivity() {
        mTitleBar = new TitleBar(this,"货物移位");
    }

    public void iniListView() {
        mListView = (ListView) findViewById(R.id.list_move_order_sheets);
        mAdapter = new MoveOrderAdapter(this,null);
        mListView.setAdapter(mAdapter);
    }
}
