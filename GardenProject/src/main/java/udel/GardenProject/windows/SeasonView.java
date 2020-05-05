package udel.GardenProject.windows;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import udel.GardenProject.enums.Canopy;
import udel.GardenProject.enums.Seasons;
import udel.GardenProject.enums.Windows;
import udel.GardenProject.garden.Model;
import udel.GardenProject.garden.View;
import udel.GardenProject.plants.Plant;
import udel.GardenProject.plotObjects.PlotObject;
import udel.GardenProject.plotObjects.PlotPlant;
import udel.GardenProject.plotObjects.YDistanceComparator;
import udel.GardenProject.plotObjects.special.PlotFlamingo;

/**
 * Preview the garden as it will appear in every season and 1, 2, and 3 years
 * down the line.
 *
 * @author Team 0
 */
public class SeasonView extends Window {

	private Group root;
	private Scene scene;

	/**
	 * Used for main layout of seasonView
	 */
	private BorderPane borderPane;

	/**
	 * vbox --> info at the top imageVBox --> where the image will be shown to user
	 * (center) layoutCenterVBox --> holds imageVBox
	 */
	private VBox vbox, imageVBox, layoutCenterVBox;

	/**
	 * text --> for the message at the top of the screen
	 */
	private Text text;

	/**
	 * tilePane --> used for buttons at the bottom toggleOptionsTilePane --> used
	 * for toggle options
	 */
	private TilePane tilePane, toggleOptionsTilePane;

	/**
	 * buttons to move between screens and save user input
	 */
	private Button back, save, next;

	/**
	 * Used for grouping different toggle selections
	 */
	private ToggleGroup seasonGroup, yearGroup, viewGroup;

	/**
	 * Used to hold the toggle groups
	 */
	private HBox seasonHBox, yearHBox, viewHBox;

	/**
	 * Prospective area where the image of the garden plot should be
	 */
	private Rectangle square;
	
	/**
	 * Canvas to draw plants on for window view
	 */
	private Canvas canvas;

	/**
	 * Final toggle the user chose for season
	 */
	public Seasons chooseSeason;

	/**
	 * Final toggle the user chose for the year
	 */
	public int chosenYear;

	/**
	 * Final toggle the user chose for the type of view
	 */
	public String chosenView;

	/**
	 * Holds the toggle options and the bottom navigation buttons
	 */
	public VBox bottomBox;
	
	/**
	 * Width of garden on the window.
	 */
	private double viewWidth;
	
	/**
	 * Depth of garden on the window.
	 */
	private double viewDepth;
	
	/**
	 * Maximum depth a plot object may be placed on plot desigh
	 */
	private final int MAXDEPTH = 550;
	
	/**
	 * Maximum width a plot object may be placed on plot design
	 */
	private final int MAXWIDTH = 620;
	
	/**
	 * Factor for scaling images in the window view
	 */
	private double factor;

	/**
	 * Create a SeasonView window instance.
	 *
	 * @param m Model
	 */
	
	int inset5 = 5;
	int buttonTextSize = 12;
	int buttonPrefWidth = 100;
	int topTextSize = 20;
	int textWrapAdjustment = 20;
	int buttonGap = 100;
	

