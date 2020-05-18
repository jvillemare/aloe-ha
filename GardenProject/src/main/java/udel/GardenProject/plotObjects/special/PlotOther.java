package udel.GardenProject.plotObjects.special;

import java.io.Serializable;

import udel.GardenProject.garden.Model;

/**
 * Any other object in a garden.
 * 
 * @version 1.0
 * @author Team 0 
 * @see {@link udel.GardenProject.plotObjects.PlotObject}
 */
public class PlotOther extends GenericSpecial implements Serializable {

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
	 * Name of object
	 */
	private static String name = "Other";
	
	/**
	 * Constructor.
	 * @param x	Horizontal position.
	 * @param y	Vertical position.
	 */
	public PlotOther(Model model, double x, double y) {
		super(model, x, y, 3.0, 3.0, windowPicture, plotPicture, name);
		// TODO Auto-generated constructor stub
	}

}
