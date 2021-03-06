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
public partial class transportInfo : TBase
{

  public string Time { get; set; }

  public string Position { get; set; }

  public string Person { get; set; }

  public string ConstructUnit { get; set; }

  public int Type { get; set; }

  public string ApplyDocCode { get; set; }

  public transportInfo() {
  }

  public transportInfo(string Time, string Position, string Person, string ConstructUnit, int Type, string ApplyDocCode) : this() {
    this.Time = Time;
    this.Position = Position;
    this.Person = Person;
    this.ConstructUnit = ConstructUnit;
    this.Type = Type;
    this.ApplyDocCode = ApplyDocCode;
  }

  public void Read (TProtocol iprot)
  {
    iprot.IncrementRecursionDepth();
    try
    {
      bool isset_Time = false;
      bool isset_Position = false;
      bool isset_Person = false;
      bool isset_ConstructUnit = false;
      bool isset_Type = false;
      bool isset_ApplyDocCode = false;
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
              Time = iprot.ReadString();
              isset_Time = true;
            } else { 
              TProtocolUtil.Skip(iprot, field.Type);
            }
            break;
          case 2:
            if (field.Type == TType.String) {
              Position = iprot.ReadString();
              isset_Position = true;
            } else { 
              TProtocolUtil.Skip(iprot, field.Type);
            }
            break;
          case 3:
            if (field.Type == TType.String) {
              Person = iprot.ReadString();
              isset_Person = true;
            } else { 
              TProtocolUtil.Skip(iprot, field.Type);
            }
            break;
          case 4:
            if (field.Type == TType.String) {
              ConstructUnit = iprot.ReadString();
              isset_ConstructUnit = true;
            } else { 
              TProtocolUtil.Skip(iprot, field.Type);
            }
            break;
          case 5:
            if (field.Type == TType.I32) {
              Type = iprot.ReadI32();
              isset_Type = true;
            } else { 
              TProtocolUtil.Skip(iprot, field.Type);
            }
            break;
          case 6:
            if (field.Type == TType.String) {
              ApplyDocCode = iprot.ReadString();
              isset_ApplyDocCode = true;
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
      if (!isset_Time)
        throw new TProtocolException(TProtocolException.INVALID_DATA);
      if (!isset_Position)
        throw new TProtocolException(TProtocolException.INVALID_DATA);
      if (!isset_Person)
        throw new TProtocolException(TProtocolException.INVALID_DATA);
      if (!isset_ConstructUnit)
        throw new TProtocolException(TProtocolException.INVALID_DATA);
      if (!isset_Type)
        throw new TProtocolException(TProtocolException.INVALID_DATA);
      if (!isset_ApplyDocCode)
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
      TStruct struc = new TStruct("transportInfo");
      oprot.WriteStructBegin(struc);
      TField field = new TField();
      field.Name = "Time";
      field.Type = TType.String;
      field.ID = 1;
      oprot.WriteFieldBegin(field);
      oprot.WriteString(Time);
      oprot.WriteFieldEnd();
      field.Name = "Position";
      field.Type = TType.String;
      field.ID = 2;
      oprot.WriteFieldBegin(field);
      oprot.WriteString(Position);
      oprot.WriteFieldEnd();
      field.Name = "Person";
      field.Type = TType.String;
      field.ID = 3;
      oprot.WriteFieldBegin(field);
      oprot.WriteString(Person);
      oprot.WriteFieldEnd();
      field.Name = "ConstructUnit";
      field.Type = TType.String;
      field.ID = 4;
      oprot.WriteFieldBegin(field);
      oprot.WriteString(ConstructUnit);
      oprot.WriteFieldEnd();
      field.Name = "Type";
      field.Type = TType.I32;
      field.ID = 5;
      oprot.WriteFieldBegin(field);
      oprot.WriteI32(Type);
      oprot.WriteFieldEnd();
      field.Name = "ApplyDocCode";
      field.Type = TType.String;
      field.ID = 6;
      oprot.WriteFieldBegin(field);
      oprot.WriteString(ApplyDocCode);
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
    StringBuilder __sb = new StringBuilder("transportInfo(");
    __sb.Append(", Time: ");
    __sb.Append(Time);
    __sb.Append(", Position: ");
    __sb.Append(Position);
    __sb.Append(", Person: ");
    __sb.Append(Person);
    __sb.Append(", ConstructUnit: ");
    __sb.Append(ConstructUnit);
    __sb.Append(", Type: ");
    __sb.Append(Type);
    __sb.Append(", ApplyDocCode: ");
    __sb.Append(ApplyDocCode);
    __sb.Append(")");
    return __sb.ToString();
  }

}

