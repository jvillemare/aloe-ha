package main.udel.windows;

import javafx.scene.control.Button;
import main.udel.plants.Plant;

/**
 * Where a user can specify what plants already exist in their garden.
 * 
 * @author Team 0
 */
public class ExistingPlants extends Window {

	private Plant existingPlant[];
	
	private Button backButton;
	private Button nextButton;
	
	public ExistingPlants() {
		super("What Plants are in your Garden?");
	}
	
	public Plant[] getExistingPlants() {
		return this.existingPlant;
	}
	
	public void setExistingPlants(Plant p) {
		
	}
	
}
