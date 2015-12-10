package sjtu.rfid.thread;


import android.os.Handler;


public class TimeoutThread extends Thread{

	private long timeout;
	private boolean isCanceled = false;
	private NoRespondingException noRespondingException;
	
	private Handler h;

	public TimeoutThread(long timeout) {
		super();
		this.timeout = timeout;
		this.noRespondingException = new NoRespondingException("Time out!");
		this.setDaemon(true);
	}
	
	public TimeoutThread(long timeout, Handler h) {
		super();
		this.timeout = timeout;
		this.noRespondingException = new NoRespondingException("Time out!");
		this.setDaemon(true);
		SetHandler(h);
	}
	
	public void SetHandler( Handler h ) {
		this.h = h;
	}
	
	public void cancel() {
		isCanceled = true;
	}
	
	public void reset() {
		isCanceled = false;
	}
	
	public void run() {

			try {
				Thread.sleep(timeout);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if( !isCanceled )
				h.sendEmptyMessage(1);

	}
	

}
