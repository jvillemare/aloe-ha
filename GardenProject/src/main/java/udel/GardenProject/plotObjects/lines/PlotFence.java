package udel.GardenProject.plotObjects.lines;

import java.io.Serializable;

import javafx.scene.image.Image;

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
	

	public PlotFence(double x, double y, double height) {
		super(x, y, height, new AdjustableLine(), windowFence, plotFence);
		// TODO Auto-generated constructor stub
	}

}
