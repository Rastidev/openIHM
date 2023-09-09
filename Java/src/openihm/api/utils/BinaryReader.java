package openihm.api.utils;

import openihm.api.lang.F;
import openihm.api.lang.Object;

public class BinaryReader extends Object{
	
	private final byte[] data;
	
	private final int sizeTab;
	
	private final long size;
	
	private final long cursorMin;
	
	private long cursor = 0;
	
	public BinaryReader() { this(new byte[0], 0, 0); }
	
	public BinaryReader(final byte[] data) {
		this.data = data;
		this.sizeTab = F.sizeof(data) + 5;
		this.size = (sizeTab - 1) * 8 + Data.sizeInByte(data);
		this.cursorMin = 40;
		cursor = cursorMin;
	}
	
	public BinaryReader(final BinaryReader data) {
		this.data = data.data;
		sizeTab = data.sizeTab;
		size = data.size;
		cursorMin = data.cursorMin;
		cursor = data.cursor;
	}
	
	public BinaryReader(final byte[] data, final long size, final long cursorMin) {
		this.data = data;
		this.size = size;
		this.cursorMin = cursorMin;
		this.sizeTab = (int) ((size + 7) / 8);
		cursor = cursorMin;
	}
	
	
	public boolean setCursor(final long cursor) {
		if(cursor < 0) return false;
		this.cursor = cursor + cursorMin;
		return true;
	}
	
	public long getCursor() { return cursor; }
	
	
	public int read() {
		if(size <= cursor) return -1;
		final int r = (data[(int) (cursor / 8)] << (31 - (cursor % 8))) >>> 31;
		cursor++;
		return r;
	}
	
	public int readInt() { return read(32); }
	
	public int read(final int count) {
		int k = 0x00000000;
		for(int i = 0; i < count; i++) k = (k << 1) | read();
		return k;
	}
	
	public boolean isEnd() { return cursor < size; }

	public byte[] getData() { return data; }

	public long size() { return size - cursorMin; }
	
	public Object _new_() { return new BinaryReader(this); }
	
	
	

}
