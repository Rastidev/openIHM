package openihm.api.lang;

public class Char extends Object{
	
	private byte value;
	
	public Char() { this((byte) 0x00); }
	
	public Char(final byte value) {
		this.value = value;
	}
	
	public Char(final Char value) { this(value.value); }
	
	public byte get() { return value; }

	public final void set(final byte value) { this.value = value; }
	
	public static int toInt(final byte a, final byte b, final byte c, final byte d) { return (a << 24) | ((b << 16) | ((c << 8) | d)); }
	
}
