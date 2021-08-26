package openihm.api.utils;

public final class ConstArray<T> extends Array<T>{
	
	public ConstArray(int size) { super(size); }

	public ConstArray(int size, T default_value) { super(size, default_value); }

	public ConstArray(List<T> list) { super(list); }
	
	public boolean set(int index, T e) { return false; }
	




}
