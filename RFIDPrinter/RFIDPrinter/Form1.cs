using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using RFIDStation;
using System.Globalization;
using Thrift.Protocol;
using System.Net;
using Thrift.Transport;
using Thrift.Server;
using System.Threading;

namespace RFIDPrinter
{
    public partial class Form1 : Form
    {
        public bool bOperatingSerial;
        private int serialDevice;                   //串口设备
        ushort[] addrArray = new ushort[2];
        Byte[] sendBuffer = new Byte[1024];
        Byte[] rcvBuffer = new Byte[1024];
        ISO14443A_UIDPARAM pUid = new ISO14443A_UIDPARAM();

        TServerTransport serverTransport;
        TServer server;
        Thread serverThread;

        public Form1()
        {

            InitializeComponent();
            serverThread = new Thread(OpenServer);
            serverThread.Start();
            openUSB(0x0505, 0x5050);
            config144443ADefault();
            
        }

        private void OpenServer()
        {
            RfidPrinterImpl handle = new RfidPrinterImpl(this);
            RfidPrinterService.Processor processor = new RfidPrinterService.Processor(handle);
            TProtocolFactory protocolFactory = new TJSONProtocol.Factory();
            serverTransport = new TServerSocket(7778);
            server = new TSimpleServer(processor, serverTransport);
            server.Serve();
        }



        private void button1_Click(object sender, EventArgs e)
        {


            //openUSB(0x0505, 0x5050);

            Byte mode = 0;

            
            pUid.uid = new ISO14443A_UID[hfReaderDll.HFREADER_ISO14443A_UID_MAX_NUM];




            mode = hfReaderDll.HFREADER_READ_UID_REPEAT;

            while (bOperatingSerial) ;

            bOperatingSerial = true;
            int rlt = hfReaderDll.iso14443AGetUID(serialDevice, addrArray[0], addrArray[1], mode, ref pUid, sendBuffer, rcvBuffer);
            bOperatingSerial = false;

            if (pUid.num > 0)
            {
                int i = 0, j = 0;
                String s;
                for (i = 0; i < pUid.num; i++)
                {
                    s = "";
                    s += pUid.uid[i].type.ToString("X").PadLeft(4, '0');
                    s += " ";
                    s += pUid.uid[i].len.ToString("X").PadLeft(2, '0');
                    s += " ";
                    for (j = 0; j < pUid.uid[i].len; j++)
                    {
                        s += pUid.uid[i].uid[j].ToString("X").PadLeft(2, '0');
                    }
                    Console.WriteLine(s);
                    lbUid.Text = "UID : \t" + s.Substring(8,8);
                }
            }
        }

        private void openUSB(ushort vid,ushort pid ){
           int handle = hfReaderDll.hfReaderOpenUsb(vid, pid);
            serialDevice = handle > 0 ? handle : 0;
        }

        private void closeUSB(ushort vid, ushort pid)
        {

            if(serialDevice > 0)
                hfReaderDll.hfReaderCloseUsb(serialDevice);
                
            serialDevice = 0;
        }

        public string getUid()
        {
            String s = "";

            Byte mode = 0;
            
            pUid.uid = new ISO14443A_UID[hfReaderDll.HFREADER_ISO14443A_UID_MAX_NUM];

            mode = hfReaderDll.HFREADER_READ_UID_REPEAT;

            while (bOperatingSerial) ;

            bOperatingSerial = true;
            int rlt = hfReaderDll.iso14443AGetUID(serialDevice, addrArray[0], addrArray[1], mode, ref pUid, sendBuffer, rcvBuffer);
            bOperatingSerial = false;

            if (pUid.num > 0)
            {
                int i = 0, j = 0;
                for (i = 0; i < pUid.num; i++)
                {
                    s = "";
                    s += pUid.uid[i].type.ToString("X").PadLeft(4, '0');
                    s += " ";
                    s += pUid.uid[i].len.ToString("X").PadLeft(2, '0');
                    s += " ";
                    for (j = 0; j < pUid.uid[i].len; j++)
                    {
                        s += pUid.uid[i].uid[j].ToString("X").PadLeft(2, '0');
                    }
                    //Console.WriteLine(s);
                    //lbUid.Text = "UID : \t" + s.Substring(8, 8);
                }
            }
            return s;
        }
   

