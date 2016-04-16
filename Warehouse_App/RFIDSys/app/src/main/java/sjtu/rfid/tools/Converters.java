/**
 * This Java Source Code and its associated Class files are
 * <P>
 * (c) 2004  Integral RFID
 * <P>
 * For further information, contact :
 * <P>
 * Chris Parkinson
 * Integral RFID
 * 1761 George Washington Way
 * Suite 188
 * Richland
 * WA 99352
 */


package sjtu.rfid.tools;

import java.util.*;

/**
 * Various Converter Methods and General Utilities
 * to input IO
 * <P>
 * @version 1.0 August 2004
 * @author Chris Parkinson
 */

public class Converters {




////////////////////////////////////////////////////////
//
// Convert a Byte Array to a Display String
//
////////////////////////////////////////////////////////
/**
 * Convert a Byte Array to a Hex String with a chracters separating each byte
 * <P>
 * @param byte array
 * @param separator the separator string
 * @return a display String
 */
 public static String toHexString(byte byteArray[], String separator){
    return toHexString(byteArray, separator, false);
}

/**
 * Convert a Byte Array to a Hex String with a chracters separating each byte
 * <P>
 * @param byte array
 * @param separator the separator string
 * @param isTagID - if true put separator every 2 bytes instead of every 1
 * @return a display String
 */
 public static String toHexString(byte byteArray[], String separator, boolean isTagID){
    if (byteArray==null)return "NULL Bytes";
    return toHexString(byteArray, 0, byteArray.length, separator, isTagID);
}

