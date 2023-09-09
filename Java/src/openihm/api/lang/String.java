package openihm.api.lang;

import openihm.api.utils.Iterator;
import openihm.api.utils.List;

public class String extends Object{
	
	/////////////////////////////////////////
	// Object Function
	////////////////////////////////////////
	private static int _id_ = 0;
	public static int _id_() {
		if(_id_ == 0) _id_ = Object._newId_();
		return _id_;
	}
	public int[] _class_() { return F.codeClassIds(_id_(), super._class_()); }
	public String _className_() { return new String("openihm.api.lang.String"); }
	public Object _new_() {
		final byte[] value = new byte[size];
		for(int i = 0; i < size; i += 1) value[i] = this.value[i];
		return new String(value, size); 
	}
	public boolean _del_() {
		value = null;
		size = -1;
		return true; 
	}
	public boolean _set_(final Object obj) {
		if(!obj._instanceOf_(_id_())) return false;
		final String str = (String) obj;
		size = str.size;
		value = new byte[size];
		for(int i = 0; i < size; i += 1) value[i] = str.value[i];
		return true;
	}
	public byte[] _data_() { return F.newClassData(value, size); }
	////////////////////////////////////////////////////////////////////////////
	// End
	/////////////////////////////////////////////////////////////////////////////
	
	
	
	

	private byte[] value;
	
	private int size = 0;
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// only on java
	public String(final java.lang.Object o) { this(o.toString().getBytes(), o.toString().length()); }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public String() { this(0); }
	
	public String(final int size) { this(new byte[size], size); }
	
	public String(final byte[] value, final int size) {
		this.value = value;
		this.size = size;
	}
	
	public String(final String str) {
		value = str.value;
		size = str.size;
	}
	
	public String(final List<Byte> list) {
		size = list.size();
		value =  new byte[size];
		Iterator<Byte> it = list.iterator();
		for(int i = 0; i < list.size(); i++) {
			value[i] = it.get();
			it.next();
		}
	}
	
	public static String merge(final String[] strs, final int sizeStrs) {
		int size = 0;
		for(int i = 0; i < sizeStrs; i++) size += strs[i].size;
		final String str = new String(size);
		int index = 0;
		for(int i = 0; i < sizeStrs; i++) {
			str.set(strs[i], index);
			index += strs[i].size;
		}
		return str;
	}
	
	
	public byte[] getValue() { return value; }
	
	public int size() { return size; }
	
	public boolean _add_(final Object obj) {
		if(!obj._instanceOf_(_id_())) return false;
		return add((String) obj);	
	}
	
	public boolean add(final String str) {
		final int newSize = size + str.size;
		final byte[] result = new byte[newSize];
		for(int i = 0; i < size; i += 1) result[i] = value[i];
		for(int i = size; i < newSize; i += 1) result[i] = str.value[newSize - size];
		size = newSize;
		return true;
	}
	
	public boolean add(final byte c) {
		final int newSize = size + 1;
		byte[] result = new byte[newSize];
		for(int i = 0; i < size; i += 1) result[i] = value[i];
		result[size] = c;
		size = newSize;
		return true;
	}
	
	public boolean set(final String str, final int index) {
		for(int i = 0; i < str.size; i += 1) value[i + index] = str.value[i];
		return true;
	}
	
	public boolean set(final byte c, final int index) {
		value[index] = c;
		return true;
	}

}
