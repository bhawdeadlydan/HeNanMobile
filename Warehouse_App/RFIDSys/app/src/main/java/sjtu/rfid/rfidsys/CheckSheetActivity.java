package sjtu.rfid.rfidsys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.apache.log4j.chainsaw.Main;

import java.util.ArrayList;
import java.util.List;

import sjtu.rfid.tools.TitleBar;

public class CheckSheetActivity extends Activity {

    TitleBar mTitle;
    ListView mListView;
    ArrayAdapter<String> mAdapter;

    int checkType = -1;
    int curSelectedPos = -1;

    List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent intent = getIntent();
        checkType = intent.getIntExtra("checkType", MainActivity.CHECK_BY_MAT);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_sheet);


        mTitle = new TitleBar(this,"盘点清单");
        mListView = (ListView)findViewById(R.id.listView_check_sheet);
        list = new ArrayList<>();
        list.add("201604141759");
        list.add("201604141760");
        list.add("201604141761");
        mAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);

        Button refreshBtn = (Button)findViewById(R.id.btn_check_sheet_refresh);
        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListView.setAdapter(mAdapter);
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getApplicationContext(),list.get(position),Toast.LENGTH_SHORT).show();
                //list.remove(position);
                //mAdapter.notifyDataSetChanged();
                curSelectedPos = position;
                Intent intent = new Intent();
                intent.setClass(CheckSheetActivity.this, checkType == MainActivity.CHECK_BY_MAT ? CheckByMatActivity.class : CheckByPosActivity.class);
                if(checkType == MainActivity.CHECK_BY_MAT) {
                    intent.putExtra("matCode","00000001");
                    intent.putExtra("matName","material");
                    intent.putExtra("isBom","否");
                }
                startActivityForResult(intent,0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK)
        {
            if(curSelectedPos >= 0 && curSelectedPos < list.size()) {
                list.remove(curSelectedPos);
                mAdapter.notifyDataSetChanged();
            }
        }
    }
}
