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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import udel.GardenProject.enums.Seasons;
import udel.GardenProject.enums.Windows;
import udel.GardenProject.garden.Model;
import udel.GardenProject.garden.View;
import udel.GardenProject.plotObjects.PlotObject;
import udel.GardenProject.plotObjects.YDistanceComparator;

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
	 * buttons to move between screens
	 */
	private Button back, backToMain, next;

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

	private int inset5 = 5;
	private int inset10 = 10;
	private int inset20 = 20;
	private int gapBetweenButtons = 100;
	private int squareHeightAdjustment = 130;
	private int squareWidthAdjustment = 40;
	private int backgroundScreenWidthAndHeight = 100;
	private int textWrapAdjustment = 20;

	/**
	 * Create a SeasonView window instance.
	 *
	 * @param m Model
	 */
	public SeasonView(Model m) {
		super(m, "Garden Previewer", Windows.SeasonView);

		borderPane = new BorderPane();
		vbox = new VBox();
		layoutCenterVBox = new VBox();
		imageVBox = new VBox();
		toggleOptionsTilePane = new TilePane();
		tilePane = new TilePane();

		text = new Text("Select the season, year, and view you would like to see your Garden in!");
		text.setFont(getModel().getHackBold20());
		text.setWrappingWidth(View.getCanvasWidth() - textWrapAdjustment);
		vbox.setAlignment(Pos.CENTER);
		vbox.getChildren().addAll(text);

		createToggleGroups();
		createButtons();

		tilePane.setAlignment(Pos.CENTER);
		tilePane.setPadding(new Insets(inset5));
		tilePane.setHgap(gapBetweenButtons);
		tilePane.getChildren().addAll(back, backToMain, next);

		toggleOptionsTilePane.setAlignment(Pos.CENTER);
		toggleOptionsTilePane.setPadding(new Insets(inset5));
		toggleOptionsTilePane.setHgap(inset20);
		toggleOptionsTilePane.setVgap(inset20);

		viewDepth = View.getCanvasHeight() - tilePane.getHeight() - vbox.getHeight()
				- toggleOptionsTilePane.getHeight() - squareHeightAdjustment;
		viewWidth = View.getCanvasWidth() - squareWidthAdjustment;
		canvas = new Canvas(viewWidth, viewDepth);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		Image sky = new Image(getClass().getResourceAsStream("/viewImages/clouds.png"));
		Image grass = new Image(getClass().getResourceAsStream("/viewImages/grass.png"));
		gc.drawImage(sky, 0, 0, canvas.getWidth(), canvas.getHeight());
		gc.drawImage(grass, 0, canvas.getHeight() / 3 * 2, canvas.getWidth(), canvas.getHeight() / 3);
		drawCanvas(gc);

		square = new Rectangle();
		square.setHeight(View.getCanvasHeight() - tilePane.getHeight() - vbox.getHeight()
				- toggleOptionsTilePane.getHeight() - squareHeightAdjustment);
		square.setWidth(View.getCanvasWidth() - squareWidthAdjustment);
		square.setStroke(Color.BLACK);
		square.setFill(null);
		imageVBox.setPadding(new Insets(0, 0, inset20, 0));
		imageVBox.getChildren().add(square);

		toggleOptionsTilePane.getChildren().addAll(seasonHBox, yearHBox, viewHBox);

		layoutCenterVBox.getChildren().add(imageVBox);

		bottomBox = new VBox();
		bottomBox.getChildren().addAll(toggleOptionsTilePane, tilePane);

		Image image = new Image(getClass().getResourceAsStream(View.getBackgroundScreenPath()));
		View.setBackgroundScreen(image, backgroundScreenWidthAndHeight, backgroundScreenWidthAndHeight);

		borderPane.setBackground(View.getBackgroundScreen());

		borderPane.setPadding(new Insets(inset10));
		borderPane.setCenter(layoutCenterVBox);
		borderPane.setTop(vbox);
		borderPane.setBottom(bottomBox);

		this.root = new Group();

		root.getChildren().add(borderPane);
		this.scene = new Scene(this.root, View.getCanvasWidth(), View.getCanvasHeight());
	}

	/**
	 * Sends input from user to the session after user clicks NEXT
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

		backToMain = new Button("Main Menu");
		backToMain.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				switchToWindow(Windows.Welcome);
			}
		});

		next = new Button("Next");
		next.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				getInput();
				switchToWindow(Windows.Download);
			}
		});

		List<Button> buttons = new ArrayList<Button>();
		buttons.add(back);
		buttons.add(backToMain);
		buttons.add(next);

		for (Button b : buttons) {
			b.setFont(getModel().getHackBold12());
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
	 * Creates the toggles for season, year, and view with handling
	 */
	public void createToggleGroups() {
		seasonHBox = new HBox();
		seasonGroup = new ToggleGroup();
		Seasons[] seasonSelection = Seasons.values();
		ObservableList<String> seasonPick = FXCollections.observableArrayList();
		for (Seasons s : seasonSelection) {
			if (s == Seasons.YEARROUND) {
				continue;
			}
			
			ToggleButton toggle = new ToggleButton(s.getSeason());
			createToggleEvent(toggle);
			seasonPick.add(s.getSeason()); // adds the seasons to an observable list
			toggle.setToggleGroup(seasonGroup);
			seasonHBox.getChildren().add(toggle);
			toggle.setOnAction((ActionEvent e) -> {
				chooseSeason=s;
				Image sky, ground;
				GraphicsContext gc = canvas.getGraphicsContext2D();
				switch(s) {
				case WINTER:
					sky = new Image(getClass().getResourceAsStream("/viewImages/overcast.png"));
					ground = new Image(getClass().getResourceAsStream("/viewImages/snow.png"));
					gc.drawImage(sky, 0, 0, canvas.getWidth(), canvas.getHeight());
					gc.drawImage(ground, 0, canvas.getHeight()/3*2, canvas.getWidth(), canvas.getHeight()/3);
					drawCanvas(gc);
					break;
				case FALL:
					sky = new Image(getClass().getResourceAsStream("/viewImages/overcast.png"));
					ground = new Image(getClass().getResourceAsStream("/viewImages/grass.png"));
					gc.drawImage(sky, 0, 0, canvas.getWidth(), canvas.getHeight());
					gc.drawImage(ground, 0, canvas.getHeight()/3*2, canvas.getWidth(), canvas.getHeight()/3);
					drawCanvas(gc);
					break;
				default:
					sky = new Image(getClass().getResourceAsStream("/viewImages/clouds.png"));
					ground = new Image(getClass().getResourceAsStream("/viewImages/grass.png"));
					gc.drawImage(sky, 0, 0, canvas.getWidth(), canvas.getHeight());
					gc.drawImage(ground, 0, canvas.getHeight()/3*2, canvas.getWidth(), canvas.getHeight()/3);
					drawCanvas(gc);
					break;
				}
			});
		}

		yearHBox = new HBox();
		yearGroup = new ToggleGroup();
		String[] yearSelection = { "0 YEARS", "1 YEAR", "2 YEARS" };
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
		String[] viewSelection = { "TOP VIEW", "WINDOW VIEW" };
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

		viewGroup.getSelectedToggle();

	}

	/**
	 * Sets style and effects for the toggle buttons
	 * 
	 * @param b Each toggle button
	 */
	public void createToggleEvent(ToggleButton b) {

		DropShadow shadow = new DropShadow();
		String notHover = "-fx-base: #76C327;" + View.getBlackTextFill() + "-fx-focus-color: #3D6447;"
				+ "-fx-outer-border: #63A331;";
		String hover = "-fx-base: white;" + View.getBlackTextFill() + "-fx-focus-color: #3D6447;"
				+ "-fx-outer-border: #63A331;";

		b.setFont(getModel().getHackBold12());
		b.setStyle(notHover);
		b.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				b.setEffect(shadow);
				b.setStyle(hover);
			}
		});

		b.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				b.setEffect(null);
				b.setStyle(notHover);
			}
		});

	}

	/**
	 * Refreshes the screen to clear any of the toggles chosen
	 */
	public void refresh() {
		// TODO: drawCanvas() doesn't need to be called here?
		ToggleGroup[] tg = { seasonGroup, yearGroup, viewGroup };
		for (ToggleGroup group : tg) {
			if (group.getSelectedToggle() != null) {
				group.getSelectedToggle().setSelected(false);
			}
		}
	}

	/**
	 * Draws all the plot objects on the canvas for the window view.
	 * 
	 * @param gc GraphicsContext that corresponds to window view canvas
	 */
	public void drawCanvas(GraphicsContext gc) {
		gc.setFill(Color.rgb(140, 140, 140, .25));
		ArrayList<PlotObject> plot = this.getModel().getSession().getPlot();
		Collections.sort(plot, new YDistanceComparator());
		DropShadow shadow = new DropShadow();
		for (PlotObject po : plot) {
			factor = .3;
			if (po.getPlotY() / MAXDEPTH > factor) {
				factor = po.getPlotY() / MAXDEPTH;
			}
			Image i = new Image(po.getWindowImage());
			gc.fillOval(po.getPlotX() / MAXWIDTH * viewWidth - (i.getWidth() / 2 * factor),
					po.getPlotY() / MAXDEPTH * (viewDepth / 3) - (i.getHeight() / 3 * factor) + viewDepth / 3 * 2,
					i.getWidth() * factor, i.getHeight() / 2 * factor);
		}
		for (PlotObject po : plot) {
			factor = .3;
			if (po.getPlotY() / MAXDEPTH > factor) {
				factor = po.getPlotY() / MAXDEPTH;
			}
			Image i = new Image(po.getWindowImage());
			gc.setEffect(shadow);
			gc.drawImage(i, po.getPlotX() / MAXWIDTH * viewWidth - (i.getWidth() / 2 * factor),
					po.getPlotY() / MAXDEPTH * (viewDepth / 3) - (i.getHeight() * factor) + viewDepth / 3 * 2,
					i.getWidth() * factor, i.getHeight() * factor);
		}

	}
}
