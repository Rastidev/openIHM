package openihm.main;

import openihm.java.loader.Root;

public final class Main {
	
	public static void main(String[] args) {Root.main(args);}
	public Main(final Root root) {this.root = root;}
	
	private final Root root;
	
	public int main() {
		System.out.println("Hello World !");
		return Root.NOTHING;
	}
}
