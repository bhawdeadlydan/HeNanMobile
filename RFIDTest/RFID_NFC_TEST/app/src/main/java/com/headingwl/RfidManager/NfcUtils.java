package com.headingwl.RfidManager;

/**
 * Created by L on 2015/12/10.
 */
public class NfcUtils {


	public static byte[] convertHexAsciiToByteArray(byte[] bytes, int offset, int length) {
		byte[] bin = new byte[length / 2];
		for (int x = 0; x < length / 2; x++) {
			bin[x] = (byte) Integer.parseInt(new String(bytes, offset + (x * 2), 2), 16);
		}
		return bin;
	}

	public static byte[] convertASCIIToBin(String ascii) {
		byte[] bin = new byte[ascii.length() / 2];
		for (int x = 0; x < bin.length; x++) {
			bin[x] = (byte) Integer.parseInt(ascii.substring(x * 2, x * 2 + 2), 16);
		}
		return bin;
	}

	public static String convertBinToASCII(byte[] bin) {
		return convertBinToASCII(bin, 0, bin.length);
	}

	public static String convertBinToASCII(byte[] bin, int offset, int length) {
		StringBuilder sb = new StringBuilder();
		for (int x = offset; x < offset + length; x++) {
			String s = Integer.toHexString(bin[x]);

			if (s.length() == 1)
				sb.append('0');
			else
				s = s.substring(s.length() - 2);
			sb.append(s);
		}
		return sb.toString().toUpperCase();
	}

	public static byte[] intTo4Bytes(int i) {
		return new byte[]{(byte) ((i >> 24) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 8) & 255), (byte) (i & 255)};
	}

	public static int bytesToInt(byte[] b, int offset) {
		return (b[offset + 0] & 255) << 24 | (b[offset + 1] & 255) << 16 | (b[offset + 2] & 255) << 8
				| (b[offset + 3] & 255);
	}

	public static int bytesAsciiToByte(byte[] b, int offset) {
		return ((b[offset] << 4 | b[offset + 1]) << 24) | ((b[offset + 2] << 4 | b[offset + 3]) << 16)
				| ((b[offset + 4] << 4 | b[offset + 5]) << 8) | ((b[offset + 6] << 4 | b[offset + 7]));
	}

	public static byte[] byteArrayReverse(byte[] byteArray) {

		byte[] temArray = new byte[byteArray.length];
		for (int i = 0; i < byteArray.length; i++) {
			temArray[i] = byteArray[byteArray.length - i - 1];
		}
		return temArray;
	}

	public static byte bytesToByte(byte[] b, int offset) {
		return (byte) (b[offset] << 4 | b[offset + 1]);
	}


	public static boolean isEqualArray(byte[] b1, byte[] b2) {
		if (b1.length != b2.length)
			return false;

		for (int i = 0; i < b1.length; i++) {
			if (b1[i] != b2[i])
				return false;
		}
		return true;
	}

	public static String binToString(byte[] b1) {
		String s = "";
		if (b1 == null)
			return s;
		for (int i = 0; i < b1.length; i++) {
			s += String.valueOf((char) b1[i]);
		}
		return s;
	}

	public static byte[] doubleToByte(double d) {
		byte[] b = new byte[8];
		long l = Double.doubleToLongBits(d);
		for (int i = 0; i < b.length; i++) {
			b[i] = new Long(l).byteValue();
			l = l >> 8;

		}
		return b;
	}

	public static double byteToDouble(byte[] b) {
		long l;

		l = b[0];
		l &= 0xff;
		l |= ((long) b[1] << 8);
		l &= 0xffff;
		l |= ((long) b[2] << 16);
		l &= 0xffffff;
		l |= ((long) b[3] << 24);
		l &= 0xffffffffl;
		l |= ((long) b[4] << 32);
		l &= 0xffffffffffl;

		l |= ((long) b[5] << 40);
		l &= 0xffffffffffffl;
		l |= ((long) b[6] << 48);

		l |= ((long) b[7] << 56);
		return Double.longBitsToDouble(l);

	}

	public static long bytesTolong4(byte[] b) {
		long temp;
		long res = 0;
		for (int i=0;i<b.length&&i<4;i++) {
			res <<= 8;
			temp = b[i] & 0xff;
			res |= temp;
		}
		return res;
	}

	public static byte[] long4Tobytes(long num) {
		byte[] b = new byte[4];
		for (int i=0;i<4;i++) {
			b[i] = (byte)(num>>>(24-(i*8)));
		}
		return b;
	}
}
