package sjtu.rfid.rfidsys;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import sjtu.rfid.tools.ConnectServer;

public class MainActivity extends Activity {

    Button btnReceiving,btnPutInStorage,btnMoveBox,btnMatCheck,btnLocCheck,btnDelivery;
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
;
    }

    public void iniBtns() {

        btnReceiving = (Button)findViewById(R.id.btn_receiving);
        btnPutInStorage = (Button)findViewById(R.id.btn_put_in_storage);
        btnMoveBox = (Button)findViewById(R.id.btn_move_box);
        btnMatCheck = (Button)findViewById(R.id.btn_mat_cehck);
        btnLocCheck = (Button)findViewById(R.id.btn_loc_check);
        btnDelivery = (Button)findViewById(R.id.btn_delivery);

        btnReceiving.setOnClickListener(btnListener);
        btnPutInStorage.setOnClickListener(btnListener);
        btnMoveBox.setOnClickListener(btnListener);
        btnMatCheck.setOnClickListener(btnListener);
        btnLocCheck.setOnClickListener(btnListener);
        btnDelivery.setOnClickListener(btnListener);

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
            }
            startActivity(intent);
        }
    }

}
