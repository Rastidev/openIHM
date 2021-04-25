package openihm.java.loader;

import openihm.main.Main;

public class Root extends Object{

	public static void main(String[] args) {
		final Main launcher = new Main(new Root());
		final int out = launcher.main(args, 0);
		System.out.println("Program finish with " + out);
		if(out == 0) System.out.println("no problem found");
		else System.out.println("probleme found");
	}
	
	private Root() {}

}
