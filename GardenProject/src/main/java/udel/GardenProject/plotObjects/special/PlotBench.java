package udel.GardenProject.plotObjects.special;

import java.io.Serializable;

import udel.GardenProject.plotObjects.PlotObject;

/**
 * Place to sit.
 * 
 * @author Team 0
 */
public class PlotBench extends PlotObject implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Path to an image of a bench for window view.
	 */
	private static String windowBench = "/viewImages/bench.png";
	
	/**
	 * Path to an image of a bench for plot design.
	 */
	private static String plotBench = "/viewImages/plotBench.png";
	
	/**
	 * TODO: ...
	 * 
	 * @param x			...
	 * @param y			...
	 */
	public PlotBench(double x, double y) {
		super(x, y, 2.0, windowBench, plotBench);
	}

}