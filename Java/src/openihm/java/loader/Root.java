package openihm.java.loader;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JRootPane;

import openihm.java.interfaces.Arguments;
import openihm.java.interfaces.Context;
import openihm.java.interfaces.Device;
import openihm.java.interfaces.VersionLanguage;
import openihm.java.interfaces.Window;
import openihm.main.Main;

public class Root extends Object implements Context, Arguments, Window{
	
	public static final int SUCCESS = 0;
	public static final int NOTHING = 1;

	public static void main(String[] args) {
		final Main launcher = new Main(new Root(args));
		final int out = launcher.main();
		if(out == NOTHING) return;
		System.out.println("Program finish with " + out);
		if(out == SUCCESS) System.out.println("no problem found");
		else System.out.println("probleme found");
	}
	
	private Root(final String[] args) {
		RootLoad.add(this);
		RootFrame frame = new RootFrame(this); 
		this.window = initJFrame(frame);
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
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private static JFrame initJFrame(final JFrame window) {
		window.setTitle("openIHM");
		window.setVisible(false);
		window.setAlwaysOnTop(false);
		window.setSize(800, 600);
		window.setLocation(0, 0);
		window.setResizable(true);
		return window;
	}
	
	private final JFrame window;

	@Override
	public final void setTitle(final String title) { window.setTitle(title); }

	@Override
	public final String getTitle() { return window.getTitle(); }

	@Override
	public final void setVisible(final boolean b) { window.setVisible(b); }

	@Override
	public final boolean isVisible() { return window.isVisible(); }

	@Override
	public final void setAlwaysOnTop(final boolean b) { window.setAlwaysOnTop(b); }

	@Override
	public final boolean isAlwaysOnTop() { return window.isAlwaysOnTop(); }

	@Override
	public final void setSize(final int witdh, final int height) { window.setSize(witdh, height); }

	@Override
	public final int getWidth() { return window.getWidth(); }

	@Override
	public final int getHeight() { return window.getHeight(); }

	@Override
	public void setLocalisation(final int x, final int y) { window.setLocation(x, y); }

	@Override
	public final int getX() { return window.getX(); }

	@Override
	public final int getY() { return window.getY(); }

	@Override
	public final void setResizable(final boolean b) { window.setResizable(b); }

	@Override
	public final boolean isResizable() {return window.isResizable(); }
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
