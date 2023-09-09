package openihm.api.utils;

import openihm.api.lang.Object;

public class Value<T> extends Object{
	
	// la valeur
	private T value;
	
	//contructeur par defaut
	public Value(){};
	
	
	/*
	 * met une valeur au noeud
	 * $value @T la valeur
	 */
	public Value(final T value){
		this.value = value;
	};
	
	//constructeur par copie
	public Value(final Value<T> i) {
		value = i.value;
	}
	
	/*
	 * renvoie @T la valeur
	 */
	public final T getValue() { return value; }
	
	/*
	 * met une valeur au noeud
	 * $value @T la valeur
	 */
	public void setValue(final T value) { this.value = value; }

}
