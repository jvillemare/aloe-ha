package udel.GardenProject.windows;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import udel.GardenProject.enums.Windows;
import udel.GardenProject.garden.Model;
import udel.GardenProject.plants.Plant;

/**
 * To display all the information of a Plant to the user.
 * 
 * @author Team 0
 */
public class PlantSelection extends Window {

	private Group root;
	private Scene scene;

	private BorderPane borderPane;
	private TilePane tilePane;
	private VBox vbox;
	private Text text;

	private Button back, next;

	private ScrollPane scroll;
	private FlowPane flow;

	private Plant plantArray[];

	private HBox toggles;
	private ToggleGroup plantGroup;
	private ToggleButton trees, bushes, flowers;

	public PlantSelection(Model m) {
		super(m, "Plant Selection");

		borderPane = new BorderPane();
		vbox = new VBox();
		tilePane = new TilePane();

		text = new Text("Please select the plants you'd like to have in your Garden");
		text.setWrappingWidth(800);
		text.setStyle("-fx-font-size: 20px;");

		plantGroup = new ToggleGroup();
		trees = new ToggleButton("Trees");
		bushes = new ToggleButton("Bushes");
		flowers = new ToggleButton("Flowers");

		trees.setToggleGroup(plantGroup);
		bushes.setToggleGroup(plantGroup);
		flowers.setToggleGroup(plantGroup);

		toggles = new HBox();
		toggles.setAlignment(Pos.CENTER);
		toggles.getChildren().addAll(trees, bushes, flowers);

		vbox.getChildren().addAll(text, toggles);

		/**
		 * TODO: need to get the proper information of the categories
		 */
		trees.setOnAction((ActionEvent e) -> {
			System.out.println("trees");
			// set so user is only looking at plant in the tree category

		});
		bushes.setOnAction((ActionEvent e) -> {
			System.out.println("bushes");
			// set so user is only looking at plant in the bushes category

		});
		flowers.setOnAction((ActionEvent e) -> {
			System.out.println("flowers");
			// set so user is only looking at plant in the flowers category
		});
		/*
		 * May need to add more toggles depending on how many categories we want to do
		 * and how many comparators we want to make
		 */

		createButtons();

		flow = new FlowPane();
		flow.setPadding(new Insets(5, 5, 5, 5));
		flow.setVgap(10);
		flow.setHgap(10);
		flow.setPrefWrapLength(870);
		flow.setStyle("-fx-background-color: DAE6F3;");

		/**
		 * TODO: Fix this so the correct plants under each label are shown This is used
		 * for testing purposes
		 */
		Image pages[] = new Image[40];
		for (int i = 0; i < 40; i++) {
			pages[i] = new Image(getClass().getResourceAsStream("/buttonImages/tree.png"), 350, 100, true, true);
			ImageView imageView = new ImageView(pages[i]);
			Button addPlant = new Button("Add Plant");

			addPlant.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					System.out.println("Add Plant: Adding Plant");

					ColorAdjust colorAdjustGrayscale = new ColorAdjust();
					colorAdjustGrayscale.setSaturation(-1);
					imageView.setEffect(colorAdjustGrayscale);

				}
			});

			Button infoButton = new Button("Info");
			infoButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					System.out.println("Info: Retreiving Plant information");
					getModel().setPlantInfoPlant(getModel().getPlants().get(0));
					switchToWindow(Windows.PlantInfo);
				}
			});

			HBox hbox = new HBox();
			hbox.getChildren().addAll(infoButton, addPlant);

			VBox imgButtonHolder = new VBox();
			imgButtonHolder.getChildren().addAll(imageView, hbox);
			flow.getChildren().add(imgButtonHolder);
		}

		scroll = new ScrollPane();
		scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		scroll.setVmax(440);
		scroll.setPrefSize(895, 520);

		scroll.setContent(flow);

		tilePane.setAlignment(Pos.CENTER);
		tilePane.setPadding(new Insets(5));
		tilePane.setHgap(100);
		tilePane.getChildren().addAll(back, next);

		borderPane.setPadding(new Insets(5, 5, 5, 5));
		borderPane.setTop(vbox);
		borderPane.setBottom(tilePane);
		borderPane.setCenter(scroll);

		this.root = new Group();
		root.getChildren().add(borderPane);
		this.scene = new Scene(this.root, 900, 600);
	}

	public void createButtons() {
		back = new Button("Go Back");
		back.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				switchToWindow(Windows.Questionnaire);
			}
		});

		next = new Button("Next");
		next.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				switchToWindow(Windows.PlotDesign);
			}
		});
	}

	@Override
	public Scene getScene() {
		// TODO Auto-generated method stub
		return this.scene;
	}

}
