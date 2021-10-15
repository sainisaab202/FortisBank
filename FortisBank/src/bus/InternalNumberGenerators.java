package bus;

public class InternalNumberGenerators {

	private static int accountNumberCount = 1111;
	private static int transactionNumberCount = 2222;
	private static int customerNumberCount = 3333;
	
	public InternalNumberGenerators(int acc, int trn, int cust) {
		accountNumberCount = acc;
		transactionNumberCount = trn;
		customerNumberCount = cust;
	}
	
	/**Generates a new account number so that duplicate account numbers are impossible */
	public static int generateAccountNumber()
	{
		accountNumberCount++;
		
		return accountNumberCount;
	}
	
	
	public static int generateTransactionNumber()
	{
		return transactionNumberCount++;
	}
	
	
	public static int generateCustomerNumber()
	{
		return customerNumberCount++;
	}
	
	public static int getAccountNumber() {
		return accountNumberCount;
	}
	public static int getTransactionNumber() {
		return transactionNumberCount;
	}
	public static int getCustomerNumber() {
		return customerNumberCount;
	}
}
