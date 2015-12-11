package sjtu.rfid.rfidsys;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rfid.service.Good;
import sjtu.rfid.thread.DeliveryScanBoxThread;
import sjtu.rfid.thread.DeliverySheetsThread;
import sjtu.rfid.tools.DeliverySheetsScanBoxExpandableAdapter;
import sjtu.rfid.tools.TitleBar;

public class DeliveryScanBoxActivity extends Activity {

    private TextView vDeliverySheetCode;
    private ExpandableListView sheetListView;
    private DeliverySheetsScanBoxExpandableAdapter tmpAdapter;
    private Map<String, Map<String, String>> mDeliveryBoxesDetails;
    private List<Map<String,String>> mDeliveryBoxes;

    private TitleBar mTitleBar;

    private DeliveryScanBoxThread deliveryScanBoxThread;
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
        setContentView(R.layout.activity_delivery_scan_box);
        vDeliverySheetCode=(TextView)findViewById(R.id.text_delivery_scan_box_order_code);
        Bundle bundle=this.getIntent().getExtras();
        String sheetCode=bundle.getString("delivery_sheet_code");
        vDeliverySheetCode.setText(sheetCode);
        iniActivity();

        deliveryScanBoxThread=new DeliveryScanBoxThread(handler,sheetCode);
        deliveryScanBoxThread.start();
    }

    public void iniActivity() {
        mTitleBar = new TitleBar(this,"申领出库");
    }

    public void iniListView(List<Good> goodList) {
        System.out.println("11111111");
        mDeliveryBoxesDetails=new HashMap<String, Map<String, String>>();
        mDeliveryBoxes = new ArrayList<>();
        sheetListView = (ExpandableListView) findViewById(R.id.list_delivery_scan_box_sheets);

        System.out.println("22222222");
        Toast.makeText(getApplicationContext(),goodList.size(),Toast.LENGTH_LONG).show();
        for(Good good:goodList){
            Map<String,String> map=new HashMap<>();
            map.put("matCode",good.getCode());
            map.put("matName", good.getDetail());
            map.put("boxCount", String.valueOf(good.getNum()));
            mDeliveryBoxes.add(map);

            Map<String, String> detailMap = new HashMap<>();
            String cartonList="\n";
            detailMap.put("isBom", good.isIs_Bom() ? "Y" : "N");
            for(String cartonNum:good.getCartonNums()){
                cartonList+=cartonNum+"\n";
            }
            detailMap.put("cartonList",cartonList);
            mDeliveryBoxesDetails.put(good.getCode(), detailMap);
        }

        tmpAdapter = new DeliverySheetsScanBoxExpandableAdapter(this, mDeliveryBoxesDetails, mDeliveryBoxes);
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
