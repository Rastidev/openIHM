package openihm.interfaces;

public abstract class Root implements Context, Arguments, Window, Mouse, Graphics, System, FileSystem{
	
	public static final int SUCCESS = 0;
	public static final int NOTHING = 1;
	
	protected Root(final byte[][] args, final int device, final int version_language) {
		this.args = args;
		this.device = device;
		this.version_language = version_language;
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////
	private final byte[][] args;
	
	@Override
	public final byte[] getArg(final int ind) {
		if(ind < args.length) return args[ind];
		return new byte[] {' '};
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
	public final byte[][] getArgs() {
		return args;
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private final int device;
	private final int version_language;
	
//	private static final List<Root> RootLoad = new ArrayList<>();
	
	@Override
	public final int getId() { return -1; }
	
	@Override
	public final int getVersionLanguage() { return version_language; }
	
	@Override
	public final int getDevice() { return device; }
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////


}
