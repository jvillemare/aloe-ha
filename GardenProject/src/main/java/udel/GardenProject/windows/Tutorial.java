package udel.GardenProject.windows;

import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
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
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import udel.GardenProject.enums.Windows;
import udel.GardenProject.garden.Model;
import udel.GardenProject.garden.View;

/**
 * Presently: Basic text description run down of all the features of the program
 * and how to use them.
 *
 * @author Team 0
 */
public class Tutorial extends Window {

	private Group root;
	private Scene scene;

	/**
	 * Allows scrolling for user when they want to access info
	 */
	private ScrollPane scroll;

	/**
	 * Center Box that holds the accordion and the top box for the instruction
	 */
	private VBox centerBox;
	/**
	 * Used for the overall layout
	 */
	private BorderPane borderPane;

	/**
	 * Button goes back to main
	 */
	private Button back;

	/**
	 * text in vbox at the top of the screen
	 */
	private Text welcomeTxt;

	/**
	 * Holds the back button at the bottom
	 */
	private TilePane backPane;

	/**
	 * Holds all the How Tos and Abouts
	 */
	private Accordion accordion;

	/**
	 * Holds the welcomeText at the top of the screen
	 */
	private VBox topBox;

	private int inset10 = 10;
	private int inset20 = 20;
	private int borderLeft = 50;
	private int infoTextSize = 15;
	private int borderBottom = 50;
	private int borderRight = 400;
	private int backPaneTranslateY = -30;
	private int scrollWidthAdjustment = 150;
	private int scrollHeightAdjustment = 115;
	private int messageWrapWidth = View.getCanvasWidth() / 5 * 3;
	private int tutorialCanvasWidth = View.getCanvasWidth() - 320;
	private int backPaneTranslateX = (tutorialCanvasWidth / 2) - (View.getButtonPrefWidth() / 2);
	private int imageWidth = 700;
	private int imageHeight = 400;

