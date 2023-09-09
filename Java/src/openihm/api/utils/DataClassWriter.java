package openihm.api.utils;

import openihm.api.lang.Char;
import openihm.api.lang.Int;
import openihm.api.lang.Object;

public class DataClassWriter extends BasicList<Byte>{
	
	
	public boolean addChar(final byte c) { return add(c); }
	
	public boolean addShort(final short s) {
		return false;
	}
	
	public boolean addInt(final int i) {
		add((byte) ((i & 0xff000000) >> 24));
		add((byte) ((i & 0x00ff0000) >> 16));
		add((byte) ((i & 0x0000ff00) >> 8));
		add((byte) (i & 0x000000ff));
		return true;
	}
	
	public boolean addLong(final long l) {
		return false;
	}
	
	public boolean addObject(final Object obj) {
		final byte[] data = obj._data_();
		final int size = Char.toInt(data[1], data[2], data[3], data[4]) + 5;
		for(int i = 5; i < size; i += 1) add(data[i]);
		return true;
	}
	
	public boolean addCharArray(final byte[] array, final int size) {
		for(int i = 0; i < size; i += 1) add(array[i]);
		return true;
	}
	
	public boolean addShortArray(final short[] array, final int size) {
		for(int i = 0; i < size; i += 1) addShort(array[i]);
		return true;
	}
	
	public boolean addIntArray(final int[] array, final int size) {
		for(int i = 0; i < size; i += 1) addInt(array[i]);
		return true;
	}
	
	public boolean addLongArray(final long[] array, final int size) {
		for(int i = 0; i < size; i += 1) addLong(array[i]);
		return true;
	}
	
	public boolean addObjectArray(final Object[] array, final int size) {
		for(int i = 0; i < size; i += 1) addObject(array[i]);
		return true;
	}
	
	public byte[] write() {
		final byte[] data = new byte[size() + 5];
		data[0] = 0x60;
		data[1] = (byte) ((size() & 0xff000000) >> 24);
		data[2] = (byte) ((size() & 0x00ff0000) >> 16);
		data[3] = (byte) ((size() & 0x0000ff00) >> 8);
		data[4] = (byte) (size() & 0x000000ff);
		int i = 5;
		for(Iterator<Byte> it = iterator(); !it.isEnd(); it.next()) {
			data[i] = it.get();
			i += 1;
		}
		return data;
	}
	
	

}
