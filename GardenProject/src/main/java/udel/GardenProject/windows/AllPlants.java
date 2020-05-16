package udel.GardenProject.windows;

import java.util.ArrayList;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.CacheHint;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import udel.GardenProject.enums.Windows;
import udel.GardenProject.garden.Model;
import udel.GardenProject.garden.View;
import udel.GardenProject.plants.Plant;

/**
 * A searchable database of all the plants a user can add to the their plot.
 * They can also add plants that were not initially in their selection.
 * 
 * @version 1.0
 * @author Team 0
 */
public class AllPlants extends Window {

	private Scene scene;
	private Group root;

	/**
	 * Main layout format for screen
	 */
	private BorderPane borderPane = new BorderPane();;

	/**
	 * Holds all the images of the plants in the scrollPane
	 */
	private FlowPane flow;

	/**
	 * Allows for scrolling of the flowPane
	 */
	private ScrollPane scroll;

	/**
	 * Infobox at the top of the screen with the letter dropdown
	 */
	private VBox infoBox;

	/**
	 * Info at the top of the screen
	 */
	private Text info;

	/**
	 * Drop down menu to select if they want all plants or just native plants
	 */
	private ComboBox<String> nativeChoice;

	/**
	 * Holds the scroll pane and the buttons at the bottom for navigating the pages
	 */
	private VBox scrollAndPageButtons;

	/**
	 * Holds the buttons for navigation of the pages
	 */
	private FlowPane pageNavigation;

	/**
	 * Buttons for navigation the pages and going back to the previous screen
	 */
	private Button prev, next, back;

	/**
	 * Pane for holding the back button at the bottom of the screen
	 */
	private TilePane tilePane;

	/**
	 * Used for the search box and the options that show up
	 */
	private GridPane container = new GridPane(); 
	
	/**
	 * List of Plants that the user can choose from depending on their search box
	 *  search
	 */
	private ArrayList<Plant> potentialPlants = new ArrayList<Plant>();
	
	/**
	 * Current page the user is on.
	 */
	private int currentPage = 0;
	
	/**
	 * Max Plants per page.
	 */
	private int maxImagesPerPage = 25;
	
	/**
	 * True if the Atlantic Filter was checked, false otherwise.
	 */
	private boolean atlanticFilter = false;
	
	/**
	 * Text that appears if no no plants fit the description.
	 */
	private Text noPlants = new Text("No Such Plants");
	
	/**
	 * Text that appears if more characters need to be added.
	 */
	private Text moreCharacters = new Text("Please Add More Characters in Search");
	
	/**
	 * The text field for search.
	 */
	private TextField text;
	
	/**
	 * Adjustments to screen and buttons
	 */
	private int inset5 = 5;
	private int inset10 = 10;
	private int inset20 = 20;
	private int scrollWidthAdjustment = 20;
	private int gapBetweenPageButtons = 220;
	private int flowPrefWidthAdjustment = 30;
	private int scrollHeightAdjustment = 150;
	private int backgroundScreenWidthAndHeight = 100;
	private int imageHeight = 350;
	private int imageWidth = 100;
	
	/**
	 * Default Image with according sizing
	 */
	private Image defaultImg = getModel().getDefaultImage(imageHeight, imageWidth);
	
