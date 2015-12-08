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
public class ReceivingSheetsScanBoxExpandableAdapter extends BaseExpandableListAdapter {

    private Map<String,List<Map<String,String>>> mReceivingBoxesDetails;
    private List<String> mReceivingBoxes;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private String receiveCode;

    public ReceivingSheetsScanBoxExpandableAdapter(Context mContext,Map<String,List<Map<String,String>>> mReceivingBoxesDetails, List<String> mReceivingBoxes){
        this.mContext = mContext;
        this.mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mReceivingBoxes = mReceivingBoxes;
        this.mReceivingBoxesDetails = mReceivingBoxesDetails;
    }

    @Override
    public int getGroupCount() {

        return mReceivingBoxes.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        String key = mReceivingBoxes.get(groupPosition);
        return mReceivingBoxesDetails.get(key).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mReceivingBoxes.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        String key = mReceivingBoxes.get(groupPosition);
        return mReceivingBoxesDetails.get(key);
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
        RelativeLayout layout= (RelativeLayout) mLayoutInflater.inflate(R.layout.item_receiving_scan_box, null);
        TextView code = (TextView) layout.findViewById(R.id.text_receiving_scan_box_code);
        code.setText(mReceivingBoxes.get(groupPosition));
        return layout;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        RelativeLayout layout= (RelativeLayout) mLayoutInflater.inflate(R.layout.item_box_detail, null);
        receiveCode=mReceivingBoxes.get(groupPosition);
        List<Map<String,String>> mapList=mReceivingBoxesDetails.get(receiveCode);
        for(int i=0;i<mapList.size();i++){
            Map<String,String> map=mapList.get(i);
            for(Map.Entry<String,String> entry:map.entrySet()){
                if(entry.getKey().equals("isBom")){
                    TextView text1 = (TextView) layout.findViewById(R.id.text_box_detail_is_bom);
                    text1.setText(text1.getText()+entry.getValue());
                }
                else if(entry.getKey().equals("itemCode")){
                    TextView text1 = (TextView) layout.findViewById(R.id.text_box_detail_item_code);
                    text1.setText(text1.getText()+entry.getValue());
                }
                else if(entry.getKey().equals("itemName")){
                    TextView text1 = (TextView) layout.findViewById(R.id.text_box_detail_item_name);
                    text1.setText(text1.getText()+entry.getValue());
                }
                else if(entry.getKey().equals("quantity")){
                    TextView text1 = (TextView) layout.findViewById(R.id.text_box_detail_quantity);
                    text1.setText(text1.getText()+entry.getValue());
                }
                else if(entry.getKey().equals("cartonOrderNumber")){
                    TextView text1 = (TextView) layout.findViewById(R.id.text_box_detail_carton_order_num);
                    text1.setText(text1.getText()+entry.getValue());
                }
                else if(entry.getKey().equals("cartonNumber")){
                    TextView text1 = (TextView) layout.findViewById(R.id.text_box_detail_carton_num);
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
