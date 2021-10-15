package bus;

public interface IManager {
	
	public void addCustomer(Customer c);
	
	public boolean removeCustomer(int customerNumber);
	
	public void printCustomer(int customerNumber);
	
	public Customer searchCustomer(int customerNumber);
	
	public void printListOfAllCustomers();
	
	public String getPassword();
	
	public int getEmpId();
	
	public void createCustomer(String name, int pin, String email, String contactNumber) throws RaiseException;

}
