package udel.GardenProject.plotObjects.special;

import java.io.Serializable;

import udel.GardenProject.plotObjects.PlotObject;

/**
 * Any other object in a garden.
 * 
 * @author Team 0 
 */
public class PlotOther extends PlotObject implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Path to an image of a raised garden bed for window view.
	 */
	private static String windowPicture = "/viewImages/gardenBed.png";
	
	/**
	 * Path to an image of a raised garden bed for plot design.
	 */
	private static String plotPicture = "/viewImages/plotGardenBed.png";

	/**
	 * TODO: ?...
	 * 
	 * @param x			...
	 * @param y			...
	 * @param height	...
	 */
	public PlotOther(double x, double y, double height, double radius) {
		super(x, y, height, radius, windowPicture, plotPicture);
		// TODO Auto-generated constructor stub
	}

}
