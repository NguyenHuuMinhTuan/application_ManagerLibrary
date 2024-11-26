package application;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import Model.Borrow;
import Model.BorrowInfo;
import Query_Selector.Query_Borrow;
import Query_Selector.Query_BuyBook;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Borrow_Book {
	 @FXML
	    private Button btn_exit, btn_add, btn_del, btn_payment;
	 @FXML
	 	private TextField ed_id, ed_quantity, ed_count;
	 @FXML
	 	private ComboBox<String> cb_idBook, cb_customer;
	 @FXML
	 	private DatePicker dp_dueDate ,dp_borrow, dp_due;
	 @FXML
	 	private ObservableList<BorrowInfo> borrowInfo ;
	 @FXML
	 	
	 	private TableView<BorrowInfo> tv_Borrow;
	 @FXML
	 	private TableColumn<BorrowInfo,Integer> col_id;
	 @FXML
	 	private TableColumn<BorrowInfo,Integer> col_idBook;
	 @FXML
	 	private TableColumn<BorrowInfo,Integer> col_quantity;
	 @FXML
	 	private TableColumn<BorrowInfo,String> col_dueDate;
	 @FXML
	 private ObservableList<BorrowInfo> borrowList;
	 @FXML
	 private RadioButton rd_status;
	 	

	   
	    @FXML
	    public void initialize() {
	    	
	    	borrowInfo = FXCollections.observableArrayList();
	    	borrowList = FXCollections.observableArrayList();
	    	tv_Borrow.setItems(borrowList);
	    	getHigherIdBorrrow();
	    	
	    	col_id.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getIdBorrow()).asObject());
	    	col_idBook.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getIdBook()).asObject());
	    	col_quantity.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getQuantity()).asObject());
	    	 col_dueDate.setCellValueFactory(cellData -> {
	    	        LocalDate dueDate = cellData.getValue().getDueDate();
	    	        if (dueDate != null) {
	    	            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	    	            return new javafx.beans.property.SimpleStringProperty(dueDate.format(formatter));
	    	        } else {
	    	            return new javafx.beans.property.SimpleStringProperty("");
	    	        }
	    	    });
	    	
	    	listenExit();
	    	listenAdd() ;
	    	 RenderID_Category();
	    	 RenderID_Customer();
	    	 ListenPayment() ;
	    	
	
	    }
	    public void getHigherIdBorrrow() {
	    	int idHiger = Query_Borrow.getIDHigher();
	    	ed_id.setText(String.valueOf(idHiger + 1 ));
	    }
	    public void saveBorrrow() {
	    	
	    	String getCustomer = cb_customer.getValue();
	    	int id_customer = Integer.parseInt(getCustomer);
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

			cb_customer.setItems(FXCollections.observableArrayList(idCustomer));
		}
		@FXML
		public void getQuantity() {
			int count = 0;
			for(BorrowInfo br : tv_Borrow.getItems()) {
				count += br.getQuantity();
			}
			ed_count.setText(String.valueOf(count));
		}
		
	    @FXML
	    public void AddBorrowTable() {
	    
	    	
	    	int id = Integer.parseInt(ed_id.getText());
	    	
	    	String getIdBook = String.valueOf(cb_idBook.getValue());
	    	
	    	int idBook = Integer.parseInt(getIdBook);
	    	
	    	int quantity = Integer.valueOf(ed_quantity.getText());
	    	
	    	LocalDate dueDate = dp_dueDate.getValue();
	    	
	    	BorrowInfo br = new BorrowInfo(0, id, idBook, quantity, dueDate);
	    	borrowList.add(br);
	    	getQuantity();
	    }
	    public void addBorrow() {
	    	if (cb_customer.getValue() == null) {
	    	    Alert alert = new Alert(Alert.AlertType.WARNING);
	    	    alert.setTitle("Cảnh báo");
	    	    alert.setHeaderText(null);
	    	    alert.setContentText("Vui lòng chọn khách hàng!");
	    	    alert.showAndWait();
	    	    return; 
	    	}
	    	
	    	String getIDCustomer = cb_customer.getValue();
	    	int idCustomer = Integer.parseInt(getIDCustomer);
	    	
	    	int quantity = Integer.parseInt(ed_count.getText());  
	    	LocalDate borrowDate = dp_borrow.getValue();
	    	LocalDate dueDate = dp_dueDate.getValue();
	    	int status = rd_status.isSelected() ? 1 : 0;
	    	
	    	Borrow borrow = new Borrow(0,idCustomer,borrowDate,dueDate,status);
	    	Query_Borrow.addBorrow(borrow);
	    	
	    	
	    }
	    public void ListenPayment() {
	    	btn_payment.setOnAction(e-> {
	    		Alert a = new Alert(AlertType.CONFIRMATION);
	    		a.setTitle("Xác nhận thanh toán");
	    		a.setHeaderText("Bạn có chắc chắn muốn thanh toán ?");
	    		a.setContentText("Hãy xác nhận để hoàn thành quá trình thanh toán !");
	    		
	    		ButtonType result = a.showAndWait().orElse(ButtonType.CANCEL);
	    		if(result == ButtonType.OK) {
	    			addBorrow();
		    		addBorrowInfo();
	    		}else {
	    			System.out.println("Thanh toán đã bị huỷ ");
	    			return ;
	    		}
	    	
	    	});
	    }
	

	    // Method to handle the exit action
	    public void exit() throws IOException {
	       
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu_Staff.fxml"));

			Parent root = loader.load();

			Stage stage = new Stage();
			stage.setTitle("Menu Staff");

			Scene scene = new Scene(root);
			stage.setScene(scene);

			stage.show();

			Stage currentStage = (Stage) btn_exit.getScene().getWindow();
			currentStage.hide();
	    }
	    public void addBorrowInfo() {
	    	ObservableList<BorrowInfo> borrowList = tv_Borrow.getItems();
	    	for(BorrowInfo borrowInfo : borrowList) {
	    		int idBorrow = borrowInfo.getIdBook();
	    		int idBook = borrowInfo.getIdBook();
	    		int quantity = borrowInfo.getQuantity();
	    		LocalDate due = borrowInfo.getDueDate();
	    		try {
	    			BorrowInfo borrowInfo2 = new BorrowInfo(0, idBorrow, idBook, quantity, due);
	    		
	    		
	    			Query_Borrow.addBillInfo(borrowInfo2);
	    		}catch(Exception e) {
	    			e.getStackTrace();
	    		}
	    		
	    	}
	    }
	   

	    public void listenExit() {
	        btn_exit.setOnAction(e -> {
	            try {
	                exit();
	            } catch (IOException ex) {
	                ex.printStackTrace(); 
	            }
	        });
	        
	    }
	    public void listenAdd() {
	    	btn_add.setOnAction(e->{
	    		AddBorrowTable();
	    	});
	    }


}
