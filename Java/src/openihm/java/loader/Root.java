package openihm.java.loader;

import java.util.ArrayList;
import java.util.List;

import openihm.java.interfaces.Arguments;
import openihm.java.interfaces.Context;
import openihm.java.interfaces.Device;
import openihm.java.interfaces.VersionLanguage;
import openihm.main.Main;

public class Root extends Object implements Context, Arguments{
	
	public static final int SUCCESS = 0;

	public static void main(String[] args) {
		final Main launcher = new Main(new Root(args));
		final int out = launcher.main();
		System.out.println("Program finish with " + out);
		if(out == SUCCESS) System.out.println("no problem found");
		else System.out.println("probleme found");
	}
	
	private Root(final String[] args) {
		RootLoad.add(this);
		this.args = args;
	}
	
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////
	private final String[] args;
	
	@Override
	public final String getArg(final int ind) {
		if(ind < args.length) return args[ind];
		return null;
	}
	
	@Override
	public final boolean isArg(final int ind) {
		if(ind < args.length) return true;
		return false;
	}
	
	@Override
	public final int getNumberArgs() {
		return args.length;
	}
	
	@Override
	public final String[] getArgs() {
		return args;
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private static final Device device = Device.COMPUTER;
	private static final VersionLanguage version_language = VersionLanguage.JAVA_8;
	
	private static final List<Root> RootLoad = new ArrayList<>();
	
	@Override
	public final int getId() { return RootLoad.indexOf(this); }

	@Override
	public final VersionLanguage getVersionLanguage() { return version_language; }

	@Override
	public final Device getDevice() { return device; }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
