package udel.GardenProject.windows;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import javax.swing.event.ChangeListener;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import udel.GardenProject.enums.Windows;
import udel.GardenProject.garden.Model;
import udel.GardenProject.plants.Plant;

/**
 * Where a user can specify what plants already exist in their garden.
 *
 * @author Team 0
 */
public class ExistingPlants extends Window {

	private Group root;
	private Scene scene;
	private BorderPane borderPane;
	private VBox vbox;

	// TODO: Change to ArrayList?
	private Plant existingPlant[];

	private Button backToMain, save, nextButton;

	/**
	 * Used for the search box and the options that show up
	 */
	private GridPane container;
	private HBox searchBox;

	/**
	 * Used for top message
	 */
	private Text text1;

	private TilePane tilePane;

	private ScrollPane scroll;

	/**
	 * Used for displaying what the user selected
	 */
	private static VBox selection;

	/**
	 * Create an ExistingPlants window instance.
	 *
	 * @param m Model
	 */
	public ExistingPlants(Model m) {
		super(m, "What Plants are in your Garden?");

		container = new GridPane();
		searchBox = new HBox();

		tilePane = new TilePane();

		// this is just for testing
		String[] options = { "pine tree", "lavendar", "sun flower" };

		TextField text = new TextField();

		text.textProperty().addListener((observable, oldValue, newValue) -> {
			if (container.getChildren().size() > 1) {
				container.getChildren().remove(1);
			}
			container.add(populateDropDownMenu(newValue, options), 0, 1);
		});

		Button close = new Button("X");
		close.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("X: removing typing");
				text.setText("");
			}
		});

		searchBox.getChildren().addAll(text, close);

		container.add(searchBox, 0, 0);
		container.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));

		borderPane = new BorderPane();
		vbox = new VBox();

		text1 = new Text("Which plants are already in your Garden?");
		text1.setWrappingWidth(800);
		text1.setStyle("-fx-font-size: 20px;");

		vbox.getChildren().addAll(text1);
		vbox.setPadding(new Insets(10, 10, 10, 10));

		backToMain = new Button("Back to Main");
		backToMain.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("Back to Main: going back to main");
				switchToWindow(Windows.Welcome);
			}
		});

		save = new Button("Save");
		save.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("Save: saving existing plants");
				// get existing plants function
			}
		});

		nextButton = new Button("Next");
		nextButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("Next: going to questionnaire");
				switchToWindow(Windows.Questionnaire);
			}
		});

		tilePane.setAlignment(Pos.CENTER);
		tilePane.setPadding(new Insets(5));
		tilePane.setHgap(100);
		tilePane.getChildren().addAll(backToMain, save, nextButton);

		scroll = new ScrollPane();
		scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
		scroll.setVmax(440);
		scroll.setPrefSize(690, 600);
		scroll.setContent(vbox);

		selection = new VBox();

		vbox.getChildren().add(selection);

		borderPane.setRight(scroll);
		borderPane.setLeft(container);
		borderPane.setTop(vbox);
		borderPane.setBottom(tilePane);

		this.root = new Group();
		root.getChildren().add(borderPane);
		this.scene = new Scene(this.root, 860, 640);
	}

	// TODO: Constructor to pass in plant array?

	@Override
	public Scene getScene() {
		// TODO Auto-generated method stub
		return this.scene;

	}

	/**
	 * Return a list of all existing plants.
	 *
	 * @return array of all plants available in GardenProject.
	 */
	public Plant[] getExistingPlants() {
		return this.existingPlant;
	}

	/**
	 * Set all the existing plants.
	 *
	 * @param p list of plants to use in project.
	 */
	public void setExistingPlants(Plant p) {

	}

	public static VBox populateDropDownMenu(String text, String[] options) {
		VBox dropDownMenu = new VBox();
		dropDownMenu.setBackground(new Background(new BackgroundFill(Color.DARKSEAGREEN, null, null)));
		dropDownMenu.setAlignment(Pos.CENTER);

		for (String option : options) {
			if (!text.replace(" ", "").isEmpty() && option.toUpperCase().contains(text.toUpperCase())) {
				Label label = new Label(option);

				label.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent mouseEvent) {
						if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
							if (mouseEvent.getClickCount() == 1) {
								label.setStyle("-fx-font-weight: bold");
								TextArea textarea = new TextArea(label.getText());
								textarea.setPrefHeight(label.getHeight() + 10);
								System.out.println("You selected a plant from the dropdown --> " + label.getText());

								Button deleteButton = new Button("X");
								HBox selectedPlant = new HBox();
								selectedPlant.setMaxWidth(Double.MAX_VALUE);
								selectedPlant.getChildren().addAll(textarea, deleteButton);

								selection.getChildren().addAll(selectedPlant);
								deleteButton.setOnAction(new EventHandler<ActionEvent>() {
									@Override
									public void handle(ActionEvent event) {
										System.out.println("X: removing selection");
										textarea.clear();
									}
								});
							}
						}
					}
				});
				dropDownMenu.getChildren().add(label);
			}
		}
		return dropDownMenu;
	}

}
