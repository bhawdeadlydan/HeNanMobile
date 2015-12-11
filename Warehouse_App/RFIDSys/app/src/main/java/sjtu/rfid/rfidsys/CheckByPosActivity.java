package sjtu.rfid.rfidsys;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rfid.service.Good;
import sjtu.rfid.entity.CheckByMatEntity;
import sjtu.rfid.thread.CheckByPosThread;
import sjtu.rfid.tools.CheckByPosExpandableAdapter;
import sjtu.rfid.tools.TitleBar;

public class CheckByPosActivity extends Activity {

    private ExpandableListView sheetListView;
    private CheckByPosExpandableAdapter tmpAdapter;
    private Map<String, Map<String, String>> mCheckByPosDetailList;
    private List<Map<String,String>> mCheckByPosList;
    private TitleBar mTitleBar;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    private int goodPos=-1;
    private CheckByPosThread checkByPosThread;
    private List<Good> goodList;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==0||msg.obj==null)
                Toast.makeText(getApplicationContext(), "获取信息失败", Toast.LENGTH_SHORT).show();
            goodList=(List<Good>)msg.obj;
            iniListView(goodList);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_by_pos);
        iniActivity();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void iniActivity(){
        mTitleBar = new TitleBar(this,"货位盘点");
    }

    public void iniListView(List<Good> goodList) {

        mCheckByPosDetailList = new HashMap<String, Map<String, String>>();
        mCheckByPosList = new ArrayList<>();
        sheetListView = (ExpandableListView) findViewById(R.id.list_check_by_pos_sheets);

        for(Good good:goodList){
            Map<String,String> map=new HashMap<>();
            map.put("matCode",good.getCode());
            map.put("expectedCount",String.valueOf(good.getExpected_Quantity()));
            map.put("realCount","0");
            mCheckByPosList.add(map);

            Map<String, String> detailMap = new HashMap<>();
            detailMap.put("isBom", good.isIs_Bom()?"Y":"N");
            detailMap.put("matName", good.getDetail());
            mCheckByPosDetailList.put(good.getCode(), detailMap);
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
