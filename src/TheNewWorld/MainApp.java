package TheNewWorld;

import TheNewWorld.model.Role;
import TheNewWorld.view.MainController;
import TheNewWorld.view.RoleController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class MainApp extends Application {

	public static String userDir;
	public FXMLLoader loader;
	public Parent parent;
	private Stage primaryStage;
	private Stage dialogStage;
	
	public MainApp() {
		userDir = System.getProperty("user.dir");
	}

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
			
			parent = loader.getRoot();
			
			parent.setOnKeyReleased(e->{
				TextField tf = (TextField) parent.lookup("#textFieldInput");
				if((!tf.isFocused()) && (e.getCode() == KeyCode.C)) {
					mc.showRoleDetail(mc.getTextFieldInput().getText());
				} 
			});
			
			primaryStage.setTitle("新世界-今日火爆开启");

			Image image = new Image(this.getClass().getResource("tnw.ico").toString(), 100, 150, false, false);
			primaryStage.getIcons().add(image);

			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
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
