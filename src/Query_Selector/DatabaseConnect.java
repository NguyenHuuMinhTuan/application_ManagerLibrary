package Query_Selector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnect {
	  public static Connection getConnection() {
	        Connection connection = null;
	        try {
	        	String url = "jdbc:sqlserver://ADMIN:1433;databaseName=Library_Group;user=sa;password=minhtuan2004;encrypt=true;TrustServerCertificate=True;";

	            connection = DriverManager.getConnection(url);
	            System.out.println("Kết nối thành công!");
	        } catch (SQLException e) {
	            System.out.println("Không thể kết nối tới cơ sở dữ liệu. Lỗi: " + e.getMessage());
	        }
	        return connection;
	    }

}
