package sjtu.rfid.rfidsys;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Properties;

import sjtu.rfid.entity.ConfigData;
import sjtu.rfid.tools.PropertiesUtil;
import sjtu.rfid.tools.TitleBar;

public class ConfigActivity extends Activity {

    private TitleBar mTitleBar;
    private Properties properties;
    private EditText vIP;
    private EditText vPort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        iniActivity();
        iniEvent();
    }

    public void iniActivity() {
        mTitleBar = new TitleBar(this,"配置信息");
        vIP=(EditText)findViewById(R.id.edittext_config_ip);
        vPort=(EditText)findViewById(R.id.edittext_config_port);
        properties= PropertiesUtil.loadConfig(ConfigActivity.this);
        if(!properties.isEmpty()){
            vIP.setText(properties.get("ip").toString());
            vPort.setText(properties.get("port").toString());
        }

    }

    public void iniEvent(){
        Button btnConfig=(Button)findViewById(R.id.btn_config_save);
        btnConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                properties=new Properties();
                properties.setProperty("ip",vIP.getText().toString());
                properties.setProperty("port", vPort.getText().toString());
                PropertiesUtil.saveConfig(ConfigActivity.this, properties);

                ConfigData.ip=vIP.getText().toString();
                ConfigData.port=Integer.parseInt(vPort.getText().toString());
                vIP.setText(vIP.getText().toString());
                vPort.setText(vPort.getText().toString());
                Toast.makeText(ConfigActivity.this,"保存成功",Toast.LENGTH_LONG).show();

            }
        });
    }
}
