package bus;

import java.io.Serializable;

public interface ICustomer extends Serializable {

	public void updatePin(int oldPin, int newPin) throws RaiseException;
	
	public int getCustomerNumber();
	
	public String getName();
	
	public void setEmail(String email) throws RaiseException;
	
	public String getEmail();
	
	public void openAccount(EnumTypeAccount type) throws RaiseException ;
	
	public void setName(String name) throws RaiseException;
	
	public void setContactNumber(String contactNumber) throws RaiseException;
	
	public void setPin(int pin) throws RaiseException ;
	
	public boolean isValidLogin(int customerNumber, int pin);
	
	public void printAccount();
	
	public Account getAccount(int accountNumber);
	
	public ListOfAccounts getAccounts();
}
