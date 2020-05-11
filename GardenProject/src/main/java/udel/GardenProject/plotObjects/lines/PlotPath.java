package udel.GardenProject.plotObjects.lines;

import java.io.Serializable;

import javafx.scene.image.Image;

/**
 * TODO: What is this? If polygon, move to polygons package and make it match
 * the other polygons.
 * 
 * @author Team 0
 */
public class PlotPath extends GenericLine implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Image of a path for window view.
	 */
	private static String windowPath = "/viewImages/path.png";
	
	private static String plotPath = "/viewImages/plotPath.png";

	/**
	 * Constructor.
	 * 
	 * @param x			Horizontal position of first point in plot design.
	 * @param y			Vertical position of first point in plot design.
	 */

	public PlotPath(double x, double y) {
		super(x, y, 1.0, new AdjustableLine(), windowPath, plotPath);
		// TODO Auto-generated constructor stub
	}	

}
