using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

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
            int size = 0;
            string preUid = "0";
            string uid = "";
            while (size != count) {
                while( uid == "" || uid == preUid ) {
                        uid = form.getUid();
                }
                if (uid != preUid)
                {
                    form.write1(data.MatCode, data.ProjectCode);
                    preUid = uid;
                    uid = "";
                    size++;
                }
            }
            return true;
        }
    }
}
