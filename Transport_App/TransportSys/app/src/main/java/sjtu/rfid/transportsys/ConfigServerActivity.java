package sjtu.rfid.transportsys;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import tools.Data;

public class ConfigServerActivity extends Activity {

    final private Data data = (Data)getApplication();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_server);

        ((TextView)findViewById(R.id.text_title)).setText("配置服务器");
        ((Button)findViewById(R.id.btn_title_back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
