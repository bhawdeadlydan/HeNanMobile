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
        RelativeLayout layout = (RelativeLayout) mLayoutInflater.inflate(R.layout.item_receiving_scan_box_detail,null);
        TextView
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
