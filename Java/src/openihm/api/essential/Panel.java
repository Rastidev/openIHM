package openihm.api.essential;

import openihm.api.graphics.Drawable;
import openihm.api.graphics.Image;
import openihm.api.graphics.Shape;
import openihm.api.utils.Matrice;
import openihm.interfaces.Graphics;

class Panel implements Drawable{
	
	private final Graphics g;
	
	private int width = 0;
	
	private int height = 0;
	
	public Panel(final Graphics g) {
		this.g = g;
	}
	
	public Panel(final Graphics g, final int width, final int height) { 
		this(g);
		if(width < 0) this.width = 0;
		else this.width = width;
		if(height < 0) this.height = 0;
		else this.height = height;
	}
	
	public int getWidth() { return this.width; }
	
	public int getHeight() { return this.height; }
	
	public Graphics getGraphics(){ return g; }
	
	public void setSize(final int width, final int height) {
		this.width = width;
		this.height = height;
	}
	
	public void update() { g.update(); }
	
	@Override
	public void fillRect(int x, int y, final int width, final int height, final int color) {
		if(width < 0 || height < 0) return;
		int maxX = width + x;
		int maxY = height + y;
		if(maxX > this.width) maxX = this.width;
		if(maxY > this.height) maxY = this.height;
		if(x < 0) x = 0;
		if(y < 0) y = 0;
		for(int i = x; i < maxX; i += 1)
			for (int j = y; j < maxY; j += 1)
				g.drawPixel(i, j, color);
	}

	@Override
	public void drawShape(final Shape shape, final int x, final int y, final int width, final int height) { shape.draw(this, x , y, width, height); }

	@Override
	public void fillCustomRect(final Matrice<Integer> matrix, int x, int y) {
		final int width = matrix.getWidth();
		final int height = matrix.getHeight();
		int maxX = width + x;
		int maxY = height + y;
		if(maxX > this.width) maxX = this.width;
		if(maxY > this.height) maxY = this.height;
		if(x < 0) x = 0;
		if(y < 0) y = 0;
		final Integer[][] value = matrix.getArrays();
		for(int i = x; i < maxX; i += 1)
			for (int j = y; j < maxY; j += 1)
				g.drawPixel(i, j, value[i][j]);
	}

	@Override
	public void drawImage(final Image image, int x, int y, final int width, final int height) {
		if(width < 0 || height < 0) return;
		int maxX = width + x;
		int maxY = height + y;
		if(maxX > this.width) maxX = this.width;
		if(maxY > this.height) maxY = this.height;
		if(x < 0) x = 0;
		if(y < 0) y = 0;
		final double varX = (double) (image.width()) / width;
		final double varY = (double) (image.height()) / height;
		final int[][] value = image.value();
		for(int i = x; i < maxX; i += 1)
			for (int j = y; j < maxY; j += 1)
				g.drawPixel(i, j, value[(int) ((i - x) * varX)][(int) ((j - y) * varY)]);
	}
	
	

}
