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
7:optional list<string> CartonNums;
8:optional string InCode = "";
9:optional string ProjectCode = "";
}

struct POS{
1:required string Apply_Doc_Code;
2:required string Apply_Person;
3:required string Apply_Unit;
4:required string Project_Code;
5:required string Apply_Date;
6:required string Receiver;
}

struct LocationInfo {
1:required i32 ID;
2:required i32 Num;
3:required string Area;
4:required i32 Location;
5:optional list<string> CartonNums;
}

struct check {
1:required string Location;
2:required string MaterialCode;
3:required i32 RealNum;
4:required string Time;
}

struct transportInfo {
1:required string Time;
2:required string Position;
3:required string Person;
4:required string ConstructUnit;
}

struct stagingInfo {
1:required string ApplyPerson;
2:required string ConstructPos;
3:required string ConstructUnit;
4:required string Time;
5:required string MaterialCode;
6:required i32 Num;
}