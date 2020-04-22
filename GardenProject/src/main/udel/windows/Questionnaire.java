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
 * Basic questions about a user's plots that informs what plants are selected.
 * 
 * @author Team 0
 */
public class Questionnaire extends Window {

	private Group root;
	private Scene scene;
	
	private BorderPane borderPane;
	private VBox vbox;
	
	private Button backToExistingPlants;
	private Button toPlotDesign;
	private Text text;
	
	public Questionnaire(Model m) {
		super(m, "Questions About Your Garden...");
		
		borderPane = new BorderPane();
		vbox = new VBox();
		
		text = new Text("Please answer the following questions about your Garden!\n");
		text.setWrappingWidth(800);
		text.setStyle("-fx-font-size: 20px;");
		vbox.getChildren().addAll(text);
		
		backToExistingPlants = new Button("Go Back");
		backToExistingPlants.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
            	switchToWindow(Windows.ExistingPlants);
            }
        });
		
		toPlotDesign = new Button("Next");
		toPlotDesign.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
            	switchToWindow(Windows.PlantSelection);
            }
        });
		
		borderPane.setTop(vbox);
		borderPane.setLeft(backToExistingPlants);
		borderPane.setRight(toPlotDesign);
		
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
