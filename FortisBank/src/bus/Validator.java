package bus;
import java.util.regex.Pattern;

public class Validator {
	
	//for balance
	public static void isPositive(int value) throws RaiseException {
		if(value <= 0) {
			throw new RaiseException("Invalid input, value must be Positive...[from SaiNi: back-end]");
		}
	}
	
	/** to check A/C withdrawal amount */
	public static void validateWithdraw(double balance, double withdrawAmount) throws RaiseException {
		if(balance < withdrawAmount) {
			throw new RaiseException("Insufficient Funds!!");
		}
		if(withdrawAmount < 20) {
			throw new RaiseException("Minimum amount to withdraw is 20$!!");
		}
		if(withdrawAmount % 20 != 0) {
			throw new RaiseException("Amount should be in multiples of 20 (20,40,60....)!!");
		}
	}
	
	/** to check A/C depositing amount */
	public static void validateDeposit(double depositAmount) throws RaiseException {
		if(depositAmount < 20) {
			throw new RaiseException("Minimum amount to deposit is 20$!!");
		}
	}
	
	/** to check contactNumber */
	public static void validatePNumber(String value)throws RaiseException {
		for(int i = 0; i!= value.length(); ++i) {
			if(!Character.isDigit(value.charAt(i))) {
				throw new RaiseException("Invalid input, value must be digit only!!!");
			}
		}
		if(value.length()!=10) {
			throw new RaiseException("Invalid input, Phone number should be 10 digits!!!");
		}
	}
	
	/** To validate names */
	public static void isAlphabetic(String value)throws RaiseException {
		if(value.length() < 1) {
			throw new RaiseException("Invalid input, value must contain alphabet letters...");
		}
		for(int i = 0; i!= value.length(); ++i) {
			if(!Character.isAlphabetic(value.charAt(i))) {
				throw new RaiseException("Invalid input, value must be alphabet letters only...");
			}
		}
	}
	
	/** To validate PIN */
	public static void validatePin(int pin) throws RaiseException {
		if(pin < 1000 || pin > 9999) {
			throw new RaiseException("Invalid input, value must be  4 digits only!!!");
		}
	}
	
	/** To validate email 
	 * @throws RaiseException */
	public static void validateEmail(String email) throws RaiseException {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
		
		Pattern pat = Pattern.compile(emailRegex);
        if (email.length() < 1) {
        	throw new RaiseException("Invalid input, you must insert your email!!!");
        }
        if(!pat.matcher(email).matches()) {
        	throw new RaiseException("Invalid input, Incorrect email format!!!");
        }
	}
}
