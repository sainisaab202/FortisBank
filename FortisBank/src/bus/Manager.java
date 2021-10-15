package bus;

import java.io.Serializable;

public class Manager implements Serializable, IManager {
	
	private static final long serialVersionUID = -5773754047878482951L;
	private int empId;
	private String password;
	private ListOfCustomers listOfCustomers;
	
	public Manager() {
		this.empId = 0;
		this.password = "unDefined";
		this.listOfCustomers = new ListOfCustomers();
	}

	public Manager(int empId, String password, ListOfCustomers listOfCustomers) {
		this.empId = empId;
		this.password = password;
		this.listOfCustomers = listOfCustomers;
	}
	
	public Manager(int empId, String password) {
		this.empId = empId;
		this.password = password;
		this.listOfCustomers = new ListOfCustomers();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getEmpId() {
		return empId;
	}

	public ListOfCustomers getListOfCustomers() {
		return listOfCustomers;
	}
	
	public void addCustomer(Customer c) {
		this.listOfCustomers.add(c);
	}
	
	public boolean removeCustomer(int customerNumber) {
		return this.listOfCustomers.delete(customerNumber);
	}
	
	public void printCustomer(int customerNumber) {
		Customer c = this.listOfCustomers.search(customerNumber);
		if(c!=null) {
			System.out.println(c);
		} else {
			System.out.println("CustomerNumber: "+customerNumber+" does not exist!");
		}
	}
	
	public Customer searchCustomer(int customerNumber) {
		return this.listOfCustomers.search(customerNumber);
	}
	
	public void printListOfAllCustomers() {
		this.listOfCustomers.print();
	}
	
	//creating customer here as manager
	public void createCustomer(String name, int pin, String email, String contactNumber) throws RaiseException
	{
		Customer c = new Customer (name,pin,email,contactNumber);
		this.listOfCustomers.add(c);
	}

	@Override
	public String toString() {
		return "Manager [empId=" + empId + ", password=" + password + ", listOfCustomers=" + listOfCustomers + "]";
	}

	
	
}
