package udel.GardenProject.plotObjects.lines;

import java.io.Serializable;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import udel.GardenProject.garden.Model;

/**
 * Fence that outlines a user's plot.
 * 
 * @version 1.0
 * @author Team 0
 * @see {@link udel.GardenProject.plotObjects.PlotObject}
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
	public Node render() {
		return null;
	}

	@Override
	public double getRenderWidth() {
		// TODO Auto-generated method stub
		return 40.0;
	}

	@Override
	public double getRenderHeight() {
		// TODO Auto-generated method stub
		return 40.0;
	}
	
	@Override
	public String getName() {
		return "Fence"; 
	}

}
