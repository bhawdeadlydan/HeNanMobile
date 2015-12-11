package sjtu.rfid.rfidsys;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ExpandableListView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sjtu.rfid.tools.ReceivingSheetsExpandableAdapter;
import sjtu.rfid.tools.TitleBar;

public class ReceivingSheetsActivity extends Activity {
    private ExpandableListView sheetListView;
    private ReceivingSheetsExpandableAdapter tmpAdapter;
    private Map<String, Map<String, String>> mReceivingCodeDetailList;
    private List<String> mReceivingCodeList;
    private TitleBar mTitleBar;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiving_sheets);
        iniActivity();
        iniListView();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void iniActivity()
    {
        mTitleBar = new TitleBar(this,"收货贴标");
    }

    public void iniListView() {

        mReceivingCodeDetailList = new HashMap<String, Map<String, String>>();
        mReceivingCodeList = new ArrayList<>();
        sheetListView = (ExpandableListView) findViewById(R.id.list_receiving_sheets);
        sheetListView.setItemsCanFocus(true);
        mReceivingCodeList.add("VD-SH-2015090000001");
        mReceivingCodeList.add("VD-SH-2015090000002");
        mReceivingCodeList.add("VD-SH-2015090000003");
        mReceivingCodeList.add("VD-SH-2015090000004");
        mReceivingCodeList.add("VD-SH-2015090000005");
        mReceivingCodeList.add("VD-SH-2015090000006");
        mReceivingCodeList.add("VD-SH-2015090000007");
        mReceivingCodeList.add("VD-SH-2015090000008");
        mReceivingCodeList.add("VD-SH-2015090000009");
        mReceivingCodeList.add("VD-SH-2015090000010");
        for (int i = 0; i < mReceivingCodeList.size(); i++) {
            Map<String, String> detailMap = new HashMap<>();
            detailMap.put("projectCode", "B1524011");
            detailMap.put("orderDate", "2015-09-01T00:00:00.000+08:00");
            detailMap.put("vendorName", "江苏亨通光电股份有限公司");
            detailMap.put("applyPerson", "苏军凯");
            detailMap.put("relatedBill", "2524-PO-2015080000021");
            mReceivingCodeDetailList.put(mReceivingCodeList.get(i), detailMap);
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
