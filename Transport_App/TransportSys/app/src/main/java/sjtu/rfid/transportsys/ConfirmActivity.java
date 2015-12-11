package sjtu.rfid.transportsys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
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
import sjtu.rfid.entity.ConfirmEntity;
import sjtu.rfid.thread.ConfirmThread;
import tools.ConfirmExpandableAdapter;

public class ConfirmActivity extends AppCompatActivity {

    ExpandableListView sheetListView;
    ConfirmExpandableAdapter tmpAdapter;
    private Map<String, Map<String, String>> mConfirmDetailList;
    private List<Map<String,String>> mConfirmList;

    private String CNum="";
    private ConfirmEntity confirmEntity;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==0||msg.obj==null)
                Toast.makeText(getApplicationContext(),"获取信息失败",Toast.LENGTH_SHORT).show();
            confirmEntity=(ConfirmEntity)msg.obj;
            TextView vApplyCode=(TextView)findViewById(R.id.text_confirm_order_code);
            vApplyCode.setText(confirmEntity.getApplyCode());
            iniListView(confirmEntity.getGoodsList());
        }
    };


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
        initEvent();


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
    public void iniListView(List<Good> goodsList) {

        mConfirmDetailList = new HashMap<String, Map<String, String>>();
        mConfirmList = new ArrayList<>();
        sheetListView = (ExpandableListView) findViewById(R.id.list_confirm_sheets);
        for(Good good:goodsList){
            Map<String,String> map=new HashMap<>();
            map.put("matCode",good.getCode());
            map.put("expectedCount",String.valueOf(good.getNum()));
            map.put("realCount","0");
            mConfirmList.add(map);

            Map<String, String> detailMap = new HashMap<>();
            detailMap.put("isBom", good.isIs_Bom()?"Y":"N");
            detailMap.put("matName", good.getDetail());
            detailMap.put("unit", good.getUnit());
            mConfirmDetailList.put(good.getCode(), detailMap);
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

    }

    private void initEvent(){

        Button btnGetOrder=(Button)findViewById(R.id.btn_confirm_scan_box_get_order);
        btnGetOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConfirmThread thread=new ConfirmThread(handler,CNum);
                thread.start();

            }
        });
    }
}

