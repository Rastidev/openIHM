package test;

import openihm.interfaces.EventMousePressed;
import openihm.interfaces.Main;
import openihm.interfaces.Root;

public final class GameTest extends Main{
	
	public static void main(java.lang.String[] args) { launch(new GameTest()); }
	
	private static final int color_0 = 0x0000ff80;
	private static final int color_1 = 0x0000ff00;
	private static final int color_2 = 0x00ffff00;
	private static final int color_3 = 0x00ff8000;
	private static final int color_4 = 0x00ff0000;
	private static final int color_5 = 0x00ff0080;
	private static final int color_6 = 0x00c04000;
	private static final int color_7 = 0x00e02000;
	private static final int color_8 = 0x00ff0000;
	private static final int color_mine = 0x00000000;
	private static final int color_not_see = 0x00888888;
	private static final int color_background = 0x0e0e0e0;
	private static final int color_background_lose = 0x00400000;
	private static final int color_background_win = 0x00008000;
	private static final int color_flag = 0x000000ff;
	
	private static final int sizeGrid = 20;
	private static final int NumberMine = 10;
	
	private static final char NotSee = 'n';
	private static final char Mine = 'm';
	private static final char See = 's';
	private static final char FlagMine = 'f';
	private static final char FlagNotSee = 'b';
	
	
	private Root root;
	
	
	public GameTest() {
		
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
	
	private int minSizeGraphics;
	private void drawCase(final int x, final int y, final int color) {
		final int sizeCase = (minSizeGraphics * 8) / (10 * sizeGrid);
		fillRect((minSizeGraphics / sizeGrid) * x + (minSizeGraphics/ (10 * sizeGrid)), 
				(minSizeGraphics / sizeGrid) * y + (minSizeGraphics/ (10 * sizeGrid)), sizeCase, sizeCase, color);
	}
	
	
	private char[][] map = null;
	
	
	private void initmap(final int x, final int y) {
		final char Void = 'v';
		map = new char[sizeGrid][sizeGrid];
		for(int i = 0; i < sizeGrid; ++i)
			for(int j = 0; j < sizeGrid; ++j) map[i][j] = NotSee;
		int i = x - 1;
		if(i < 0) i = 0;
		int fi = x + 1;
		if(fi >= sizeGrid) fi = sizeGrid - 1;
		int j = y - 1;
		if(j < 0) j = 0;
		int fj = y + 1;
		if(fj >= sizeGrid) fj = sizeGrid - 1;
		for(int k = i; k <= fi; k++)
			for(int l = j; l <= fj; l++) map[k][l] = Void;
		int Nbnotplaced = NumberMine;
		while(Nbnotplaced != 0) {
			int rx = root.rand() % sizeGrid;
			int ry = root.rand() % sizeGrid;
			if(rx < 0) rx = -rx;
			if(ry < 0) ry = -ry;
			if(map[rx][ry] == Void || map[rx][ry] == Mine) continue;
			map[rx][ry] = Mine;
			--Nbnotplaced;
		}
		for(int k = i; k <= fi; k++)
			for(int l = j; l <= fj; l++) map[k][l] = NotSee;
		seeCase(x, y);
	}
	
	private int nbCaseNotSee = 0;
	
	private void EventCase(final int x, final int y, final int button) {
		if(map == null) {
			if(button == 3) return;
			nbCaseNotSee = sizeGrid * sizeGrid - NumberMine;
			initmap(x, y);
			return;
		}
		if(button == 3) {
			switch(map[x][y]) {
			case NotSee:
				map[x][y] = FlagNotSee;
				drawCase(x, y, color_flag);
				return;
			case FlagMine:
				map[x][y] = Mine;
				drawCase(x, y, color_not_see);
				return;
			case FlagNotSee:
				map[x][y] = NotSee;
				drawCase(x, y, color_not_see);
				return;
			case Mine:
				map[x][y] = FlagMine;
				drawCase(x, y, color_flag);
			default:
				return;
			}
		}
		switch (map[x][y]) {
		case See:
			return;
		case NotSee:
			seeCase(x, y);
			if(nbCaseNotSee != 0) return;
			fillRect(0, 0, root.getGraphicsWidth(), root.getGraphicsHeight(), color_background_win);
			for(int i = 0; i < sizeGrid; ++i)
				for(int j = 0; j < sizeGrid; ++j)
					if(map[i][j] == Mine || map[i][j] == FlagMine) drawCase(i, j, color_mine);
					else drawCase(i, j, getColor(i, j));
			root.setMousePressed(new EventMousePressed() {public void action(int x, int y, int button) {}});
			return;
		case Mine:
			fillRect(0, 0, root.getGraphicsWidth(), root.getGraphicsHeight(), color_background_lose);
			for(int i = 0; i < sizeGrid; ++i)
				for(int j = 0; j < sizeGrid; ++j)
					if(map[i][j] == Mine || map[i][j] == FlagMine) drawCase(i, j, color_mine);
					else drawCase(i, j, getColor(i, j));
			root.setMousePressed(new EventMousePressed() {public void action(int x, int y, int button) {}});
		default:
			return;
		}
		
	}
	
	private void seeCase(final int x, final int y) {
		int color = getColor(x, y);
		drawCase(x, y, color);
		map[x][y] = See;
		--nbCaseNotSee;
		if(color != color_0) return;
		int i = x - 1;
		if(i < 0) i = 0;
		int fi = x + 1;
		if(fi >= sizeGrid) fi = sizeGrid - 1;
		int j = y - 1;
		if(j < 0) j = 0;
		int fj = y + 1;
		if(fj >= sizeGrid) fj = sizeGrid - 1;
		for(int k = i; k <= fi; k++)
			for(int l = j; l <= fj; l++)
				if(map[k][l] != See && map[k][l] != FlagMine && map[k][l] != FlagNotSee) seeCase(k, l);
	}
	
	private int getColor(final int x, final int y) {
		int i = x - 1;
		if(i < 0) i = 0;
		int fi = x + 1;
		if(fi >= sizeGrid) fi = sizeGrid - 1;
		int j = y - 1;
		if(j < 0) j = 0;
		int fj = y + 1;
		if(fj >= sizeGrid) fj = sizeGrid - 1;
		int nbmine = 0;
		for(int k = i; k <= fi; k++)
			for(int l = j; l <= fj; l++) if(map[k][l] == Mine || map[k][l] == FlagMine) ++nbmine;
		switch(nbmine) {
		case 0:
			return color_0;
		case 1:
			return color_1;
		case 2:
			return color_2;
		case 3:
			return color_3;
		case 4:
			return color_4;
		case 5:
			return color_5;
		case 6:
			return color_6;
		case 7:
			return color_7;
		case 8:
			return color_8;
		default:
			return 0x00ff00ff;
		}
	}
	
	private boolean existInterface(final int id, final int nbmethodes) {
		for(int i = 0; i < nbmethodes; i++) if(root.exist(id, i)) return true;
		return false;
	}
	

	@Override
	public int main(Root root) {
		this.root = root;
		root.setWindowVisible(true);
		if(root.getGraphicsWidth() > root.getGraphicsHeight()) minSizeGraphics = root.getGraphicsHeight();
		else minSizeGraphics = root.getGraphicsWidth();
		fillRect(0, 0, root.getGraphicsWidth(), root.getGraphicsHeight(), color_background);
		for(int i = 0; i < sizeGrid; ++i)
			for(int j = 0; j < sizeGrid; ++j) drawCase(i, j, color_not_see);
		root.setMousePressed(new EventMousePressed() {
			
			@Override
			public void action(int x, int y, int button) {
				EventCase(x / (minSizeGraphics / sizeGrid), y / (minSizeGraphics / sizeGrid), button);
				root.update();
			}
		});
		root.update();
		return Root.NOTHING;
		
		
		
	}

}
