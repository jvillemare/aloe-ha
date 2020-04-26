package udel.GardenProject.windows;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import udel.GardenProject.enums.Windows;
import udel.GardenProject.garden.Model;
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
	 * Button that saves ... TODO what?
	 */
	private Button saveButton;
	
	/**
	 * Button that goes to the previous window
	 */
	private Button backButton;
	
	private BorderPane border;
	
	private HBox searchBox;
	
	private FlowPane flow;
	
	private ScrollPane scroll;
	
	public AllPlants(Model m) {
		super(m, "Plant Database");
		
		Label label = new Label("So many plants...");
		
		border = new BorderPane();
		
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
		flow.setPadding(new Insets(5, 5, 5, 5));
		flow.setVgap(10);
		flow.setHgap(10);
		flow.setPrefWrapLength(870);
		flow.setStyle("-fx-background-color: DAE6F3;");

		// This is used for testing purposes
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
					switchToWindow(Windows.PlantInfo);
					//getModel().getWindow(Windows.PlantInfo).displayPlant(myCoolPlant, Windows.PlantSelection);
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
		
		border.setTop(txt);
		border.setStyle("-fx-background-color: DAE6F3;");
		border.setLeft(searchBox);
		border.setCenter(scroll);
		
		StackPane button = new StackPane();
		Button back = new Button("Go Back");
		back.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				switchToWindow(Windows.PlotDesign);
			}
		});
	
		button.setStyle("-fx-background-color: DAE6F3;");
		button.getChildren().add(back);
		
		border.setBottom(button); 
		
		this.root = new Group();
		root.getChildren().add(border);
		this.scene = new Scene(this.root, 1150, 570);
	}
	
	@Override
	public Scene getScene() {
		// TODO Auto-generated method stub
		return this.scene;
	}

}
