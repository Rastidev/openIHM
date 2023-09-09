package openihm.api.lang;

class Value<T> extends Object{
	
	T value;
	
	public Value(final T value) { this.value = value; }
	
	public final T $() { return value; }
	
	public final void $(final T value) { this.value = value; } 
}
