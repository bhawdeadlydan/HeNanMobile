package sjtu.rfid.transportsys;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import rfid.service.inStagingInfo;
import rfid.service.stagingInfo;
import sjtu.rfid.entity.TmpSearchEntity;
import sjtu.rfid.thread.TmpSearchThread;
import tools.SearchTmpInListAdapter;
import tools.SearchTmpOutListAdapter;

public class TmpSearchActivity extends Activity {

    EditText editText;
    ListView listViewIn;
    ListView listViewOut;
    Button btnSearch;

    private List<inStagingInfo> in;
    private List<stagingInfo> out;
    SearchTmpInListAdapter mAdapterIn;
    SearchTmpOutListAdapter mAdapterOut;


    private TmpSearchEntity tmpSearchEntity;
    private Handler handler=new Handler(){

        @Override
        public void handleMessage(Message msg) {
            if(msg.what==0||msg.obj==null)
                Toast.makeText(getApplicationContext(), "获取信息失败", Toast.LENGTH_SHORT).show();
            else if (msg.what==1){
                tmpSearchEntity=(TmpSearchEntity)msg.obj;
                iniListView(tmpSearchEntity);
                mAdapterIn.notifyDataSetChanged();
                mAdapterOut.notifyDataSetChanged();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tmp_search);

        iniActivity();
    }

    public  void iniActivity() {
        ((Button)findViewById(R.id.btn_title_back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ((TextView)findViewById(R.id.text_title)).setText("暂存点查询");

        listViewIn = (ListView)findViewById(R.id.listview_tmp_search_in);
        listViewOut = (ListView)findViewById(R.id.listview_tmp_search_out);
        editText = (EditText)findViewById(R.id.edittext_tmp_search);
        btnSearch = (Button)findViewById(R.id.btn_tmp_search);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search = editText.getText().toString();
                TmpSearchThread tmpSearchThread = new TmpSearchThread(search, handler);
                tmpSearchThread.start();
            }
        });
    }

    public void iniListView(TmpSearchEntity tmpSearchEntity){

        in=tmpSearchEntity.getIn();
        out=tmpSearchEntity.getOut();
        mAdapterIn = new SearchTmpInListAdapter(this,in);
        mAdapterOut = new SearchTmpOutListAdapter(this,out);
        listViewIn.setAdapter(mAdapterIn);
        listViewOut.setAdapter(mAdapterOut);
    }
}
