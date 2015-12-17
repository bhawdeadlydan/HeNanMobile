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
 * Created by user on 12/6/2015.
 */
public class PutInStorageExpandableAdapter extends BaseExpandableListAdapter {

    private LayoutInflater mLayoutInflater;
    private Map<String,Map<String,String>> mPutInStorageDetailList;
    private List<Map<String,String>> mPutInStorageList;
    private Context mContext;


    public PutInStorageExpandableAdapter(Context mContext, Map<String, Map<String, String>> mPutInStorageDetailList, List<Map<String, String>> mPutInStorageList){
        this.mContext = mContext;
        this.mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mPutInStorageList = mPutInStorageList;
        this.mPutInStorageDetailList = mPutInStorageDetailList;
    }

    @Override
    public int getGroupCount() {
        return mPutInStorageList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mPutInStorageList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        String key=mPutInStorageList.get(groupPosition).get("boxCode");
        return mPutInStorageDetailList.get(key);

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

        RelativeLayout layout= (RelativeLayout) mLayoutInflater.inflate(R.layout.item_put_in_storage_box, null);
        if(mPutInStorageList.size()==0||mPutInStorageList==null)
            return layout;
        TextView vboxCode = (TextView) layout.findViewById(R.id.text_put_in_storage_box_code);
        TextView vmatName = (TextView) layout.findViewById(R.id.text_put_in_storage_mat_name);
        vboxCode.setText(mPutInStorageList.get(groupPosition).get("boxCode"));
        vmatName.setText(vmatName.getText()+mPutInStorageList.get(groupPosition).get("matName"));
        return layout;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        RelativeLayout layout= (RelativeLayout) mLayoutInflater.inflate(R.layout.item_put_in_storage_box_detail, null);
        if(mPutInStorageDetailList.size()==0||mPutInStorageDetailList==null)
            return layout;
        Map<String,String> map=mPutInStorageDetailList.get(mPutInStorageList.get(groupPosition).get("boxCode"));

        TextView text1 = (TextView) layout.findViewById(R.id.text_put_in_storage_box_detail_mat_code);
        text1.setText(text1.getText()+map.get("matCode"));
        TextView text2 = (TextView) layout.findViewById(R.id.text_put_in_storage_box_detail_unit);
        text2.setText(text2.getText()+map.get("unit"));
        TextView text3 = (TextView) layout.findViewById(R.id.text_put_in_storage_box_detail_count);
        text3.setText(text3.getText()+map.get("count"));
        TextView text4 = (TextView) layout.findViewById(R.id.text_put_in_storage_box_detail_project_code);
        text4.setText(text4.getText()+map.get("projectCode"));
        TextView text5 = (TextView) layout.findViewById(R.id.text_put_in_storage_box_detail_order_code);
        text5.setText(text5.getText()+map.get("asnCode"));

        return layout;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
}
