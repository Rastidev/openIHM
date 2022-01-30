package test;

import openihm.api.essential.Launcher;
import openihm.api.essential.Scene;
import openihm.api.essential.Stage;
import openihm.api.graphics.Image;
import openihm.api.view.ImageView;
import openihm.interfaces.Root;

final class MyClass extends Launcher{
	
	public static void main(java.lang.String[] args) { launch(new MyClass()); }

	@Override
	public void start(Stage stage) {
//		Graphics shapeGreen = new Graphics() {
//			@Override
//			public void draw() {
//				fillRect(0, 0, 1, 1, 0x0000ff00);
//			}
//		};
//		Graphics shapeRed = new Graphics() {
//			
//			@Override
//			public void draw() {
//				fillRect(0, 0, 1, 1, 0x00ff0000);
//			}
//		};
//		View v1 = new View(shapeGreen);
//		v1.addInterfaceEvent(new PressedEvent() {
//			
//			private boolean isGreen = true;
//
//			@Override
//			public void action(double x, double y, int type) {
//				if(isGreen) v1.setGraphics(shapeRed);
//				else v1.setGraphics(shapeGreen);
//				isGreen = !isGreen;
//				stage.update();
//			}
//			
//		});
//		BlockView v3 = new BlockView();
//		v3.addView(v1, 0, 0, 0.5, 1);
//		v3.addView(new ImageView(randImg(stage.getRoot())), 0.5, 0, 0.5, 1);
//		v3.reloadEvent();
		stage.setScene(new Scene(new ImageView(randImg(stage.getRoot()))));
		stage.update();
		stage.setVisible(true);
//		FileSystem fs = stage.getRoot();
//		String paths = String.$("test.txt");
//		if(fs.fileExist(paths.getValue(), paths.size(), FileSystem.WINDOWS_FILE)) {
//			System.cout.$(String.$("test.txt exist")).endl();
//			System.cout.$(String.$(new java.lang.String(fs.getFile(paths.getValue(), paths.size(), FileSystem.WINDOWS_FILE))));
//		}
//		else System.cerr.$(String.$("test.txt no found"));
	}
	
	private static Image randImg(final Root root) {
		Image img = new Image(10, 10);
		int[][] v = img.value();
		for(int i = 0; i < img.width(); i += 1)
			for(int j = 0; j < img.height(); j += 1) {
				v[i][j] =  root.rand();
			}
		return img;
	}
	

}
