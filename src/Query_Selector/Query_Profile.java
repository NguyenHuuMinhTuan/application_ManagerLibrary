package Query_Selector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Model.Account;

public class Query_Profile {
	 public static Account getProfileAccount(String username) {
	        Account account = null;
	        String query = "SELECT * FROM Account WHERE username = ?";
	        
	        try (Connection conn = DatabaseConnect.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(query)) {
	            
	            stmt.setString(1, username);
	            ResultSet rs = stmt.executeQuery();
	            
	            if (rs.next()) {
	            	String user = rs.getString("username");
	                String password = rs.getString("password");
	                String email = rs.getString("email");
	                String phone = rs.getString("phone");
	                int type = rs.getInt("type_account");
	                
	                account = new Account(user, password, email, phone, type);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        
	        return account;
	    }
	 public boolean updateProfileAccount( String newPassword, String newEmail, String newPhone, String username) {
		    String query = "UPDATE ACCOUNT SET PASSWORD = ?, EMAIL = ?, PHONE = ? WHERE USERNAME = ?";
		    
		    try (Connection conn = DatabaseConnect.getConnection();
		         PreparedStatement stmt = conn.prepareStatement(query)) {
		        
		     
		        stmt.setString(1, newPassword);
		        stmt.setString(2, newEmail);
		        stmt.setString(3, newPhone);
		        stmt.setString(4, username);
		        
		       
		        int rowsAffected = stmt.executeUpdate();
		        
		     
		        return rowsAffected > 0;
		        
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    
		    return false;
		}

	 public boolean resetPassword(String username) {
	  
	        String query = "UPDATE ACCOUNT SET PASSWORD = LOWER (CONVERT(Nvarchar(50),HASHBYTES('MD5','0'),2)) WHERE USERNAME = ?";
	        
	        try (Connection conn = DatabaseConnect.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(query)) {

	             stmt.setString(1, username);
	             int rowsAffected = stmt.executeUpdate();
	            
	             return rowsAffected > 0;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        
	        return false;
	    }

}
