package sjtu.rfid.rfidsys;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sjtu.rfid.tools.CheckByPosExpandableAdapter;
import sjtu.rfid.tools.PutInStorageExpandableAdapter;
import sjtu.rfid.tools.TitleBar;

public class PutInStorageActivity extends Activity {

    private ExpandableListView sheetListView;
    private PutInStorageExpandableAdapter tmpAdapter;
    private Map<String, Map<String, String>> mPutInStorageDetailList;
    private List<Map<String,String>> mPutInStorageList;
    private TitleBar mTitleBar;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_put_in_storage);
        iniActivity();
        iniListView();
        iniEvent();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void iniActivity() {
        mTitleBar = new TitleBar(this,"入库上架");
    }

    public void iniListView() {

        mPutInStorageDetailList = new HashMap<String, Map<String, String>>();
        mPutInStorageList = new ArrayList<>();
        sheetListView = (ExpandableListView) findViewById(R.id.list_put_in_storage_sheets);
        for(int i=0;i<9;i++){
            Map<String,String> map=new HashMap<>();
            map.put("boxCode","EPC201509000000"+(i+1));
            map.put("matName","光缆");
            mPutInStorageList.add(map);

        }
        for (int i = 0; i < mPutInStorageList.size(); i++) {
            Map<String, String> detailMap = new HashMap<>();
            detailMap.put("matCode", "11111111"+(i+1));
            detailMap.put("unit", "公里");
            detailMap.put("count", "100");
            mPutInStorageDetailList.put(mPutInStorageList.get(i).get("boxCode"), detailMap);
        }
        tmpAdapter = new PutInStorageExpandableAdapter(this,mPutInStorageDetailList,mPutInStorageList);
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

        TextView vGoodsPos=(TextView)findViewById(R.id.text_put_in_storage_loc);
        vGoodsPos.setText("A12");
    }

    public void iniEvent(){
        Button btnScanLoc=(Button)findViewById(R.id.btn_put_in_storage_scan_loc);
        Button btnScanBox=(Button)findViewById(R.id.btn_put_in_storage_scan_box);
        Button btnClear=(Button)findViewById(R.id.btn_put_in_storage_clear);
        Button btnBind=(Button)findViewById(R.id.btn_put_in_storage_bind);

        btnScanLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnScanBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnBind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
}
