struct Data {
	1:string matCode;
	2:string projectCode;
}

service RfidPrinterService {
	bool printData(1:Data data,2:i32 count);
}