package openihm.interfaces;

public interface Context {
	

	
	public static final int NUMBER_INTERFACES = 2;
	
	public static final int WINDOW = 0;
	public static final int MOUSE = 1;
	
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
	

}
