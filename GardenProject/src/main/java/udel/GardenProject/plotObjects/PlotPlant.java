package udel.GardenProject.plotObjects;

import java.io.Serializable;

import javafx.scene.image.Image;
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
	 * Image of a floor plant.
	 */
	private static Image floor = new Image("/viewImages/floorFlowers.png", 162, 186, true, false);

	/**
	 * Image of an understory plant.
	 */
	private static Image understory = new Image("/viewImages/understory.png", 228, 318, true, false);
	
	/**
	 * Image of a canopy plant.
	 */
	private static Image canopy = new Image("/viewImages/canopy.png", 733, 456, true, false);
	
	/**
	 * Image of an emergent plant.
	 */
	private static Image emergent = new Image("/viewImages/emergent.png", 697, 558, true, false);
	
	/**
	 * Image of a plant. Will be used if no canopy specified.
	 */
	private static Image aPlant = new Image("/viewImages/sunflower.png", 153, 176, true, false);
	
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
		super(x, y, checkIfCanopy(p.getCanopy()), chooseImage(p));
		this.p = p;
	}
	
	/**
	 * Getter.
	 * @return	Plant object.
	 */
	public Plant getPlant() {
		return this.p;
	}
	
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
	public static Image chooseImage(Plant p) {
		if (p.getCanopy() == null) {
			return aPlant;
		}
		switch(p.getCanopy()) {
		case FLOOR:
			return floor;
		case UNDERSTORY:
			return understory;
		case CANOPY:
			return canopy;
		case EMERGENT:
			return emergent;
		default:
			return aPlant;
		}
	}

}
