package openihm.api.file;

import openihm.api.lang.String;

public class Paths {
	
	public static final int INTERN_FILE = 0;
	public static final int WINDOWS_FILE = 1;
	
	public static Paths getWindows(final String paths) { return new Paths(paths, WINDOWS_FILE); }
	
	private final int typePaths;
	
	private final byte[] paths;
	
	private final int sizePaths;
	
	private Paths(final byte[] paths, final int sizePaths, final int typePaths) {
		this.typePaths = typePaths;
		this.paths = paths;
		this.sizePaths = sizePaths;
	}
	
	private Paths(final String paths, final int typePaths) { this(paths.getValue(), paths.size(), typePaths); }
	
	public String getPaths() { return new String(paths, sizePaths); }
	
	public int getTypePaths() { return typePaths; }
	
	byte[] getPathsTab() { return paths; }
	
	int getPathsSize() { return sizePaths; }
	
	

}
