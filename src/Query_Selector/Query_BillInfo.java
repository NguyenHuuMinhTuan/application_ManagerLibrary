package Query_Selector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import Model.BillInfo;
import Model.Book;
import Model.Borrow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public class Query_BillInfo {
	public static ObservableList<BillInfo> getBill(){
		ObservableList<BillInfo> bill = FXCollections.observableArrayList();
		try {
			String query = " SELECT * FROM BILLINFO";
			Connection con = DatabaseConnect.getConnection();
			PreparedStatement stm = con.prepareStatement(query);
			ResultSet rs = stm.executeQuery();
			
			while(rs.next()) {
				BillInfo bf = new BillInfo(rs.getInt("ID"),rs.getInt(1),
						rs.getInt(2), rs.getInt("QUANTITY"),
						rs.getFloat("PRICE"), rs.getFloat("DISCOUNT"));
				bill.add(bf);
			}
			rs.close();
			stm.close();
			con.close();

		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return bill;
		
	}
	public static void displayBooks(TableView<BillInfo> tableView) {
		tableView.setItems(getBill());
	}
	public static ObservableList<Borrow> getBorrow() {
		ObservableList<Borrow> borrowList = FXCollections.observableArrayList() ;
		String sql = " SELECT * FROM BORROW";
		try {
			Connection con = DatabaseConnect.getConnection();
			PreparedStatement smt = con.prepareStatement(sql);
			ResultSet rs = smt.executeQuery();
			while(rs.next()) {
				Borrow br = new Borrow(rs.getInt("ID")
						,rs.getInt("CUSTOMER_ID")
						,rs.getDate("BORROW_DATE").toLocalDate()
						,rs.getDate("RETURN_DATE").toLocalDate()
						,rs.getInt("STATUS"));
							borrowList.add(br);
			}
		}catch(Exception e) {
			e.getStackTrace();
		}
		return borrowList;
	}
	public static void displayBorrows(TableView<Borrow> tableView) {
		tableView.setItems(getBorrow());
	}
}
