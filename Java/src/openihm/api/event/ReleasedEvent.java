package openihm.api.event;

import openihm.api.utils.List;

public abstract class ReleasedEvent extends InterfaceEvent{

	public ReleasedEvent(final List<Integer> type) { 
		super(EVENT_RELEASE, type.getArray().getConstArray(), true); 
	}
	
	public ReleasedEvent() {
		super(EVENT_RELEASE, null, false);
	}
	public abstract void action(final double x, final double y, final int type);

}
