package udel.GardenProject.windows;

import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
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
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import udel.GardenProject.enums.Windows;
import udel.GardenProject.garden.Model;
import udel.GardenProject.garden.View;
import udel.GardenProject.plants.Plant;
import udel.GardenProject.plotObjects.PlotObject;
import udel.GardenProject.plotObjects.polygons.AdjustablePolygon;

/**
 * Heart of the application: Where the user can drag plants, obstacles, shade,
 * text boxes, and interact with their virtual, top-down plot.
 *
 * @author Team 0
 */
public class PlotDesign extends Window {

	private Group root;
	private Scene scene;

	/**
	 * Used for the overall layout
	 */
	private BorderPane borderPane;

	/**
	 * Used to hold text, left and right panels
	 */
	private VBox vbox, autoRateVBox;

	private Text text;

	/**
	 * Used for labeling autorate bars
	 */
	private Text animalsFedTxt, contBloomTxt, matchTxt, transitionTxt;

	/**
	 * Text above editPlotButton
	 */
	private Text editPlotText;

	/**
	 * Used inside of leftDropdownVBox for the plants we will put in the plot
	 */
	private TilePane tilePane;

	private int statistics[];

	/**
	 * Buttons in tilePane at the bottom of the screen
	 */
	private Button backButton, saveButton, loadButton, nextButton;

	/**
	 * Used for when the user wants to edit the points of their plot
	 */
	private Button editPlotButton;

	/**
	 * Used to outline the center area
	 */
	private Rectangle box;

	/**
	 * The adjustable plot for the user to move around.
	 */
	private AdjustablePolygon poly;

	/**
	 * Used for placement of adjustable polygon and plants/obstacles etc
	 */
	private Group group;

	/**
	 * Used in drag
	 */
	private double xbound;

	/**
	 * ScrollPane created for the canopy drop down choices
	 */
	private ScrollPane scrollSelections;

	private int rectWidth = View.getCanvasWidth() / 5 * 3;
	private int rectHeight = View.getCanvasHeight() / 7 * 6;
	private int scrollPrefWidth = View.getCanvasWidth() / 3 + 30;
	private int scrollPrefHeight = View.getCanvasHeight() / 5 * 4;
	private int autoRateBoxWidth = 255;
	private int autoRateBoxHeight = 550;
	private int gapBetweenButtons = 100;
	private int backgroundWidthAndHeight = 100;
	private int borderTopAndBottonMargin = 90;
	private int borderSideMargins = 200;
	private int autoRateBarWidth = 200;
	private int autoRateBarHeight = 10;

