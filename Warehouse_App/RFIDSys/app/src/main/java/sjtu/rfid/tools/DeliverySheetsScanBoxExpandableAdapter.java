package sjtu.rfid.tools;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import sjtu.rfid.rfidsys.R;

/**
 * Created by user on 12/7/2015.
 */
public class DeliverySheetsScanBoxExpandableAdapter extends BaseExpandableListAdapter {

    private Map<String,Map<String,String>> mDeliveryBoxesDetails;
    private List<Map<String,String>> mDeliveryBoxes;
    private List<Integer> mRealCountList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public DeliverySheetsScanBoxExpandableAdapter(Context mContext, Map<String, Map<String, String>> mDeliveryBoxesDetails, List<Map<String,String>> mDeliveryBoxes){
        this.mContext = mContext;
        this.mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mDeliveryBoxes = mDeliveryBoxes;
        this.mDeliveryBoxesDetails = mDeliveryBoxesDetails;

        this.mRealCountList = new ArrayList<>();

        for( int i = 0; i < mDeliveryBoxes.size(); i++ ) {
            mRealCountList.add(0);
        }
    }

    @Override
    public int getGroupCount() {

        return mDeliveryBoxes.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        String key = mDeliveryBoxes.get(groupPosition).get("matCode");
        return mDeliveryBoxesDetails.get(key).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mDeliveryBoxes.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        String key = mDeliveryBoxes.get(groupPosition).get("matCode");
        return mDeliveryBoxesDetails.get(key);
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
        RelativeLayout layout= (RelativeLayout) mLayoutInflater.inflate(R.layout.item_delivery_scan_box, null);
        TextView matCode = (TextView) layout.findViewById(R.id.text_delivery_scan_mat_code);
        TextView matName = (TextView) layout.findViewById(R.id.text_delivery_scan_mat_name);
        TextView expectedCount = (TextView) layout.findViewById(R.id.text_delivery_scan_expected_count);
        matCode.setText(matCode.getText()+mDeliveryBoxes.get(groupPosition).get("matCode"));
        matName.setText(matCode.getText()+mDeliveryBoxes.get(groupPosition).get("matName"));
        expectedCount.setText(expectedCount.getText()+mDeliveryBoxes.get(groupPosition).get("expectedCount"));

        return layout;
    }

    @Override
    public View getChildView(final int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        RelativeLayout layout= (RelativeLayout) mLayoutInflater.inflate(R.layout.item_delivery_scan_box_detail, null);

        Map<String,String> map=mDeliveryBoxesDetails.get(mDeliveryBoxes.get(groupPosition).get("matCode"));

        TextView text1 = (TextView) layout.findViewById(R.id.text_delivery_scan_box_detail_is_bom);
        text1.setText(text1.getText()+map.get("isBom"));


        TextView text2 = (TextView) layout.findViewById(R.id.text_delivery_scan_box_detail_box_list);
        text2.setText(text2.getText()+map.get("cartonList"));

        EditText realCount = (EditText) layout.findViewById(R.id.edittext_delivery_scan_box_scan_count);
        Integer t = mRealCountList.get(groupPosition);

        realCount.setText(String.valueOf(t));
        realCount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if( !s.toString().equals("") ) {
                    mRealCountList.set(groupPosition, Integer.valueOf(s.toString()));
                } else {
                    mRealCountList.set(groupPosition, 0);
                }
            }
        });
        return layout;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
