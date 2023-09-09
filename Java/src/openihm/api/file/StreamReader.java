package openihm.api.file;

public abstract class StreamReader {
	
	public static final int GOOD = 1;
	public static final int ENDSTREAM = 0;
	public static final int ERROR = -1;
	public static final int TYPENOFOUND = -2;
	public static final int READERROR = -3;
	public static final int CURSORERROR = -4;
	public static final int CLOSEERROR = -5;
	
	private long cursor = 0;
	
	public abstract int getState();

}
