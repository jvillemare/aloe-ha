package udel.GardenProject.windows;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import udel.GardenProject.enums.Windows;
import udel.GardenProject.garden.Model;
import udel.GardenProject.garden.View;
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

	/**
	 * For getting rid of duplicates the user has entered
	 */
	private static HashSet<String> existingPlants = new HashSet();

	/**
	 * Used to convert HashSet existingPlants into an ArrayList
	 */
	private List<String> existingPlantsList;

	/**
	 * Used for the buttons at the top of the screen
	 */
	private Button backToMain, save, nextButton;

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
	 * Used to place button for the bottom of the borderPane
	 */
	private TilePane tilePane;

	/**
	 * Used to help user look through all the choices they have made
	 */
	private ScrollPane scroll;

	/**
	 * Used for displaying what the user selected
	 */
	private static VBox selection;

	private TextField text;

	/**
	 * Create an ExistingPlants window instance.
	 *
	 * @param m Model
	 */
	public ExistingPlants(Model m) {
		super(m, "What Plants are in your Garden?");

		borderPane = new BorderPane();
		vbox = new VBox();
		container = new GridPane();
		searchBox = new HBox();
		tilePane = new TilePane();

		selection = new VBox();
		selection.setPadding(new Insets(5, 5, 5, 5));
		selection.setMaxWidth(700);
		selection.setStyle("-fx-background-color: #F6E8E8;"); // pink behind the selected plants

		text1 = new Text("Which plants are already in your Garden?");

		text1.setWrappingWidth(View.getCanvasWidth() - selection.getWidth() - 170);
		text1.setStyle("-fx-font-size: 20px;");
		text1.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/Hack-Bold.ttf"), 20));
		vbox.setStyle("-fx-background-color: #F6E8E8;");
		vbox.getChildren().addAll(text1);
		vbox.setPadding(new Insets(10, 10, 10, 10));

		createSearch();
		createButtons();

		tilePane.setAlignment(Pos.CENTER);
		tilePane.setPadding(new Insets(5));
		tilePane.setHgap(100);
		tilePane.getChildren().addAll(backToMain, save, nextButton);

		vbox.getChildren().add(selection);

		scroll = new ScrollPane();
		scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		scroll.setPrefSize(View.getCanvasWidth() - selection.getWidth() - 208,
				View.getCanvasHeight() - vbox.getHeight() - tilePane.getHeight() - 35);
		scroll.setContent(vbox);

		borderPane.setStyle("-fx-background-color: #F6E8E8;"); // pink
		borderPane.setRight(scroll);
		borderPane.setLeft(container);
		borderPane.setTop(vbox);
		borderPane.setBottom(tilePane);

		this.root = new Group();
		root.getChildren().add(borderPane);
		this.scene = new Scene(this.root, View.getCanvasWidth(), View.getCanvasHeight());
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

	/**
	 * Creating buttons at the bottom of the screen
	 */
	public void createButtons() {

		backToMain = new Button("Back to Main");
		backToMain.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				switchToWindow(Windows.Welcome);
			}
		});

		save = new Button("Save");
		save.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// get existing plants function
			}
		});

		nextButton = new Button("Next");
		nextButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				existingPlantsList = new ArrayList<String>(existingPlants);
				System.out.println(existingPlantsList);
				switchToWindow(Windows.Questionnaire);
			}
		});
	}

	/**
	 * Used to create searching area for when the user looks up plants
	 */
	public void createSearch() {

		int len = this.getModel().getPlants().size();
		String[] options = new String[len];
		for (int i = 0; i < len; i++) {
			options[i] = this.getModel().getPlants().get(i).getLatinName();
			if (this.getModel().getPlants().get(i).getCommonNames() != null) {
				String common1 = this.getModel().getPlants().get(i).getCommonNames()[0];
				options[i] = options[i] + " (" + common1 + ")";
			}
		}

		text = new TextField();
		text.textProperty().addListener((observable, oldValue, newValue) -> {
			if (container.getChildren().size() > 1) {
				container.getChildren().remove(1);
			}
			container.add(populateDropDownMenu(newValue, options), 0, 1);
		});

		close = new Button("Clear");
		close.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/Hack-Bold.ttf"), 13));
		close.setStyle("-fx-background-color: #FFFFFF;" + "-fx-border-width: 1;" + "-fx-border-color: #000000;");
		close.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				text.setText("");

			}
		});

		searchBox.getChildren().addAll(text, close);

		container.add(searchBox, 0, 0);
		container.setStyle("-fx-background-color: #76C327;"); // green
	}

	public static VBox populateDropDownMenu(String text, String[] options) {
		VBox dropDownMenu = new VBox();
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
								if (existingPlants.add(label.getText())) {

									Text textarea = new Text(label.getText());
									textarea.setStyle("-fx-font-size: 20px;");
									textarea.setFont(Font
											.loadFont(getClass().getResourceAsStream("/fonts/Hack-Regular.ttf"), 15));

									Button deleteButton = new Button("X");
									deleteButton.setStyle("-fx-background-color: #FFFFFF;" + "-fx-text-fill: #BC0504;"
											+ "-fx-border-width: 1;" + "-fx-border-color: #000000;"); // creates the red
																										// X inside the
																										// button
									deleteButton.setFont(Font.font("Verdana", FontWeight.BOLD, 10));

									DropShadow shadow = new DropShadow();
									deleteButton.addEventHandler(MouseEvent.MOUSE_ENTERED,
											new EventHandler<MouseEvent>() {
												@Override
												public void handle(MouseEvent e) {
													deleteButton.setEffect(shadow);
													deleteButton.setStyle("-fx-background-color: #C1AFAF;"
															+ "-fx-text-fill: #BC0504;" + "-fx-border-width: 1;"
															+ "-fx-border-color: #000000;");
												}
											});

									deleteButton.addEventHandler(MouseEvent.MOUSE_EXITED,
											new EventHandler<MouseEvent>() {
												@Override
												public void handle(MouseEvent e) {
													deleteButton.setEffect(null);
													deleteButton.setStyle("-fx-background-color: #FFFFFF;"
															+ "-fx-text-fill: #BC0504;" + "-fx-border-width: 1;"
															+ "-fx-border-color: #000000;");
												}
											});

									HBox selectedPlant = new HBox(100);
									selectedPlant.getChildren().addAll(deleteButton, textarea);

									selection.getChildren().addAll(selectedPlant);
									deleteButton.setOnAction(new EventHandler<ActionEvent>() {
										@Override
										public void handle(ActionEvent event) {
											existingPlants.remove(label.getText());
											selectedPlant.getChildren().removeAll(deleteButton, textarea);
										}
									});
								}
							}
						}
					}
				});
				dropDownMenu.getChildren().add(label);
			}
		}
		return dropDownMenu;
	}

	private static void setFont(Font loadFont) {
		// TODO Auto-generated method stub

	}

}
