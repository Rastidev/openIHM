package openihm.api.system;

public final class System {
	
	public static void init(final openihm.interfaces.System sys) {
		if(get == null) get = sys;
	}
	
	static openihm.interfaces.System get = null;
	
	public static Ostream cerr = new Cerr();
	
	public static Ostream cout = new Cout();

}
