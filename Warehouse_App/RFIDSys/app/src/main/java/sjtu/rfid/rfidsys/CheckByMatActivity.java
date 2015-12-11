package sjtu.rfid.rfidsys;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
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

import rfid.service.ASN;
import rfid.service.Good;
import rfid.service.LocationInfo;
import sjtu.rfid.entity.CheckByMatEntity;
import sjtu.rfid.thread.CheckByMatThread;
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

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==0||msg.obj==null)
                Toast.makeText(getApplicationContext(), "获取信息失败", Toast.LENGTH_SHORT).show();
            checkByMatEntity=(CheckByMatEntity)msg.obj;
            iniListView(checkByMatEntity);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_by_mat);
        iniActivity();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        checkByMatThread=new CheckByMatThread(CNum,handler);
        checkByMatThread.start();
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
            mCheckByMatList.add(mapDetail);
        }
        mAdapter = new CheckByMatAdapter(this,mCheckByMatList);
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
}
