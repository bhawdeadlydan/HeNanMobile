package sjtu.rfid.rfidsys;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sjtu.rfid.tools.DeliverySheetsExpandableAdapter;

public class DeliverySheetsActivity extends Activity {
    ExpandableListView sheetListView;
    DeliverySheetsExpandableAdapter tmpAdapter;
    private Map<String, Map<String, String>> mDeliveryCodeDetailList;
    private List<String> mDeliveryCodeList;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_sheets);
        iniListView();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void iniListView() {

        mDeliveryCodeDetailList = new HashMap<String, Map<String, String>>();
        mDeliveryCodeList = new ArrayList<>();
        sheetListView = (ExpandableListView) findViewById(R.id.list_delivery_sheets);
        mDeliveryCodeList.add("2524-PDE-OUT-2015090000001");
        mDeliveryCodeList.add("2524-PDE-OUT-2015090000002");
        mDeliveryCodeList.add("2524-PDE-OUT-2015090000003");
        mDeliveryCodeList.add("2524-PDE-OUT-2015090000004");
        mDeliveryCodeList.add("2524-PDE-OUT-2015090000005");
        mDeliveryCodeList.add("2524-PDE-OUT-2015090000006");
        mDeliveryCodeList.add("2524-PDE-OUT-2015090000007");
        mDeliveryCodeList.add("2524-PDE-OUT-2015090000008");
        mDeliveryCodeList.add("2524-PDE-OUT-2015090000009");
        mDeliveryCodeList.add("2524-PDE-OUT-2015090000010");
        for (int i = 0; i < mDeliveryCodeList.size(); i++) {
            Map<String, String> detailMap = new HashMap<>();
            detailMap.put("applyPerson", "苏军凯");
            detailMap.put("projectCode", "B1524011");
            detailMap.put("applyUnit", "平顶山分公司\\网络部\\工程建设中心");
            detailMap.put("applyDate", "2015/10/01");
            detailMap.put("receiver", "盛来");
            mDeliveryCodeDetailList.put(mDeliveryCodeList.get(i), detailMap);
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
