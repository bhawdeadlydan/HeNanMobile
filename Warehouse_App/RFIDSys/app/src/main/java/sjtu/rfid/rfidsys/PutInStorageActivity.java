package sjtu.rfid.rfidsys;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rfid.service.ASN;
import rfid.service.Good;
import sjtu.rfid.thread.PutInStorageThread;
import sjtu.rfid.tools.CheckByPosExpandableAdapter;
import sjtu.rfid.tools.PutInStorageExpandableAdapter;
import sjtu.rfid.tools.TitleBar;

public class PutInStorageActivity extends Activity {

    private ExpandableListView sheetListView;
    private PutInStorageExpandableAdapter tmpAdapter;
    private Map<String, Map<String, String>> mPutInStorageDetailList;
    private List<Map<String,String>> mPutInStorageList;
    private TitleBar mTitleBar;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    private PutInStorageThread putInStorageThread;
    private String CNum="EPC201509000000";
    private Good good;
    public String goodPos="";

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==0||msg.obj==null)
                Toast.makeText(getApplicationContext(), "获取信息失败", Toast.LENGTH_SHORT).show();
            good=(Good)msg.obj;
            iniListView(good);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_put_in_storage);
        mPutInStorageDetailList = new HashMap<String, Map<String, String>>();
        mPutInStorageList = new ArrayList<>();
        iniActivity();
        iniEvent();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void iniActivity() {
        mTitleBar = new TitleBar(this,"入库上架");
    }

    public void iniListView(Good good) {
        if(good==null)
            return;

        sheetListView = (ExpandableListView) findViewById(R.id.list_put_in_storage_sheets);
        Map<String,String> map=new HashMap<>();
        map.put("boxCode", CNum);
        map.put("matName", good.getDetail());
        mPutInStorageList.add(map);

        Map<String, String> detailMap = new HashMap<>();
        detailMap.put("matCode", good.getCode());
        detailMap.put("unit", good.getUnit());
        detailMap.put("count", String.valueOf(good.getNum()));
        mPutInStorageDetailList.put(CNum, detailMap);

        tmpAdapter = new PutInStorageExpandableAdapter(this,mPutInStorageDetailList,mPutInStorageList);
        sheetListView.setAdapter(tmpAdapter);

        sheetListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                for (int i = 0; i < tmpAdapter.getGroupCount(); i++) {
                    if (groupPosition != i) {
                        sheetListView.collapseGroup(i);
                    }
                }
            }
        });

    }

    public void iniEvent(){
        Button btnScanLoc=(Button)findViewById(R.id.btn_put_in_storage_scan_loc);
        Button btnScanBox=(Button)findViewById(R.id.btn_put_in_storage_scan_box);
        Button btnClear=(Button)findViewById(R.id.btn_put_in_storage_clear);
        Button btnBind=(Button)findViewById(R.id.btn_put_in_storage_bind);

        btnScanLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView vGoodsPos=(TextView)findViewById(R.id.text_put_in_storage_loc);
                vGoodsPos.setText("A11");
            }
        });
        btnScanBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                putInStorageThread=new PutInStorageThread(handler,CNum);
                putInStorageThread.start();
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPutInStorageDetailList.clear();
                mPutInStorageList.clear();
            }
        });
        btnBind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
}
