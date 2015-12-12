package sjtu.rfid.transportsys;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
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
import rfid.service.Good;

import sjtu.rfid.entity.ArrivalEntity;

import sjtu.rfid.thread.ArrivalThread;
import sjtu.rfid.thread.CommitTransInfoThread;
import sjtu.rfid.thread.GeoCoderThread;
import tools.ArrivalExpandableAdapter;
import tools.Data;


public class ArrivalActivity extends Activity {

    ExpandableListView sheetListView;
    ArrivalExpandableAdapter tmpAdapter;
    private Map<String, Map<String, String>> mArrivalDetailList;
    private List<Map<String,String>> mArrivalList;

    private GeoCoderThread geoCoderThread;
    private ArrivalThread arrivalThread;
    private CommitTransInfoThread commitTransInfoThread;

    private String charge;
    private String time;
    private String position;
    private String type;
    private String applyCode;
    private double lng=0.0;
    private double lat=0.0;


    private String CNum="";
    private ArrivalEntity arrivalEntity;
    private boolean commitResult;

    public BDLocationListener myListener = LocationListener.getInstance();

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==0||msg.obj==null)
                Toast.makeText(getApplicationContext(),"获取信息失败",Toast.LENGTH_SHORT).show();
            arrivalEntity=(ArrivalEntity)msg.obj;
            TextView vApplyCode=(TextView)findViewById(R.id.text_arrival_order_code);
            vApplyCode.setText(arrivalEntity.getApplyCode());
            iniListView(arrivalEntity.getGoodsList());
        }
    };
    private Handler geoHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //Toast.makeText(getApplicationContext(), msg.obj.toString(), Toast.LENGTH_LONG).show();
            if(msg.what==0||msg.obj==null)
                Toast.makeText(getApplicationContext(),"获取地址失败\"",Toast.LENGTH_SHORT).show();
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
            if(commitResult)
                Toast.makeText(getApplicationContext(),"提交成功",Toast.LENGTH_SHORT).show();
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

        iniActivity();
        iniEvent();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        LocationListener listener=(LocationListener)myListener;
        geoCoderThread=new GeoCoderThread(listener.getLongitude(),listener.getLatitude(),geoHandler);
        geoCoderThread.start();

    }
    public void iniActivity()
    {
        Intent intent = getIntent();
        TextView title = (TextView) findViewById(R.id.text_title);
        int func = intent.getIntExtra("function",0);
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
        Button btnPos=(Button)findViewById(R.id.btn_arrival_position);
        Button btnGetOrder=(Button)findViewById(R.id.btn_arrival_scan_box_get_order);
        Button btnScanWrite=(Button)findViewById(R.id.btn_arrival_scan_and_write);
        Button btnCommit=(Button)findViewById(R.id.btn_arrival_commit);

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
                applyCode="";
                arrivalThread=new ArrivalThread(handler,CNum);
                arrivalThread.start();

            }
        });
        btnScanWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //扫描货物标签并写入相关数据线程


            }
        });
        btnCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Data data= (Data)getApplication();
                charge=data.getName();
                Date date=new Date();
                DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                time=format.format(date);
                //position
                Intent intent = getIntent();
                int func = intent.getIntExtra("function",0);
                type=func+"";//0:暂存点，1：施工点
                //applyCode

                commitTransInfoThread=new CommitTransInfoThread(charge,time,position,type,applyCode,lng,lat,handler);
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
}

