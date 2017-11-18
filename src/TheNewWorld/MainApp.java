package TheNewWorld;

import java.util.Collection;
import java.util.HashMap;

import com.sun.crypto.provider.RC2Cipher;

import TheNewWorld.model.Role;
import TheNewWorld.util.RoleUtil;
import TheNewWorld.util.WorldUtil;
import TheNewWorld.view.MainController;
import TheNewWorld.view.RoleController;
import TheNewWorld.view.RoleCreatorController;
import TheNewWorld.view.RoleListController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

@SuppressWarnings("unused")
public class MainApp extends Application {

	public static String userDir;
	public FXMLLoader loader;
	public Parent parent;
	private Stage primaryStage;
	private Stage dialogStage;
	private static final int FIXED_HEIGHT = 483;
	private static final int FIXED_WIDTH = 910;
	private static final int FIXED_X = 43;
	private static final int FIXED_Y = 118;
	private static final int TA_MARGIN_LEFT = FIXED_X;
	private static String  combinationKey1;
	private static String  combinationKey2;
	private static TextArea ta ;
	private static TextField tf;
	
	static{
		userDir = System.getProperty("user.dir");
	}
	

	@SuppressWarnings("static-access")
@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		try {
			
			loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/Main.fxml"));
			AnchorPane anchorPane = loader.load();
			Scene scene = new Scene(anchorPane);
			primaryStage.setScene(scene);
			
			MainController mc = loader.getController();
			mc.setMa(this);
			
			mc.getRole1().setOnMouseClicked(ee->{
				String role1 =mc.getRole1().getText();
				if(mc.getRole1().getText().contains(":")) {
					mc.showRoleDetail(role1.split("\\:")[1]);
				}
					
			});
			parent = loader.getRoot();
			
			parent.setOnMouseMoved(e->{
				
				
				//ta.setPrefWidth(primaryStage.getWidth()-TA_MARGIN_LEFT*2);
				//ta.autosize();
				if(true) {
					return;
				}
				ta.appendText("\r\n");
				ta.appendText("primaryStage.getMaxWidth()="+primaryStage.getMaxWidth());
				ta.appendText("\t");
				ta.appendText("primaryStage.getMinWidth()="+primaryStage.getMinWidth());
				ta.appendText("\t");
				ta.appendText("primaryStage.getWidth()="+primaryStage.getWidth());
				ta.appendText("\r\n");
				ta.appendText("primaryStage.getMaxHeight()="+primaryStage.getMaxHeight());
				ta.appendText("\t");
				ta.appendText("primaryStage.getMinHeight()="+primaryStage.getMinHeight());
				ta.appendText("\t");
				ta.appendText("primaryStage.getHeight()="+primaryStage.getHeight());
				
				
				if(true) {
					return;
				}
				ta.appendText("\r\n");
				ta.appendText("primaryStage.getX()="+primaryStage.getX());
				ta.appendText("\r\n");
				ta.appendText("primaryStage.getY()="+primaryStage.getY());
				
				if(true) {
					return;
				}
				parent.maxHeight(1);
				ta.appendText("\r\n");
				ta.appendText("parent.maxHeight(1)="+parent.maxHeight(1));
				ta.appendText("\t");
				ta.appendText("parent.maxHeight(200)="+parent.maxHeight(200));
				ta.appendText("\r\n");
				ta.appendText("parent.maxWidth(1)="+parent.maxWidth(1));
				ta.appendText("\t");
				ta.appendText("parent.prefHeight(200)="+parent.prefHeight(200));
				ta.appendText("\t");
				ta.appendText("parent.prefWidth(200)="+parent.prefWidth(200));
				
				if(true) {
					return;
				}
				ta.appendText("\r\n");
				ta.appendText("parent.getLayoutX()="+parent.getLayoutX());
				ta.appendText("\t");
				ta.appendText("parent.getLayoutY()="+parent.getLayoutY());
				ta.appendText("\t");
				ta.appendText("parent.getScaleX()="+parent.getScaleX());
				ta.appendText("\t");
				ta.appendText("parent.getScaleY()="+parent.getScaleY());
				ta.appendText("\t");
				ta.appendText("parent.getScaleZ()="+parent.getScaleZ());
				ta.appendText("\r\n");
				ta.appendText("parent.getTranslateX()="+parent.getTranslateX());
				ta.appendText("\t");
				ta.appendText("parent.getTranslateY()="+parent.getTranslateY());
				ta.appendText("\t");
				ta.appendText("parent.getTranslateZ()="+parent.getTranslateZ());
				
				
				if(true) {
					return;
				}
				ta.appendText("\r\n");
				ta.appendText("e.getX()="+e.getX());
				ta.appendText("\t");
				ta.appendText("e.getY()="+e.getY());
				ta.appendText("\t");
				ta.appendText("e.getZ()="+e.getZ());
				ta.appendText("\t");
				ta.appendText("e.getSceneX()="+e.getSceneX());
				ta.appendText("\t");
				ta.appendText("e.getSceneY()="+e.getSceneY());
				ta.appendText("\t");
				ta.appendText("e.getScreenX()="+e.getScreenX());
				ta.appendText("\t");
				ta.appendText("e.getScreenY()="+e.getScreenY());
			});
			
			
			parent.setOnKeyPressed(e->{
				if(e.isControlDown()) {
					combinationKey1 = "ControlDown";
				}
				
			});
			
			parent.setOnKeyReleased(e->{
				KeyCode kc = e.getCode();
				Integer keyNum = null;
				
				if(kc == KeyCode.ENTER) {
					tf  = mc.getTextFieldInput();
						String txt = tf.getText();
					if(tf.isFocused()) {
						ta.requestFocus();
					}else {
						tf.requestFocus();
					}
					mc.init();
					HashMap<String, String> r =RoleUtil.roleNamePathMap;
					if(r.containsKey(txt)) {
						mc.getRole1().setText(mc.getRole1().getText().split("\\:")[0]+":"+txt);
						
						tf.setText("");
					}
				}
				
				
				if(!e.isControlDown()) {
					combinationKey1 = null;
				}
				
				if(ta.isFocused()) {
					try {
						keyNum = Integer.valueOf(kc.getName());
					} catch (Exception e2) {
					}
					if(keyNum == null) {
						if(kc == KeyCode.C) {
							mc.showRoleDetail(mc.getRole1().getText().split("\\:")[1]);
						}else if("ControlDown".equals(combinationKey1) && kc==KeyCode.S){
							mc.saveInfo(ta.getText());
							ta.appendText(mc.getShowInfo());
						}
						
					}else {
						if(keyNum == 0){
							ta.setPrefHeight(FIXED_HEIGHT);
							ta.setLayoutY(FIXED_Y);
						}else {
							ta.setPrefHeight(FIXED_HEIGHT*0.1*keyNum);
							ta.setLayoutY(FIXED_Y+FIXED_HEIGHT*0.1*(10-keyNum));
						}
					}
					
				}
				
			});
			
			primaryStage.setTitle("新世界-今日火爆开启");

			Image image = new Image(this.getClass().getResource("tnw.ico").toString(), 100, 150, false, false);
			primaryStage.getIcons().add(image);

			scene.getStylesheets().add  
			 (MainApp.class.getResource("application.css").toExternalForm());
			
			//primaryStage.initStyle(StageStyle.TRANSPARENT);
			//primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.show();
			ta = (TextArea) parent.lookup("#textAreaShow");
			tf = (TextField) parent.lookup("#textFieldInput");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	public String handleRoleList(){
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApp.class.getResource("view/RoleList.fxml"));
		try {
			GridPane gp = (GridPane) loader.load();
			dialogStage = new Stage();
			dialogStage.setTitle("角色列表");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(gp);
			
			scene.getStylesheets().add  
			(MainApp.class.getResource("roleList.css").toExternalForm());
			
			dialogStage.setScene(scene);
			
			RoleListController rlc = loader.getController();
			rlc.setDialogStage(dialogStage);
			
			parent = loader.getRoot();
			
			dialogStage.setOnShown(e->{
				rlc.init();
			});
			
			dialogStage.setResizable(false);
			dialogStage.setAlwaysOnTop(false);
			
			dialogStage.showAndWait();
			return rlc.name;
		} catch (Exception e) {
			e.printStackTrace();
			return e.getLocalizedMessage();
		}
	}

