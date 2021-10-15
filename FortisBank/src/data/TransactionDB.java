package data;
/**
 * Here we use SQL commands only to search and list all transaction
 * We must not allow anyone to modify or delete any transaction from our database
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import bus.EnumTypeTransaction;
import bus.Transaction;

public class TransactionDB {
	static private Connection con;
	static private String query = " ";
	static private Statement stmt = null;
	static private ResultSet rs = null;
	static private Transaction aTransaction = null;
	
	public static ArrayList<Transaction> getTransactionList() throws SQLException{
		con = ConnectionDB.getConnection();
		
		query = "SELECT transactionnumber, description, transactiondate,"
				+ " amount, transactiontype, accountnumber FROM transaction";
		
		stmt = con.createStatement();
		
		rs = null;
		rs = stmt.executeQuery(query);
		
		ArrayList<Transaction> transactionList = new ArrayList<Transaction>();
		while(rs.next()) {
			int tranNumber = Integer.parseInt(rs.getString("transactionnumber"));
			String desc = rs.getString("description");
			
			java.sql.Date strDate = rs.getDate("transactiondate");
			
			Double amount = Double.parseDouble(rs.getString("amount"));
			String tran = rs.getString("transactiontype");
			EnumTypeTransaction tranType = (tran.equals("deposit")?EnumTypeTransaction.deposit:EnumTypeTransaction.withdraw);
		
			aTransaction = new Transaction(tranNumber, desc, strDate, amount, tranType);
			transactionList.add(aTransaction);
		}
		
		return transactionList;
	}
	
	/*
	 * return 1 if added successfully otherwise 0
	 */
	public static int add(Transaction aTran, int accountNumber) throws SQLException {
		con = ConnectionDB.getConnection();
//		System.out.println((aTran.getTransactionDate()));
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		query = "Insert into transaction "
				+ "values("+aTran.getTransactionNumber()+", '"+aTran.getDescription()+"', '"+dateFormat.format(aTran.getTransactionDate())+"', "+aTran.getAmount()+", '"+aTran.getTransactionType().toString()+"', "+accountNumber+")";
		
		try {
			stmt = con.createStatement();
			int rowAffected = stmt.executeUpdate(query);
			con.commit();
			if(rowAffected > 0) {
				return 1;
			}else {
				return 0;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		
	}
	
	public static Transaction search(int transactionNumber) throws SQLException{
		con = ConnectionDB.getConnection();
		
		query = "SELECT transactionnumber, description, transactiondate,"
				+ " amount, transactiontype, accountnumber FROM transaction WHERE transactionnumber="+transactionNumber;
		
		
		stmt = con.createStatement();
		
		rs = null;
		rs = stmt.executeQuery(query);
		
		aTransaction = null;
		
		if(rs.next()) {
			
			int tranNumber = Integer.parseInt(rs.getString("transactionnumber"));
			String desc = rs.getString("description");
			java.sql.Date strDate = rs.getDate("transactiondate");
			Double amount = Double.parseDouble(rs.getString("amount"));
			String tran = rs.getString("transactiontype");
			EnumTypeTransaction tranType = (tran.equals("deposit")?EnumTypeTransaction.deposit:EnumTypeTransaction.withdraw);
			
			aTransaction = new Transaction(tranNumber, desc, strDate, amount, tranType);
		}
		
		return aTransaction;
	}
}
