package openihm.interfaces;

public interface Window {
	
	
	//nombre de methode: 14;
	public static final int SET_TITLE = 2;
	public static final int SET_VISIBLE = 3;
	public static final int SET_ALWAYS_ON_TOP = 4;
	public static final int SET_SIZE = 5;
	public static final int GET_WIDTH = 6;
	public static final int GET_HEIGHT = 7;
	public static final int SET_LOCALISATION = 8;
	public static final int GET_X = 9;
	public static final int GET_Y = 10;
	public static final int SET_RESIZABLE = 11;
	
	
	
	
	/*
	 * renvoie @boolean[] le tableau des méthodes disponibles de @Window
	 * $context @Context
	 */ 
	static boolean[] existList(final Context context) {
		final int existTabLenght = 12;
		boolean[] tab = new boolean[existTabLenght];
		final int[] config_one_device = {Device.COMPUTER};
		final int config_one_deviceLength = 1;
		final int[] config_one_version_language = {VersionLanguage.JAVA_8};
		final int config_one_version_languageLength = 1;
		final boolean config_one = Context.exist(context, config_one_device, config_one_deviceLength, config_one_version_language, config_one_version_languageLength);
		tab[SET_TITLE] = config_one;
		tab[SET_VISIBLE] = config_one;
		tab[SET_ALWAYS_ON_TOP] = config_one;
		tab[SET_SIZE] = config_one;
		tab[GET_WIDTH] = config_one;
		tab[GET_HEIGHT] = config_one;
		tab[SET_LOCALISATION] = config_one;
		tab[GET_X] = config_one;
		tab[GET_Y] = config_one;
		tab[SET_RESIZABLE] = config_one;
		Context.finishConfigTabExist(tab, existTabLenght);
		return tab;
	}
	
	/*
	 * met un nom à la fenetre
	 * $title @char[] le nom de la fenetre
	 */
	public abstract void setWindowTitle(final char[] title, final int size);
	
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
	 * renvoie @int la taille horizontale de la fenetre
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
