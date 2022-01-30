package openihm.api.graphics;

public abstract class Graphics {
	
	private Drawable g;
	private int x;
	private int y;
	private int width;
	private int height;
	
	
	
	public void draw(final Drawable g, final int x, final int y, final int width, final int height) {
		this.g = g;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		draw();
	}
	
	public abstract void draw();

	
	public void fillRect(final double x, final double y, final double width, final double height, int color) {
		g.fillRect((int)(this.x + x * this.width), (int)(this.y + y * this.height), (int)(width * this.width), (int)(height * this.height), color);
	}

	public void drawGraphics(final Graphics g, final double x, final double y, final double width, final double height) {
		this.g.drawGraphics(g, (int)(this.x + x * this.width), (int)(this.y + y * this.height), (int)(width * this.width), (int)(height * this.height));
	}
	
	public void drawImage(final Image image, final double x, final double y, final double width, final double height) {
		g.drawImage(image, (int)(this.x + x * this.width), (int)(this.y + y * this.height), (int)(width * this.width), (int)(height * this.height));
	}
	
}
