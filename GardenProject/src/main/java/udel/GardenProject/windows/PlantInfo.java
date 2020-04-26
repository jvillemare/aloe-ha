package udel.GardenProject.windows;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import udel.GardenProject.enums.Windows;
import udel.GardenProject.garden.Model;
import udel.GardenProject.plants.Plant;

/**
 * To display all the information of a Plant to the user.
 * 
 * @author Team 0
 */
public class PlantInfo extends Window {
	
	private Scene scene;
	private Group root;
	
	private Button backButton;
	private Image plantImage;
	
	private BorderPane borderPane;

	public PlantInfo(Model m) {
		super(m, "Plant Info: "); 		
		
		borderPane = new BorderPane();
		
		
		backButton = new Button("Go Back");
		backButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
            	switchToWindow(Windows.Questionnaire);
            }
        });
		
		borderPane.setBottom(backButton);
		
		
		
	}
	
	@Override
	public Scene getScene() {
		// TODO Auto-generated method stub
		return this.scene;
	}
	
	/**
	 * Change PlantInfo's scene and display a plant's info by it's latin name.
	 * This will be used by PlantSelection and AllPlants for their button
	 * getModel().getWindow(Windows.PlantInfo).displayPlant(myCoolPlant, Windows.AllPlants)
	 * 
	 * @param plantLatinName Linnaeus Genus species plant name.
	 */
	public void displayPlant(Plant plant, Windows previousWindow) {
		// TODO: Keep String or use different param?
		
		setTitle("Plant Info: " + plant.getLatinName());
		
		Text name = new Text(plant.getLatinName());
		name.setFont(new Font(20));
		name.setFill(Color.DARKGREEN);
		
		StackPane namePane = new StackPane();
		namePane.getChildren().add(name);
		namePane.setStyle("-fx-background-color: DAE6F3;");
		
		Text light = new Text("Light: " + plant.getLight());
		Text bloom = new Text("Bloom Season: " + plant.getBloomTime());
		Text description = new Text (plant.getDescription());
		Text info = new Text(bloom + "\n"
				+ light + "\n" + description);
		info.setFont(new Font(18));
		info.setFill(Color.DARKGREEN);
		
		ImageView img = new ImageView();
		plantImage = new Image(getClass().getResourceAsStream("/buttonImages/seed.png"),
				300, 100, true, true);
		img.setImage(plantImage);
		
		borderPane.setLeft(img);
		borderPane.setCenter(info);
		borderPane.setTop(namePane);
		borderPane.setStyle("-fx-background-color: DAE6F3;");
		
		backButton = new Button("Go Back");
		backButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
            	switchToWindow(previousWindow);
            }
        });
		
		borderPane.setBottom(backButton);
		
		this.root = new Group();
		root.getChildren().add(borderPane);
		this.scene = new Scene(this.root);
		
	}
	
}
