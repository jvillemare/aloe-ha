package udel.GardenProject.windows;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import udel.GardenProject.enums.Windows;
import udel.GardenProject.garden.Model;
import udel.GardenProject.plants.Plant;

/**
 * Heart of the application: Where the user can drag plants, obstacles, shade,
 * text boxes, and interact with their virtual, top-down plot.
 *
 * @author Team 0
 */
public class PlotDesign extends Window {

	private Group root;
	private Scene scene;

	private BorderPane borderPane;
	private VBox vbox;
	private Text text;

	private int statistics[];
	private Button saveButton;
	private Button backButton;
	private Button loadButton;
	private Button nextButton;

	/**
	 * Create a new PlotDesign window instance.
	 *
	 * @param m Model
	 */
	public PlotDesign(Model m) {
		super(m, "Plot Designer");

		borderPane = new BorderPane();
		vbox = new VBox();

		text = new Text("Welcome to the Plot Design!\n");
		text.setWrappingWidth(800);
		text.setStyle("-fx-font-size: 20px;");
		vbox.getChildren().addAll(text);


		backButton = new Button("Go Back");
		backButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
            	switchToWindow(Windows.PlantSelection);
            }
        });

		nextButton = new Button("Next");
		nextButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
            	switchToWindow(Windows.SeasonView);
            }
        });

		borderPane.setTop(vbox);
		borderPane.setLeft(backButton);
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

	/**
	 */
	public void getObstacle() {

	}

	public Object setObstacle(Object obstacle) {
		return null;
	}

	public Plant setPlant(Plant p) {
		return p;
	}

	public void getPlant() {

	}

}
