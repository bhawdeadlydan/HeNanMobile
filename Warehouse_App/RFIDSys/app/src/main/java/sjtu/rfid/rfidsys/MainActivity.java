package sjtu.rfid.rfidsys;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Properties;

import sjtu.rfid.entity.ConfigData;
import sjtu.rfid.tools.ConnectServer;
import sjtu.rfid.tools.PropertiesUtil;

public class MainActivity extends Activity {

    Button btnReceiving,btnPutInStorage,btnMoveBox,btnMatCheck,btnLocCheck,btnDelivery,benConfig;
    MainButtonListener btnListener = new MainButtonListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniBtns();
        ConnectServer connectServer=new ConnectServer();
        if(!connectServer.isNetworkAvailable(this)){
            Toast.makeText(getApplicationContext(),"网络连接不可用",Toast.LENGTH_SHORT).show();
        }

        Properties properties=PropertiesUtil.loadConfig(getApplicationContext());
        if( properties.get("ip") == null || properties.get("port") == null )
            Toast.makeText(getApplicationContext(),"请您先配置服务器信息！",Toast.LENGTH_LONG).show();
        else {
            ConfigData.ip=properties.get("ip").toString();
            ConfigData.port=Integer.valueOf(properties.get("port").toString());
;       }
    }

    public void iniBtns() {

        btnReceiving = (Button)findViewById(R.id.btn_receiving);
        btnPutInStorage = (Button)findViewById(R.id.btn_put_in_storage);
        btnMoveBox = (Button)findViewById(R.id.btn_move_box);
        btnMatCheck = (Button)findViewById(R.id.btn_mat_cehck);
        btnLocCheck = (Button)findViewById(R.id.btn_loc_check);
        btnDelivery = (Button)findViewById(R.id.btn_delivery);
        benConfig=(Button)findViewById(R.id.btn_config);

        btnReceiving.setOnClickListener(btnListener);
        btnPutInStorage.setOnClickListener(btnListener);
        btnMoveBox.setOnClickListener(btnListener);
        btnMatCheck.setOnClickListener(btnListener);
        btnLocCheck.setOnClickListener(btnListener);
        btnDelivery.setOnClickListener(btnListener);
        benConfig.setOnClickListener(btnListener);

    }

    private class MainButtonListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            switch (v.getId()) {
                case R.id.btn_receiving:
                    intent.setClass(MainActivity.this, ReceivingSheetsActivity.class);
                    break;
                case R.id.btn_put_in_storage:
                    intent.setClass(MainActivity.this, PutInStorageActivity.class);
                    break;
                case R.id.btn_move_box:
                    intent.setClass(MainActivity.this, MoveOrderListActivity.class);
                    break;
                case R.id.btn_mat_cehck:
                    intent.setClass(MainActivity.this, CheckByMatActivity.class);
                    break;
                case R.id.btn_loc_check:
                    intent.setClass(MainActivity.this, CheckByPosActivity.class);
                    break;
                case R.id.btn_delivery:
                    intent.setClass(MainActivity.this, DeliverySheetsActivity.class);
                    break;
                case R.id.btn_config:
                    intent.setClass(MainActivity.this, ConfigActivity.class);
                    break;
            }
            startActivity(intent);
        }
    }

}
