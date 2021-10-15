package bus;

import java.io.Serializable;
import java.util.ArrayList;

public class ListOfTransactions implements Serializable{
	
	private static final long serialVersionUID = 3698208152479660356L;
	private ArrayList<Transaction> listOfTransactions;
	
	public ListOfTransactions() {
		
		this.listOfTransactions = new ArrayList<Transaction>();
	}

	
	public void add(Transaction trs) 
	{		
		listOfTransactions.add(trs);
	}
	
	public void delete(Transaction trs) 
	{	
		listOfTransactions.remove(trs);
	}
	
	public Transaction search(int transactionNumber) 
	{
		for (Transaction trs : listOfTransactions) 
		{
			if(trs.getTransactionNumber() == transactionNumber) 
			{
				return trs;
			}
		}
		return null;
	}
	
	
	public String print() 
	{
		String tmp = "\n---All the transactions ---\n";
		for (Transaction transaction : listOfTransactions) 
		{
			tmp+= "\n"+transaction.getDescription()+"\n";
		}
		return tmp;
	}
}
