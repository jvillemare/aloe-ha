package udel.GardenProject.plotObjects;

import java.io.Serializable;

import udel.GardenProject.plants.Plant;

/**
 * Plant that can appear on the plot in PlotDesign.
 * 
 * @author Team 0
 */
public class PlotPlant implements PlotObject, Serializable {
	
	private Plant p;
	
	/**
	 * Horizontal.
	 */
	private double x;
	
	/**
	 * Vertical.
	 */
	private double y;
	
	/**
	 * Plant that can appear on in the PlotDesign.
	 * 
	 * @param p	Plant that's appearing on the Plot.
	 * @param x Horizontal position in PlotDesign.
	 * @param y Vertical position in PlotDesign.
	 */
	public PlotPlant(Plant p, double x, double y) {
		this.p = p;
		this.x = x;
		this.y = y;
	}

	@Override
	public double getPlotX() {
		return this.x;
	}

	@Override
	public double getPlotY() {
		return this.y;
	}

}
