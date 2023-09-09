package openihm.api.graphics;

public class Image {
	
	private final int[][] value;
	
	private final int width;
	
	private final int height;
	
	
	public Image(final int[][] value, final int width, final int height) {
		this.width = width;
		this.height = height;
		this.value = value;
	}
	
	public Image(final int width, final int height) { this(new int[width][height], width, height); }
	
	public Image(final int width, final int height, final int DefaultColor) {
		this(width, height);
		for(int i = 0; i < width; i++)
			for(int j = 0; j < width; j++)
				value[i][j] = DefaultColor;
	}
	
	public Image(final Image image) {
		this(image.width, image.height);
		for(int i = 0; i < width; i++)
			for(int j = 0; j < width; j++)
				value[i][j] = image.value[i][j];
	}

	public int width() { return width; }
	
	public int height() { return height; }
	
	public int[][] value() { return value; }
	
	public static Image readPNG() {
		return null;
	}


}
