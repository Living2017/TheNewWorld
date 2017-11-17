package TheNewWorld.view;

import java.io.IOException;

import TheNewWorld.util.RoleUtil;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RoleCreatorController {
	

	@FXML
	private TextField name;
	@FXML
	private ComboBox<String> gender;
	@FXML
	private ComboBox<String> vocation;
	
	
	@FXML
	public void handelConfirmCreate() {
		try {
			RoleUtil ru = new RoleUtil();
			message = ru.generateRole(name.getText(), vocation.getValue(), gender.getValue());
			if(message.contains("创建成功")) {
				dialogStage.close();
			}else {
				name.setText(message);
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	private String message;
	private Stage dialogStage;

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ComboBox<String> getGender() {
		return gender;
	}

	public void setGender(ComboBox<String> gender) {
		this.gender = gender;
	}

	public ComboBox<String> getVocation() {
		return vocation;
	}

	public void setVocation(ComboBox<String> vocation) {
		this.vocation = vocation;
	}
	

	
}
