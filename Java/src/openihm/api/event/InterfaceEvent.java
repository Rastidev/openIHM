package openihm.api.event;

import openihm.api.utils.List;

public class InterfaceEvent {
	
	public static final int COUNT = 6;
	public static final int EVENT_MOVED = 0;
	public static final int EVENT_PRESSED = 1;
	public static final int EVENT_RELEASE = 2;
	public static final int EVENT_SCROLL = 3;
	public static final int EVENT_DRAGGED = 4;
	public static final int EVENT_CLICK = 5;
	
	private final int idEvent;
	
	private final List<Integer> type;
	
	private final boolean isType;
	
	InterfaceEvent(final int idEvent, final List<Integer> type, final boolean isType) {
		this.idEvent = idEvent;
		this.type = type;
		this.isType = isType;
	}
	
	public int getIdEvent() { return this.idEvent; }
	
	protected List<Integer> getType() { return type; }
	
	protected boolean isType() { return isType; }
	
	
}

