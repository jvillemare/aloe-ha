package udel.GardenProject.windows;

import java.util.ArrayList;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import udel.GardenProject.enums.Moisture;
import udel.GardenProject.enums.PlotObjects;
import udel.GardenProject.enums.Seasons;
import udel.GardenProject.enums.SoilTypes;
import udel.GardenProject.enums.Sunlight;
import udel.GardenProject.enums.Windows;
import udel.GardenProject.garden.Model;
import udel.GardenProject.plotObjects.PlotObject;
import udel.GardenProject.plotObjects.lines.PlotFence;
import udel.GardenProject.plotObjects.lines.PlotPath;
import udel.GardenProject.plotObjects.polygons.PlotForest;
import udel.GardenProject.plotObjects.polygons.PlotPatio;
import udel.GardenProject.plotObjects.polygons.PlotPlayground;
import udel.GardenProject.plotObjects.polygons.PlotPool;
import udel.GardenProject.plotObjects.polygons.PlotRoad;
import udel.GardenProject.plotObjects.polygons.PlotRock;
import udel.GardenProject.plotObjects.polygons.PlotShed;
import udel.GardenProject.plotObjects.special.PlotBirdBath;
import udel.GardenProject.plotObjects.special.PlotOther;

/**
 * Basic questions about a user's plots that informs what plants are selected.
 *
 * @author Team 0
 */
public class Questionnaire extends Window {

	private Group root;
	private Scene scene;

	/**
	 * Used for holding the leaves on either side of the questionnaire
	 */
	private VBox leftLeaves, rightLeaves;

	/**
	 * Background layout
	 */
	private BorderPane borderPane;

	/**
	 * VBox creating for text for questions
	 */
	private VBox vbox;

	/**
	 * Vbox for holding the title
	 */
	private VBox topBox;

	/**
	 * TilePane created for buttons at the bottom
	 */
	private TilePane tilePane;

	/**
	 * For saving all of the user's questionnaire answers And moving between screens
	 */
	private Button backToExistingPlants, save, toPlotDesign;

	/**
	 * Used for the text in the VBox Info text
	 */
	private Text text, q0, q1, q2, q3, q4, q5, q6, q7, q8;

	/**
	 * Allows your to scroll down the screen
	 */
	private ScrollPane scroll;

	/**
	 * Used for single answers
	 */
	public ChoiceBox<String> q4ChoiceBox, q5ChoiceBox, q6ChoiceBox, choiceBox;

	/**
	 * Used to combine check boxes
	 */
	public ListView<CheckBox> q2ListView, q3ListView, q7ListView, q8ListView;

	/**
	 * Used for selection answers from questions 2, 3, 7, 8
	 */
	public CheckBox q2checkBox1, q2checkBox2;
	public CheckBox q3checkBox1, q3checkBox2, q3checkBox3, q3checkBox4, q3checkBox5, q3checkBox6, q3checkBox7,
			q3checkBox8, q3checkBox9, q3checkBox10;
	public CheckBox q7checkBox1, q7checkBox2, q7checkBox3, q7checkBox4, q7checkBox5;
	public CheckBox q8checkBox1, q8checkBox2, q8checkBox3, q8checkBox4, q8checkBox5, q8checkBox6, q8checkBox7,
			q8checkBox8;

	/**
	 * Used to iterate through and see which selections are checked by the user
	 */
	public ArrayList<CheckBox> nearPlot = new ArrayList<CheckBox>();
	public ArrayList<CheckBox> inPlot = new ArrayList<CheckBox>();
	public ArrayList<CheckBox> seasonWant = new ArrayList<CheckBox>();
	public ArrayList<CheckBox> colorWant = new ArrayList<CheckBox>();

	/**
	 * PlotObject arrays for what the user has in or near their garden
	 */
	public ArrayList<PlotObject> plotNearArr, plotInArr;

	/**
	 * The seasons the user wants to see their plants bloom
	 */
	public ArrayList<Seasons> seasonArr;

	/**
	 * Colors the user wants to see in their garden
	 */
	public ArrayList<Color> colorArr;

	/**
	 * Used for user input
	 */
	Label gardenLabel, gardenWidth, gardenHeight;
	TextField textField, q1textField1, q1textField2;
	HBox hbname, hbwidth, hbheight;