        public int GetHexInput(String s, Byte[] buffer, int num)
        {
            int i = 0;
            if (s.Length != 2 * num)
            {
                MessageBox.Show("数据长度错误");
                return -1;
            }
            for (i = 0; i < s.Length; i++)
            {
                char c = s[i];
                if ((c < '0' || c > '9') && (c < 'a' || c > 'f') && (c < 'A' || c > 'F'))
                {
                    MessageBox.Show("请以16进制格式输入数据，例如：00 01 FF");
                    return -1;
                }
            }
            for (i = 0; i < num; i++)
            {
                buffer[i] = Convert.ToByte(s.Substring(i * 2, 2), 16);
            }

            return num;
        }

        private bool config144443ADefault()
        {
            if (serialDevice < 0)
                return false;
            HFREADER_CONFIG pReaderConfig = new HFREADER_CONFIG();
            pReaderConfig.afi = 0;
            pReaderConfig.afiCtrl = 0;
            pReaderConfig.baudrate = 7;
            pReaderConfig.beepStatus = 1;
            pReaderConfig.cmdMode = 1;
            pReaderConfig.readerAddr = 1;
            pReaderConfig.uidSendMode = 1;
            pReaderConfig.workMode = 18;
            pReaderConfig.result = new HFREADER_OPRESULT();
            pReaderConfig.result.errType = 0;
            pReaderConfig.result.flag = 0;
            pReaderConfig.result.srcAddr = 0;
            pReaderConfig.result.targetAddr = 0;
            pReaderConfig.result.t = 0;
            pReaderConfig.tagStatus = hfReaderDll.HFREADER_CFG_TAG_NOQUIET;

            int rlt = hfReaderDll.hfReaderSetConfig(serialDevice, addrArray[0], addrArray[1], ref pReaderConfig, sendBuffer, rcvBuffer);

            if (rlt > 0)
                Console.WriteLine("Config OK");

            return true;
        }

        private void button1_Click_1(object sender, EventArgs e)

        {
            config144443ADefault();
        }

        private void button2_Click(object sender, EventArgs e)
        {
            


        }

        private void read_Click(object sender, EventArgs e)
        {
            read1();
        }

        private void write_Click(object sender, EventArgs e)
        {
            write1("2510TP000009924_53009", "B1524011");
        }

        private void read1()
        {
            String code1 = HexStringToString(readone("01"),Encoding.UTF8);
            String code2 = HexStringToString(readone("02"), Encoding.UTF8);
            String projectCode = HexStringToString(readone("04"),Encoding.UTF8);
            String time = HexStringToString(readone("05"),Encoding.UTF8);
            code1 = code1.TrimEnd('\0');
            code2 = code2.TrimEnd('\0');
            projectCode = projectCode.TrimEnd('\0');
            time = time.TrimEnd('\0');
            //code1.Insert(code1.Length, code2);
            Console.WriteLine(code1+code2);
            Console.WriteLine(projectCode);
            Console.WriteLine(time);
        }

