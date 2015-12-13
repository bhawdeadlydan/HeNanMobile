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
public partial class Data : TBase
{
  private string _matCode;
  private string _projectCode;

  public string MatCode
  {
    get
    {
      return _matCode;
    }
    set
    {
      __isset.matCode = true;
      this._matCode = value;
    }
  }

  public string ProjectCode
  {
    get
    {
      return _projectCode;
    }
    set
    {
      __isset.projectCode = true;
      this._projectCode = value;
    }
  }


  public Isset __isset;
  #if !SILVERLIGHT
  [Serializable]
  #endif
  public struct Isset {
    public bool matCode;
    public bool projectCode;
  }

  public Data() {
  }

  public void Read (TProtocol iprot)
  {
    iprot.IncrementRecursionDepth();
    try
    {
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
              MatCode = iprot.ReadString();
            } else { 
              TProtocolUtil.Skip(iprot, field.Type);
            }
            break;
          case 2:
            if (field.Type == TType.String) {
              ProjectCode = iprot.ReadString();
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
      TStruct struc = new TStruct("Data");
      oprot.WriteStructBegin(struc);
      TField field = new TField();
      if (MatCode != null && __isset.matCode) {
        field.Name = "matCode";
        field.Type = TType.String;
        field.ID = 1;
        oprot.WriteFieldBegin(field);
        oprot.WriteString(MatCode);
        oprot.WriteFieldEnd();
      }
      if (ProjectCode != null && __isset.projectCode) {
        field.Name = "projectCode";
        field.Type = TType.String;
        field.ID = 2;
        oprot.WriteFieldBegin(field);
        oprot.WriteString(ProjectCode);
        oprot.WriteFieldEnd();
      }
      oprot.WriteFieldStop();
      oprot.WriteStructEnd();
    }
    finally
    {
      oprot.DecrementRecursionDepth();
    }
  }

  public override string ToString() {
    StringBuilder __sb = new StringBuilder("Data(");
    bool __first = true;
    if (MatCode != null && __isset.matCode) {
      if(!__first) { __sb.Append(", "); }
      __first = false;
      __sb.Append("MatCode: ");
      __sb.Append(MatCode);
    }
    if (ProjectCode != null && __isset.projectCode) {
      if(!__first) { __sb.Append(", "); }
      __first = false;
      __sb.Append("ProjectCode: ");
      __sb.Append(ProjectCode);
    }
    __sb.Append(")");
    return __sb.ToString();
  }

}
