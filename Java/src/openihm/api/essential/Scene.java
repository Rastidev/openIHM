package openihm.api.essential;

import openihm.api.graphics.Graphics;
import openihm.api.view.View;

public class Scene{
	
	private View view = null;
	
	public Scene() { this(null); }
	
	public Scene(final View view) { 
		this.setView(view);	
	}
	
	public void setView(View view) {
		if(view == null) view = new View(new Graphics() {@Override public void draw() {}}) {
		};
		this.view = view; 
	}
	
	public View getView() { return view; }
	
	void eventScroll(double scroll) { view.eventScroll(scroll); }
	
	void eventReleased(double x, double y, int type) { view.eventReleased(x, y, type); }
	
	void eventPressed(double x, double y, int type) { view.eventPressed(x, y, type); }
	
	void eventMoved(double x, double y) { view.eventMoved(x, y); }
	
	void eventDragged(double x, double y, int type) { view.eventDragged(x, y, type); }
	
	void eventClick(double x, double y, int type) { view.eventClick(x, y, type); }
	
	void paint(final Stage stage) { stage.getPanel().drawGraphics(view.getGraphics(), 0, 0, stage.getWidth(), stage.getHeight());}
	
}
