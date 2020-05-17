package udel.GardenProject.plotObjects.special;

import java.io.Serializable;

import udel.GardenProject.garden.Model;

/**
 * Bath for birds.
 * 
 * @version 1.0
 * @author Team 0
 * @see {@link udel.GardenProject.plotObjects.PlotObject}
 */
public class PlotBirdBath extends GenericSpecial implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Path to an image of a bird bath for window view.
	 */
	private static String windowBirdBath = "/viewImages/birdBath.png";
	
	/**
	 * Path to an image of a bird bath for plot design.
	 */
	private static String plotBirdBath= "/viewImages/plotBirdbath.png";

	/**
	 * Name of object
	 */
	private static String name = "Bird Bath";
	
	/**
	 * Constructor.
	 * @param x	Horizontal position.
	 * @param y	Vertical position.		
	 */
	public PlotBirdBath(Model model, double x, double y) {
		super(model, x, y, 2.0, 1.5, windowBirdBath, plotBirdBath, name); // a bird bath is about 4 feet tall right? 
	}

}
