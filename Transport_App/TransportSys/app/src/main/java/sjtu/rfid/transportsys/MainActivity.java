package sjtu.rfid.transportsys;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.THttpClient;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

import java.net.InetSocketAddress;
import java.util.List;

import rfid.service.RFIDService;
import sjtu.rfid.thread.GeoCoderThread;
import sjtu.rfid.thread.TestThread;

public class MainActivity extends AppCompatActivity {

    private Button btnConfig, btnApply, btnArriveTmp, btnArriveConstruct;
    private MainButtonListener mBtnListener = new MainButtonListener();

    private TestThread testThread;


    private Handler TestHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Toast.makeText(getApplicationContext(), msg.obj.toString(), Toast.LENGTH_LONG).show();
        }
    };

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniBtns();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void iniBtns() {

        btnConfig = (Button) findViewById(R.id.btn_main_config);
        btnApply = (Button) findViewById(R.id.btn_main_check);
        btnArriveTmp = (Button) findViewById(R.id.btn_main_arrive_tmp);
        btnArriveConstruct = (Button) findViewById(R.id.btn_main_arrive_construct);

        btnConfig.setOnClickListener(mBtnListener);
        btnApply.setOnClickListener(mBtnListener);
        btnArriveTmp.setOnClickListener(mBtnListener);
        btnArriveConstruct.setOnClickListener(mBtnListener);

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
                    testThread=new TestThread(TestHandler);
                    testThread.start();
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
            }
            startActivity(intent);
        }
    }
}
