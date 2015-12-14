package sjtu.rfid.rfidsys;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rfid.service.Good;
import rfid.service.LocationInfo;
import rfid.service.check;
import sjtu.rfid.entity.CheckByMatEntity;
import sjtu.rfid.thread.CheckByMatThread;
import sjtu.rfid.thread.CheckThread;
import sjtu.rfid.tools.CheckByMatAdapter;
import sjtu.rfid.tools.TitleBar;

public class CheckByMatActivity extends Activity {

    private ListView sheetListView;
    private TitleBar mTitleBar;
    private CheckByMatAdapter mAdapter;
    private List<Map<String,String>> mCheckByMatList;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    private String CNum="";
    private CheckByMatThread checkByMatThread;
    private CheckByMatEntity checkByMatEntity;
    private CheckThread checkThread;
    private List<check> checkList;
    private boolean checkResult;


    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==0||msg.obj==null)
                Toast.makeText(getApplicationContext(), "获取信息失败", Toast.LENGTH_SHORT).show();
            checkByMatEntity=(CheckByMatEntity)msg.obj;
            iniListView(checkByMatEntity);
        }
    };

    private Handler handlerCheck=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==0||msg.obj==null)
                Toast.makeText(getApplicationContext(), "获取信息失败", Toast.LENGTH_SHORT).show();
            checkResult=(boolean)msg.obj;
            if(checkResult)
                Toast.makeText(getApplicationContext(), "提交成功", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getApplicationContext(), "提交失败", Toast.LENGTH_SHORT).show();
        }
    };

    private Handler handlerScanTag = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if( msg.what == 1 ) {
                mAdapter.notifyDataSetChanged();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_by_mat);
        iniActivity();
        iniEvent();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void iniActivity() {
        mTitleBar = new TitleBar(this,"货物盘点");
    }

    public void iniListView(CheckByMatEntity checkByMatEntity) {

        mCheckByMatList=new ArrayList<>();
        Good good=checkByMatEntity.getGood();
        List<LocationInfo> locationInfoList=checkByMatEntity.getLocationInfoList();

        sheetListView=(ListView)findViewById(R.id.list_check_by_mat_sheets);
        TextView vmatCode=(TextView)findViewById(R.id.text_check_by_mat_mat_code);
        vmatCode.setText(vmatCode.getText()+good.getCode());
        TextView vmatName=(TextView)findViewById(R.id.text_check_by_mat_mat_name);
        vmatName.setText(vmatName.getText() + good.getDetail());
        TextView visBom=(TextView)findViewById(R.id.text_check_by_mat_is_bom);
        visBom.setText(visBom.getText() + (good.isIs_Bom()?"Y":"N"));

        for(LocationInfo locationInfo:locationInfoList){
            Map<String,String> mapDetail=new HashMap<>();
            mapDetail.put("posDes",String.valueOf(locationInfo.getID()));
            mapDetail.put("expectedCount",String.valueOf(locationInfo.getNum()));
            mapDetail.put("readedCount","0");
            mCheckByMatList.add(mapDetail);
        }
        mAdapter = new CheckByMatAdapter(this,mCheckByMatList,handlerScanTag);
        sheetListView.setAdapter(mAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "CheckByMat Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://sjtu.rfid.rfidsys/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "CheckByMat Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://sjtu.rfid.rfidsys/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    public void iniEvent(){

        Button btnScanGetInfo=(Button)findViewById(R.id.btn_check_by_mat_scan_box_get_info);
        Button btnCommit=(Button)findViewById(R.id.btn_check_by_mat_commit);

        btnScanGetInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //读货位标签线程

                checkByMatThread=new CheckByMatThread(CNum,handler);
                checkByMatThread.start();
            }
        });
        btnCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkThread=new CheckThread(checkList,handlerCheck);
                checkThread.start();
            }
        });
    }
}
