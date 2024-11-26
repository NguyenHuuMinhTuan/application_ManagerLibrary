package Query_Selector;

import java.sql.Connection;
import java.sql.PreparedStatement;



public class Query_forgetPass {

	public static boolean forgetPass(String username, String email, String password) {
		String sql = " Update Account set Password = ? where username = ? and email = ? ";
		String hashByte = PasswordUtils.hashPassword(password);
		try {
			Connection con = DatabaseConnect.getConnection();
			PreparedStatement smt = con.prepareStatement(sql);
			smt.setString(1,hashByte);
			smt.setString(2,username);
			smt.setString(3,email);
			
			int row = smt.executeUpdate();
			
			smt.close();
			con.close();
 
			System.out.println("Đổi mật khẩu thành công");
			return row > 0;
			
		
			
		}catch(Exception e) {
			e.getStackTrace();
		}
		System.out.println("Đổi mật khẩu thất bại !");
		return false;
	
	}
}