	/**
	 * Create a new PlotDesign window instance.
	 *
	 * @param m Model
	 */
	public PlotDesign(Model m) {
		super(m, "Plot Designer");

		borderPane = new BorderPane();
		vbox = new VBox();
		tilePane = new TilePane();
		group = new Group();

		box = new Rectangle(rectWidth, rectHeight);
		box.setStroke(Color.BLACK);
		box.setFill(Color.WHITE);
		group.getChildren().add(box);

		text = new Text(
				"Welcome to the Plot Design! Place all of your plants and objects on your plot to complete your garden!");
		text.setWrappingWidth(View.getCanvasWidth());
		text.setFont(
				Font.loadFont(getClass().getResourceAsStream(View.getHackBold()), View.getTextSizeForButtonsAndText()));

		vbox.setPadding(new Insets(0, 0, 20, 5));
		vbox.getChildren().addAll(text);

		editPlotText = new Text("\nAddPlot");
		editPlotText.setStyle("-fx-font-size: 20px;");
		editPlotButton = new Button("Edit Plot");
		editPlotButton.setPadding(new Insets(10, 5, 10, 5));
		editPlotButton.setStyle("-fx-font-size: 20px;");

		editPlotButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				poly = new AdjustablePolygon(Color.GREEN, Color.YELLOW, 40, 40);
				group.getChildren().add(poly.getPolygon());
				group.getChildren().addAll(poly.getAnchors());
				autoRateVBox.getChildren().addAll(poly.genButton(400, 100));

			}
		});

		Accordion choices = new Accordion();

		/**
		 * TODO: get the corresponding plants to be able to use the functions
		 * createFlowPane for the plants and obstacles May need another method for the
		 * obstacles
		 */
		TitledPane existing = new TitledPane("Existing Plants", new Text("Existing Plants")); // createFlowPane(/*Model.existingPlantsArray*/));
		TitledPane selected = new TitledPane("Selected Plants", new Text("SelectedPlants")); // createFlowPane(/*Model.selectedPlantsArray*/));
		TitledPane obstacles = new TitledPane("Obstacles", editPlotButton); // createFlowPane(/*Model.obstaclesArray*/));
		List<TitledPane> accArr = new ArrayList<TitledPane>();
		accArr.add(existing);
		accArr.add(selected);
		accArr.add(obstacles);

		for (TitledPane t : accArr) {
			t.setFont(Font.loadFont(getClass().getResourceAsStream(View.getHackBold()),
					View.getTextSizeForButtonsAndText()));
			choices.getPanes().add(t);
		}

		scrollSelections = new ScrollPane();
		scrollPaneFormat(scrollSelections);
		scrollSelections.setPrefSize(scrollPrefWidth, scrollPrefHeight);
		scrollSelections.setContent(choices);

		animalsFedTxt = new Text("Animals Fed");
		contBloomTxt = new Text("Continuous Bloom");
		matchTxt = new Text("Matches Garden");
		transitionTxt = new Text("Transition");

		ArrayList<Text> autoRatings = new ArrayList<Text>();
		autoRatings.add(animalsFedTxt);
		autoRatings.add(contBloomTxt);
		autoRatings.add(matchTxt);
		autoRatings.add(transitionTxt);

		autoRateVBox = new VBox();
		autoRateVBox.setPrefWidth(autoRateBoxWidth);
		autoRateVBox.setPrefHeight(autoRateBoxHeight);
		autoRateVBox.setPadding(new Insets(5));

		for (Text t : autoRatings) {
			t.setStyle("-fx-font-size: 20px;");
			Rectangle r = new Rectangle(autoRateBarWidth, autoRateBarHeight);
			r.setStroke(Color.BLACK);
			r.setFill(Color.WHITE);
			autoRateVBox.getChildren().addAll(t, r);
		}

		Text goToPlantData = new Text("\nGet More Plants");
		goToPlantData.setStyle("-fx-font-size: 20px;");
		Button plantDataButton = new Button("Plant Database");
		plantDataButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				switchToWindow(Windows.AllPlants);
			}
		});

		plantDataButton.setPadding(new Insets(10, 5, 10, 5));
		plantDataButton.setStyle("-fx-font-size: 20px;");

		autoRateVBox.getChildren().addAll(goToPlantData, plantDataButton);

		createButtons();

		tilePane.setAlignment(Pos.CENTER);
		tilePane.setPadding(new Insets(5));
		tilePane.setHgap(gapBetweenButtons);
		tilePane.getChildren().addAll(backButton, saveButton, loadButton, nextButton);

		Image image = new Image(getClass().getResourceAsStream(View.getBackgroundScreenPath()));
		View.setBackgroundScreen(image, backgroundWidthAndHeight, backgroundWidthAndHeight);

		borderPane.setBackground(View.getBackgroundScreen());
		BorderPane.setMargin(box,
				new Insets(borderTopAndBottonMargin, borderSideMargins, borderTopAndBottonMargin, borderSideMargins));
		borderPane.setPadding(new Insets(10));
		borderPane.setTop(vbox);
		borderPane.setRight(autoRateVBox);
		borderPane.setLeft(choices);
		borderPane.setBottom(tilePane);
		borderPane.setCenter(group);

		this.root = new Group();
		root.getChildren().add(borderPane);
		this.scene = new Scene(this.root, View.getCanvasWidth(), View.getCanvasHeight());
	}

	/**
	 * Formats the scroll Pane with the necessary attributes to be consistent
	 * 
	 * @param scroll --> a scroll pane
	 */
	public void scrollPaneFormat(ScrollPane scroll) {
		scroll.setPadding(new Insets(10, 0, 10, 30));
		scroll.setMaxWidth(View.getCanvasWidth() / 3);
		scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
		scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		scroll.setStyle(View.getWhiteBackgroundStyle() + "-fx-border-color: #F6AAA4;" + "-fx-border-insets: 5;"
				+ "-fx-border-width: 3;" + "-fx-border-style: solid;");

	}

	/**
	 * Function creates a flow pane (with Scroll) for the accordion bar selected.
	 * (Only for existing and selected plants).
	 * 
	 * @param canopy --> Takes in a canopy
	 */
	public FlowPane createFlowPane(Plant[] list) {

		FlowPane plantFlow = new FlowPane();

		/**
		 * TODO: set all of the plants here that correspond with canopy.
		 */
		/*
		 * This is a temporary addition to test if function works
		 */
		for (int i = 0; i < list.length; i++) {

			/**
			 * TODO: Get the images of the plants and add them here
			 */

			/**
			 * TODO: Add a mouse hover handler here to give info for each image
			 */

			/**
			 * TODO: Add dragging feature here...?
			 */

		}

		return plantFlow;

	}

	/**
	 * Creates the buttons that will be added to the tilePane at the bottom of the
	 * screen
	 */
	public void createButtons() {
		backButton = new Button("Go Back");
		backButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				switchToWindow(Windows.PlantSelection);
			}
		});

		saveButton = new Button("Save");
		saveButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// add saving function
			}
		});

		loadButton = new Button("Load");
		loadButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// add loading function
			}
		});

		nextButton = new Button("Next");
		nextButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				switchToWindow(Windows.SeasonView);
			}
		});

		List<Button> buttons = new ArrayList<Button>();
		buttons.add(backButton);
		buttons.add(saveButton);
		buttons.add(loadButton);
		buttons.add(nextButton);

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

	@Override
	public Scene getScene() {
		// TODO Auto-generated method stub
		return this.scene;
	}

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

	/**
	 * TODO: FIX DRAGGING
	 * 
	 * @param img
	 */
	public void addimage(ImageView img) {
		group.getChildren().add(img);
	}

	public void drag(MouseEvent event) {
		ImageView n = (ImageView) event.getSource();
		ImageView tmp = new ImageView(n.getImage());
		System.out.println(n.getX());
		xbound = n.getParent().getLayoutBounds().getMaxX();
		System.out.println(n.getScene().getWidth());
		if (!n.getParent().getLayoutBounds().contains(new Point2D(n.getX() + event.getX(), n.getY() + event.getY()))) {
			addimage(tmp);
		}

	}

	public EventHandler getHandlerForDrag() {
		return event -> drag((MouseEvent) event);
	}

	/**
	 * Remove everything from the flow panes, the center box, and the autorate boxes
	 * and add info back in again for the correct session
	 */
	public void refresh() {
		/**
		 * TODO: remove everything from flowpanes and add back in
		 */
		/**
		 * TODO: remove everything from center box and add back in
		 */
		/**
		 * TODO: remove everything from autorate boxes and add back in
		 */

	}
}