	public Questionnaire(Model m) {
		super(m, "Questions About Your Garden...");

		leftLeaves = new VBox();
		leftLeaves.setPadding(new Insets(10, 0, 10, 30));

		rightLeaves = new VBox();
		rightLeaves.setPadding(new Insets(10, 30, 10, 0));

		createLeaves(leftLeaves);
		createLeaves(rightLeaves);

		borderPane = new BorderPane();
		topBox = new VBox();
		vbox = new VBox();
		tilePane = new TilePane();

		text = new Text(
				"Welcome to the Aloe-ha questionnaire! Please fill out the questions below. Remember, you must answer all of the questions to continue.\n");
		text.setWrappingWidth(View.getCanvasWidth() - 20);
		text.setStyle("-fx-font-size: 20px;");
		text.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/Hack-Bold.ttf"), 30));

		topBox.getChildren().add(text);
		topBox.setStyle("-fx-background-color: #F6E8E8;");
		topBox.setPadding(new Insets(10, 10, 0, 10));

		vbox.setStyle("-fx-background-color: #F6DCDA;");
		vbox.setAlignment(Pos.CENTER);
		vbox.setMaxWidth(View.getCanvasWidth() - 30);
		vbox.setPrefSize((View.getCanvasWidth() / 2), View.getCanvasHeight() * (40 / 16));
		vbox.setPadding(new Insets(10, 10, 10, 10));

		populateQuestionnaire();
		createButtons();

		tilePane.setAlignment(Pos.CENTER);
		tilePane.setPadding(new Insets(5, 5, 5, 5));
		tilePane.setHgap(100);
		tilePane.getChildren().addAll(backToExistingPlants, save, toPlotDesign);

		scroll = new ScrollPane();
		scroll.setStyle("-fx-background-color: #FFFFFF;" + "-fx-border-color: #F6AAA4;" + "-fx-border-insets: 5;"
				+ "-fx-border-width: 3;" + "-fx-border-style: solid;");
		scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		scroll.setMaxWidth((View.getCanvasWidth() - 450));
		scroll.setPrefSize((View.getCanvasWidth() - 600),
				View.getCanvasHeight() - tilePane.getHeight() - topBox.getHeight() - 115);
		scroll.setContent(vbox);

		borderPane.setStyle("-fx-background-color: #F6E8E8;");
		borderPane.setLeft(leftLeaves);
		borderPane.setRight(rightLeaves);
		borderPane.setTop(topBox);
		borderPane.setCenter(scroll);
		borderPane.setBottom(tilePane);

		this.root = new Group();
		root.getChildren().add(borderPane);
		this.scene = new Scene(this.root, View.getCanvasWidth(), View.getCanvasHeight());
	}

	/**
	 * Creates a vertical column of the logo multiple times. This shows up twice, on
	 * either side of the questionnaire
	 * 
	 * @param box
	 */
	public void createLeaves(VBox box) {
		box.setSpacing(10);
		box.setAlignment(Pos.CENTER);

		for (int i = 0; i < 5; i++) {
			Image logo = new Image(getClass().getResourceAsStream("/buttonImages/fiveLeaf.png"));
			ImageView logoShow = new ImageView(logo);
			logoShow.setFitHeight(logoShow.getFitWidth() / 2);
			logoShow.setFitWidth(logoShow.getFitWidth() / 2);
			box.getChildren().add(logoShow);
		}
	}

	/**
	 * Pulls together the questions and how it is handled into the vbox. Ability to
	 * change order of questions here.
	 */
	public void populateQuestionnaire() {
		createQ1();
		createQ2();
		createQ3();
		createQ4();
		createQ5();
		createQ6();
		createQ7();
		createQ8();
		createQ9();
	}

	/**
	 * Creates the Text of each question
	 * 
	 * @param question
	 * @return
	 */
	public Text createText(String question) {
		Text t = new Text(question);
		t.setWrappingWidth(800);
		t.setStyle("-fx-font-size: 20px;");
		vbox.getChildren().add(t);
		return t;
	}

	/**
	 * Question that allows user to type in the name of their garden
	 */
	public void createQ1() {
		createText("1) What would you like to name your Garden?");
		gardenLabel = new Label("Garden Name:");
		textField = new TextField();
		hbname = new HBox();
		hbname.getChildren().addAll(gardenLabel, textField);
		hbname.setSpacing(10);
		vbox.getChildren().addAll(hbname);
	}

