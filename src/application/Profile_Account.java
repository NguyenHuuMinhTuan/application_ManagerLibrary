package application;

import Model.Account;
import Query_Selector.PasswordUtils;
import Query_Selector.Query_Profile;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Profile_Account {

	private String username;
	
	@FXML
	private TextField tf_username, tf_password,tf_email,tf_phone, tf_type;
	
	@FXML
	private Button btn_update,btn_resetPass,btn_avatar,btn_exit;
	
	@FXML
	public  Account account;
	

	
	public Profile_Account(String Name) {
		
		this.username = Name;
	}
	

	
	public void initialize() {
		account = Query_Profile.getProfileAccount(username);
		getData();
		
		listenUpdate();
		listenReset();
		listenExit();
	
	
	}
	public void listenUpdate() {
		btn_update.setOnAction(e->{
			update();
		
		});
	}
	public void listenReset() {
		btn_resetPass.setOnAction(e->{
			resetpass();
		});
	}
	public void resetpass() {
		String username = tf_username.getText();
		Query_Profile reset = new Query_Profile();
		boolean isReset = reset.resetPassword(username);
		if(isReset) {
			Alert a = new Alert(AlertType.CONFIRMATION,"Cập nhật mật khẩu thành công");
			a.showAndWait();
			getData();
		}
		else {
			Alert a = new Alert(AlertType.ERROR,"Cập nhật không thành công");
			a.showAndWait();
			
		}
	}
	public void update() {
	
		String username = tf_username.getText();
		String passWord = tf_password.getText();
		String password = PasswordUtils.hashPassword(passWord);
		String email = tf_email.getText();
		String phone = tf_phone.getText();
		
			Query_Profile update = new Query_Profile();
			boolean isUpdate = update.updateProfileAccount( password, email, phone,username);
			if(isUpdate) {
				Alert a = new Alert(AlertType.CONFIRMATION,"Cập nhật thành công");
				a.showAndWait();
				getData();
			}
			else {
				Alert a = new Alert(AlertType.ERROR,"Cập nhật không thành công");
				a.showAndWait();
				
			}

		
	}
	@FXML
	public void listenExit() {
	    btn_exit.setOnAction(e -> {
	       
	        Stage currentStage = (Stage) btn_exit.getScene().getWindow();
	        currentStage.close();
	    });
	}
	public void getData() {
		
		if(account!=null) {
			
			tf_username.setText(account.getUsername());
			tf_password.setText(account.getPassword());
			tf_email.setText(account.getEmail());
			tf_phone.setText(account.getPhone());
			int type = account.getType();
			tf_type.setText(String.valueOf(type));
		}else {
			Alert a = new Alert(AlertType.ERROR,"Tài khoản không tồn tại !");
			a.showAndWait();
			return;
		}
	}

}
