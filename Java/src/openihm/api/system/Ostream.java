package openihm.api.system;

import openihm.api.utils.String;

public abstract class Ostream {
	
	protected abstract void print(final char c);
	
	protected abstract void print(final int i);
	
	public Ostream $(final char[] out, final int size) {
		for(int i = 0; i < size; i++) print(out[i]);
		return this;
	}
	
	public Ostream $(final int i) {
		print(i);
		return this;
	}
	
	public Ostream $(final String out) { return $(out.getValue(), out.size()); }
	
	public Ostream $(final char c) {
		print(c);
		return this;
	}
	
	public Ostream endl() {
		print('\n');
		return this;
	}
}
