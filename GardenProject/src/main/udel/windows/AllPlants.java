package main.udel.windows;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import main.udel.plants.Plant;

/**
 * A searchable database of all the plants a user can add to the their plot.
 * They can also add plants that were not initially in their selection.
 * 
 * @author Team 0
 */
public class AllPlants extends Window {
	
	private Scene scene;
	
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
	
	public AllPlants() {
		super("Plant Database");
	}
	
	@Override
	public Scene getScene() {
		// TODO Auto-generated method stub
		return this.scene;
	}

}
