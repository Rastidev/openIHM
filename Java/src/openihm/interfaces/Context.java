package openihm.interfaces;

public interface Context {
	
	public static final int THIS = 0;
	public static final int ALL = 1;
	
	//nombre d'interface : 2
	public static final int WINDOW = 0;
	public static final int MOUSE = 1;
	
	static boolean[][] getExist(final Context context){
		final boolean[][] tab = new boolean[2][];
		tab[WINDOW] = Window.existList(context);
		tab[MOUSE] = Mouse.existList(context);
		return tab;
	}
	
	/*
	 * renvoie @int l'id du root
	 */
	public abstract int getId();
	
	/*
	 * renvoie @VersionLanguage la version de java
	 */
	public abstract int getVersionLanguage();
	
	/*
	 * renvoie @Device l'equipement electronique utilise
	 */
	public abstract int getDevice();
	

	public boolean exist(final int Interface, final int Methode);
	
	static boolean exist(final Context context, final int[] devices, final int devicesLength, final int[] version_languages, final int version_languagesLength) {
				for(int i = 0; i < devicesLength; ++i) {
					if(context.getDevice() != devices[i]) continue;
					for(int j = 0; j < version_languagesLength; ++j) if(context.getVersionLanguage() == version_languages[j]) return true;
					return false;
				}
				return false;
	}
	
	static void finishConfigTabExist(final boolean[] tab, final int tabLenght) {
		tab[THIS] = false;
		tab[ALL] = true;
		for(int i = 2; i < tabLenght; i += 1) {
			if(tab[i]) tab[THIS] = true;
			else{
				tab[ALL] = false;
				if(tab[THIS]) return;
			}
			
		}
	}
	

}
