package sjtu.rfid.rfidsys;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rfid.service.ASN;
import rfid.service.POS;
import sjtu.rfid.thread.DeliverySheetsThread;
import sjtu.rfid.thread.ReceivingSheetsThread;
import sjtu.rfid.tools.ConnectServer;
import sjtu.rfid.tools.DeliverySheetsExpandableAdapter;
import sjtu.rfid.tools.TitleBar;

public class DeliverySheetsActivity extends Activity {
    private ExpandableListView sheetListView;
    private DeliverySheetsExpandableAdapter tmpAdapter;
    private Map<String, Map<String, String>> mDeliveryCodeDetailList;
    private List<String> mDeliveryCodeList;
    private TitleBar mTitleBar;

    private DeliverySheetsThread deliverySheetsThread;
    private List<POS> posList;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==0||msg.obj==null)
                Toast.makeText(getApplicationContext(), "获取信息失败", Toast.LENGTH_SHORT).show();
            posList=(List<POS>)msg.obj;
            //iniListView(posList);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_sheets);
        iniActivity();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

    }

    @Override
    protected void onResume() {
        super.onResume();
        ConnectServer connectServer=new ConnectServer();
        if(connectServer.isNetworkAvailable(this)){
            deliverySheetsThread=new DeliverySheetsThread(handler);
            deliverySheetsThread.start();
        }else{
            Toast.makeText(getApplicationContext(),"网络连接不可用",Toast.LENGTH_SHORT).show();
        }

    }

    public void iniActivity() {
        mTitleBar = new TitleBar(this,"申领出库");
        Button refreshBtn = (Button)findViewById(R.id.btn_delivery_sheets_refresh);
        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniListView(posList);
            }
        });
    }

    public void iniListView(List<POS> posList) {

        mDeliveryCodeDetailList = new HashMap<String, Map<String, String>>();
        mDeliveryCodeList = new ArrayList<>();
        sheetListView = (ExpandableListView) findViewById(R.id.list_delivery_sheets);
        if(posList == null)
            return;
        for(POS pos:posList){
            mDeliveryCodeList.add(pos.getApply_Doc_Code());

            Map<String, String> detailMap = new HashMap<>();
            detailMap.put("applyPerson", pos.getApply_Person());
            detailMap.put("projectCode", pos.getProject_Code());
            detailMap.put("applyUnit", pos.getApply_Unit());
            detailMap.put("applyDate", pos.getApply_Date());
            detailMap.put("receiver", pos.getReceiver());
            mDeliveryCodeDetailList.put(pos.getApply_Doc_Code(), detailMap);
        }

        tmpAdapter = new DeliverySheetsExpandableAdapter(this, mDeliveryCodeDetailList, mDeliveryCodeList);
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

}
