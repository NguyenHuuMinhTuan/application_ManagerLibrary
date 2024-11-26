package Query_Selector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Model.Book;
import Model.Revenue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public class Query_Revenue {
	public static ObservableList<Revenue> getRevenue(){
		ObservableList<Model.Revenue> revenueList = FXCollections.observableArrayList();
		String query = "SELECT \r\n"
				+ "    YEAR(BILL_DATE) AS [Year],\r\n"
				+ "    MONTH(BILL_DATE) AS [Month],\r\n"
				+ "    SUM(TOTAL_PAYMENT) AS [Total_Revenue]\r\n"
				+ "FROM \r\n"
				+ "    BILL\r\n"
				+ "WHERE \r\n"
				+ "    STATUS = 1\r\n"
				+ "GROUP BY \r\n"
				+ "    YEAR(BILL_DATE), MONTH(BILL_DATE)\r\n"
				+ "ORDER BY \r\n"
				+ "    YEAR(BILL_DATE), MONTH(BILL_DATE);\r\n";
		try {
			Connection conn = DatabaseConnect.getConnection();
			PreparedStatement stmt = conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Revenue revenue = new Revenue(rs.getInt("Year"), rs.getInt("Month"), rs.getFloat("Total_Revenue"));
				revenueList.add(revenue);
			
			}
			rs.close();
			stmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return revenueList;
				
	}
	public static void displayBooks(TableView<Revenue> tableView) {
		tableView.setItems(getRevenue());
	}


}
