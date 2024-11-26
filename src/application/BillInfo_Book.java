package application;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import Model.BillInfo;
import Model.Borrow;
import Query_Selector.Query_BillInfo;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class BillInfo_Book {
	@FXML
	TableView <BillInfo>tb_billInfo;
	@FXML
	TableView <Borrow> tv_borrow;
	@FXML
	TableColumn <Borrow, Integer> col_idBorrow;
	@FXML
	TableColumn <Borrow, Integer> col_idCustomer;
	@FXML
	TableColumn <Borrow, String> col_dateBorrow;
	@FXML
	TableColumn <Borrow, String> col_due;
	@FXML
	TableColumn <Borrow, Integer> col_status;

	
	@FXML
	TableColumn <BillInfo, Integer> col_id;
	@FXML
	TableColumn <BillInfo, Integer> col_idBill;
	@FXML
	TableColumn <BillInfo, Integer> col_idBook;
	@FXML
	TableColumn <BillInfo, Integer> col_quantity;
	@FXML
	TableColumn <BillInfo, Float> col_price;
	@FXML
	TableColumn <BillInfo, Float> col_discount;
	@FXML
	
	Query_BillInfo billInfo;
	
	public void initialize() {
		col_id.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
		col_idBill.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId_Bill()).asObject());
		col_idBook.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId_Book()).asObject());
		col_quantity.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getQuantity()).asObject());
		col_price.setCellValueFactory(cellData -> new SimpleFloatProperty(cellData.getValue().getPrice()).asObject());
		col_discount.setCellValueFactory(cellData -> new SimpleFloatProperty(cellData.getValue().getDiscount()).asObject());
		billInfo.displayBooks(tb_billInfo);
		
		col_idBorrow.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
		col_idCustomer.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getIdCustomer()).asObject());
		 col_dateBorrow.setCellValueFactory(cellData -> {
 	        LocalDate dueDate = cellData.getValue().getBorrowDate();
 	        if (dueDate != null) {
 	            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
 	            return new javafx.beans.property.SimpleStringProperty(dueDate.format(formatter));
 	        } else {
 	            return new javafx.beans.property.SimpleStringProperty("");
 	        }
 	    });
		 col_due.setCellValueFactory(cellData -> {
 	        LocalDate dueDate = cellData.getValue().getReturnDate();
 	        if (dueDate != null) {
 	            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
 	            return new javafx.beans.property.SimpleStringProperty(dueDate.format(formatter));
 	        } else {
 	            return new javafx.beans.property.SimpleStringProperty("");
 	        }
 	    });
		 col_status.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getStatus()).asObject());
		 billInfo.displayBorrows(tv_borrow);
		
	}
}
