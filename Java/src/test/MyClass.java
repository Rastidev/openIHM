package test;

import openihm.api.dev.Dev;
import openihm.api.essential.Launcher;
import openihm.api.essential.Scene;
import openihm.api.essential.Stage;
import openihm.api.event.PressedEvent;
import openihm.api.file.File;
import openihm.api.file.Paths;
import openihm.api.graphics.Graphics;
import openihm.api.graphics.Image;
import openihm.api.lang.F;
import openihm.api.lang.Int;
import openihm.api.lang.String;
import openihm.api.utils.AdvencedList;
import openihm.api.utils.Array;
import openihm.api.utils.BasicList;
import openihm.api.utils.BinaryReader;
import openihm.api.utils.Data;
import openihm.api.utils.Iterator;
import openihm.api.utils.List;
import openihm.api.utils.Map;
import openihm.api.utils.Pair;
import openihm.api.utils.TreeMap;
import openihm.api.utils.png.PortableNetworkGraphics;
import openihm.api.view.BlockView;
import openihm.api.view.ImageView;
import openihm.api.view.View;
import openihm.interfaces.FileSystem;
import openihm.interfaces.Root;
import openihm.api.system.System;

final class MyClass extends Launcher{
	
	public static void main(java.lang.String[] args) { launch(new MyClass()); }

	@Override
	public void start(Stage stage) {
		f2();
	}
	
	 public void f1(Stage stage) {
			Graphics shapeGreen = new Graphics() {
				@Override
				public void draw() {
					fillRect(0, 0, 1, 1, 0x0000ff00);
				}
			};
			Graphics shapeRed = new Graphics() {
				
				@Override
				public void draw() {
					fillRect(0, 0, 1, 1, 0x00ff0000);
				}
			};
			View v1 = new View(shapeGreen);
			v1.addInterfaceEvent(new PressedEvent() {
				
				private boolean isGreen = true;

				@Override
				public void action(double x, double y, int type) {
					if(isGreen) v1.setGraphics(shapeRed);
					else v1.setGraphics(shapeGreen);
					isGreen = !isGreen;
					stage.update();
				}
				
			});
			BlockView v3 = new BlockView();
			v3.addView(v1, 0, 0, 0.5, 1);
			final Image img;
			File file = new File(Paths.getWindows(new String("a.png")));
			if(!file.exist()) {
				System.cerr.$(new String("test.png no found"));
				img = randImg(stage.getRoot());
			}
			else {
				PortableNetworkGraphics png = new PortableNetworkGraphics();
				String stringFile = file.getContain();
				png.read(stringFile.getValue(), stringFile.size());
				img = png.getImage();
			}
			v3.addView(new ImageView(img), 0.5, 0, 0.5, 1);
			v3.reloadEvent();
			stage.setScene(new Scene(v3));
			stage.update();
			stage.setVisible(true);
	 }
	 
	 
	 
	 private static void f2() {
		 Map<String, Integer> map = new TreeMap<>();
		 List<String> list = new BasicList<String>();
		 list.add(new String("test"));
		 list.add(new String("bonjour"));
		 list.add(new String("null"));
		 list.add(new String("chercher"));
		 list.add(new String("autre"));
		 
		 list.add(new String("tes"));
		 list.add(new String("bonjoure"));
		 list.add(new String(""));
		 list.add(new String("null"));
		 list.add(new String("rkfgepo"));
		 
		 for(int i = 0; i < list.size(); i++) map.put(list.get(i), i);
		 list.add(new String("gfni"));
		 list.add(new String("gnriuegniuergb"));
		 list.add(new String("bonjoures"));
		 list.add(new String("te"));
		 
		 System.cout.$(new String("part2")).endl().endl();
		 for(int i = 0; i < list.size(); i++) {
			 System.cout.$(list.get(i));
			 final Integer r = map.get(list.get(i));
			 if(r == null) System.cout.$(new String(" is null")).endl();
			 else if (r != i) System.cout.$(new String(" has bad index")).endl();
			 else System.cout.$(new String(" is work")).endl();
		 }
		 System.cout.$(map.size()).endl();
		 for(int i = 0; i < list.size(); i++) map.remove(list.get(i));
		 System.cout.$(map.size()).endl();
		 
		 
	 }
	 
	 private static void f3() {
		 final String str = new String("bonjour");
		 BinaryReader btr  = new BinaryReader(str._data_());
		 for(int i = 0; i < btr.size(); i += 1) {
			 	if(i % 8 == 0) System.cout.endl();
				System.cout.$((int) btr.read());
		 }
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
