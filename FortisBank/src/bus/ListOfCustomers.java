package bus;

import java.io.Serializable;
import java.util.ArrayList;

public class ListOfCustomers implements Serializable {
	
	private static final long serialVersionUID = -6326437547389048239L;
	private ArrayList<Customer> listOfAllCustomers;
	
	public ListOfCustomers() {
		listOfAllCustomers = new ArrayList<Customer>();
	}
	
	public void add(Customer acc) 
	{
		listOfAllCustomers.add(acc);
	}
	
	public void delete(Customer acc) 
	{	
		listOfAllCustomers.remove(acc);
	}
	
	public boolean delete(int customerNumber) 
	{	
		if(search(customerNumber)!=null) {
			delete(search(customerNumber));
			return true;
		}
		return false;
	}
	
	public Customer search(int customerNumber) 
	{
		for (Customer acc : listOfAllCustomers) 
		{
			if(acc.getCustomerNumber() == customerNumber) 
			{
				return acc;
			}
		}
		return null;
	}
	
	//overload this function for more functionality
	public void print() 
	{
		for (Customer acc : listOfAllCustomers) 
		{
			System.out.println(acc.toString());
		}
	}
}
