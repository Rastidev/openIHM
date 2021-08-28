package openihm.interfaces;

public interface Mouse {
	
	public static final int NUMBER_METHODES = 4;
	
	public static final int SET_RELEASE = 0;
	public static final int SET_PRESSED = 1;
	public static final int SET_MOVED = 2;
	public static final int SET_SCROLL = 3;
	
	public void setMouseReleased(final EventMouseReleased e);
	
	public void setMousePressed(final EventMousePressed e);
	
	public void setMouseMoved(final EventMouseMoved e);

	public void setMouseScroll(final EventMouseScroll e);
}
