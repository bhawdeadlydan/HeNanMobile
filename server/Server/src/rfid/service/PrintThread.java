package rfid.service;

import client.Data;
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
        for(Iterator it = batchGoods.iterator();it.hasNext();){
            ArrayList<String[]> goods = (ArrayList<String[]>)it.next();
            printTag(goods);
            Data data = new Data();
            data.setProjectCode(projectCode);
            data.setMatCode(code);
            if(client == null || !client.printData(data, goods.size()))
                break;
        }
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
        for(Iterator it = goods.iterator(); it.hasNext();){
            String [] good = (String[]) it.next();
            printOne(projectCode, code, good[0], good[1], good[2], good[3],good[4],vendorName);
            trigger(good[4]);
        }
    }
    public void printOne(String ProjectCode, String Code, String ItemName, String ItemCode, String ItemNum, String ItemUnit, String EPC,
                         String VendorName) {
        try {
            File file1 = new File(Config.DATAPATH,"epc.txt");
            BufferedWriter fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file1,false),"UTF-8"));
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
