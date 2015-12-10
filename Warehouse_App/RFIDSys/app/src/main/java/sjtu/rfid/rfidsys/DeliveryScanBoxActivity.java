package sjtu.rfid.rfidsys;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sjtu.rfid.tools.DeliverySheetsScanBoxExpandableAdapter;
import sjtu.rfid.tools.TitleBar;

public class DeliveryScanBoxActivity extends Activity {

    private TextView vDeliverySheetCode;
    private ExpandableListView sheetListView;
    private DeliverySheetsScanBoxExpandableAdapter tmpAdapter;
    private Map<String, List<Map<String, String>>> mDeliveryBoxesDetails;
    private List<Map<String,String>> mDeliveryBoxes;
    private List<Map<String, String>> mapList;
    private TitleBar mTitleBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_scan_box);
        vDeliverySheetCode=(TextView)findViewById(R.id.text_delivery_scan_box_order_code);
        Bundle bundle=this.getIntent().getExtras();
        String delivery_sheet_code=bundle.getString("delivery_sheet_code");
        vDeliverySheetCode.setText(delivery_sheet_code);
        iniActivity();
        iniListView();
    }

    public void iniActivity() {
        mTitleBar = new TitleBar(this,"申领出库");
    }

    public void iniListView() {
        mDeliveryBoxesDetails=new HashMap<String, List<Map<String, String>>>();
        mapList = new ArrayList<Map<String,String>>();
        mDeliveryBoxes = new ArrayList<>();
        sheetListView = (ExpandableListView) findViewById(R.id.list_delivery_scan_box_sheets);
        for(int i=0;i<9;i++){
            Map<String,String> map=new HashMap<>();
            map.put("matCode","20150900010150"+(i+1));
            map.put("expectedCount","10");
            map.put("realCount","10");
            mDeliveryBoxes.add(map);

        }

        for (int i = 0; i < mDeliveryBoxes.size(); i++) {
            Map<String, String> detailMap = new HashMap<>();
            detailMap.put("isBom", "Y");
            detailMap.put("itemName", "传统型光缆交接箱\\复合材料（SMC）\\满配壁挂式\\144芯");
            detailMap.put("itemCode", "10091005");
            detailMap.put("quantity", "1");
            mapList.clear();
            mapList.add(detailMap);
            mDeliveryBoxesDetails.put(mDeliveryBoxes.get(i).get("matCode"), mapList);
        }
        tmpAdapter = new DeliverySheetsScanBoxExpandableAdapter(this, mDeliveryBoxesDetails, mDeliveryBoxes);
        sheetListView.setAdapter(tmpAdapter);


        sheetListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                /*
                if(prePos==-1) {
                    sheetListView.expandGroup(groupPosition);
                    prePos=groupPosition;
                }
                else{
                    sheetListView.collapseGroup(prePos);
                    sheetListView.expandGroup(groupPosition);
                }
                */
                for (int i = 0; i < tmpAdapter.getGroupCount(); i++) {
                    if (groupPosition != i) {
                        sheetListView.collapseGroup(i);
                    }
                }
            }
        });
    }
}
