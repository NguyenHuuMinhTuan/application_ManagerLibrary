package application;

import Query_Selector.Query_forgetPass;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ForgetPassword {
	@FXML
	private TextField tf_username,tf_email,tf_newPass,tf_confirmPass;
	@FXML
	private Button btn_changePass, btn_exit;
	
	public void initialize() {
		listenExit();
		 listenChange();
	}
	public void listenChange() {
		btn_changePass.setOnAction(e-> {
			changePass();
		});
	}
	public void listenExit() {
		btn_exit.setOnAction(e-> {
			loadFXMLScene("Login_Form.fxml");
			Stage stage = ( Stage) btn_exit.getScene().getWindow();
			stage.close();
		});
	}
	public void changePass() {
		String username = tf_username.getText().toString().trim();
		String email = tf_email.getText().toString().trim();
		String newPass = tf_newPass.getText().toString().trim();
		String confirm = tf_confirmPass.getText().toString().trim();
		if(username.isEmpty() || email.isEmpty() || newPass.isEmpty() || confirm.isEmpty()) {
			Alert a = new Alert(Alert.AlertType.ERROR,"Vui lòng điền đầy đủ thông tin ");
			a.showAndWait();
			return;
		}else if(!newPass.equals(confirm)) {
			Alert a = new Alert(Alert.AlertType.ERROR,"Mật khẩu và xác nhận mật khẩu không khớp ! Vui lòng nhập lại ! ");
			a.showAndWait();
			return;
		}
		else {
			Query_forgetPass.forgetPass(username, email, newPass);
			loadFXMLScene("Login_Form.fxml");
		}
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
