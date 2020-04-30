package udel.GardenProject.windows;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import udel.GardenProject.enums.Windows;
import udel.GardenProject.garden.Model;
import udel.GardenProject.garden.View;

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

	/**
	 * The image that is being used at the logo for the welcome screen
	 */
	private Image logo;

	/**
	 * Created so the logo can be seen on the GUI
	 */
	private ImageView logoShow;

	/**
	 * Used for placing the title and the buttons in the center
	 */
	private VBox centerVBox;

	public Welcome(Model m) {
		super(m, "Welcome Menu");

		welcomeScreen = new BorderPane();
		logo = new Image(getClass().getResourceAsStream("/buttonImages/logo.png"));

		logoShow = new ImageView(logo);

		logoShow.setFitHeight(logoShow.getFitWidth() * 6);
		logoShow.setFitWidth(logoShow.getFitWidth() * 5);

		createButtons();

		buttonFlow = new FlowPane(Orientation.HORIZONTAL);
		buttonFlow.setAlignment(Pos.CENTER);
		buttonFlow.setPadding(new Insets(50, 10, 30, 10));
		buttonFlow.setVgap(50);
		buttonFlow.setHgap(50);
		buttonFlow.setPrefWrapLength(View.getCanvasWidth());
		buttonFlow.getChildren().addAll(startNewPlot, loadSavedPlot, tutorialButton);

		centerVBox = new VBox();
		centerVBox.setAlignment(Pos.CENTER);
		centerVBox.setPadding(new Insets(View.getCanvasHeight() / 8, 0, View.getCanvasHeight() / 5, 0));
		centerVBox.getChildren().addAll(logoShow, buttonFlow);

		welcomeScreen.setStyle("-fx-background-color: #F6E8E8;");
		welcomeScreen.setCenter(centerVBox);

		this.root = new Group();
		root.getChildren().add(welcomeScreen);
		this.scene = new Scene(this.root, View.getCanvasWidth(), View.getCanvasHeight());
	}

	@Override
	public Scene getScene() {
		return this.scene;

	}

	public void createButtons() {

		DropShadow shadow = new DropShadow();

		startNewPlot = new Button("Start New Plot");
		startNewPlot.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				switchToWindow(Windows.ExistingPlants);
			}
		});

		loadSavedPlot = new Button("Load Saved Plot");
		loadSavedPlot.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				switchToWindow(Windows.Download);
			}
		});

		tutorialButton = new Button("Tutorial");
		tutorialButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				switchToWindow(Windows.Tutorial);
			}
		});

		List<Button> buttonArr = new ArrayList<Button>();
		buttonArr.add(startNewPlot);
		buttonArr.add(loadSavedPlot);
		buttonArr.add(tutorialButton);

		for (Button b : buttonArr) {
			b.setPrefWidth(300);
			b.setContentDisplay(ContentDisplay.TOP);
			b.setStyle("-fx-background-color: #63A331; " + "-fx-background-radius: 0");
			b.setPadding(new Insets(20, 40, 20, 40));
			b.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/Hack-Bold.ttf"), 20));

			b.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					b.setEffect(shadow);
					b.setStyle("-fx-background-color: #76C327;");
					b.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/Hack-Italic.ttf"), 20));
				}
			});

			b.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					b.setEffect(null);
					b.setStyle("-fx-background-color: #63A331;");
					b.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/Hack-Bold.ttf"), 20));
				}
			});

		}
	}
}
