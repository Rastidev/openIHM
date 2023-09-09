package openihm.interfaces;

import openihm.java.loader.RootLoader;

public abstract class Main {
	
	public static void launch(final Main Class) { launch(new byte[0][0], Class); }
	
	public static void launch(final Object[] args, final Main Class) {
		launch(RootLoader.start(args), Class); 
	}
	
	public static void launch(final byte[][] args, final Main Class) { launch(RootLoader.start(args), Class); }
	
	
	public static void launch(final Root root, final Main Class) {
		final int out = Class.main(root); 
		if(out == Root.NOTHING) return;
		byte[] msg = new byte[]{'P','r','o','g','r','a','m',' ','f','i','n','i','s','h',' ','w','i','t','h',' '};
		for(int i = 0; i < 20; i++) root.print_out(msg[i]); 
		printInteger(root, out);
		root.print_out((byte) 0x0A);
		if(out == Root.SUCCESS) {
			msg = new byte[] {'n','o',' ','p','r','o','b','l','e','m',' ','f','o','u','n','d'};
			for(int i = 0; i < 16; i++) root.print_out(msg[i]); 
		}
		else {
			msg = new byte[] {'p','r','o','b','l','e','m',' ','f','o','u','n','d'};
			for(int i = 0; i < 13; i++) root.print_out(msg[i]); 
		}
		root.print_out((byte) 0x0A);
	}
	
	private static void printInteger(final System sys, int a) {
		if(a == 0) sys.print_out((byte) '0');
		final byte[] digits = new byte[11];
		int size = 0;
		final boolean isNeg = a < 0;
		if(isNeg) a *= -1;
		while(a != 0) {
			digits[size] = (byte)((a % 10) + 0x30);
			a /= 10;
			size += 1;
		}
		if(isNeg) sys.print_out((byte) '-');
		for(int i = 0; i < size; i += 1) sys.print_out(digits[i]);
	}
	
	public abstract int main(final Root root);
}