	/**
	 * Question that allows the user to type the width and length of their plot
	 */
	public void createQ2() {
		createText("2) How big is the plot you wish to plant your garden (in ft). (NO LETTERS ALLOWED)");

		gardenWidth = new Label("Width:");
		q1textField1 = new TextField();
		hbwidth = new HBox();
		hbwidth.getChildren().addAll(gardenWidth, q1textField1);

		gardenHeight = new Label("Height:");
		q1textField2 = new TextField();
		hbheight = new HBox();
		hbheight.getChildren().addAll(gardenHeight, q1textField2);

		ArrayList<HBox> setHBoxAttributes = new ArrayList<HBox>();
		setHBoxAttributes.add(hbwidth);
		setHBoxAttributes.add(hbheight);

		for (HBox hb : setHBoxAttributes) {
			hb.setSpacing(10);
		}
		vbox.getChildren().addAll(hbwidth, hbheight);
	}

	/**
	 * Question asking user if their garden is near a road or forest
	 */
	public void createQ3() {

		createText("3) Is your plot near any of the following? (Please select all that apply)");
		List<String> objectsNearPlot = List.of(PlotObjects.ROAD.toString(), PlotObjects.FOREST.toString()); 
		ObservableList<CheckBox> q2items = FXCollections.observableArrayList(); // add checkboxes to this list
		for (String object : objectsNearPlot) {
			CheckBox c = new CheckBox(object);
			q2items.add(c); // added to this list to view
			nearPlot.add(c); // added to this arrayList for future checking purposes when user clicks save
		}
		q2ListView = new ListView<>();
		q2ListView.setItems(q2items); // add the items in the observable array to the listView
		q2ListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		vbox.getChildren().addAll(q2ListView);

	}

	/**
	 * Question asking the user if they have any of the items (plot objects) in
	 * their plot
	 */
	public void createQ4() {

		createText("4) Do you have any of the following items in your garden? (Please select all that apply)");
		List<String> objectsInPlot = List.of("FENCE", "POOL", "PLAYGROUND", "PATH", "NONREMOVEABLE TREES", "PATIO",
				"BIRDBATH", "SHED", "ROCKS", "OTHER"); // list of items for near plot
		ObservableList<CheckBox> q3items = FXCollections.observableArrayList(); // add checkboxes to this list
		for (String object : objectsInPlot) {
			CheckBox c = new CheckBox(object);
			q3items.add(c); // added to this list to view
			inPlot.add(c); // added to this arrayList for future checking purposes when user clicks save
		}
		q3ListView = new ListView<>();
		q3ListView.setItems(q3items); // add the items in the observable array to the listView
		q3ListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		vbox.getChildren().addAll(q3ListView);

	}

	/**
	 * Question asking about the moisture of the user's plot
	 */
	public void createQ5() {
		createText(
				"5) Does your entire plot have the same level of moisture? If yes, what level of moisture does your garden have?");
		q4ChoiceBox = new ChoiceBox<>();
		q4ChoiceBox.getItems().addAll(Moisture.DRY.toString(), Moisture.DRY_MOIST.toString(), Moisture.MOIST.toString(),
				Moisture.MOIST_DAMP.toString(), Moisture.DAMP.toString(), "My plot has different moisture");
		vbox.getChildren().addAll(q4ChoiceBox);
	}

	/**
	 * Question asking about the soil type of the user's plot
	 */
	public void createQ6() {
		createText(
				"6) Does your entire plot have the same soil type? If yes, what soil type does your garden have?");
		q5ChoiceBox = new ChoiceBox<>();
		q5ChoiceBox.getItems().addAll(SoilTypes.CLAY.toString(), SoilTypes.SANDY.toString(), SoilTypes.LOAMY.toString(),
				"My plot has different soil types");
		vbox.getChildren().addAll(q5ChoiceBox);
	}

	/**
	 * Question asking about the sunlight of the user's plot
	 */
	public void createQ7() {
		createText(
				"7) Does your entire plot receive the same amount of sunlight? If yes, to what degree of lighing does your garden get?");
		q6ChoiceBox = new ChoiceBox<>();
		q6ChoiceBox.getItems().addAll(Sunlight.FULLSUN.toString(), Sunlight.PARTIALSHADE.toString(),
				Sunlight.PARTIALSUN.toString(), Sunlight.FULLSHADE.toString(),
				"My plot receives different levels of sunlight");
		vbox.getChildren().addAll(q6ChoiceBox);
	}

