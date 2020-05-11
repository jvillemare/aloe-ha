package udel.GardenProject.plotObjects;

import java.io.Serializable;

import udel.GardenProject.enums.Canopy;
import udel.GardenProject.plants.Plant;

/**
 * Plant that can appear on the plot in PlotDesign.<br><br>
 * 
 * Special object that stays in the root of the 
 * <code>udel.GardenProject.plotObjects</code> package.
 * 
 * @author Team 0
 */
public class PlotPlant extends PlotObject implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * The plant this PlotPlant is representing.
	 */
	private Plant p;
	
	/**
	 * Plant that can appear on in the PlotDesign.
	 * 
	 * @param p	Plant that's appearing on the Plot.
	 * @param x Horizontal position in PlotDesign.
	 * @param y Vertical position in PlotDesign.
	 */
	public PlotPlant(Plant p, double x, double y) {
		super(x, y, checkIfCanopy(p.getCanopy()), chooseWindowImage(p), choosePlotImage(p));
		this.p = p;
	}
	
	/**
	 * Getter.
	 * @return	Plant object.
	 */
	public Plant getPlant() {
		return this.p;
	}
	
	/**
	 * Checks to see if the canopy is null.
	 * @param c Canopy of a plant.
	 * @return -1 if the canopy is null. Otherwise it will return the 
	 * getAverageCanopyHeight of the given canopy.
	 */
	public static double checkIfCanopy(Canopy c) {
		if (c == null) {
			return -1;
		}
		else {
			return c.getAverageCanopyHeight();
		}
	}
	
	
	/**
	 * Determines the image to be used for the window view.
	 * @param p The plant object.
	 * @return The correct image based on canopy level.
	 */
	public static String chooseWindowImage(Plant p) {
		if (p.getCanopy() == null) {
			return "/viewImages/sunflower.png";
		}
		switch(p.getCanopy()) {
		case FLOOR:
			return "/viewImages/floor.png";
		case UNDERSTORY:
			return "/viewImages/understory.png";
		case CANOPY:
			return "/viewImages/canopy.png";
		case EMERGENT:
			return "/viewImages/emergent.png";
		default:
			return "/viewImages/sunflower.png";
		}
	}
	
	/**
	 * Determines the image to be used for the plot design.
	 * @param p The plant object.
	 * @return The correct image based on canopy level.
	 */
	public static String choosePlotImage(Plant p) {
		if (p.getCanopy() == null) {
			return "/viewImages/sunflower.png";
		}
		switch(p.getCanopy()) {
		case FLOOR:
			return "/viewImages/floor.png";
		case UNDERSTORY:
			return "/viewImages/understory.png";
		case CANOPY:
			return "/viewImages/canopy.png";
		case EMERGENT:
			return "/viewImages/emergent.png";
		default:
			return "/viewImages/sunflower.png";
		}
	}

}
