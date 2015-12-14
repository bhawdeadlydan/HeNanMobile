package sjtu.rfid.rfidsys;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import rfid.service.ASN;
import rfid.service.Good;
import sjtu.rfid.thread.PrintThread;
import sjtu.rfid.thread.ReceivingScanBoxScanTagThread;
import sjtu.rfid.thread.ReceivingScanBoxThread;
import sjtu.rfid.thread.ReceivingSubmitThread;
import sjtu.rfid.tools.ReceivingSheetsScanBoxExpandableAdapter;
import sjtu.rfid.tools.TitleBar;

public class ReceivingScanBoxActivity extends Activity {

    private TextView vReceSheetCode;
    private String sheetCode="";
    private ExpandableListView sheetListView;
    private ReceivingSheetsScanBoxExpandableAdapter tmpAdapter;
    private Map<String, Map<String, String>> mReceivingBoxesDetails;
    private List<Map<String,String>> mReceivingBoxes;

    //记录每个ERP编码下，已经扫到的箱号，避免重复扫描造成错误计数
    private Map<String, Set<String>> mReceivingBoxesItemsList;

    private TitleBar mTitleBar;


    private ReceivingScanBoxThread receivingScanBoxThread;
    private ReceivingSubmitThread receivingSubmitThread;
    private PrintThread printThread;
    private ReceivingScanBoxScanTagThread receivingScanBoxScanTagThread;
    boolean isReading = false;

    private List<Good> goodList;
    private boolean receivingResult;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==0||msg.obj==null)
                Toast.makeText(getApplicationContext(), "获取信息失败", Toast.LENGTH_SHORT).show();
            goodList=(List<Good>)msg.obj;
            iniListView(goodList);
        }
    };
    private Handler handlerReceiving=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==0||msg.obj==null)
                Toast.makeText(getApplicationContext(), "获取信息失败", Toast.LENGTH_SHORT).show();
            receivingResult=(boolean)msg.obj;
            if(receivingResult) {
                Toast.makeText(getApplicationContext(), "提交成功", Toast.LENGTH_SHORT).show();
                finish();
            }
            else
                Toast.makeText(getApplicationContext(), "提交失败", Toast.LENGTH_SHORT).show();
        }
    };

    private Handler handlerPrint=new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==0)
                Toast.makeText(getApplicationContext(), "打印失败", Toast.LENGTH_SHORT).show();
            else if(msg.what==1)
                Toast.makeText(getApplicationContext(),"打印成功，请前往获取标签！", Toast.LENGTH_SHORT).show();
            ((Button)findViewById(R.id.btn_receiving_scan_box_print)).setEnabled(false);
        }
    };

    private Handler handlerScanTag=new Handler() {
        @Override
        public void handleMessage(Message msg){
            if(msg.what == 1){
                tmpAdapter.notifyDataSetChanged();
            }
        }

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiving_scan_box);

        vReceSheetCode=(TextView)findViewById(R.id.text_receiving_scan_box_order_number);
        Bundle bundle = new Bundle();
        bundle = this.getIntent().getExtras();
        sheetCode=bundle.getString("receiving_sheet_code");
        vReceSheetCode.setText(sheetCode);
        iniActivity();
        iniEvent();

        receivingScanBoxThread=new ReceivingScanBoxThread(handler,sheetCode);
        receivingScanBoxThread.start();
    }

    public void iniActivity(){
        mTitleBar = new TitleBar(this,"收货贴标");
    }

    public void iniListView(List<Good> goodList) {
        mReceivingBoxesDetails=new HashMap<>();
        mReceivingBoxes = new ArrayList<>();
        mReceivingBoxesItemsList = new HashMap<>();
        sheetListView = (ExpandableListView) findViewById(R.id.list_receiving_scan_box_sheets);
        for(Good good:goodList){
            Map<String,String> map=new HashMap<>();
            map.put("matCode",good.getCode());
            map.put("matName", good.getDetail());
            map.put("boxCount",String.valueOf(good.getNum()));
            mReceivingBoxes.add(map);

            Map<String, String> detailMap = new HashMap<>();
            String cartonList="\n";
            detailMap.put("isBom", good.isIs_Bom() ? "Y" : "N");
            for(String cartonNum:good.getCartonNums()){
                cartonList+=cartonNum+"\n";
            }
            detailMap.put("cartonList",cartonList);
            mReceivingBoxesDetails.put(good.getCode(), detailMap);

            Set<String> boxSet = new HashSet<>();
            mReceivingBoxesItemsList.put(good.getCode(),boxSet);

        }

        tmpAdapter = new ReceivingSheetsScanBoxExpandableAdapter(this, mReceivingBoxesDetails, mReceivingBoxes,mReceivingBoxesItemsList);
        sheetListView.setAdapter(tmpAdapter);

        sheetListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                for (int i = 0; i < tmpAdapter.getGroupCount(); i++) {
                    if (groupPosition != i) {
                        sheetListView.collapseGroup(i);
                    }
                }
            }
        });

    }

    public void iniEvent(){
        final Button btnScan=(Button)findViewById(R.id.btn_receiving_scan_box_scan);
        Button btnCommit=(Button)findViewById(R.id.btn_receiving_scan_box_commit);
        Button btnPrint=(Button)findViewById(R.id.btn_receiving_scan_box_print);

        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //读货物标签线程
                if( isReading ) {
                    isReading = false;
                    btnScan.setText("扫描标签");
                    receivingScanBoxScanTagThread.setIsReading(false);

                } else if( !isReading ){
                    isReading = true;
                    btnScan.setText("停止扫描");
                    receivingScanBoxScanTagThread = new ReceivingScanBoxScanTagThread(mReceivingBoxesItemsList,true,handlerScanTag);
                    receivingScanBoxScanTagThread.start();
                }

            }
        });
        btnCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                receivingSubmitThread=new ReceivingSubmitThread(sheetCode,handlerReceiving);
                receivingSubmitThread.start();

            }
        });

        btnPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                printThread = new PrintThread(sheetCode,handlerPrint);
                printThread.start();

            }
        });



    }
}
