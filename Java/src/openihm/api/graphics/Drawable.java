package openihm.api.graphics;

import openihm.api.utils.Matrice;

public interface Drawable {
	
	public abstract void fillRect(int x, int y, final int width, final int height, final int color);
	
	public abstract void fillCustomRect(final Matrice<Integer> matrice, final int x, final int y);
	
	public abstract void drawImage(final Image image, final int x, final int y, final int witdh, final int height);
	
	public abstract void drawShape(final Shape shape, final int x, final int y, final int witdh, final int height);

}
