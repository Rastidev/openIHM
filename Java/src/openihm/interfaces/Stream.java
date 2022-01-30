package openihm.interfaces;

public abstract class Stream {
	
	public static final int GOOD = 1;
	public static final int ENDSTREAM = 0;
	public static final int ERROR = -1;
	public static final int TYPENOFOUND = -2;
	public static final int READERROR = -3;
	public static final int CURSORERROR = -4;
	public static final int CLOSEERROR = -5;
	
	
	
	public abstract boolean write(final char c);
	
	public abstract char read();
	
	public abstract boolean getCursorPosition(final long pos);
	
	public abstract int getState();
	
	public abstract boolean close();

}
