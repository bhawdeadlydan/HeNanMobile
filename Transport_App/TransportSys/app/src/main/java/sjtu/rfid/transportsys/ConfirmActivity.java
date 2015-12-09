package sjtu.rfid.transportsys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tools.ArrivalExpandableAdapter;
import tools.ConfirmExpandableAdapter;

public class ConfirmActivity extends AppCompatActivity {

    ExpandableListView sheetListView;
    ConfirmExpandableAdapter tmpAdapter;
    private Map<String, Map<String, String>> mConfirmDetailList;
    private List<Map<String,String>> mConfirmList;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
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
        title.setText("复核出库");
    }
    public void iniListView() {

        mConfirmDetailList = new HashMap<String, Map<String, String>>();
        mConfirmList = new ArrayList<>();
        sheetListView = (ExpandableListView) findViewById(R.id.list_confirm_sheets);
        for(int i=0;i<9;i++){
            Map<String,String> map=new HashMap<>();
            map.put("matCode","2524-PDE-OUT-201509000000"+(i+1));
            map.put("expectedCount","10");
            map.put("realCount","0");
            mConfirmList.add(map);

        }
        for (int i = 0; i < mConfirmList.size(); i++) {
            Map<String, String> detailMap = new HashMap<>();
            detailMap.put("isBom", "Y");
            detailMap.put("matName", "B1524011");
            detailMap.put("unit", "公里");
            mConfirmDetailList.put(mConfirmList.get(i).get("matCode"), detailMap);
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
        TextView vApplyCode=(TextView)findViewById(R.id.text_confirm_order_code);
        vApplyCode.setText("VS-DH-0000000000000000001");

    }
}

