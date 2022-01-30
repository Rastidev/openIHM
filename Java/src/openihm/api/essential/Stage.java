package openihm.api.essential;

import openihm.interfaces.EventMouseMoved;
import openihm.interfaces.EventMousePressed;
import openihm.interfaces.EventMouseReleased;
import openihm.interfaces.EventMouseScroll;
import openihm.interfaces.Root;
import openihm.api.graphics.Drawable;
import openihm.api.utils.String;
import openihm.api.view.View;

public class Stage {
	
	private final Root root;
	
	private final Panel panel;
	
	private String title = null;
	
	private boolean isVisible = false;
	
	private boolean isAlwaysOnTop = false;
	
	private boolean isResizable = false;
	
	private Scene scene;
	
	public Stage(final Root root) {
		this.root = root;
		panel = new Panel(root, root.getGraphicsWidth(), root.getGraphicsHeight());
		root.setWindowVisible(isVisible);
		root.setWindowAlwaysOnTop(isAlwaysOnTop);
		root.setWindowResizable(isResizable);
		title = String.$("openIHM_API");
		root.setWindowTitle(title.getValue(), title.size());
		///////////////////////////
		root.setMouseMoved(new EventMouseMoved() {
			@Override
			public void action(int x, int y) {
				scene.eventMoved( (double) x / root.getGraphicsWidth(), (double) y / root.getGraphicsHeight());
			}
		});
		root.setMousePressed(new EventMousePressed() {	
			@Override
			public void action(int x, int y, int button) {
				scene.eventPressed(
						(double) x / root.getGraphicsWidth(), (double) y / root.getGraphicsHeight(), button);
			}
		});
		root.setMouseReleased(new EventMouseReleased() {
			@Override
			public void action(int x, int y, int button) {
				scene.eventReleased( (double) x / root.getGraphicsWidth(), (double) y / root.getGraphicsHeight(), button);
				
			}
		});
		root.setMouseScroll(new EventMouseScroll() {
			@Override
			public void action(int scroll) {
				scene.eventScroll((double) scroll / 360);
			}
		});
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
	Drawable getPanel() { return panel; }
	
	
	public void update() { 
		panel.drawGraphics(scene.getView().getGraphics(), 0, 0, getWidth(), getHeight()); 
		panel.update();
	}
	
	public void setScene(final Scene scene) {
		if(scene == null) {
			this.scene = new Scene() {
				public void setView(final View view) {}
				@Override void eventScroll(double scroll) {}
				@Override void eventReleased(double x, double y, int type) {}
				@Override void eventPressed(double x, double y, int type) {}
				@Override void eventMoved(double x, double y) {}
				@Override void eventDragged(double x, double y, int type) {}
				@Override void eventClick(double x, double y, int type) {}
			};
		}
		else {
			this.scene = scene;
			update();
		}
	}

}
