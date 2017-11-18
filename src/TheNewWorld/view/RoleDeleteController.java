package TheNewWorld.view;

import TheNewWorld.util.RoleUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class RoleDeleteController {

	@FXML
	private Label roleDelete;
	@FXML
	private Button confirmDelete;
	
	@FXML
	private void confirmDelete() {
		if(RoleUtil.delteRole(roleDelete.getText())) {
			message = "角色["+roleDelete.getText()+"]删除成功";
		}else {
			message = "角色["+roleDelete.getText()+"]删除失败";
		}
		dialogStage.close();
	}
	
	public String message;
	private Stage dialogStage;
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	public Label getRoleDelete() {
		return roleDelete;
	}
	public void setRoleDelete(Label roleDelete) {
		this.roleDelete = roleDelete;
	}
	


}
