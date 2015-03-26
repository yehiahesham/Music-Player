package main;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;


public class Control implements Initializable
{
	
	//boolean f=false;
	String location= new File("C:\\Users\\yehia\\Documents\\Eclipse\\ALIENW Player\\src\\fxml\\sound\\Peter Gundry The Last Gypsy (Epic Big Violin Drama).mp3").toString();
	
	
	@FXML
	private Text song_name;
	@FXML
	private Button play_pause;
	@FXML
	private Button stop;
	@FXML
	private Button mute_button;
	@FXML
	private Button browser;
	@FXML
	private Button Forward_bt;
	@FXML
	private Button Backward_bt;
	@FXML
	private ListView<String> playlist;
	
	ObservableList listviewadder= FXCollections.observableArrayList();
	List<File> selectedFiles = new ArrayList<File>();
	List<String> selectedFiles_names = new ArrayList<String>();
	Media media;
	MediaPlayer player;
	String Location;
	ImageView pause	  = 	new ImageView ("fxml/images/player_pause.png");
	ImageView play	  =  	new ImageView ("fxml/images/player_play.png");
	ImageView muted	  =  	new ImageView ("fxml/images/Volume-mute-icon.png");
	ImageView unmuted =     new ImageView ("fxml/images/Volume-medium-icon.png");

