package sjtu.rfid.tools;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import sjtu.rfid.rfidsys.R;

/**
 * Created by user on 12/7/2015.
 */
public class ReceivingSheetsScanBoxExpandableAdapter extends BaseExpandableListAdapter {

    private Map<String,Map<String,String>> mReceivingBoxesDetails;
    private List<Map<String,String>> mReceivingBoxes;
    private List<Integer> mRealCountList;
    private Map<String, Set<String>> mReceivingBoxesItemsList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public ReceivingSheetsScanBoxExpandableAdapter(Context mContext,Map<String,Map<String,String>> mReceivingBoxesDetails, List<Map<String,String>> mReceivingBoxes,Map<String, Set<String>> mReceivingBoxesItemsList){
        this.mContext = mContext;
        this.mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mReceivingBoxes = mReceivingBoxes;
        this.mReceivingBoxesDetails = mReceivingBoxesDetails;
        this.mRealCountList = new ArrayList<>();
        this.mReceivingBoxesItemsList = mReceivingBoxesItemsList;

        for( int i = 0; i < mReceivingBoxes.size(); i++ ) {
            mRealCountList.add(0);
        }

    }

    @Override
    public int getGroupCount() {

        return mReceivingBoxes.size();

    }

    @Override
    public int getChildrenCount(int groupPosition) {

        return 1;

    }

    @Override
    public Object getGroup(int groupPosition) {

        return mReceivingBoxes.get(groupPosition);

    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {

        String key = mReceivingBoxes.get(groupPosition).get("matCode");
        return mReceivingBoxesDetails.get(key);

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

        RelativeLayout layout= (RelativeLayout) mLayoutInflater.inflate(R.layout.item_receiving_scan_box, null);
        TextView matCode = (TextView) layout.findViewById(R.id.text_receiving_scan_box_mat_code);
        TextView matName = (TextView) layout.findViewById(R.id.text_receiving_scan_box_mat_name);
        TextView boxCount = (TextView) layout.findViewById(R.id.text_receiving_scan_box_count);
        matCode.setText(matCode.getText()+mReceivingBoxes.get(groupPosition).get("matCode"));
        matName.setText(matName.getText()+mReceivingBoxes.get(groupPosition).get("matName"));
        boxCount.setText(boxCount.getText()+mReceivingBoxes.get(groupPosition).get("boxCount"));
        return layout;

    }

    @Override
    public View getChildView(final int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        RelativeLayout layout= (RelativeLayout) mLayoutInflater.inflate(R.layout.item_receiving_scan_box_detail, null);
        Map<String,String> map=mReceivingBoxesDetails.get(mReceivingBoxes.get(groupPosition).get("matCode"));

        TextView text1 = (TextView) layout.findViewById(R.id.text_receiving_scan_box_detail_is_bom);
        text1.setText(text1.getText()+map.get("isBom"));


        TextView text2 = (TextView) layout.findViewById(R.id.text_receiving_scan_box_detail_box_list);
        text2.setText(text2.getText() + map.get("cartonList"));

        EditText realCount = (EditText) layout.findViewById(R.id.edittext_receiving_scan_box_count_count);


        String matCode = mReceivingBoxes.get(groupPosition).get("matCode");
        Integer t = mReceivingBoxesItemsList.get(matCode).size();
        mRealCountList.set(groupPosition,t);

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
