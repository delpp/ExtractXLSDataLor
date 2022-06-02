package extract.XLS.data;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

@SuppressWarnings("restriction")
public class Start extends Application{
	
	@Override
	public void start(Stage stage) throws IOException{
		
		//ViewLoader<AnchorPane, Object> viewLoader =	new ViewLoader<AnchorPane, Object>("view/window.fxml");

		
		//AnchorPane anchorPane = viewLoader.getLayout();
		
		FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("view/window.fxml"));
		
		//System.out.println(fxmlLoader.load().toString());
		
		AnchorPane anchorPane = fxmlLoader.load();
			
		Scene scene = new Scene(anchorPane);
				
		stage.setScene(scene);
		

		stage.show();
	}
	
	public static void main(String args[]){
		launch();
	}
}