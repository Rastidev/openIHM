package openihm.api.event;

public abstract class ScrollEvent extends InterfaceEvent{

	ScrollEvent() { super(EVENT_SCROLL, null, false); }
	
	public abstract void action(final double scroll);

}
