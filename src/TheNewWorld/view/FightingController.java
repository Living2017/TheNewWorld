package TheNewWorld.view;

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
	
	@SuppressWarnings("static-access")
	@FXML
	private void fighting() {
		int riF1 = gp.getRowIndex(F1);
		int ciF1 = gp.getColumnIndex(F1);
		int riF2 = gp.getRowIndex(F2);
		int ciF2 = gp.getColumnIndex(F2);
		
		if((riF1==riF2&&Math.abs(ciF1-ciF2)==1)
				|| (ciF1==ciF2&&Math.abs(riF1-riF2)==1)) {
			return;
		}
		
		
		
		
		if(riF1<12) {
			gp.setRowIndex(F1,riF1+1);
		}else if(riF1>12) {
			gp.setRowIndex(F1,riF1-1);
		}
		
		else if(ciF1<12) {
			gp.setColumnIndex(F1,ciF1+1);
		}
		else if(ciF1>12) {
			gp.setColumnIndex(F1,ciF1-1);
		}
		
		if(riF2<12) {
			gp.setRowIndex(F2,riF2+1);
		}else if(riF2>12) {
			gp.setRowIndex(F2,riF2-1);
		}
		
		else if(ciF2<12) {
			gp.setColumnIndex(F2,ciF2+1);
		}
		else if(ciF2>12) {
			gp.setColumnIndex(F2,ciF2-1);
		}

	}
	
	
	public String message;
	@SuppressWarnings("unused")
	private Stage dialogStage;
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
}
