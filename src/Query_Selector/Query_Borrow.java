package Query_Selector;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import Model.Borrow;
import Model.BorrowInfo;

public class Query_Borrow {
	
	public  static boolean addBorrow(Borrow borrow) {
	    String sql = "INSERT INTO BORROW VALUES (?, ?, ?, ?)";
	    try (Connection con = DatabaseConnect.getConnection();
	         PreparedStatement smt = con.prepareStatement(sql)) {

	        smt.setInt(1, borrow.getIdCustomer());
	        smt.setDate(2, Date.valueOf(borrow.getBorrowDate())); 
	        smt.setDate(3, Date.valueOf(borrow.getReturnDate())); 
	        smt.setInt(4, borrow.getStatus());

	        int rowInserted = smt.executeUpdate();
	        return rowInserted > 0;
	    } catch (Exception e) {
	        e.printStackTrace(); 
	    }

	    return false;
	}
	public  static boolean addBillInfo(BorrowInfo borrowInfo) {
		
	String sql = "INSERT INTO BORROWINFO VALUES (?, ?, ?, ?)";
	try(Connection con = DatabaseConnect.getConnection();
		PreparedStatement smt = con.prepareStatement(sql)){
		smt.setInt(1,borrowInfo.getIdBook());
		smt.setInt(2, borrowInfo.getIdBorrow());
		smt.setInt(3, borrowInfo.getQuantity());
		smt.setDate(4, Date.valueOf(borrowInfo.getDueDate()));
		
		int rowInserted = smt.executeUpdate();
		
		return rowInserted > 0 ;
		
	}catch(Exception e) {
		e.getStackTrace();
	}

	
		return false;
		
	}
	public static int getIDHigher() {
	    int id = 0;
	    String query = "SELECT TOP 1 ID FROM Borrow ORDER BY ID DESC";
	    try {
	        Connection con = DatabaseConnect.getConnection();
	        Statement stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        if (rs.next()) {  
	            id = rs.getInt("ID");
	        }
	    } catch (Exception e) {
	        e.printStackTrace(); 
	    }
	    return id;
	}
}
