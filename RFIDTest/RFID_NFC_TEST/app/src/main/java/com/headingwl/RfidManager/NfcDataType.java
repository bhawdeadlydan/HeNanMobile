package com.headingwl.RfidManager;


import android.support.annotation.Nullable;

/**
 * Created by L on 2015/12/14.
 */
public class NfcDataType {

	public NfcDataType() {
		NfcDataTypeBase nfcDataTypeBase = new NfcDataTypeBase();
	}

	public class NfcDataTypeBase {

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
			return timestamp;
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
		String TransPerson;
		String TransStation;
		String Location;
		String ConsPerson;
		long timestamp;
		double gpsN,gpsE;

		@Override
		public String toString() {
			return super.toString();
		}
	}
	public class UID extends NfcDataTypeBase {

		public UID(String uid) {
			this.uid = uid;
		}

		public  String toString() {
			return uid;
		}
	}

	public class ItemInf extends NfcDataTypeBase {
		public ItemInf(String ERPCode,String projectCode,int quantity, long timestamp) {
			this.ERPCode = ERPCode;
			this.projectCode = projectCode;
			this.quantity = quantity;
			this.timestamp = timestamp;
		}
		public String toString() {
			return ERPCode+ ',' + projectCode + "," + timestamp + ',' +quantity;
		}
	}

	public class REQInf extends NfcDataTypeBase {
		public REQInf(String nEPC,String nREQ,String nReqPerson,
					   String nWorkTeam,String nCheckPerson,long nTimestamp) {
			EPC = nEPC;
			REQ = nREQ;
			ReqPerson = nReqPerson;
			WorkTeam = nWorkTeam;
			CheckPerson = nCheckPerson;
			timestamp = nTimestamp;
		}
		public String toString() {
			return EPC+ ',' + REQ + ',' +ReqPerson +','+
			       WorkTeam + ',' + CheckPerson + ',' + timestamp;
		}
	}

	public class TransInf extends NfcDataTypeBase {
		public TransInf(String nTransPerson,String nTransStation,String nLocation,
		                   double nGpsN,double nGpsE,long nTimestamp) {
			TransPerson = nTransPerson;
			TransStation = nTransStation;
			Location = nLocation;
			gpsN = nGpsN;
			gpsE = nGpsE;
			timestamp = nTimestamp;
		}
		public String toString() {
			return TransPerson + ',' + TransStation + ',' + Location + ',' + timestamp;
		}
	}

	public class ConsInf extends NfcDataTypeBase {
		public ConsInf(String nConsPerson,String nLocation,String nWorkTeam,double nGpsN,double nGpsE,long nTimestamp) {
			ConsPerson = nConsPerson;
			Location =nLocation;
			WorkTeam = nWorkTeam;
			gpsN = nGpsN;
			gpsE = nGpsE;
			timestamp =nTimestamp;
		}
		public String toString() {
			return ConsPerson + ',' + Location + ',' + timestamp;
		}

	}
}
