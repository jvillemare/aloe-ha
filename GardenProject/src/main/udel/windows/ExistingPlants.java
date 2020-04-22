package main.udel.windows;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import main.udel.enums.Windows;
import main.udel.garden.Model;
import main.udel.plants.Plant;

/**
 * Where a user can specify what plants already exist in their garden.
 * 
 * @author Team 0
 */
public class ExistingPlants extends Window {

	private Group root;
	private Scene scene;
	private BorderPane borderPane;
	private VBox vbox;

	private Plant existingPlant[];
	
	private Button backToMain;
	private Button nextButton;
	
	private GridPane container;
	private HBox searchBox;
	
	private Text text1;
	
	public ExistingPlants(Model m) {
		super(m, "What Plants are in your Garden?");
		
		
		
		container = new GridPane();
		searchBox = new HBox();
		
		// this is just for testing
		String[] options = {"pine tree",
							"lavendar",
							"sun flower"};
		
		TextField text = new TextField();

		text.textProperty().addListener((observable, oldValue, newValue) -> {
            if(container.getChildren().size()>1){ // if already contains a drop-down menu -> remove it 
                container.getChildren().remove(1);
            }
            container.add(populateDropDownMenu(newValue, options),0,1); // then add the populated drop-down menu to the second row in the grid pane
        });
        Button close = new Button("X");
        Button search = new Button("Search");
        searchBox.getChildren().addAll(text,close,search);

        container.add(searchBox, 0, 0);
        container.setBackground(new Background(new BackgroundFill(Color.GRAY, null,null)));
        
		
		borderPane = new BorderPane();
		vbox = new VBox();
		
		text1 = new Text("Which plants are already in your Garden?");
		text1.setWrappingWidth(800);
		text1.setStyle("-fx-font-size: 20px;");
		vbox.getChildren().addAll(text1);
		
		
		backToMain = new Button("Back to Main");
		backToMain.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
            	switchToWindow(Windows.Welcome);
            }
        });
		
		nextButton = new Button("Next");
		nextButton.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
            	switchToWindow(Windows.Questionnaire);
            }
        });
		
		
		
		borderPane.setTop(vbox);
		borderPane.setCenter(container);
		borderPane.setBottom(backToMain);
		borderPane.setRight(nextButton);
		
		this.root = new Group();
		root.getChildren().add(borderPane);
		this.scene = new Scene(this.root);
	}
	
	@Override
	public Scene getScene() {
		// TODO Auto-generated method stub
		return this.scene;

	}
	
	public Plant[] getExistingPlants() {
		return this.existingPlant;
	}
	
	public void setExistingPlants(Plant p) {
		
	}
	
	
	
	 public static VBox populateDropDownMenu(String text, String[] options){
	        VBox dropDownMenu = new VBox();
	        dropDownMenu.setBackground(new Background(new BackgroundFill(Color.YELLOW, null,null))); // colors just for example
	        dropDownMenu.setAlignment(Pos.CENTER); // all these are optional and up to you

	        for(String option : options){ // loop through every String in the array
	            // if the given text is not empty and doesn't consists of spaces only, as well as it's a part of one (or more) of the options
	            if(!text.replace(" ", "").isEmpty() && option.toUpperCase().contains(text.toUpperCase())){ 
	                Label label = new Label(option); // create a label and set the text 
	                // you can add listener to the label here if you want
	                // your user to be able to click on the options in the drop-down menu
	                dropDownMenu.getChildren().add(label); // add the label to the VBox
	            }
	        }

	        return dropDownMenu; // at the end return the VBox (i.e. drop-down menu)
	    }
	
	
	
	
	
	
	
	
	
	
	
}
