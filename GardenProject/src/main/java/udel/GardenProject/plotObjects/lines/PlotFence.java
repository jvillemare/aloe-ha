package udel.GardenProject.plotObjects.lines;

import java.io.Serializable;

import javafx.scene.image.ImageView;
import udel.GardenProject.garden.Model;

/**
 * TODO: What is?...
 * 
 * @author Team 0
 */
public class PlotFence extends GenericLine implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Path to image of a fence for window view.
	 */
	private static String windowFence = "/viewImages/fence.png";
	
	/**
	 * Path of image of a fence for plot design.
	 */
	private static String plotFence = "/viewImages/plotFence.png";
	
	/**
	 * Constructor.
	 * @param x	Horizontal position of first point of fence.
	 * @param y Vertical position of first point of fence.
	 * @param height	Height in feet of fence.
	 */
	public PlotFence(Model model, double x, double y, double height) {
		super(model, x, y, height, new AdjustableLine(), windowFence, plotFence);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ImageView render() {
		// TODO Auto-generated method stub
		return null;
	}

}
