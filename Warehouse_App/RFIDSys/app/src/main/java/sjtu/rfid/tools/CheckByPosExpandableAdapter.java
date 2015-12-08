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
public class CheckByPosExpandableAdapter extends BaseExpandableListAdapter {

    private LayoutInflater mLayoutInflater;
    private Map<String,Map<String,String>> mGoodsDetailList;
    private List<String> mGoodsList;
    private Context mContext;
    private  TextView codeLable;

    public CheckByPosExpandableAdapter(Context mContext, Map<String, Map<String, String>> mGoodsDetailList, List<String> mGoodsList){
        this.mContext = mContext;
        this.mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mGoodsList = mGoodsList;
        this.mGoodsDetailList = mGoodsDetailList;
    }

    @Override
    public int getGroupCount() {
        return mGoodsList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mGoodsList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        String key=mGoodsList.get(groupPosition);
        return mGoodsDetailList.get(key);

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
        RelativeLayout layout= (RelativeLayout) mLayoutInflater.inflate(R.layout.item_checkByPos_scan_box, null);
        codeLable = (TextView) layout.findViewById(R.id.text_checkByPos_scan_box_code);
        codeLable.setText(mGoodsList.get(groupPosition));
        return layout;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        RelativeLayout layout= (RelativeLayout) mLayoutInflater.inflate(R.layout.item_box_detail, null);
        Map<String,String> map=mGoodsDetailList.get(mGoodsList.get(groupPosition));
        for(Map.Entry<String,String> entry:map.entrySet()){
            if(entry.getKey().equals("isBom")){
                TextView text1 = (TextView) layout.findViewById(R.id.text_box_detail_is_bom);
                text1.setText(text1.getText()+entry.getValue());
            }
            else if(entry.getKey().equals("itemCode")){
                TextView text1 = (TextView) layout.findViewById(R.id.text_box_detail_item_code);
                text1.setText(text1.getText()+entry.getValue());
            }
            else if(entry.getKey().equals("itemName")){
                TextView text1 = (TextView) layout.findViewById(R.id.text_box_detail_item_name);
                text1.setText(text1.getText()+entry.getValue());
            }
            else if(entry.getKey().equals("quantity")){
                TextView text1 = (TextView) layout.findViewById(R.id.text_box_detail_quantity);
                text1.setText(text1.getText()+entry.getValue());
            }
            else if(entry.getKey().equals("cartonOrderNumber")){
                TextView text1 = (TextView) layout.findViewById(R.id.text_box_detail_carton_order_num);
                text1.setText(text1.getText()+entry.getValue());
            }
            else if(entry.getKey().equals("cartonNumber")){
                TextView text1 = (TextView) layout.findViewById(R.id.text_box_detail_carton_num);
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
