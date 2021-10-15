package bus;

import java.util.Date;

public interface IAccount {
	
	public int getAccountNumber();
	
	public double getBalance();
	
	public EnumTypeAccount getAccType();
	
	public Date getOpenDate();
	
	public double getFees();
	
	

	public abstract int withdraw(double amount) throws RaiseException;
	
	public abstract int deposit(double amount) throws RaiseException;
}
