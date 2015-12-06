package sjtu.rfid.rfidsys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import sjtu.rfid.tools.ReceivingSheetsExpandableAdapter;

public class ReceivingSheetsActivity extends AppCompatActivity {
    ExpandableListView sheetListView;
    List<String> list = new ArrayList<>();
    int mPreGroupPos = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiving_sheets);
        iniListView();

    }

    public void iniListView() {

        sheetListView = (ExpandableListView) findViewById(R.id.list_receiving_sheets);


        list.add("0000000001");
        list.add("0000000002");
        list.add("0000000003");

        ReceivingSheetsExpandableAdapter tmpAdapter = new ReceivingSheetsExpandableAdapter(this,list);
        sheetListView.setAdapter(tmpAdapter);


        sheetListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                
            }
        });



    }
}
