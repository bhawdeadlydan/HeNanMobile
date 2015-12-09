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

public class MoveActivity extends AppCompatActivity {

    ExpandableListView sheetListView;
    CheckByPosExpandableAdapter tmpAdapter;
    private Map<String, Map<String, String>> mMoveDetailList;
    private List<Map<String,String>> mMoveList;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move);
        iniListView();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void iniListView() {

        mMoveDetailList = new HashMap<String, Map<String, String>>();
        mMoveList = new ArrayList<>();
        sheetListView = (ExpandableListView) findViewById(R.id.lsit_move_sheets);
        for(int i=0;i<9;i++){
            Map<String,String> map=new HashMap<>();
            map.put("boxCode","000000000"+(i+1));
            map.put("matName","光缆"+(i+1));
            map.put("originLoc","A11");
            mMoveList.add(map);

        }
        for (int i = 0; i < mMoveList.size(); i++) {
            Map<String, String> detailMap = new HashMap<>();
            detailMap.put("boxCode","000000000"+(i+1));
            detailMap.put("matName","光缆"+(i+1));
            detailMap.put("originLoc","A11");
            mMoveDetailList.put(mMoveList.get(i).get("boxCode"), detailMap);
        }
        tmpAdapter = new CheckByPosExpandableAdapter(this,mMoveDetailList,mMoveList);
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
        TextView vcurLoc=(TextView)findViewById(R.id.text_move_cur_loc);
        vcurLoc.setText("B11");
    }
}
