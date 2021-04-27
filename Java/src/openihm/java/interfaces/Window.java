package openihm.java.interfaces;

public interface Window {
	
	/*
	 * renvoie @boolean:true si le root peut avoir une fenetre type @Window
	 * $context @Context de la class @Root
	 */
	public static boolean exist(final Context context) {
		Device[] DeviceIs = {Device.COMPUTER};
		Device actualDevice = context.getDevice();
		for(Device d: DeviceIs) {
			if(actualDevice != d) continue;
			VersionLanguage[] versionIsLanguage = { VersionLanguage.JAVA_8 };
			VersionLanguage actualVersionLanguage = context.getVersionLanguage();
			for(VersionLanguage v: versionIsLanguage) if(actualVersionLanguage == v) return true;
			return false;
		}
		return false;
	}
	
	/*
	 * met un nom à la fenetre
	 * $title @String le nom de la fenetre
	 */
	public abstract void setTitle(final String title);
	
	/*
	 * renvoie @String le nom de la fenetre
	 */
	public abstract String getTitle();
	
	/*
	 * met la fenetre visible ou invisible
	 * $b @boolean:true si la fenetre doit etre visible.
	 */
	public abstract void setVisible(final boolean b);
	
	/*
	 * renvoie @boolean:true si la fenetre est visible sinon @boolean:false
	 */
	public abstract boolean isVisible();
	
	/*
	 * met la fenetre au premier plan
	 * $b @boolean:true si la fenetre doit etre au premier plan
	 */
	public abstract void setAlwaysOnTop(final boolean b);
	
	/*
	 * renvoie @boolean:true si la fenetre est au premier plan sinon @boolean:false
	 */
	public abstract boolean isAlwaysOnTop();
	
	/*
	 * met la taille de la fenetre
	 * $witdh @int la taille horizontale
	 * $height @int la taille verticale
	 */
	public abstract void setSize(final int witdh, final int height);
	
	/*
	 * renvoie @int la taille horizontale de la fenetre
	 */
	public abstract int getWidth();
	
	/*
	 * renvoie @int la taille horizontale de la fenetre
	 */
	public abstract int getHeight();
	
	/*
	 * met la localisation de la fenetre
	 * $x @int la position horizontale
	 * $y @int la position verticale
	 */
	public abstract void setLocalisation(final int x, final int y);
	
	/*
	 * renvoie @int la position horizontale de la fenetre
	 */
	public abstract int getX();
	
	/*
	 * renvoie @int la position verticale de la fenetre
	 */
	public abstract int getY();
	
	/*
	 * met la fenetre redimentionnable ou invisible
	 * $b @boolean:true si la fenetre doit etre redimentionnable
	 */
	public abstract void setResizable(final boolean b);
	
	/*
	 * renvoie @boolean:true si la fenetre est redimentionnable sinon @boolean:false
	 */
	public abstract boolean isResizable();
	
	
	
}
