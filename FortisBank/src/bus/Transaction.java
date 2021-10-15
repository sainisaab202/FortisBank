package bus;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Transaction implements Serializable{
	
	private static final long serialVersionUID = 6186852682694630L;
	private int transactionNumber;
	private String description;
	private Date transactionDate;
	private double amount;
	private EnumTypeTransaction transactionType;
	
	public Transaction() {
		super();
		this.transactionNumber = -1;
		this.description = "undefined";
		this.transactionDate = new Date();
		this.amount = 0;
		this.transactionType = EnumTypeTransaction.undefined;
	}
	
	//WORKING
	public Transaction(double amount,EnumTypeTransaction transactionType) {
		//transactionCount++;
		this.transactionNumber = InternalNumberGenerators.generateTransactionNumber();
		this.amount = amount;
		this.transactionType = transactionType;
		this.transactionDate = new Date();
		this.description = getDescription();
	}
	
	/**
	 * For database stuff
	 * @param transactionNumber
	 * @param description
	 * @param transactionDate
	 * @param amount
	 * @param transactionType
	 */
	public Transaction(int transactionNumber, String description, Date transactionDate, double amount, EnumTypeTransaction transactionType) {
		this.transactionNumber = transactionNumber;
		this.description = description;
		this.transactionDate = transactionDate;
		this.amount = amount;
		this.transactionType = transactionType;
	}

	public int getTransactionNumber() {
		return transactionNumber;
	}
	
	public Date getTransactionDate() {
		return this.transactionDate;
	}

	public double getAmount() {
		return this.amount;
	}

	public EnumTypeTransaction getTransactionType() {
		return this.transactionType;
	}
	
	//method that returns the receipt of the transaction
	public String getDescription()
	{
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String date = formatter.format(transactionDate);
		
		return "-----Time of transaction : "+date+"\n"
				+ "Transaction Number: "+transactionNumber+"\nAmount: "+this.amount+"\nType: "+transactionType;			
	}


	@Override
	public String toString() {
		return "Transaction [transactionNumber=" + transactionNumber + ", description=" + description
				+ ", transactionDate=" + transactionDate + ", amount=" + amount + ", transactionType=" + transactionType
				+ "]";
	}
	
}
