package sjtu.rfid.tools;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import sjtu.rfid.entity.Config;
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
    boolean isReading = false;
    private Map<Integer,Integer> mPosMap;


    public CheckByMatAdapter(Context mContext, List<Map<String,String>> mCheckByMatList, Handler mHandler,Map<Integer,Integer> posMap) {
        this.mContext = mContext;
        this.mLayoutInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mCheckByMatList = mCheckByMatList;
        this.mHandler = mHandler;
        this.mPosMap = posMap;
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

        vPosDes.setText(vPosDes.getText()+Config.Location.get(Integer.valueOf(map.get("posDes"))-1));
        vECount.setText(vECount.getText()+map.get("expectedCount"));
        vSCount.setText(vSCount.getText() + "0");
        //vReadedCount.setText("扫到数量"+Integer.valueOf(map.get("readedCount")));
        vReadedCount.setText("扫到数量"+mPosMap.get(Integer.valueOf(map.get("posDes"))));

        Button scanButton = (Button)layout.findViewById(R.id.btn_check_by_mat_scan_box);
        scanButton.setOnClickListener(new MyClickListener(map.get("matCode"),Integer.valueOf(map.get("expectedCount")),
                Integer.valueOf(map.get("posDes"))));
        return layout;
    }

    public class MyClickListener implements View.OnClickListener
    {
        String code;
        Boolean isReading;
        Integer expectedCnt;
        Integer pos;

        public MyClickListener(String code,Integer expectedCnt,Integer pos) {
            this.code = code;
            this.isReading = false;
            this.expectedCnt = expectedCnt;
            this.pos = pos;
        }

        @Override
        public void onClick(View v) {

            Message msg = mHandler.obtainMessage();
            if( !isReading ) {
                isReading = true;
                msg.what = 0;// 0 for start
                msg.obj = pos;
                mHandler.sendMessage(msg);
            } else {
                isReading = false;
                msg.what = 1;// 1 for stop
                mHandler.sendMessage(msg);
            }


/*            if( !isReading ) {
                isReading = true;
                checkByMatScanTagThread = new CheckByMatScanTagThread(code, isReading, expectedCnt, mHandler);
                checkByMatScanTagThread.start();
            } else if ( isReading ) {
                isReading = false;
                checkByMatScanTagThread.SetIsReading(isReading);
            }*/
        }
    }
}
