package udel.GardenProject.plotObjects.special;

import java.io.Serializable;

import udel.GardenProject.plotObjects.PlotObject;

/**
 * TODO: What is?...
 * 
 * @author Team 0
 */
public class PlotBench extends PlotObject implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Path to image of a bench.
	 */
	private static String bench = "/viewImages/bench.png";
	
	/**
	 * TODO: ...
	 * 
	 * @param x			...
	 * @param y			...
	 */
	public PlotBench(double x, double y) {
		super(x, y, 2.0, bench);
	}

}