package application;

import java.time.LocalDate;


import java.util.List;

import Model.Bill;
import Model.BillInfo;

import Query_Selector.Query_BuyBook;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Buy_Book {

	@FXML
	private Button btn_exit;

	@FXML
	private TableView<BillInfo> tv_BuyProducts;

	@FXML
	private TableColumn<BillInfo, Integer> col_id;
	@FXML
	private TableColumn<BillInfo, Integer> col_idBook;
	@FXML
	private TableColumn<BillInfo, Integer> col_quantity;
	@FXML
	private TableColumn<BillInfo, Float> col_price;
	@FXML
	private TableColumn<BillInfo, Float> col_discount;

	@FXML
	private TextField tf_id, tf_price, tf_discount, tf_search, tf_quantity, tf_totalPrice, tf_methodPay, tf_status;

	@FXML
	private ComboBox<String> cb_idBook, cb_idCustomer;

	@FXML
	private DatePicker dp_Time;
	@FXML
	private Button btn_add, btn_Payment, btn_deleteProducts, btn_clearTableView, btn_addBill;

	private ObservableList<BillInfo> billInfoList;

	@FXML
	public void initialize() {

		billInfoList = FXCollections.observableArrayList();
		tv_BuyProducts.setItems(billInfoList);

	
		col_id.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId_Bill()).asObject());
		col_idBook.setCellValueFactory(
				cellData -> new SimpleIntegerProperty(cellData.getValue().getId_Book()).asObject());
		col_quantity.setCellValueFactory(
				cellData -> new SimpleIntegerProperty(cellData.getValue().getQuantity()).asObject());
		col_price.setCellValueFactory(cellData -> new SimpleFloatProperty(cellData.getValue().getPrice()).asObject());
		col_discount
				.setCellValueFactory(cellData -> new SimpleFloatProperty(cellData.getValue().getDiscount()).asObject());

//		 tv_BuyProducts.getItems().addListener((ListChangeListener<BillInfo>) change -> {
//			 Render_totalPrice();
//		    });

		// Render
		RenderID_Category();
		RenderID_Customer();
		Render_totalPrice();
		getIDHiger();

		
		btn_exit.setOnAction(e -> listenExit());
		listenAdd();
		ListenPayment();
		listenClear();
		listenExit();
	
		

	}

	// render
	@FXML
	public void Render_totalPrice() {
		   float totalPayment = 0;
		    
		   
		    for (BillInfo info : tv_BuyProducts.getItems()) {
		    	
		        float itemTotal = info.getQuantity() * info.getPrice() * ( info.getDiscount() / 100);
		        totalPayment += itemTotal;
		    }
		    tf_totalPrice.setText(String.format("%.2f", totalPayment));
	}

	@FXML
	public void RenderID_Category() {

		Query_BuyBook processQuery = new Query_BuyBook();

		List<String> idBook = processQuery.getIdBooksFromDatabase();

		cb_idBook.setItems(FXCollections.observableArrayList(idBook));
	}
	@FXML
	public void RenderID_Customer() {

		Query_BuyBook processQuery = new Query_BuyBook();

		List<String> idCustomer = processQuery.getIdCustomerFromDatabase();

		cb_idCustomer.setItems(FXCollections.observableArrayList(idCustomer));
	}
	

	@FXML
	public void listenAdd() {
		btn_add.setOnAction(e -> {
			addBillInfoToTable();
		});
	}

	@FXML
	public void SaveBillInfo() {
		ObservableList<BillInfo> billList = tv_BuyProducts.getItems();

		for (BillInfo billInfo : billList) {
			int idBill = billInfo.getId_Bill();
			int id_Book = billInfo.getId_Book();
			int quantity = billInfo.getQuantity();
			float price = billInfo.getPrice();
			float discount = billInfo.getDiscount();
			try {

				BillInfo bInfo = new BillInfo(0,idBill, id_Book, quantity, price, discount);
				if (Query_BuyBook.addBilInfo(bInfo)) {
					Alert a = new Alert(AlertType.CONFIRMATION, "Thêm chi tiết thành công");
					a.showAndWait();
				}

			} catch (Exception e) {
				Alert a = new Alert(AlertType.ERROR, "Thêm chi tiết thất bại");
				a.showAndWait();
			}

		}
	}
	@FXML
	public void getIDHiger() {
		int id = Query_BuyBook.getIDHigher();
		tf_id.setText(String.valueOf(id + 1));
	}
	
	@FXML
	public void SaveBill() {
		
		int getValueId =  Integer.parseInt(cb_idCustomer.getValue());
		LocalDate date = dp_Time.getValue();
		float totalPrice = Float.parseFloat(tf_totalPrice.getText());
		int methodPay = Integer.parseInt(tf_methodPay.getText());
		int status = Integer.parseInt(tf_status.getText());
		
		Bill bill = new Bill(getValueId,date,totalPrice,methodPay,status);
		if(Query_BuyBook.addBill(bill)) {
			Alert alert = new Alert(AlertType.CONFIRMATION,"Thêm hoá đơn thành công");
			alert.showAndWait();
			getIDHiger();
		}else {
			Alert alert = new Alert(AlertType.ERROR,"Thêm hoá đơn thất bại");
			alert.showAndWait();
			
		}
	}

	@FXML
	public void clearTableView() {
		tv_BuyProducts.getItems().clear();
		int resetPrice = 0;
		tf_totalPrice.setText(String.valueOf(resetPrice));
	}

	@FXML
	public void addBillInfoToTable() {
		try {

			int id = Integer.parseInt(tf_id.getText());
			String getValueCB = cb_idBook.getValue();
			int idBook = Integer.parseInt(getValueCB);
			int quantity = Integer.parseInt(tf_quantity.getText());
			float price = Float.parseFloat(tf_price.getText());
			float discount = Float.parseFloat(tf_discount.getText());

			BillInfo newBillInfo = new BillInfo(0,id, idBook, quantity, price, discount);

			billInfoList.add(newBillInfo);
			Render_totalPrice();

		
			tf_price.clear();
			tf_discount.clear();
			cb_idBook.setValue(null);
			tf_quantity.clear();
		} catch (NumberFormatException e) {
			Alert a = new Alert(Alert.AlertType.ERROR, "Vui lòng điền đúng giá trị vào ô");
			a.showAndWait();
		} catch (NullPointerException e) {
			Alert a = new Alert(Alert.AlertType.ERROR, "Có lỗi xảy ra. Vui lòng kiểm tra lại thông tin.");
			a.showAndWait();
		}
	}


	// Method dành cho các Button
	public void ListenPayment() {
		btn_Payment.setOnAction(e -> {
			SaveBill();
			SaveBillInfo();

		});

	}

		
	@FXML
	public void listenExit() {
	    btn_exit.setOnAction(e -> {
	       
	        Stage currentStage = (Stage) btn_exit.getScene().getWindow();
	        currentStage.close();
	    });
	}

	public void listenClear() {
		btn_clearTableView.setOnAction(e -> {
			clearTableView();
		});
	}
	
}
