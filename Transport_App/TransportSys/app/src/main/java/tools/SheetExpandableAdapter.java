package tools;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import sjtu.rfid.transportsys.ConfirmActivity;
import sjtu.rfid.transportsys.R;

/**
 * Created by shao on 2015/12/18.
 */
public class SheetExpandableAdapter extends BaseExpandableListAdapter {

    private LayoutInflater mLayoutInflater;
    private Map<String,Map<String,String>> mDeliveryCodeDetailList;
    private List<String> mDeliveryCodeList;
    private Context mContext;
    private TextView codeLable;

    public SheetExpandableAdapter(Context mContext, Map<String, Map<String, String>> mDeliveryCodeDetailList, List<String> mDeliveryCodeList){
        this.mContext = mContext;
        this.mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mDeliveryCodeList = mDeliveryCodeList;
        this.mDeliveryCodeDetailList = mDeliveryCodeDetailList;
    }

    @Override
    public int getGroupCount() {
        return mDeliveryCodeList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mDeliveryCodeList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        String key=mDeliveryCodeList.get(groupPosition);
        return mDeliveryCodeDetailList.get(key);
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
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        RelativeLayout layout= (RelativeLayout) mLayoutInflater.inflate(R.layout.item_sheet, null);
        codeLable = (TextView) layout.findViewById(R.id.text_sheet_code);;
        codeLable.setText(mDeliveryCodeList.get(groupPosition));

        Button button = (Button) layout.findViewById(R.id.btn_sheet);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(mContext, ConfirmActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("delivery_sheet_code", mDeliveryCodeList.get(groupPosition));
                String code = mDeliveryCodeList.get(groupPosition);
                Map<String,String> m = mDeliveryCodeDetailList.get(code);
                bundle.putString("applyUnit",m.get("applyUnit"));

                ArrayList list = new ArrayList();
                list.add(mDeliveryCodeList);
                bundle.putInt("index",groupPosition);

                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });
        return layout;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
