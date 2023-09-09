package openihm.interfaces;

public interface FileSystem {
	
	public static final int INTERN_FILE = 0;
	
	public static final int WINDOWS_FILE = 1;

	public abstract Stream readFile(final byte[] paths, final int pathsSize, final int pathsType);
	
	public abstract boolean fileExist(final byte[] paths, final int pathsSize, final int pathsType);
	
	public abstract boolean canFileExecute(final byte[] paths, final int pathsSize, final int pathsType);
	
	public abstract boolean canFileRead(final byte[] paths, final int pathsSize, final int pathsType);
	
	public abstract boolean canFileWrite(final byte[] paths, final int pathsSize, final int pathsType);
	
	
}
