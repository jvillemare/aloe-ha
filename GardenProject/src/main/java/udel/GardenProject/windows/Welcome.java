package udel.GardenProject.windows;

import java.io.File;
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
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
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
	 * Buttons that takes you to Existing Plants
	 */
	private Button startNewPlot;

	/**
	 * Button that takes you to Download
	 */
	private Button loadSavedPlot;

	/**
	 * Button that takes you to Tutorial
	 */
	private Button tutorialButton;

	/**
	 * Overall layout of the screen is set in a BorderPane
	 */
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

	/**
	 * Adjustments made to buttons, and screen
	 */
	private int logoHeightFactor = 6;
	private int logoWidthFactor = 5;
	private int buttonSideInset = 20;
	private int buttonTopBottomInset = 40;
	private int buttonFlowSidePadding = 10;
	private int buttonFlowTopPadding = 50;
	private int buttonFlowBottomPadding = 30;

	public Welcome(Model m) {
		super(m, "Welcome Menu");

		welcomeScreen = new BorderPane();
		logo = new Image(getClass().getResourceAsStream("/buttonImages/logo.png"));

		logoShow = new ImageView(logo);
		logoShow.setFitHeight(logoShow.getFitWidth() * logoHeightFactor);
		logoShow.setFitWidth(logoShow.getFitWidth() * logoWidthFactor);

		createButtons();

		buttonFlow = new FlowPane(Orientation.HORIZONTAL);
		buttonFlow.setAlignment(Pos.CENTER);
		buttonFlow.setPadding(new Insets(buttonFlowTopPadding, buttonFlowSidePadding, buttonFlowBottomPadding,
				buttonFlowSidePadding));
		buttonFlow.setVgap(50);
		buttonFlow.setHgap(50);
		buttonFlow.setPrefWrapLength(View.getCanvasWidth());
		buttonFlow.getChildren().addAll(startNewPlot, loadSavedPlot, tutorialButton);

		centerVBox = new VBox();
		centerVBox.setAlignment(Pos.CENTER);
		centerVBox.setPadding(new Insets(View.getCanvasHeight() / 8, 0, View.getCanvasHeight() / 5, 0));
		centerVBox.getChildren().addAll(logoShow, buttonFlow);

		welcomeScreen.setStyle(View.getPinkBackgroundStyle());
		welcomeScreen.setCenter(centerVBox);

		this.root = new Group();
		root.getChildren().add(welcomeScreen);
		this.scene = new Scene(this.root, View.getCanvasWidth(), View.getCanvasHeight());
	}

	@Override
	public Scene getScene() {
		return this.scene;

	}

	/**
	 * Creating the main 3 buttons on the screen with effects
	 */
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
				javafx.stage.Window scene2 = null;
				FileChooser fileChooser = new FileChooser();
				fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Garden Project", "*.gardenproject"));

				/*
				 * User opens up a saved file and then be taken to plot design
				 */
				fileChooser.setTitle("Load 'Aloe-Ha' Garden Project");
				File file = fileChooser.showOpenDialog(scene2);

				if (file != null) {
					getModel().loadSession(file.getAbsolutePath());
					switchToWindow(Windows.PlotDesign);
				}

			}
		});

		tutorialButton = new Button("Tutorial");
		tutorialButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				switchToWindow(Windows.Tutorial);
			}
		});

		/*
		 * Adding Style and Effects to each of the buttons
		 */
		List<Button> buttonArr = new ArrayList<Button>();
		buttonArr.add(startNewPlot);
		buttonArr.add(loadSavedPlot);
		buttonArr.add(tutorialButton);

		for (Button b : buttonArr) {
			b.setPrefWidth(View.getWelcomeButtonWidth());
			b.setContentDisplay(ContentDisplay.TOP);
			b.setStyle(View.getDarkGreenBackgroundStyle() + "-fx-background-radius: 0");
			b.setPadding(new Insets(buttonSideInset, buttonTopBottomInset, buttonSideInset, buttonTopBottomInset));
			b.setFont(Font.loadFont(getClass().getResourceAsStream(View.getHackBold()),
					View.getTextSizeForButtonsAndText()));

			b.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					b.setEffect(shadow);
					b.setStyle(View.getLightGreenBackgroundStyle());
					b.setFont(Font.loadFont(getClass().getResourceAsStream(View.getHackItalic()),
							View.getTextSizeForButtonsAndText()));
				}
			});

			b.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					b.setEffect(null);
					b.setStyle(View.getDarkGreenBackgroundStyle());
					b.setFont(Font.loadFont(getClass().getResourceAsStream(View.getHackBold()),
							View.getTextSizeForButtonsAndText()));
				}
			});

		}
	}

	/**
	 * Refreshes the screen each time the user opens this up. Screen does not
	 * refresh on initial startup
	 */
	public void refresh() {
		System.out.println("refreshing in welcome");
	}

}