        private string readone(String addr)
        {
            if (serialDevice < 0)
            {
                MessageBox.Show("请先打开串口");
                return null;
            }
            ushort[] addrArray = new ushort[2];

            ISO14443A_BLOCKPARAM pBlock = new ISO14443A_BLOCKPARAM();

            pBlock.uid.uid = new Byte[hfReaderDll.HFREADER_ISO14443A_LEN_MAX_UID];
            pBlock.block = new Byte[hfReaderDll.HFREADER_ISO14443A_LEN_M1BLOCK * hfReaderDll.HFREADER_ISO14443A_M1BLOCKNUM_MAX];
            pBlock.key = new Byte[hfReaderDll.HFREADER_ISO14443A_LEN_M1_KEY];

            Byte[] sendBuffer = new Byte[1024];
            Byte[] rcvBuffer = new Byte[1024];

            Byte[] blockNum = new Byte[1];
            Byte[] blockAddr = new Byte[1];

            String startAddress = addr;

            if (GetHexInput(startAddress, blockAddr, 1) <= 0)
            {
                return null;
            }
            pBlock.addr = blockAddr[0];

            if (GetHexInput("01", blockNum, 1) <= 0)
            {
                return null;
            }
            pBlock.num = blockNum[0];
            pBlock.keyType = hfReaderDll.HFREADER_ISO14443A_KEY_A;
            GetHexInput("FFFFFFFFFFFF", pBlock.key, hfReaderDll.HFREADER_ISO14443A_LEN_M1_KEY);
            while (bOperatingSerial) ;
            bOperatingSerial = true;
            int rlt = hfReaderDll.iso14443AAuthReadM1Block(serialDevice, addrArray[0], addrArray[1], ref pBlock, sendBuffer, rcvBuffer);
            bOperatingSerial = false;
            if (rlt > 0)
            {
                if (pBlock.result.flag == 0)
                {
                    String s = "";
                    int j = 0;
                    int i = 0;
                    for (j = 0; j < pBlock.num; j++)
                    {
                        for (i = 0; i < hfReaderDll.HFREADER_ISO14443A_LEN_M1BLOCK; i++)
                        {
                            s += pBlock.block[j * hfReaderDll.HFREADER_ISO14443A_LEN_M1BLOCK + i].ToString("X").PadLeft(2, '0');
                        }
                        s += "\r\n";
                    }
                    return s;
                }

            }
            return null;
        }

