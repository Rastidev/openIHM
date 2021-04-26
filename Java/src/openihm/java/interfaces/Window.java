package openihm.java.interfaces;

public interface Window {
	
	public static boolean exist(final Context context) {
		Device[] DeviceIs = {Device.COMPUTER};
		Device actualDevice = context.getDevice();
		for(Device d: DeviceIs) if(actualDevice == d) return true;
		return false;
	}
	
	public abstract void setTitle(final String title);
	
	public abstract String getTitle();
	
	public abstract void setVisible(final boolean b);
	
	public abstract boolean isVisible();
	
	public abstract void setAlwaysOnTop(final boolean b);
	
	public abstract boolean isAlwaysOnTop();
	
	public abstract void setSize(final int witdh, final int height);
	
	public abstract int getWidth();
	
	public abstract int getHeight();
	
	public abstract void setLocalisation(final int x, final int y);
	
	public abstract int getX();
	
	public abstract int getY();
	
	public abstract void setResizable(final boolean b);
	
	public abstract boolean isResizable();
	
	
	
}
