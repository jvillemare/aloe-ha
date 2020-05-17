package udel.GardenProject.plotObjects.special;

import java.io.Serializable;

import udel.GardenProject.garden.Model;

/**
 * Critical object. Wards off the evil spirits.
 * 
 * @version 1.0
 * @author Team 0
 * @see {@link udel.GardenProject.plotObjects.PlotObject}
 */
public class PlotFlamingo extends GenericSpecial implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Path to an image of a lawn flamingo for window view.
	 */
	private static String windowFlamingo = "/viewImages/flamingo.png";
	
	/**
	 * Path to an image of a lawn flamingo for plot design.
	 */
	private static String plotFlamingo = "/viewImages/plotFlamingo.png";

	/**
	 * Name of object
	 */
	private static String name = "Flamingo";
	
	/**
	 * Constructor.
	 * @param x	Horizontal position.
	 * @param y Vertical position.
	 */
	public PlotFlamingo(Model model, double x, double y) {
		super(model, x, y, 2.0, 1.0, windowFlamingo, plotFlamingo, name);
		// TODO Auto-generated constructor stub
	}
	
}