	public SeasonView(Model m) {
		super(m, "Garden Previewer");

		borderPane = new BorderPane();
		vbox = new VBox();
		layoutCenterVBox = new VBox();
		imageVBox = new VBox();
		toggleOptionsTilePane = new TilePane();
		tilePane = new TilePane();

		text = new Text("Select the season, year, and view you would like to see your Garden in!");
		text.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/Hack-Bold.ttf"), topTextSize));
		text.setWrappingWidth(View.getCanvasWidth() - textWrapAdjustment);
		vbox.setAlignment(Pos.CENTER);
		vbox.getChildren().addAll(text);

		createToggleGroups();
		createButtons();

		tilePane.setAlignment(Pos.CENTER);
		tilePane.setPadding(new Insets(5));
		tilePane.setHgap(buttonGap);
		tilePane.getChildren().addAll(back, save, next);

		toggleOptionsTilePane.setAlignment(Pos.CENTER);
		toggleOptionsTilePane.setPadding(new Insets(inset5));
		toggleOptionsTilePane.setHgap(20);
		toggleOptionsTilePane.setVgap(20);

		
		viewDepth = View.getCanvasHeight() - tilePane.getHeight() - vbox.getHeight()
				- toggleOptionsTilePane.getHeight() - 130;
		viewWidth = View.getCanvasWidth() - 20;
		canvas = new Canvas(viewWidth, viewDepth);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		Image sky = new Image(getClass().getResourceAsStream("/windowImages/clouds.png"));
		gc.drawImage(sky, 0, 0, canvas.getWidth(), canvas.getHeight());
		gc.setFill(Color.FORESTGREEN);
		gc.fillRect(0, canvas.getHeight()/3*2, canvas.getWidth(), canvas.getHeight()/3);
		drawCanvas(gc);
		
		square = new Rectangle();
		square.setHeight(View.getCanvasHeight() - tilePane.getHeight() - vbox.getHeight()
				- toggleOptionsTilePane.getHeight() - 130);
		square.setWidth(View.getCanvasWidth() - 20);
		square.setStroke(Color.BLACK);
		square.setFill(null);
		imageVBox.setPadding(new Insets(0, 0, 20, 0));
		imageVBox.getChildren().add(square);

		toggleOptionsTilePane.getChildren().addAll(seasonHBox, yearHBox, viewHBox);

		Image image = new Image(getClass().getResourceAsStream("/buttonImages/splash2.png"));
		BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
		BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
		Background background = new Background(backgroundImage);

		layoutCenterVBox.getChildren().add(imageVBox);

		bottomBox = new VBox();
		bottomBox.getChildren().addAll(toggleOptionsTilePane, tilePane);

		borderPane.setBackground(background);
		borderPane.setPadding(new Insets(10));
		borderPane.setCenter(layoutCenterVBox);
		borderPane.setTop(vbox);
		borderPane.setBottom(bottomBox);

		this.root = new Group();

		root.getChildren().add(borderPane);
		this.scene = new Scene(this.root, View.getCanvasWidth(), View.getCanvasHeight());
	}

	/**
	 * Sends input from user to the session after user clicks SAVE
	 */
	public void getInput() {

		getSession().setSeasonInput(chooseSeason);
		getSession().setYearInput(chosenYear);
		getSession().setViewInput(chosenView);

	}

	@Override
	public Scene getScene() {
		// TODO Auto-generated method stub
		return this.scene;
	}

