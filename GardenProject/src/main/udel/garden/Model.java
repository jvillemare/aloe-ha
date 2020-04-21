package main.udel.garden;

import main.udel.enums.Windows;
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
		
		windows = new Window[Windows.values().length];
		
		windows[Windows.AllPlants.ordinal()] = new AllPlants();
		windows[Windows.Download.ordinal()] = new Download();
		windows[Windows.PlantInfo.ordinal()] = new PlantInfo();
		windows[Windows.PlotDesign.ordinal()] = new PlotDesign();
		windows[Windows.Questionnaire.ordinal()] = new Questionnaire();
		windows[Windows.SeasonView.ordinal()] = new SeasonView();
		windows[Windows.Tutorial.ordinal()] = new Tutorial();
		windows[Windows.Welcome.ordinal()] = new Welcome(this);
		
		this.currentWindow = this.windows[Windows.Welcome.ordinal()];
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
	
	public void setWindow(Windows w) {
		currentWindow = windows[w.ordinal()];
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
