package openihm.api.utils;

public class Value<T> {
	
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
