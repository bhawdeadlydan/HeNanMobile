package sjtu.rfid.tools;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import sjtu.rfid.rfidsys.R;

/**
 * Created by user on 12/10/2015.
 */
public class TitleBar {

    TextView mTitle;
    Button mBtnBack;
    Activity mActivity;

    public TitleBar(Activity activity,String title)
    {
        mActivity = activity;
        mTitle = (TextView)mActivity.findViewById(R.id.text_title);
        mBtnBack = (Button)mActivity.findViewById(R.id.btn_title_back);
        mTitle.setText(title);
        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.finish();
            }
        });
    }

}
