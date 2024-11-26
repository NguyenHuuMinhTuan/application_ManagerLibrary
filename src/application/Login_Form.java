package application;

import Query_Selector.Query_Login;
import Query_Selector.Query_getType;
import javafx.fxml.*;

import javafx.scene.control.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.control.Hyperlink;

public class Login_Form {
	@FXML
	private TextField txt_username;
	
	@FXML
	private PasswordField txt_password;

	@FXML
	private Button btn_login, btn_register, btn_exit;

	@FXML
	private Hyperlink hp_forgetPassword;
	
	@FXML
	public void initialize() {
		forgetChange();
	
		
	}
	
	@FXML
	void forgetChange() {
		hp_forgetPassword.setOnAction(e->{
			try {
				forgetPassword();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
	}

	@FXML
	void login() {
		btn_login.setOnAction(event -> {
			if (txt_username.toString().isEmpty() || txt_password.toString().isEmpty()) {
				Alert a = new Alert(Alert.AlertType.ERROR, "Không được để trống tên tài khoản hoặc mật khẩu !");
				a.showAndWait();
			} else {
				String username = txt_username.getText();
				String password = txt_password.getText();

				boolean isLoggedIn = checkLogin(username, password);

				if (isLoggedIn) {
					Alert alert = new Alert(Alert.AlertType.INFORMATION, "Đăng nhập thành công!");
					alert.showAndWait();
					try {
						int type = Query_getType.getType(username);

						if (type != 1) {

							Menu_Staff_Scene();

						} else {
							Manager_Menu_Scene();

						}

					} catch (Exception e) {
						e.printStackTrace();
					}

				} else {
					Alert alert = new Alert(Alert.AlertType.ERROR, "Sai tài khoản hoặc mật khẩu!");
					alert.showAndWait();
				}

			}

		});

	};

	private boolean checkLogin(String username, String password) {
		return Query_Login.checkLogin(username, password);
	}

	@FXML
	public void register() throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Book_Manager.fxml"));

		Parent root = loader.load();

		Stage stage = new Stage();
		stage.setTitle("Book Managerment");

		Scene scene = new Scene(root);
		stage.setScene(scene);

		stage.show();

		Stage currentStage = (Stage) btn_login.getScene().getWindow();
		currentStage.hide();

	}

	@FXML
	void Exit() {
		System.exit(0);
	}

	@FXML
	public void forgetPassword() throws Exception {
	    
	    FXMLLoader loader = new FXMLLoader(getClass().getResource("ForgetPassword.fxml"));
	    Parent root = loader.load();

	  
	    Stage stage = new Stage();
	    stage.setTitle("Quên Mật Khẩu"); 
	    stage.setScene(new Scene(root));

	  
	    stage.show();

	 
	    Stage currentStage = (Stage) hp_forgetPassword.getScene().getWindow();
	    currentStage.hide();
	}


	@FXML
	public void Manager_Menu_Scene() throws Exception {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu_Manager.fxml"));

		String NameAccount = txt_username.getText();

		Menu_Manager menu = new  Menu_Manager(NameAccount);
		
		loader.setController(menu);

		Parent root = loader.load();

		Stage stage = new Stage();
		stage.setTitle("Menu Admin");

		Scene scene = new Scene(root);
		stage.setScene(scene);

		stage.show();

		Stage currentStage = (Stage) btn_login.getScene().getWindow();
		currentStage.close();
	}

	@FXML
	public void Menu_Staff_Scene() throws Exception {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu_Staff.fxml"));

		String Name_Login = txt_username.getText();

		Menu_Staff staff = new Menu_Staff(Name_Login);

		loader.setController(staff);

		Parent root = loader.load();

		Stage stage = new Stage();
		stage.setTitle("Menu Staff");

		Scene scene = new Scene(root);
		stage.setScene(scene);

		stage.show();

		Stage currentStage = (Stage) btn_login.getScene().getWindow();
		currentStage.hide();
	}

}
