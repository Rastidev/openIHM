package openihm.api.utils;

import openihm.api.lang.String;
import openihm.api.system.System;

public class Array<T> implements List<T>{
	
	private final int size;
	
	private final Object[] value;
	
	public Array(final int size, final T default_value) {
		value =  new Object[size];
		for(int i = 0; i < size; i++) value[i] = default_value;
		this.size = size;
	}
	
	public Array(final int size) { this(size, null); }
	
	public Array(final List<T> list) {
		value =  new Object[list.size()];
		Iterator<T> it = list.iterator();
		for(int i = 0; i < list.size(); i++) {
			value[i] = it.get();
			it.next();
		}
		this.size = list.size();
	}
	
	
	private boolean isBadIndex(final int index) {
		if(index >= size) {
			System.cerr.$(new String("bad index : size=")).$(size);
			System.cerr.$(new String(" / index=")).$(index).endl();
			return true;
		}
		if(index < 0) {
			System.cerr.$(new String("bad index: index < 0 : index =")).$(index).endl();
			return true;
		}
		return false;
	}
	
	public ConstArray<T> getConstArray() { return new ConstArray<>(this); }

	@Override
	public int size() { return size; }
	
	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			
			private int i;

			@Override
			public void next() { i++; }

			@SuppressWarnings("unchecked")
			@Override
			public T get() { return (T) value[i]; }

			@Override
			public boolean set(T value) {
				Array.this.value[i] = value;
				return true;
			}

			@Override
			public boolean isEnd() { return i >= size; }
			
		};
	}

	@SuppressWarnings("unchecked")
	@Override
	public T get(int index) { 
		if(isBadIndex(index)) return null;
		return (T) value[index];
	}

	@Override
	public boolean set(int index, T e) {
		if(isBadIndex(index)) return false;
		value[index] = e;
		return true;
	}

	@Override
	public boolean add(T e) {return false;}
	@Override
	public boolean insert(int index, T e) {return false;}
	@Override
	public boolean remove(int index) {return false;}
	@Override
	public boolean resize(int size) {return false;}
	@Override
	public boolean addFirst(T e) {return false;}
	@Override
	public boolean removeLast() {return false;}
	@Override
	public boolean removeFirst() {return false;}

}
