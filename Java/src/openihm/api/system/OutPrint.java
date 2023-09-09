package openihm.api.system;

import openihm.api.lang.Int;
import openihm.api.lang.String;

public abstract class OutPrint {
	
	protected abstract void print(final byte c);
	
	public OutPrint $(final byte[] out, final int size) {
		for(int i = 0; i < size; i++) print((byte) out[i]);
		return this;
	}
	
	public OutPrint $(final int i) { return $(Int.toString(i)); }
	
	public OutPrint $(final String out) { return $(out.getValue(), out.size()); }
	
	public OutPrint $(final byte c) {
		print((byte) c);
		return this;
	}
	
	public OutPrint endl() {
		print((byte) 0x0A);
		return this;
	}
}
