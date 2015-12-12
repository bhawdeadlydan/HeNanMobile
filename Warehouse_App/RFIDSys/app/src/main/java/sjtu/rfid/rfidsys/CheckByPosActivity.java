package sjtu.rfid.rfidsys;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
import rfid.service.check;
import sjtu.rfid.thread.CheckByPosThread;
import sjtu.rfid.thread.CheckThread;
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

    private CheckThread checkThread;
    private List<check> checkList;
    private boolean checkResult;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==0||msg.obj==null)
                Toast.makeText(getApplicationContext(), "获取信息失败", Toast.LENGTH_SHORT).show();
            goodList=(List<Good>)msg.obj;
            iniListView(goodList);
        }
    };

    private Handler handlerCheck=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==0||msg.obj==null)
                Toast.makeText(getApplicationContext(), "获取信息失败", Toast.LENGTH_SHORT).show();
            checkResult=(boolean)msg.obj;
            if(checkResult)
                Toast.makeText(getApplicationContext(), "提交成功", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getApplicationContext(), "提交失败", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_by_pos);
        iniActivity();
        iniEvent();

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
            map.put("expectedCount",String.valueOf(good.getNum()));
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

    }

    public void iniEvent(){
        Button btnScanLoc=(Button)findViewById(R.id.btn_check_by_pos_scan_loc);
        Button btnScanBox=(Button)findViewById(R.id.btn_check_by_pos_scan_box);
        Button btnCommit=(Button)findViewById(R.id.btn_check_by_pos_commit);

        btnScanLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //读货位标签线程

                TextView vGoosdPos=(TextView)findViewById(R.id.text_check_by_pos_loc);
                vGoosdPos.setText(vGoosdPos.getText()+"A22");
            }
        });
        btnScanBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //读货物标签线程

                checkByPosThread=new CheckByPosThread(goodPos,handler);
                checkByPosThread.start();
            }
        });
        btnCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkThread=new CheckThread(checkList,handlerCheck);
                checkThread.start();
            }
        });
    }
}
