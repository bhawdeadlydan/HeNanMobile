package sjtu.rfid.thread;

public class NoRespondingException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NoRespondingException(String errMessage) {
		super(errMessage);
	}

}
