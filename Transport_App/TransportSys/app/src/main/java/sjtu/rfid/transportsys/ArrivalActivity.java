package sjtu.rfid.transportsys;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocationListener;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import baidu.poistion.service.LocationListener;
import nfc.NfcDataType;
import nfc.NfcTask;
import nfc.RfidNfc;
import rfid.service.Good;

import sjtu.rfid.entity.ArrivalEntity;

import sjtu.rfid.thread.ArrivalThread;
import sjtu.rfid.thread.CommitTransInfoThread;
import sjtu.rfid.thread.GeoCoderThread;
import tools.ArrivalExpandableAdapter;
import tools.Data;


public class ArrivalActivity extends Activity  implements RfidNfc.TagUidCallBack{

    ExpandableListView sheetListView;
    ArrivalExpandableAdapter tmpAdapter;
    private Map<String, Map<String, String>> mArrivalDetailList;
    private List<Map<String,String>> mArrivalList;

    private GeoCoderThread geoCoderThread;
    private ArrivalThread arrivalThread;
    private CommitTransInfoThread commitTransInfoThread;

    private String charge;
    private String time;
    private String position="";
    private String type;
    private String applyCode;
    private double lng=0.0;
    private double lat=0.0;

    private ArrivalEntity arrivalEntity;
    private boolean commitResult;

    private int func=-1;
    private static RfidNfc nnfc;
    private Data data ;

    private Map<String,Integer> mapScan=new HashMap<>();
    private Map<String,Integer> mapExpect=new HashMap<>();
    private List<Good> goodList;

    public BDLocationListener myListener = LocationListener.getInstance();

    private Button btnPos;
    private Button btnGetOrder;
    private Button btnScanWrite;
    private Button btnCommit;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==0||msg.obj==null)
                Toast.makeText(getApplicationContext(),"获取信息失败",Toast.LENGTH_SHORT).show();
            arrivalEntity=(ArrivalEntity)msg.obj;
            goodList=arrivalEntity.getGoodsList();
            for(Good good:goodList){
                mapScan.put(good.getCode(),0);
                mapExpect.put(good.getCode(),good.getNum());
            }
            iniListView(goodList);
        }
    };
    private Handler geoHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //Toast.makeText(getApplicationContext(), msg.obj.toString(), Toast.LENGTH_LONG).show();
            TextView vAddress=(TextView)findViewById(R.id.text_arrival_address);
            position=msg.obj.toString();
            vAddress.setText(msg.obj.toString());
        }
    };

    private Handler commitHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //Toast.makeText(getApplicationContext(), msg.obj.toString(), Toast.LENGTH_LONG).show();
            if(msg.what==0||msg.obj==null)
                Toast.makeText(getApplicationContext(),"获取信息失败",Toast.LENGTH_SHORT).show();
            commitResult=(boolean)msg.obj;
            if(commitResult) {
                Toast.makeText(getApplicationContext(), "提交成功", Toast.LENGTH_SHORT).show();
                finish();
            }
            else
                Toast.makeText(getApplicationContext(),"提交失败",Toast.LENGTH_SHORT).show();

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
        setContentView(R.layout.activity_arrival);

        data = (Data) getApplication();

        iniActivity();
        iniEvent();

        nnfc = new RfidNfc(this);
        nnfc.hfInit(this);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

