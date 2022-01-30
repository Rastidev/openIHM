package openihm.java.loader;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

import openihm.interfaces.Device;
import openihm.interfaces.EventMouseMoved;
import openihm.interfaces.EventMousePressed;
import openihm.interfaces.EventMouseReleased;
import openihm.interfaces.EventMouseScroll;
import openihm.interfaces.FileSystem;
import openihm.interfaces.Mouse;
import openihm.interfaces.Root;
import openihm.interfaces.Stream;
import openihm.interfaces.VersionLanguage;
import openihm.interfaces.Window;

public class RootloaderComputerJava8 extends Root{
	
	RootloaderComputerJava8(final char[][] args) {
		super(args, Device.COMPUTER, VersionLanguage.JAVA_8);
		this.exist = getExist();
		window = new JFrame("openIHM");
		window.setContentPane( new JPanel() {
			private static final long serialVersionUID = 1L;
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(graphicsImage, 0, 0, null);
			}
		});
		window.setVisible(false);
		window.setAlwaysOnTop(false);
		window.setSize(800, 600);
		window.setLocation(0, 0);
		window.setResizable(true);
		window.setUndecorated(true);
		graphicsImage = new BufferedImage(getWindowWidth(), getWindowHeight(), BufferedImage.TYPE_INT_RGB);
		clearGraphics();
		window.repaint();
		window.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased( MouseEvent e) {
				mouseReleased.action(e.getX(), e.getY(), e.getButton());
			}
			@Override
			public void mousePressed( MouseEvent e) {
				mousePressed.action(e.getX(), e.getY(), e.getButton());
			}
			@Override
			public void mouseExited( MouseEvent e) {}
			@Override
			public void mouseEntered( MouseEvent e) {}
			@Override
			public void mouseClicked( MouseEvent e) {}
		});
		window.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved( MouseEvent e) {
				mouseMoved.action(e.getX(), e.getY()); 
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				mouseMoved.action(e.getX(), e.getY());
			}
		});
		window.addMouseWheelListener(new MouseWheelListener() {
			
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				mouseScroll.action((int)(e.getScrollAmount() * e.getPreciseWheelRotation()) * 5);
			}
		});
		this.mouseReleased = new EventMouseReleased() {public void action(int x, int y, int button){}};
		this.mousePressed = new EventMousePressed() {public void action(int x, int y, int button) {}};
		this.mouseMoved = new EventMouseMoved() {public void action(int x, int y) {}};
		this.mouseScroll = new EventMouseScroll() {public void action(int scroll) {}};
		random = new Random();
	}
	
	 private final static boolean[][] getExist(){
		final boolean[][] tab = new boolean[NUMBER_INTERFACES][];
		tab[WINDOW] = existMethodesWindow();
		tab[MOUSE] = existMethodesMouse();
		return tab;
	}
	
	private final boolean[][] exist;
	@Override
	public boolean exist(final int Interface, final int Methode) { return exist[Interface][Methode]; }
		
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/*
	 * renvoie @boolean[] le tableau des méthodes disponibles de @Window
	 */ 
	private static boolean[] existMethodesWindow() {
		boolean[] tab = new boolean[Window.NUMBER_METHODES];
		tab[SET_TITLE] = true;
		tab[SET_VISIBLE] = true;
		tab[SET_ALWAYS_ON_TOP] = true;
		tab[SET_SIZE] = true;
		tab[GET_WIDTH] = true;
		tab[GET_HEIGHT] = true;
		tab[SET_LOCALISATION] = true;
		tab[GET_X] = true;
		tab[GET_Y] = true;
		tab[SET_RESIZABLE] = true;
		return tab;
	}
	
	private final JFrame window;

	@Override
	public final void setWindowTitle(final char[] title, final int size) { window.setTitle(new String(title)); }

	@Override
	public final void setWindowVisible(final boolean b) {
		if(b) reloadGraphics();
		window.setVisible(b); 
	}


	@Override
	public final void setWindowAlwaysOnTop(final boolean b) { window.setAlwaysOnTop(b); }

	@Override
	public final void setWindowSize(final int witdh, final int height) { 
		window.setSize(witdh, height);
		reloadGraphics();
	}

	@Override
	public final int getWindowWidth() { return window.getWidth(); }

	@Override
	public final int getWindowHeight() { return window.getHeight(); }

	@Override
	public void setWindowLocalisation(final int x, final int y) { window.setLocation(x, y); }

	@Override
	public final int getWindowX() { return window.getX(); }

	@Override
	public final int getWindowY() { return window.getY(); }

	@Override
	public final void setWindowResizable(final boolean b) { window.setResizable(b); }


	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	/*
	 * renvoie @boolean[] le tableau des méthodes disponibles de @Mouse
	 */
	private static boolean[] existMethodesMouse() {
		boolean[] tab = new boolean[Mouse.NUMBER_METHODES];
		tab[SET_RELEASE] = true;
		tab[SET_PRESSED] = true;
		tab[SET_MOVED] = true;
		tab[SET_SCROLL] = true;
		return tab;
	}
	
	private EventMouseReleased mouseReleased;
	
	@Override
	public void setMouseReleased(final EventMouseReleased e) { this.mouseReleased = e; }
	
	private EventMousePressed mousePressed;

	@Override
	public void setMousePressed(final EventMousePressed e) { this.mousePressed = e; }
	
	private EventMouseMoved mouseMoved;

	@Override
	public void setMouseMoved(final EventMouseMoved e) { this.mouseMoved = e; }
	
	private EventMouseScroll mouseScroll;

	@Override
	public void setMouseScroll(final EventMouseScroll e) { this.mouseScroll = e; }
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private static final int DEFAULT_COLOR = 0xffffffff;
	
	private BufferedImage graphicsImage;
	
	
	private void reloadGraphics() {
		final BufferedImage oldImage = graphicsImage;
		graphicsImage = new BufferedImage(getWindowWidth(), getWindowHeight(), BufferedImage.TYPE_INT_RGB);
		clearGraphics();
		graphicsImage.getGraphics().drawImage(oldImage, 0, 0, null);
		window.repaint();
	}

	@Override
	public void drawPixel(final int x, final int y, final int rgb) { graphicsImage.setRGB(x, y, rgb); }

	@Override
	public void update() { window.repaint(); }
	
	@Override
	public int getGraphicsWidth() { return graphicsImage.getWidth(); }

	@Override
	public int getGraphicsHeight() { return graphicsImage.getHeight(); }
	
	
	private void clearGraphics() {
		for(int i = 0; i < getWindowWidth(); i += 1)
			for (int j = 0; j < getWindowHeight(); j += 1)
				drawPixel(i, j, DEFAULT_COLOR);
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static char[][] readArgs(final Object[] Vstr) {
		final char[][] Args = new char[Vstr.length][];
		for(int i = 0; i < Args.length; i++) {
			final String str = Vstr[i].toString();
			final char[] Arg = new char[str.length()];
			for(int j = 0; j < Arg.length; j++) Arg[j] = str.charAt(j);
			Args[i] = Arg;
		}
		return Args;
	}

	@Override
	public void print_out(char c) {
		System.out.print(c);
	}

	@Override
	public void print_out(int n) {
		System.out.print(n);
		
	}

	@Override
	public void print_err(int n) {
		System.err.print(n);
	}

	@Override
	public void print_err(char c) {
		System.err.print(c);
	}
	
	
	private final Random random;

	@Override
	public int rand() {
		return random.nextInt();
	}
	
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public Stream openFile(final char[] paths, final int pathsSize, final int pathsType) {
		Stream out = null;
		switch(pathsType) {
		case FileSystem.INTERN_FILE:
			out = null;
			break;
		case FileSystem.WINDOWS_FILE:
			out = getStreamWindowsFile(paths, pathsSize);
			break;
		default:
			out = new Stream() {
				@Override public boolean write(char c) {return false;}
				@Override public char read() {return 0;}
				@Override public boolean getCursorPosition(long pos) {return false;}
				@Override public int getState() { return TYPENOFOUND; }
				@Override public boolean close() {return false;}
			};
		}
		if(out == null)	return new Stream() {
			@Override public boolean write(char c) {return false;}
			@Override public char read() {return 0;}
			@Override public boolean getCursorPosition(long pos) {return false;}
			@Override public int getState() { return ERROR; }
			@Override public boolean close() {return false;}
		};
		return out;
	}
	
	private Stream getStreamWindowsFile(final char[] paths, final int pathsSize) {
		try {
			final InputStream ifs = new FileInputStream(new File(new String(paths)));
			return new Stream() {
				
				private int error = 0;
				
				@Override
				public boolean write(char c) {return false;}
				
				@Override
				public char read() {try {
					return (char) ifs.read();
				} catch (IOException e) {error = READERROR; return 0;}}
				
				@Override
				public int getState() {
					if(error != 0) return error;
					try { if(ifs.available() != 0) return GOOD; } 
					catch (IOException e) { return ERROR; }
					return ENDSTREAM;
				}
				
				@Override
				public boolean getCursorPosition(long pos) {
					try {
						ifs.reset();
						ifs.skip(pathsSize);
					} catch (IOException e) {
						error = CURSORERROR;
						return false;
					}
					return true;
				}

				@Override
				public boolean close() { 
					try {ifs.close();} 
					catch (IOException e) {error = CLOSEERROR; return false;} 
					return true; 
				}
			};
		} catch (IOException e) {}
		return null;
	}

	@Override
	public boolean fileExist(final char[] paths, final int pathsSize, final int pathsType) {
		return new File(new String(paths)).exists();
	}
}
