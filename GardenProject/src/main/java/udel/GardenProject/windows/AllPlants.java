package udel.GardenProject.windows;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
	 * Array of Plants
	 */
	private Plant[] plants;
	
	/**
	 * Button that saves ... TODO what?
	 */
	private Button saveButton;
	
	/**
	 * Button that goes to the next window
	 */
	private Button nextButton;
	
	/**
	 * Button that goes to the previous window
	 */
	private Button backButton;
	
	public AllPlants(Model m) {
		super(m, "Plant Database");
		
		Label label = new Label("So many plants...");
		
		//brings you too outside database
		
		this.root = new Group();
		root.getChildren().add(label);
		this.scene = new Scene(this.root, 200, 200);
	}
	
	@Override
	public Scene getScene() {
		// TODO Auto-generated method stub
		return this.scene;
	}

}
