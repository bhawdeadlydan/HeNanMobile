package sjtu.rfid.transportsys;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Properties;

import sjtu.rfid.entity.ConfigData;
import tools.Data;
import tools.PropertiesUtil;

public class ConfigServerActivity extends Activity {

    private EditText vIP;
    private EditText vPort;
    private Properties properties;

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
        iniActivity();
        iniEvent();
    }
    public void iniActivity() {
        vIP=(EditText)findViewById(R.id.edittext_config_server_ip);
        vPort=(EditText)findViewById(R.id.edittext_config_server_port);
        properties= PropertiesUtil.loadConfig(ConfigServerActivity.this);
        if(!properties.isEmpty()){
            vIP.setText(properties.get("ip").toString());
            vPort.setText(properties.get("port").toString());
        }
    }

    public void iniEvent(){
        Button btnConfig=(Button)findViewById(R.id.btn_config_server_save);
        btnConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ip=vIP.getText().toString();
                String port=vPort.getText().toString();
                if (ip.equals("") || port.equals("") )
                    Toast.makeText(getApplicationContext(), "您未完成全部信息的填选！", Toast.LENGTH_SHORT).show();
                else {

                    properties=new Properties();
                    properties.setProperty("ip",ip);
                    properties.setProperty("port", port);
                    PropertiesUtil.saveConfig(ConfigServerActivity.this, properties);

                    ConfigData.ip=vIP.getText().toString();
                    ConfigData.port=Integer.parseInt(vPort.getText().toString());
                    Toast.makeText(ConfigServerActivity.this,"保存成功",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
