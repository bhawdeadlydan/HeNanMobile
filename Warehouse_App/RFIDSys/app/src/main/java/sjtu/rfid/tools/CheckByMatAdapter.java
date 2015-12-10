package sjtu.rfid.tools;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;

import java.util.List;
import java.util.Map;

import sjtu.rfid.rfidsys.R;

/**
 * Created by user on 12/10/2015.
 */
public class CheckByMatAdapter extends BaseAdapter{

    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<Map<String,String>> mCheckByMatList;

    public CheckByMatAdapter(Context mContext, List<Map<String,String>> mCheckByMatList) {
        this.mContext = mContext;
        this.mLayoutInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mCheckByMatList = mCheckByMatList;
    }

    @Override
    public int getCount() {
        return 5;
        //return mCheckByMatList.size();
    }

    @Override
    public Object getItem(int position) {
        return mCheckByMatList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RelativeLayout layout = (RelativeLayout) mLayoutInflater.inflate(R.layout.item_check_by_mat_box,null);
        return layout;
    }
}
