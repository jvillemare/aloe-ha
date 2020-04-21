package main.udel.windows;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import main.udel.garden.Model;
import main.udel.plants.Plant;

/**
 * Where a user can specify what plants already exist in their garden.
 * 
 * @author Team 0
 */
public class ExistingPlants extends Window {
	
	private Scene scene;

	private Plant existingPlant[];
	
	private Button backButton;
	private Button nextButton;
	
	public ExistingPlants(Model m) {
		super(m, "What Plants are in your Garden?");
	}
	
	@Override
	public Scene getScene() {
		// TODO Auto-generated method stub
		return this.scene;
	}
	
	public Plant[] getExistingPlants() {
		return this.existingPlant;
	}
	
	public void setExistingPlants(Plant p) {
		
	}
	
}
