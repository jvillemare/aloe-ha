package udel.GardenProject.windows;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.CacheHint;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import udel.GardenProject.enums.Windows;
import udel.GardenProject.garden.Model;
import udel.GardenProject.garden.View;
import udel.GardenProject.plants.Plant;

/**
 * Where a user can specify what plants already exist in their garden.
 *
 * @version 1.0
 * @author Team 0
 */
public class ExistingPlants extends Window {

	private Group root;
	private Scene scene;
	private BorderPane borderPane;
	private VBox vbox;

	/**
	 * Used for the buttons at the top of the screen
	 */
	private Button backToMain, nextButton;

	/**
	 * Used to remove typing in search bar
	 */
	private Button close;

	/**
	 * Used for the search box and the options that show up
	 */
	private GridPane container;

	/**
	 * Used to put the container and X button together
	 */
	private HBox searchBox;

	/**
	 * Used for top message
	 */
	private Text text1;

	/**
	 * This is the textfield the user types into as the "search"
	 */
	private TextField text;

	/**
	 * Used to place button for the bottom of the borderPane
	 */
	private TilePane tilePane;

	/**
	 * Used to help user look through all the choices they have made
	 */
	private ScrollPane scroll;

	/**
	 * Used for the container for the dropdown menu when the user is typing and
	 * there are too many plants to fit on the screen
	 */
	private ScrollPane containerScroll;

	/**
	 * Used for displaying what the user selected
	 */
	private static VBox selection;

	/**
	 * Hashmap with the name of the plant as the key and the plant as the value
	 */
	private HashMap<String, Plant> dropDownPlants;

	/**
	 * TODO: What is?...
	 */
	private String[] plantImageLinks;

	/**
	 * Text that appears if no no plants fit the description.
	 */
	private String noPlants = "No Such Plants";

	/**
	 * Text that appears if more characters need to be added.
	 */
	private String moreCharacters = "Please Add More Characters in Search";

	/**
	 * Warning for invasive plants.
	 */
	private Alert warning = new Alert(AlertType.WARNING);

	/**
	 * Used for the user to type in the search box
	 */
	private int inset5 = 5;
	private int inset10 = 10;
	private int inset8 = 8;
	private int inset20 = 20;
	private int buttonGap = 100;
	private int wrapTextAdjustment = 860;
	private int selectedPlantHBoxSize = 50;
	private int selectedPlantFontSize = 15;
	private int scrollWidthAdjustment = 12;
	private int scrollHeightAdjustment = 35;
	private int backgroundScreenWidth = 100;
	private int backgroundScreenHeight = 100;
	private int tooltipImageWidthAndHeight = 300;
	private String boldFontWeight = "-fx-font-weight: bold";
	private int containerScrollPrefHeight = (View.getCanvasHeight() - 50);
	private int selectedPlantWrappingWidth = View.getCanvasWidth() / 9 * 5;
	private int containerScrollPrefWidth = (View.getCanvasWidth() / 3 - 10);

	/**
	 * Default Image to size.
	 */
	private Image defaultImg = getModel().getDefaultImage(tooltipImageWidthAndHeight, tooltipImageWidthAndHeight);

	/**
	 * Create an ExistingPlants window instance.
	 *
	 * @param m Model
	 */
	public ExistingPlants(Model m) {
		super(m, "What Plants are in your Garden?", Windows.ExistingPlants);

		borderPane = new BorderPane();
		vbox = new VBox();
		container = new GridPane();
		container.setPadding(new Insets(10));
		searchBox = new HBox();
		tilePane = new TilePane();

		selection = new VBox();
		selection.setPadding(new Insets(inset5));
		selection.setStyle(View.getPinkBackgroundStyle());

		text1 = new Text(
				"Please type the name of the plant(s) you have in your garden in the" + 
				" search box on the left by its Latin OR common name. Once you " +
				"have found your plant, click on its name to add it to your list of" + 
				" existing plants. If you don't have any existing plants in your garden," + 
				" click 'Next' to continue.");

		text1.setWrappingWidth(wrapTextAdjustment);
		text1.setFont(getModel().getHackBold20());
		vbox.setStyle(View.getPinkBackgroundStyle());
		vbox.getChildren().addAll(text1);
		vbox.setPadding(new Insets(inset10));

		createSearch();
		createButtons();

		tilePane.setAlignment(Pos.CENTER);
		tilePane.setPadding(new Insets(inset5));
		tilePane.setHgap(buttonGap);
		tilePane.getChildren().addAll(backToMain, nextButton);

		vbox.getChildren().add(selection);

		scroll = new ScrollPane();
		scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		scroll.setPrefSize(View.getCanvasWidth() / 3 * 2 + scrollWidthAdjustment,
				View.getCanvasHeight() - vbox.getHeight() - tilePane.getHeight() - scrollHeightAdjustment);
		scroll.setContent(vbox);

		Image image = new Image(getClass().getResourceAsStream(View.getBackgroundScreenPath()));
		View.setBackgroundScreen(image, backgroundScreenWidth, backgroundScreenHeight);

		borderPane.setBackground(View.getBackgroundScreen());
		borderPane.setRight(scroll);
		borderPane.setLeft(containerScroll);
		borderPane.setCenter(vbox);
		borderPane.setBottom(tilePane);

		this.root = new Group();
		root.getChildren().add(borderPane);
		this.scene = new Scene(this.root, View.getCanvasWidth(), View.getCanvasHeight());
	}

