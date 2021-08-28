package openihm.api.essential;

import openihm.interfaces.Context;
import openihm.interfaces.Root;
import openihm.interfaces.Window;
import openihm.api.graphics.Drawable;
import openihm.api.utils.String;

public class Stage {
	
	private final Root root;
	
	private final Panel panel;
	
	private String title = null;
	
	private boolean isVisible = false;
	
	private boolean isAlwaysOnTop = false;
	
	private boolean isResizable = false;
	
	public Stage(final Root root) {
		this.root = root;
		if(root.exist(Context.WINDOW , 0)) {
			panel = new Panel(root, root.getWindowWidth(), root.getWindowHeight());
			if(root.exist(Context.WINDOW, Window.SET_VISIBLE)) root.setWindowVisible(isVisible);
			if(root.exist(Context.WINDOW, Window.SET_ALWAYS_ON_TOP)) root.setWindowAlwaysOnTop(isAlwaysOnTop);
			if(root.exist(Context.WINDOW, Window.SET_RESIZABLE)) root.setWindowResizable(isResizable);
			if(root.exist(Context.WINDOW, Window.SET_TITLE)) {
				title = String.$("openIHM_API");
				root.setWindowTitle(title.getValue(), title.size());
			}
		}
		else {
			panel = null;
		}
	}
	
	/*
	 * renvoie @Root du Stage
	 */
	public Root getRoot() { return root; }
	
	/*
	 * met un nom à la fenetre
	 * $title @String le nom de la fenetre
	 */
	public void setTitle(final String title) { 
		if(title == null) root.setWindowTitle(new char[0], 0);
		else root.setWindowTitle(title.getValue(), title.size());
		this.title = title;
	}
	
	/*
	 * renvoie @String le nom de la fenetre
	 */
	public String getTitle() { return title; }
	
	/*
	 * met la fenetre visible ou invisible
	 * $b @boolean:true si la fenetre doit etre visible.
	 */
	public void setVisible(final boolean b) { 
		root.setWindowVisible(b); 
		isVisible = b; 
	}
	
	/*
	 * renvoie @boolean:true si la fenetre est visible sinon @boolean:false
	 */
	public boolean isVisible() { return isVisible; }
	
	/*
	 * met la fenetre au premier plan
	 * $b @boolean:true si la fenetre doit etre au premier plan
	 */
	public void setAlwaysOnTop(final boolean b) { 
		root.setWindowAlwaysOnTop(b); 
		isAlwaysOnTop = b;
	}
	
	/*
	 * renvoie @boolean:true si la fenetre est au premier plan sinon @boolean:false
	 */
	public boolean isAlwaysOnTop() { return isAlwaysOnTop; }
	
	/*
	 * met la taille de la fenetre
	 * $witdh @int la taille horizontale
	 * $height @int la taille verticale
	 */
	public void setSize(int width, int height) {
		if(width < 0) width = 0;
		if(height < 0) height = 0;
		root.setWindowSize(width, height);
		panel.setSize(width, height);
	}
	
	/*
	 * renvoie @int la taille horizontale de la fenetre
	 */
	public int getWidth() { return root.getWindowWidth(); }
	
	/*
	 * renvoie @int la taille horizontale de la fenetre
	 */
	public int getHeight() { return root.getWindowHeight(); }
	
	/*
	 * met la localisation de la fenetre
	 * $x @int la position horizontale
	 * $y @int la position verticale
	 */
	public void setLocalisation(final int x, final int y) { root.setWindowLocalisation(x, y); }
	
	/*
	 * renvoie @int la position horizontale de la fenetre
	 */
	public int getX() { return root.getWindowX(); }
	
	/*
	 * renvoie @int la position verticale de la fenetre
	 */
	public int getY() { return root.getWindowY(); }
	
	/*
	 * met la fenetre redimentionnable ou invisible
	 * $b @boolean:true si la fenetre peut etre redimentionnable
	 */
	public void setResizable(final boolean b) { 
		root.setWindowResizable(b);
		isResizable = b;
	}
	
	/*
	 * renvoie @boolean:true si la fenetre est redimentionnable sinon @boolean:false
	 */
	public boolean isResizable() { return isResizable; }
	
	/*
	 * renvoie @Drawable le graphics du Stage
	 */
	public Panel getPanel() { return panel; }

}
