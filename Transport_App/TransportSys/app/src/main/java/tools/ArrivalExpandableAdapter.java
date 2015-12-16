package tools;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import sjtu.rfid.transportsys.R;

/**
 * Created by user on 12/6/2015.
 */
public class ArrivalExpandableAdapter extends BaseExpandableListAdapter {

    private LayoutInflater mLayoutInflater;
    private Map<String,Map<String,String>> mArrivalDetailList;
    private List<Map<String,String>> mArrivalList;
    private Context mContext;

    public ArrivalExpandableAdapter(Context mContext, Map<String, Map<String, String>> mArrivalDetailList, List<Map<String, String>> mArrivalList){
        this.mContext = mContext;
        this.mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mArrivalList = mArrivalList;
        this.mArrivalDetailList = mArrivalDetailList;
    }

    @Override
    public int getGroupCount() {
        return mArrivalList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mArrivalList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        String key=mArrivalList.get(groupPosition).get("matCode");
        return mArrivalDetailList.get(key);

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
        RelativeLayout layout= (RelativeLayout) mLayoutInflater.inflate(R.layout.item_box, null);
        TextView codeLable = (TextView) layout.findViewById(R.id.text_item_box_mat_code);
        TextView realCount = (TextView) layout.findViewById(R.id.text_item_box_real_count);
        TextView expectedCount = (TextView) layout.findViewById(R.id.text_item_box_expected_count);
        codeLable.setText(codeLable.getText()+mArrivalList.get(groupPosition).get("matCode"));
        realCount.setText(realCount.getText()+mArrivalList.get(groupPosition).get("realCount"));
        expectedCount.setText(expectedCount.getText()+mArrivalList.get(groupPosition).get("expectedCount"));
        return layout;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        RelativeLayout layout= (RelativeLayout) mLayoutInflater.inflate(R.layout.item_box_detail, null);
        Map<String,String> map=mArrivalDetailList.get(mArrivalList.get(groupPosition).get("matCode"));
        TextView text1 = (TextView) layout.findViewById(R.id.text_box_detail_is_bom);
        text1.setText(text1.getText()+map.get("isBom"));
        TextView text2 = (TextView) layout.findViewById(R.id.text_box_detail_mat_name);
        text2.setText(text2.getText()+map.get("matName"));
        TextView text3 = (TextView) layout.findViewById(R.id.text_box_detail_unit);
        text3.setText(text3.getText()+map.get("unit"));

        return layout;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
