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
public class MoveExpandableAdapter extends BaseExpandableListAdapter {

    private LayoutInflater mLayoutInflater;
    private Map<String,Map<String,String>> mMoveDetailList;
    private List<Map<String,String>> mMoveList;
    private Context mContext;

    public MoveExpandableAdapter(Context mContext, Map<String, Map<String, String>> mMoveDetailList, List<Map<String, String>> mMoveList){
        this.mContext = mContext;
        this.mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mMoveList = mMoveList;
        this.mMoveDetailList = mMoveDetailList;
    }

    @Override
    public int getGroupCount() {
        return mMoveList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mMoveList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        String key=mMoveList.get(groupPosition).get("boxCode");
        return mMoveDetailList.get(key);

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
        RelativeLayout layout= (RelativeLayout) mLayoutInflater.inflate(R.layout.item_move_box, null);
        TextView vboxCode = (TextView) layout.findViewById(R.id.text_move_box_code);
        TextView vmatName = (TextView) layout.findViewById(R.id.text_move_box_mat_name);
        TextView voriginLoc = (TextView) layout.findViewById(R.id.text_move_box_origin_loc);
        vboxCode.setText(vboxCode.getText()+mMoveList.get(groupPosition).get("boxCode"));
        vmatName.setText(vmatName.getText()+mMoveList.get(groupPosition).get("matName"));
        voriginLoc.setText(voriginLoc.getText()+mMoveList.get(groupPosition).get("originLoc"));
        return layout;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        RelativeLayout layout= (RelativeLayout) mLayoutInflater.inflate(R.layout.item_move_box, null);
        Map<String,String> map=mMoveDetailList.get(mMoveList.get(groupPosition).get("boxCode"));
        for(Map.Entry<String,String> entry:map.entrySet()){
            if(entry.getKey().equals("boxCode")){
                TextView text1 = (TextView) layout.findViewById(R.id.text_move_box_code);
                text1.setText(text1.getText()+entry.getValue());
            }
            else if(entry.getKey().equals("matName")){
                TextView text1 = (TextView) layout.findViewById(R.id.text_move_box_mat_name);
                text1.setText(text1.getText()+entry.getValue());
            }
            else if(entry.getKey().equals("originLoc")){
                TextView text1 = (TextView) layout.findViewById(R.id.text_move_box_origin_loc);
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
