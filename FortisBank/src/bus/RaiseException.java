package bus;

public class RaiseException extends Exception{

	private static final long serialVersionUID = 3646880168532816597L;
	
	private static String msg = "Invalid Input: General Exception for Fortis Bank";
	
	public RaiseException() {
		super(msg);
	}
	
	public RaiseException(String message) {
		super(message);
	}
}
