package sjtu.rfid.tools;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import sjtu.rfid.rfidsys.R;
import sjtu.rfid.thread.CheckByMatScanTagThread;

/**
 * Created by user on 12/10/2015.
 */
public class CheckByMatAdapter extends BaseAdapter{

    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<Map<String,String>> mCheckByMatList;

    private CheckByMatScanTagThread checkByMatScanTagThread;

    private Handler mHandler;


    public CheckByMatAdapter(Context mContext, List<Map<String,String>> mCheckByMatList, Handler mHandler) {
        this.mContext = mContext;
        this.mLayoutInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mCheckByMatList = mCheckByMatList;
        this.mHandler = mHandler;
    }

    @Override
    public int getCount() {
        return mCheckByMatList.size();
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
        Map<String,String> map=mCheckByMatList.get(position);
        TextView vPosDes=(TextView)layout.findViewById(R.id.text_check_by_mat_loc);
        TextView vECount=(TextView)layout.findViewById(R.id.text_check_by_mat_expected_count);
        TextView vSCount=(TextView)layout.findViewById(R.id.text_check_by_mat_real_count);
        TextView vReadedCount = (TextView)layout.findViewById(R.id.text_check_by_mat_real_count);

        vPosDes.setText(vPosDes.getText()+map.get("posDes"));
        vECount.setText(vECount.getText()+map.get("expectedCount"));
        vSCount.setText(vSCount.getText() + "0");
        vReadedCount.setText("扫到数量"+Integer.valueOf(map.get("readedCount")));

        Button scanButton = (Button)layout.findViewById(R.id.btn_check_by_mat_scan_box);
        scanButton.setOnClickListener(new MyClickListener(map.get("matCode"),Integer.valueOf(map.get("expectedCount"))));
        return layout;
    }

    public class MyClickListener implements View.OnClickListener
    {
        String code;
        Boolean isReading;
        Integer expectedCnt;

        public MyClickListener(String code,Integer expectedCnt) {
            this.code = code;
            this.isReading = false;
            this.expectedCnt = expectedCnt;
        }

        @Override
        public void onClick(View v) {
            if( !isReading ) {
                isReading = true;
                checkByMatScanTagThread = new CheckByMatScanTagThread(code, isReading, expectedCnt, mHandler);
                checkByMatScanTagThread.start();
            } else if ( isReading ) {
                isReading = false;
                checkByMatScanTagThread.SetIsReading(isReading);
            }
        }
    }
}
