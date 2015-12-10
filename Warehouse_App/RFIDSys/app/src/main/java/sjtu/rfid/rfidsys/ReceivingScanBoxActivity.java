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

import sjtu.rfid.tools.ReceivingSheetsScanBoxExpandableAdapter;
import sjtu.rfid.tools.TitleBar;

public class ReceivingScanBoxActivity extends Activity {

    private TextView vReceSheetCode;
    private ExpandableListView sheetListView;
    private ReceivingSheetsScanBoxExpandableAdapter tmpAdapter;
    private Map<String, List<Map<String, String>>> mReceivingBoxesDetails;
    private List<Map<String,String>> mReceivingBoxes;
    private List<Map<String, String>> mapList;
    private TitleBar mTitleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiving_scan_box);

        vReceSheetCode=(TextView)findViewById(R.id.text_receiving_scan_box_mat_code);
        //Bundle bundle=this.getIntent().getExtras();
        //String receiving_sheet_code=bundle.getString("receiving_sheet_code");
        //vReceSheetCode.setText(vReceSheetCode.getText()+receiving_sheet_code);
        iniActivity();
        iniListView();
    }

    public void iniActivity(){
        mTitleBar = new TitleBar(this,"收货贴标");
    }

    public void iniListView() {
        mReceivingBoxesDetails=new HashMap<String, List<Map<String, String>>>();
        mapList = new ArrayList<Map<String,String>>();
        mReceivingBoxes = new ArrayList<>();
        sheetListView = (ExpandableListView) findViewById(R.id.list_receiving_scan_box_sheets);
        for(int i=0;i<9;i++){
            Map<String,String> map=new HashMap<>();
            map.put("matCode","20150900010150"+(i+1));
            map.put("boxCount","5");
            map.put("innerCount","10");
            mReceivingBoxes.add(map);

        }

        for (int i = 0; i < mReceivingBoxes.size(); i++) {
            Map<String, String> detailMap = new HashMap<>();
            detailMap.put("isBom", "Y");
            detailMap.put("itemName", "传统型光缆交接箱\\复合材料（SMC）\\满配壁挂式\\144芯");
            detailMap.put("quantity", "1");
            detailMap.put("unit","套");
            mapList.clear();
            mapList.add(detailMap);
            mReceivingBoxesDetails.put(mReceivingBoxes.get(i).get("matCode"), mapList);
        }
        tmpAdapter = new ReceivingSheetsScanBoxExpandableAdapter(this, mReceivingBoxesDetails, mReceivingBoxes);
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
