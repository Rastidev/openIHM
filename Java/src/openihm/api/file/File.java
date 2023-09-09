package openihm.api.file;

import openihm.api.lang.String;
import openihm.api.system.System;
import openihm.interfaces.FileSystem;

public class File {
	
	static FileSystem fs = null;
	
	public static void init(final FileSystem FS) {
		if(fs == null) fs = FS; 
	}
	
	final Paths paths;
	
	public File(final Paths paths){
		this.paths = paths;
	}
	
	
	public Paths getPaths() { return paths; }
	
	
	public String getContain() {
		final FileStreamReader fsr = new FileStreamReader(this);
		final int size = fsr.available();
		final byte[] data = new byte[size];
		for(int i = 0; i < size; i++) {
			if(fsr.getState() != StreamReader.GOOD) {
				System.cerr.$(new String("error getContain with code ")).$(fsr.getState()).endl();
				return null;
			}
			data[i] = fsr.read();
		}
		return new String(data, size);
	}
	
	public boolean exist() { return fs.fileExist(paths.getPathsTab(), paths.getPathsSize(), paths.getTypePaths()); }
	
}