	public String CreateRoleDetail(){
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApp.class.getResource("view/RoleCreator.fxml"));
		try {
			GridPane gp = (GridPane) loader.load();
			dialogStage = new Stage();
			dialogStage.setTitle("创建人物");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(gp);
			
			scene.getStylesheets().add  
			 (MainApp.class.getResource("roleCreator.css").toExternalForm());
			
			dialogStage.setScene(scene);
			
			RoleCreatorController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			dialogStage.setResizable(false);
			dialogStage.setAlwaysOnTop(false);
			
			controller.getGender().getItems().addAll("男","女");
			controller.getGender().setValue("男");
			
			Collection<String> strings = WorldUtil.nameMap.values();
			Object[] objects=strings.toArray();
			String[] vocation = new String[objects.length] ;
			for (int i = 0; i < objects.length; i++) {
				vocation[i] = (String) objects[i];
			}
			controller.getVocation().getItems().addAll(vocation);
			controller.getVocation().setValue(vocation[0]);
			
			dialogStage.showAndWait();
			return controller.getMessage();
		} catch (Exception e) {
			e.printStackTrace();
			return e.getLocalizedMessage();
		}
	}
	
	public void ShowRoleDetail(Role role) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApp.class.getResource("view/Role.fxml"));
		try {
			GridPane gp = (GridPane) loader.load();
			
			gp.setOnKeyReleased(e->{
				if(e.getCode() == KeyCode.C) {
					dialogStage.close();
				}
			});
			
			// Create the dialog Stage.
			dialogStage = new Stage();
			dialogStage.setTitle("人物属性");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(gp);
			
			scene.getStylesheets().add  
			 (MainApp.class.getResource("role.css").toExternalForm());
			
			dialogStage.setScene(scene);

			// Set the person into the controller.
			RoleController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setRole(role);
			dialogStage.setResizable(false);
			dialogStage.setAlwaysOnTop(false);
			
			dialogStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
