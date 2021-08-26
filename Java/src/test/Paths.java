package test;

public final class Paths {
	
	public Paths(final char[] intern, final int internSize, final char[] windows, final int windowsSize) {
		this.intern = intern;
		this.internSize = internSize;
		this.windows = windows;
		this.windowsSize = windowsSize;
	}
	
	private final char[] intern;
	public char[] getIntern() { return intern; }
	private final int internSize;
	public int getInternSize() { return internSize; }
	
	private final char[] windows;
	public char[] getWindows() { return windows; }
	private final int windowsSize;
	public int getWindowsSize() { return windowsSize; }
	

}
