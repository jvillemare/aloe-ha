package udel.GardenProject.windows;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import udel.GardenProject.enums.Windows;
import udel.GardenProject.garden.Model;

/**
 * Welcome screen that first appears when the user starts the program.
 * 
 * @author Team 0
 */
public class Welcome extends Window {


	private Group root;
	private Scene scene;
	Button startNewPlot;
	Button loadSavedPlot;
	Button tutorialButton;
	
	Image imageSeed;
	Image imagePlant;
	Image imageTree;
	
	private BorderPane welcomeScreen;
	private FlowPane buttonFlow;
	
	public Welcome(Model m) {
		super(m, "Welcome Menu");
		
		welcomeScreen = new BorderPane();
		
		Text welcome = new Text("Aloe-Ha to your Garden!");
		welcome.setFont(new Font(25));
		welcome.setFill(Color.DARKGREEN); 
		
		StackPane welcomeText = new StackPane();
		welcomeText.getChildren().add(welcome);
		
		welcomeScreen.setStyle("-fx-background-color: DAE6F3;");
		welcomeScreen.setTop(welcomeText);
		
		
		buttonFlow = new FlowPane(Orientation.HORIZONTAL);
		buttonFlow.setPadding(new Insets(5, 0, 5, 0));
		buttonFlow.setVgap(4);
		buttonFlow.setHgap(4);
		buttonFlow.setPrefWrapLength(600);
		buttonFlow.setStyle("-fx-background-color: DAE6F3;");
		
		
		imageSeed = new Image(
				getClass().getResourceAsStream("/buttonImages/seed.png"),
				300, 100, true, true);
		startNewPlot = new Button("Start New Plot", new ImageView(imageSeed));
		startNewPlot.setMaxSize(300,100);
		
		startNewPlot.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
            	switchToWindow(Windows.ExistingPlants);
            }
        });
		
		imagePlant = new Image(
				getClass().getResourceAsStream("/buttonImages/images.png"),
				300, 100, true, true);
		
		loadSavedPlot = new Button("Load Saved Plot", new ImageView(imagePlant));
		loadSavedPlot.setMaxSize(300,100);
		
		loadSavedPlot.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
            	//switchToWindow(Windows.Questionnaire);
            	//TODO: figure out how to handle this. 
            }
        });
		
		imageTree = new Image(
				getClass().getResourceAsStream("/buttonImages/tree.png"),
				300, 100, true, true);
		tutorialButton = new Button("Tutorial", new ImageView(imageTree));
		tutorialButton.setMaxSize(300, 100);
		
		tutorialButton.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
            	switchToWindow(Windows.Tutorial);
            }
        });
		
				
		buttonFlow.getChildren().addAll(startNewPlot, loadSavedPlot,
				tutorialButton);
		
		welcomeScreen.setCenter(buttonFlow);
		
		this.root = new Group();
		root.getChildren().add(welcomeScreen);
		this.scene = new Scene(this.root);
	}

	@Override
	public Scene getScene() {
		return this.scene;

	}
	
	
}
