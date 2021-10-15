package bus;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileHandler {

	//proving a path to for our collectionOfCustomers
	private static String filePath = "src//data//Manager.ser" ;
	private static String file4InternalNumbers = "src//data//internalNum.ser" ;
	
	/** To write a manger into file */
	public static void writeManagerToFile(Manager manager) throws IOException
	{
		FileOutputStream fos = new FileOutputStream(filePath);
		@SuppressWarnings("resource")
		ObjectOutputStream oos = new ObjectOutputStream (fos);		
		oos.writeObject(manager);	
		
		//for internal numbers to be saved in file
		fos = new FileOutputStream(file4InternalNumbers);
		oos = new ObjectOutputStream (fos);
		//creating object to save and getting current numbers from generator
		InternalNumberInit ini = new InternalNumberInit(InternalNumberGenerators.getAccountNumber(),
				InternalNumberGenerators.getCustomerNumber(),
				InternalNumberGenerators.getTransactionNumber());
		
		oos.writeObject(ini);
		
		oos.close();
		fos.close();
	}
	
	//reading from the file
	public static Manager readManagerFromFile() throws IOException, ClassNotFoundException
	{
        FileInputStream fis = new FileInputStream(filePath);
        ObjectInputStream ois = new ObjectInputStream(fis);
        
		Manager manager = (Manager)ois.readObject();	
		
		//for internal numbers
		fis = new FileInputStream(file4InternalNumbers);
		ois = new ObjectInputStream(fis);
		InternalNumberInit ing = (InternalNumberInit)ois.readObject();
		ing.setInternalNumberGenerator();
		
		ois.close();
		fis.close();
		
		return manager;
	}
		
	
	
}
