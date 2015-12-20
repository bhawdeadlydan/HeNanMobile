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
public class TmpConfirmAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    Map<String,Integer> mItemDetailList;
    List<String> mItemList;

    public TmpConfirmAdapter(Context mContext,Map<String,Integer> mItemDetailList, List<String> mItemList) {
        this.mContext = mContext;
        this.mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mItemDetailList = mItemDetailList;
        this.mItemList = mItemList;
    }

    @Override
    public int getCount() {
        return mItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RelativeLayout layout= (RelativeLayout) mLayoutInflater.inflate(R.layout.item_tmp_confirm, null);
        TextView vCode = (TextView)layout.findViewById(R.id.item_tmp_confirm_code);
        TextView vCnt = (TextView)layout.findViewById(R.id.item_tmp_confirm_cnt);
        String code = mItemList.get(position);
        vCode.setText("物料编码："+code);
        Integer cnt = mItemDetailList.get(code);
        vCnt.setText("物料数量："+cnt);
        return layout;
    }
}
