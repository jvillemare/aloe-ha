package udel.GardenProject.garden;

import java.io.Serializable;
import java.util.ArrayList;

import udel.GardenProject.plants.plotObjects.PlotObject;

/**
 * Holds all the critical user-state information so that a
 * <code>.gardenproject</code> file can be easily loaded in and out without much
 * delay.
 * 
 * Isolated so that it can be saved and loaded easily.
 * 
 * All attributes should be adequately javadoc'd for future reference.
 * 
 * @author Team 0
 */
public class Session implements Serializable {

	/**
	 * Prevent newer version of GardenProject being loaded by older versions of
	 * save files to prevent errors.
	 * 
	 * Should only update this if absolutely necessary.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Name of the user's plot so that they can customize them.
	 */
	private String plotName = "";
	
	/**
	 * Objects to be displayed on PlotDesign.
	 */
	private ArrayList<PlotObject> plot = new ArrayList<PlotObject>();

}
