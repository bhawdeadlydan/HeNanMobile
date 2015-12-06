package sjtu.rfid.tools;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import sjtu.rfid.rfidsys.R;

/**
 * Created by user on 12/6/2015.
 */
public class ReceivingSheetsExpandableAdapter extends BaseExpandableListAdapter {

    private LayoutInflater mLayoutInflater;
    private List<String> mReceivingCodeList;
    private Context mContext;

    public ReceivingSheetsExpandableAdapter(Context mContext,List<String> mReceivingCodeList){
        this.mContext = mContext;
        this.mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mReceivingCodeList = mReceivingCodeList;
    }

    @Override
    public int getGroupCount() {
        return mReceivingCodeList.size();
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
        RelativeLayout layout= (RelativeLayout) mLayoutInflater.inflate(R.layout.item_receiving_sheet,null);
        TextView codeLable = (TextView) layout.findViewById(R.id.receiving_sheet_item_code);
        codeLable.setText(mReceivingCodeList.get(groupPosition));
        return layout;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        RelativeLayout layout= (RelativeLayout) mLayoutInflater.inflate(R.layout.item_receiving_sheet_detail,null);
        TextView text1 = (TextView) layout.findViewById(R.id.textReceivingSheetCode);
        text1.setText("text1111");
        TextView text2 = (TextView) layout.findViewById(R.id.textView5);
        text2.setText("text5555");
        return layout;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
