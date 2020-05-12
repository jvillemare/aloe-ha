package udel.GardenProject.plotObjects.lines;

import java.io.Serializable;

import javafx.scene.image.ImageView;
import udel.GardenProject.garden.Model;

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
	public PlotPath(Model model, double x, double y) {
		super(model, x, y, 1.0, new AdjustableLine(), windowPath, plotPath);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ImageView render() {
		// TODO Auto-generated method stub
		return null;
	}	

}
