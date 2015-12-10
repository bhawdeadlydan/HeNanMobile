package sjtu.rfid.rfidsys;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

import sjtu.rfid.tools.CheckByMatAdapter;
import sjtu.rfid.tools.TitleBar;

public class CheckByMatActivity extends Activity {

    private ListView sheetListView;
    private TitleBar mTitleBar;
    private CheckByMatAdapter mAdapter;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_by_mat);
        iniActivity();
        iniListView();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void iniActivity() {
        mTitleBar = new TitleBar(this,"货物盘点");
    }

    public void iniListView() {

        sheetListView=(ListView)findViewById(R.id.list_check_by_mat_sheets);
//        sheetListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getData()));
        TextView vmatCode=(TextView)findViewById(R.id.text_check_by_mat_mat_code);
        vmatCode.setText("1111111");
        TextView vmatName=(TextView)findViewById(R.id.text_check_by_mat_mat_name);
        vmatName.setText(vmatName.getText()+"光交箱");
        TextView visBom=(TextView)findViewById(R.id.text_check_by_mat_is_bom);
        visBom.setText(visBom.getText()+"Y");
        mAdapter = new CheckByMatAdapter(this,null);
        sheetListView.setAdapter(mAdapter);
    }
    private List<String> getData(){

        List<String> data = new ArrayList<String>();
        data.add("货位：A11");
        data.add("货位：A12");
        data.add("货位：B11");
        data.add("货位：B12");

        return data;
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
