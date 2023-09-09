package openihm.java.loader;

import openihm.interfaces.Root;

public class RootLoader extends RootloaderComputerJava8 {
	
	RootLoader(byte[][] args) {super(args);}

	public static Root start(final byte[][] args) { return new RootLoader(args); }
	
	public static Root start(final Object[] args) { return new RootLoader(readArgs(args)); }

}
