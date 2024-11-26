package application;

import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Menu_Staff {
	@FXML
	private TextField tf_username;

	@FXML
	private Button btn_buyBook, btn_borrowBook, btn_ProfileAccount, btn_customer, btn_exit;
	
	@FXML
	private String value;

	public Menu_Staff(String value) {
		this.value = value;

	}

	public void initialize() {

		tf_username.setText(value);
		changeSection();
	}

	public void changeSection() {
		btn_buyBook.setOnAction(e -> {
			loadFXMLScene("Buy_Book.fxml");

		});
		btn_borrowBook.setOnAction(e -> {
			loadFXMLScene("Borrow_Book.fxml");

		});
		btn_ProfileAccount.setOnAction(e -> {
			loadFXMLScene("Profile_Account.fxml");

		});
		btn_customer.setOnAction(e -> {
			loadFXMLScene("Customer_Manager.fxml");

		});
		btn_exit.setOnAction(e->{
			loadFXMLScene("Login_Form.fxml");
		});
	}

	private void loadFXMLScene(String fxmlFile) {
	    try {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
	        Parent root = loader.load();
	        
	        Stage newStage = new Stage();
	        newStage.setScene(new Scene(root));
	        newStage.centerOnScreen();
	        
	        newStage.show();
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}
