package TheNewWorld;
	
import TheNewWorld.view.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;


public class MainApp extends Application {
	
	public static String userDir;
	
	public MainApp() {
		userDir = System.getProperty("user.dir");
	}
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class
                    .getResource("view/Main.fxml"));
			AnchorPane anchorPane = loader.load();
			Scene scene = new Scene(anchorPane);
			primaryStage.setScene(scene);
			MainController mc = loader.getController();
			mc.setMa(this);
			primaryStage.setTitle("新世界-今日火爆开启");
			
			Image image= new Image(this.getClass().getResource("tnw.ico").toString(), 100, 150, false, false);
			primaryStage.getIcons().add(image);
			
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
