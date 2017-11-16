package TheNewWorld.view;

import TheNewWorld.model.Role;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class RoleController {
	
	@FXML
	private Label name;
	@FXML
	private Label gender;
	@FXML
	private Label vocation;
	@FXML
	private Label level;
	@FXML
	private Label life;
	@FXML
	private Label mana;
	@FXML
	private Label attack;
	@FXML
	private Label defense;
	@FXML
	private Label physique;
	@FXML
	private Label power;
	@FXML
	private Label nimble;
	@FXML
	private Label intelligence;
	@FXML
	private Button physiqueb;
	@FXML
	private Button powerb;
	@FXML
	private Button nimbleb;
	@FXML
	private Button intelligenceb;
	@FXML
	private Label attackDistance;
	@FXML
	private Label attackRate;
	@FXML
	private Label attackSpeed;
	@FXML
	private Label pace;
	@FXML
	private Label useful;
	
	@SuppressWarnings("unused")
	private Stage dialogStage;
	@SuppressWarnings("unused")
	private Role role;
	

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public void setRole(Role role2) {
		this.role = role2;
		
		 name            .setText(role2.getName());
		 gender          .setText(role2.getGender());
		 vocation        .setText(role2.getVocation());
		 level           .setText(role2.getLevel().toString());
		 life            .setText(role2.getLife().toString());
		 mana            .setText(role2.getMana().toString());
		 attack          .setText(role2.getAttack().toString());
		 defense         .setText(role2.getDefense().toString());
		 physique        .setText(role2.getPhysique().toString());
		 power           .setText(role2.getPower().toString());
		 nimble          .setText(role2.getNimble().toString());
		 intelligence    .setText(role2.getIntelligence().toString());
		 attackDistance  .setText(role2.getAttackDistance().toString());
		 attackRate      .setText(role2.getAttackRate().toString());
		 attackSpeed     .setText(role2.getAttackSpeed().toString());
		 pace            .setText(role2.getPace().toString());
		
	}

}
