package udel.GardenProject.plotObjects.special;

import java.io.Serializable;

import javafx.scene.image.ImageView;
import udel.GardenProject.garden.Model;
import udel.GardenProject.plotObjects.PlotObject;

/**
 * Critical object. Wards off the evil spirits.
 * 
 * @author Team 0
 */
public class PlotFlamingo extends PlotObject implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Path to an image of a lawn flamingo for window view.
	 */
	private static String windowFlamingo = "/viewImages/flamingo.png";
	
	/**
	 * Path to an image of a lawn flamingo for plot design.
	 */
	private static String plotFlamingo = "/viewImages/plotFlamingo.png";

	/**
	 * Constructor.
	 * @param x	Horizontal position.
	 * @param y Vertical position.
	 */
	public PlotFlamingo(Model model, double x, double y) {
		super(model, x, y, 2.0, 1.0, windowFlamingo, plotFlamingo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ImageView render() {
		// TODO Auto-generated method stub
		return null;
	}

}
