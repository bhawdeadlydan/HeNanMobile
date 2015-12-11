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
        TextView vboxCode = (TextView) layout.findViewById(R.id.text_put_in_storage_box_code);
        TextView vmatName = (TextView) layout.findViewById(R.id.text_put_in_storage_mat_name);
        vboxCode.setText(mPutInStorageList.get(groupPosition).get("boxCode"));
        vmatName.setText(vmatName.getText()+mPutInStorageList.get(groupPosition).get("matName"));
        return layout;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        RelativeLayout layout= (RelativeLayout) mLayoutInflater.inflate(R.layout.item_put_in_storage_box_detail, null);
        Map<String,String> map=mPutInStorageDetailList.get(mPutInStorageList.get(groupPosition).get("boxCode"));
        for(Map.Entry<String,String> entry:map.entrySet()){
            if(entry.getKey().equals("matCode")){
                TextView text1 = (TextView) layout.findViewById(R.id.text_put_in_storage_box_detail_mat_code);
                text1.setText(text1.getText()+entry.getValue());
            }
            else if(entry.getKey().equals("unit")){
                TextView text1 = (TextView) layout.findViewById(R.id.text_put_in_storage_box_detail_unit);
                text1.setText(text1.getText()+entry.getValue());
            }
            else if(entry.getKey().equals("count")){
                TextView text1 = (TextView) layout.findViewById(R.id.text_put_in_storage_box_detail_count);
                text1.setText(text1.getText()+entry.getValue());
            }
        }
        return layout;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
