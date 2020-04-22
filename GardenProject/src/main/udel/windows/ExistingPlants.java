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

	// TODO: Change to ArrayList?
	private Plant existingPlant[];
	
	private Button backButton;
	private Button nextButton;
	
	/**
	 * Create an ExistingPlants window instance.
	 * 
	 * @param m Model
	 */
	public ExistingPlants(Model m) {
		super(m, "What Plants are in your Garden?");
	}
	
	// TODO: Constructor to pass in plant array?
	
	@Override
	public Scene getScene() {
		// TODO Auto-generated method stub
		return this.scene;
	}
	
	/**
	 * Return a list of all existing plants.
	 * 
	 * @return array of all plants available in GardenProject.
	 */
	public Plant[] getExistingPlants() {
		return this.existingPlant;
	}
	
	/**
	 * Set all the existing plants.
	 * 
	 * @param p	list of plants to use in project.
	 */
	public void setExistingPlants(Plant p) {
		
	}
	
}
