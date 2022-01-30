package openihm.api.view;

import openihm.api.event.InterfaceEvent;
import openihm.api.event.MovedEvent;
import openihm.api.event.PressedEvent;
import openihm.api.event.ReleasedEvent;
import openihm.api.event.ScrollEvent;
import openihm.api.graphics.Graphics;
import openihm.api.utils.AdvencedList;
import openihm.api.utils.Array;
import openihm.api.utils.Iterator;
import openihm.api.utils.List;

public class View {
	
	private List<List<InterfaceEvent>> listEvent = null;
	
	public View(final Graphics g) {
		this.g = g;
		final Array<List<InterfaceEvent>> array = new Array<List<InterfaceEvent>>(InterfaceEvent.COUNT);
		array.set(InterfaceEvent.EVENT_MOVED, new AdvencedList<InterfaceEvent>());
		array.set(InterfaceEvent.EVENT_PRESSED, new AdvencedList<InterfaceEvent>());
		array.set(InterfaceEvent.EVENT_RELEASE, new AdvencedList<InterfaceEvent>());
		array.set(InterfaceEvent.EVENT_SCROLL, new AdvencedList<InterfaceEvent>());
		array.set(InterfaceEvent.EVENT_DRAGGED, new AdvencedList<InterfaceEvent>());
		array.set(InterfaceEvent.EVENT_CLICK, new AdvencedList<InterfaceEvent>());
		listEvent = array.getConstArray();
	}
	
	public View() { this(null); }
	
	// le visuel de la vue
	private Graphics g;
	
	public Graphics getGraphics() { return g; }
	
	public void setGraphics(final Graphics g) { this.g = g; }
	
	public void eventMoved(double x, double y) {
		for(Iterator<InterfaceEvent> it = listEvent.get(InterfaceEvent.EVENT_MOVED).iterator(); !it.isEnd(); it.next())
			((MovedEvent) it.get()).action(x, y);
	}

	public void eventPressed(double x, double y, int type) {
		for(Iterator<InterfaceEvent> it = listEvent.get(InterfaceEvent.EVENT_PRESSED).iterator(); !it.isEnd(); it.next())
			((PressedEvent) it.get()).action(x, y, type);
	}

	public void eventReleased(double x, double y, int type) {
		for(Iterator<InterfaceEvent> it = listEvent.get(InterfaceEvent.EVENT_RELEASE).iterator(); !it.isEnd(); it.next())
			((ReleasedEvent) it.get()).action(x, y, type);
	}

	public void eventScroll(double scroll) {
		for(Iterator<InterfaceEvent> it = listEvent.get(InterfaceEvent.EVENT_SCROLL).iterator(); !it.isEnd(); it.next())
			((ScrollEvent) it.get()).action(scroll);
	}

	public void eventDragged(double x, double y, int type) {
		// TODO Auto-generated method stub
		
	}

	public void eventClick(double x, double y, int type) {
		// TODO Auto-generated method stub
		
	}
	
	public void addInterfaceEvent(final InterfaceEvent e) { listEvent.get(e.getIdEvent()).add(e); }
	
	public boolean delete(final InterfaceEvent e) {
		final List<InterfaceEvent> listOfIdEvent = listEvent.get(e.getIdEvent());
		return listOfIdEvent.remove(listOfIdEvent.indexOf(e)); 
	}
	
}
