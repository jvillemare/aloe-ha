package udel.GardenProject.windows;

import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import udel.GardenProject.enums.Windows;
import udel.GardenProject.garden.Model;
import udel.GardenProject.garden.View;

/**
 * Presently: Basic text description run down of all the features of the program
 * and how to use them.
 *
 * @author Team 0
 */
public class Tutorial extends Window {

	private Group root;
	private Scene scene;

	/**
	 * Allows scrolling for user when they want to access info
	 */
	private ScrollPane scroll;

	/**
	 * Used to hold the info at the top of the screen
	 */
	private VBox vbox;

	/**
	 * Center Box that holds the accordion
	 */
	private VBox centerBox;
	/**
	 * Used for the overall layout
	 */
	private BorderPane borderPane;

	/**
	 * Button goes back to main
	 */
	private Button back;

	/**
	 * text in vbox at the top of the screen
	 */
	private Text welcomeTxt;

	/**
	 * Holds the back button at the bottom
	 */
	private TilePane backPane;

	/**
	 * Holds all the How Tos
	 */
	private Accordion accordion;

	int inset10 = 10;
	int backgroundWidthAndHeight = 100;
	int topTextSize = 20;
	
	/**
	 * Create a Tutorial window instance.
	 *
	 * @param m Model
	 */
	public Tutorial(Model m) {
		super(m, "Tutorial Window");

		borderPane = new BorderPane();
		borderPane.setPadding(new Insets(inset10, inset10, inset10, inset10));
		backPane = new TilePane();
		vbox = new VBox();
		vbox.setPadding(new Insets(0, 0, inset10, 0));

		welcomeTxt = new Text(
				"Welcome to the Tutorial! Click on the drop down options below to help you get started on your plot. Happy planting!");
		welcomeTxt.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/Hack-Bold.ttf"), topTextSize));
		welcomeTxt.setWrappingWidth(View.getCanvasWidth());

		VBox welcomePane = new VBox();
		welcomePane.getChildren().add(welcomeTxt);

		back = new Button("Back to Main");
		back.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				switchToWindow(Windows.Welcome);
			}
		});

		centerBox = new VBox();
		centerBox.setPadding(new Insets(10, 10, 10, 10));

		createAccordion();

		centerBox.getChildren().add(accordion);

		scroll = new ScrollPane();
		scroll.setContent(centerBox);
		scroll.setMaxWidth(View.getCanvasWidth() - 90);
		scroll.setMaxHeight(View.getCanvasHeight() - vbox.getHeight() - backPane.getHeight() - 130);
		scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

		backPane.setAlignment(Pos.CENTER);
		backPane.setPadding(new Insets(10, 10, 10, 10));
		backPane.getChildren().add(back);

		
		Image image = new Image(getClass().getResourceAsStream("/buttonImages/splash2.png"));
		BackgroundSize backgroundSize = new BackgroundSize(backgroundWidthAndHeight, backgroundWidthAndHeight, true,
				true, true, false);
		BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
		Background background = new Background(backgroundImage);

		borderPane.setBackground(background);
		borderPane.setTop(welcomePane);
		borderPane.setCenter(scroll);
		borderPane.setBottom(backPane);

		this.root = new Group();
		root.getChildren().add(borderPane);
		this.scene = new Scene(this.root, View.getCanvasWidth(), View.getCanvasHeight());

	}

	@Override
	public Scene getScene() {
		// TODO Auto-generated method stub
		return this.scene;
	}

	public void createAccordion() {
		accordion = new Accordion();
		
		
		/**
		 * TODO: Abstract this. Find a better way to display information. 
		 * TitledPane does not allow changed made to the new Text!!!!!!!!!!!
		 * 
		 */
		TitledPane pane1 = new TitledPane("How to Get Started",
				new Text("Click on the 'Start New Plot' Button to begin your plot."));
		TitledPane pane2 = new TitledPane("How to Navigate to Different Screens",
				new Text("Click on the different buttons at the bottom of the screen to navigate back and forth."));
		TitledPane pane3 = new TitledPane("How to Add Existing Plants", new Text(
				"In the search bar, start typing the name of your plant, either its Latin or common name. Click on the plant name to add it to your list. Click the 'X' button next to the plant's name to remove it from your list of existing plants."));
		TitledPane pane4 = new TitledPane("About the Questionnaire", new Text(
				"The questionnaire will help us figure out which plants will be best suited for your garden and help give recommendations for a great garden!"));
		TitledPane pane5 = new TitledPane("How to Select Plants", new Text(
				"From the Plant Selection Screen, you will see a filtered list of plants to select from. You can click 'Add Plant' to add the plant to you list. If you are unsure about the plant, click 'Info' to learn more."));
		TitledPane pane6 = new TitledPane("...", new Text("..."));
		TitledPane pane7 = new TitledPane("...", new Text("..."));
		TitledPane pane8 = new TitledPane("...", new Text("..."));
		TitledPane pane9 = new TitledPane("...", new Text("..."));
		TitledPane pane10 = new TitledPane("...", new Text("..."));
		TitledPane pane11 = new TitledPane("...", new Text("..."));
		TitledPane pane12 = new TitledPane("...", new Text("..."));
		TitledPane pane13 = new TitledPane("...", new Text("..."));
		TitledPane pane14 = new TitledPane("...", new Text("..."));
		TitledPane pane15 = new TitledPane("...", new Text("..."));
		TitledPane pane16 = new TitledPane("...", new Text("..."));

		List<TitledPane> accArr = new ArrayList<TitledPane>();
		accArr.add(pane1);
		accArr.add(pane2);
		accArr.add(pane3);
		accArr.add(pane4);
		accArr.add(pane5);
		accArr.add(pane6);
		accArr.add(pane7);
		accArr.add(pane8);
		accArr.add(pane9);
		accArr.add(pane10);
		accArr.add(pane11);
		accArr.add(pane12);
		accArr.add(pane13);
		accArr.add(pane14);
		accArr.add(pane15);
		accArr.add(pane16);

		for (TitledPane t : accArr) {
			t.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/Hack-Bold.ttf"), 20));
			accordion.getPanes().add(t);
		}
	}

}