 /**
 * Convert a Byte Array to a Hex String with a chracters separating each byte
 * <P>
 * @param byte array
 * @param separator the separator string
 * @return a display String
 */
 public static String toHexString(byte byteArray[], int offset, int length, String separator){
    return toHexString(byteArray, offset, length, separator, false);
}

/**
 * Convert a Byte Array to a Hex String with a chracters separating each byte
 * <P>
 * @param byte array
 * @param separator the separator string
 * @param isTagID - if true put separator every 2 bytes instead of every 1
 * @return a display String
 */
 public static String toHexString(byte byteArray[], int offset, int length, String separator, boolean isTagID){
    if (byteArray==null)return "NULL Bytes";
    if (separator==null)separator="";
    StringBuffer buffer = new StringBuffer();
    int evenCounter=0;
    for (int i=length-1; i>=0; i--){
        String token = Integer.toHexString((int)byteArray[offset+i] & 0xff);
        buffer.insert(0, token);
        if (token.length()==0)buffer.insert(0, "00");
        if (token.length()==1)buffer.insert(0, "0");
        evenCounter++;
        if (isTagID==false)buffer.insert(0, separator);
        if (isTagID==true && evenCounter==2)buffer.insert(0,separator);
        if (evenCounter==2)evenCounter=0;

    }
    return buffer.toString().toUpperCase().trim();
}



/**
 * Convert a Byte Array to a Binary String
 * <P>
 * @param byte array
 * @return a display String
 * @param padding length (-1) to not pad
 */
 public static String toBinaryString(byte byteArray[], int padLength){
    if (byteArray==null)return "NULL Bytes";
    StringBuffer buffer = new StringBuffer();
    for (int i=byteArray.length-1; i>=0; i--){
	String token = Integer.toBinaryString((int)byteArray[i] & 0xff);
	buffer.insert(0, token);
	//Pad to fill this byte unless its the last byte
	if (i>0)
	    for (int j=token.length(); j<8; j++)buffer.insert(0, "0");
    }
    //Finally pad to fill required length
    if (padLength>0){
        int l = buffer.length();
        for (int i =l; i<padLength; i++)buffer.insert(0, "0");
    }
    return buffer.toString();
}
////////////////////////////////////////////////////////
//
// Convert an Integer to a Hex String
//
////////////////////////////////////////////////////////
/**
 * Convert an Integer to a Hex String
 * <P>
 * @param integer the int to convert
 * @param byteLength number of bytes to convert
 * @return a display String
 */
 public static String toHexString(long value, int byteCount){
    byte byteArray[] = new byte[byteCount];
    if (byteCount>=1)byteArray[byteCount-1] = (byte)(value & 0xff);
    if (byteCount>=2)byteArray[byteCount-2] = (byte)((value >> 8)& 0xff);
    if (byteCount>=3)byteArray[byteCount-3] = (byte)((value >> 16) & 0xff);
    if (byteCount>=4)byteArray[byteCount-4] = (byte)((value >> 24) & 0xff);
    if (byteCount>=5)byteArray[byteCount-5] = (byte)((value >> 32) & 0xff);
    if (byteCount>=6)byteArray[byteCount-6] = (byte)((value >> 40)& 0xff);
    if (byteCount>=7)byteArray[byteCount-7] = (byte)((value >> 48) & 0xff);
    if (byteCount>=8)byteArray[byteCount-8] = (byte)((value >> 56) & 0xff);
   return toHexString(byteArray, "");
}


/**
 * Convert an Integer to a Binary String
 * <P>
 * @param integer the int to convert
 * @param byteLength number of bytes to convert
 * @return a display String
 */
 public static String toBinaryString(long value, int byteCount){
    byte byteArray[] = new byte[byteCount];
    if (byteCount>=1)byteArray[byteCount-1] = (byte)(value & 0xff);
    if (byteCount>=2)byteArray[byteCount-2] = (byte)((value >> 8)& 0xff);
    if (byteCount>=3)byteArray[byteCount-3] = (byte)((value >> 16) & 0xff);
    if (byteCount>=4)byteArray[byteCount-4] = (byte)((value >> 24) & 0xff);
    if (byteCount>=5)byteArray[byteCount-5] = (byte)((value >> 32) & 0xff);
    if (byteCount>=6)byteArray[byteCount-6] = (byte)((value >> 40)& 0xff);
    if (byteCount>=7)byteArray[byteCount-7] = (byte)((value >> 48) & 0xff);
    if (byteCount>=8)byteArray[byteCount-8] = (byte)((value >> 56) & 0xff);
    return toBinaryString(byteArray, byteCount*8);
}



////////////////////////////////////////////////////////
//
// Convert a Display String to a Byte Array
//
////////////////////////////////////////////////////////
/**
 * Convert a Display String of Hex chars to a Byte Array
 * Each 2 chars is converted to a byte, with assembly
 * working left to right
 * Any characters not of Hex origin will be ignored
 * <P>
 * @param string the display string
 * @param byte array
 */
 public static byte[] fromHexString(String displayString){
    if (displayString==null)return new byte[0];
    if (displayString.length()<1)return new byte[0];

    displayString = displayString.toUpperCase();

    //Remove all non hex characters
    StringBuffer buffer = new StringBuffer();
    for (int i=0; i<displayString.length(); i++){
	char c = displayString.charAt(i);
	if (c>='0' && c<='9')buffer.append(c);
	else if (c>='A' && c<='F')buffer.append(c);
  }

    //Is this even or odd length ? If odd, add a zero
    if ( (int)(buffer.length()/2)*2 != buffer.length())buffer.insert(0, "0");

    //Now get each 2 characters and convert to bytes
    byte byteArray[] = new byte[buffer.length()/2];
    for (int i=0; i<byteArray.length; i++){
            int c1 = buffer.charAt(i*2);
            if (c1>='A')c1=c1-'A' + 10;
            else c1=c1-'0';
            int c2 = buffer.charAt((i*2)+1);
            if (c2>='A')c2=c2-'A' + 10;
            else c2=c2-'0';
 	    byteArray[i] = (byte)(c1*16 + c2);
    }
    return byteArray;
}

/**
 * Convert a Display String of Binary chars to a Byte Array
 * Each 8 chars are converted to a byte, with assembly
 * working left to right
 * Any characters not of Binary origin will be ignored
 * <P>
 * @param string the display string
 * @param byte array
 */
 public static byte[] fromBinaryString(String displayString){
    if (displayString==null)return new byte[0];
    if (displayString.length()<1)return new byte[0];

    //Remove all non binary characters
    StringBuffer buffer = new StringBuffer();
    for (int i=0; i<displayString.length(); i++){
	char c = displayString.charAt(i);
	if (c=='0' || c=='1')buffer.append(c);
   }

    //How many bytes ?
    int byteCount = buffer.length()/8;
    if ((byteCount * 8)!=buffer.length()){
	byteCount++;
	//Now round up length to div 8
	do{
	    buffer.insert(0, "0");
	}while(buffer.length()<(byteCount*8));
    }

    int stringIndex=buffer.length()-1;
    byte byteArray[]=new byte[byteCount];
    for (int byteIndex=byteCount-1; byteIndex>=0; byteIndex--){
	int b = 0;
	for (int bitIndex=7; bitIndex>=0; bitIndex--){
	    b=b*2;
	    if (buffer.charAt(stringIndex-bitIndex)=='1')b= b + 1;
	}
	stringIndex=stringIndex-8;
	byteArray[byteIndex]=(byte)b;
    }
    return byteArray;
}

 public static void main(String[] args){
	 System.out.println(new String(fromHexString("32303135313231323030303030303035")));
 }

}