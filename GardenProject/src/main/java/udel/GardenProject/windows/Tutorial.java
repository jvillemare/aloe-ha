package udel.GardenProject.windows;

import javafx.scene.Scene;
import udel.GardenProject.garden.Model;

/**
 * Presently: Basic text description run down of all the features of the 
 * program and how to use them.
 * 
 * @author Team 0
 */
public class Tutorial extends Window {
	
	private Scene scene;

	/**
	 * Create a Tutorial window instance.
	 * 
	 * @param m Model
	 */
	public Tutorial(Model m) {
		super(m, "Tutorial Window");
	}

	@Override
	public Scene getScene() {
		// TODO Auto-generated method stub
		return this.scene;
	}
	
}
