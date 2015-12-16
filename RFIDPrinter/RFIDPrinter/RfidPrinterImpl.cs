using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace RFIDPrinter
{
    class RfidPrinterImpl : RfidPrinterService.Iface
    {
        Form1 form;
        

        public RfidPrinterImpl(Form1 form)
        {
            this.form = form;
        }


        public bool printData(Data data, int count)
        {

            String text = "当前需写入标签内信息及标签数量：\r\n" + "物料编码：" + data.MatCode + "\r\n项目编码："
                + data.ProjectCode + "\r\n标签数量：" + count + "\r\n";
            //form.infoTextBox.Text = text;
            //form.Update();
            int size = 0;
            string preUid = "0";
            string uid = "";
            while (size != count) {
                while( uid == "" || uid == preUid ) {
                        uid = form.getUid();
                }
                if (uid != preUid)
                {
                    size++;
                    String show = text + "当前已写入标签：" + uid+"当前数量："+size;
                    Console.WriteLine(show);
                    //form.write1(data.MatCode, data.ProjectCode);
                    //form.infoTextBox.Text = show;
                    //form.Update();
                    preUid = uid;
                    uid = "";
                    
                }
            }
            return true;
        }
    }
}