        public void write1(String Code, String ProjectCode)
        {
            if (Code.Length > 16)
            {
                writeone(Code.Substring(0,16), "01");
                writeone(Code.Substring(16), "02");
            }
            else
            {
                writeone(Code, "01");
            }
            writeone(ProjectCode, "04");
            String date;
            DateTime dt = DateTime.Now;
            System.DateTime startTime = TimeZone.CurrentTimeZone.ToLocalTime(new System.DateTime(1970, 1, 1));
            int timestamp = (int)(dt - startTime).TotalSeconds;
            date = timestamp.ToString();

            writeone(date, "05");
        }
        private void writeone(String str, String addr)
        {
            if (serialDevice < 0)
            {
                MessageBox.Show("请先打开串口");
                return;
            }
            ushort[] addrArray = new ushort[2];

            ISO14443A_BLOCKPARAM pBlock = new ISO14443A_BLOCKPARAM();
            pBlock.uid.uid = new Byte[hfReaderDll.HFREADER_ISO14443A_LEN_MAX_UID];
            pBlock.block = new Byte[hfReaderDll.HFREADER_ISO14443A_LEN_M1BLOCK * hfReaderDll.HFREADER_ISO14443A_M1BLOCKNUM_MAX];
            pBlock.key = new Byte[hfReaderDll.HFREADER_ISO14443A_LEN_M1_KEY];
            Byte[] data = new Byte[hfReaderDll.HFREADER_ISO14443A_LEN_M1BLOCK];
            Byte[] sendBuffer = new Byte[1024];
            Byte[] rcvBuffer = new Byte[1024];

            Byte[] blockNum = new Byte[1];
            Byte[] blockAddr = new Byte[1];

            String startAddress = addr;

            //str = str.PadRight(16, '0');

            if (GetHexInput(startAddress, blockAddr, 1) <= 0)
            {
                return;
            }
            pBlock.addr = blockAddr[0];

            if (GetHexInput("01", blockNum, 1) <= 0)
            {
                return;
            }
            pBlock.num = blockNum[0];
            pBlock.keyType = hfReaderDll.HFREADER_ISO14443A_KEY_A;
            GetHexInput("FFFFFFFFFFFF", pBlock.key, hfReaderDll.HFREADER_ISO14443A_LEN_M1_KEY);

            string s = Str2Hex(str);
            s = s.PadRight(32, '0');

            
            GetHexInput(s, data, hfReaderDll.HFREADER_ISO14443A_LEN_M1BLOCK);
            for (int j = 0; j < hfReaderDll.HFREADER_ISO14443A_LEN_M1BLOCK; j++)
            {
                pBlock.block[0 * hfReaderDll.HFREADER_ISO14443A_LEN_M1BLOCK + j] = data[j];
            }
            //if (s.Length > 16)
            //{
            //    data = new Byte[hfReaderDll.HFREADER_ISO14443A_LEN_M1BLOCK];
            //    s = s.Substring(16);
            //    GetHexInput(s, data, hfReaderDll.HFREADER_ISO14443A_LEN_M1BLOCK);
            //    for (int j = 0; j < hfReaderDll.HFREADER_ISO14443A_LEN_M1BLOCK; j++)
            //    {
            //        pBlock.block[1 * hfReaderDll.HFREADER_ISO14443A_LEN_M1BLOCK + j] = data[j];
            //    }
            //}
            //data = new Byte[hfReaderDll.HFREADER_ISO14443A_LEN_M1BLOCK];
            //GetHexInput(ProjectCode, data, hfReaderDll.HFREADER_ISO14443A_LEN_M1BLOCK);
            //for (int j = 0; j < hfReaderDll.HFREADER_ISO14443A_LEN_M1BLOCK; j++)
            //{
            //    pBlock.block[4 * hfReaderDll.HFREADER_ISO14443A_LEN_M1BLOCK + j] = data[j];
            //}

            //data = new Byte[hfReaderDll.HFREADER_ISO14443A_LEN_M1BLOCK];

            //GetHexInput(date, data, hfReaderDll.HFREADER_ISO14443A_LEN_M1BLOCK);
            //for (int j = 0; j < hfReaderDll.HFREADER_ISO14443A_LEN_M1BLOCK; j++)
            //{
            //    pBlock.block[5 * hfReaderDll.HFREADER_ISO14443A_LEN_M1BLOCK + j] = data[j];
            //}

            while (bOperatingSerial) ;
            bOperatingSerial = true;
            int rlt = hfReaderDll.iso14443AAuthWriteM1Block(serialDevice, addrArray[0], addrArray[1], ref pBlock, sendBuffer, rcvBuffer);
            bOperatingSerial = false;

            Console.WriteLine("写成功！");
        }

        public string Str2Hex(string s)
        {
            string result = string.Empty;

            byte[] arrByte = System.Text.Encoding.GetEncoding("UTF-8").GetBytes(s);
            for (int i = 0; i < arrByte.Length; i++)
            {
                result += System.Convert.ToString(arrByte[i], 16);        //Convert.ToString(byte, 16)把byte转化成十六进制string 
            }

            return result;
        } 

        private string HexStringToString(string hs,Encoding encode)
        {


            byte[] vBytes = new byte[hs.Length / 2];
            for (int i = 0; i < hs.Length; i += 2)
                if (!byte.TryParse(hs.Substring(i, 2), NumberStyles.HexNumber, null, out vBytes[i / 2]))
                    vBytes[i / 2] = 0;
            return ASCIIEncoding.Default.GetString(vBytes);


            //byte[] b = new byte[hs.Length];
            // byte[] tmp = new byte[hs.Length/2];
            ////逐个字符变为16进制字节数据
            //for (int j = 0; j < hs.Length/2; j++)
            //{
            //    string s = hs.Substring(j*2,2);
            //    tmp[j] = (byte)Convert.ToB16(s, 16);

            //}
            ////按照指定编码将字节数组变为字符串
            //return encode.GetString(b);
        }

        private void button2_Click_1(object sender, EventArgs e)
        {
            Data d1 = new Data();
            d1.ProjectCode = "B1524011";
            d1.MatCode = "2510TP000009924_66666";
            RfidPrinterImpl insance = new RfidPrinterImpl(this);
            insance.printData(d1,2);
        }
    }
}