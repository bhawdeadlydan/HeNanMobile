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
public class CheckByPosExpandableAdapter extends BaseExpandableListAdapter {

    private LayoutInflater mLayoutInflater;
    private Map<String,Map<String,String>> mCheckByPosDetailList;
    private List<Map<String,String>> mCheckByPosList;
    private Context mContext;

    public CheckByPosExpandableAdapter(Context mContext, Map<String, Map<String, String>> mCheckByPosDetailList, List<Map<String,String>> mCheckByPosList){
        this.mContext = mContext;
        this.mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mCheckByPosList = mCheckByPosList;
        this.mCheckByPosDetailList = mCheckByPosDetailList;
    }

    @Override
    public int getGroupCount() {
        return mCheckByPosList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mCheckByPosList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        String key=mCheckByPosList.get(groupPosition).get("matCode");
        return mCheckByPosDetailList.get(key);

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
        RelativeLayout layout= (RelativeLayout) mLayoutInflater.inflate(R.layout.item_check_by_pos_box, null);
        TextView codeLable = (TextView) layout.findViewById(R.id.text_check_by_pos_mat_code);
        TextView realCount = (TextView) layout.findViewById(R.id.text_check_by_pos_real_count);
        TextView expectedCount = (TextView) layout.findViewById(R.id.text_check_by_pos_expected_count);
        codeLable.setText(codeLable.getText()+mCheckByPosList.get(groupPosition).get("matCode"));
        realCount.setText(realCount.getText()+mCheckByPosList.get(groupPosition).get("realCount"));
        expectedCount.setText(expectedCount.getText()+mCheckByPosList.get(groupPosition).get("expectedCount"));
        return layout;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        RelativeLayout layout= (RelativeLayout) mLayoutInflater.inflate(R.layout.item_check_box_detail, null);
        Map<String,String> map=mCheckByPosDetailList.get(mCheckByPosList.get(groupPosition).get("matCode"));
        for(Map.Entry<String,String> entry:map.entrySet()){
            if(entry.getKey().equals("isBom")){
                TextView text1 = (TextView) layout.findViewById(R.id.text_check_box_detail_is_bom);
                text1.setText(text1.getText()+entry.getValue());
            }
            else if(entry.getKey().equals("matName")){
                TextView text1 = (TextView) layout.findViewById(R.id.text_check_box_detail_mat_name);
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
