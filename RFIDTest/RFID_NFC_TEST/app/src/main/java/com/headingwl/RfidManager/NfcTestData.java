package com.headingwl.RfidManager;

import com.headingwl.RfidManager.NfcDataType.*;

/**
 * Created by L on 2015/12/14.
 * add some test data on 2015/12/17
 */
public class NfcTestData {
	NfcDataType nfcDataType;

	public REQInf reqInf ;
	public ItemInf itemInf;
	public TransInf transInf;
	public ConsInf consInf;
	public UID uid;

	public NfcTestData() {
		nfcDataType = new NfcDataType();
		reqInf = nfcDataType.new REQInf("2015121600000152","2524-REQ-2015120000127","申请人A","施工队B","复核人C",Long.decode("1450081000"));
		itemInf = nfcDataType.new ItemInf("10041806","B1524015",3,Long.decode("1450080000"));
		transInf = nfcDataType.new TransInf("运输人D","广州和新","河南省平顶山市神马大道柏杨路",33.7720510000,113.1989350000,Long.decode("1450082000"));
		consInf = nfcDataType.new ConsInf("工作人员E","河南省平顶山市神马大道施工","广州和新",33.7720510000,113.1989350000,Long.decode("1450083000"));
		uid = nfcDataType.new UID("E1E2E3E4");
	}
}
