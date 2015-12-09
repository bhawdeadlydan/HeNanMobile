package sjtu.rfid.tools;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import sjtu.rfid.rfidsys.R;

/**
 * Created by user on 12/7/2015.
 */
public class DeliverySheetsScanBoxExpandableAdapter extends BaseExpandableListAdapter {

    private Map<String,List<Map<String,String>>> mDeliveryBoxesDetails;
    private List<Map<String,String>> mDeliveryBoxes;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public DeliverySheetsScanBoxExpandableAdapter(Context mContext, Map<String, List<Map<String, String>>> mDeliveryBoxesDetails, List<Map<String,String>> mDeliveryBoxes){
        this.mContext = mContext;
        this.mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mDeliveryBoxes = mDeliveryBoxes;
        this.mDeliveryBoxesDetails = mDeliveryBoxesDetails;
    }

    @Override
    public int getGroupCount() {

        return mDeliveryBoxes.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        String key = mDeliveryBoxes.get(groupPosition).get("matCode");
        return mDeliveryBoxesDetails.get(key).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mDeliveryBoxes.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        String key = mDeliveryBoxes.get(groupPosition).get("matCode");
        return mDeliveryBoxesDetails.get(key);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        RelativeLayout layout= (RelativeLayout) mLayoutInflater.inflate(R.layout.item_delivery_scan_box, null);
        TextView matCode = (TextView) layout.findViewById(R.id.text_delivery_scan_mat_code);
        TextView expectedCount = (TextView) layout.findViewById(R.id.text_delivery_scan_expected_count);
        TextView realCount = (TextView) layout.findViewById(R.id.text_delivery_scan_real_count);
        matCode.setText(matCode.getText()+mDeliveryBoxes.get(groupPosition).get("matCode"));
        expectedCount.setText(expectedCount.getText()+mDeliveryBoxes.get(groupPosition).get("expectedCount"));
        realCount.setText(realCount.getText()+mDeliveryBoxes.get(groupPosition).get("realCount"));
        return layout;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        RelativeLayout layout= (RelativeLayout) mLayoutInflater.inflate(R.layout.item_delivery_scan_box_detail, null);
        List<Map<String,String>> mapList=mDeliveryBoxesDetails.get(mDeliveryBoxes.get(groupPosition).get("matCode"));
        for(int i=0;i<mapList.size();i++){
            Map<String,String> map=mapList.get(i);
            for(Map.Entry<String,String> entry:map.entrySet()){
                if(entry.getKey().equals("isBom")){
                    TextView text1 = (TextView) layout.findViewById(R.id.text_delivery_scan_box_detail_is_bom);
                    text1.setText(text1.getText()+entry.getValue());
                }
                else if(entry.getKey().equals("itemCode")){
                    TextView text1 = (TextView) layout.findViewById(R.id.text_delivery_scan_box_detail_item_code);
                    text1.setText(text1.getText()+entry.getValue());
                }
                else if(entry.getKey().equals("itemName")){
                    TextView text1 = (TextView) layout.findViewById(R.id.text_delivery_scan_box_detail_item_name);
                    text1.setText(text1.getText()+entry.getValue());
                }
                else if(entry.getKey().equals("quantity")){
                    TextView text1 = (TextView) layout.findViewById(R.id.text_delivery_scan_box_detail_quantity);
                    text1.setText(text1.getText()+entry.getValue());
                }
            }
        }
        return layout;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
