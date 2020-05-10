package udel.GardenProject.windows;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
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
import udel.GardenProject.plants.Plant;

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
	 * Create a SeasonView window instance.
	 *
	 * @param m Model
	 */
	private int inset5 = 5;
	private int inset10 = 10;
	private int inset20 = 20;
	private int gapBetweenButtons = 100;
	private int squareHeightAdjustment = 130;
	private int squareWidthAdjustment = 20;
	private int backgroundScreenWidthAndHeight = 100;
	private int textWrapAdjustment = 20;

	public SeasonView(Model m) {
		super(m, "Garden Previewer");

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
		List<String> seasonSelection = List.of("SPRING", "SUMMER", "WINTER", "FALL");
		ObservableList<String> seasonPick = FXCollections.observableArrayList();
		for (String s : seasonSelection) {
			ToggleButton toggle = new ToggleButton(s);
			createToggleEvent(toggle);
			seasonPick.add(s); // adds the seasons to an observable list
			toggle.setToggleGroup(seasonGroup);
			seasonHBox.getChildren().add(toggle);
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
					chosenView = "TOP";
				}
				if (v.equals("WINDOW VIEW")) {
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
		ToggleGroup[] tg = { seasonGroup, yearGroup, viewGroup };
		for (ToggleGroup group : tg) {
			if (group.getSelectedToggle() != null) {
				group.getSelectedToggle().setSelected(false);
			}
		}
	}

}
