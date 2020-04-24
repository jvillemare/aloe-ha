package udel.GardenProject.windows;

import javafx.collections.FXCollections;
import java.util.ArrayList;

import javax.script.Bindings;

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

		String[] questions = { "1) What would you like to name your Garden?",
				"2) How big is the plot you wish to plant your garden",
				"3) Is your plot near any of the following? (Please select all that apply)",
				"4) Do you have any of the following items in your garden? (Please select all that apply)",
				"5) Does your entire plot have the same level of moisture? If yes, what level of moisture does your garden have?",
				"6) Does your entire plot have the same soil type? If yes, what soil type does your garden have?",
				"7) Does your entire plot receive the same amount of sunlight? If yes, to what degree of lighing does your garden get?",
				"8) When would you like to see your garden bloom? (Please select all that apply?",
				"9) What color blooms would you like to see in your garden? (Please select all that apply)" };

		borderPane = new BorderPane();
		vbox = new VBox();
		tilePane = new TilePane();

		text = new Text(
				"Welcome to the Aloe-ha questionnaire! Please fill out the questions below. Remember, you must answer all of the questions to continue.\n");
		text.setWrappingWidth(800);
		text.setStyle("-fx-font-size: 20px;");

		q0 = new Text(questions[0]);
		q0.setWrappingWidth(800);
		q0.setStyle("-fx-font-size: 20px;");

		gardenLabel = new Label("Garden Name:");
		textField = new TextField();
		hbname = new HBox();
		hbname.getChildren().addAll(gardenLabel, textField);
		hbname.setSpacing(10);

		q1 = new Text(questions[1]);
		q1.setWrappingWidth(800);
		q1.setStyle("-fx-font-size: 20px;");

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

		q2 = new Text(questions[2]);
		q2.setWrappingWidth(800);
		q2.setStyle("-fx-font-size: 20px;");

		q2checkBox1 = new CheckBox("Road");
		q2checkBox2 = new CheckBox("Forest");

		q2ListView = new ListView<>();
		ObservableList<CheckBox> q1items = FXCollections.observableArrayList(q2checkBox1, q2checkBox2);
		q2ListView.setItems(q1items);
		q2ListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		q3 = new Text(questions[3]);
		q3.setWrappingWidth(800);
		q3.setStyle("-fx-font-size: 20px;");

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

		q4 = new Text(questions[4]);
		q4.setWrappingWidth(800);
		q4.setStyle("-fx-font-size: 20px;");

		q4ChoiceBox = new ChoiceBox<>();
		q4ChoiceBox.getItems().addAll("Dry", "Dry-moist", "Moist", "Moist-damp", "Damp",
				"My plot has different moisture");

		q5 = new Text(questions[5]);
		q5.setWrappingWidth(800);
		q5.setStyle("-fx-font-size: 20px;");

		q5ChoiceBox = new ChoiceBox<>();
		q5ChoiceBox.getItems().addAll("Peaty", "Loamy", "Sandy", "Chalky", "Clay", "Silty",
				"My plot has different soil types");

		q6 = new Text(questions[6]);
		q6.setWrappingWidth(800);
		q6.setStyle("-fx-font-size: 20px;");

		q6ChoiceBox = new ChoiceBox<>();
		q6ChoiceBox.getItems().addAll("Full-sun", "Partial-shade", "Partial-sun", "Full-shade",
				"My plot receives different levels of sunlight");

		q7 = new Text(questions[7]);
		q7.setWrappingWidth(800);
		q7.setStyle("-fx-font-size: 20px;");

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

		q8 = new Text(questions[8]);
		q8.setWrappingWidth(800);
		q8.setStyle("-fx-font-size: 20px;");

		q8checkBox1 = new CheckBox("White");
		q8checkBox2 = new CheckBox("Yello");
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

		vbox.setPadding(new Insets(10, 10, 10, 10));
		vbox.getChildren().addAll(text, q0, hbname, q1, hbwidth, hbheight, q2, q2ListView, q3, q3ListView, q4,
				q4ChoiceBox, q5, q5ChoiceBox, q6, q6ChoiceBox, q7, q7ListView, q8, q8ListView);

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
				// getResponses(...);
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

		tilePane.setAlignment(Pos.CENTER);
		tilePane.setPadding(new Insets(5));
		tilePane.setHgap(100);
		tilePane.getChildren().addAll(backToExistingPlants, save, toPlotDesign);

		scroll = new ScrollPane();
		scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
		scroll.setVmax(440);
		scroll.setPrefSize(850, 600);
		scroll.setContent(vbox);

		borderPane.setRight(scroll);
		borderPane.setTop(vbox);
		borderPane.setBottom(tilePane);

		this.root = new Group();
		root.getChildren().add(borderPane);
		this.scene = new Scene(this.root, 850, 650);
	}

	@Override
	public Scene getScene() {
		// TODO Auto-generated method stub
		return this.scene;

	}

	/*
	 * TODO: change function so it gets all the answers at the same time This
	 * function will be used in save.setOnAction
	 */
	private void getResponses(ChoiceBox<String> choiceBox) {
		String choice = choiceBox.getValue();
		System.out.println(choice);
	}

}
