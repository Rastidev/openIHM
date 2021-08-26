package openihm.interfaces;

public class TimerThread extends Thread{
	
	private final System sys;
	
	private final long ms = 1000;
	
	private int n = 0;
	
	public TimerThread (final System sys, final Runnable run) {
		this.sys = sys;
	}
	
	public void run() {
//		long lastTime = sys.getDate();
//		while(true) {
//			if(sys.getDate() - lastTime > ms) {
//				lastTime = sys.getDate();
//				java.lang.System.out.println(n);
//				n++;
//			}
//			if(n > 10) break;
//		}
	}

}
