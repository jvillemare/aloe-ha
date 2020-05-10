package udel.GardenProject.windows;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.ColorAdjust;
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
import udel.GardenProject.enums.Windows;
import udel.GardenProject.garden.Model;
import udel.GardenProject.garden.View;
import udel.GardenProject.plants.Plant;

/**
 * A searchable database of all the plants a user can add to the their plot.
 * They can also add plants that were not initially in their selection.
 * 
 * @author Team 0
 */
public class AllPlants extends Window {

	private Scene scene;
	private Group root;

	/**
	 * Main layout format for screen
	 */
	private BorderPane borderPane;

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
	 * Drop down menu to select the letter
	 */
	private ComboBox comboBox;

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
	 * Adjustments to screen and buttons
	 */
	private int inset5 = 5;
	private int inset10 = 10;
	private int inset20 = 20;
	private int comboBoxXAdjustment = 40;
	private int comboBoxYAdjustment = -20;
	private int scrollWidthAdjustment = 20;
	private int gapBetweenPageButtons = 220;
	private int flowPrefWidthAdjustment = 30;
	private int scrollHeightAdjustment = 150;
	private int backgroundScreenWidthAndHeight = 100;

	public AllPlants(Model m) {
		super(m, "Plant Database", Windows.AllPlants);

    // TODO: Left over from merge conflict
		//infoBox = new VBox();
		//scrollAndPageButtons = new VBox();

		//info = new Text(
		//		"Need more plants? Click on the drop down list for the first letter of the plant name you want to add. Plants will be found based on their Latin names.");
		//info.setFont(getModel().getHackBold20());
		//info.setWrappingWidth(View.getCanvasWidth());
		
		//left side will be filter
		TextField text = new TextField();
	
		searchBox = new HBox();
		
		Button close = new Button("X");
        Button search = new Button("Search");
        searchBox.getChildren().addAll(text,close,search);
		
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

		/*
		 * Populates the drop down menu A-Z
		 */
		ObservableList<String> letterChoice = FXCollections.observableArrayList();
		for (char alphabet = 'A'; alphabet <= 'Z'; alphabet++) {
			letterChoice.add(Character.toString(alphabet));
		}
		comboBox = new ComboBox(letterChoice);
		comboBox.setTranslateX(View.getCanvasWidth() / 2 - comboBoxXAdjustment);
		comboBox.setTranslateY(comboBoxYAdjustment);
		comboBox.setPromptText("Select");
		infoBox.getChildren().addAll(info, comboBox);

		comboBox.setOnAction(e -> {
			/**
			 * TODO: implement functionality with this function (function found at bottom of
			 * screen)
			 */
			populateFlowPane((String) comboBox.getValue()); // comboBox.getValue() is the letter that is chosen
		});

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
		prev.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				/**
				 * TODO: implement going back a page Will need to have some way of keeping track
				 * what page you are on?
				 */
			}
		});
		prev.setFont(getModel().getHackBold12());
		handleButtonEffect(prev);
		pageNavigation.getChildren().add(prev);

		next = new Button("Next ->");
		next.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				/**
				 * TODO: implement going to the next page Will need to have some way of keeping
				 * track what page you are on?
				 */
			}
		});
		next.setFont(getModel().getHackBold12());
		handleButtonEffect(next);
		pageNavigation.getChildren().add(next);

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

		back.setFont(Font.loadFont(getClass().getResourceAsStream(View.getHackBold()), View.getButtonTextSize() + 3));
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
	 * Gets the letter chosen from the drop down menu and gets all the corresponding
	 * plants that start with that later (by Latin name)
	 * 
	 * @param letter The letter chosen from the drop down
	 */
	public void populateFlowPane(String letter) {
		System.out.println(letter);

		/**
		 * TODO: change this to the corresponding letter-plant name
		 */
		int num = 30;
		Image pages[] = new Image[num];
		for (int i = 0; i < num; i++) {
			Plant p = getModel().getPlants().get(i);

			String[] plantImg = p.getImages();

			if (plantImg != null) {
				pages[i] = new Image(p.getImages()[0], 350, 100, true, true);
			} else {
				pages[i] = new Image(getClass().getResourceAsStream("/buttonImages/tree.png"), 350, 100, true, true);
			}

			ImageView imageView = new ImageView(pages[i]);

			Text latinName = new Text(p.getLatinName());

			/**
			 * Creates the Add Plant button for each plant and changes image color depending
			 * on if the plant is added or not
			 */
			Button addPlant = new Button("Add Plant");
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
						/**
						 * TODO: add plant to the corresponding HashMap*** of Plants
						 */
					} else {
						imageView.setEffect(null);
						addPlant.setText("Add Plant");
						/**
						 * TODO: remove plant to the corresponding HashMap*** of Plants
						 */
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
	}

	/**
	 * Sets attributes for Buttons and handles mouse effects
	 * 
	 * @param b Button
	 */
	public void handleButtonEffect(Button b) {

		b.setStyle(View.getLightGreenBackgroundStyle() + View.getBlackTextFill());
		b.setPrefWidth(View.getButtonPrefWidth());
		// b.setPadding(new Insets(inset10));
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
