/**
 * Autogenerated by Thrift Compiler (0.9.3)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package rfid.service;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import javax.annotation.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked"})
@Generated(value = "Autogenerated by Thrift Compiler (0.9.3)", date = "2015-12-20")
public class stagingInfo implements org.apache.thrift.TBase<stagingInfo, stagingInfo._Fields>, java.io.Serializable, Cloneable, Comparable<stagingInfo> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("stagingInfo");

  private static final org.apache.thrift.protocol.TField APPLY_PERSON_FIELD_DESC = new org.apache.thrift.protocol.TField("ApplyPerson", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField CONSTRUCT_POS_FIELD_DESC = new org.apache.thrift.protocol.TField("ConstructPos", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField CONSTRUCT_UNIT_FIELD_DESC = new org.apache.thrift.protocol.TField("ConstructUnit", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField TIME_FIELD_DESC = new org.apache.thrift.protocol.TField("Time", org.apache.thrift.protocol.TType.STRING, (short)4);
  private static final org.apache.thrift.protocol.TField MATERIAL_CODE_FIELD_DESC = new org.apache.thrift.protocol.TField("MaterialCode", org.apache.thrift.protocol.TType.STRING, (short)5);
  private static final org.apache.thrift.protocol.TField NUM_FIELD_DESC = new org.apache.thrift.protocol.TField("Num", org.apache.thrift.protocol.TType.I32, (short)6);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new stagingInfoStandardSchemeFactory());
    schemes.put(TupleScheme.class, new stagingInfoTupleSchemeFactory());
  }

  public String ApplyPerson; // required
  public String ConstructPos; // required
  public String ConstructUnit; // required
  public String Time; // required
  public String MaterialCode; // required
  public int Num; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    APPLY_PERSON((short)1, "ApplyPerson"),
    CONSTRUCT_POS((short)2, "ConstructPos"),
    CONSTRUCT_UNIT((short)3, "ConstructUnit"),
    TIME((short)4, "Time"),
    MATERIAL_CODE((short)5, "MaterialCode"),
    NUM((short)6, "Num");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // APPLY_PERSON
          return APPLY_PERSON;
        case 2: // CONSTRUCT_POS
          return CONSTRUCT_POS;
        case 3: // CONSTRUCT_UNIT
          return CONSTRUCT_UNIT;
        case 4: // TIME
          return TIME;
        case 5: // MATERIAL_CODE
          return MATERIAL_CODE;
        case 6: // NUM
          return NUM;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __NUM_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.APPLY_PERSON, new org.apache.thrift.meta_data.FieldMetaData("ApplyPerson", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.CONSTRUCT_POS, new org.apache.thrift.meta_data.FieldMetaData("ConstructPos", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.CONSTRUCT_UNIT, new org.apache.thrift.meta_data.FieldMetaData("ConstructUnit", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.TIME, new org.apache.thrift.meta_data.FieldMetaData("Time", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.MATERIAL_CODE, new org.apache.thrift.meta_data.FieldMetaData("MaterialCode", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.NUM, new org.apache.thrift.meta_data.FieldMetaData("Num", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(stagingInfo.class, metaDataMap);
  }

  public stagingInfo() {
  }

  public stagingInfo(
    String ApplyPerson,
    String ConstructPos,
    String ConstructUnit,
    String Time,
    String MaterialCode,
    int Num)
  {
    this();
    this.ApplyPerson = ApplyPerson;
    this.ConstructPos = ConstructPos;
    this.ConstructUnit = ConstructUnit;
    this.Time = Time;
    this.MaterialCode = MaterialCode;
    this.Num = Num;
    setNumIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public stagingInfo(stagingInfo other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetApplyPerson()) {
      this.ApplyPerson = other.ApplyPerson;
    }
    if (other.isSetConstructPos()) {
      this.ConstructPos = other.ConstructPos;
    }
    if (other.isSetConstructUnit()) {
      this.ConstructUnit = other.ConstructUnit;
    }
    if (other.isSetTime()) {
      this.Time = other.Time;
    }
    if (other.isSetMaterialCode()) {
      this.MaterialCode = other.MaterialCode;
    }
    this.Num = other.Num;
  }

  public stagingInfo deepCopy() {
    return new stagingInfo(this);
  }

  @Override
  public void clear() {
    this.ApplyPerson = null;
    this.ConstructPos = null;
    this.ConstructUnit = null;
    this.Time = null;
    this.MaterialCode = null;
    setNumIsSet(false);
    this.Num = 0;
  }

  public String getApplyPerson() {
    return this.ApplyPerson;
  }

  public stagingInfo setApplyPerson(String ApplyPerson) {
    this.ApplyPerson = ApplyPerson;
    return this;
  }

  public void unsetApplyPerson() {
    this.ApplyPerson = null;
  }

  /** Returns true if field ApplyPerson is set (has been assigned a value) and false otherwise */
  public boolean isSetApplyPerson() {
    return this.ApplyPerson != null;
  }

  public void setApplyPersonIsSet(boolean value) {
    if (!value) {
      this.ApplyPerson = null;
    }
  }

  public String getConstructPos() {
    return this.ConstructPos;
  }

  public stagingInfo setConstructPos(String ConstructPos) {
    this.ConstructPos = ConstructPos;
    return this;
  }

  public void unsetConstructPos() {
    this.ConstructPos = null;
  }

  /** Returns true if field ConstructPos is set (has been assigned a value) and false otherwise */
  public boolean isSetConstructPos() {
    return this.ConstructPos != null;
  }

  public void setConstructPosIsSet(boolean value) {
    if (!value) {
      this.ConstructPos = null;
    }
  }

  public String getConstructUnit() {
    return this.ConstructUnit;
  }

  public stagingInfo setConstructUnit(String ConstructUnit) {
    this.ConstructUnit = ConstructUnit;
    return this;
  }

  public void unsetConstructUnit() {
    this.ConstructUnit = null;
  }

  /** Returns true if field ConstructUnit is set (has been assigned a value) and false otherwise */
  public boolean isSetConstructUnit() {
    return this.ConstructUnit != null;
  }

  public void setConstructUnitIsSet(boolean value) {
    if (!value) {
      this.ConstructUnit = null;
    }
  }

  public String getTime() {
    return this.Time;
  }

  public stagingInfo setTime(String Time) {
    this.Time = Time;
    return this;
  }

  public void unsetTime() {
    this.Time = null;
  }

  /** Returns true if field Time is set (has been assigned a value) and false otherwise */
  public boolean isSetTime() {
    return this.Time != null;
  }

  public void setTimeIsSet(boolean value) {
    if (!value) {
      this.Time = null;
    }
  }

  public String getMaterialCode() {
    return this.MaterialCode;
  }

  public stagingInfo setMaterialCode(String MaterialCode) {
    this.MaterialCode = MaterialCode;
    return this;
  }

  public void unsetMaterialCode() {
    this.MaterialCode = null;
  }

  /** Returns true if field MaterialCode is set (has been assigned a value) and false otherwise */
  public boolean isSetMaterialCode() {
    return this.MaterialCode != null;
  }

  public void setMaterialCodeIsSet(boolean value) {
    if (!value) {
      this.MaterialCode = null;
    }
  }

  public int getNum() {
    return this.Num;
  }

  public stagingInfo setNum(int Num) {
    this.Num = Num;
    setNumIsSet(true);
    return this;
  }

  public void unsetNum() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __NUM_ISSET_ID);
  }

  /** Returns true if field Num is set (has been assigned a value) and false otherwise */
  public boolean isSetNum() {
    return EncodingUtils.testBit(__isset_bitfield, __NUM_ISSET_ID);
  }

  public void setNumIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __NUM_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case APPLY_PERSON:
      if (value == null) {
        unsetApplyPerson();
      } else {
        setApplyPerson((String)value);
      }
      break;

    case CONSTRUCT_POS:
      if (value == null) {
        unsetConstructPos();
      } else {
        setConstructPos((String)value);
      }
      break;

    case CONSTRUCT_UNIT:
      if (value == null) {
        unsetConstructUnit();
      } else {
        setConstructUnit((String)value);
      }
      break;

    case TIME:
      if (value == null) {
        unsetTime();
      } else {
        setTime((String)value);
      }
      break;

    case MATERIAL_CODE:
      if (value == null) {
        unsetMaterialCode();
      } else {
        setMaterialCode((String)value);
      }
      break;

    case NUM:
      if (value == null) {
        unsetNum();
      } else {
        setNum((Integer)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case APPLY_PERSON:
      return getApplyPerson();

    case CONSTRUCT_POS:
      return getConstructPos();

    case CONSTRUCT_UNIT:
      return getConstructUnit();

    case TIME:
      return getTime();

    case MATERIAL_CODE:
      return getMaterialCode();

    case NUM:
      return getNum();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case APPLY_PERSON:
      return isSetApplyPerson();
    case CONSTRUCT_POS:
      return isSetConstructPos();
    case CONSTRUCT_UNIT:
      return isSetConstructUnit();
    case TIME:
      return isSetTime();
    case MATERIAL_CODE:
      return isSetMaterialCode();
    case NUM:
      return isSetNum();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof stagingInfo)
      return this.equals((stagingInfo)that);
    return false;
  }

  public boolean equals(stagingInfo that) {
    if (that == null)
      return false;

    boolean this_present_ApplyPerson = true && this.isSetApplyPerson();
    boolean that_present_ApplyPerson = true && that.isSetApplyPerson();
    if (this_present_ApplyPerson || that_present_ApplyPerson) {
      if (!(this_present_ApplyPerson && that_present_ApplyPerson))
        return false;
      if (!this.ApplyPerson.equals(that.ApplyPerson))
        return false;
    }

    boolean this_present_ConstructPos = true && this.isSetConstructPos();
    boolean that_present_ConstructPos = true && that.isSetConstructPos();
    if (this_present_ConstructPos || that_present_ConstructPos) {
      if (!(this_present_ConstructPos && that_present_ConstructPos))
        return false;
      if (!this.ConstructPos.equals(that.ConstructPos))
        return false;
    }

    boolean this_present_ConstructUnit = true && this.isSetConstructUnit();
    boolean that_present_ConstructUnit = true && that.isSetConstructUnit();
    if (this_present_ConstructUnit || that_present_ConstructUnit) {
      if (!(this_present_ConstructUnit && that_present_ConstructUnit))
        return false;
      if (!this.ConstructUnit.equals(that.ConstructUnit))
        return false;
    }

    boolean this_present_Time = true && this.isSetTime();
    boolean that_present_Time = true && that.isSetTime();
    if (this_present_Time || that_present_Time) {
      if (!(this_present_Time && that_present_Time))
        return false;
      if (!this.Time.equals(that.Time))
        return false;
    }

    boolean this_present_MaterialCode = true && this.isSetMaterialCode();
    boolean that_present_MaterialCode = true && that.isSetMaterialCode();
    if (this_present_MaterialCode || that_present_MaterialCode) {
      if (!(this_present_MaterialCode && that_present_MaterialCode))
        return false;
      if (!this.MaterialCode.equals(that.MaterialCode))
        return false;
    }

    boolean this_present_Num = true;
    boolean that_present_Num = true;
    if (this_present_Num || that_present_Num) {
      if (!(this_present_Num && that_present_Num))
        return false;
      if (this.Num != that.Num)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_ApplyPerson = true && (isSetApplyPerson());
    list.add(present_ApplyPerson);
    if (present_ApplyPerson)
      list.add(ApplyPerson);

    boolean present_ConstructPos = true && (isSetConstructPos());
    list.add(present_ConstructPos);
    if (present_ConstructPos)
      list.add(ConstructPos);

    boolean present_ConstructUnit = true && (isSetConstructUnit());
    list.add(present_ConstructUnit);
    if (present_ConstructUnit)
      list.add(ConstructUnit);

    boolean present_Time = true && (isSetTime());
    list.add(present_Time);
    if (present_Time)
      list.add(Time);

    boolean present_MaterialCode = true && (isSetMaterialCode());
    list.add(present_MaterialCode);
    if (present_MaterialCode)
      list.add(MaterialCode);

    boolean present_Num = true;
    list.add(present_Num);
    if (present_Num)
      list.add(Num);

    return list.hashCode();
  }

  @Override
  public int compareTo(stagingInfo other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetApplyPerson()).compareTo(other.isSetApplyPerson());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetApplyPerson()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.ApplyPerson, other.ApplyPerson);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetConstructPos()).compareTo(other.isSetConstructPos());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetConstructPos()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.ConstructPos, other.ConstructPos);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetConstructUnit()).compareTo(other.isSetConstructUnit());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetConstructUnit()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.ConstructUnit, other.ConstructUnit);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetTime()).compareTo(other.isSetTime());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTime()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.Time, other.Time);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetMaterialCode()).compareTo(other.isSetMaterialCode());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetMaterialCode()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.MaterialCode, other.MaterialCode);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetNum()).compareTo(other.isSetNum());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetNum()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.Num, other.Num);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("stagingInfo(");
    boolean first = true;

    sb.append("ApplyPerson:");
    if (this.ApplyPerson == null) {
      sb.append("null");
    } else {
      sb.append(this.ApplyPerson);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("ConstructPos:");
    if (this.ConstructPos == null) {
      sb.append("null");
    } else {
      sb.append(this.ConstructPos);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("ConstructUnit:");
    if (this.ConstructUnit == null) {
      sb.append("null");
    } else {
      sb.append(this.ConstructUnit);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("Time:");
    if (this.Time == null) {
      sb.append("null");
    } else {
      sb.append(this.Time);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("MaterialCode:");
    if (this.MaterialCode == null) {
      sb.append("null");
    } else {
      sb.append(this.MaterialCode);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("Num:");
    sb.append(this.Num);
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws TException {
    // check for required fields
    if (ApplyPerson == null) {
      throw new TProtocolException("Required field 'ApplyPerson' was not present! Struct: " + toString());
    }
    if (ConstructPos == null) {
      throw new TProtocolException("Required field 'ConstructPos' was not present! Struct: " + toString());
    }
    if (ConstructUnit == null) {
      throw new TProtocolException("Required field 'ConstructUnit' was not present! Struct: " + toString());
    }
    if (Time == null) {
      throw new TProtocolException("Required field 'Time' was not present! Struct: " + toString());
    }
    if (MaterialCode == null) {
      throw new TProtocolException("Required field 'MaterialCode' was not present! Struct: " + toString());
    }
    // alas, we cannot check 'Num' because it's a primitive and you chose the non-beans generator.
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class stagingInfoStandardSchemeFactory implements SchemeFactory {
    public stagingInfoStandardScheme getScheme() {
      return new stagingInfoStandardScheme();
    }
  }

  private static class stagingInfoStandardScheme extends StandardScheme<stagingInfo> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, stagingInfo struct) throws TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // APPLY_PERSON
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.ApplyPerson = iprot.readString();
              struct.setApplyPersonIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // CONSTRUCT_POS
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.ConstructPos = iprot.readString();
              struct.setConstructPosIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // CONSTRUCT_UNIT
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.ConstructUnit = iprot.readString();
              struct.setConstructUnitIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // TIME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.Time = iprot.readString();
              struct.setTimeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // MATERIAL_CODE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.MaterialCode = iprot.readString();
              struct.setMaterialCodeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // NUM
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.Num = iprot.readI32();
              struct.setNumIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      if (!struct.isSetNum()) {
        throw new TProtocolException("Required field 'Num' was not found in serialized data! Struct: " + toString());
      }
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, stagingInfo struct) throws TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.ApplyPerson != null) {
        oprot.writeFieldBegin(APPLY_PERSON_FIELD_DESC);
        oprot.writeString(struct.ApplyPerson);
        oprot.writeFieldEnd();
      }
      if (struct.ConstructPos != null) {
        oprot.writeFieldBegin(CONSTRUCT_POS_FIELD_DESC);
        oprot.writeString(struct.ConstructPos);
        oprot.writeFieldEnd();
      }
      if (struct.ConstructUnit != null) {
        oprot.writeFieldBegin(CONSTRUCT_UNIT_FIELD_DESC);
        oprot.writeString(struct.ConstructUnit);
        oprot.writeFieldEnd();
      }
      if (struct.Time != null) {
        oprot.writeFieldBegin(TIME_FIELD_DESC);
        oprot.writeString(struct.Time);
        oprot.writeFieldEnd();
      }
      if (struct.MaterialCode != null) {
        oprot.writeFieldBegin(MATERIAL_CODE_FIELD_DESC);
        oprot.writeString(struct.MaterialCode);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(NUM_FIELD_DESC);
      oprot.writeI32(struct.Num);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class stagingInfoTupleSchemeFactory implements SchemeFactory {
    public stagingInfoTupleScheme getScheme() {
      return new stagingInfoTupleScheme();
    }
  }

  private static class stagingInfoTupleScheme extends TupleScheme<stagingInfo> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, stagingInfo struct) throws TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      oprot.writeString(struct.ApplyPerson);
      oprot.writeString(struct.ConstructPos);
      oprot.writeString(struct.ConstructUnit);
      oprot.writeString(struct.Time);
      oprot.writeString(struct.MaterialCode);
      oprot.writeI32(struct.Num);
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, stagingInfo struct) throws TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.ApplyPerson = iprot.readString();
      struct.setApplyPersonIsSet(true);
      struct.ConstructPos = iprot.readString();
      struct.setConstructPosIsSet(true);
      struct.ConstructUnit = iprot.readString();
      struct.setConstructUnitIsSet(true);
      struct.Time = iprot.readString();
      struct.setTimeIsSet(true);
      struct.MaterialCode = iprot.readString();
      struct.setMaterialCodeIsSet(true);
      struct.Num = iprot.readI32();
      struct.setNumIsSet(true);
    }
  }

}

