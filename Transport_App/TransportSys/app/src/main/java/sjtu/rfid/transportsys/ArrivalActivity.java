package sjtu.rfid.transportsys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tools.ArrivalExpandableAdapter;

public class ArrivalActivity extends AppCompatActivity {

    ExpandableListView sheetListView;
    ArrivalExpandableAdapter tmpAdapter;
    private Map<String, Map<String, String>> mArrivalDetailList;
    private List<Map<String,String>> mArrivalList;

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
}

