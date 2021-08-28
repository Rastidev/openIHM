package openihm.interfaces;

public interface Graphics {
	
	
	/*
	 * dessine à la position x , y un pixel de couleur rgb
	 * $x @int la position horizontale depuis le haut gauche
	 * $y @int la position verticale depuis le haut gauche
	 * $rgb @int la couleur rgb du pixel
	 */
	public void drawPixel(final int x, final int y, final int rgb);
	
	/*
	 * met à jour le graphics sur la fenetre
	 */
	public void update();
	
	/*
	 * renvoie @int la taille horizontale du graphique
	 */
	public int getGraphicsWidth();
	
	/*
	 * renvoie @int la taille verticale du graphique
	 */
	public int getGraphicsHeight();
}
