package main.udel.garden;

import main.udel.plants.Plant;
import main.udel.windows.*;

/**
 * Updates the stage: Contains logic and data.
 * 
 * @author Team 0
 */
public class Model {
	
	/**
	 * Width of stage.
	 */
	private int width;
	
	/**
	 * Height of stage.
	 */
	private int height;
	
	/**
	 * All the windows that can be displayed to the user.
	 */
	private Window[] windows;
	
	/**
	 * The current window being displayed to the user.
	 */
	private Window currentWindow;
	
	/**
	 * All the plants.
	 */
	private Plant[] plants;
	
	// TODO: How to handle current Window? With a Window object or int pointing
	// to index of current Window?
	
	public Model(int width, int height) {
		this.width = width;
		this.height = height;
		
		this.windows = new Window[]{
			new AllPlants(),
			new Download(),
			new ExistingPlants(),
			new PlantInfo(),
			new PlotDesign(),
			new Questionnaire(),
			new SeasonView(),
			new Tutorial(),
			new Welcome()
		};
		
		this.currentWindow = this.windows[8];
	}
	
	/**
	 * TODO: Figure out later...
	 */
	public void update() {
		
	}
	
	/**
	 * Gets the current Window that should be displayed to the user.
	 * 
	 * @return current Window.
	 */
	public Window getWindow() {
		return currentWindow;
	}
	
	/**
	 * Change the current that is displayed to another window.
	 * @param w 
	 */
	public void setWindow(Window w) {
		currentWindow = w;
	}
	
	/**
	 * Search the array of plants and return an array of all Plants that match
	 * the query.
	 * 
	 * @param	query	Simple, non-regex query where the query can appear
	 * 					anywhere in the plant latin or common name.
	 * @return	null if no matching results, a Plant array of all matching 
	 * 			results.
	 */
	public Plant[] searchPlants(String query) {
		// TODO: Implement
		return null;
	}

}
