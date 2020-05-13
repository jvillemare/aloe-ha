package udel.GardenProject.windows;

import java.net.MalformedURLException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
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
 * To display all the information of a Plant to the user.
 * 
 * @author Team 0
 */
public class PlantInfo extends Window {

	private Scene scene;
	private Group root;

	/**
	 * Button that brings you back to the previous screen
	 */
	private Button backButton;

	/**
	 * Image of the default plants
	 */
	private Image plantImage;

	/**
	 * All the information about the plant to be shown to the user
	 */
	private VBox information;

	/**
	 * The background layout
	 */
	private BorderPane borderPane;

	/**
	 * Used for the user to be able to scroll through the information
	 */
	private ScrollPane scroll;

	/**
	 * The name of the plant at the top of the screen
	 */
	private Text name;

	/**
	 * The pane to which the back button is places (at the bottom of the screen)
	 */
	private TilePane button;
	
	/**
	 * The text for URL for copyright: empty if using default image.
	 */
	private Text url = new Text("");
	
	/**
	 * Adjustments, fonts, widths and heights for the Info Screen format
	 */
	private int inset10 = 10;
	private int inset20 = 20;
	private int inset200 = 200;
	private double buttonSizeFactor = 1.5;
	private int screenWidth = (View.getCanvasWidth() / 5 * 4) - 100;
	private int scrollPrefWidth = screenWidth / 2;
	private int imgWidthAndHeight = screenWidth / 2;
	private int infoWrapTextWidth = screenWidth / 2 - 20;
	private int infoMinHeight = View.getCanvasHeight() - 100;
	private int scrollPrefHeight = View.getCanvasHeight() - 100;
	private int screenWidthAdjustment = 30;
	
	/**
	 * Default image sized to scale.
	 */
	private Image defaultImg = getModel().getDefaultImage(imgWidthAndHeight, imgWidthAndHeight);
	
	public PlantInfo(Model m) {
		super(m, "Plant Info: ", Windows.PlantInfo);

		borderPane = new BorderPane();

	}

	/**
	 * Change PlantInfo's scene and display a plant's info by it's latin name. This
	 * will be used by PlantSelection and AllPlants for their button
	 * 
	 * @param plantLatinName Linnaeus Genus species plant name.
	 * @throws MalformedURLException 
	 */
	public void displayPlant(Plant plant) throws MalformedURLException {

		setTitle("Plant Info: " + plant.getLatinName());

		name = new Text(plant.getLatinName());
		name.setFont(getModel().getHackBoldItalic20());

		information = new VBox();
		information.setStyle(View.getWhiteBackgroundStyle());

		Text light = makeText("Light: " + plant.getLight());
		Text moisture;
		Text soil;
		Text canopy;
		try {
			moisture = makeText("Moisture: " + plant.getMoisture().getFriendlyName());
		}catch(NullPointerException e) {
			moisture = makeText("Moisture: Unavailable");
		}
		
		try {
			soil = makeText("Soil Type: " + plant.getSoilType().getName());
		}catch(NullPointerException e) {
			soil = makeText("Soil Type: Unavailable");
		}
		
		try {
			canopy = makeText("Canopy: " + plant.getCanopy().getFriendlyName());
		}catch(NullPointerException e) {
			canopy = makeText("Canopy Type: Unavailable");
		}
		
		Text delaware = makeText("Delaware Native: " + plant.getDelawareNative());
		Text description = makeText(plant.getDescription());
		Text newLine = new Text("");

		information.minHeight(infoMinHeight);
		information.getChildren().addAll(light, moisture, soil, canopy, delaware, newLine, description);

		scroll = new ScrollPane();
		scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		scroll.setPrefSize(scrollPrefWidth, scrollPrefHeight);
		scroll.setPadding(new Insets(inset10));
		scroll.setStyle(View.getWhiteBackgroundStyle() + "-fx-border-color: #F6AAA4;" + "-fx-border-insets: 5;"
				+ "-fx-border-width: 3;" + "-fx-border-style: solid;");
		scroll.setContent(information);

		String[] plantImg = plant.getImages();

		// Get the actual image if it exists
		if (plantImg != null) {
			String path = plant.getImages()[0];
			plantImage = new Image(path, imgWidthAndHeight, imgWidthAndHeight, true, true);
			url = makeText("Image Source: " + Plant.getImageSourceDomain(path));
			url.setFont(getModel().getHackBoldItalic20());
		} else {
			// get a default image
			plantImage = defaultImg;
			url = makeText("");
		}

		// where the image will be place
		VBox image = new VBox();
		image.setPadding(new Insets(inset10));
		ImageView img = new ImageView();
		img.setImage(plantImage);
		image.getChildren().addAll(img, name, url);

		createButton();

		borderPane.setStyle(View.getPinkBackgroundStyle());
		borderPane.setPadding(new Insets(inset10, inset200, inset10, inset10));
		borderPane.setLeft(image);
		borderPane.setRight(scroll);
		borderPane.setBottom(button);
		
		screenWidth = (int) (imgWidthAndHeight + scrollPrefWidth + screenWidthAdjustment);
		
		this.root = new Group();
		root.getChildren().add(borderPane);
		this.scene = new Scene(this.root, screenWidth, View.getCanvasHeight());

	}

	@Override
	public Scene getScene() {
		// TODO Auto-generated method stub
		return this.scene;
	}

	/**
	 * Create the Text in the box of information and formats i
	 * 
	 * @param info The info that is gathers from the internet
	 * @return The text that is supposed to be in the info box
	 */
	public Text makeText(String info) {
		Text desire = new Text(info);
		desire.setWrappingWidth(infoWrapTextWidth);
		desire.minWidth(infoWrapTextWidth);
		desire.setFont(new Font(View.getTextSizeForButtonsAndText() * buttonSizeFactor));
		return desire;
	}

	/**
	 * Refreshes the screen and to get the correct info from Model
	 */
	public void refresh() {
		try {
			displayPlant(getModel().getPlantInfoPlant());
		}catch(MalformedURLException e) {
			System.out.println("PlantInfo: Failed to trim source image URL");
		}
	}

	/**
	 * Creates the back button at the bottom of the screen and places it in a
	 * TilePane
	 */
	public void createButton() {

		DropShadow shadow = new DropShadow();

		button = new TilePane();

		backButton = new Button("Go Back");
		backButton.setFont(Font.loadFont(getClass().getResourceAsStream(View.getHackBold()),
				View.getButtonTextSize() * buttonSizeFactor));
		backButton.setStyle(View.getLightGreenBackgroundStyle() + View.getBlackTextFill());
		backButton.setPrefWidth(View.getButtonPrefWidth());
		backButton.setPrefHeight(View.getButtonPrefWidth() / 2);

		backButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				getModel().goToLastWindow();
			}
		});

		backButton.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				backButton.setEffect(shadow);
				backButton.setStyle(View.getWhiteBackgroundStyle() + View.getBlackTextFill());
			}
		});

		backButton.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				backButton.setEffect(null);
				backButton.setStyle(View.getLightGreenBackgroundStyle() + View.getBlackTextFill());
			}
		});

		button.setPadding(new Insets(inset20));
		button.setAlignment(Pos.CENTER);
		button.getChildren().add(backButton);
	}
}
