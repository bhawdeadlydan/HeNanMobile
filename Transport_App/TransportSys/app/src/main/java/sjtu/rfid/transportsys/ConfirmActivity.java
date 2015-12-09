package sjtu.rfid.transportsys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import tools.ConfirmExpandableListAdapter;

public class ConfirmActivity extends AppCompatActivity {

    ExpandableListView mListView;
    ConfirmExpandableListAdapter mAdapter;


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        //iniListView();
        iniActivity();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void iniActivity()
    {
        TextView tmpTitle = (TextView) findViewById(R.id.text_title_title);
        Intent intent = getIntent();
        tmpTitle.setText("申领物料");
    }

    public void iniListView() {

        mAdapter = new ConfirmExpandableListAdapter(this,null,null);
        mListView.setAdapter(mAdapter);

    }
}