	/**
	 * Create a Tutorial window instance.
	 *
	 * @param m Model
	 */
	public Tutorial(Model m) {
		super(m, "Tutorial Window", Windows.Tutorial);

		borderPane = new BorderPane();
		borderPane.setPadding(new Insets(10));
		backPane = new TilePane();
		centerBox = new VBox();
		topBox = new VBox();

		welcomeTxt = new Text(
				"Welcome to the Tutorial! Click on the drop down options below to help you get started on your plot. Happy planting!");
		welcomeTxt.setFont(getModel().getHackBold20());
		welcomeTxt.setWrappingWidth(messageWrapWidth);

		topBox.setStyle(View.getPinkBackgroundStyle());
		topBox.setPadding(new Insets(inset10));
		topBox.getChildren().add(welcomeTxt);

		scroll = new ScrollPane();

		scroll.setStyle(View.getWhiteBackgroundStyle() + "-fx-border-color: #F6AAA4;" + "-fx-border-insets: 5;"
				+ "-fx-border-width: 3;" + "-fx-border-style: solid;");
		scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		scroll.setMaxWidth(View.getCanvasHeight() + scrollWidthAdjustment);
		scroll.setPrefSize(View.getCanvasHeight() + scrollWidthAdjustment,
				View.getCanvasHeight() - backPane.getHeight() - scrollHeightAdjustment);

		createAccordion();

		centerBox.setStyle("-fx-background-color: #F6DCDA;");
		centerBox.setPadding(new Insets(inset20, inset10, inset10, inset10));
		centerBox.setMaxWidth(scroll.getWidth());
		centerBox.getChildren().addAll(topBox, accordion);

		scroll.setContent(centerBox);

		DropShadow shadow = new DropShadow();
		back = new Button("Go Back");
		back.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				switchToWindow(Windows.Welcome);
			}
		});

		back.setFont(Font.loadFont(getClass().getResourceAsStream(View.getHackBold()), View.getButtonTextSize() + 3));
		back.setStyle(View.getLightGreenBackgroundStyle() + View.getBlackTextFill());
		back.setPrefWidth(View.getButtonPrefWidth());
		back.setPrefHeight(View.getButtonPrefWidth() / 2);

		back.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				back.setEffect(shadow);
				back.setStyle(View.getWhiteBackgroundStyle() + View.getBlackTextFill());
			}
		});

		back.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				back.setEffect(null);
				back.setStyle(View.getLightGreenBackgroundStyle() + View.getBlackTextFill());
			}
		});

		backPane.setTranslateX(backPaneTranslateX);
		backPane.setTranslateY(backPaneTranslateY);
		backPane.getChildren().add(back);

		Image image = new Image(getClass().getResourceAsStream("/buttonImages/splash2.png"));
		View.setBackgroundScreen(image, 0, 0);

		borderPane.setBackground(View.getBackgroundScreen());

		BorderPane.setMargin(scroll, new Insets(0, borderRight, borderBottom, borderLeft));
		borderPane.setCenter(scroll);
		borderPane.setBottom(backPane);

		this.root = new Group();
		root.getChildren().add(borderPane);
		this.scene = new Scene(this.root, tutorialCanvasWidth, View.getCanvasHeight());

	}

	@Override
	public Scene getScene() {
		// TODO Auto-generated method stub
		return this.scene;
	}

	/**
	 * Sets the new information drop down menu with the the Hack Bold font, size 20,
	 * and puts it into the dropdown menu itself.
	 * 
	 * @param s The string that shows the title of the drop down menu
	 * @param v The VBox to be placed within the drop down menu with the information
	 *          (strings and images)
	 */
	public void createTitledPane(String s, VBox v) {
		TitledPane titledPane = new TitledPane(s, v);
		titledPane.setFont(getModel().getHackBold20());
		accordion.getPanes().add(titledPane);
	}

	/**
	 * Creates the dropdown menus for each of the different titledPanes If another
	 * tiled pane needs to be added, just place it anywhere within the list.
	 */
	public void createAccordion() {

		accordion = new Accordion();

		createTitledPane("How to Get Started", createGetStarted());
		createTitledPane("How to Navigate to Different Screens", createNavigation());
		createTitledPane("About Tool Tips", createHoverInfo());
		createTitledPane("How to Add Existing Plants", createAddExistingPlants());
		createTitledPane("About the Questionnaire", createQuestionnaireTutorial());
		createTitledPane("How to Select Plants", createSelectingPlants());
		createTitledPane("About Your Plot Design", createPlotDesign());
		createTitledPane("About the Garden Previewer", createGardenPreviewer());
		createTitledPane("About the Load and Save Plot Screen", createDownload());
		createTitledPane("About the Plant Database", createPlantDatabase());
		createTitledPane("About the Plant Info Screen", createPlantInfo());
		createTitledPane("About Adding Obstacles to Your Plot", createObstacles());
		createTitledPane("Where the Plant Information Comes From", createAbout());

	}

	/**
	 * Creates the text for the information in each of the dropdown menus
	 * 
	 * @param s          A sentence that will be displayed on the screen
	 * @param contentBox The VBox that the Text will be put into
	 */
	public void createContentText(String s, VBox contentBox) {
		Text message = new Text(s);
		message.setFont(Font.loadFont(getClass().getResourceAsStream(View.getHackBold()), infoTextSize));
		message.setWrappingWidth(messageWrapWidth);
		contentBox.getChildren().add(message);
	}

	/**
	 * Create the corresponding image and adds it to the content box
	 * 
	 * @param img
	 * @param contentBox
	 */
	public void createContentImage(String img, VBox contentBox) {
		Image image = new Image(getClass().getResourceAsStream(img));
		ImageView iv = new ImageView(image);
		iv.setStyle("-fx-padding: 10");

		/**
		 * TODO: may have to add parameters for width and height for each image. Blocked
		 * until program is finished an we can start taking screenshots of the screens
		 */
		if(img.equals("/buttonImages/fiveleaf.png")) {
			iv.setFitHeight(70);
			iv.setFitWidth(150);
		}else {
			iv.setFitHeight(imageHeight);
			iv.setFitWidth(imageWidth);
		}
		
		contentBox.getChildren().add(iv);
	}

	/**
	 * Sets up the foundation for the Getting Started drop down
	 * 
	 * @return The content box
	 */
	public VBox createGetStarted() {

		VBox contentBox = new VBox();
		createContentText("Click on the get started button on the main screen.", contentBox);
		createContentImage("/tutorialImages/Welcome_Page.png", contentBox); // image of the start new plot button
		createContentText("Follow the instruction on each of the screen to complete your design garden.", contentBox);
		return contentBox;

	}

	/**
	 * Sets up how the user can navigate between screens
	 * 
	 * @return The content Box
	 */
	public VBox createNavigation() {
		VBox contentBox = new VBox();
		createContentText(
				"Click on the buttons at the bottom of the screen to either go back to the previous screen or move on to the next one.",
				contentBox);
		createContentImage("/buttonImages/fiveleaf.png", contentBox); // image of the Go Back and Next Buttons
		return contentBox;
	}

	/**
	 * Gives information about the tool tips in the program
	 * 
	 * @return The content box
	 */
	public VBox createHoverInfo() {
		VBox contentBox = new VBox();
		createContentText(
				"Tool tips are set up to help you know more about what to do. For example, hover over the name of plant from the drop down when you search for a plant. You will be able to see a picture of the plant!",
				contentBox);
		createContentImage("/tutorialImages/Tool_Tips.png", contentBox); // image of existing plants drop down with hover
		return contentBox;
	}

	/**
	 * Sets up how the user can use the Existing Plants screen
	 * 
	 * @return The content box
	 */
	public VBox createAddExistingPlants() {
		VBox contentBox = new VBox();
		createContentText(
				"Search the plants you already have in your garden by typing either the plant's Latin or common name. Once selected, the name of the plant will become bold and your selected plant will appear in the box on the right.",
				contentBox);
		createContentImage("/tutorialImages/Existing_Plants.png", contentBox); // image of the user selecting a plant
		createContentText(
				"If your would like to remove the plant from your selection, click the X button on the left of the corresponding plant name.",
				contentBox);
		createContentImage("/tutorialImages/X_Button.png", contentBox); // image of the user hovering over the X next to a
																		// plant's name
		return contentBox;
	}

	/**
	 * Gives information about the Questionnaire screen
	 * 
	 * @return The content Box
	 */
	public VBox createQuestionnaireTutorial() {
		VBox contentBox = new VBox();
		createContentText(
				"Answer all the questions to the best of your ability. Your answers will help us filter out plants that cannot be placed in your garden and help you select plants that are native to your area.",
				contentBox);
		createContentImage("/tutorialImages/Question.png", contentBox);
		return contentBox;
	}

	/**
	 * Information about using the Plant Selection screen
	 * 
	 * @return The Content Box
	 */
	public VBox createSelectingPlants() {
		VBox contentBox = new VBox();
		createContentText(
				"On the Plant Selection screen, you will see 4 options corresponding to different canopy levels.",
				contentBox);
		createContentImage("/tutorialImages/Plant_Selection.png", contentBox); // image of the 4 canopies
		createContentText(
				"Click the 'Add Plant' button to select the plants from each canopy level for a garden that supports plants that require different levels of sunlight.",
				contentBox);
		createContentText("If you would like more infomation about the plant, click on the info button to learn more",
				contentBox);
		createContentImage("/tutorialImages/Add_Plant.png", contentBox); // image of the info and add plant button
		createContentText(
				"If you would like to remove your plant selection, click the 'Remove' button on the corresponding plant.",
				contentBox);
		createContentImage("/tutorialImages/Remove_Plant.png", contentBox); // image of the remove button with the plant
		return contentBox;
	}

	/**
	 * Tells the user how to use the Plot Design screen
	 * 
	 * @return The Content Box
	 */
	public VBox createPlotDesign() {

		VBox contentBox = new VBox();
		createContentText(
				"Your house is located at the bottom edge of the screen as if you are looking at your garden with a bird's eye view.",
				contentBox);
		createContentText("Click and drag your plants from the Existing Plants and Selected Plants drop down menus.",
				contentBox);
		createContentImage("/buttonImages/fiveleaf.png", contentBox); // image of the drop down menu and clicking and
																		// dragging
		createContentText(
				"The Obstacles drop down menu has buttons that you can click on to make the object appear on your plot. Each of the obstacles in the drop down menu are selected from your answers in the Questionnnaire screen.",
				contentBox);
		return contentBox;
	}

	/**
	 * Tell the user about the Garden Previewer screen
	 * 
	 * @return The Content Box
	 */
	public VBox createGardenPreviewer() {

		VBox contentBox = new VBox();
		createContentText("Select the season you would like to view your garden in.", contentBox);
		createContentImage("/buttonImages/fiveleaf.png", contentBox); // image of the season toggles
		createContentText(
				"Selecting the different years will give you a view of how your garden would look in 0, 1, or 2 years.",
				contentBox);
		createContentImage("/buttonImages/fiveleaf.png", contentBox); // image of the year toggles
		createContentText(
				"The different views will also allow your to see different perspectives of your plot. The TOP VIEW will show you a view of your garden in a 'bird's eye view', and the WINDOW VIEW will show you a view of your garden looking out the window from your house.",
				contentBox);
		createContentImage("/buttonImages/fiveleaf.png", contentBox); // image of the view toggles
		createContentText("Click SAVE to see the new image appear.", contentBox);
		// add image of different view examples?
		return contentBox;
	}

	/**
	 * Tells the user about how to use the Download screen
	 * 
	 * @return The Content Box
	 */
	public VBox createDownload() {

		VBox contentBox = new VBox();
		createContentText(
				"You can load your plot to access and edit it at a later time. You can also select how you would like to download the image of your plot from the selection chosen from the previous screen. ",
				contentBox);
		createContentImage("/buttonImages/fiveleaf.png", contentBox); // image of the how downloading works
		return contentBox;
	}

	/**
	 * Tells the user more about the Plant Database and what it should be used for
	 * 
	 * @return The Content Box
	 */
	public VBox createPlantDatabase() {

		VBox contentBox = new VBox();
		createContentText(
				"The Plant DataBase button on the Plot Design screen will take you to where you can look at ALL plants, including plants not in your native area. You can search and choose which plants you'd like to add to your garden.",
				contentBox);
		createContentImage("/tutorialImages/All_Plants.png", contentBox); // image of the plant data base screen
		return contentBox;
	}

	/**
	 * Gives an explanation about the Plant Info screen
	 * 
	 * @return The Content Box
	 */
	public VBox createPlantInfo() {
		VBox contentBox = new VBox();
		createContentText(
				"The Plant Info button will take you to a screen that shows you all the information of the plant. This information allows your to learn more about the plants and help you decide which plants to add from the Plant Selection screen and the Plant Database",
				contentBox);
		createContentImage("/tutorialImages/Plant_Info.png", contentBox); // image of the Plant Info screen and how it works
		return contentBox;
	}

	/**
	 * Tells the user how the obstacles are used on the Plot Design
	 * 
	 * @return The content Box
	 */
	public VBox createObstacles() {
		VBox contentBox = new VBox();
		createContentText(
				"Add the obstacles from the buttons in the drop down menu in the Plot Design screen. You can adjust the object by clicking the corresponding Resume Edit/Complete Edit Button. The yellow dots on the object will allow you to change the size and/or shape of the object to your desire.",
				contentBox);
		createContentImage("/buttonImages/fiveleaf.png", contentBox); // image of adding an obstacle
		return contentBox;
	}

	/**
	 * Tells the user where the plant information is coming from and links to the
	 * open source information
	 * 
	 * @return
	 */
	public VBox createAbout() {
		VBox contentBox = new VBox();
		createContentText("This project was created from the following data bases.", contentBox);
		createContentText("Add link", contentBox); // hyperlink?
		createContentText("Add link", contentBox); // hyperlink?
		createContentText("Add link", contentBox); // hyperlink?
		createContentText("Add link", contentBox); // hyperlink?
		return contentBox;
	}

}
