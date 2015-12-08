package sjtu.rfid.rfidsys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sjtu.rfid.tools.ReceivingSheetsExpandableAdapter;
import sjtu.rfid.tools.ReceivingSheetsScanBoxExpandableAdapter;

public class ReceivingScanBoxActivity extends AppCompatActivity {

    private TextView vReceSheetCode;
    ExpandableListView sheetListView;
    ReceivingSheetsScanBoxExpandableAdapter tmpAdapter;
    private Map<String, List<Map<String, String>>> mReceivingBoxesDetails;
    private List<String> mReceivingBoxes;
    private List<Map<String, String>> mapList;
    private int prePos=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiving_scan_box);

        vReceSheetCode=(TextView)findViewById(R.id.text_receiving_scan_box_code);
        Bundle bundle=this.getIntent().getExtras();
        String receiving_sheet_code=bundle.getString("receiving_sheet_code");
        vReceSheetCode.setText(vReceSheetCode.getText()+receiving_sheet_code);

        iniListView();
    }

    public void iniListView() {
        mReceivingBoxesDetails=new HashMap<String, List<Map<String, String>>>();
        mapList = new ArrayList<Map<String,String>>();
        mReceivingBoxes = new ArrayList<>();
        sheetListView = (ExpandableListView) findViewById(R.id.list_receiving_box_sheets);
        mReceivingBoxes.add("201509000101501");
        mReceivingBoxes.add("201509000101502");
        mReceivingBoxes.add("201509000101503");
        mReceivingBoxes.add("201509000101504");
        mReceivingBoxes.add("201509000101505");
        mReceivingBoxes.add("201509000101506");
        mReceivingBoxes.add("201509000101507");
        mReceivingBoxes.add("201509000101508");
        mReceivingBoxes.add("201509000101509");
        mReceivingBoxes.add("201509000101510");

        for (int i = 0; i < mReceivingBoxes.size(); i++) {
            Map<String, String> detailMap = new HashMap<>();
            detailMap.put("isBom", "Y");
            detailMap.put("itemCode", "10091005");
            detailMap.put("itemName", "传统型光缆交接箱\\复合材料（SMC）\\满配壁挂式\\144芯");
            detailMap.put("quantity", "1");
            detailMap.put("cartonOrderNumber", mReceivingBoxes.get(i)+"/1/1");
            detailMap.put("cartonNumber", mReceivingBoxes.get(i));
            mapList.clear();
            mapList.add(detailMap);
            mReceivingBoxesDetails.put(mReceivingBoxes.get(i), mapList);
        }
        tmpAdapter = new ReceivingSheetsScanBoxExpandableAdapter(this, mReceivingBoxesDetails, mReceivingBoxes);
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
