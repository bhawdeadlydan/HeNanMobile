namespace java rfid.service

struct ASN{
1:required string Code;
2:required string Project_Code;
3:required string Order_Date;
4:required string Vendor_Name;
5:required string Apply_Person;
6:required string Releated_Bill1;
}

struct Good{
1:required string Code;
2:optional i32 Num = 0;
3:required bool Is_Bom;
4:required string Detail;
5:optional string Unit = "N";
6:optional i32 Expected_Quantity = 0;
}

struct POS{
1:required string Apply_Doc_Code;
2:required string Apply_Person;
3:required string Apply_Unit;
4:required string Project_Code;
5:required string Apply_Date;
6:required string Receiver;
}

