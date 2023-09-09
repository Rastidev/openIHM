package openihm.interfaces;

public interface Window {
	
	
	public static final int NUMBER_METHODES = 10;
	
	public static final int SET_TITLE = 0;
	public static final int SET_VISIBLE = 1;
	public static final int SET_ALWAYS_ON_TOP = 2;
	public static final int SET_SIZE = 3;
	public static final int GET_WIDTH = 4;
	public static final int GET_HEIGHT = 5;
	public static final int SET_LOCALISATION = 6;
	public static final int GET_X = 7;
	public static final int GET_Y = 8;
	public static final int SET_RESIZABLE = 9;
	
	/*
	 * met un nom à la fenetre
	 * $title @char[] le nom de la fenetre
	 */
	public abstract void setWindowTitle(final byte[] title, final int size);
	
	/*
	 * met la fenetre visible ou invisible
	 * $b @boolean:true si la fenetre doit etre visible.
	 */
	public abstract void setWindowVisible(final boolean b);
	
	/*
	 * met la fenetre au premier plan
	 * $b @boolean:true si la fenetre doit etre au premier plan
	 */
	public abstract void setWindowAlwaysOnTop(final boolean b);
	
	/*
	 * met la taille de la fenetre
	 * $witdh @int la taille horizontale
	 * $height @int la taille verticale
	 */
	public abstract void setWindowSize(final int width, final int height);
	
	/*
	 * renvoie @int la taille horizontale de la fenetre
	 */
	public abstract int getWindowWidth();
	
	/*
	 * renvoie @int la taille verticale de la fenetre
	 */
	public abstract int getWindowHeight();
	
	/*
	 * met la localisation de la fenetre
	 * $x @int la position horizontale
	 * $y @int la position verticale
	 */
	public abstract void setWindowLocalisation(final int x, final int y);
	
	/*
	 * renvoie @int la position horizontale de la fenetre
	 */
	public abstract int getWindowX();
	
	/*
	 * renvoie @int la position verticale de la fenetre
	 */
	public abstract int getWindowY();
	
	/*
	 * met la fenetre redimentionnable ou invisible
	 * $b @boolean:true si la fenetre doit etre redimentionnable
	 */
	public abstract void setWindowResizable(final boolean b);
	
	
	
}
