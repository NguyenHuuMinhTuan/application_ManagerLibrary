package Query_Selector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public class Query_Customer {
	public static ObservableList<Customer> getCustomer() {
		ObservableList<Customer> customerList = FXCollections.observableArrayList();
		String query = "SELECT * FROM CUSTOMER";

		try {
			Connection conn = DatabaseConnect.getConnection();
			PreparedStatement stmt = conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Customer customer = new Customer(rs.getInt("ID"), rs.getString("USERNAME"), rs.getInt("PHONE"),rs.getString("EMAIL"),
						 rs.getString("ADDRESS"), rs.getString("MEMBERSHIP"));

				customerList.add(customer);
			}

			rs.close();
			stmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return customerList;
	}

	public static void displayCustomer(TableView<Customer> tableView) {
		tableView.setItems(getCustomer());
	}

	public static boolean AddCustomer(Customer customer) {
		String query = "INSERT INTO CUSTOMER VALUES (?, ?, ?, ?, ?)";

		try {
			Connection conn = DatabaseConnect.getConnection();
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, customer.getUsername());
			stmt.setInt(2, customer.getPhone());
			stmt.setString(3,customer.getEmail());
			stmt.setString(4, customer.getAddress());
			stmt.setString(5, customer.getMemberShip());
	
			int rowsInserted = stmt.executeUpdate();

			stmt.close();
			conn.close();

			return rowsInserted > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean updateCustomer(Customer customer, int id) {
		String query = "UPDATE CUSTOMER SET USERNAME = ?, PHONE = ?,EMAIL = ?, ADDRESS = ?, MEMBERSHIP= ? WHERE ID = ?";
		try {
			Connection con = DatabaseConnect.getConnection();
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, customer.getUsername());
			stmt.setInt(2, customer.getPhone());
			stmt.setString(3,customer.getEmail());
			stmt.setString(4, customer.getAddress());
			stmt.setString(5, customer.getMemberShip());
			stmt.setInt(6, id);

			int rowsInserted = stmt.executeUpdate();

			stmt.close();
			con.close();

			return rowsInserted > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}

	public static boolean deleteCustomer(int id) {
		String query = "DELETE FROM CUSTOMER WHERE ID = ?";

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

	public static void searchBooks(TableView<Customer> tableView,String keyword) {
		ObservableList<Customer> customerList = FXCollections.observableArrayList();
		String query = "SELECT * FROM CUSTOMER WHERE USERNAME LIKE ? ";

		try {
			Connection conn = DatabaseConnect.getConnection();
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, "%" + keyword + "%");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Customer customer = new Customer(rs.getInt("ID"), rs.getString("USERNAME"), rs.getInt("PHONE"),rs.getString("EMAIL"),
						 rs.getString("ADDRESS"), rs.getString("MEMBERSHIP"));

				customerList.add(customer);
			}

			rs.close();
			stmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		tableView.setItems(customerList);
	}

}
