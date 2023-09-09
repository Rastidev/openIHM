package openihm.api.lang;

public class Object {
	
	
	public static int _id_() { return 0; }
	
	private static int lastClassId = 0;
	
	protected static final int _newId_() {
		lastClassId += 1;
		return lastClassId;
	}
	
	
	//Class Operator
	public final boolean _instanceOf_(final int id) {
		final int[] thisIds = _class_();
		final int size = thisIds[0] + 1;
		for(int i = 1; i < size; i += 1) if(thisIds[i] == id) return true;
		return false;
	}
	
	/*
	 * index 0 = size
	 * index 1-infini = interface and class
	 */
	public int[] _class_() { return new int[]{1, 0}; }
	
	public String _className_() { return new String("openihm.api.lang.Object"); }
	
	public Object _ptr_() { return this; }
	
	public Object _new_() { return null; }
	
	public int _ref_() { return 0xffffffff; }
	
	public boolean _del_() { return false; }
	
	/*
	 * 1bit = type of data 0 from openihm and 1 for custom
	 * 2-3bit = type of size (byte = 0)(short = 1)(int = 3)(class = 4)
	 */
	public byte[] _data_() { return new byte[] {0x60, 0x00, 0x00, 0x00, 0x00}; }
	
	public boolean _set_(final Object obj) { return false; }
	
	
	
	
	
	
	
	//Arithmetic Operators
	
	/*
	 * operateur +=
	 */
	public boolean _add_(final Object obj) { return false; }
	
	/*
	 * operateur -=
	 */
	public boolean _sub_(final Object obj) { return false; }
	
	/*
	 * operateur *=
	 */
	public boolean _mul_(final Object obj) { return false; }
	
	/*
	 * operateur /=
	 */
	public boolean _div_(final Object obj) { return false; }
	
	/*
	 * operateur %=
	 */
	public boolean _mod_(final Object obj) { return false; }
	
	/*
	 * operateur ++
	 */
	public boolean _next_() { return false; }
	
	/*
	 * operateur --
	 */
	public boolean _prev_() { return false; }
	
	
	//Relational Operators
	
	/*
	 *  operateur ==
	 */
	public boolean _eql_(final Object obj) { return false; }
	
	/*
	 *  operateur !=
	 */
	public boolean _neql_(final Object obj) { return !_eql_(obj); }
	
	/*
	 *  operateur >
	 */
	public boolean _mor_(final Object obj) { return false; }
	
	/*
	 *  operateur <
	 */
	public boolean _les_(final Object obj) { return false; }
	
	/*
	 * operateur >=
	 */
	public boolean _nles_(final Object obj) { return !_les_(obj); }
	
	/*
	 * operateur <=
	 */
	public boolean _nmor_(final Object obj) { return !_mor_(obj); }
	
	
	//Bitwise Operators
	
	/*
	 * operateur &=
	 */
	public boolean _and_(final Object obj) { return false; }
	
	/*
	 * operateur |=
	 */
	public boolean _or_(final Object obj) { return false; }
	
	/*
	 *  operateur ^=
	 */
	public boolean _xor_(final Object obj) { return false; }
	
	/*
	 * operateur ~=
	 */
	public boolean _not_(final Object obj) { return false; }
	
	/*
	 *  operateur <<=
	 */
	public boolean _lsh_(final Object obj) { return false; }
	
	/*
	 *  operateur >>=
	 */
	public boolean _rsh_(final Object obj) { return false; }
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
