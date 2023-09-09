package openihm.api.utils;

import openihm.api.lang.Object;

public class ArrayList<T> implements List<T>{
	
	private int size;
	
	private Object[] value;
	
	
	public ArrayList(final int size, final T default_value) {
		this.size = size;
		value = new Object[size];
		for(int i = 0; i < size; i++) value[i] = (Object) default_value;
	}
	
	public ArrayList(final int size) { this(size, null); }
	
	public ArrayList() { this(0); }
	
	public ArrayList(final List<T> list) {
		value =  new Object[list.size()];
		Iterator<T> it = list.iterator();
		for(int i = 0; i < list.size(); i++) {
			value[i] = (Object) it.get();
			it.next();
		}
		this.size = list.size();
	}
	
	
	@Override
	public int size() { return size; }

	@Override
	public T get(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean set(int index, T e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean resize(int size) {
		// TODO Auto-generated method stub
		return false;
	}

}
