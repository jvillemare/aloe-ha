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
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import udel.GardenProject.enums.Canopy;
import udel.GardenProject.enums.Windows;
import udel.GardenProject.garden.Model;
import udel.GardenProject.garden.View;

/**
 * To display all the information of a Plant to the user.
 * 
 * @author Team 0
 */
public class PlantSelection extends Window {

	private Group root;
	private Scene scene;

	/**
	 * Used for overall layout
	 */
	private BorderPane borderPane;

	/**
	 * Hold the buttons at the bottom of the screen
	 */
	private TilePane tilePane;

	/**
	 * Used to hold the text and the toggles at the top of the screen
	 */
	private VBox vbox;

	/**
	 * Information at the top of the screen
	 */
	private Text text;

	/**
	 * Navigation buttons at the bottom of the screen
	 */
	private Button back, mainMenu, next;

	/**
	 * ScrollPane for the FlowPane where user's selections of plants are placed w
	 */
	private ScrollPane scrollSelected;

	/**
	 * ScrollPane for the accordion selection
	 */
	private ScrollPane scrollCanopies;

	/**
	 * Flow Pane for the user to see what plants they have selected
	 */
	private FlowPane selectedPlantsBox;

	/**
	 * Box that holds the ScrollPane for both the accordion and the user selection
	 * of plants
	 */
	private HBox centerBox;

	/**
	 * Adjustments to screen, buttons, and panes
	 */
	private int borderSideMargins = 80;
	private int gapBetweenButtons = 100;
	private int borderTopAndBottonMargin = 40;
	private int backgroundScreenWidthAndHeight = 100;
	private int prefScrollWidth = View.getCanvasWidth() / 3 + 30;
	private int prefScrollHeight = View.getCanvasHeight() / 5 * 4;
	private int selectedPlantBoxMinWidth = View.getCanvasWidth() / 2;
	private int scrollSelectedWidth = View.getCanvasWidth() / 2 + 30;
	private int scrollSelectedHeight = View.getCanvasHeight() / 5 * 4;
	private int selectedPlantBoxMinHeight = View.getCanvasHeight() / 5 * 4;

	public PlantSelection(Model m) {
		super(m, "Plant Selection");

		borderPane = new BorderPane();
		vbox = new VBox();
		tilePane = new TilePane();

		text = new Text("Please select the plants you'd like to have in your Garden");
		text.setWrappingWidth(View.getCanvasWidth());

		text.setFont(
				Font.loadFont(getClass().getResourceAsStream(View.getHackBold()), View.getTextSizeForButtonsAndText()));
		vbox.getChildren().addAll(text);

		createButtons();

		centerBox = new HBox();

		Accordion canopySelection = new Accordion();
		TitledPane floor = new TitledPane("Floor", createFlowPane(Canopy.FLOOR));
		TitledPane understory = new TitledPane("Understory", createFlowPane(Canopy.UNDERSTORY));
		TitledPane canopy = new TitledPane("Canopy", createFlowPane(Canopy.CANOPY));
		TitledPane emergent = new TitledPane("Emergent", createFlowPane(Canopy.EMERGENT));
		List<TitledPane> accArr = new ArrayList<TitledPane>();
		accArr.add(floor);
		accArr.add(understory);
		accArr.add(canopy);
		accArr.add(emergent);

		for (TitledPane t : accArr) {
			t.setFont(Font.loadFont(getClass().getResourceAsStream(View.getHackBold()),
					View.getTextSizeForButtonsAndText()));
			canopySelection.getPanes().add(t);
		}

		scrollCanopies = new ScrollPane();
		scrollPaneFormat(scrollCanopies);
		scrollCanopies.setPrefSize(prefScrollWidth, prefScrollHeight);
		scrollCanopies.setContent(canopySelection);

		selectedPlantsBox = new FlowPane();
		selectedPlantsBox.setMinWidth(selectedPlantBoxMinWidth);
		selectedPlantsBox.setMinHeight(selectedPlantBoxMinHeight);

		scrollSelected = new ScrollPane();
		scrollPaneFormat(scrollSelected);

		scrollSelected.setPrefSize(scrollSelectedWidth, scrollSelectedHeight);
		scrollSelected.setContent(selectedPlantsBox);

		centerBox.getChildren().addAll(scrollCanopies, scrollSelected);

		tilePane.setAlignment(Pos.CENTER);
		tilePane.setHgap(gapBetweenButtons);
		tilePane.getChildren().addAll(back, mainMenu, next);

		Image image = new Image(getClass().getResourceAsStream(View.getBackgroundScreenPath()));
		View.setBackgroundScreen(image, backgroundScreenWidthAndHeight, backgroundScreenWidthAndHeight);

		borderPane.setBackground(View.getBackgroundScreen());
		BorderPane.setMargin(centerBox,
				new Insets(borderTopAndBottonMargin, borderSideMargins, borderTopAndBottonMargin, borderSideMargins));
		borderPane.setPadding(new Insets(5));
		borderPane.setTop(vbox);
		borderPane.setBottom(tilePane);
		borderPane.setCenter(centerBox);

		this.root = new Group();
		root.getChildren().add(borderPane);
		this.scene = new Scene(this.root, View.getCanvasWidth(), View.getCanvasHeight());
	}

