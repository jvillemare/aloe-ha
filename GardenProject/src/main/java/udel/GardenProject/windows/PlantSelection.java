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
import main.udel.plants.Plant;

/**
 * To display all the information of a Plant to the user.
 * 
 * @author Team 0
 */
public class PlantSelection extends Window {
	
	private Group root;
	private Scene scene;

	private BorderPane borderPane;
	private VBox vbox;
	private Text text;
	
	private Button back;
	private Button next;
	
	private Plant plantArray[]; 
	

	public PlantSelection(Model m) {
		super(m, "Plant Info: Pine Trees");
		
		borderPane = new BorderPane();
		vbox = new VBox();
		
		text = new Text("Please select the plants you'd like to have in your Garden");
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
		
		next = new Button("To Plot Design");
		next.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
            	switchToWindow(Windows.PlotDesign);
            }
        });
		
		borderPane.setTop(vbox);
		borderPane.setLeft(back);
		borderPane.setRight(next);
		
		this.root = new Group();
		root.getChildren().add(borderPane);
		this.scene = new Scene(this.root);
	}
	
	@Override
	public Scene getScene() {
		// TODO Auto-generated method stub
		return this.scene;
	}
	
}
