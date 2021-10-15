package bus;

import java.io.Serializable;

public class Customer implements  ICustomer, Serializable{

	private static final long serialVersionUID = -3857769599057222128L;
	private int customerNumber;
	private String name;
	private int pin;
	private String email;
	private String contactNumber;
	private ListOfAccounts accounts;
	
	/** will create only customer you have to add checking account by using openAccount */
	public Customer()
	{
		this.customerNumber = InternalNumberGenerators.generateCustomerNumber();
		this.name = "";
		this.pin = -1;
		this.email = "";
		this.contactNumber = "";
		this.accounts = new ListOfAccounts();
	}
	
	
	//--For new customer--
	public Customer(String name, int pin, String email, String contactNumber) throws RaiseException {
		super();
		this.customerNumber = InternalNumberGenerators.generateCustomerNumber();
		this.setName(name);
		this.setPin(pin);
		this.setEmail(email);
		this.setContactNumber(contactNumber);
		
	
		
		Account acTmp = new Checking(pin, 0, 2);
	
		// adding the first checking account into the cutomer's list of accounts
		this.accounts = new ListOfAccounts();
		this.accounts.add(acTmp);
	}

	public int getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(int customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) throws RaiseException {
		Validator.isAlphabetic(name);
		this.name = name;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) throws RaiseException {
		Validator.validatePin(pin);
		this.pin = pin;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws RaiseException {
		Validator.validateEmail(email);
		this.email = email;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) throws RaiseException {
		Validator.validatePNumber(contactNumber);
		this.contactNumber = contactNumber;
	}

	public ListOfAccounts getAccounts() {
		return accounts;
	}
	
	

	public void setAccounts(ListOfAccounts accounts) {
		this.accounts = accounts;
	}

	@Override
	public String toString() {
		return "Customer [customerNumber=" + customerNumber + ", name=" + name + ", pin=" + pin + ", email=" + email
				+ ", contactNumber=" + contactNumber + ", accounts=" + accounts.print()  +"]";
	}
	
	/**user wants to change the pin */
	public void updatePin(int oldPin, int newPin) throws RaiseException
	{
		if(oldPin == pin) {
			this.setPin(newPin);
		}else {
			throw new RaiseException("Wrong PIN");
		}
	}
	
	public void openAccount(EnumTypeAccount type) throws RaiseException {
		Account tmpAc = null;
		if(type == EnumTypeAccount.checking) {
			tmpAc = new Checking(this.pin, 0, 2);
		}else if(type == EnumTypeAccount.savings) {
			tmpAc = new Savings(this.pin, 0, 10, 1.2);
		}else if(type == EnumTypeAccount.credit) {
			tmpAc = new Credit(this.pin, 0, 10, 1000);
		}
		this.accounts.add(tmpAc);
	}


	@Override
	public boolean isValidLogin(int customerNumber, int pin) {
		return customerNumber == this.getCustomerNumber() && pin == this.pin;
	}

	@Override
	public void printAccount() {
		String info="";
		int counter = 1;
		for (Account acc : accounts.getAllAccounts()) {
			info += counter+". "+ acc.getAccountNumber() +" "+acc.getAccType()+ "\n";
			counter++;
		}
		System.out.println(info);
	}
	
	@Override
	public Account getAccount(int accountNumber) {
		for(Account acc : accounts.getAllAccounts()) {
			if(acc.getAccountNumber() == accountNumber) {
				return acc;
			}
		}
		return null;
	}
}
