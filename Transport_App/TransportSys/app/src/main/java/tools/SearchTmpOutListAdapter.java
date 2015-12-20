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

import rfid.service.stagingInfo;
import sjtu.rfid.transportsys.R;

/**
 * Created by shao on 2015/12/20.
 */
public class SearchTmpOutListAdapter extends BaseAdapter {

    Context mContext;
    LayoutInflater mLayoutInflater;
    private List<stagingInfo> out;

    public  SearchTmpOutListAdapter(Context mContext,List<stagingInfo> out) {
        this.mContext = mContext;
        this.mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.out = out;
    }

    @Override
    public int getCount() {
        return out.size();
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
        stagingInfo s=out.get(position);
        RelativeLayout layout= (RelativeLayout) mLayoutInflater.inflate(R.layout.item_out_list, null);
        TextView vCode = (TextView)layout.findViewById(R.id.text_out_list_code);
        TextView vCnt = (TextView)layout.findViewById(R.id.text_out_list_cnt);
        TextView vDate = (TextView)layout.findViewById(R.id.text_out_list_date);
        TextView vPerson = (TextView)layout.findViewById(R.id.text_out_list_person);
        vCode.setText("物料编码："+s.getMaterialCode());
        vCnt.setText("物料数量："+s.getNum());
        vDate.setText("申领时间："+s.getTime());
        vPerson.setText("申领人："+s.getApplyPerson());
        return layout;
    }
}
