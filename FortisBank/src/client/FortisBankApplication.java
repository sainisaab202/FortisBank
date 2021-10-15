//	PROJECT BY: RAZA KHAN, DIEGO SOSA, GURPREET SAINI
package client;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
import bus.*;
import data.TransactionDB;


public class FortisBankApplication{
	//global Variables
	static Scanner scan = new Scanner(System.in);
	static IManager manager;
	
	public static void displayMenu() throws ClassNotFoundException, IOException
	{
		System.out.println("-----Welcome to FortisBank-----");
		System.out.println("------Please identify Yourself------");
		boolean valid= true;
		do 
		{
			System.out.println("------Main Menu------");
			System.out.println("-- Press [1] for Bank Manager Press[2] for Customer Press[3] for Exit --");
			int userInput = -1;
			
			while(!scan.hasNextInt())
			{
				System.out.println("Input can only be numeric");
				scan.next();	//verifying the the input is 0-9
			}
			userInput = scan.nextInt();
			if(userInput == 1)
			{
				valid = true;
				loginManager();
			}
			
			else if(userInput==2)
			{
				loginCustomer();
				valid=true;
			}
			else if(userInput==3)
			{
				System.out.println("Thank you for using our services\n"+
						"Project By: Raza Khan, Diego Sosa, Gurpreet Saini");
				valid = false;
			}
		}while(valid == true);
	}
	
	public static void loginManager() throws ClassNotFoundException, IOException
	{
		System.out.println("Please enter your Employee Id ");
		while(!scan.hasNextInt())
		{
			System.out.println("You can enter digits only 0-9!!!");
			scan.next();
		}
		int managerEmpId = scan.nextInt();
		
		System.out.println("Please enter your Password ");
		String managerPass = scan.next();
		
		// checking to see if credentials match
		if(manager.getEmpId() == managerEmpId && manager.getPassword().equals(managerPass))
		{
			System.out.println(" Welcome" + managerEmpId);
			//new function name here 
			generateManagerMenu();
		}
		else
		{
			System.out.println("incorrect information");
		}
	}
	
