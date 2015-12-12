package sjtu.rfid.rfidsys;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rfid.service.Good;
import sjtu.rfid.thread.DeliveryScanBoxThread;
import sjtu.rfid.thread.DeliverySubmitThread;
import sjtu.rfid.tools.DeliverySheetsScanBoxExpandableAdapter;
import sjtu.rfid.tools.TitleBar;

public class DeliveryScanBoxActivity extends Activity {

    private TextView vDeliverySheetCode;
    private ExpandableListView sheetListView;
    private DeliverySheetsScanBoxExpandableAdapter tmpAdapter;
    private Map<String, Map<String, String>> mDeliveryBoxesDetails;
    private List<Map<String,String>> mDeliveryBoxes;

    private TitleBar mTitleBar;

    private String cartonList="\n";
    private DeliveryScanBoxThread deliveryScanBoxThread;
    private DeliverySubmitThread deliverySubmitThread;
    private List<Good> goodList;
    private String applyCode;
    private boolean deliveryResult;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==0||msg.obj==null)
                Toast.makeText(getApplicationContext(), "获取信息失败", Toast.LENGTH_SHORT).show();
            goodList=(List<Good>)msg.obj;
            iniListView(goodList);
        }
    };

    private Handler handlerDelivery=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==0||msg.obj==null)
                Toast.makeText(getApplicationContext(), "获取信息失败", Toast.LENGTH_SHORT).show();
            deliveryResult=(boolean)msg.obj;
            if(deliveryResult)
                Toast.makeText(getApplicationContext(), "数据提交成功", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getApplicationContext(), "数据提交失败", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_scan_box);
        vDeliverySheetCode=(TextView)findViewById(R.id.text_delivery_scan_box_order_code);
        Bundle bundle=this.getIntent().getExtras();
        applyCode=bundle.getString("delivery_sheet_code");
        vDeliverySheetCode.setText(applyCode);
        iniActivity();
        iniEvent();

        deliveryScanBoxThread=new DeliveryScanBoxThread(handler,applyCode);
        deliveryScanBoxThread.start();
    }

    public void iniActivity() {
        mTitleBar = new TitleBar(this,"申领出库");
    }

    public void iniListView(List<Good> goodList) {
        mDeliveryBoxesDetails=new HashMap<String, Map<String, String>>();
        mDeliveryBoxes = new ArrayList<>();
        sheetListView = (ExpandableListView) findViewById(R.id.list_delivery_scan_box_sheets);

        for(Good good:goodList){
            Map<String,String> map=new HashMap<>();
            map.put("matCode",good.getCode());
            map.put("matName", good.getDetail());
            map.put("expectedCount", String.valueOf(good.getNum()));
            mDeliveryBoxes.add(map);

            Map<String, String> detailMap = new HashMap<>();
            detailMap.put("isBom", good.isIs_Bom() ? "Y" : "N");
//            for(String cartonNum:good.getCartonNums()){
//                cartonList+=cartonNum+"，";
//            }
//            detailMap.put("cartonList",cartonList);
            detailMap.put("cartonList","");
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

    public void iniEvent(){
        Button btnScan=(Button)findViewById(R.id.btn_delivery_scan_box_scan);
        Button btnCommit=(Button)findViewById(R.id.btn_delivery_scan_box_commit);

        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //读货物标签线程

            }
        });
        btnCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> CNums=new ArrayList<String>();
                String[] CNumString;
                cartonList=cartonList.trim();
                CNumString=cartonList.split(",");
                for(int i=0;i<CNumString.length;i++){
                    CNums.add(CNumString[i]);
                }
                deliverySubmitThread=new DeliverySubmitThread(applyCode,CNums,handlerDelivery);
                deliverySubmitThread.start();;
            }
        });
    }
}
