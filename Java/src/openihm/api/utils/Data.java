package openihm.api.utils;

import openihm.api.lang.F;

public class Data {
	
	public static byte[] getData(final byte[] data) {
		final int size = F.sizeof(data);
		byte[] decodeData = new byte[size];
		for(int i = 0; i < size; ++i) decodeData[i] = data[i + 5];
		return decodeData;
	}
	
	public static int sizeInByte(final byte[] data) {
		if((data[0] & 0x60) == 0x60) return 8;
		return (data[0] >> 3) & 0x0f; 
	}
	
	public static long sizeBit(final byte[] data) { return (F.sizeof(data) - 1) * 8 + Data.sizeInByte(data); }

}
