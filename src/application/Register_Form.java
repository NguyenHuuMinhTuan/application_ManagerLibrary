package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Register_Form {
	@FXML 
	private Button btn_Cancel, btn_Confirm, btn_Return;
	
	@FXML
	private TextField txt_username, txt_password, txt_confirmPassword, txt_Email, txt_Phone;
	
	@FXML
	void Return() throws Exception {
		

		FXMLLoader loader = new FXMLLoader(getClass().getResource("Login_Form.fxml"));
		Parent root = loader.load();

		Stage stage = new Stage();
		stage.setTitle("New Scene");

		Scene scene = new Scene(root);
		stage.setScene(scene);

		stage.show();
		
		Stage currentStage = (Stage) btn_Return.getScene().getWindow();
		currentStage.hide();
		
	}

}
