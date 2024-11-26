package Query_Selector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.Book;
import javafx.scene.control.TableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Query_Book {

	

	public static ObservableList<Book> getBooks() {
		ObservableList<Book> bookList = FXCollections.observableArrayList();
		String query = "SELECT * FROM BOOK";

		try {
			Connection conn = DatabaseConnect.getConnection();
			PreparedStatement stmt = conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Book book = new Book(rs.getInt("ID"), rs.getString("TITLE"), rs.getString("AUTHOR"),
						rs.getInt("ID_CATEGORY"), rs.getString("PUBLISHER"), rs.getDate("PUBLISHER_YEAR").toLocalDate(),
						rs.getInt("QUANTITY"), rs.getString("DESCRIPTION"), rs.getFloat("PRICE"));
				bookList.add(book);
			}

			rs.close();
			stmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return bookList;
	}

	public static void displayBooks(TableView<Book> tableView) {
		tableView.setItems(getBooks());
	}

	public static boolean addBook(Book book) {
		String query = "INSERT INTO BOOK (TITLE, AUTHOR, ID_CATEGORY, PUBLISHER, PUBLISHER_YEAR, QUANTITY, DESCRIPTION, PRICE) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			Connection conn = DatabaseConnect.getConnection();
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, book.getTitle());
			stmt.setString(2, book.getAuthor());
			stmt.setInt(3, book.getCategoryId());
			stmt.setString(4, book.getPublisher());
			stmt.setDate(5, java.sql.Date.valueOf(book.getPublisherYear()));
			stmt.setInt(6, book.getQuantity());
			stmt.setString(7, book.getDescription());
			stmt.setFloat(8, book.getPrice());

			int rowsInserted = stmt.executeUpdate();

			stmt.close();
			conn.close();

			return rowsInserted > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean updateBook(Book book, int id) {
		String query = "UPDATE BOOK SET TITLE = ?, AUTHOR = ?, ID_CATEGORY = ?, PUBLISHER= ?, PUBLISHER_YEAR = ?, QUANTITY = ?, DESCRIPTION = ?, PRICE = ? WHERE ID = ?";
		try {
			Connection con = DatabaseConnect.getConnection();
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, book.getTitle());
			stmt.setString(2, book.getAuthor());
			stmt.setInt(3, book.getCategoryId());
			stmt.setString(4, book.getPublisher());
			stmt.setDate(5, java.sql.Date.valueOf(book.getPublisherYear()));
			stmt.setInt(6, book.getQuantity());
			stmt.setString(7, book.getDescription());
			stmt.setFloat(8, book.getPrice());
			stmt.setInt(9, id);

			int rowsInserted = stmt.executeUpdate();

			stmt.close();
			con.close();

			return rowsInserted > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}

	public static boolean deleteBook(int id) {
		String query = "DELETE FROM BOOK WHERE ID = ?";

		try {
			Connection conn = DatabaseConnect.getConnection();
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, id);

			int rowsDeleted = stmt.executeUpdate();

			stmt.close();
			conn.close();

			return rowsDeleted > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static ObservableList<Book> searchBooks(String keyword) {
		ObservableList<Book> bookList = FXCollections.observableArrayList();
		String query = "SELECT * FROM BOOK WHERE TITLE LIKE ? ";

		try {
			Connection conn = DatabaseConnect.getConnection();
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, "%" + keyword + "%");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Book book = new Book(rs.getInt("ID"), rs.getString("TITLE"), rs.getString("AUTHOR"),
						rs.getInt("ID_CATEGORY"), rs.getString("PUBLISHER"), rs.getDate("PUBLISHER_YEAR").toLocalDate(),
						rs.getInt("QUANTITY"), rs.getString("DESCRIPTION"), rs.getFloat("PRICE"));
				bookList.add(book);
			}

			rs.close();
			stmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return bookList;
	}

	public List<String> getCategoryIDsFromDatabase() {
		List<String> categoryIDs = new ArrayList<>();

		String query = "SELECT id FROM category";

		try (Connection conn = DatabaseConnect.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {

			// Duyệt qua kết quả và thêm vào danh sách
			while (rs.next()) {
				categoryIDs.add(rs.getString("id"));
			}
		} catch (SQLException e) {
			e.printStackTrace(); // Xử lý lỗi nếu có
		}
		return categoryIDs;
	}
}
