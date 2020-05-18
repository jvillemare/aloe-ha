package udel.GardenProject.enums;

/**
 * All the windows that can be displayed to the user. Does not specify any name
 * or other attributes, as all of of a windows attributes (titles, scenes, etc.)
 * may change during runtime.
 * 
 * @version 1.0
 * @author Team 0
 */
public enum Windows {
	
	/**
	 * Plant database where plants can be searched through.
	 */
	AllPlants, 
	
	/**
	 * Sketch the hard structure of the plot (buildings, roads, forests, etc.)
	 */
	BluePrint, 
	
	/**
	 * Where a user can save their plot as an image or 
	 * <code>.gardenproject</code> file.
	 */
	Download, 
	
	/**
	 * Where a user can specify existing plants in their plot.
	 */
	ExistingPlants, 
	
	/**
	 * Where a user can view information about a specific plant.
	 */
	PlantInfo, 
	
	/**
	 * Where a user can design their plot.
	 */
	PlotDesign, 
	
	/**
	 * Where a user can view where they've saved past garden projects.
	 */
	PreviousSaves, 
	
	/**
	 * Where a user can specify details about their plot.
	 */
	Questionnaire, 
	
	/**
	 * Where a user can preview their plot in different seasons and the future.
	 */
	SeasonView, 
	
	/**
	 * Where a user can learn about how to use the application.
	 */
	Tutorial, 
	
	/**
	 * Opening welcome screen.
	 */
	Welcome, 
		
	/**
	 * Where a user can select plants to add to their plot based on their
	 * Questionnaire answers.
	 */
	PlantSelection;

}
