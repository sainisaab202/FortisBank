package bus;

public class Credit extends Account{

	private static final long serialVersionUID = -2128203290782762611L;
	private double creditLimit;
	
	/** Default constructor set credit limit to $1000 */
	public Credit() {
		super();
		creditLimit = 1000;
	}

	/***
	 * Will use when creating a credit account first time from client side
	 * @throws RaiseException 
	 */
	public Credit(int pin, double balance, double fees,
			double creditLimit) throws RaiseException {
		super(pin, EnumTypeAccount.credit, balance, fees);
		this.creditLimit = creditLimit;
	}
	
	//getters and setters
	public double getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(double creditLimit) {
		this.creditLimit = creditLimit;
	}

	
	/** To use your credit card 
	 * 	returns [1]:  withdraw without extra charges
	 * 			[2]:  withdraw with fee (withdraw more than credit limit)
	 * @throws RaiseException 
	 * */
	@Override
	public int withdraw(double amount) throws RaiseException 
	{
		if(amount<20) {
			throw new RaiseException("Minimum amount to withdraw is 20$");
		}
		if(this.getBalance() + amount <= this.creditLimit)
		{
			this.setBalance(this.getBalance()+amount);
			
			//create a transaction and adding it to the list of transactions of this account 
			Transaction tmpTransaction = new Transaction(amount, EnumTypeTransaction.withdraw);
			this.addTransaction(tmpTransaction);
			
			return 1;
		} 
		else
		{
			this.setBalance(this.getBalance() + amount + getFees());
			
			//create a transaction and adding it to the list of transactions of this account 
			Transaction tmpTransaction = new Transaction(amount, EnumTypeTransaction.withdraw);
			this.addTransaction(tmpTransaction);
			
			return 2;
		}
	}

	/** To pay your credit card bill (same as payCredit()) 
	 * @throws RaiseException */
	@Override
	public int deposit(double amount) throws RaiseException {
		Validator.validateDeposit(amount);
		this.setBalance(this.getBalance() - amount);
		
		//create a transaction and adding it to the list of transactions of this account 
		Transaction tmpTransaction = new Transaction(amount, EnumTypeTransaction.deposit);
		this.addTransaction(tmpTransaction);
		
		return 0;
	}

}
