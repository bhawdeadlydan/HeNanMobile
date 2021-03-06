package rfid.service;

import client.Data;
import client.Item;
import client.PrintClient;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by richard on 2015/12/13.
 */
public class PrintThread implements Runnable{

    private PrintClient client;
    private String projectCode;
    private String code;
    private String vendorName;
    private ArrayList<ArrayList<String[]>> batchGoods;

    public PrintThread(String ProjectCode, String Code, String VendorName, ArrayList<ArrayList<String[]>> BatchGoods){
        client = new PrintClient();
        this.projectCode = ProjectCode;
        this.code = Code;
        this.vendorName = VendorName;
        this.batchGoods = BatchGoods;
    }
    @Override
    public void run() {
        System.out.println("print thread starting..");
        Data data = new Data();
        data.setProjectCode(projectCode);
        data.setCode(code);
        data.setVendorName(vendorName);
        List<List<Item>> batGoods = new ArrayList<>();
        for(Iterator<ArrayList<String[]>> it = batchGoods.iterator(); it.hasNext();) {
            ArrayList<String[]> l = it.next();
            List<Item> items = new ArrayList<>();
            for(int i = 0; i < l.size(); i++) {
                Item item = new Item();
                item.setItemName(l.get(i)[0]);
                item.setItemCode(l.get(i)[1]);
                item.setItemNum(l.get(i)[2]);
                item.setItemUnit(l.get(i)[3]);
                item.setEPC(l.get(i)[4]);
                items.add(item);
            }
            batGoods.add(items);
        }
        data.setBatchGoods(batGoods);
        client.callPrinter(data);

//        for(int i = 0; i < batchGoods.size(); i++){
//            printTag(batchGoods.get(i));
//            Data data = new Data();
//            data.setProjectCode(projectCode);
//            data.setMatCode(batchGoods.get(i).get(0)[1]);
//            System.out.println("print sequence " + i + " start");
////             if(client == null || !client.printData(data, 2)) {
//            if(client == null || !client.printData(data, batchGoods.get(i).size())) {
//                System.out.println("print sequence " + i + " over unexpectedly");
//                break;
//            }
//            System.out.println("print sequence " + i + " over");
//        }
        System.out.println("print thread exiting..");
        client.close();
    }
    /*
    *项目编号
    *入库单号
    *物料名称
    *物料编码
    *数量
    *单位
    *epc，箱号
    *VENDOR_NAME厂商名称
    * */
    public void printTag(List<String[]> goods){
//        for(int i = 0; i < 1; i++){
        for(int i = 0; i < goods.size(); i++){
            if(i == 1)
                break;
            String [] good = goods.get(i);
            printOne(projectCode, code, good[0], good[1], good[2], good[3],good[4],vendorName);
            trigger(good[4]);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void printOne(String ProjectCode, String Code, String ItemName, String ItemCode, String ItemNum, String ItemUnit, String EPC,
                         String VendorName) {
        try {
            File file1 = new File(Config.DATAPATH,"epc.txt");
            BufferedWriter fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file1,false),"GB2312"));
            fw.write(ProjectCode + "," + Code + "," + ItemName + "," + ItemCode + ","+ ItemNum + ","
                    + ItemUnit + "," + EPC + "," + VendorName);
            fw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void trigger(String EPC){
        try {
            File file1 = new File(Config.DETECTPATH,EPC + ".txt");
            BufferedWriter fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file1,false),"UTF-8"));
            fw.write(EPC);
            fw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
