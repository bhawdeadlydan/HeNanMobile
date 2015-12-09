package sjtu.rfid.tools;

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

import java.util.List;
import java.util.Map;

import sjtu.rfid.rfidsys.DeliveryScanBoxActivity;
import sjtu.rfid.rfidsys.R;

/**
 * Created by user on 12/6/2015.
 */
public class DeliverySheetsExpandableAdapter extends BaseExpandableListAdapter {

    private LayoutInflater mLayoutInflater;
    private Map<String,Map<String,String>> mDeliveryCodeDetailList;
    private List<String> mDeliveryCodeList;
    private Context mContext;
    private  TextView codeLable;

    public DeliverySheetsExpandableAdapter(Context mContext, Map<String, Map<String, String>> mDeliveryCodeDetailList, List<String> mDeliveryCodeList){
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
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        RelativeLayout layout= (RelativeLayout) mLayoutInflater.inflate(R.layout.item_delivery_sheet, null);
        codeLable = (TextView) layout.findViewById(R.id.text_delivery_sheet_code);
        codeLable.setText(codeLable.getText()+mDeliveryCodeList.get(groupPosition));
        Button button = (Button) layout.findViewById(R.id.btn_delivery_sheet);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(mContext, DeliveryScanBoxActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("delivery_sheet_code",codeLable.getText().toString());
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });
        return layout;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        RelativeLayout layout= (RelativeLayout) mLayoutInflater.inflate(R.layout.item_delivery_sheet_detail, null);
        Map<String,String> map=mDeliveryCodeDetailList.get(mDeliveryCodeList.get(groupPosition));
        for(Map.Entry<String,String> entry:map.entrySet()){
            if(entry.getKey().equals("applyPerson")){
                TextView text1 = (TextView) layout.findViewById(R.id.text_delivery_sheet_detail_apply_person);
                text1.setText(text1.getText()+entry.getValue());
            }
            else if(entry.getKey().equals("projectCode")){
                TextView text1 = (TextView) layout.findViewById(R.id.text_delivery_sheet_detail_project_code);
                text1.setText(text1.getText()+entry.getValue());
            }
            else if(entry.getKey().equals("applyUnit")){
                TextView text1 = (TextView) layout.findViewById(R.id.text_delivery_sheet_detail_apply_unit);
                text1.setText(text1.getText()+entry.getValue());
            }
            else if(entry.getKey().equals("applyDate")){
                TextView text1 = (TextView) layout.findViewById(R.id.text_delivery_sheet_detail_apply_date);
                text1.setText(text1.getText()+entry.getValue());
            }
            else if(entry.getKey().equals("receiver")){
                TextView text1 = (TextView) layout.findViewById(R.id.text_delivery_sheet_detail_receiver);
                text1.setText(text1.getText()+entry.getValue());
            }
        }
        //Button button = (Button) layout.findViewById(R.id.btn_receiving_sheet);
        /*button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(mContext, ReceivingScanBoxActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("receiving_sheet_code",receiveCode);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });*/
        return layout;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
