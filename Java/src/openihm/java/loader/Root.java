package openihm.java.loader;

import openihm.main.Main;

public class Root extends Object{
	
	public static final int SUCCESS = 0;

	public static void main(String[] args) {
		final Main launcher = new Main(new Root(args));
		final int out = launcher.main();
		System.out.println("Program finish with " + out);
		if(out == SUCCESS) System.out.println("no problem found");
		else System.out.println("probleme found");
	}
	
	private final String[] args;
	
	public final String getArg(final int ind) {
		if(ind < args.length) return args[ind];
		return null;
	}
	
	public final boolean isArg(final int ind) {
		if(ind < args.length) return true;
		return false;
	}
	
	public final int getNumberArgs() {
		return args.length;
	}
	
	public final String[] getArgs() {
		return args;
	}
	
	private Root(final String[] args) {
		this.args = args;
	}

}
