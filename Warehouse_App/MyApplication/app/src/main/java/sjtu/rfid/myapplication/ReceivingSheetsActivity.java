package sjtu.rfid.myapplication;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sjtu.rfid.view.ReceivingSheetsExpandableAdapter;

public class ReceivingSheetsActivity extends Activity {

    ExpandableListView sheetListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiving_sheets);

        iniListView();

    }

    public void iniListView() {

        sheetListView = (ExpandableListView) findViewById(R.id.list_receiving_sheets);


        List<String> list = new ArrayList<>();
        list.add("0000000001");
        list.add("0000000002");

        ReceivingSheetsExpandableAdapter tmpAdapter = new ReceivingSheetsExpandableAdapter(this,list);
        sheetListView.setAdapter(tmpAdapter);

    }
}