	boolean first_time=true;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		play.setFitWidth(87);
		play.setFitHeight(87);
		pause.setFitWidth(87);
		pause.setFitHeight(87);
		muted.setFitWidth(80);
		muted.setFitHeight(70);
		unmuted.setFitWidth(80);
		unmuted.setFitHeight(70);
		song_name.setTextAlignment(TextAlignment.CENTER);
		mute_button.setGraphic(muted);
	}
	
	public void playMusic (ActionEvent event)
	{
		if(playlist.getItems().isEmpty())
			openfile(null);
		else
			Location = selectedFiles.get(playlist.getSelectionModel().getSelectedIndex()).toURI().toString();

		
		if(first_time)
		{	
			media = new Media(Location);
			player= new MediaPlayer(media);
			player.play();
			play_pause.setGraphic(pause);
			first_time=false;
			song_name.setText(playlist.getSelectionModel().getSelectedItem());
			
		}
		else 
		{
			
			if(player.getStatus().equals(player.statusProperty().get().PLAYING))
			{
				player.pause();
				play_pause.setGraphic(play);
			}
			
			else
			{
				player.play();
				play_pause.setGraphic(pause);
			}
			
		}
		
	}
	
	public void Stop(ActionEvent event)
	{		
		
		if(player.getStatus().equals(player.statusProperty().get().PLAYING))
			{
				player.stop();
				play_pause.setGraphic(play);
			}
		
		
	}
	
	public void un_mute(ActionEvent event)
	{
		if(player.isMute())
		{
			player.setMute(false);
			mute_button.setGraphic(muted);
		}
			
		else
		{	player.setMute(true);
			mute_button.setGraphic(unmuted);
		}
		
	}
	
	public void Forward(ActionEvent event)
	{
		player.stop();
		int temp=playlist.getSelectionModel().getSelectedIndex()+1;
		if(temp>=playlist.getItems().size())
			playlist.selectionModelProperty().get().select(0);
		else
			playlist.selectionModelProperty().get().select(temp);	
		Location = selectedFiles.get(playlist.getSelectionModel().getSelectedIndex()).toURI().toString();
		media = new Media(Location);
		player= new MediaPlayer(media);
		player.play();
		play_pause.setGraphic(pause);
		song_name.setText(playlist.getSelectionModel().getSelectedItem());
	}
	
	public void Backward(ActionEvent event)
	{
		player.stop();
		int temp=playlist.getSelectionModel().getSelectedIndex()-1;
		if(temp<0)
			playlist.selectionModelProperty().get().select(playlist.getItems().size()-1);
		else
			playlist.selectionModelProperty().get().select(temp);	
		Location = selectedFiles.get(playlist.getSelectionModel().getSelectedIndex()).toURI().toString();
		media = new Media(Location);
		player= new MediaPlayer(media);
		player.play();
		play_pause.setGraphic(pause);
		song_name.setText(playlist.getSelectionModel().getSelectedItem());
	}
	
	
	
	public void openfile(ActionEvent event)

	{
		/*     
		File[] selectedFiles2;
		JFileChooser fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(fc);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			selectedFiles2 = fc.getSelectedFiles();
		 	*/
		//setting up the Browse Window
		Stage arg0 = null;
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Browse for Audio File(s)");
		fileChooser.getExtensionFilters().addAll(
				new ExtensionFilter("Mp3 Files","*.mp3"),
				new ExtensionFilter("Wav Files","*.wav"),
				new ExtensionFilter( "aac Files","*.aac"),
				new ExtensionFilter("Audio Files", "*.*") 				
				);
		//getting the selected files in a list from the Browser
		selectedFiles = fileChooser.showOpenMultipleDialog(arg0);
		//inserting new items , no repeated Items. // make it a set we ry7 dem3'ak
		if(selectedFiles_names.size()==0)
			for(int j=0;j<selectedFiles.size();j++)
							selectedFiles_names.add(selectedFiles.get(j).getName());
		else
			{
			for(int j=0;j<selectedFiles.size();j++)
				if( selectedFiles_names.contains(selectedFiles.get(j).getName())==false)
					selectedFiles_names.add(selectedFiles.get(j).getName());
			}
		System.out.println(selectedFiles_names.size());
				//added it to the listview
		listviewadder=FXCollections.observableArrayList(selectedFiles_names);
			playlist.setItems(listviewadder);
			playlist.selectionModelProperty().get().select(0);
		
	}

	
	/*
	
	public void openfile(ActionEvent event)
	{
		Stage arg0 = null;
		DirectoryChooser chooser = new DirectoryChooser();
		chooser.setTitle("JavaFX Projects");
		//File defaultDirectory = new File("c:/dev/javafx");
		//chooser.setInitialDirectory(defaultDirectory);
		File c = chooser.showDialog(arg0);
		
		//inserting new items , no repeated Items. // make it a set we ry7 dem3'ak
		if(selectedFiles_names.size()==0)
			for(int j=0;j<selectedFiles.size();j++)
							selectedFiles_names.add(selectedFiles.get(j).getName());
		else
			{
			for(int j=0;j<selectedFiles.size();j++)
				if( selectedFiles_names.contains(selectedFiles.get(j).getName())==false)
					selectedFiles_names.add(selectedFiles.get(j).getName());
			}
		System.out.println(selectedFiles_names.size());
				//added it to the listview
		listviewadder=FXCollections.observableArrayList(selectedFiles_names);
			playlist.setItems(listviewadder);
			playlist.selectionModelProperty().get().select(0);
		
	}
	
	
	
	*/
	
	
	public void playvia_doubleclk(MouseEvent click)
	{
		if(click.getClickCount()==2 && click.getButton()==MouseButton.PRIMARY)
		{
			if(player.getStatus().equals(player.statusProperty().get().PLAYING))
				player.stop();
			System.out.println("********");
			System.out.println(playlist.getSelectionModel().getSelectedIndex());
			System.out.println(Location);
			Location = selectedFiles.get(playlist.getSelectionModel().getSelectedIndex()).toURI().toString();
			System.out.println(Location);
			System.out.println("********");
			media = new Media(Location);
			player= new MediaPlayer(media);
			player.play();
			play_pause.setGraphic(pause);
			song_name.setText(playlist.getSelectionModel().getSelectedItem());
		}
		
		  for(int j=0;j<selectedFiles.size();j++)
			{
				System.out.println(j);
				System.out.println(selectedFiles.get(j).getName());
				System.out.println(playlist.itemsProperty().get().get(j));
				System.out.println("-------------");
			}
			
		
		/*else if(click.getButton()==MouseButton.SECONDARY)
		{
			 ContextMenu cm = new ContextMenu();
			 MenuItem menuItem1 = new MenuItem("line 1");
		     MenuItem menuItem2 = new MenuItem("line 2");
		     MenuItem menuItem3 = new MenuItem("line 3");

		     cm.getItems().addAll(menuItem1, menuItem2, menuItem3);
		     Rectangle rectangle = new Rectangle(70, 70, Color.TAN);
		     	Group root = new Group();
		        root.getChildren().addAll(rectangle);
		        Scene options= new Scene(root, 350, 250);
		        Stage arg1 = new Stage();
		        arg1.setScene(options);
			    arg1.show();
			    cm.show(rectangle, click.getScreenX(), click.getScreenY());
			arg1.setWidth(10);
			arg1.setHeight(200);
			arg1.setX(click.getX());
			arg1.setY(click.getY());
			arg1.show();
			arg1.setResizable(false);
			
		}
		 */
	}

}