	/**
	 * Question asking about when the user wants their flowers to bloom
	 */
	public void createQ8() {

		createText("8) When would you like to see your garden bloom? (Please select all that apply?");
		List<String> seasonsWanted = List.of(Seasons.SPRING.toString(), Seasons.SUMMER.toString(),
				Seasons.WINTER.toString(), Seasons.FALL.toString(), Seasons.YEARROUND.toString()); 
		ObservableList<CheckBox> q7items = FXCollections.observableArrayList(); // add checkboxes to this list
		for (String object : seasonsWanted) {
			CheckBox c = new CheckBox(object);
			q7items.add(c); // added to this list to view
			seasonWant.add(c); // added to this arrayList for future checking purposes when user clicks save
		}
		q7ListView = new ListView<>();
		q7ListView.setItems(q7items);
		q7ListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		vbox.getChildren().addAll(q7ListView);
	}

	/**
	 * Question asking about what colors they would like to see in their garden
	 */
	public void createQ9() {

		createText("9) What color blooms would you like to see in your garden? (Please select all that apply)");
		List<String> colorsWanted = List.of("WHITE", "YELLOW", "ORANGE", "RED", "PURPLE", "BLUE", "GREEN", "BROWN"); 
		ObservableList<CheckBox> q8items = FXCollections.observableArrayList();
		for (String object : colorsWanted) {
			CheckBox c = new CheckBox(object);
			q8items.add(c); // added to this list to view
			colorWant.add(c); // added to this arrayList for future checking purposes when user clicks save
		}
		q8ListView = new ListView<>();
		q8ListView.setItems(q8items);
		q8ListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		vbox.getChildren().addAll(q8ListView);
	}

