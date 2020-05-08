package udel.GardenProject.plotObjects.special;

import java.io.Serializable;

import javafx.scene.image.Image;
import udel.GardenProject.plotObjects.PlotObject;

/**
 * TODO: What is?...
 * 
 * @author Team 0
 */
public class PlotBench extends PlotObject implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Image of a bench.
	 */
	private static Image bench = new Image("/viewImages/bench.png", 222, 134, true, false);
	
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