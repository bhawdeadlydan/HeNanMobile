namespace java rfid.service

include "data.thrift"

service RFIDService {
list<data.ASN> getReceivingSheets();
list<data.Good> getGoodsListByCode(1:string Code,2:bool printable);//入库单号
bool printTag(1:string Code);
string getCodeByCNum(1:string CNum);
bool bindLocationAndGoods(1:i32 LocationID, 2: list<string> CNums);
list<data.POS> getApplySheets();
list<data.POS> getOutApplySheets();
data.POS getPOSInfoByApplyDocCode(1:string ApplyDocCode);
list<data.Good> getGoodsListByApplyDocCode(1:string ApplyDocCode);
bool confirmRetrieval(1:string ApplyDocCode, 2:map<string, i32> cartons);
bool confirmReceiving(1:string Code);//入库单号
data.Good getGoodByCNum(1:string CNum);
list<data.LocationInfo> getLocationListByItemErpCode(1:string ItemERPCode, 2:bool isBom);
list<data.Good> getGoodsByLocation(1:i32 Location);
string getApplyDocCodeByCNum(1:string CNum);
bool confirmInventory(1:list<data.check> checks);
bool confirmArrive(1:string charge, 2:string Time, 3:string Position,4: string Type, 5:string PosApplyDocCode, 6:double longitude, 7:double latitude);
bool stagingSiteCheckout(1:string applyPerson, 2:string constructPos, 3:string constructUnit, 4:string materialCode, 5:i32 num);
list<data.transportInfo> getTransportInfo();
list<data.stagingInfo> getStagingInfo(1:string constructUnit);
list<data.inStagingInfo> getInStagingInfo(1:string constructUnit);
bool addPic(1:string ApplyDocCode, 2:list<string> URL);
list<string> getPicsByApplyDocCode(1:string ApplyDocCode);
bool toPrint();
list<data.Data> callPrinter();
}