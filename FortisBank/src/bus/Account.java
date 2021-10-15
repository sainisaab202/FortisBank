//	PROJECT BY: RAZA KHAN, DIEGO SOSA, GURPREET SAINI
package bus;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Date;

import data.TransactionDB;

abstract public class Account implements IAccount, Serializable {

	private static final long serialVersionUID = -4599698619980713148L;
	private int accountNumber;
	private int pin;
	private EnumTypeAccount accType;
	private Date openDate;
	private double balance;
	private ListOfTransactions transactions;
	private double fees;
	
	
	public Account() {

		this.accountNumber = -1;
		this.pin = -1;
		this.accType = EnumTypeAccount.undefined;
		this.openDate = new Date();
		this.balance = 0;
		this.transactions = new ListOfTransactions();
		this.fees = 0;
	}

	/** inserting an account directly in the client layer */
	public Account( int pin, EnumTypeAccount accType, double balance, double fees) throws RaiseException {
		
		this.accountNumber = InternalNumberGenerators.generateAccountNumber();
		this.setPin(pin);
		this.accType = accType;
		this.openDate = new Date();
		this.balance = balance;
		this.transactions = new ListOfTransactions();
		this.fees = fees;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) throws RaiseException {
		Validator.validatePin(pin);
		this.pin = pin;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getFees() {
		return fees;
	}

	public void setFees(double fees) {
		this.fees = fees;
	}

	public EnumTypeAccount getAccType() {
		return accType;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public ListOfTransactions getTransactions() {
		return transactions;
	}

	protected void addTransaction(Transaction trs) {
		//here we need TODO save in our database also
		try {
			TransactionDB.add(trs, accountNumber);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		transactions.add(trs);
	}
	
	public abstract int withdraw(double amount) throws RaiseException;
	
	public abstract int deposit(double amount) throws RaiseException;

	@Override
	public String toString() {
		return "Account [accountNumber=" + accountNumber + ", pin=" + pin + ", accType=" + accType + ", openDate="
				+ openDate + ", balance=" + balance + ", fees=" + fees + ", transactions=" + transactions.print() +  "]";
	}
	
	
}
