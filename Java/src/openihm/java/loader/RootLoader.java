package openihm.java.loader;

import openihm.interfaces.Root;

public class RootLoader extends RootloaderComputerJava8 {
	
	RootLoader(char[][] args) {super(args);}

	public static Root start(final char[][] args) { return new RootLoader(args); }
	
	public static Root start(final Object[] args) { return new RootLoader(readArgs(args)); }

}
