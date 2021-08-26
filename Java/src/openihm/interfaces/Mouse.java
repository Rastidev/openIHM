package openihm.interfaces;

public interface Mouse {
	
	//nombre de methode: 12;
	
	public static final int SET_RELEASE = 2;
	public static final int SET_PRESSED = 3;
	public static final int SET_MOVED = 4;
	public static final int SET_SCROLL = 5;
	
	
	
	
	/*
	 * renvoie @boolean[] le tableau des méthodes disponibles de @Mouse
	 * $context @Context
	 */
	static boolean[] existList(final Context context) {
		final int existTabLenght = 6;
		boolean[] tab = new boolean[existTabLenght];
		final int[] config_one_device = {Device.COMPUTER};
		final int config_one_deviceLength = 1;
		final int[] config_one_version_language = {VersionLanguage.JAVA_8};
		final int config_one_version_languageLength = 1;
		final boolean config_one = Context.exist(context, config_one_device, config_one_deviceLength, config_one_version_language, config_one_version_languageLength);
		tab[SET_RELEASE] = config_one;
		tab[SET_PRESSED] = config_one;
		tab[SET_MOVED] = config_one;
		tab[SET_SCROLL] = config_one;
		Context.finishConfigTabExist(tab, existTabLenght);
		return tab;
	}
	
	public void setMouseReleased(final EventMouseReleased e);
	
	public void setMousePressed(final EventMousePressed e);
	
	public void setMouseMoved(final EventMouseMoved e);

	public void setMouseScroll(final EventMouseScroll e);
}
