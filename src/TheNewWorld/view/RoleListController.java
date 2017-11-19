package TheNewWorld.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import TheNewWorld.MainApp;
import TheNewWorld.util.RoleUtil;
import TheNewWorld.util.WorldUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class RoleListController {
	
	
  public  void init(){
		ArrayList<Label> arrayList = new ArrayList<Label>();
		arrayList.add(this.label00);
		arrayList.add(this.label10);
		arrayList.add(this.label20);
		arrayList.add(this.label30);
		arrayList.add(this.label40);
		arrayList.add(this.label50);
		arrayList.add(this.label60);
		arrayList.add(this.label70);

		arrayList.add(this.label01);
		arrayList.add(this.label11);
		arrayList.add(this.label21);
		arrayList.add(this.label31);
		arrayList.add(this.label41);
		arrayList.add(this.label51);
		arrayList.add(this.label61);
		arrayList.add(this.label71);

		arrayList.add(this.label02);
		arrayList.add(this.label12);
		arrayList.add(this.label22);
		arrayList.add(this.label32);
		arrayList.add(this.label42);
		arrayList.add(this.label52);
		arrayList.add(this.label62);
		arrayList.add(this.label72);

		arrayList.add(this.label03);
		arrayList.add(this.label13);
		arrayList.add(this.label23);
		arrayList.add(this.label33);
		arrayList.add(this.label43);
		arrayList.add(this.label53);
		arrayList.add(this.label63);
		arrayList.add(this.label73);

		arrayList.add(this.label04);
		arrayList.add(this.label14);
		arrayList.add(this.label24);
		arrayList.add(this.label34);
		arrayList.add(this.label44);
		arrayList.add(this.label54);
		arrayList.add(this.label64);
		arrayList.add(this.label74);

		arrayList.add(this.label05);
		arrayList.add(this.label15);
		arrayList.add(this.label25);
		arrayList.add(this.label35);
		arrayList.add(this.label45);
		arrayList.add(this.label55);
		arrayList.add(this.label65);
		arrayList.add(this.label75);

		arrayList.add(this.label06);
		arrayList.add(this.label16);
		arrayList.add(this.label26);
		arrayList.add(this.label36);
		arrayList.add(this.label46);
		arrayList.add(this.label56);
		arrayList.add(this.label66);
		arrayList.add(this.label76);

		arrayList.add(this.label07);
		arrayList.add(this.label17);
		arrayList.add(this.label27);
		arrayList.add(this.label37);
		arrayList.add(this.label47);
		arrayList.add(this.label57);
		arrayList.add(this.label67);
		arrayList.add(this.label77);


		
		HashMap<String, String> m=RoleUtil.roleNamePathMap;
		HashMap<String, String> n=RoleUtil.nameVocationMap;
		HashMap<String, String> g=RoleUtil.nameGenderMap;
		HashMap<String, String> w=WorldUtil.vocationColorMap;
		HashMap<String, String> c=WorldUtil.cnameMap;
		Set<String> set = m.keySet();
		int i=0;
		for (String string : set) {
			Label label=arrayList.get(i);
			label.setText(string);
			label.setOpacity(1);
			label.setOnMouseClicked(e->{
				name = string;
				dialogStage.close();
			});
			String png = g.get(string)+n.get(string);
			String imagePath = "file:"+MainApp.userDir.replace("\\", "/")+"/src/images/"+png+".png";
			String style = "-fx-opacity: 0.9;"
					+ "-fx-border-color:"+w.get(c.get(n.get(string)))
			+";-fx-background-image: url(\""+imagePath+"\");"
			+ "-fx-background-size: 35%;"
			+ "-fx-background-repeat:no-repeat;"
			+ "-fx-background-position:right;";
			label.setStyle(style);
			
			i++;
		}
		
	}
	 
	public String name; 

	public  Label getLabel00() {
		return label00;
	}

	public  void setLabel00(Label label00) {
		this.label00 = label00;
	}



	@FXML private Label label00;
	@FXML private Label label10;
	@FXML private Label label20;
	@FXML private Label label30;
	@FXML private Label label40;
	@FXML private Label label50;
	@FXML private Label label60;
	@FXML private Label label70;
	
	
	@FXML private Label label01;
	@FXML private Label label11;
    @FXML private Label label21;
    @FXML private Label label31;
    @FXML private Label label41;
    @FXML private Label label51;
    @FXML private Label label61;
    @FXML private Label label71;
	
	@FXML private Label label02;
    @FXML private Label label12;
    @FXML private Label label22;
    @FXML private Label label32;
    @FXML private Label label42;
    @FXML private Label label52;
    @FXML private Label label62;
    @FXML private Label label72;
	
	@FXML private Label label03;
    @FXML private Label label13;
    @FXML private Label label23;
    @FXML private Label label33;
    @FXML private Label label43;
    @FXML private Label label53;
    @FXML private Label label63;
    @FXML private Label label73;
	
	@FXML private Label label04;
    @FXML private Label label14;
    @FXML private Label label24;
    @FXML private Label label34;
    @FXML private Label label44;
    @FXML private Label label54;
    @FXML private Label label64;
    @FXML private Label label74;
	
	@FXML private Label label05;
    @FXML private Label label15;
    @FXML private Label label25;
    @FXML private Label label35;
    @FXML private Label label45;
    @FXML private Label label55;
    @FXML private Label label65;
    @FXML private Label label75;
	
	@FXML private Label label06;
    @FXML private Label label16;
    @FXML private Label label26;
    @FXML private Label label36;
    @FXML private Label label46;
    @FXML private Label label56;
    @FXML private Label label66;
    @FXML private Label label76;
	
	@FXML private Label label07;
    @FXML private Label label17;
    @FXML private Label label27;
    @FXML private Label label37;
    @FXML private Label label47;
    @FXML private Label label57;
    @FXML private Label label67;
    @FXML private Label label77;
    
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
}
