package openihm.api.utils;

public class String {

	private char[] value;
	
	private int size = 0;
	
	public static String $(final Object o) { return new String(o.toString().toCharArray(), o.toString().length()); }
	
	public String() {
		value = new char[0];
		size = 0;
	}
	
	// warning work only on java
	public String(final char[] value, final int size) {
		this.value = value;
		this.size = size;
	}
	
	public char[] getValue() { return value; }
	
	public int size() { return size; }

}
