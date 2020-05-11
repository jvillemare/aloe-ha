package udel.GardenProject.plotObjects.special;

import java.io.Serializable;

import udel.GardenProject.plotObjects.PlotObject;

/**
 * Critical object. Wards off the evil spirits.
 * 
 * @author Team 0
 */
public class PlotFlamingo extends PlotObject implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Path to an image of a lawn flamingo for window view.
	 */
	private static String windowFlamingo = "/viewImages/flamingo.png";
	
	/**
	 * Path to an image of a lawn flamingo for plot design.
	 */
	private static String plotFlamingo;

	public PlotFlamingo(double x, double y, double height) {
		super(x, y, height, windowFlamingo, plotFlamingo);
		// TODO Auto-generated constructor stub
	}

}
