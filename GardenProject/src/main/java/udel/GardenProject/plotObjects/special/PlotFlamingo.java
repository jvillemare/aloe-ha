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
	 * Path to image of a lawn flamingo
	 */
	private static String flamingo = "/viewImages/flamingo.png";

	public PlotFlamingo(double x, double y, double height) {
		super(x, y, height, flamingo);
		// TODO Auto-generated constructor stub
	}

}
