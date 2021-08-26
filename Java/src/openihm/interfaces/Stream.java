package openihm.interfaces;

public abstract class Stream {
	
	public abstract boolean write(final char c);
	
	public abstract char read();
	
	public abstract boolean CursorPosition(final long pos);

}
