package sjtu.rfid.transportsys;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import baidu.poistion.service.LocationListener;
import sjtu.rfid.entity.ConfigData;
import tools.ConnectServer;
import tools.Data;
import tools.PropertiesUtil;

public class MainActivity extends Activity {

    private Button btnConfig, btnApply, btnArriveTmp, btnArriveConstruct,btnConfigServer;
    private MainButtonListener mBtnListener = new MainButtonListener();


    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = LocationListener.getInstance();

    private Data data;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(myListener);

        mLocationClient.start();
        mLocationClient.requestLocation();

        iniBtns();
        iniInfo();
        ConnectServer connectServer=new ConnectServer();
        if(!connectServer.isNetworkAvailable(this)){
            Toast.makeText(getApplicationContext(),"网络连接不可用",Toast.LENGTH_SHORT).show();
        }

        Properties properties= PropertiesUtil.loadConfig(getApplicationContext());
        if( properties.get("ip") == null || properties.get("port") == null )
            Toast.makeText(getApplicationContext(),"请您先配置服务器信息！",Toast.LENGTH_SHORT).show();
        else {
            ConfigData.ip=properties.get("ip").toString();
            ConfigData.port=Integer.valueOf(properties.get("port").toString());
        }

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void iniBtns() {

        btnConfig = (Button) findViewById(R.id.btn_main_config);
        btnApply = (Button) findViewById(R.id.btn_main_check);
        btnArriveTmp = (Button) findViewById(R.id.btn_main_arrive_tmp);
        btnArriveConstruct = (Button) findViewById(R.id.btn_main_arrive_construct);
        btnConfigServer=(Button)findViewById(R.id.btn_main_config_server);

        btnConfig.setOnClickListener(mBtnListener);
        btnApply.setOnClickListener(mBtnListener);
        btnArriveTmp.setOnClickListener(mBtnListener);
        btnArriveConstruct.setOnClickListener(mBtnListener);
        btnConfigServer.setOnClickListener(mBtnListener);

    }

    public void iniInfo() {
        data = (Data) getApplication();
        try {
            File filepath = this.getFilesDir();
            File file = new File(filepath.toString(),"info.txt");
            if (!file.exists()) {
                file.createNewFile();
                data.setName("");
                data.setPhone("");
                data.setCompany("");
                data.setRole("0");
            } else {
                InputStreamReader reader = new InputStreamReader(new FileInputStream(file), "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(reader);
                String line = null;
                if ((line = bufferedReader.readLine()) != null) {
                    data.setName(line);
                    data.setPhone(bufferedReader.readLine());
                    data.setCompany(bufferedReader.readLine());
                    data.setRole(bufferedReader.readLine());
                    return;
                } else {
                    data.setName("");
                    data.setPhone("");
                    data.setCompany("");
                    data.setRole("0");
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://sjtu.rfid.transportsys/http/host/path")
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
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://sjtu.rfid.transportsys/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    private class MainButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            switch (v.getId()) {
                case R.id.btn_main_config:
                    intent.setClass(MainActivity.this, ConfigActivity.class);
                    break;
                case R.id.btn_main_check:
                    intent.setClass(MainActivity.this, ConfirmActivity.class);
                    break;
                case R.id.btn_main_arrive_tmp:
                    intent.setClass(MainActivity.this, ArrivalActivity.class);
                    intent.putExtra("function", 0);

                    break;
                case R.id.btn_main_arrive_construct:
                    intent.setClass(MainActivity.this, ArrivalActivity.class);
                    intent.putExtra("function", 1);
                    break;
                case R.id.btn_main_config_server:
                    intent.setClass(MainActivity.this, ConfigServerActivity.class);
                    break;
            }
            startActivity(intent);
        }
    }
}
