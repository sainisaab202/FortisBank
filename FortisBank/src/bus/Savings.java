package bus;

public class Savings extends Account{

	private static final long serialVersionUID = 703943473106072771L;
	private double annualInterestRate;
	private double annualGain;
	
	public Savings() {
		super();
		this.annualInterestRate = 1.2;
		this.annualGain = 0;
	}

	/** For creating saving account first time at client side 
	 * @throws RaiseException */
	public Savings(int pin, double balance, double fees,
			double annualInterestRate) throws RaiseException {
		super(pin, EnumTypeAccount.savings, balance, fees);
		this.annualInterestRate = annualInterestRate;
		this.annualGain = this.getAnnualGain();
	}

	public double getAnnualInterestRate() {
		return annualInterestRate;
	}

	public double getAnnualGain() {
		annualGain = this.getBalance() * getAnnualInterestRate()/100;
		return annualGain;
	}

	/** Returns [0] if fails  else [1]: if amount withdraw succeed  
	 * @throws RaiseException */
	@Override
	public int withdraw(double amount) throws RaiseException {
		Validator.validateWithdraw(this.getBalance(), amount);
		if(amount <= this.getBalance()) {
			this.setBalance(this.getBalance() - amount);
			
			//create a transaction and adding it to the list of transactions of this account 
			Transaction tmpTransaction = new Transaction(amount, EnumTypeTransaction.withdraw);
			this.addTransaction(tmpTransaction);
			
			return 1;
		}
		return 0;
	}

	/** deposit return [0]: if fails and return [1]: if succeed 
	 * @throws RaiseException */
	@Override
	public int deposit(double amount) throws RaiseException {
		Validator.validateDeposit(amount);
		if(amount < 0) {
			return 0;
		} else {
			this.setBalance(this.getBalance() + amount);
			
			//create a transaction and adding it to the list of transactions of this account 
			Transaction tmpTransaction = new Transaction(amount, EnumTypeTransaction.deposit);
			this.addTransaction(tmpTransaction);
			
			return 1;
		}
	}

}
