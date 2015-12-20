package sjtu.rfid.transportsys;

import android.app.Activity;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.os.Handler;
import android.widget.Toast;

import nfc.NfcDataType;
import nfc.NfcTask;
import nfc.RfidNfc;
import sjtu.rfid.thread.ArrivalThread;
import tools.TmpConfirmAdapter;

public class TmpConfirmActivity extends Activity implements RfidNfc.TagUidCallBack{

    Button btnScan,btnCommit;
    ListView listView;
    Map<String,Integer> mItemDetailList;
    List<String> mItemList;
    TmpConfirmAdapter mAdapter;

    private static RfidNfc nnfc;
    private Handler handler=new Handler(){

        @Override
        public void handleMessage(Message msg) {
            if(msg.what==0||msg.obj==null)
                Toast.makeText(getApplicationContext(), "获取信息失败", Toast.LENGTH_SHORT).show();
            else if (msg.what==1){
//                arrivalEntity = (ArrivalEntity) msg.obj;
//                goodList = arrivalEntity.getGoodsList();
//                for (Good good : goodList) {
//                    mapScan.put(good.getCode(), 0);
//                    mapExpect.put(good.getCode(), good.getNum());
//                }
//                iniListView(goodList);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tmp_confirm);

        nnfc = new RfidNfc(this);
        nnfc.hfInit(this);

        iniActivity();
    }

    public void iniActivity() {
        ((Button)findViewById(R.id.btn_title_back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ((TextView)findViewById(R.id.text_title)).setText("暂存点复核出库");
        btnScan = (Button)findViewById(R.id.btn_tmp_confirm_scan);
        btnCommit = (Button)findViewById(R.id.btn_tmp_confirm_commit);
        listView = (ListView)findViewById(R.id.listview_tmp_confirm);
        mItemList = new ArrayList<>();
        mItemDetailList = new HashMap<>();

        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nnfc.nfcTask.clearNfcTask();
                nnfc.nfcTask.addNfcTask(NfcTask.NfcTaskType.ReadData, NfcTask.NfcTaskName.ItemInf, null);
                btnScan.setEnabled(false);
            }
        });

        btnCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void iniListView(){

        mAdapter = new TmpConfirmAdapter(this,mItemDetailList,mItemList);
        listView.setAdapter(mAdapter);
    }
    @Override
    public void onTagUidGet(NfcDataType.NfcDataTypeBase uid) {

    }

    @Override
    public void onBlockGet(String block) {

    }

    @Override
    public void onSectorGet(String sector) {

    }

    @Override
    public void getItemInf(NfcDataType.NfcDataTypeBase ItemInf) {

    }

    @Override
    public void getREQInf(NfcDataType.NfcDataTypeBase REQInf) {

    }

    @Override
    public void getTransInf(NfcDataType.NfcDataTypeBase TransInf) {

    }

    @Override
    public void getConsInf(NfcDataType.NfcDataTypeBase ConsInf) {

    }

    @Override
    public void onReadTag(NfcTask.NfcTaskName nfcTaskName, NfcDataType.NfcDataTypeBase NfcDataTypeBase) {

        if(nfcTaskName== NfcTask.NfcTaskName.ItemInf){
            btnScan.setEnabled(true);
            String epcCode=NfcDataTypeBase.getERPCode();
        }
    }

    @Override
    public void onWriteTaskEnd(NfcTask.NfcTaskName nfcTaskName, NfcDataType.NfcDataTypeBase nfcDataTypeBase, boolean ret) {

    }
}
