struct Item {
	1:string itemName;
	2:string itemCode;
	3:string itemNum;
	4:string itemUnit;
	5:string EPC;
}

struct Data {
	1:string projectCode;
	2:string code;
	3:string vendorName;
	4:list<list<Item>> batchGoods;
}

service RfidPrinterService {
	bool callPrinter(1:Data data);
}