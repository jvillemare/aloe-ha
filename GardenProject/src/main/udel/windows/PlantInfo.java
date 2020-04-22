package main.udel.windows;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import main.udel.garden.Model;
import main.udel.plants.Plant;

/**
 * To display all the information of a Plant to the user.
 * 
 * @author Team 0
 */
public class PlantInfo extends Window {
	
	private Scene scene;
	
	private Plant plantArray[];
	private Button backButton;

	public PlantInfo(Model m) {
		super(m, "Plant Info: Pine Trees"); // this will have to change to the name of the plant they've clicked on
		
		
		
		// pop up window
		
		
	}
	
	@Override
	public Scene getScene() {
		// TODO Auto-generated method stub
		return this.scene;
	}
	
}
