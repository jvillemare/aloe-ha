package udel.GardenProject.windows;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import udel.GardenProject.enums.GardenView;
import udel.GardenProject.enums.Seasons;
import udel.GardenProject.enums.Windows;
import udel.GardenProject.enums.Year;
import udel.GardenProject.garden.Model;
import udel.GardenProject.garden.View;
import udel.GardenProject.plotObjects.PlotObject;
import udel.GardenProject.plotObjects.YDistanceComparator;

/**
 * Preview the garden as it will appear in every season and 1, 2, and 3 years
 * down the line.
 *
 * @version 1.0
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
	 * Graphics Context for canvas.
	 */
	private GraphicsContext gc;

	/**
	 * Final toggle the user chose for season. Defaults to Summer.
	 */
	public Seasons chooseSeason = Seasons.SUMMER;

	/**
	 * Final toggle the user chose for the year
	 */
	public Year chosenYear;

	/**
	 * Final toggle the user chose for the type of view
	 */
	public GardenView chosenView;

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
	private final int MAXDEPTH = 600;

	/**
	 * Maximum width a plot object may be placed on plot design
	 */
	private final int MAXWIDTH = 750;
	
	/**
	 * Minimum amount of particles that may be drawn for snow or leaves.
	 */
	private int minRandomParticles = 50;
	
	/**
	 * Maximum amount of particles that may be drawn for snow or leaves.
	 */
	private int maxRandomParticles = 100;
	
	/**
	 * Effect that will be used on plants to show change in season.
	 */
	private Effect effect;
	
	/**
	 * Size of ovals for snow in winter season.
	 */
	private int snowSize = 4;
	
	/**
	 * Size of images for falling leaf 1 for fall season.
	 */
	private int leafSize1 = 50;
	
	/**
	 * Size of images for falling leaf 2 for fall season. 
	 */
	private int leafSize2 = 20;
	
	/**
	 * Minimum scaling down for image in window view in regards to distance.
	 */
	private double minScale = .3;
	
	/**
	 * Current scale size in regards to year.
	 */
	private double curYearScale = 1;
	
	/**
	 * Amount to scale for Year 0.
	 */
	private double scale0 = .25;
	
	/**
	 * Amount to scale for Year 1.
	 */
	private double scale1 = .5;
	
	/**
	 * Amount to scale for Year 2.
	 */
	private double scale2 = 1;
	
	/**
	 * Image that will be used for sky in window view.
	 */
	private Image sky = new Image(getClass().getResourceAsStream("/viewImages/blueSky.png"));
	
	/**
	 * Image that will be used for ground in window view.
	 */
	private Image ground = new Image(getClass().getResourceAsStream("/viewImages/grass.png"));
	
	/**
	 * ColorAdjust specifically for the background images.
	 */
	ColorAdjust bgAdjust = new ColorAdjust();

	private int inset5 = 5;
	private int inset10 = 10;
	private int inset20 = 20;
	private int gapBetweenButtons = 100;
	private int squareHeightAdjustment = 130;
	private int squareWidthAdjustment = 40;
	private int backgroundScreenWidthAndHeight = 100;
	private int textWrapAdjustment = 20;
	private int subImageX = 10;
	private int subImageY = 35;
	private int subImageWidth = 1260;
	private int subImageHeight = 570;

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

		text = new Text("Select different seasons and years for your garden. Click 'Next' to go to the download screen.");
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
		gc = canvas.getGraphicsContext2D();
		drawBackground();
		drawCanvas();

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
		return this.scene;
	}

	/**
	 * Buttons for navigation are created and handled.
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
				
				/*
				 * Gets bounds from screen and creates a Buffered Image to be sent to session
				 */
				try {
			        Bounds bounds = root.getBoundsInLocal();
			        Bounds screenBounds = root.localToScreen(bounds);
			        int x = (int) screenBounds.getMinX();
			        int y = (int) screenBounds.getMinY();
			        int width = (int) screenBounds.getWidth();
			        int height = (int) screenBounds.getHeight();
			        java.awt.Rectangle screenRect = new java.awt.Rectangle(x, y, width, height);
			        BufferedImage capture = new Robot().createScreenCapture(screenRect);
			        capture = capture.getSubimage(subImageX, subImageY, subImageWidth, subImageHeight);
			        getSession().setScreenShot(capture);
			    } catch (AWTException ex) {
			        ex.printStackTrace();
			    }
				
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
	 * Creates the toggles for season, year, and view with handling.
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
				ColorAdjust hue = new ColorAdjust();
				switch(s) {
				case WINTER:
					hue.setHue(-.4);
					this.effect = hue;
					sky = new Image(getClass().getResourceAsStream("/viewImages/overcast.png"));
					ground = new Image(getClass().getResourceAsStream("/viewImages/snow.png"));
					this.bgAdjust.setHue(0);
					break;
				case FALL:
					hue.setHue(-.2);
					this.effect = hue;
					sky = new Image(getClass().getResourceAsStream("/viewImages/clouds.png"));
					ground = new Image(getClass().getResourceAsStream("/viewImages/grass.png"));
					this.bgAdjust.setHue(-.1);
					break;
				default:
					hue.setHue(0);
					this.effect = hue;
					sky = new Image(getClass().getResourceAsStream("/viewImages/blueSky.png"));
					ground = new Image(getClass().getResourceAsStream("/viewImages/grass.png"));
					this.bgAdjust.setHue(0);
					break;
				}
				drawCanvas();
			});
		}

		yearHBox = new HBox();
		yearGroup = new ToggleGroup();
		Year[] years = Year.values();
		ObservableList<String> yearPick = FXCollections.observableArrayList();
		for (Year y : years) {
			ToggleButton toggle = new ToggleButton(y.getYear());
			createToggleEvent(toggle);
			yearPick.add(y.getYear()); // adds the seasons to an observable list
			toggle.setToggleGroup(yearGroup);
			yearHBox.getChildren().add(toggle);
			toggle.setOnAction((ActionEvent e) -> {
				chosenYear = y;
				switch(chosenYear) {
				case YEAR0:
					this.curYearScale = scale0;
					break;
				case YEAR1:
					this.curYearScale = scale1;
					break;
				case YEAR2:
					this.curYearScale = scale2;
					break;
				}
				drawCanvas();
			});
		}

		viewHBox = new HBox();
		viewGroup = new ToggleGroup();
		GardenView[] views = GardenView.values();
		ObservableList<String> viewPick = FXCollections.observableArrayList();
		for (GardenView v : views) {
			ToggleButton toggle = new ToggleButton(v.getView());
			createToggleEvent(toggle);
			viewPick.add(v.getView());
			toggle.setToggleGroup(viewGroup);
			viewHBox.getChildren().add(toggle);
			toggle.setOnAction((ActionEvent e) -> {
				chosenView = v;
				switch(v) {
				case TOPVIEW:
					imageVBox.getChildren().set(0, square);
					break;
				case WINDOWVIEW:
					imageVBox.getChildren().set(0, canvas);
					break;
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
	 * Refreshes the screen to clear any of the toggles chosen.
	 */
	public void refresh() {
		/**
		 * TODO: Clear screen (Plot image) and add back in from session
		 */
		ToggleGroup[] tg = { seasonGroup, yearGroup, viewGroup };
		for (ToggleGroup group : tg) {
			if (group.getSelectedToggle() != null) {
				group.getSelectedToggle().setSelected(false);
			}
		}
		drawCanvas();
	}

	/**
	 * Draws all the plot objects on the canvas for the window view.
	 */
	public void drawObjects() {
		gc.setFill(Color.rgb(140, 140, 140, .2));
		ArrayList<PlotObject> plot = this.getModel().getSession().getPlot();
		Collections.sort(plot, new YDistanceComparator());
		GaussianBlur gb = new GaussianBlur();
		gc.setFill(Color.rgb(140, 140, 140, .25));
		gb.setRadius(inset10);
		for (PlotObject po : plot) {
			po.windowRender(gc, gb, minScale, MAXDEPTH, MAXWIDTH, viewDepth, viewWidth, curYearScale, this.effect);
		}
		gc.setEffect(null);

	}
	
	/**
	 * Draws ground and sky for the window view.
	 */
	public void drawBackground() {
		gc.setEffect(this.bgAdjust);
		gc.drawImage(sky, 0, 0, canvas.getWidth(), canvas.getHeight());
		gc.drawImage(ground, 0, canvas.getHeight() / 3 * 2, canvas.getWidth(), canvas.getHeight() / 3);
		gc.setEffect(this.effect);
	}
	
	/**
	 * Randomly draws leaves or snow on the window view based on season.
	 */
	public void drawRandom() {
		int numPart = (int) (Math.random() * ((maxRandomParticles - minRandomParticles) + 1)) + minRandomParticles;
		switch (this.chooseSeason) {
		case FALL:
			Image[] leaves = {
					new Image(getClass().getResourceAsStream("/viewImages/fallLeaf1.png"), leafSize1, leafSize1, true,
							false),
					new Image(getClass().getResourceAsStream("/viewImages/fallLeaf2.png"), leafSize2, leafSize2, true,
							false) };
			for (int i = 0; i < numPart; i++) {
				gc.drawImage(leaves[(int) (Math.random() * 2)], (Math.random() * ((viewWidth) + 1)),
						(Math.random() * ((viewDepth) + 1)));
			}
			return;
		case WINTER:
			for (int i = 0; i < numPart; i++) {
				gc.setFill(Color.rgb(255, 255, 255, Math.random()));
				gc.fillOval((Math.random() * ((viewWidth) + 1)), (Math.random() * ((viewDepth) + 1)), snowSize,
						snowSize);
			}
			return;
		default:
			return;
		}
	}
	
	/**
	 * Combines all of the drawing methods into a single method.
	 * Clears Canvas and then redraws it.
	 */
	public void drawCanvas() {
		gc.clearRect(0, 0, viewWidth, viewDepth);
		drawBackground();
		drawObjects();
		drawRandom();
	}
	
}