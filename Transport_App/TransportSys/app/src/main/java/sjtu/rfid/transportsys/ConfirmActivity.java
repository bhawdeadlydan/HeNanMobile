package sjtu.rfid.transportsys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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

import nfc.NfcDataType;
import nfc.NfcDataType.*;
import nfc.NfcTask;
import nfc.RfidNfc;
import rfid.service.Good;
import sjtu.rfid.entity.ConfirmEntity;
import sjtu.rfid.thread.ConfirmThread;
import tools.ConfirmExpandableAdapter;
import tools.Data;

public class ConfirmActivity extends Activity implements RfidNfc.TagUidCallBack{

    ExpandableListView sheetListView;
    ConfirmExpandableAdapter tmpAdapter;
    private Map<String, Map<String, String>> mConfirmDetailList;
    private List<Map<String,String>> mConfirmList;

    private String applyCode="";
    private ConfirmEntity confirmEntity;


    private static RfidNfc nnfc;
    private Data data ;

    private int count=0;


    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==0||msg.obj==null)
                Toast.makeText(getApplicationContext(),"获取信息失败",Toast.LENGTH_SHORT).show();
            confirmEntity=(ConfirmEntity)msg.obj;
            iniListView(confirmEntity.getGoodsList());
        }
    };


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        data = (Data) getApplication();
        iniActivity();
        initEvent();



        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        nnfc = new RfidNfc(this);
        nnfc.hfInit(this);

    }
    public void iniActivity()
    {
        Intent intent = getIntent();
        TextView title = (TextView) findViewById(R.id.text_title);
        title.setText("复核出库");
        Button btnBack = (Button) findViewById(R.id.btn_title_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    public void iniListView(List<Good> goodsList) {

        mConfirmDetailList = new HashMap<String, Map<String, String>>();
        mConfirmList = new ArrayList<>();
        sheetListView = (ExpandableListView) findViewById(R.id.list_confirm_sheets);
        for(Good good:goodsList){
            Map<String,String> map=new HashMap<>();
            map.put("matCode",good.getCode());
            map.put("expectedCount",String.valueOf(good.getNum()));
            map.put("realCount",String.valueOf(count));
            mConfirmList.add(map);

            Map<String, String> detailMap = new HashMap<>();
            detailMap.put("isBom", good.isIs_Bom()?"Y":"N");
            detailMap.put("matName", good.getDetail());
            detailMap.put("unit", good.getUnit());
            mConfirmDetailList.put(good.getCode(), detailMap);
        }
        tmpAdapter = new ConfirmExpandableAdapter(this,mConfirmDetailList,mConfirmList);
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

    private void initEvent(){

        Button btnGetOrder=(Button)findViewById(R.id.btn_confirm_scan_box_get_order);
        Button btnScanWrite=(Button)findViewById(R.id.bnt_confirm_scan_and_write);
        Button btnCommit=(Button)findViewById(R.id.btn_confirm_commit);
        btnGetOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //扫描货物标签线程
                nnfc.nfcTask.clearNfcTask();
                nnfc.nfcTask.addNfcTask(NfcTask.NfcTaskType.ReadData, NfcTask.NfcTaskName.REQInf, null);
                nnfc.processTask(null);
            }
        });

        btnScanWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //扫描货物标签并写入相关信息线程
                nnfc.nfcTask.clearNfcTask();
                nnfc.nfcTask.addNfcTask(NfcTask.NfcTaskType.ReadData, NfcTask.NfcTaskName.ItemInf, null);
                NfcDataType nfcDataType = new NfcDataType();
                REQInf reqInf = nfcDataType.new REQInf("",applyCode,confirmEntity.getPos().getApply_Person(),"","",System.currentTimeMillis());
                //REQInf reqInf = nfcDataType.new REQInf("E00000000000000000000000","2524-REQ-2015100000297","申请人A","施工队B","复查人C",Long.decode("1450070000"));
                nnfc.nfcTask.addNfcTask(NfcTask.NfcTaskType.WriteData, NfcTask.NfcTaskName.REQInf,reqInf);
                nnfc.processTask(null);
            }
        });

        btnCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //暂不知做何操作

            }
        });

    }

    @Override
    public void onTagUidGet(final NfcDataType.NfcDataTypeBase uid) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), uid.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onSectorGet(String sector){

    }
    public void onBlockGet(final String block)
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //Toast.makeText(getApplicationContext(), block.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void getItemInf(final NfcDataType.NfcDataTypeBase itemInf){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //Toast.makeText(getApplicationContext(), itemInf.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void getREQInf(final NfcDataType.NfcDataTypeBase reqInf){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
               // Toast.makeText(getApplicationContext(), reqInf.toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onWriteTaskEnd(final NfcTask.NfcTaskName nfcTaskName,final NfcDataType.NfcDataTypeBase nfcDataTypeBase,final boolean ret)
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //Toast.makeText(getApplicationContext(),"写入" + nfcDataTypeBase.toString() + (ret  ? "succeed":"failed\n" + nfcDataTypeBase.toString()), Toast.LENGTH_SHORT).show();
                //Toast.makeText(getApplicationContext(),nfcDataTypeBase.getREQ(), Toast.LENGTH_SHORT).show();
                if(nfcTaskName== NfcTask.NfcTaskName.REQInf){
                    if(ret){
                        Toast.makeText(getApplicationContext(),"写入成功", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(getApplicationContext(),"写入失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public  void onReadTag(final NfcTask.NfcTaskName nfcTaskName, final NfcDataType.NfcDataTypeBase nfcDataTypeBase)
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //Toast.makeText(getApplicationContext(),  nfcDataTypeBase.toString(), Toast.LENGTH_SHORT).show();
                if(nfcTaskName== NfcTask.NfcTaskName.REQInf) {
                    applyCode = nfcDataTypeBase.getREQ();
                    TextView vApplyCode = (TextView) findViewById(R.id.text_confirm_order_code);
                    vApplyCode.setText(applyCode);
                    ConfirmThread thread = new ConfirmThread(handler, applyCode);
                    thread.start();
                }else if(nfcTaskName== NfcTask.NfcTaskName.ItemInf){
                    Toast.makeText(getApplicationContext(),  nfcDataTypeBase.getERPCode(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void getTransInf(final NfcDataType.NfcDataTypeBase TransInf){

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //Toast.makeText(getApplicationContext(), TransInf.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void getConsInf(final NfcDataType.NfcDataTypeBase consInf){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //Toast.makeText(getApplicationContext(), consInf.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(nnfc!=null)
            nnfc.onResume(this);

    }
}

