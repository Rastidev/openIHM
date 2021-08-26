package openihm.interfaces;

public interface System {
	
	public abstract void print_out(final char c);
	
	public abstract void print_out(final int n);
	
	public abstract void print_err(final char c);
	
	public abstract void print_err(final int n);
	
	public abstract int rand();
}
