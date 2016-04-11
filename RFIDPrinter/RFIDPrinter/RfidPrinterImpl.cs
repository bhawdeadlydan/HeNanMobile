using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace RFIDPrinter
{
    class RfidPrinterImpl : RfidPrinterService.Iface
    {
        public static String DATAPATH = "d://data";
        public static String DETECTPATH = "d://rfid_print";
        Form1 form;
        private string projectCode;
        private String code;
        private String vendorName;

        public RfidPrinterImpl(Form1 form)
        {
            this.form = form;
            projectCode = "";
            code = "";
            vendorName = "";
        }

        public bool callPrinter(Data data)
        {
            projectCode = data.ProjectCode;
            code = data.Code;
            vendorName = data.VendorName;
            for(int i = 0; i < data.BatchGoods.Count; i++)
            {
                for(int j = 0; j < data.BatchGoods.ElementAt(i).Count; j++)
                {
                    printTag(data.BatchGoods.ElementAt(i).ElementAt(j));
                    bool rst = printData(data.BatchGoods.ElementAt(i).ElementAt(j).ItemCode);
                    if(!rst)
                    {
                        form.textBox1.Text += "写高频标签失败，是否重试，请点击左侧按钮。\n";
                        //wait until user choose the option
                        while (form.option == 0) ;
                        if(form.option == 1)//retry
                        {
                            j--;
                            form.textBox1.Text += "准备重试..\n";
                        }
                        else if(form.option == -1)//terminate
                        {
                            form.textBox1.Text += "准备退出\n";
                            return false;
                        }
                    }
                }
            }
            return true;
        }

        public bool printData(String matCode)
        {

            String text = "当前需写入标签内信息及标签数量：\r\n" + "物料编码：" + matCode + "\r\n项目编码："
                + projectCode + "\n";
            Console.WriteLine(text);
            form.textBox1.Text += text;
            for(int i = 0; i < 3; i++)
                form.write1(matCode, projectCode);
            string[] res = form.read1();
            if (res[0] == matCode && res[1] == projectCode)
                return true;
            return false;

            //form.infoTextBox.Text = text;
            //form.Update();
            //int size = 0;
            //string preUid = "0";
            //string uid = "";
            //while (size != count) {
            //    while( uid == "" || uid == preUid ) {
            //            uid = form.getUid();
            //    }
            //    if (uid != preUid)
            //    {
            //        size++;
            //        String show = text + "当前已写入标签：" + uid+"当前数量："+size;
            //        Console.WriteLine(show);
            //        //form.write1(data.MatCode, data.ProjectCode);
            //        //form.infoTextBox.Text = show;
            //        //form.Update();
            //        preUid = uid;
            //        uid = "";

            //    }
            //}
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
        public void printTag(Item good)
        {
            //        for(int i = 0; i < 1; i++){
                //if (i == 1)
                //    break;
            printOne(projectCode, code, good.ItemName, good.ItemCode, good.ItemNum, good.ItemUnit, good.EPC, vendorName);
            trigger(good.EPC);
            Thread.Sleep(3000);
        }
        public void printOne(String ProjectCode, String Code, String ItemName, String ItemCode, String ItemNum, String ItemUnit, String EPC,
                             String VendorName)
        {
            string path = Path.Combine(DATAPATH, "epc.txt");
            string content = ProjectCode + "," + Code + "," + ItemName + "," + ItemCode + "," + ItemNum + ","
                    + ItemUnit + "," + EPC + "," + VendorName;
            File.WriteAllText(path, content);
        }

        public void trigger(String EPC)
        {
            string path = Path.Combine(DETECTPATH, EPC + ".txt");
            string content = EPC;
            File.WriteAllText(path, content);
        }

    }
}
