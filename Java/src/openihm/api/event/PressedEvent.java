package openihm.api.event;

import openihm.api.utils.List;

public abstract class PressedEvent extends InterfaceEvent{
	
	public PressedEvent(final List<Integer> type) { 
		super(EVENT_PRESSED, type.getArray().getConstArray(), true); 
	}
	
	public PressedEvent() { 
		super(EVENT_PRESSED, null, false); 
	}

	public abstract void action(final double x, final double y, final int type);

}
