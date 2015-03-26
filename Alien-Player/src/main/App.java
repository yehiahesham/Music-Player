package main;

import java.io.File;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class App extends Application 
{
	File[] allFiles;
int fileindex = 0;
String ssound;//getClass().getResource("/fxml/sound/Peter Gundry The Last Gypsy (Epic Big Violin Drama).mp3").toString();
Media media ;
MediaPlayer x;

	public static void main(String[] args)
	{
		launch(args);

	}
	
	
	@Override
	public void start(Stage arg0) throws Exception 
	{
		Parent root = FXMLLoader.load(getClass().getResource("/fxml/MAIN.fxml"));
		Scene scene= new Scene(root);
		arg0.setScene(scene);
		arg0.getIcons().add(new Image("/fxml/images/1421038171_Alienware.png"));
		arg0.setTitle("ALIENW Player");
		arg0.setResizable(false);
		arg0.show();
		
	}

}