	/**
	 * Creates the handling for the buttons at the bottom of the screen 
	 */
	public void createButtons() {
		backToExistingPlants = new Button("Go Back");
		backToExistingPlants.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				switchToWindow(Windows.ExistingPlants);
			}
		});

		save = new Button("Save");
		save.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				//sends information from questionnaire to Session for later use
				getSession().setPlotName(textField.getText());
				getSession().setWidthOfUserPlot(Integer.parseInt(q1textField1.getText()));
				getSession().setLengthOfUserPlot(Integer.parseInt(q1textField2.getText()));
				getSession().setObjectsNearPlot(checkSelectedNearPlot(nearPlot));
				getSession().setObjectsInPlot(checkSelectedInPlot(inPlot));
				getSession().setMoistureOfPlot(getChoice(q4ChoiceBox));
				getSession().setSoilTypeOfPlot(getChoice(q5ChoiceBox));
				getSession().setSunlightOfPlot(getChoice(q6ChoiceBox));
				getSession().setSeasonsUserSelected(checkSelectedSeasons(seasonWant));
				getSession().setColorsUserWants(checkSelectedColor(colorWant));

			}
		});

		toPlotDesign = new Button("Next");
		toPlotDesign.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				switchToWindow(Windows.PlantSelection);
			}
		});
	}

	@Override
	public Scene getScene() {
		// TODO Auto-generated method stub
		return this.scene;

	}

	/**
	 * Extracts String value from choice box. Used on button call save
	 * 
	 * @param q4ChoiceBox2
	 * @return
	 */
	public String getChoice(ChoiceBox<String> choice) {
		String x = choice.getValue().toString();
		return x;
	}

	/**
	 * Checks which options are selected by the user and returns an arraylist of
	 * PlotObjects
	 * 
	 * @param cb --> checkboxes selected for objects outside (near) of plot
	 */
	public ArrayList<PlotObject> checkSelectedNearPlot(ArrayList<CheckBox> cb) {

		plotNearArr = new ArrayList<PlotObject>();

		for (int counter = 0; counter < cb.size(); counter++) {
			if (cb.get(counter).isSelected()) {

				// TODO: Shouldn't be creating new object instances, use the 
				//			newly created PlotObjects enum
				/*
				if (cb.get(counter).getText().equals("Road")) {
					plotNearArr.add(new PlotRoad());
				} else if (cb.get(counter).getText().equals(PlotObjects.FOREST.toString())) {
					plotNearArr.add(new PlotForest());
				}*/

			}
		}
		return plotNearArr;
	}

	/**
	 * Checks which options are selected by the user and returns an ArrayList of
	 * PlotObjects
	 * 
	 * @param cb --> checkboxes that are in each listview for objects IN the user's plot
	 */
	public ArrayList<PlotObject> checkSelectedInPlot(ArrayList<CheckBox> cb) {

		plotInArr = new ArrayList<PlotObject>();

		for (int counter = 0; counter < cb.size(); counter++) {
			if (cb.get(counter).isSelected()) {
        /*
				if (cb.get(counter).getText().equals("FENCE")) {
					plotInArr.add(new PlotFence());
				} else if (cb.get(counter).getText().equals("POOL")) {
					plotInArr.add(new PlotPool());
				} else if (cb.get(counter).getText().equals("PLAYGROUND")) {
					plotInArr.add(new PlotPlayground());
				} else if (cb.get(counter).getText().equals("Path")) {
					plotInArr.add(new PlotPath());
				} else if (cb.get(counter).getText().equals("Non-Removeable trees")) {
					// TODO: Remove checkbox 5. PlotTrees were deleted because we
					// 			have PlotPlant, and trees are plants and we shouldn't
					//			duplicate that
					//plotInArr.add(new PlotTrees());
				} else if (cb.get(counter).getText().equals("Patio/other lounging area")) {

				} else if (cb.get(counter).getText().equals("NONREMOVEABLE TREES")) {
					plotInArr.add(new PlotTrees());
				} else if (cb.get(counter).getText().equals("PATIO")) {
					plotInArr.add(new PlotPatio());
				} else if (cb.get(counter).getText().equals("BIRDBATH")) {
					plotInArr.add(new PlotBirdBath());
				} else if (cb.get(counter).getText().equals("SHED")) {
					plotInArr.add(new PlotShed());
				} else if (cb.get(counter).getText().equals("ROCKS")) {
					plotInArr.add(new PlotRock());
				} else if (cb.get(counter).getText().equals("OTHER")) {
					plotInArr.add(new PlotOther());
				}*/

			}
		}
		return plotInArr;
	}

	/**
	 * Checks which options are selected by the user and returns an arraylist of
	 * seasons
	 * 
	 * @param cb --> checkboxes selected for seasons 
	 */
	public ArrayList<Seasons> checkSelectedSeasons(ArrayList<CheckBox> cb) {

		seasonArr = new ArrayList<Seasons>();

		for (int counter = 0; counter < cb.size(); counter++) {
			if (cb.get(counter).isSelected()) {

				if (cb.get(counter).getText().equals("WINTER")) {
					seasonArr.add(Seasons.WINTER);
				} else if (cb.get(counter).getText().equals("SUMMER")) {
					seasonArr.add(Seasons.SUMMER);
				} else if (cb.get(counter).getText().equals("SPRING")) {
					seasonArr.add(Seasons.SPRING);
				} else if (cb.get(counter).getText().equals("FALL")) {
					seasonArr.add(Seasons.FALL);
				} else if (cb.get(counter).getText().equals("YEARROUND")) {
					seasonArr.add(Seasons.YEARROUND);
				}
			}
		}
		return seasonArr;
	}

	/**
	 * Checks which options are selected by the user and returns an arraylist of
	 * colors
	 * 
	 * @param cb --> checkboxes checked for the colors
	 */
	public ArrayList<Color> checkSelectedColor(ArrayList<CheckBox> cb) {

		colorArr = new ArrayList<Color>();

		for (int counter = 0; counter < cb.size(); counter++) {
			if (cb.get(counter).isSelected()) {
				if (cb.get(counter).getText().equals("WHITE")) {
					colorArr.add(Color.WHITE);
				} else if (cb.get(counter).getText().equals("YELLOW")) {
					colorArr.add(Color.YELLOW);
				} else if (cb.get(counter).getText().equals("ORANGE")) {
					colorArr.add(Color.ORANGE);
				} else if (cb.get(counter).getText().equals("RED")) {
					colorArr.add(Color.RED);
				} else if (cb.get(counter).getText().equals("PURPLE")) {
					colorArr.add(Color.PURPLE);
					colorArr.add(Color.VIOLET);
				} else if (cb.get(counter).getText().equals("BLUE")) {
					colorArr.add(Color.BLUE);
				} else if (cb.get(counter).getText().equals("GREEN")) {
					colorArr.add(Color.GREEN);
				} else if (cb.get(counter).getText().equals("BROWN")) {
					colorArr.add(Color.BROWN);
				}
			}
		}
		return colorArr;
	}

}
