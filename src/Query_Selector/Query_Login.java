package Query_Selector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Query_Login {

    public static boolean checkLogin(String username, String password) {
        boolean isValid = false;
        
        // Chuyển mật khẩu người dùng nhập vào thành MD5 hash
        String hashedPassword = PasswordUtils.hashPassword(password);

        // Câu truy vấn chỉ so sánh username và mật khẩu đã băm
        String query = "SELECT * FROM Account WHERE username = ? AND password = ?";
        try {
            Connection conn = DatabaseConnect.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            
           
            stmt.setString(1, username);
            stmt.setString(2, hashedPassword); // Sử dụng mật khẩu đã băm
            
            // Thực hiện truy vấn
            ResultSet rs = stmt.executeQuery();

           
            if (rs.next()) {
                isValid = true;
            }

            // Đóng các tài nguyên
            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return isValid;
    }
}
