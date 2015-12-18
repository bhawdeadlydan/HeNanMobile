package sjtu.rfid.transportsys;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rfid.service.POS;
import sjtu.rfid.thread.SheetsThread;
import tools.ConnectServer;
import tools.SheetExpandableAdapter;

public class SheetActivity extends Activity {

    private ExpandableListView sheetListView;
    private SheetExpandableAdapter tmpAdapter;
    private Map<String, Map<String, String>> mDeliveryCodeDetailList;
    private List<String> mDeliveryCodeList;

    private SheetsThread deliverySheetsThread;
    private List<POS> posList;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==0||msg.obj==null)
                Toast.makeText(getApplicationContext(), "获取信息失败", Toast.LENGTH_SHORT).show();
            posList=(List<POS>)msg.obj;
            iniListView(posList);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sheet);
        iniActivity();

    }

    public void iniActivity() {
        TextView title = (TextView) findViewById(R.id.text_title);
        title.setText("申领出库");
        Button btnBack = (Button) findViewById(R.id.btn_title_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected  void onResume() {
        super.onResume();
        ConnectServer connectServer=new ConnectServer();
        if(connectServer.isNetworkAvailable(this)){
            SheetsThread sheetsThread=new SheetsThread(handler);
            sheetsThread.start();
        }else{
            Toast.makeText(getApplicationContext(), "网络连接不可用", Toast.LENGTH_SHORT).show();
        }
    }

    public void iniListView(List<POS> posList) {

        mDeliveryCodeDetailList = new HashMap<String, Map<String, String>>();
        mDeliveryCodeList = new ArrayList<>();
        sheetListView = (ExpandableListView) findViewById(R.id.list_sheets);
        sheetListView.setEnabled(false);
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

        tmpAdapter = new SheetExpandableAdapter(this, mDeliveryCodeDetailList, mDeliveryCodeList);
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
