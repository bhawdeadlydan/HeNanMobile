package sjtu.rfid.tools;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.util.List;
import java.util.Map;

import sjtu.rfid.rfidsys.MoveActivity;
import sjtu.rfid.rfidsys.R;

/**
 * Created by user on 12/10/2015.
 */
public class MoveOrderAdapter extends BaseAdapter {

    private List<Map<String,String>> mOrders;
    private LayoutInflater mLayoutInflater;
    private Context mContext;

    public MoveOrderAdapter(Context mContext, List<Map<String,String>> mOrders) {
        this.mContext = mContext;
        this.mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mOrders = mOrders;
    }
    @Override
    public int getCount() {
        //return mOrders.size();
        return 5;
    }

    @Override
    public Object getItem(int position) {
        return mOrders.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RelativeLayout layout= (RelativeLayout) mLayoutInflater.inflate(R.layout.item_move_order_sheet, null);
        Button btn = (Button) layout.findViewById(R.id.btn_move_order_move);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(mContext, MoveActivity.class);
                mContext.startActivity(intent);
            }
        });
        return layout;
    }
}
