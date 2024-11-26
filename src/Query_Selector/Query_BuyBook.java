package Query_Selector;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.Bill;
import Model.BillInfo;
import Model.Book;

public class Query_BuyBook {

	public List<String> getIdBooksFromDatabase() {
		List<String> idBook = new ArrayList<>();

		String query = "SELECT id FROM Book";

		try (Connection conn = DatabaseConnect.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {

			// Duyệt qua kết quả và thêm vào danh sách
			while (rs.next()) {
				idBook.add(rs.getString("id"));
			}
		} catch (SQLException e) {
			e.printStackTrace(); // Xử lý lỗi nếu có
		}
		return idBook;
	}

	public List<String> getIdCustomerFromDatabase() {
		List<String> idBook = new ArrayList<>();

		String query = "SELECT id FROM Customer";

		try (Connection conn = DatabaseConnect.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {

			// Duyệt qua kết quả và thêm vào danh sách
			while (rs.next()) {
				idBook.add(rs.getString("id"));
			}
		} catch (SQLException e) {
			e.printStackTrace(); // Xử lý lỗi nếu có
		}
		return idBook;
	}

	public static boolean addBilInfo(BillInfo billInfo) {
		String query = "INSERT INTO BillInfo VALUES (?, ?, ?, ?, ?)";

		try {
			Connection conn = DatabaseConnect.getConnection();
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, billInfo.getId_Bill());
			stmt.setInt(2, billInfo.getId_Book());
			stmt.setInt(3, billInfo.getQuantity());
			stmt.setFloat(4, billInfo.getPrice());
			stmt.setFloat(5, billInfo.getDiscount());

			int rowsInserted = stmt.executeUpdate();

			stmt.close();
			conn.close();

			return rowsInserted > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean addBill(Bill bill) {
		String query = "INSERT INTO Bill VALUES (?, ?, ?, ?, ?)";

		try {
			Connection conn = DatabaseConnect.getConnection();
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, bill.getId_Customer());
			Date sqlDate = Date.valueOf(bill.getDate());
			stmt.setDate(2, sqlDate);
			stmt.setFloat(3, bill.getTotal_Payment());
			stmt.setInt(4, bill.getPay_Method());
			stmt.setInt(5, bill.getStatus());

			int rowsInserted = stmt.executeUpdate();

			stmt.close();
			conn.close();

			return rowsInserted > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static int getIDHigher() {
	    int id = 0;
	    String query = "SELECT TOP 1 ID FROM BILL ORDER BY ID DESC";
	    try {
	        Connection con = DatabaseConnect.getConnection();
	        Statement stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        if (rs.next()) {  // dùng `if` vì chỉ có một kết quả duy nhất
	            id = rs.getInt("ID");
	        }
	    } catch (Exception e) {
	        e.printStackTrace(); // in ra lỗi nếu có
	    }
	    return id;
	}

}
