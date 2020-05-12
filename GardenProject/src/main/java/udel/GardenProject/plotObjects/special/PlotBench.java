package udel.GardenProject.plotObjects.special;

import java.io.Serializable;

import javafx.scene.image.ImageView;
import udel.GardenProject.garden.Model;
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
	 * Constructor.
	 * @param x	Horizontal position.
	 * @param y Vertical position.
	 */
	public PlotBench(Model model, double x, double y) {
		super(model, x, y, 2.0, 5.0, windowBench, plotBench);
	}

	@Override
	public ImageView render() {
		// TODO Auto-generated method stub
		return null;
	}

}