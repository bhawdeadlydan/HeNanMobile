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
 * Created by user on 12/7/2015.
 */
public class ConfirmExpandableListAdapter extends BaseExpandableListAdapter {

    private LayoutInflater mLayoutInflater;
    private Map<String,Map<String,String>> mBoxDetailList;
    private List<String> mBoxList;
    private Context mContext;

    public ConfirmExpandableListAdapter(Context mContext, Map<String,Map<String,String>> mBoxDetailList, List<String> mBoxList)
    {
        this.mContext = mContext;
        this.mBoxDetailList = mBoxDetailList;
        this.mBoxList = mBoxList;
        this.mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getGroupCount() {
//        return mBoxList.size();
        return 15;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        RelativeLayout layout = (RelativeLayout) mLayoutInflater.inflate(R.layout.item_box,null);
        TextView boxCodeLabel = (TextView) layout.findViewById(R.id.text_box_code);
        boxCodeLabel.setText("货箱号：000000000000000"+groupPosition);
        return layout;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        RelativeLayout layout = (RelativeLayout) mLayoutInflater.inflate(R.layout.item_box_detail,null);
        TextView projectCode  = (TextView) layout.findViewById(R.id.text_box_detail_project_code);
        TextView applyPerson = (TextView) layout.findViewById(R.id.text_box_detail_apply_person);
        TextView num = (TextView) layout.findViewById(R.id.text_box_detail_num);
        projectCode.setText("项目编码：1234567890");
        applyPerson.setText("项目负责人：张三");
        num.setText("数量：3");
        return layout;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
