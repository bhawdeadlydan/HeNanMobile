/**
 * Autogenerated by Thrift Compiler (0.9.3)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
using System;
using System.Collections;
using System.Collections.Generic;
using System.Text;
using System.IO;
using Thrift;
using Thrift.Collections;
using System.Runtime.Serialization;
using Thrift.Protocol;
using Thrift.Transport;


#if !SILVERLIGHT
[Serializable]
#endif
public partial class check : TBase
{

  public string Location { get; set; }

  public string MaterialCode { get; set; }

  public int RealNum { get; set; }

  public string Time { get; set; }

  public check() {
  }

  public check(string Location, string MaterialCode, int RealNum, string Time) : this() {
    this.Location = Location;
    this.MaterialCode = MaterialCode;
    this.RealNum = RealNum;
    this.Time = Time;
  }

  public void Read (TProtocol iprot)
  {
    iprot.IncrementRecursionDepth();
    try
    {
      bool isset_Location = false;
      bool isset_MaterialCode = false;
      bool isset_RealNum = false;
      bool isset_Time = false;
      TField field;
      iprot.ReadStructBegin();
      while (true)
      {
        field = iprot.ReadFieldBegin();
        if (field.Type == TType.Stop) { 
          break;
        }
        switch (field.ID)
        {
          case 1:
            if (field.Type == TType.String) {
              Location = iprot.ReadString();
              isset_Location = true;
            } else { 
              TProtocolUtil.Skip(iprot, field.Type);
            }
            break;
          case 2:
            if (field.Type == TType.String) {
              MaterialCode = iprot.ReadString();
              isset_MaterialCode = true;
            } else { 
              TProtocolUtil.Skip(iprot, field.Type);
            }
            break;
          case 3:
            if (field.Type == TType.I32) {
              RealNum = iprot.ReadI32();
              isset_RealNum = true;
            } else { 
              TProtocolUtil.Skip(iprot, field.Type);
            }
            break;
          case 4:
            if (field.Type == TType.String) {
              Time = iprot.ReadString();
              isset_Time = true;
            } else { 
              TProtocolUtil.Skip(iprot, field.Type);
            }
            break;
          default: 
            TProtocolUtil.Skip(iprot, field.Type);
            break;
        }
        iprot.ReadFieldEnd();
      }
      iprot.ReadStructEnd();
      if (!isset_Location)
        throw new TProtocolException(TProtocolException.INVALID_DATA);
      if (!isset_MaterialCode)
        throw new TProtocolException(TProtocolException.INVALID_DATA);
      if (!isset_RealNum)
        throw new TProtocolException(TProtocolException.INVALID_DATA);
      if (!isset_Time)
        throw new TProtocolException(TProtocolException.INVALID_DATA);
    }
    finally
    {
      iprot.DecrementRecursionDepth();
    }
  }

  public void Write(TProtocol oprot) {
    oprot.IncrementRecursionDepth();
    try
    {
      TStruct struc = new TStruct("check");
      oprot.WriteStructBegin(struc);
      TField field = new TField();
      field.Name = "Location";
      field.Type = TType.String;
      field.ID = 1;
      oprot.WriteFieldBegin(field);
      oprot.WriteString(Location);
      oprot.WriteFieldEnd();
      field.Name = "MaterialCode";
      field.Type = TType.String;
      field.ID = 2;
      oprot.WriteFieldBegin(field);
      oprot.WriteString(MaterialCode);
      oprot.WriteFieldEnd();
      field.Name = "RealNum";
      field.Type = TType.I32;
      field.ID = 3;
      oprot.WriteFieldBegin(field);
      oprot.WriteI32(RealNum);
      oprot.WriteFieldEnd();
      field.Name = "Time";
      field.Type = TType.String;
      field.ID = 4;
      oprot.WriteFieldBegin(field);
      oprot.WriteString(Time);
      oprot.WriteFieldEnd();
      oprot.WriteFieldStop();
      oprot.WriteStructEnd();
    }
    finally
    {
      oprot.DecrementRecursionDepth();
    }
  }

  public override string ToString() {
    StringBuilder __sb = new StringBuilder("check(");
    __sb.Append(", Location: ");
    __sb.Append(Location);
    __sb.Append(", MaterialCode: ");
    __sb.Append(MaterialCode);
    __sb.Append(", RealNum: ");
    __sb.Append(RealNum);
    __sb.Append(", Time: ");
    __sb.Append(Time);
    __sb.Append(")");
    return __sb.ToString();
  }

}

