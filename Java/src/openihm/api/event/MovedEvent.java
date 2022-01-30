package openihm.api.event;

public abstract class MovedEvent extends InterfaceEvent{
	
	public MovedEvent() { super(EVENT_MOVED, null, false); }

	public abstract void action(final double x, final double y);
}
