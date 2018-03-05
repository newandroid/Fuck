package css.com.fuck.utils;

import java.io.UnsupportedEncodingException;

public class BinaryUtils {
	
	public static byte[] int2BytesByLH(int value) {
		int temp = value;
		byte[] b = new byte[4];
		for (int i = 0; i < b.length; i++) {
			b[i] = new Integer(temp & 0xff).byteValue();// 将最低位保存在最高位
			temp = temp >> 8; // 向右移8位
		}
		return b;
	}

	public static int byteArray2Int(byte[] bytes){
		byte[] array = new byte[4];
		for (int i = 0; i < 4; i++) {
			array[i] = bytes[3 - i];
		}
		int result = 0;
		byte loop;
		for (int i = 0; i < 4; i++) {
			loop = array[i];
			int offSet = array.length - i - 1;
			result += (loop & 0xFF) << (8 * offSet);
		}
		return result;
	}

	
    public static byte[] shortToByte(short number) { 
        int temp = number; 
        byte[] b = new byte[2]; 
        for (int i = 0; i < b.length; i++) { 
            b[i] = new Integer(temp & 0xff).byteValue();// 将最低位保存在最低位
            temp = temp >> 8; // 向右移8位 
        } 
        return b; 
    } 
 
    
    public static short byteToShort(byte[] b) { 
        short s = 0; 
        short s0 = (short) (b[0] & 0xff);// 最低位 
        short s1 = (short) (b[1] & 0xff); 
        s1 <<= 8; 
        s = (short) (s0 | s1); 
        return s; 
    }
    
    
    public static String CRC16CCITT(String content, String characterEncoding) throws UnsupportedEncodingException {
    	int crc = 0xFFFF;          // initial value
        int polynomial = 0x1021;   // 0001 0000 0010 0001  (0, 5, 12) 
        byte[] bytes = content.getBytes(characterEncoding);
        for (byte b : bytes) {
            for (int i = 0; i < 8; i++) {
                boolean bit = ((b   >> (7-i) & 1) == 1);
                boolean c15 = ((crc >> 15    & 1) == 1);
                crc <<= 1;
                if (c15 ^ bit) crc ^= polynomial;
             }
        }
        crc &= 0xffff;
        return Integer.toHexString(crc);
    }
    
    public static String CRC16CCITT(byte[] bytes) {
    	int crc = 0xFFFF;          // initial value
        int polynomial = 0x1021;   // 0001 0000 0010 0001  (0, 5, 12) 
        for (byte b : bytes) {
            for (int i = 0; i < 8; i++) {
                boolean bit = ((b   >> (7-i) & 1) == 1);
                boolean c15 = ((crc >> 15    & 1) == 1);
                crc <<= 1;
                if (c15 ^ bit) crc ^= polynomial;
             }
        }
        crc &= 0xffff;
        return Integer.toHexString(crc);
    }
    
    public static byte[] hexStringToBytes(String hexString) {
	       if (hexString == null || hexString.equals("")) {
	           return null;
	       }
	       if (hexString.length() % 2 != 0) {
	    	   hexString = "0" + hexString;
	       }
	       hexString = hexString.toUpperCase();
	       int length = hexString.length() / 2;
	       char[] hexChars = hexString.toCharArray();
	       byte[] d = new byte[length];
	       for (int i = 0; i < length; i++) {
	           int pos = i * 2;
	           d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
	       }
	       return d;
	}
	
    public static byte charToByte(char c) {
	       return (byte) "0123456789ABCDEF".indexOf(c);
	}
    
    public static String bytesToHexString(byte[] src){
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
    
    public static String hexString2binaryString(String hexString)
	{
		if (hexString == null || hexString.length() % 2 != 0)
			return null;
		StringBuffer sbBinary = new StringBuffer();
		String tmp;
		for (int i = 0; i < hexString.length(); i++)
		{
			tmp = "0000"
					+ Integer.toBinaryString(Integer.parseInt(hexString
							.substring(i, i + 1), 16));
			sbBinary.append(tmp.substring(tmp.length() - 4));
		}
		return sbBinary.toString();
	}
    
    public static byte byteCheckSum(String hexString) {
    	int checkSum = 0;
    	byte[] bytes = hexStringToBytes(hexString);
    	for (byte b : bytes) {
    		checkSum = checkSum + b;
		}
    	checkSum = ~checkSum;
    	checkSum = checkSum + 1;
        return (byte)checkSum;
    }


	
}