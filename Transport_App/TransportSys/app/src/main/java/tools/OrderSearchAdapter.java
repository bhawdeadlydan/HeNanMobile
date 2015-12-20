package tools;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import sjtu.rfid.transportsys.R;

/**
 * Created by shao on 2015/12/20.
 */
public class OrderSearchAdapter extends BaseAdapter {

    Context mContext;
    LayoutInflater mLayoutInflater;
    List<Map<String,String>> mSheetList;

    public OrderSearchAdapter(Context mContext,List<Map<String,String>> mSheetList) {
        this.mContext = mContext;
        this.mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mSheetList = mSheetList;
    }

    @Override
    public int getCount() {
        return mSheetList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RelativeLayout layout= (RelativeLayout) mLayoutInflater.inflate(R.layout.item_order_seach, null);
        TextView vOrder = (TextView)layout.findViewById(R.id.text_order_seach_code);
        TextView vStatus = (TextView)layout.findViewById(R.id.text_order_seach_status);
        TextView vDate = (TextView)layout.findViewById(R.id.text_order_seach_date);
        return layout;
    }
}
