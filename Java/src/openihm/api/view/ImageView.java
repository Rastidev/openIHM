package openihm.api.view;

import openihm.api.graphics.Graphics;
import openihm.api.graphics.Image;

public class ImageView extends View{
	
	private Image image;
	
	public ImageView(final Image img) {
		setImage(img);
	}
	
	public ImageView() {
		this(null);
	}
	
	public void setImage(final Image img) {
		image = img;
		if(image == null) setGraphics(new Graphics(){public void draw(){}});
		else setGraphics(new Graphics() {
			@Override
			public void draw() {
				drawImage(image, 0, 0, 1, 1);
			}
		});
	}
	
	public Image getImage() { return image; }
	
}
