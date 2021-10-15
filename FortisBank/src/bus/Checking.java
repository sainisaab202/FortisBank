package bus;

import java.util.Date;

public class Checking extends Account {

	private static final long serialVersionUID = 3406133376911930443L;
	private int monthlyTransactionCounter;
	private Date resetDate;
	private int monthlyFreeTransactionsLimit = 4;
	
	public Checking() {
		super();
		this.monthlyTransactionCounter = 0;
	}

	//first time the account opens
	public Checking(int pin, double balance, double fees) throws RaiseException 
	{
		super(pin, EnumTypeAccount.checking, balance, fees);
		this.monthlyTransactionCounter = 0;
		this.resetDate = this.getOpenDate();//calling creation date of the account
	}

	public int getMonthlyTransactionCounter() {
		return monthlyTransactionCounter;
	}

	public void setMonthlyTransactionCounter(int monthlyTransactionCounter) {
		this.monthlyTransactionCounter = monthlyTransactionCounter;
	}
	
	
	private long daysBetween(Date one, Date two) 
	{
		long difference = (one.getTime() - two.getTime())/86400000;
		return Math.abs(difference);
	}
	
	/** [1] : Withdraw Successful, [-1] : Insufficient Funds, [2] : Minimum-Amount to Withdraw is 20,
	 * [3] : Withdraw can only be in Multiples of 20   
	 * @throws RaiseException */ 
	@Override
	public int withdraw(double amount) throws RaiseException
	{
		Validator.validateWithdraw(this.getBalance(), amount);
		//we need: balance, fees, to the check monthlyFreee, and our counter 
		
		if(this.getBalance()>= amount) 
		{
			if(amount < 20)
			{
				return 2;
			}
			else if(amount %20 !=0 )
			{
				return 3;
			}
			
			//---------------------------checking for the fees by finding the number of days between the last reset
			
			Date today = new Date();
			long days = this.daysBetween(resetDate, today);
			
			
			//------------------------end of checking for fees
			
			if(days >=28) 
			{
				// no fees
				double totalAmount = this.getBalance()-amount;
				this.setBalance(totalAmount);
				
				//create a transaction and adding it to the list of transactions of this account 
				Transaction tmpTransaction = new Transaction(amount, EnumTypeTransaction.withdraw);
				this.addTransaction(tmpTransaction);
				
				this.resetDate = new Date(); // we reset the reset day
				this.monthlyTransactionCounter =1;
				return 1;
			}
			else
			{
				// we need to increment the counter right away
				this.setMonthlyTransactionCounter(this.getMonthlyTransactionCounter()+1);
				
				if(this.monthlyTransactionCounter > this.monthlyFreeTransactionsLimit) 
				{
					// has fees
					double totalAmount = this.getBalance()-amount - this.getFees();
					this.setBalance(totalAmount);
					
					//create a transaction and adding it to the list of transactions of this account 
					Transaction tmpTransaction = new Transaction(amount, EnumTypeTransaction.withdraw);
					this.addTransaction(tmpTransaction);
					
					return 1;
				}
				else 
				{
					double totalAmount = this.getBalance()-amount;
					this.setBalance(totalAmount);
					
					//create a transaction and adding it to the list of transactions of this account 
					Transaction tmpTransaction = new Transaction(amount, EnumTypeTransaction.withdraw);
					this.addTransaction(tmpTransaction);
					
					return 1 ;
				}
			}	
		}
		else 
		{
			return -1;
		}	
	}

	/** [1] : Deposit Successful, [2] : Minimum-Amount to Deposit is 20
	 * @throws RaiseException */ 
	@Override
	public int deposit(double amount) throws RaiseException 
	{
		Validator.validateDeposit(amount);
		
		if(amount <20 ) 
		{
			return 2;
		}
		
		Date today = new Date();
		long days = this.daysBetween(resetDate, today);
		
		//------------------------end of checking for fees
		
		if(days >=28) 
		{	
			// no fees
			double totalAmount = this.getBalance()+amount;
			this.setBalance(totalAmount);
			
			
			this.resetDate = new Date(); // we reset the reset day
			this.monthlyTransactionCounter =1;
			
			
			//create a transaction and adding it to the list of transactions of this account 
			Transaction tmpTransaction = new Transaction(amount, EnumTypeTransaction.deposit);
			this.addTransaction(tmpTransaction);
			
			return 1;
		}
		else
		{	
			// we need to increment the counter right away
			this.setMonthlyTransactionCounter(this.getMonthlyTransactionCounter()+1);
			
			if(this.monthlyTransactionCounter > this.monthlyFreeTransactionsLimit) 
			{	
				// has fees
				double totalAmount = this.getBalance()+amount - this.getFees();
				this.setBalance(totalAmount);
				
				
				//create a transaction and adding it to the list of transactions of this account 
				Transaction tmpTransaction = new Transaction(amount, EnumTypeTransaction.deposit);
				this.addTransaction(tmpTransaction);
				
				return 1;
			}
			else 
			{	
				double totalAmount = this.getBalance()+amount;
				this.setBalance(totalAmount);
				
				//create a transaction and adding it to the list of transactions of this account 
				Transaction tmpTransaction = new Transaction(amount, EnumTypeTransaction.deposit);
				
				this.addTransaction(tmpTransaction);
				
				return 1 ;
			}
		}	
	}
 
	
}