//        LocationListener listener=(LocationListener)myListener;
//        geoCoderThread=new GeoCoderThread(listener.getLongitude(),listener.getLatitude(),geoHandler);
//        geoCoderThread.start();

    }
    public void iniActivity()
    {
        Intent intent = getIntent();
        TextView title = (TextView) findViewById(R.id.text_title);
        func = intent.getIntExtra("function",0);
        switch (func) {
            case 0:
                title.setText("暂存点收货");
                break;
            case 1:
                title.setText("施工点收货");
                break;
        }

        Button btnBack = (Button) findViewById(R.id.btn_title_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void iniListView(List<Good> goodsList) {

        mArrivalDetailList = new HashMap<String, Map<String, String>>();
        mArrivalList = new ArrayList<>();
        sheetListView = (ExpandableListView) findViewById(R.id.list_arrive_sheets);
        for(Good good:goodsList){
            Map<String,String> map=new HashMap<>();
            map.put("matCode",good.getCode());
            map.put("expectedCount",String.valueOf(good.getNum()));
            map.put("realCount","0");
            mArrivalList.add(map);

            Map<String, String> detailMap = new HashMap<>();
            detailMap.put("isBom", good.isIs_Bom()?"Y":"N");
            detailMap.put("matName", good.getDetail());
            detailMap.put("unit", good.getUnit());
            mArrivalDetailList.put(good.getCode(), detailMap);
        }
        tmpAdapter = new ArrivalExpandableAdapter(this,mArrivalDetailList,mArrivalList);
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
        btnPos=(Button)findViewById(R.id.btn_arrival_position);
        btnGetOrder=(Button)findViewById(R.id.btn_arrival_scan_box_get_order);
        btnScanWrite=(Button)findViewById(R.id.btn_arrival_scan_and_write);
        btnCommit=(Button)findViewById(R.id.btn_arrival_commit);

        btnPos.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                LocationListener listener=(LocationListener)myListener;
                lng=listener.getLongitude();
                lat=listener.getLatitude();
                geoCoderThread=new GeoCoderThread(listener.getLongitude(),listener.getLatitude(),geoHandler);
                geoCoderThread.start();

            }
        });

        btnGetOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //扫描货物标签线程
                nnfc.nfcTask.clearNfcTask();
                nnfc.nfcTask.addNfcTask(NfcTask.NfcTaskType.ReadData, NfcTask.NfcTaskName.REQInf, null);
                //nnfc.processTask(null);
                //btnGetOrder.setEnabled(false);

            }
        });
        btnScanWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //扫描货物标签并写入相关数据线程
                if(position.equals("")){
                    Toast.makeText(getApplicationContext(),"请先定位",Toast.LENGTH_SHORT).show();
                }else {
                    nnfc.nfcTask.clearNfcTask();
                    nnfc.nfcTask.addNfcTask(NfcTask.NfcTaskType.ReadData, NfcTask.NfcTaskName.ItemInf, null);
                    nnfc.processTask(null);
                }

            }
        });
        btnCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                charge=data.getName();
                Date date=new Date();
                DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                time=format.format(date);
                //position

                type=func+"";//0:暂存点，1：施工点，2:复核出库
                //applyCode
                commitTransInfoThread=new CommitTransInfoThread(charge,time,position,type,applyCode,lng,lat,commitHandler);
                commitTransInfoThread.start();
            }
        });


    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Arrival Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://sjtu.rfid.transportsys/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Arrival Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://sjtu.rfid.transportsys/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
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
                if(func==0){
                    if(nfcTaskName== NfcTask.NfcTaskName.TransInf){
                        if(ret){
                            Toast.makeText(getApplicationContext(),"暂存点写入成功", Toast.LENGTH_SHORT).show();
                            mArrivalList.clear();
                            mArrivalDetailList.clear();
                            for(Good good:goodList){
                                Map<String,String> map=new HashMap<>();
                                map.put("matCode",good.getCode());
                                map.put("expectedCount",String.valueOf(good.getNum()));
                                map.put("realCount",String.valueOf(mapScan.get(good.getCode())));
                                mArrivalList.add(map);

                                Map<String, String> detailMap = new HashMap<>();
                                detailMap.put("isBom", good.isIs_Bom()?"Y":"N");
                                detailMap.put("matName", good.getDetail());
                                detailMap.put("unit", good.getUnit());
                                mArrivalDetailList.put(good.getCode(), detailMap);
                            }
                            tmpAdapter.notifyDataSetChanged();
                        }

                        else
                            Toast.makeText(getApplicationContext(),"暂存点写入失败", Toast.LENGTH_SHORT).show();
                    }
                }else if(func==1){
                    if(nfcTaskName== NfcTask.NfcTaskName.ConsInf){
                        if(ret){
                            Toast.makeText(getApplicationContext(),"施工点写入成功", Toast.LENGTH_SHORT).show();
                            mArrivalList.clear();
                            mArrivalDetailList.clear();
                            for(Good good:goodList){
                                Map<String,String> map=new HashMap<>();
                                map.put("matCode",good.getCode());
                                map.put("expectedCount",String.valueOf(good.getNum()));
                                map.put("realCount",String.valueOf(mapScan.get(good.getCode())));
                                mArrivalList.add(map);

                                Map<String, String> detailMap = new HashMap<>();
                                detailMap.put("isBom", good.isIs_Bom()?"Y":"N");
                                detailMap.put("matName", good.getDetail());
                                detailMap.put("unit", good.getUnit());
                                mArrivalDetailList.put(good.getCode(), detailMap);
                            }
                            tmpAdapter.notifyDataSetChanged();
                        }
                        else
                            Toast.makeText(getApplicationContext(),"施工点写入失败", Toast.LENGTH_SHORT).show();
                    }
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
                if(nfcTaskName== NfcTask.NfcTaskName.REQInf) {
                    //btnGetOrder.setEnabled(true);
                    applyCode = nfcDataTypeBase.getREQ();
                    TextView vApplyCode = (TextView) findViewById(R.id.text_arrival_order_code);
                    vApplyCode.setText(applyCode);
                    arrivalThread=new ArrivalThread(handler,applyCode);
                    arrivalThread.start();
                }else if(nfcTaskName== NfcTask.NfcTaskName.ItemInf){
                    String epcCode=nfcDataTypeBase.getERPCode();
                    if(mapScan.containsKey(epcCode)){
                        if(mapScan.get(epcCode)>=mapExpect.get(epcCode)){
                            Toast.makeText(getApplicationContext(),  "货物:"+epcCode+"数量已够", Toast.LENGTH_SHORT).show();
                        }else {
                            if(func==0){
                                InnerThread1 innerThread1=new InnerThread1(data.getName(),data.getCompany(),position,lat,lng,System.currentTimeMillis(),nnfc);
                                innerThread1.start();
                            }
                            else if(func==1) {
                                InnerThread2 innerThread2=new InnerThread2(data.getName(),position,data.getCompany(),lat,lng,System.currentTimeMillis(),nnfc);
                                innerThread2.start();
                            }
                            mapScan.put(epcCode,mapScan.get(epcCode)+1);
                        }
                    }else{
                        Toast.makeText(getApplicationContext(),  "错误:该货物不再此申领单中", Toast.LENGTH_SHORT).show();
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
    class InnerThread1 extends Thread{

        private String transPerson;
        private String transStation;
        private String location;
        private double gpsN;
        private double gpsE;
        private long time;
        private RfidNfc nnfc;

        public InnerThread1(String transPerson, String transStation, String location, double gpsN, double gpsE, long time,RfidNfc nnfc) {
            this.transPerson = transPerson;
            this.transStation = transStation;
            this.location = location;
            this.gpsE = gpsE;
            this.gpsN = gpsN;
            this.time = time;
            this.nnfc=nnfc;
        }

        @Override
        public void run() {
            nnfc.nfcTask.clearNfcTask();
            NfcDataType nfcDataType = new NfcDataType();
            NfcDataType.TransInf transInf = nfcDataType.new TransInf(transPerson,transStation,location,gpsN,gpsE, time);
            nnfc.nfcTask.addNfcTask(NfcTask.NfcTaskType.WriteData, NfcTask.NfcTaskName.TransInf, transInf);
            nnfc.processTask(null);
        }
    }
    class InnerThread2 extends Thread{

        private String consPerson;
        private String location;
        private String company;
        private double gpsN;
        private double gpsE;
        private long time;
        private RfidNfc nnfc;

        public InnerThread2(String consPerson, String location, String company,double gpsN, double gpsE, long time, RfidNfc nnfc) {
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

