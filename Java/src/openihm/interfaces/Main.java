package openihm.interfaces;

import openihm.java.loader.RootLoader;

public abstract class Main {
	
	public static void launch(final Main Class) { launch(new char[0][0], Class); }
	
	public static void launch(final Object[] args, final Main Class) {
		launch(RootLoader.start(args), Class); 
	}
	
	public static void launch(final char[][] args, final Main Class) { launch(RootLoader.start(args), Class); }
	
	
	public static void launch(final Root root, final Main Class) {
		final int out = Class.main(root); 
		if(out == Root.NOTHING) return;
		char[] msg = new char[]{'P','r','o','g','r','a','m',' ','f','i','n','i','s','h',' ','w','i','t','h',' '};
		for(int i = 0; i < 20; i++) root.print_out(msg[i]); 
		root.print_out(out);
		root.print_out('\n');
		if(out == Root.SUCCESS) {
			msg = new char[] {'n','o',' ','p','r','o','b','l','e','m',' ','f','o','u','n','d'};
			for(int i = 0; i < 16; i++) root.print_out(msg[i]); 
		}
		else {
			msg = new char[] {'p','r','o','b','l','e','m',' ','f','o','u','n','d'};
			for(int i = 0; i < 13; i++) root.print_out(msg[i]); 
		}
		root.print_out('\n');
	}
	
	public abstract int main(final Root root);
}
