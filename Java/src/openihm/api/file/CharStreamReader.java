package openihm.api.file;

import openihm.api.lang.String;
import openihm.api.utils.BasicList;
import openihm.api.utils.List;

public abstract class CharStreamReader extends StreamReader{
	
	private int cursor = 0;

	public abstract byte read();
	
	public String read(final int size) {
		byte[] tab = new byte[size];
		for(int i = 0; i < size && getState() == GOOD; i++) tab[i] = read();
		return new String(tab, size);
	}
	
	public String getLine() {
		List<Byte> list = new BasicList<>();
		if(getState() != GOOD) return new String();
		byte r = read();
		while(getState() == GOOD && r != '\n') {
			list.add(r);
			r = read();
		}
		return new String(list);
	}
	
	public boolean setCursor(final int pos) {
		cursor = pos;
		return true;
	}
	
	public int getCursor() { return cursor; }
	
	public abstract int available();

}
