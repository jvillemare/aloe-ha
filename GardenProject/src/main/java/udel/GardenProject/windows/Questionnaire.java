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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import udel.GardenProject.enums.Windows;
import udel.GardenProject.garden.Model;

/**
 * Basic questions about a user's plots that informs what plants are selected.
 *
 * @author Team 0
 */
public class Questionnaire extends Window {

	private Group root;
	private Scene scene;

	/**
	 * Background layout
	 */
	private BorderPane borderPane;

	/**
	 * VBox creating for text for title and questions
	 */
	private VBox vbox;

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
	 * Used for user input
	 */
	Label gardenLabel, gardenWidth, gardenHeight;
	TextField textField, q1textField1, q1textField2;
	HBox hbname, hbwidth, hbheight;

	public Questionnaire(Model m) {
		super(m, "Questions About Your Garden...");

		borderPane = new BorderPane();
		vbox = new VBox();
		tilePane = new TilePane();

		text = new Text(
				"Welcome to the Aloe-ha questionnaire! Please fill out the questions below. Remember, you must answer all of the questions to continue.\n");
		text.setWrappingWidth(800);
		text.setStyle("-fx-font-size: 20px;");
		vbox.setStyle("-fx-background-color: DAE6F3;");
		vbox.setPadding(new Insets(10, 10, 10, 10));
		vbox.getChildren().add(text);

		populateQuestionnaire();
		createButtons();

		tilePane.setAlignment(Pos.CENTER);
		tilePane.setPadding(new Insets(5));
		tilePane.setHgap(100);
		tilePane.getChildren().addAll(backToExistingPlants, save, toPlotDesign);

		scroll = new ScrollPane();
		scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
		scroll.setVmax(440);
		scroll.setPrefSize(830, 600);
		scroll.setContent(vbox);

		borderPane.setStyle("-fx-background-color: DAE6F3;");
		borderPane.setRight(scroll);
		borderPane.setTop(vbox);
		borderPane.setBottom(tilePane);

		this.root = new Group();
		root.getChildren().add(borderPane);
		this.scene = new Scene(this.root, 830, 635);
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
		return t;
	}

	public void createQ1() {
		q0 = createText("1) What would you like to name your Garden?");

		gardenLabel = new Label("Garden Name:");
		textField = new TextField();
		hbname = new HBox();
		hbname.getChildren().addAll(gardenLabel, textField);
		hbname.setSpacing(10);

		vbox.getChildren().addAll(q0, hbname);
	}

	public void createQ2() {
		q1 = createText("2) How big is the plot you wish to plant your garden");

		gardenWidth = new Label("Width:");
		q1textField1 = new TextField();
		hbwidth = new HBox();
		hbwidth.getChildren().addAll(gardenWidth, q1textField1);
		hbwidth.setSpacing(10);

		gardenHeight = new Label("Height:");
		q1textField2 = new TextField();
		hbheight = new HBox();
		hbheight.getChildren().addAll(gardenHeight, q1textField2);
		hbheight.setSpacing(10);

		vbox.getChildren().addAll(q1, hbwidth, hbheight);
	}

	public void createQ3() {
		q2 = createText("3) Is your plot near any of the following? (Please select all that apply)");

		q2checkBox1 = new CheckBox("Road");
		q2checkBox2 = new CheckBox("Forest");

		q2ListView = new ListView<>();
		ObservableList<CheckBox> q1items = FXCollections.observableArrayList(q2checkBox1, q2checkBox2);
		q2ListView.setItems(q1items);
		q2ListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		vbox.getChildren().addAll(q2, q2ListView);
	}

