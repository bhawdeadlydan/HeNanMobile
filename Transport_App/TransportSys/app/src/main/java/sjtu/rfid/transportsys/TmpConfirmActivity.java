package sjtu.rfid.transportsys;

import android.app.Activity;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import sjtu.rfid.thread.TmpConfirmThread;
import tools.Data;
import tools.TmpConfirmAdapter;

public class TmpConfirmActivity extends Activity implements RfidNfc.TagUidCallBack{

    Button btnScan,btnCommit;
    EditText editTextAddr;
    ListView listView;
    Map<String,Integer> mItemDetailList;
    List<String> mItemList;
    TmpConfirmAdapter mAdapter;

    private static RfidNfc nnfc;
    private Handler handler=new Handler(){

        @Override
        public void handleMessage(Message msg) {
            if(msg.what==0)
                Toast.makeText(getApplicationContext(), "提交失败", Toast.LENGTH_SHORT).show();
            else if (msg.what==1){
                Toast.makeText(getApplicationContext(),"提交成功",Toast.LENGTH_SHORT).show();
                finish();
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
        iniListView();
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
        editTextAddr=(EditText)findViewById(R.id.edittext_construct_address);
        mItemList = new ArrayList<>();
        mItemDetailList = new HashMap<>();

        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextAddr.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"请先填写施工单位",Toast.LENGTH_SHORT).show();
                }else{
                    nnfc.nfcTask.clearNfcTask();
                    nnfc.nfcTask.addNfcTask(NfcTask.NfcTaskType.ReadData, NfcTask.NfcTaskName.ItemInf, null);
                    nnfc.processTask(null);
                    //btnScan.setEnabled(false);
                }
            }
        });

        btnCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Data data = (Data) getApplication();
                String name = data.getName();
                String unit = data.getCompany();
                String address = editTextAddr.getText().toString();
                TmpConfirmThread t = new TmpConfirmThread(handler, name, unit, address, mItemDetailList);
                t.start();
            }
        });
    }

    public void iniListView(){

        mAdapter = new TmpConfirmAdapter(this,mItemDetailList,mItemList);
        listView.setAdapter(mAdapter);
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
                if(ret){
                    Toast.makeText(getApplicationContext(),"写入成功", Toast.LENGTH_SHORT).show();
                }else{
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
                //Toast.makeText(getApplication(),nfcDataTypeBase.toString(),Toast.LENGTH_SHORT).show();
                if(nfcTaskName== NfcTask.NfcTaskName.ItemInf){
                    //btnScan.setEnabled(true);
                    Data data = (Data) getApplication();
                    String epcCode=nfcDataTypeBase.getERPCode();
                    if(editTextAddr.getText().toString().equals("")){
                        Toast.makeText(getApplicationContext(),"请先填写施工单位",Toast.LENGTH_SHORT).show();
                    }else {
                        InnerThread innerThread = new InnerThread(data.getName(), editTextAddr.getText().toString(), data.getCompany(), 0, 0, System.currentTimeMillis(), nnfc);
                        innerThread.start();
                        if (mItemList.contains(epcCode)) {
                            mItemDetailList.put(epcCode, mItemDetailList.get(epcCode) + 1);
                        } else {
                            mItemList.add(epcCode);
                            mItemDetailList.put(epcCode, 1);
                        }
                        mAdapter.notifyDataSetChanged();
                    }
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

    class InnerThread extends Thread{

        private String consPerson;
        private String location;
        private String company;
        private double gpsN;
        private double gpsE;
        private long time;
        private RfidNfc nnfc;

        public InnerThread(String consPerson, String location, String company,double gpsN, double gpsE, long time, RfidNfc nnfc) {
            this.consPerson = consPerson;
            this.location = location;
            this.company=company;
            this.gpsE = gpsE;
            this.gpsN = gpsN;
            this.time = time;
            this.nnfc = nnfc;
        }

        @Override
        public void run() {
            nnfc.nfcTask.clearNfcTask();
            NfcDataType nfcDataType = new NfcDataType();
            NfcDataType.ConsInf consInf = nfcDataType.new ConsInf(consPerson,location,company,gpsN,gpsE,time);
            nnfc.nfcTask.addNfcTask(NfcTask.NfcTaskType.WriteData, NfcTask.NfcTaskName.ConsInf, consInf);
            nnfc.processTask(null);
        }
    }
}
