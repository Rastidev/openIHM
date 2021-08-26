package openihm.interfaces;

import java.util.ArrayList;
import java.util.List;

public abstract class Root implements Context, Arguments, Window, Mouse, Graphics, System, FileSystem{
	
	public static final int SUCCESS = 0;
	public static final int NOTHING = 1;
	
	protected Root(final char[][] args, final int device, final int version_language) {
		this.args = args;
		this.device = device;
		this.version_language = version_language;
		this.exist = Context.getExist(this);
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////
	private final char[][] args;
	
	@Override
	public final char[] getArg(final int ind) {
		if(ind < args.length) return args[ind];
		return new char[] {' '};
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
	public final char[][] getArgs() {
		return args;
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private final int device;
	private final int version_language;
	
	private final boolean[][] exist;
	
	private static final List<Root> RootLoad = new ArrayList<>();
	
	@Override
	public final int getId() { return RootLoad.indexOf(this); }
	
	@Override
	public final int getVersionLanguage() { return version_language; }
	
	@Override
	public final int getDevice() { return device; }
	
	@Override
	public boolean exist(final int Interface, final int Methode) { return exist[Interface][Methode]; }
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////


}