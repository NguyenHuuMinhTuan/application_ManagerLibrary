package application;

import 	Query_Selector.PasswordUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Encription {
	@FXML 
	private Button btn_stranslate;
	
	@FXML
	private TextField tf_ecrip, tf_pass;
	
	public void initialize() {
		listenTranslate();
	}
	@FXML
	public void listenTranslate() {
		btn_stranslate.setOnAction(e->{
			Translate();
		});
	}
	public void Translate() {
		String ecrip = tf_ecrip.getText();
		String change = PasswordUtils.hashPassword(ecrip);
		System.out.println(change);
		
		tf_pass.setText(change);
	}


}