	/**
	 * Buttons for navigation are created and handled
	 */
	public void createButtons() {

		back = new Button("Go Back");
		back.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				switchToWindow(Windows.PlotDesign);
			}
		});

		save = new Button("Save");
		save.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				getInput();
			}
		});

		next = new Button("Next");
		next.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				switchToWindow(Windows.Download);
			}
		});

		List<Button> buttons = new ArrayList<Button>();
		buttons.add(back);
		buttons.add(save);
		buttons.add(next);

		for (Button b : buttons) {
			b.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/Hack-Bold.ttf"), buttonTextSize));
			b.setStyle("-fx-background-color: #76C325;" + "-fx-text-fill: #000000;");
			b.setPrefWidth(buttonPrefWidth);

			DropShadow shadow = new DropShadow();
			b.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					b.setEffect(shadow);
					b.setStyle("-fx-background-color: #FFFFFF;" + "-fx-text-fill: #000000;");
				}
			});

			b.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					b.setEffect(null);
					b.setStyle("-fx-background-color: #76C325;" + "-fx-text-fill: #000000;");
				}
			});

		}

	}

	/**
	 * Creates the toggles for season, year, and view with handling
	 */
	public void createToggleGroups() {
		seasonHBox = new HBox();
		seasonGroup = new ToggleGroup();
		List<String> seasonSelection = List.of("SPRING", "SUMMER", "WINTER", "FALL");
		ObservableList<String> seasonPick = FXCollections.observableArrayList();
		for (String s : seasonSelection) {
			ToggleButton toggle = new ToggleButton(s);
			seasonPick.add(s); // adds the seasons to an observable list
			toggle.setToggleGroup(seasonGroup);
			seasonHBox.getChildren().add(toggle);
			createToggleEvent(toggle);
			toggle.setOnAction((ActionEvent e) -> {
				if (s.equals("SUMMER")) {
					chooseSeason = Seasons.SUMMER;
				}
				if (s.equals("SPRING")) {
					chooseSeason = Seasons.SPRING;
				}
				if (s.equals("FALL")) {
					chooseSeason = Seasons.FALL;
				}
				if (s.equals("WINTER")) {
					chooseSeason = Seasons.WINTER;
				}
			});
		}

		yearHBox = new HBox();
		yearGroup = new ToggleGroup();
		List<String> yearSelection = List.of("0 YEARS", "1 YEAR", "2 YEARS");
		ObservableList<String> yearPick = FXCollections.observableArrayList();
		for (String y : yearSelection) {
			ToggleButton toggle = new ToggleButton(y);
			createToggleEvent(toggle);
			yearPick.add(y); // adds the seasons to an observable list
			toggle.setToggleGroup(yearGroup);
			yearHBox.getChildren().add(toggle);
			toggle.setOnAction((ActionEvent e) -> {
				if (y.equals("0 YEARS")) {
					chosenYear = 0;
				}
				if (y.equals("1 YEAR")) {
					chosenYear = 1;
				}
				if (y.equals("2 YEARS")) {
					chosenYear = 2;
				}
			});
		}

		viewHBox = new HBox();
		viewGroup = new ToggleGroup();
		List<String> viewSelection = List.of("TOP VIEW", "WINDOW VIEW");
		ObservableList<String> viewPick = FXCollections.observableArrayList();
		for (String v : viewSelection) {
			ToggleButton toggle = new ToggleButton(v);
			createToggleEvent(toggle);
			viewPick.add(v);
			toggle.setToggleGroup(viewGroup);
			viewHBox.getChildren().add(toggle);
			toggle.setOnAction((ActionEvent e) -> {
				if (v.equals("TOP VIEW")) {
					imageVBox.getChildren().set(0, square);
					chosenView = "TOP";
				}
				if (v.equals("WINDOW VIEW")) {
					imageVBox.getChildren().set(0, canvas);
					chosenView = "WINDOW";
				}
			});
		}

	}

	public void createToggleEvent(ToggleButton b) {

		DropShadow shadow = new DropShadow();

		b.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/Hack-Bold.ttf"), buttonTextSize));
		b.setStyle("-fx-background-color: #76C325;" + "-fx-text-fill: #000000;");
		b.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				if (b.isSelected()) {
					b.setEffect(shadow);
					b.setStyle("-fx-background-color: #FFFFFF;" + "-fx-text-fill: #000000;");
				} else {
					b.setEffect(null);
					b.setStyle("-fx-background-color: #76C325;" + "-fx-text-fill: #000000;");
				}
			}
		});
	}
	
	public void drawCanvas(GraphicsContext gc) {
		//ArrayList<PlotObject> plot = this.getModel().getSession().getPlot();
		ArrayList<PlotObject> plot = new ArrayList<>();
		plot.add(new PlotPlant(new Plant(null, null, null, null, 0, null, null, Canopy.FLOOR, true, false, null, null), 50, MAXDEPTH));
		plot.add(new PlotPlant(new Plant(null, null, null, null, 0, null, null, Canopy.FLOOR, true, false, null, null), 180, MAXDEPTH));
		plot.add(new PlotPlant(new Plant(null, null, null, null, 0, null, null, Canopy.FLOOR, true, false, null, null), 290, MAXDEPTH));
		plot.add(new PlotPlant(new Plant(null, null, null, null, 0, null, null, Canopy.UNDERSTORY, true, false, null, null), 225, 450));
		plot.add(new PlotPlant(new Plant(null, null, null, null, 0, null, null, Canopy.UNDERSTORY, true, false, null, null), 25, 400));
		plot.add(new PlotPlant(new Plant(null, null, null, null, 0, null, null, Canopy.UNDERSTORY, true, false, null, null), 530, 390));
		plot.add(new PlotPlant(new Plant(null, null, null, null, 0, null, null, Canopy.CANOPY, true, false, null, null), 100, 180));
		plot.add(new PlotPlant(new Plant(null, null, null, null, 0, null, null, Canopy.EMERGENT, true, false, null, null), 350, 175));
		plot.add(new PlotPlant(new Plant(null, null, null, null, 0, null, null, Canopy.EMERGENT, true, false, null, null), 150, 230));
		plot.add(new PlotFlamingo(225, 100, 3));
		plot.add(new PlotFlamingo(400, 405, 3));
		plot.add(new PlotPlant(new Plant(null, null, null, null, 0, null, null, Canopy.CANOPY, true, false, null, null), 395, 350));
		plot.add(new PlotPlant(new Plant(null, null, null, null, 0, null, null, Canopy.EMERGENT, true, false, null, null), 475, 500));
		Image floor = new Image(getClass().getResourceAsStream("/buttonImages/flower1.png"), 104, 126, true, false);
		Image understory = new Image(getClass().getResourceAsStream("/buttonImages/bush1.png"), 175, 231, true, false);
		Image canopy = new Image(getClass().getResourceAsStream("/buttonImages/tree1.png"), 611, 380, true, false);
		Image emergent = new Image(getClass().getResourceAsStream("/buttonImages/tree3.png"), 581, 465, true, false);
		Image obstacle = new Image(getClass().getResourceAsStream("/buttonImages/boulder.png"), 126, 76, true, false);
		Collections.sort(plot, new YDistanceComparator());
		for (PlotObject po : plot) {
			double factor = .3;
			if (po.getPlotY()/MAXDEPTH > factor) {
				factor = po.getPlotY()/MAXDEPTH; 
			}
			gc.setEffect(new DropShadow());
			if (po.getClass().equals(PlotPlant.class)) {
				if (po.getHeight() <= 15) {
					gc.drawImage(floor, po.getPlotX()/MAXWIDTH*viewWidth - (floor.getWidth()/2*factor), po.getPlotY()/MAXDEPTH*(viewDepth/3) - (floor.getHeight()*factor) + viewDepth/3*2, floor.getWidth() * factor, floor.getHeight() * factor);
				}
				else if(po.getHeight() <= 55) {
					gc.drawImage(understory, po.getPlotX()/MAXWIDTH*viewWidth - (understory.getWidth()/2*factor), po.getPlotY()/MAXDEPTH*(viewDepth/3) - (understory.getHeight()*factor) + viewDepth/3*2, understory.getWidth() * factor, understory.getHeight() * factor);
				}
				else if(po.getHeight() <= 95) {	
					gc.drawImage(canopy, po.getPlotX()/MAXWIDTH*viewWidth - (canopy.getWidth()/2*factor), po.getPlotY()/MAXDEPTH*(viewDepth/3) - (canopy.getHeight()*factor) + viewDepth/3*2, canopy.getWidth() * factor, canopy.getHeight() * factor);
				}
				else {
					gc.drawImage(emergent, po.getPlotX()/MAXWIDTH*viewWidth - (emergent.getWidth()/2*factor), po.getPlotY()/MAXDEPTH*(viewDepth/3) - (emergent.getHeight()*factor) + viewDepth/3*2, emergent.getWidth() * factor, emergent.getHeight() * factor);
				}
			}
			else {
				gc.drawImage(obstacle, po.getPlotX()/MAXWIDTH*viewWidth - (obstacle.getWidth()/2*factor), po.getPlotY()/MAXDEPTH*(viewDepth/3) - (obstacle.getHeight()*factor) + viewDepth/3*2, obstacle.getWidth() * factor, obstacle.getHeight() * factor);
			}
		}
		
	}

}
