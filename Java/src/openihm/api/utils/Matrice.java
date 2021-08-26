package openihm.api.utils;

public class Matrice<T> {
	
	private final Object[][] Value;
	
	private final int width;
	
	private final int height;
	
	public Matrice(final int width, final int height) {
		this.width = width;
		this.height = height;
		Value = new Object[width][height];
	}
	
	@SuppressWarnings("unchecked")
	public T[][] getArrays() { return (T[][]) Value; }
	
	public int getWidth() { return width; }
	
	public int getHeight() { return height; }
	
	private boolean isBadIndex(final int i, final int j) {
		if(i < 0 || i >= width || j < 0 || j >= height) return true;
		return false;
	}
	
	public boolean setValue(final int i, final int j, final T value) {
		if(isBadIndex(i, j)) return false;
		Value[i][j] = value;
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public T getValue(final int i, final int j) { return (T) Value[i][j]; }

}