	public void createQ4() {

		q3 = createText("4) Do you have any of the following items in your garden? (Please select all that apply)");

		q3checkBox1 = new CheckBox("Fence");
		q3checkBox2 = new CheckBox("Pool");
		q3checkBox3 = new CheckBox("Playground");
		q3checkBox4 = new CheckBox("Path");
		q3checkBox5 = new CheckBox("Non-Removeable trees");
		q3checkBox6 = new CheckBox("Patio/other lounging area");
		q3checkBox7 = new CheckBox("Bird Bath/Feeder");
		q3checkBox8 = new CheckBox("Shed");
		q3checkBox9 = new CheckBox("Rocks");
		q3checkBox10 = new CheckBox("Other");

		q3ListView = new ListView<>();
		ObservableList<CheckBox> q2items = FXCollections.observableArrayList(q3checkBox1, q3checkBox2, q3checkBox3,
				q3checkBox4, q3checkBox5, q3checkBox6, q3checkBox7, q3checkBox8, q3checkBox9, q3checkBox10);
		q3ListView.setItems(q2items);
		q3ListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		vbox.getChildren().addAll(q3, q3ListView);
	}

	public void createQ5() {
		q4 = createText(
				"5) Does your entire plot have the same level of moisture? If yes, what level of moisture does your garden have?");

		q4ChoiceBox = new ChoiceBox<>();
		q4ChoiceBox.getItems().addAll("Dry", "Dry-moist", "Moist", "Moist-damp", "Damp",
				"My plot has different moisture");

		vbox.getChildren().addAll(q4, q4ChoiceBox);
	}

	public void createQ6() {
		q5 = createText(
				"6) Does your entire plot have the same soil type? If yes, what soil type does your garden have?");

		q5ChoiceBox = new ChoiceBox<>();
		q5ChoiceBox.getItems().addAll("Clay", "Sandy", "Loamy", "My plot has different soil types");

		vbox.getChildren().addAll(q5, q5ChoiceBox);
	}

	public void createQ7() {
		q6 = createText(
				"7) Does your entire plot receive the same amount of sunlight? If yes, to what degree of lighing does your garden get?");

		q6ChoiceBox = new ChoiceBox<>();
		q6ChoiceBox.getItems().addAll("Full-sun", "Partial-shade", "Partial-sun", "Full-shade",
				"My plot receives different levels of sunlight");

		vbox.getChildren().addAll(q6, q6ChoiceBox);
	}

	public void createQ8() {

		q7 = createText("8) When would you like to see your garden bloom? (Please select all that apply?");

		q7checkBox1 = new CheckBox("Spring");
		q7checkBox2 = new CheckBox("Summer");
		q7checkBox3 = new CheckBox("Winter");
		q7checkBox4 = new CheckBox("Fall");
		q7checkBox5 = new CheckBox("Yearround");

		q7ListView = new ListView<>();
		ObservableList<CheckBox> q7items = FXCollections.observableArrayList(q7checkBox1, q7checkBox2, q7checkBox3,
				q7checkBox4, q7checkBox5);
		q7ListView.setItems(q7items);
		q7ListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		vbox.getChildren().addAll(q7, q7ListView);
	}

	public void createQ9() {

		q8 = createText("9) What color blooms would you like to see in your garden? (Please select all that apply)");

		q8checkBox1 = new CheckBox("White");
		q8checkBox2 = new CheckBox("Yellow");
		q8checkBox3 = new CheckBox("Orange");
		q8checkBox4 = new CheckBox("Red");
		q8checkBox5 = new CheckBox("Purple/Violet");
		q8checkBox6 = new CheckBox("Blue");
		q8checkBox7 = new CheckBox("Green");
		q8checkBox8 = new CheckBox("Brown");

		q8ListView = new ListView<>();
		ObservableList<CheckBox> q8items = FXCollections.observableArrayList(q8checkBox1, q8checkBox2, q8checkBox3,
				q8checkBox4, q8checkBox5, q8checkBox6, q8checkBox7, q8checkBox8);
		q8ListView.setItems(q8items);
		q8ListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		vbox.getChildren().addAll(q8, q8ListView);
	}

	public void createButtons() {
		backToExistingPlants = new Button("Go Back");
		backToExistingPlants.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("Go Back: moving to ExistingPlants window");
				switchToWindow(Windows.ExistingPlants);
			}
		});

		save = new Button("Save");
		save.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("Save: saving questionnaire responses");
				getResponses();

			}
		});

		toPlotDesign = new Button("Next");
		toPlotDesign.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("Next: moving to PlantSelection window");
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
	 * TODO:
	 */
	private void getResponses() {

	}

}