	public static void generateManagerMenu() throws ClassNotFoundException, IOException
	{
		System.out.println("---Welcome Manager---");
		
		int managerInput = -1;
		boolean managerBool = false;
		
		while(managerInput != 7)
		{
			System.out.println("\n\n[1] Add Customer [2] Remove Customer [3] Search Customer [4] Display All Customers [5] List All Transactions [6] Search Transaction [7] Logout");			
			do
			{
				while(!scan.hasNextInt())
				{
					System.out.println("You can only choose options 1 through 7 please");
					scan.next();
				}
				managerInput = scan.nextInt();
				if(managerInput > 7 )
				{
					System.out.println("You cannot choose an option above 7");
					managerBool = false;
				}
				else
				{	//best case to continue operation
					managerBool = true;
					break;
				}
			}while(managerBool == false);
			
		// menu input is now validated 
			switch (managerInput) {
			case 1:
				addCustomer();
				break;
			case 2:
				removeCustomer();
				break;
			case 3:
				searchCustomer();
				break;
			case 4:
				manager.printListOfAllCustomers();
				break;
			case 5:
				try {
					System.out.println("------All Transaction------");
					for (Transaction aTransaction : TransactionDB.getTransactionList()) {
						System.out.println(aTransaction);
					}					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			case 6:
				System.out.println("--Search for a Transaction--");
				System.out.print("Please, Enter a Transaction number to perform search: ");
				while(!scan.hasNextInt())
				{
					System.out.println("You can enter digits only (0-9)");
					scan.next();
				}
				int transactionId = scan.nextInt();
				Transaction t = null;
				try {
					t = TransactionDB.search(transactionId);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				if(t!=null) {
					System.out.println(t);
				}else {
					System.out.println("Transaction number "+ transactionId + " does not exist");
				}
				break;
			case 7:
				System.out.println("---Logging off---");				
				FileHandler.writeManagerToFile((Manager)manager);
				return;
			default:
				break;
			}
		}			
	}
	
	public static void searchCustomer() {
		System.out.println("\n--Search--");
		System.out.println("Enter customer number: ");
		while(!scan.hasNextInt())
		{
			System.out.println("You can enter digits only 0-9!!!");
			scan.next();
		}
		int customerId = scan.nextInt();
		manager.printCustomer(customerId);
	}
	
	public static void removeCustomer() {
		System.out.println("Enter customer number you wish to remove: ");
		while(!scan.hasNextInt())
		{
			System.out.println("You can enter digits only 0-9!!!");
			scan.next();
		}
		int customerId = scan.nextInt();
		if(manager.removeCustomer(customerId)) {
			System.out.println("Remove successful!");
		}else {
			System.out.println("This customer number does not exist!");
		}
	}
	
	public static void addCustomer() {
		ICustomer cs1 = new Customer();
		System.out.println("--Enter Customer Information--");
		
		boolean valid = false;
		do {
			try {
				System.out.print("Name: ");
				cs1.setName(scan.next());
				valid = true;
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}while(!valid);
		
		valid = false;
		do {
			try {
				System.out.print("Email: ");
				cs1.setEmail(scan.next());
				valid = true;
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}while(!valid);
		
		valid = false;
		do {
			try {
				System.out.print("Contact Number: ");
				cs1.setContactNumber(scan.next());
				valid = true;
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}while(!valid);
			
		valid = false;
		do {
			try {
				System.out.print("Customer Pin(digit only 0-9 and starting from 1000): ");
				while(!scan.hasNextInt())
				{
					System.out.println("You can enter digits only 0-9!!!");
					scan.next();
				}
				cs1.setPin(scan.nextInt());
				valid = true;
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}while(!valid);
		
		try {
			cs1.openAccount(EnumTypeAccount.checking);
		} catch (RaiseException e) {
			System.out.println(e.getMessage());
		}
		
		manager.addCustomer((Customer) cs1);
	}
	
	public static void loginCustomer() {
		System.out.println("Please enter your customerNumber ");
		while(!scan.hasNextInt())
		{
			System.out.println("You can enter digits only 0-9!!!");
			scan.next();
		}
		int customerNumber = scan.nextInt();
		
		System.out.println("Please enter your Password ");
		while(!scan.hasNextInt())
		{
			System.out.println("You can enter digits only 0-9!!!");
			scan.next();
		}
		int pin = scan.nextInt();
		
		//checking if customer exists
		ICustomer c = manager.searchCustomer(customerNumber);
		if(c!=null) {
			//checking if customer enter valid id and pin
			if(c.isValidLogin(customerNumber, pin)){
				//here customer successful login
				generateCustomerMenu(c);
			}else {
				System.out.println("customerNumber and pin does not match");
			}
		}else {
			System.out.println("incorrect information");
		}
	}
	
	public static void generateCustomerMenu(ICustomer c) {
		System.out.println("---Welcome "+c.getName()+"---");
		
		int input;
		
		do {
			System.out.println("[1] Display information [2] Update information [3] Select an Account(deposit/withdraw) [4] Open New Account [5] Logout");
			while(!scan.hasNextInt()) {
				System.out.println("Please, Enter a number related to your operation: ");
				scan.next();
			}
			input = scan.nextInt();
			
			switch (input) {
				case 1:
					System.out.println(c);
					break;
				case 2:
					updateInfo(c);
					break;
				case 3:
					selectAccount(c);
					break;
				case 4:
					openNewAccount(c);
					break;
				case 5:
					try {
						FileHandler.writeManagerToFile((Manager)manager);
					} catch (IOException e) {
						System.out.println(e.getMessage());
					}
					System.out.println("--Logging off--");
					return;
				default:
					System.out.println("Wrong Choice! Please try Again!!");
					break;
			}
			
		}while(true);
					
	}
	
	public static void openNewAccount(ICustomer c) {
		System.out.println("--Open New Account--");
		do {
			System.out.println("Select the type of account [1] Checking, [2] Savings, [3] Credit, [0] Exit");
			int input;
			while(!scan.hasNextInt()) {
				System.out.println("Please, Enter the number related to your choice!!");
				scan.next();
			}
			input = scan.nextInt();
			switch (input) {
				case 0:
					System.out.println("Back to previous Menu");
					return;
				case 1:
					try {
						c.openAccount(EnumTypeAccount.checking);
						System.out.println("Account Successfully created");
					} catch (RaiseException e) {
						System.out.println(e.getMessage());
					}
					break;
				case 2:
					try {
						c.openAccount(EnumTypeAccount.savings);
						System.out.println("Account Successfully created");
					} catch (RaiseException e) {
						System.out.println(e.getMessage());
					}
					break;
				case 3:
					try {
						c.openAccount(EnumTypeAccount.credit);
						System.out.println("Account Successfully created");
					} catch (RaiseException e) {
						System.out.println(e.getMessage());
					}
					break;
		
				default:
					System.out.println("Wrong choice, Please try Again");
					break;
			}
		}while(true);
	}
	
	public static void selectAccount(ICustomer c) {
		int input;
		do {	
			System.out.println("Select an account number to perform a transaction [0] for Exit: ");
			c.printAccount();
			
			while(!scan.hasNextInt()) {
				System.out.println("Please, Enter the account number related to your choice!!");
				scan.next();
			}
			input = scan.nextInt();
			if(c.getAccount(input)!=null) {
				IAccount acc = (IAccount)c.getAccount(input);
				int tranType;
				do {
					System.out.println("Choose [1] Withdraw [2] Deposit [0] Exit");
					while(!scan.hasNextInt()) {
						System.out.println("Please, Enter number corrosponding to your transaction!");
						scan.next();
					}
					tranType = scan.nextInt();
					int amount=-1;
					switch (tranType) {
					case 1:
						System.out.println("--Withdraw--");
						System.out.println("Enter the amount to withdraw, [0] to exit");
						do {
							try {
								while(!scan.hasNextInt()) { System.out.println("Please enter amount in numbers"); scan.next(); }
								amount = scan.nextInt();
								acc.withdraw(amount);
								System.out.println("Withdraw succeed!!");
								break;
							} catch (RaiseException e) {
								System.out.println(e.getMessage());
							}
						}while(amount!=0);
						break;
					case 2:
						System.out.println("--Deposit--");
						System.out.println("Enter the amount to deposit, [0] to exit");
						do {
							try {
								while(!scan.hasNextInt()) { System.out.println("Please enter amount in numbers"); scan.next(); }
								amount = scan.nextInt();
								acc.deposit(amount);
								System.out.println("Deposit succeed!!");
								break;
							} catch (RaiseException e) {
								System.out.println(e.getMessage());
							}
						}while(amount!=0);
						break;
					case 0:
						System.out.println("Going Back to previous Menu");
						tranType = 0;
						break;
					default:
						System.out.println("Wrong choice, Please try again!!");
						break;
					}
					//TODO saving here after each transaction 
					try {
						FileHandler.writeManagerToFile((Manager)manager);
					} catch (IOException e) {
						System.out.println(e.getMessage());
					}
				}while(tranType!=0);
				
			}else if(input == 0){
				System.out.println("Going back to the menu!!");
			}else{
				System.out.println("Wrong Choice");
			}
		}while(input != 0);
	}
	
	public static void updateInfo(ICustomer c) {
		int input;
		do {
			System.out.println("What you want to update ?");
			System.out.println("[1] Pin [2] Email [3] Contact Number [4] Name [5] Back to menu");
			while(!scan.hasNextInt()) {
				System.out.println("Please, Enter the corrosponding number related to your choice!!");
				scan.next();
			}
			input = scan.nextInt();
			
			boolean valid = false;
			switch (input) {
				case 1:
					do {
						try {
							System.out.print("Customer Pin(OLD): ");
							while(!scan.hasNextInt())
							{
								System.out.println("You can enter digits only 0-9!!!");
								scan.next();
							}
							int oldPin = scan.nextInt();
							
							System.out.print("Customer Pin(NEW): ");
							while(!scan.hasNextInt())
							{
								System.out.println("You can enter digits only 0-9!!!");
								scan.next();
							}
							int newPin = scan.nextInt();
							
							c.updatePin(oldPin, newPin);
							valid = true;
							System.out.println("Updation Successful!");
						}
						catch(Exception e) {
							System.out.println(e.getMessage());
						}
					}while(!valid);
					break;
				case 2:
					do {
						try {
							System.out.print("Email: ");
							c.setEmail(scan.next());
							valid = true;
							System.out.println("Updation Successful!");
						}
						catch(Exception e) {
							System.out.println(e.getMessage());
						}
					}while(!valid);
					break;
				case 3:
					do {
						try {
							System.out.print("Contact Number: ");
							c.setContactNumber(scan.next());
							valid = true;
						}
						catch(Exception e) {
							System.out.println(e.getMessage());
						}
					}while(!valid);
					break;
				case 4:
					do {
						try {
							System.out.print("Name: ");
							c.setName(scan.next());
							valid = true;
						}
						catch(Exception e) {
							System.out.println(e.getMessage());
						}
					}while(!valid);
					break;
				case 5:		//back to previous menu
					return;
				default:
					System.out.println("Wrong choice, Please Try Again!!");
					break;
			}
		}while(true);
	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {

		//will read file
		manager = (IManager)FileHandler.readManagerFromFile();
		//manager empId=1111, password=qwer
		
		//starts application
		displayMenu();
		
		
		
		
//		creating first time numberGenerator
//		FileOutputStream fos = new FileOutputStream("src//data//internalNum.ser");
//		ObjectOutputStream oos = new ObjectOutputStream (fos);
//		//creating object to save and getting current numbers from generator
//		InternalNumberInit ini = new InternalNumberInit(InternalNumberGenerators.getAccountNumber(),
//				InternalNumberGenerators.getCustomerNumber(),
//				InternalNumberGenerators.getTransactionNumber());
//		
//		oos.writeObject(ini);
	}

}
