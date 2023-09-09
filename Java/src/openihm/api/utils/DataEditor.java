package openihm.api.utils;

public class DataEditor {
	
	private char[] data;
	
	private int sizeTab;
	
	private long size;
	
	public DataEditor(final DataEditor obj) {
		data = obj.data;
		size = obj.size;
		sizeTab = obj.sizeTab;
	}
	
	public DataEditor(final long size) {
		this.size = size;
		sizeTab = (int) ((size + 7) / 8);
		data = new char[sizeTab];
		for(int i = 0; i < sizeTab; i++) data[i] = 0x00;
	}
	
	public DataEditor() { this(0); }
	
	
//	public boolean resize(final long size) {
//		final int sizeTab = (int) (size / 8 + 1);
//		final char[] data = new char[sizeTab];
//		final int stop;
//		if(sizeTab < this.sizeTab) stop = sizeTab;
//		else stop = this.sizeTab;
//		for(int i = 0; i < stop; i++) data[i] = this.data[i];
//		for(int i = stop; i < sizeTab; i++) data[i] = 0x00;
//		this.data = data;
//		this.size = size;
//		this.sizeTab = sizeTab;
//		return true;
//	}
	
	
	
	private static char getValueChar(final int begin, final int nbBit, final char c) {
		return (char) ((c >> begin));
	}
	
	
	public char[] get(final long begin, final long end) {
		if(end >= size || begin > end) return null;
		final long size = end - begin;
		final int tabSize = (int) ((size + 7) / 8);
		final char[] result = new char[tabSize];
		final char partOne = (char) (begin % 8);
		final char partTwo = (char) (8 - partOne);
		for(int i = 0; i < tabSize; i++) {
			
		}
		
		return result;
	}
	
	public boolean set(final long begin, final char[] value, final long size) {
		return reset(begin, begin + size) && add(begin, value, size);
	}
	
	public boolean add(final long begin, final char[] value, final long size) {
		return false;
	}
	
	public boolean reset(final long begin, final long end) {
		return false;
	}
	
	public boolean reset() { return reset(0, size); }
	
	

}
