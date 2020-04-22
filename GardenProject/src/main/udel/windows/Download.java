package main.udel.windows;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import main.udel.enums.Windows;
import main.udel.garden.Model;

/**
 * Where the user can export their plot as a PNG/JPEG, save it as a
 * <code>.gardenproject</code> file, load a saved project, or exit.
 * 
 * @author Team 0
 */
public class Download extends Window {
	
	private Group root;
	private Scene scene;
	
	private BorderPane borderPane;
	private VBox vbox;
	private Text text;
	
	private static final String downloadSceneTitle = "Load or Save Your Plot";
	private String lastSaveFile;
	
	private Button back;

	public Download(Model m) {
		super(m, downloadSceneTitle);
		this.lastSaveFile = "";
		
		borderPane = new BorderPane();
		vbox = new VBox();
		
		text = new Text("Congrats! You've created your Garden! How would you like to save?");
		text.setWrappingWidth(800);
		text.setStyle("-fx-font-size: 20px;");
		vbox.getChildren().addAll(text);
		
		back = new Button("Go Back");
		back.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
            	switchToWindow(Windows.Questionnaire);
            }
        });
		
		borderPane.setTop(vbox);
		borderPane.setLeft(back);
		
		this.root = new Group();
		root.getChildren().add(borderPane);
		this.scene = new Scene(this.root);
	}
	
	public Download(Model m, String lastSaveFile) {
		super(m, downloadSceneTitle);
		this.lastSaveFile = lastSaveFile;		
		
		
		
	}
	
	@Override
	public Scene getScene() {
		// TODO Auto-generated method stub
		return this.scene;
	}
	
	/**
	 * saves the PNG plant 
	 * @return true if it saved and false if it did not
	 */
	public boolean savePNG(String filename) {
		return false;
	}
	
	/**
	 * saves the plant
	 * @return true if saved and false if it did not
	 */
	public boolean save(String filename) {
		return false;
	}
	
	/**
	 * loads the plotdesign of a window
	 * @return the plotdesign desired
	 */
	public PlotDesign load() {
		return null;
	}
	
	/**
	 * exits
	 */
	public void exit() {
		
	}
	
}