	public void scrollPaneFormat(ScrollPane scroll) {
		scroll.setPadding(new Insets(10));
		scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		scroll.setStyle(View.getWhiteBackgroundStyle() + "-fx-border-color: #F6AAA4;" + "-fx-border-insets: 5;"
				+ "-fx-border-width: 3;" + "-fx-border-style: solid;");

	}

	/**
	 * Function creates a flow pane (with Scroll) for the type of canopy selected.
	 * 
	 * @param canopy --> Takes in a canopy
	 */
	public FlowPane createFlowPane(Canopy canopy) {

		FlowPane flowCanopy = new FlowPane();

		/**
		 * TODO: set all of the plants here that correspond with canopy.
		 */
		/*
		 * This is a temporary addition to test if function works
		 */
		Image pages[] = new Image[20];
		for (int i = 0; i < 20; i++) {
			pages[i] = new Image(getClass().getResourceAsStream("/buttonImages/tree.png"), 350, 100, true, true);
			ImageView imageView = new ImageView(pages[i]);
			Button addPlant = new Button("Add Plant");

			Button infoButton = new Button("Info");
			infoButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					getModel().setPlantInfoPlant(getModel().getPlants().get(0));
					switchToWindow(Windows.PlantInfo);
				}
			});

			HBox buttonHolder = new HBox();
			buttonHolder.getChildren().addAll(infoButton, addPlant);

			VBox imgButtonHolder = new VBox();
			imgButtonHolder.getChildren().addAll(imageView, buttonHolder);
			flowCanopy.getChildren().add(imgButtonHolder);

			addPlant.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					selectedPlantsBox.getChildren().add(imgButtonHolder);

				}
			});
		}

		return flowCanopy;

	}

	/**
	 * Function that creates button at the bottom of the screen with handlers
	 */
	public void createButtons() {
		back = new Button("Go Back");
		back.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				switchToWindow(Windows.Questionnaire);
			}
		});

		mainMenu = new Button("Main Menu");
		mainMenu.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				switchToWindow(Windows.Welcome);
			}
		});

		next = new Button("Next");
		next.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				switchToWindow(Windows.PlotDesign);
			}
		});

		List<Button> buttons = new ArrayList<Button>();
		buttons.add(back);
		buttons.add(mainMenu);
		buttons.add(next);

		for (Button b : buttons) {
			b.setFont(Font.loadFont(getClass().getResourceAsStream(View.getHackBold()), View.getButtonTextSize()));
			b.setStyle(View.getLightGreenBackgroundStyle() + View.getBlackTextFill());
			b.setPrefWidth(View.getButtonPrefWidth());

			DropShadow shadow = new DropShadow();
			b.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					b.setEffect(shadow);
					b.setStyle(View.getWhiteBackgroundStyle() + View.getBlackTextFill());
				}
			});

			b.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					b.setEffect(null);
					b.setStyle(View.getLightGreenBackgroundStyle() + View.getBlackTextFill());
				}
			});
		}
	}

	/**
	 * Refreshes the screen to have the correct plants on the right side according
	 * to the session
	 */
	public void refresh() {
		/**
		 * TODO: create proper implementation of clearing and repopulating the flow pane
		 * for selected plants
		 */
		/*
		 * selectedPlantsBox.getChildren().clear(); Iterator<Plant> pItr =
		 * getSession().getSelectedPlants().iterator(); while (pItr.hasNext()) {
		 * populateRightBox(pItr.next()); }
		 */
	}

	@Override
	public Scene getScene() {
		// TODO Auto-generated method stub
		return this.scene;
	}

}
