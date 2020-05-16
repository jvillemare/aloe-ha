package udel.GardenProject.plotObjects.special;

import java.io.Serializable;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import udel.GardenProject.garden.Model;
import udel.GardenProject.plotObjects.PlotObject;

/**
 * Critical object. Wards off the evil spirits.
 * 
 * @version 1.0
 * @author Team 0
 * @see {@link udel.GardenProject.plotObjects.PlotObject}
 */
public class PlotFlamingo extends GenericSpecial implements Serializable {

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
	 * Render Width of the object
	 */
	private static double Width=40.0;
	
	/**
	 * Render Height of the object
	 */
	private static double Height=40.0;
	
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
	public double getRenderWidth() {
		// TODO Auto-generated method stub
		return Width;
	}

	@Override
	public double getRenderHeight() {
		// TODO Auto-generated method stub
		return Height;
	}

}
