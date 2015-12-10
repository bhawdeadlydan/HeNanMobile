package sjtu.rfid.transportsys;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import rfid.service.POS;
import rfid.service.RFIDService;
import sjtu.rfid.thread.TestThread;
import tools.ArrivalExpandableAdapter;
import tools.GeoCoder;


public class ArrivalActivity extends AppCompatActivity {

    ExpandableListView sheetListView;
    ArrivalExpandableAdapter tmpAdapter;
    private Map<String, Map<String, String>> mArrivalDetailList;
    private List<Map<String,String>> mArrivalList;

    private TestThread thread;

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
        iniListView();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();


    }
    public void iniActivity()
    {
        Intent intent = getIntent();
        TextView title = (TextView) findViewById(R.id.text_title_title);
        int func = intent.getIntExtra("function",0);
        switch (func) {
            case 0:
                title.setText("暂存点收货");
                break;
            case 1:
                title.setText("施工点收货");
                break;
        }
    }
    public void iniListView() {

        mArrivalDetailList = new HashMap<String, Map<String, String>>();
        mArrivalList = new ArrayList<>();
        sheetListView = (ExpandableListView) findViewById(R.id.list_arrive_sheets);
        for(int i=0;i<9;i++){
            Map<String,String> map=new HashMap<>();
            map.put("matCode","2524-PDE-OUT-201509000000"+(i+1));
            map.put("expectedCount","10");
            map.put("realCount","0");
            mArrivalList.add(map);

        }
        for (int i = 0; i < mArrivalList.size(); i++) {
            Map<String, String> detailMap = new HashMap<>();
            detailMap.put("isBom", "Y");
            detailMap.put("matName", "B1524011");
            detailMap.put("unit", "公里");
            mArrivalDetailList.put(mArrivalList.get(i).get("matCode"), detailMap);
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
        TextView vApplyCode=(TextView)findViewById(R.id.text_arrival_order_code);
        vApplyCode.setText("VS-DH-0000000000000000001");

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

