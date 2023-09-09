package openihm.api.lang;

public class Int extends Value<Integer>{
	
	/////////////////////////////////////////
	// Object Function
	////////////////////////////////////////
	private static int _id_ = 0;
	public static int _id_() {
		if(_id_ == 0) _id_ = Object._newId_();
		return _id_;
	}
	public int[] _class_() { return F.codeClassIds(_id_(), super._class_()); }
	public String _className_() { return new String("openihm.api.lang.Int"); }
	public Object _new_() { return new Int(value.intValue()); }
	public boolean _del_() { value = null; return true;}
	public boolean _set_(final Object obj) {
		if(!obj._instanceOf_(_id_())) return false;
		$(((Int)obj).value.intValue());
		return true;
	}
	public byte[] _data_() {
		byte[] data = new byte[9];
		data[0] = 0x60;
		data[1] = 0x4;
		data[2] = 0;
		data[3] = 0;
		data[4] = 0;
		Int.toChars(value, data, 5);
		return data; 
	}
	////////////////////////////////////////////////////////////////////////////
	// End
	/////////////////////////////////////////////////////////////////////////////
	
	public Int() { super(0); }
	
	public Int(final int value) { super(value); }
	
	public Int(final Int value) { super(value.value); }

	public boolean _add_(final Object obj) { 
		if(!obj._instanceOf_(_id_())) return false;
		value += ((Int) obj).value;
		return true;
	}
	
	public boolean _sub_(final Object obj) { 
		if(!obj._instanceOf_(_id_())) return false;
		value -= ((Int) obj).value;
		return true;
	}
	
	public boolean _mul_(final Object obj) {
		if(!obj._instanceOf_(_id_())) return false;
		value *= ((Int) obj).value;
		return true;
	}
	
	public boolean _div_(final Object obj) { 
		if(!obj._instanceOf_(_id_())) return false;
		value /= ((Int) obj).value;
		return true;
	}
	
	public boolean _mod_(final Object obj) {
		if(!obj._instanceOf_(_id_())) return false;
		value %= ((Int) obj).value;
		return true;
	}
	
	public boolean _next_() {
		value++;
		return true;
	}
	
	public boolean _prev_() {
		value--;
		return true;
	}
	
	public static byte toChar(final int a, final int ind){ return (byte) ( (a << (8 * ind)) >> 24 ); }
	
	public static byte toChar(final int a){ return (byte) a; }
	
	public static byte[] toChars(final int a){ 
		return new byte[] { (byte) ((a & 0xff000000) >> 24), (byte) ((a & 0x00ff0000) >> 16), (byte) ((a & 0x0000ff00) >> 8), (byte) (a & 0x000000ff)};
	}
	
	public static byte[] toChars(final int value, final byte[] tab, final int begin) {
		tab[begin] = (byte) ((value & 0xff000000) >> 24);
		tab[begin + 1] = (byte) ((value & 0x00ff0000) >> 16);
		tab[begin + 2] = (byte) ((value & 0x0000ff00) >> 8);
		tab[begin + 3] = (byte) (value & 0x000000ff);
		return tab;
	}
	
	public static long toLong(final int a, final int b) { return (((long) a) << 32) | b; }
	
	public static byte toCharacter(final int a) { return (byte)((a % 10) + 0x30); }
	
	public static String toString(int a) {
		if(a == 0) return new String("0");
		final byte[] digits = new byte[11];
		int size = 0;
		final boolean isNeg = a < 0;
		if(isNeg) a *= -1;
		while(a != 0) {
			digits[size] = toCharacter(a);
			a /= 10;
			size += 1;
		}
		if(isNeg) {
			digits[size] = '-';
			size += 1;
		}
		final byte[] result = new byte[size];
		for(int i = 0; i < size; i += 1) result[size - 1 - i] = digits[i];
		return new String(result, size);
	}

}
