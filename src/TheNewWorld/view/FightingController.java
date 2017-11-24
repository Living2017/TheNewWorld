package TheNewWorld.view;

import TheNewWorld.util.FightingThread;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class FightingController {

	@FXML
	private GridPane gp;
	@FXML
	private Button F1;
	@FXML
	private Button F2;
	
	int riF1;
	int ciF1;
	int riF2;
	int ciF2;
	
	boolean a =false;
	
	Thread dThread ;
	
	@FXML
	private void fighting() {
		
		
		FightingThread fightingThread = new FightingThread(gp, F1, F1, ciF1, ciF1, ciF1, ciF1);
		
		fightingThread.start();


	}
	

	
	
	public String message;
	@SuppressWarnings("unused")
	private Stage dialogStage;
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
}
