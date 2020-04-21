package main.udel.windows;

import javafx.scene.Scene;
import javafx.scene.control.Button;
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

	public PlantInfo() {
		super("Plant Info: Pine Trees");
	}
	
	@Override
	public Scene getScene() {
		// TODO Auto-generated method stub
		return this.scene;
	}
	
}