	@Override
	public Scene getScene() {
		// TODO Auto-generated method stub
		return this.scene;
	}

	/**
	 * Creating buttons at the bottom of the screen
	 */
	public void createButtons() {

		backToMain = new Button("Go Back");
		backToMain.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				switchToWindow(Windows.Welcome);
			}
		});

		nextButton = new Button("Next");
		nextButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				switchToWindow(Windows.Questionnaire);
			}
		});

		/*
		 * Setting Style and Effects for Buttons
		 */
		List<Button> buttons = new ArrayList<Button>();
		buttons.add(backToMain);
		buttons.add(nextButton);

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
	 * Used to create searching area for when the user looks up plants
	 */
	public void createSearch() {

		text = new TextField();
		text.textProperty().addListener((observable, oldValue, newValue) -> {
			// getting whatever the user type inside the container if they've
			// typed anything.
			if (container.getChildren().size() > 1) {
				container.getChildren().remove(1);
			}
			container.add(populateDropDownMenu(newValue), 0, 1);

		});

		containerScroll = new ScrollPane();
		containerScroll.setPrefSize(containerScrollPrefWidth, containerScrollPrefHeight);
		containerScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		containerScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		containerScroll.setContent(container);

		Tooltip tooltipSearch = new Tooltip(
				"Search up your existing plants and click on the name to add it to your list.");
		text.setTooltip(tooltipSearch);

		close = new Button("Clear");
		close.setFont(getModel().getHackBold12());
		close.setStyle(View.getWhiteBackgroundStyle() + "-fx-border-width: 1;" + "-fx-border-color: #000000;");
		close.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				text.setText("");

			}
		});

		Tooltip tooltip = new Tooltip("Click to clear the text box.");
		close.setTooltip(tooltip);
		text.setPrefWidth(View.getCanvasWidth() / 4 - close.getWidth());
		searchBox.setPadding(new Insets(0, inset20, inset8, 0));
		searchBox.getChildren().addAll(text, close);

		container.add(searchBox, 0, 0);
		container.setStyle(View.getLightGreenBackgroundStyle());
	}

	/**
	 * A method that gets the plant names and puts them in a drop down to create a
	 * list of plants the user may be searching for.
	 * 
	 * @param query what the user typed
	 * @return the list of plants that contains the string the user typed in
	 */
	public VBox populateDropDownMenu(String query) {
		VBox dropDownMenu = new VBox();
		dropDownMenu.setAlignment(Pos.BASELINE_LEFT);

		dropDownPlants = getModel().searchPlants(query);

		if (dropDownPlants != null && !dropDownPlants.isEmpty()) {
			for (Plant p : dropDownPlants.values()) {
				Label label = new Label(p.getFriendlyName());
				label.setMaxWidth(containerScroll.getWidth());

				/**
				 * Sets up a highlight color for the user to see what plant they are hovering
				 * over
				 */
				label.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent e) {
						label.setStyle(View.getPinkBackgroundStyle());

						/**
						 * Gets the image the user is hovering over and creates a tool tip for that
						 * image
						 */
						/**
						 * TODO: FIX the name of the trim to latin name is 2 strings but when it calls
						 * for the images it could include var. ...
						 */
						try {
							plantImageLinks = p.getImages();
						} catch (NullPointerException Exception) {
							plantImageLinks = null;
						}

						Image plantImage;

						// Get the actual image if it exists
						if (plantImageLinks != null) {
							try {
								String path = plantImageLinks[0];
								plantImage = new Image(path, tooltipImageWidthAndHeight, tooltipImageWidthAndHeight,
										true, true);
							} catch (NullPointerException Exception) {
								plantImage = defaultImg;
							} catch(ArrayIndexOutOfBoundsException outOfBounds) {
								plantImage = defaultImg;
							}

						} else {
							// get a default image
							plantImage = defaultImg;
						}

						Tooltip tooltipPick = new Tooltip();
						label.setTooltip(tooltipPick); // tooltip set on the name of plant from the selected list

						ImageView imageView = new ImageView(plantImage);
						imageView.setCache(true);
						imageView.setCacheHint(CacheHint.SPEED);
						tooltipPick.setGraphic(imageView);
						tooltipPick.setShowDelay(Duration.seconds(2));
					}
				});

				label.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent e) {
						label.setStyle(View.getLightGreenBackgroundStyle());
					}
				});

				/**
				 * When a plant is clicked on, name will turn bold, plant will get added to box
				 * on the right (with a delete box), and is added to the existing plants array
				 */
				label.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent mouseEvent) {
						if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
							if (mouseEvent.getClickCount() == 1) {
								label.setStyle(boldFontWeight);
								if (getModel().getSession().getExistingPlants().add(p)) {
									if (p.getInvasive()) {
										warning.setContentText(
												p.getLatinName() + " is an invasive plant." + System.lineSeparator()
														+ "If possible, please remove it from your actual garden.");
										Stage warningStage = (Stage) warning.getDialogPane().getScene().getWindow();
										warning.show();
										warningStage.setAlwaysOnTop(true);
										warningStage.toFront();
									}
									populateRightBox(p);
								}
							}
						}
					}
				});
				dropDownMenu.getChildren().add(label);
			}
		} else {
			if (text.getText().length() < 3) {
				Label label = new Label(moreCharacters);
				label.setMaxWidth(containerScroll.getWidth());
				dropDownMenu.getChildren().add(label);
			} else {
				Label label = new Label(noPlants);
				label.setMaxWidth(containerScroll.getWidth());
				dropDownMenu.getChildren().add(label);
			}
		}
		return dropDownMenu;
	}

	/**
	 * Takes the plants in existing plants and the plants that are being chosen to
	 * be added ot the right VBox with the Latin name and a delete box
	 * 
	 * @param p Plant that has been selected from the drop down
	 */
	private void populateRightBox(Plant p) {

		Text textarea = new Text(p.getFriendlyName());
		textarea.setWrappingWidth(selectedPlantWrappingWidth);
		textarea.setStyle("-fx-font-size: 20px;");
		textarea.setFont(
				Font.loadFont(getClass().getResourceAsStream("/fonts/Hack-Regular.ttf"), selectedPlantFontSize));

		Button deleteButton = new Button("X");
		deleteButton.setStyle(View.getWhiteBackgroundStyle() + "-fx-text-fill: #BC0504;" + "-fx-border-width: 1;"
				+ "-fx-border-color: #000000;"); // creates the red
													// X inside the
													// button
		deleteButton.setFont(Font.font("Verdana", FontWeight.BOLD, 10));

		/**
		 * Hovering over delete button characteristics so user knows that they are over
		 * the delete button
		 */
		DropShadow shadow = new DropShadow();
		deleteButton.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				deleteButton.setEffect(shadow);
				textarea.setEffect(shadow);
				deleteButton.setStyle("-fx-background-color: #C1AFAF;" + "-fx-text-fill: #BC0504;"
						+ "-fx-border-width: 1;" + "-fx-border-color: #000000;");
			}
		});

		deleteButton.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				deleteButton.setEffect(null);
				textarea.setEffect(null);
				deleteButton.setStyle(View.getWhiteBackgroundStyle() + "-fx-text-fill: #BC0504;"
						+ "-fx-border-width: 1;" + "-fx-border-color: #000000;");
			}
		});

		HBox selectedPlant = new HBox(selectedPlantHBoxSize);
		selectedPlant.getChildren().addAll(deleteButton, textarea);

		selection.getChildren().addAll(selectedPlant);
		deleteButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				getModel().getSession().getExistingPlants().remove(p);
				selectedPlant.getChildren().removeAll(deleteButton, textarea);
			}
		});
	}

	/**
	 * Clears the screen of any plants on the selected side and places the plants in
	 * again according to the session in use.
	 */
	public void refresh() {
		selection.getChildren().clear();
		Iterator<Plant> pItr = getSession().getExistingPlants().iterator();
		while (pItr.hasNext()) {
			populateRightBox(pItr.next());
		}
	}

}
