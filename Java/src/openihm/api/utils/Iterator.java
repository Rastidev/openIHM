package openihm.api.utils;

public interface Iterator<T> {
	
	public void next();
	
	public T get();
	
	public boolean set(final T value);
	
	public boolean isEnd();

}
