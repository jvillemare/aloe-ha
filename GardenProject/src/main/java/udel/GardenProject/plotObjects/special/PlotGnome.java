package udel.GardenProject.plotObjects.special;

import java.io.Serializable;

import udel.GardenProject.plotObjects.PlotObject;

/**
 * Critical object. Wards off the evil spirits.
 * 
 * @author Team 0
 */
public class PlotGnome extends PlotObject implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Path to image of a garden gnome.
	 */
	private static String gnome = "/viewImages/gnome.png";

	public PlotGnome(double x, double y) {
		super(x, y, 1, gnome);
		// TODO Auto-generated constructor stub
	}

}