package test;

import openihm.api.essential.Launcher;
import openihm.api.essential.Stage;
import openihm.api.graphics.Graphics;
import openihm.api.graphics.Image;
import openihm.api.graphics.Shape;
import openihm.api.system.System;
import openihm.api.utils.String;
import openihm.interfaces.FileSystem;
import openihm.interfaces.Root;

final class MyClass extends Launcher{
	
	public static void main(java.lang.String[] args) { launch(new MyClass()); }

	@Override
	public void start(Stage stage) {
		/* Graphics g = new Graphics();
		Shape shape = new Shape() {
			
			@Override
			public void draw() {
				fillRect(0.2, 0.2, 0.7, 0.5, 0x0000ff00);
				fillRect(0.1, 0.8, 0.1, 0.9, 0x000000ff);
			}
		};
		Shape shape2 = new Shape() {
			
			@Override
			public void draw() {
				fillRect(0.8, 0.7, 0.8, 0.8, 0x00ff0000);
				drawShape(shape, 0.5, 0.5, 1, 1);
			}
		};
		g.addShape(shape);
		g.addShape(shape2);
		
		stage.getPanel().drawShape(g, 0, 0, 500, 200);
		Image img = new Image(10, 10);
		int[][] v = img.value();
		for(int i = 0; i < img.width(); i += 1)
			for(int j = 0; j < img.height(); j += 1) {
				v[i][j] =  stage.getRoot().rand();
			}
		stage.getPanel().drawImage(img, 0, 0, 200, 200);
		stage.setVisible(true); */
		FileSystem fs = stage.getRoot();
		String paths = String.$("test.txt");
		if(fs.fileExist(paths.getValue(), paths.size(), FileSystem.WINDOWS_FILE)) {
			System.cout.$(String.$("test.txt exist")).endl();
			System.cout.$(String.$(new java.lang.String(fs.getFile(paths.getValue(), paths.size(), FileSystem.WINDOWS_FILE))));
		}
		else System.cerr.$(String.$("test.txt no found"));
	}

}
