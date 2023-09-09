package openihm.api.file;

import openihm.interfaces.Stream;

public class FileStreamReader extends CharStreamReader{
	
	final Stream s;
	
	long cursor = 0;
	
	public FileStreamReader(final File file) {
		final Paths paths = file.getPaths();
		s = File.fs.readFile(paths.getPathsTab(), paths.getPathsSize(), paths.getTypePaths());
	}

	@Override
	public int getState() { return s.getState(); }

	@Override
	public int available() { return s.available(); }

	public boolean close() { return s.close(); }
	
	public byte read() { return s.read(); }

	public boolean setCursor(int pos) { return s.setCursorPosition(pos) && super.setCursor(pos); }
	
	

}
