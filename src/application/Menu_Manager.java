package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Menu_Manager {
	
	@FXML 
	private TextField tf_username;
	
	@FXML
	private Button btn_Buy, btn_Borrow, btn_BillInfo, btn_ManagerBook, btn_Revenue, btn_CustomerManager, btn_Profile, btn_Exit, btn_encription;
	@FXML 
	private String Name;
	
	public Menu_Manager(String Name) {
		this.Name = Name;
		
	}
	public void initialize() {

		tf_username.setText(Name);
		changeSection();
		
	
	}
	@FXML
	public void Manager_Menu_Scene() throws Exception {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("Profile_Account.fxml"));

		Profile_Account menu = new  Profile_Account(Name);
		
		loader.setController(menu);

		Parent root = loader.load();

		Stage stage = new Stage();
		stage.setTitle("Profile Account");

		Scene scene = new Scene(root);
		stage.setScene(scene);

		stage.show();

		Stage currentStage = (Stage) btn_Profile.getScene().getWindow();
		currentStage.close();
	}
	public void changeSection() {
		btn_Buy.setOnAction(e -> {
			loadFXMLScene("Buy_Book.fxml");

		});
		btn_Borrow.setOnAction(e -> {
			loadFXMLScene("Borrow_Book.fxml");

		});
		btn_Profile.setOnAction(e->{
			try {
				Manager_Menu_Scene();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});


		btn_BillInfo.setOnAction(e -> {
			loadFXMLScene("Bill_Info.fxml");

		});
		btn_CustomerManager.setOnAction(e -> {
			loadFXMLScene("Customer_Manager.fxml");

		});
		btn_Revenue.setOnAction(e -> {
			loadFXMLScene("Revenue.fxml");

		});
		btn_Exit.setOnAction(e->{
			loadFXMLScene("Login_Form.fxml");
		});
		btn_ManagerBook.setOnAction(e->{
			 loadFXMLScene("Book_Manager.fxml");
		 });
		btn_encription.setOnAction(e->{
			loadFXMLScene("Encription.fxml");
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
