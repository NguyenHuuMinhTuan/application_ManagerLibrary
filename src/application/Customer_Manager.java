package application;



import Model.Customer;
import Query_Selector.Query_Customer;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Customer_Manager {
	@FXML
	private TableView<Customer> tv_customer;
	
	@FXML
	private TextField tf_username, tf_phone, tf_email, tf_address,tf_memberShip,tf_search, tf_id;
	
	@FXML
	private Button btn_add,btn_edit,btn_del,btn_search,btn_exit;

	@FXML
	private TableColumn<Customer, Integer> col_id;
	@FXML
	private TableColumn<Customer, String> col_username;
	@FXML
	private TableColumn<Customer, String> col_email;
	@FXML
	private TableColumn<Customer, Integer> col_phone;
	@FXML
	private TableColumn<Customer, String> col_address;
	@FXML
	private TableColumn<Customer, String> col_type;
	@FXML
	Query_Customer customer;
	
	public void initialize() {
		col_id.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
		col_username.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUsername()));
		col_phone.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getPhone()).asObject());
		col_email.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
		col_address.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAddress()));
		col_type.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMemberShip()));
		
		Query_Customer.displayCustomer(tv_customer);
		renderItems();
		listenAdd();
		listenDelete();
		listenUpdate();
		listenSearch();
		exit();
	
	}
	public void exit() {
		btn_exit.setOnAction(e->{
			Stage stage = (Stage) btn_exit.getScene().getWindow();
			stage.close();
		});

	}
	public void addCustomer() {
		String username  = tf_username.getText().toString().trim();
		int phone_user = Integer.parseInt(tf_phone.getText());
		String email = tf_email.getText();
		String address = tf_address.getText().toString().trim();
		String memberShip = tf_memberShip.getText().toString().trim();
		Customer customer = new Customer(0,username,phone_user,email, address, memberShip);
		Query_Customer.AddCustomer(customer);
		Query_Customer.displayCustomer(tv_customer);
	
	}
	public void deleteCustomer() {
		int id = Integer.parseInt(tf_id.getText());
		customer.deleteCustomer(id);
		Query_Customer.displayCustomer(tv_customer);
	}
	public void UpdateCustomer() {
		String username  = tf_username.getText().toString().trim();
		int phone_user = Integer.parseInt(tf_phone.getText());
		String email = tf_email.getText();
		String address = tf_address.getText().toString().trim();
		String memberShip = tf_memberShip.getText().toString().trim();
		int id = Integer.parseInt(tf_id.getText());
		Customer cus = new Customer(0,username,phone_user,email,address,memberShip);
		customer.updateCustomer(cus, id);
		Query_Customer.displayCustomer(tv_customer);
		
	}
	public void renderItems() {
		tv_customer.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				tf_username.setText(newValue.getUsername());
				tf_phone.setText(String.valueOf(newValue.getPhone()));
				tf_email.setText(newValue.getEmail());
				tf_address.setText(newValue.getAddress());
				tf_memberShip.setText(newValue.getMemberShip());

			} else {
				Alert a = new Alert(Alert.AlertType.ERROR, "Giá trị rỗng");
				a.showAndWait();
			}

		});
	}
	public void reLoad() {
		tv_customer.getItems().clear();
		
		customer.displayCustomer(tv_customer);
	}
	public void searchCustomer() {
		String search = tf_search.getText();
		if(search.isEmpty()) {
			reLoad();
			Alert a = new Alert(AlertType.ERROR,"Làm ơn đừng có để trống !");
			a.showAndWait();
		}else
			tv_customer.getItems().clear();
			customer.searchBooks(tv_customer,search);
	}
	public void listenAdd() {
		btn_add.setOnAction(e->{
			addCustomer();
		});
	}
	public void listenDelete() {
		btn_del.setOnAction(e->{
			deleteCustomer();
		});
	}
	public void listenUpdate() {
		btn_edit.setOnAction( e->{
			UpdateCustomer();
		});
	}
	public void listenSearch() {
		btn_search.setOnAction(e ->{
			searchCustomer();
		});
	}
	

}
