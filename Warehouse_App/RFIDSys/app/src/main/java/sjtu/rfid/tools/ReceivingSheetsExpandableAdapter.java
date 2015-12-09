package sjtu.rfid.tools;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import sjtu.rfid.rfidsys.R;
import sjtu.rfid.rfidsys.ReceivingScanBoxActivity;

/**
 * Created by user on 12/6/2015.
 */
public class ReceivingSheetsExpandableAdapter extends BaseExpandableListAdapter {

    private LayoutInflater mLayoutInflater;
    private Map<String,Map<String,String>> mReceivingCodeDetailList;
    private List<String> mReceivingCodeList;
    private Context mContext;
    private  TextView codeLable;
    String receiveCode;

    public ReceivingSheetsExpandableAdapter(Context mContext,Map<String,Map<String,String>> mReceivingCodeDetailList, List<String> mReceivingCodeList){
        this.mContext = mContext;
        this.mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mReceivingCodeList = mReceivingCodeList;
        this.mReceivingCodeDetailList = mReceivingCodeDetailList;
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
        return mReceivingCodeList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        String key=mReceivingCodeList.get(groupPosition);
        return mReceivingCodeDetailList.get(key);

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
        RelativeLayout layout= (RelativeLayout) mLayoutInflater.inflate(R.layout.item_receiving_sheet, null);
        codeLable = (TextView) layout.findViewById(R.id.text_receiving_sheet_code);
        codeLable.setText(mReceivingCodeList.get(groupPosition));
        Button button = (Button) layout.findViewById(R.id.btn_receiving_sheet);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(mContext, ReceivingScanBoxActivity.class);
                //Bundle bundle=new Bundle();
                //bundle.putString("receiving_sheet_code",codeLable.getText().toString());
                //intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });
        return layout;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        RelativeLayout layout= (RelativeLayout) mLayoutInflater.inflate(R.layout.item_receiving_sheet_detail, null);
        //receiveCode=mReceivingCodeList.get(groupPosition);
        Map<String,String> map=mReceivingCodeDetailList.get(mReceivingCodeList.get(groupPosition));
        for(Map.Entry<String,String> entry:map.entrySet()){
            if(entry.getKey().equals("projectCode")){
                TextView text1 = (TextView) layout.findViewById(R.id.text_receiving_sheet_detail_project_code);
                text1.setText(text1.getText()+entry.getValue());
            }
            else if(entry.getKey().equals("orderDate")){
                TextView text1 = (TextView) layout.findViewById(R.id.text_receiving_sheet_detail_order_date);
                text1.setText(text1.getText()+entry.getValue());
            }
            else if(entry.getKey().equals("vendorName")){
                TextView text1 = (TextView) layout.findViewById(R.id.text_receiving_sheet_detail_vendor_name);
                text1.setText(text1.getText()+entry.getValue());
            }
            else if(entry.getKey().equals("applyPerson")){
                TextView text1 = (TextView) layout.findViewById(R.id.text_receiving_sheet_detail_apply_person);
                text1.setText(text1.getText()+entry.getValue());
            }
            else if(entry.getKey().equals("relatedBill")){
                TextView text1 = (TextView) layout.findViewById(R.id.text_receiving_sheet_detail_related_bill);
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
