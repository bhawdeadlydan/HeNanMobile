package sjtu.rfid.rfidsys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sjtu.rfid.tools.CheckByPosExpandableAdapter;

public class CheckByPosActivity extends AppCompatActivity {

    ExpandableListView sheetListView;
    CheckByPosExpandableAdapter tmpAdapter;
    private Map<String, Map<String, String>> mCheckByPosDetailList;
    private List<Map<String,String>> mCheckByPosList;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_by_pos);
        iniListView();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void iniListView() {

        mCheckByPosDetailList = new HashMap<String, Map<String, String>>();
        mCheckByPosList = new ArrayList<>();
        sheetListView = (ExpandableListView) findViewById(R.id.list_check_by_pos_sheets);
        for(int i=0;i<9;i++){
            Map<String,String> map=new HashMap<>();
            map.put("matCode","2524-PDE-OUT-201509000000"+(i+1));
            map.put("expectedCount","10");
            map.put("realCount","0");
            mCheckByPosList.add(map);

        }
        for (int i = 0; i < mCheckByPosList.size(); i++) {
            Map<String, String> detailMap = new HashMap<>();
            detailMap.put("isBom", "Y");
            detailMap.put("matName", "B1524011");
            mCheckByPosDetailList.put(mCheckByPosList.get(i).get("matCode"), detailMap);
        }
        tmpAdapter = new CheckByPosExpandableAdapter(this,mCheckByPosDetailList,mCheckByPosList);
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
        TextView vGoosdPos=(TextView)findViewById(R.id.text_check_by_pos_loc);
        vGoosdPos.setText(vGoosdPos.getText()+"A22");

    }
}
