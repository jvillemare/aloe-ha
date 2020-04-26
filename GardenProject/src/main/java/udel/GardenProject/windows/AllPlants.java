package udel.GardenProject.windows;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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
		
		border.setTop(txt);
		border.setStyle("-fx-background-color: DAE6F3;");
		border.setLeft(searchBox);
		
		
		
		//center will be all plants
		
		
		
		//brings you too outside database
		
		this.root = new Group();
		root.getChildren().add(border);
		this.scene = new Scene(this.root, 200, 200);
	}
	
	@Override
	public Scene getScene() {
		// TODO Auto-generated method stub
		return this.scene;
	}

}
