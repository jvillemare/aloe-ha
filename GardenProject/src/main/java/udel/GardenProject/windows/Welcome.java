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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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

	/**
	 * Buttons to move to corresponding windows
	 */
	Button startNewPlot;
	Button loadSavedPlot;
	Button tutorialButton;

	/**
	 * Images are tied to the buttons
	 */
	Image imageSeed;
	Image imagePlant;
	Image imageTree;

	private BorderPane welcomeScreen;

	/**
	 * Used for placing the buttons neatly
	 */
	private FlowPane buttonFlow;

	private Text welcome;

	/**
	 * Used for placing the title and the buttons in the center
	 */
	private VBox centerVBox;

	public Welcome(Model m) {
		super(m, "Welcome Menu");

		welcomeScreen = new BorderPane();
		welcome = new Text("Aloe-Ha to your Garden!");
		welcome.setStyle("-fx-font-size: 50px;");
		welcome.setFill(Color.DARKGREEN);

		createButtons();

		buttonFlow = new FlowPane(Orientation.HORIZONTAL);
		buttonFlow.setPadding(new Insets(5, 0, 5, 0));
		buttonFlow.setVgap(4);
		buttonFlow.setHgap(4);
		buttonFlow.setPrefWrapLength(600);
		buttonFlow.getChildren().addAll(startNewPlot, loadSavedPlot, tutorialButton);

		centerVBox = new VBox();
		centerVBox.setPadding(new Insets(200, 200, 200, 200));
		centerVBox.getChildren().addAll(welcome, buttonFlow);

		welcomeScreen.setStyle("-fx-background-color: DAE6F3;");
		welcomeScreen.setCenter(centerVBox);

		this.root = new Group();
		root.getChildren().add(welcomeScreen);
		this.scene = new Scene(this.root, 940, 580);
	}

	@Override
	public Scene getScene() {
		return this.scene;

	}

	public void createButtons() {

		imageSeed = new Image(getClass().getResourceAsStream("/buttonImages/seed.png"), 300, 100, true, true);
		startNewPlot = new Button("Start New Plot", new ImageView(imageSeed));
		startNewPlot.setMaxSize(300, 100);
		startNewPlot.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				System.out.println("Start New Plot: switching to Existing Plants window");
				switchToWindow(Windows.ExistingPlants);
			}
		});

		imagePlant = new Image(getClass().getResourceAsStream("/buttonImages/images.png"), 300, 100, true, true);
		loadSavedPlot = new Button("Load Saved Plot", new ImageView(imagePlant));
		loadSavedPlot.setMaxSize(300, 100);
		loadSavedPlot.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				System.out.println("Load: loading saved plots");
				// TODO: figure out how to handle this.
			}
		});

		imageTree = new Image(getClass().getResourceAsStream("/buttonImages/tree.png"), 300, 100, true, true);
		tutorialButton = new Button("Tutorial", new ImageView(imageTree));
		tutorialButton.setMaxSize(300, 100);
		tutorialButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				System.out.println("Tutorial: switching to tutorial window");
				switchToWindow(Windows.Tutorial);
			}
		});
	}
}