	public AllPlants(Model m) {
		super(m, "Plant Database", Windows.AllPlants);
		
		infoBox = new VBox();
		scrollAndPageButtons = new VBox();

		info = new Text(
				"Need more plants? Search or go through this database. Plants will be found based on their Latin names.");
		info.setFont(getModel().getHackBold20());
		info.setWrappingWidth(View.getCanvasWidth());
		
		Text title = new Text("Please Make Plant Selections");
		title.setFont(new Font(18));
		title.setFill(Color.DARKGREEN);
		
		StackPane txt = new StackPane();
		txt.setStyle("-fx-background-color: DAE6F3;");
		txt.getChildren().add(title);
		
		flow = new FlowPane();
		flow.setPadding(new Insets(inset5));
		flow.setVgap(inset10);
		flow.setHgap(inset20);
		flow.setPrefWrapLength(View.getCanvasWidth() - flowPrefWidthAdjustment);
		flow.setStyle(View.getWhiteBackgroundStyle());
		
		createSearch();

		scroll = new ScrollPane();
		scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
		scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		scroll.setMaxWidth(View.getCanvasWidth() - scrollWidthAdjustment);
		scroll.setPrefSize(View.getCanvasWidth() - scrollWidthAdjustment,
				View.getCanvasHeight() - scrollHeightAdjustment - 15);
		scroll.setContent(flow);

		pageNavigation = new FlowPane();
		pageNavigation.setHgap(View.getCanvasWidth() - gapBetweenPageButtons);
		
		prev = new Button("<- Prev");
		if(currentPage == 0) {
			prev.setVisible(false);
		}
		prev.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				currentPage--;
				populateFlowPane();
				if(currentPage == 0) {
					prev.setVisible(false);
				}
			}
		});
		prev.setFont(getModel().getHackBold12());
		handleButtonEffect(prev);
		pageNavigation.getChildren().add(prev);

		next = new Button("Next ->");
		next.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				currentPage++;
				populateFlowPane();
				prev.setVisible(true);
			}
		});
		next.setFont(getModel().getHackBold12());
		handleButtonEffect(next);
		pageNavigation.getChildren().add(next);
		
		ObservableList<String> nativeChoices = FXCollections.observableArrayList();
		nativeChoices.add("All Plants");
		nativeChoices.add("Delaware Native Plants");
		nativeChoice = new ComboBox<String>(nativeChoices);
		nativeChoice.setPromptText("All Plants");
		
		nativeChoice.setOnAction(e -> {
			if(((String)nativeChoice.getValue()).equals("All Plants")) {
				atlanticFilter = false;
				currentPage = 0;
				prev.setVisible(false);
				text.setText("");
				potentialPlants.clear();
			}else if(((String)nativeChoice.getValue()).equals("Delaware Native Plants")) {
				atlanticFilter = true;
				currentPage = 0;
				text.setText("");
				prev.setVisible(false);
				potentialPlants.clear();
			}
			populateFlowPane();
		});
		
		infoBox.getChildren().addAll(info, container, nativeChoice);
		
		populateFlowPane();

		scrollAndPageButtons.getChildren().addAll(scroll, pageNavigation);

		tilePane = new TilePane();
		tilePane.setAlignment(Pos.CENTER);
		back = new Button("Go Back");
		back.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				switchToWindow(Windows.PlotDesign);
			}
		});

		back.setFont(Font.loadFont(getClass().getResourceAsStream(View.getHackBold()),
				View.getButtonTextSize() + 3));
		back.setPrefHeight(View.getButtonPrefWidth() / 2);
		handleButtonEffect(back);
		tilePane.getChildren().add(back);

		Image image = new Image(getClass().getResourceAsStream(View.getBackgroundScreenPath()));
		View.setBackgroundScreen(image, backgroundScreenWidthAndHeight, backgroundScreenWidthAndHeight);

		borderPane.setBackground(View.getBackgroundScreen());
		borderPane.setPadding(new Insets(inset10));
		borderPane.setTop(infoBox);
		borderPane.setCenter(scrollAndPageButtons);
		borderPane.setBottom(tilePane);

		this.root = new Group();
		root.getChildren().add(borderPane);
		this.scene = new Scene(this.root, View.getCanvasWidth(), View.getCanvasHeight());
	}
	
	/**
	 * Used to create searching area for when the user looks up plants
	 */
	public void createSearch() {
		
		HBox searchBox = new HBox();
		searchBox.setAlignment(Pos.CENTER);

		text = new TextField();
		text.textProperty().addListener((observable, oldValue, newValue) -> {
			// getting whatever the user type inside the container if they've
			// typed anything.
			if (container.getChildren().size() > 1) {
				container.getChildren().remove(1);
			}
			populateOptions(newValue);
			populateFlowPane();

		});
		

		Tooltip tooltipSearch = new Tooltip(
				"Search desired plants here.");
		text.setTooltip(tooltipSearch);

		Button close = new Button("Clear");
		close.setFont(getModel().getHackBold12());
		close.setStyle(View.getWhiteBackgroundStyle() + "-fx-border-width: 1;" + "-fx-border-color: #000000;");
		close.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				text.setText("");
				potentialPlants.clear();
				populateFlowPane();
			}
		});

		Tooltip tooltip = new Tooltip("Click to clear the text box.");
		close.setTooltip(tooltip);
		text.setPrefWidth(View.getCanvasWidth() / 4 - close.getWidth());
	
		searchBox.getChildren().addAll(text, close);

		container.add(searchBox, 0, 0);
	}
	
	/**
	 * Populates an ArrayList of all the potential plants user wants from 
	 * search.
	 * 
	 * @param query
	 */
	public void populateOptions(String query) {
		potentialPlants.clear();
		currentPage = 0;

		if(atlanticFilter) {
			if(getModel().searchNativePlants(query) != null) {
				for(Map.Entry<String, Plant> entry : getModel().searchNativePlants(query).entrySet()) {
					potentialPlants.add(entry.getValue());
				}
			}
		}else {
			if(getModel().searchPlants(query) != null) {
				for(Map.Entry<String, Plant> entry : getModel().searchPlants(query).entrySet()) {
					potentialPlants.add(entry.getValue());
				}
			}
		}	
	}
	
	/**
	 * Populates the searchbox.
	 * 
	 * @param letter The letter chosen from the drop down
	 */
	public void populateFlowPane() {
		flow.getChildren().clear();
		next.setVisible(true);
		
		if(!potentialPlants.isEmpty() && potentialPlants != null) {
			if(potentialPlants.size() <= maxImagesPerPage) {
				next.setVisible(false);
				for(Plant p : potentialPlants) {
					createBox(p);
				}
			}else {
				pageCreation(potentialPlants);
			}
		}else {
			if(text.getText().isEmpty()) {
				showDefault();
			}else {
				if(text.getText().length() < 3) {
					moreCharacters();
				}else {
					noSuchPlant();
				}
			}
		}
	}
	
	/**
	 * Showcases the default amount of plants for AllPlants.
	 */
	public void showDefault() {
		if(atlanticFilter) {
			pageCreation(getModel().getNativePlants());
		}else {
			pageCreation(getModel().getPlants());
		}
	}
	
	/**
	 * Fills the flowPane with the Plants depending upon what page the user is on
	 * @param PlantList
	 */
	public void pageCreation(ArrayList<Plant> PlantList) {
		int plantStart = currentPage * maxImagesPerPage;
		for(int i = plantStart; i < plantStart + maxImagesPerPage && i < PlantList.size(); i++) {
			createBox(PlantList.get(i));
			if(i == PlantList.size() - 1) {
				next.setVisible(false);
			}
		}
	}
	
	/**
	 * Adds a Text announcing there are not plants.
	 */
	public void noSuchPlant() {
		noPlants.setFont(getModel().getHackBold20());
		StackPane noPlant = new StackPane();
		noPlant.getChildren().add(noPlants);
		flow.getChildren().add(noPlant);
	}
	
	/**
	 * Adds a Text announcing the user needs to add more characters to search.
	 */
	public void moreCharacters() {
		moreCharacters.setFont(getModel().getHackBold20());
		StackPane moreChar = new StackPane();
		moreChar.getChildren().add(moreCharacters);
		flow.getChildren().add(moreChar);
	}
	
	/**
	 * Creates a Plants box including its image, add button, and info button.
	 * @param Plant
	 */
	public void createBox(Plant p) {
		Image plantImg = defaultImg;
		
		String[] plantImgPath = p.getImages();
		if (plantImgPath != null) {
			try {
				plantImg = new Image(plantImgPath[0], 350, 100, true, true, true);
			}catch(ArrayIndexOutOfBoundsException Exception){
				plantImg = defaultImg;
			}
			
		}

		ImageView imageView = new ImageView(plantImg);
		imageView.setCache(true);
		imageView.setCacheHint(CacheHint.SPEED);

		Text latinName = new Text(p.getLatinName());

		Button addPlant;
		if (getSession().getSelectedPlants().contains(p)) {
			addPlant = new Button("Remove");
			ColorAdjust colorAdjust = new ColorAdjust();
			colorAdjust.setContrast(0.4);
			colorAdjust.setHue(-0.05);
			colorAdjust.setBrightness(0.9);
			colorAdjust.setSaturation(0.8);
			imageView.setEffect(colorAdjust);
			
		}else {
			addPlant = new Button("Add Plant");
		}
		
		addPlant.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {

				if (addPlant.getText().equals("Add Plant")) {
					ColorAdjust colorAdjust = new ColorAdjust();
					colorAdjust.setContrast(0.4);
					colorAdjust.setHue(-0.05);
					colorAdjust.setBrightness(0.9);
					colorAdjust.setSaturation(0.8);
					imageView.setEffect(colorAdjust);
					addPlant.setText("Remove");
					getSession().getSelectedPlants().add(p);
				} else {
					imageView.setEffect(null);
					addPlant.setText("Add Plant");
					getSession().getSelectedPlants().remove(p);
				}
			}
		});

		Button infoButton = new Button("Info");
		infoButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				getModel().setPlantInfoPlant(p);
				switchToWindow(Windows.PlantInfo);
			}
		});

		HBox hbox = new HBox();
		hbox.getChildren().addAll(infoButton, addPlant);

		VBox imgButtonHolder = new VBox();
		imgButtonHolder.getChildren().addAll(imageView, latinName, hbox);
		flow.getChildren().add(imgButtonHolder);
	}

	/**
	 * Sets attributes for Buttons and handles mouse effects
	 * 
	 * @param b Button
	 */
	public void handleButtonEffect(Button b) {

		b.setStyle(View.getLightGreenBackgroundStyle() + View.getBlackTextFill());
		b.setPrefWidth(View.getButtonPrefWidth());
		b.setAlignment(Pos.CENTER);

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

	@Override
	public Scene getScene() {
		// TODO Auto-generated method stub
		return this.scene;
	}

}
