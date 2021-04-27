package openihm.java.interfaces;

public interface Context {	
	
	/*
	 * renvoie @int l'id du root
	 */
	public abstract int getId();
	
	/*
	 * renvoie @VersionLanguage la version de java
	 */
	public abstract VersionLanguage getVersionLanguage();
	
	/*
	 * renvoie @Device l'equipement electronique utilise
	 */
	public abstract Device getDevice();

}
