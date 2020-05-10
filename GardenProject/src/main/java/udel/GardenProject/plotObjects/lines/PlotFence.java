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
	 * Path to image of a fence.
	 */
	private static String fence = "/viewImages/fence.png";
	

	public PlotFence(double x, double y, double height) {
		super(x, y, height, new AdjustableLine(), fence);
		// TODO Auto-generated constructor stub
	}

}
