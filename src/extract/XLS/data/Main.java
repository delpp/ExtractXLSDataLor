package extract.XLS.data;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application{
	
	public void start(Stage stage) throws IOException{
		
		ViewLoader<AnchorPane, Object> viewLoader =	new ViewLoader<AnchorPane, Object>("view/window.fxml");

		AnchorPane anchorPane = viewLoader.getLayout();

		Scene scene = new Scene(anchorPane);
		stage.setScene(scene);
		
		stage.show();
	}
	
	public static void main(String args[]){
		launch();
	}
}