package nfc;


import java.sql.Date;
import java.sql.Time;

/**
 * Created by L on 2015/12/14.
 */
public class NfcDataType {

    public NfcDataType(){
        NfcDataTypeBase nfcDataTypeBase = new NfcDataTypeBase();

    }

    public class NfcDataTypeBase{

        public int getQuantity() {
            return quantity;
        }

        public String getUid() {
            return uid;
        }

        public String getERPCode() {
            return ERPCode;
        }

        public String getProjectCode() {
            return projectCode;
        }

        public String getEPC() {
            return EPC;
        }

        public String getREQ() {
            return REQ;
        }

        public String getReqPerson() {
            return ReqPerson;
        }

        public String getWorkTeam() {
            return WorkTeam;
        }

        public String getCheckPerson() {
            return CheckPerson;
        }

        public String getTransPerson() {
            return TransPerson;
        }

        public String getTransStation() {
            return TransStation;
        }

        public String getLocation() {
            return Location;
        }

        public String getConsPerson() {
            return ConsPerson;
        }

        public long getTimestamp() {
            return Timestamp;
        }

        public double getGpsN() {
            return gpsN;
        }

        public double getGpsE() {
            return gpsE;
        }

        int quantity;
        String uid;
        String ERPCode;
        String projectCode;
        String EPC;
        String REQ;
        String ReqPerson;
        String WorkTeam;
        String CheckPerson;
        public String TransPerson;
        String TransStation;
        String Location;
        String ConsPerson;
        long Timestamp;
        double gpsN,gpsE;

        @Override
        public String toString() {
            return super.toString();
        }
    }

    public class UID extends NfcDataTypeBase {
        //public String uid;

        public UID(String uid)
        {
            this.uid = uid;
        }

        public  String toString()
        {
            return uid;
        }
    }

    public class ItemInf extends NfcDataTypeBase{

        /*String ERPCode;
        String projectCode;
        int quantity;*/

        public ItemInf(String ERPCode,String projectCode,int quantit){
            this.ERPCode = ERPCode;
            this.projectCode = projectCode;
            this.quantity = quantit;
        }

        public String toString()
        {
            return ERPCode+ ',' + projectCode + ',' +quantity;
        }
    }

    public class REQInf extends NfcDataTypeBase{
/*
        String EPC;
        String REQ;
        String ReqPerson;
        String WorkTeam;
        String CheckPerson;
        long Timestamp;
*/

        public REQInf(String nEPC,String nREQ, String nReqPerson,
                            String nWorkTeam,String nCheckPerson,long nTimestamp){
            EPC = nEPC;
            REQ = nREQ;
            ReqPerson = nReqPerson;
            WorkTeam = nWorkTeam;
            CheckPerson = nCheckPerson;
            Timestamp = nTimestamp;


        }

        public String toString()
        {
            long timed = System.currentTimeMillis();
            return EPC+ ',' + REQ + ',' +ReqPerson +','+
                        WorkTeam + ',' + CheckPerson + ',' + String.valueOf(Timestamp);
        }
    }

    public class TransInf extends NfcDataTypeBase{

        /*
        String TransPerson;
        String TransStation;
        String Location;
        double gpsN,gpsE;
        long TimeStamp;*/

        public TransInf(String nTransPerson, String nTransStation, String nLocation,
                        double nGpsN, double nGpsE, long nTimestamp){
            TransPerson = nTransPerson;
            TransStation = nTransStation;
            Location = nLocation;
            gpsN = nGpsN;
            gpsE = nGpsE;
            Timestamp = nTimestamp;
        }

        public String toString()
        {
            return TransPerson + ',' + TransStation + ',' + Location + ',' + String.valueOf(Timestamp);
        }
    }

    public class ConsInf extends NfcDataTypeBase{
        /*
        String ConsPerson;
        String Location;
        double gpsN,gpsE;
        long Timestamp;
        */

        public ConsInf(String nConsPerson, String nLocation, double nGpsN, double nGpsE, long nTimestamp){
            ConsPerson = nConsPerson;
            Location =nLocation;
            gpsN = nGpsN;
            gpsE = nGpsE;
            Timestamp =nTimestamp;
        }

        public String toString(){
            return ConsPerson + ',' + Location + ',' + String.valueOf(Timestamp);
        }

    }
}
