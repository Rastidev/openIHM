package test;

import java.util.Timer;
import java.util.TimerTask;

import openihm.interfaces.EventMousePressed;
import openihm.interfaces.Main;
import openihm.interfaces.Root;

public class GameLife extends Main{
	
	public static void main(String[] args) { launch(new GameLife()); }
	
	private static final boolean[] ruleNegative = new boolean[]{ false, false, false, true, false, false, false, false, false};
	
	private static final boolean[] rulePositive = new boolean[]{ false, false, true, true, false, false, false, false, false};
	
	private static final boolean[] ruleNeutre = new boolean[]{ false, false, false, false, false, false, true, true, true};
	
	private static final int stabiliteP = 20;
	
	private static final int resistanceP = 2;
	
	private static final int stabiliteN = 10;
	
	private static final int antipressionN = 1;
	
	
	private int[][] grid;
	
	private Root root;
	
	
	private void init(final int width, final int height) {
		grid = new int[width][height];
		for(int i =0; i < grid.length; i++)
			for(int j = 0; j < grid[0].length; j++)
				grid[i][j] = randNegative();
		
	}
	
	private boolean existCase(final int x, final int y){
		return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length;
	}
	
	
	private int getNbVoisinPositive(final int x, final int y) {
		int nB = 0;
		for(int i = x - 1; i <= x + 1 ; i++)
			for(int j = y - 1; j <= y + 1; j++)
				if(existCase(i, j) && grid[i][j] > 0) nB++;
		if(grid[x][y] > 0) nB--;
		return nB;
	}
	
	private int getNbVoisinPositiveR2(final int x, final int y) {
		int nB = 0;
		for(int i = x - 2; i <= x + 2 ; i++)
			for(int j = y - 2; j <= y + 2; j++)
				if(existCase(i, j) && grid[i][j] > 0) nB++;
		if(grid[x][y] > 0) nB--;
		return nB;
	}
	
	private int getNbVoisinNeutre(final int x, final int y) {
		int nB = -1;
		for(int i = x - 1; i <= x + 1 ; i++)
			for(int j = y - 1; j <= y + 1; j++)
				if(existCase(i, j) && grid[i][j] == 0) nB++;
		return nB;
	}
	
	
	private int randNegative() {
		int rand = root.rand() % (antipressionN);
		if(rand > 0) rand = -rand;
		rand -= stabiliteN;
		return rand;
	}
	
	private int randPositive() {
		int rand = root.rand() % (resistanceP);
		if(rand < 0) rand = -rand;
		rand += stabiliteP;
		return rand;
	}
	
	
	private void update() {
		int[][] newGrid = new int[grid.length][grid[0].length];
		for(int i =0; i < grid.length; i++)
			for(int j = 0; j < grid[0].length; j++)
				if(grid[i][j] > 0) {
					if(rulePositive[getNbVoisinPositive(i, j)]) newGrid[i][j]= grid[i][j] - 1;
					else newGrid[i][j]= randNegative();
					
				}
				else if(ruleNegative[getNbVoisinPositive(i, j)]) newGrid[i][j] = randPositive();
				else newGrid[i][j]= grid[i][j];
		grid = newGrid;
		newGrid = new int[grid.length][grid[0].length];
		for(int i =0; i < grid.length; i++)
			for(int j = 0; j < grid[0].length; j++)
				if(grid[i][j] == 0) {
					if(ruleNeutre[getNbVoisinNeutre(i, j)] && getNbVoisinPositiveR2(i, j) == 0) newGrid[i][j] = randPositive();
					else newGrid[i][j] = randNegative();
				}
				else if(grid[i][j] < 0) newGrid[i][j] = grid[i][j] + 1;
				else newGrid[i][j] = grid[i][j];
		grid = newGrid;
	}
	
	private void fillRect(int x, int y, final int width, final int height, final int color) {
		if(width < 0 || height < 0) return;
		int maxX = width + x;
		int maxY = height + y;
		if(maxX > root.getGraphicsWidth()) maxX = root.getGraphicsWidth();
		if(maxY > root.getGraphicsHeight()) maxY = root.getGraphicsHeight();
		if(x < 0) x = 0;
		if(y < 0) y = 0;
		for(int i = x; i < maxX; i += 1)
			for (int j = y; j < maxY; j += 1)
				root.drawPixel(i, j, color);
	}
	
	private void initSizeDraw() {
		if(grid.length > grid[0].length) {
			sizedraw = root.getGraphicsWidth() / grid.length;
		}
		else {
			sizedraw = root.getGraphicsHeight() / grid[0].length;
		}
	}
	
	private int sizedraw;
	
	private void draw() {
		int color;
		for(int i =0; i < grid.length; i++)
			for(int j = 0; j < grid[0].length; j++) {
				if(grid[i][j] > 0) color = 0x00000000;
				//else if(grid[i][j] == 0) color = 0xffE5C0E3;
				else color = 0xffffffff;
//				if(grid[i][j] > 0) {
//					color = 0x000000ff - grid[i][j] * 20;
//					color = color << 16;
//					color = (color | 0xff00ff00) & 0xffffff00;
//				}
//				else {
//					color = 0x000000ff + grid[i][j] * 8;
//					color = color << 8;
//					color = (color | 0xffff0000) & 0xffffff00;
//				}
				fillRect(sizedraw * i, sizedraw * j, sizedraw, sizedraw, color);
			}
	}

	@Override
	public int main(Root root) {
		this.root = root;
		root.setWindowSize(1080, 1080);
		init(500, 500);
		for(int i = 0; i < stabiliteN; i++) update();
		initSizeDraw();
		Timer chrono = new Timer();
		chrono.schedule(new TimerTask() {
			@Override
			public void run() {
				update();
				draw();
				root.update();
			}
		}, 100, 100);
		
//		root.setMousePressed(new EventMousePressed() {
//			@Override
//			public void action(int x, int y, int button) {
//				update();
//				draw();
//				root.update();
//			}
//		});
		draw();
		root.setWindowVisible(true);
		root.update();
		return Root.NOTHING;
	}

}
