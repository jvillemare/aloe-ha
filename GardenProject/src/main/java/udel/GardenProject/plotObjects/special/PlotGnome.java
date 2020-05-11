package udel.GardenProject.plotObjects.special;

import java.io.Serializable;

import udel.GardenProject.plotObjects.PlotObject;

/**
 * Another Critical object. Wards off the evil spirits.
 * 
 * @author Team 0
 */
public class PlotGnome extends PlotObject implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Path to an image of a garden gnome for window view.
	 */
	private static String windowGnome = "/viewImages/gnome.png";
	
	/**
	 * Path to an image of a garden gnome for plot design.
	 */
	private static String plotGnome = "/viewImages/plotGnome.png";
	
	public PlotGnome(double x, double y) {
		super(x, y, 1, windowGnome, plotGnome);
		// TODO Auto-generated constructor stub
	}

}