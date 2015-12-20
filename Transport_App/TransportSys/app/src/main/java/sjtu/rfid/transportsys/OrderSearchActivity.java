package sjtu.rfid.transportsys;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rfid.service.Good;
import sjtu.rfid.entity.ArrivalEntity;
import sjtu.rfid.thread.OrderSearchThread;
import tools.OrderSearchAdapter;

public class OrderSearchActivity extends Activity {

    ListView listView;
    OrderSearchAdapter mAdapter;
    List<Map<String,String>> mSheetList;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==0||msg.obj==null)
                Toast.makeText(getApplicationContext(), "获取信息失败", Toast.LENGTH_SHORT).show();
            else if (msg.what==1){
//                arrivalEntity = (ArrivalEntity) msg.obj;
//                goodList = arrivalEntity.getGoodsList();
//                for (Good good : goodList) {
//                    mapScan.put(good.getCode(), 0);
//                    mapExpect.put(good.getCode(), good.getNum());
//                }
//                iniListView(goodList);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_search);

        iniActivity();
    }

    public  void iniActivity() {
        ((Button)findViewById(R.id.btn_title_back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ((TextView)findViewById(R.id.text_title)).setText("申领单查询");
        listView = (ListView)findViewById(R.id.listview_order_search);
        mSheetList = new ArrayList<>();

        OrderSearchThread orderSearchThread=new OrderSearchThread(handler);
        orderSearchThread.start();

    }

    public void iniListView(){
        mAdapter = new OrderSearchAdapter(this,mSheetList);
        listView.setAdapter(mAdapter);
    }
}
