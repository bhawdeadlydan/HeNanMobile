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

import rfid.service.inStagingInfo;
import sjtu.rfid.transportsys.R;

/**
 * Created by shao on 2015/12/20.
 */
public class SearchTmpInListAdapter extends BaseAdapter {

    Context mContext;
    LayoutInflater mLayoutInflater;
    private List<inStagingInfo> in;

    public SearchTmpInListAdapter(Context mContext,List<inStagingInfo> in)
    {
        this.mContext = mContext;
        this.mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.in  = in;
    }
    @Override
    public int getCount() {
        return in.size();
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
        inStagingInfo i=in.get(position);
        RelativeLayout layout= (RelativeLayout) mLayoutInflater.inflate(R.layout.item_in_list, null);
        TextView vCode = (TextView)layout.findViewById(R.id.text_in_list_code);
        TextView vCnt = (TextView)layout.findViewById(R.id.text_in_list_cnt);
        TextView vDate = (TextView)layout.findViewById(R.id.text_in_list_date);
        vCode.setText("物料编码："+i.getMaterialCode());
        vCnt.setText("物料数量："+i.getNum());
        vDate.setText("入库时间："+i.getTime());
        return layout;
    }
}
