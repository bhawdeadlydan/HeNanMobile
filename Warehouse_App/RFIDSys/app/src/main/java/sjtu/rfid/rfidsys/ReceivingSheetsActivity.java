package sjtu.rfid.rfidsys;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rfid.service.ASN;
import sjtu.rfid.thread.ReceivingSheetsThread;
import sjtu.rfid.tools.ReceivingSheetsExpandableAdapter;
import sjtu.rfid.tools.TitleBar;

public class ReceivingSheetsActivity extends Activity {
    private ExpandableListView sheetListView;
    private ReceivingSheetsExpandableAdapter tmpAdapter;
    private Map<String, Map<String, String>> mReceivingCodeDetailList;
    private List<String> mReceivingCodeList;
    private TitleBar mTitleBar;

    private ReceivingSheetsThread receivingSheetsThread;
    private List<ASN> asnList;

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
            asnList=(List<ASN>)msg.obj;
            iniListView(asnList);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiving_sheets);
        iniActivity();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        receivingSheetsThread=new ReceivingSheetsThread(handler);
        receivingSheetsThread.start();
    }

    public void iniActivity()
    {
        mTitleBar = new TitleBar(this,"收货贴标");
    }

    public void iniListView(List<ASN> asnList) {

        mReceivingCodeDetailList = new HashMap<String, Map<String, String>>();
        mReceivingCodeList = new ArrayList<>();
        sheetListView = (ExpandableListView) findViewById(R.id.list_receiving_sheets);
        sheetListView.setItemsCanFocus(true);
        for(ASN asn:asnList){
            mReceivingCodeList.add(asn.getCode());

            Map<String, String> detailMap = new HashMap<>();
            detailMap.put("projectCode", asn.getProject_Code());
            detailMap.put("orderDate", asn.getOrder_Date());
            detailMap.put("vendorName", asn.getVendor_Name());
            detailMap.put("applyPerson", asn.getApply_Person());
            detailMap.put("relatedBill", asn.getReleated_Bill1());
            mReceivingCodeDetailList.put(asn.getCode(), detailMap);
        }
        tmpAdapter = new ReceivingSheetsExpandableAdapter(this, mReceivingCodeDetailList, mReceivingCodeList);
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

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "ReceivingSheets Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://sjtu.rfid.rfidsys/http/host/path")
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
                "ReceivingSheets Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://sjtu.rfid.rfidsys/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
