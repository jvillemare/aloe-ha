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
import javafx.scene.layout.FlowPane;
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

	FlowPane flow;

	/**
	 * Create a Welcome screen window instance.
	 *
	 * @param m Model
	 */
	public Welcome(Model m) {
		super(m, "Welcome Menu");


		flow = new FlowPane(Orientation.HORIZONTAL);
		flow.setPadding(new Insets(5, 0, 5, 0));
		flow.setVgap(4);
		flow.setHgap(4);
		flow.setPrefWrapLength(1000);
		flow.setStyle("-fx-background-color: DAE6F3;");



		imageSeed = new Image(getClass().getResourceAsStream("/buttonImages/seed.png"));
		startNewPlot = new Button("Start New Plot", new ImageView(imageSeed));

		startNewPlot.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
            	switchToWindow(Windows.ExistingPlants);
            }
        });

		imagePlant = new Image(getClass().getResourceAsStream("/buttonImages/images.png"));
		loadSavedPlot = new Button("Load Saved Plot", new ImageView(imagePlant));

		loadSavedPlot.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
            	//switchToWindow(Windows.Questionnaire);
            	//TODO: figure out how to handle this.
            }
        });

		imageTree = new Image(getClass().getResourceAsStream("/buttonImages/tree.png"));
		tutorialButton = new Button("Tutorial", new ImageView(imageTree));

		tutorialButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
            	switchToWindow(Windows.Tutorial);
            }
        });

		flow.getChildren().addAll(startNewPlot, loadSavedPlot, tutorialButton);

		this.root = new Group();
		root.getChildren().add(helloWorld);
		this.scene = new Scene(this.root, 150, 150);
	}

	@Override
	public Scene getScene() {
		return this.scene;

	}


}
