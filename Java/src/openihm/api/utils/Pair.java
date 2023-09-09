package openihm.api.utils;

import openihm.api.lang.Object;

public class Pair <K, V> extends Object{
	
	private K key;
	
	private V value;
	
	public Pair(final K key, final V value){
		this.value = value;
		this.key = key;
	}
	
	public Pair(final K key) { this(key, null); }
	
	public Pair() { this(null, null); }
	
	public boolean set(final K key, final V value) {
		return setKey(key) & setValue(value);
	}
	
	public boolean setKey(final K key) {
		this.key = key;
		return true;
	}
	
	public boolean setValue(final V value) {
		this.value = value;
		return true;
	}
	
	public K getKey() { return key; }
	
	public V getValue() { return value; }
	

}
