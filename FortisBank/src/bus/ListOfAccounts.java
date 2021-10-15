package bus;

import java.io.Serializable;
import java.util.ArrayList;

public class ListOfAccounts implements Serializable {
	
	private static final long serialVersionUID = -7985285804686057113L;
	private ArrayList<Account> listOfAccounts;
	
	public ListOfAccounts() {
		
		this.listOfAccounts = new ArrayList<Account>();
	}

	
	public void add(Account acc) 
	{
		this.listOfAccounts.add(acc);
	}
	
	public void delete(Account acc) 
	{	
		this.listOfAccounts.remove(acc);
	}
	
	public ArrayList<Account> getAllAccounts() {
		return listOfAccounts;
	}
	
	public Account search(int accountNumber) 
	{
		for (Account acc : listOfAccounts) 
		{
			if(acc.getAccountNumber() == accountNumber) 
			{
				return acc;
			}
		}
		return null;
	}
	
	
	public String print() 
	{
		String tmp="\n--List of Accounts--\n";
		for (Account acc : listOfAccounts) 
		{
			tmp+= "\n"+acc.toString()+"\n";
		}
		return tmp;
	}
}
