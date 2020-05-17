package udel.GardenProject.plotObjects.special;

import java.io.Serializable;

import udel.GardenProject.garden.Model;

/**
 * Another Critical object. Wards off the evil spirits.
 * 
 * @version 1.0
 * @author Team 0
 * @see {@link udel.GardenProject.plotObjects.PlotObject}
 */
public class PlotGnome extends GenericSpecial implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Path to an image of a garden gnome for window view.
	 */
	private static String windowGnome = "/viewImages/gnome.png";
	
	/**
	 * Path to an image of a garden gnome for plot design.
	 */
	private static String plotGnome = "/viewImages/plotGnome.png";
	
	/**
	 * Name of object
	 */
	private static String name = "Gnome";

	/**
	 * Constructor.
	 * @param x	Horizontal position.
	 * @param y Vertical position.
	 */
	public PlotGnome(Model model, double x, double y) {
		super(model, x, y, 1, 1.0, windowGnome, plotGnome, name);
		// TODO Auto-generated constructor stub
	}
	
}