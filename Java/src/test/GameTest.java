package test;

import java.util.Timer;

import openihm.interfaces.Graphics;
import openihm.interfaces.Main;
import openihm.interfaces.Root;
import openihm.api.utils.String;

public final class GameTest extends Main{
	
	public static void main(String[] args) { new GameTest(); }
	
	private Graphics g;
	
	private int life;
	
	private int[][] Obj;
	
	private static final int WinX = 500;
	private static final int WinY = 800;
	private static final int NumberLife = 6;
	private static final int backgroundgame = 0x00000000;

	@Override
	public int main(Root root) {
		root.setWindowResizable(false);
		root.setWindowSize(WinX, WinY);
		root.setWindowTitle(String.$("Jeu test").getValue(), 8);
		root.setWindowVisible(true);
		g = root;
		return start();
	}
	
	private int start() {
		life = NumberLife;
		Obj = new int[10][];
		drawRect(0, 0, WinX, WinY, 0x006F2A09);
		drawRect(0, WinY / 12, WinX, WinY - WinY / 12, backgroundgame);
		drawLife();
		Obj = new int[][]{{4, 100}, {6, 100}, {8, 100}, {12, 100}, {15, 100}, {19, 100}};
		//new Tick().start();
		return Root.NOTHING;
	}
	
	
	private void drawLife() {
		for(int i = 0; i < NumberLife; i += 1) {
			if(i < life) drawCoeur(i, false);
			else drawCoeur(i, true);
		}
	}
	
	
	private void drawCoeur(final int id, final boolean isEmpty) {
		final int color; 
		if(isEmpty) color = 0x00777777;
		else color = 0x00ff0000;
		drawRect((WinX / NumberLife) * id + (WinX / NumberLife) / 4, WinY / 48,
				(WinX / NumberLife) / 2, WinY / 24, color);
	}
	
	private static final int SizeObjectX = WinX / 20;
	private static final int SizeObjectY = WinY / 100;
	private void drawObjects() {
		for(int[] tab: Obj) if (tab != null) drawRect(tab[0] * SizeObjectX, tab[1] * SizeObjectY , SizeObjectX, SizeObjectY, 0x00ffffff);
	}
	
	private void addObject(final int x) {
		for(int i = 0; i < Obj.length; i += 1) if(Obj[i] == null) {
			Obj[i] = new int[] {x, 100};
			return;
		}
	}
	
	
	private void drawRect(final int x, final int y, final int witdh, final int height, final int color) {
		for(int i = x; i < witdh + x; i += 1)
			for (int j = y; j < height + y; j += 1)
				g.drawPixel(i, j, color);
	}
	
	private void event(final int x, final int y) {
	}
	
	
	
	private class Tick extends Thread {

		@Override
		public void run() {
			for(int[] tab: Obj) if (tab != null) {
				tab[1] -= 1;
				if(tab[1] == 0) {
					life -= 1;
					if(life == 0) this.interrupt();
				}
			}
			drawObjects();
			try { this.wait(500); } catch (InterruptedException e) { this.interrupt(); }
		}
		
	}

}
