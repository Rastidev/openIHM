package openihm.java.interfaces;

public interface Arguments {
	public String getArg(final int ind);
	
	public boolean isArg(final int ind);
	
	public int getNumberArgs();
	
	public String[] getArgs();
}
