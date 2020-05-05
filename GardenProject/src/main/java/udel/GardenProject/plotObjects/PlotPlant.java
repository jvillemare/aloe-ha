package udel.GardenProject.plotObjects;

import java.io.Serializable;

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
		// TODO: A plant is always about a foot wide? Let's say for simplicity.
		// Fix later. Definitely a helper method in plant that reads the 
		// description or calculates or something
		super(x, y, p.getCanopy().getAverageCanopyHeight(), 5.0);
		this.p = p;
	}
	
	/**
	 * Getter.
	 * @return	Plant object.
	 */
	public Plant getPlant() {
		return this.p;
	}

}
