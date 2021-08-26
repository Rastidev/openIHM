package openihm.interfaces;

public interface FileSystem {
	
	public static final int INTERN_FILE = 0;
	
	public static final int WINDOWS_FILE = 1;

	public abstract Stream openFile(final char[] paths, final int pathsSize, final int pathsType);
	
	public abstract char[] getFile(final char[] paths, final int pathsSize, final int pathsType);
	
	public abstract boolean fileExist(final char[] paths, final int pathsSize, final int pathsType);
}
