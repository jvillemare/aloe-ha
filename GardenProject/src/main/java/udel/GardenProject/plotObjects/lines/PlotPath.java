package udel.GardenProject.plotObjects.lines;

import java.io.Serializable;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import udel.GardenProject.garden.Model;

/**
 * A fence that typically goes around a user's plot.
 * 
 * @version 1.0
 * @author Team 0
 * @see {@link udel.GardenProject.plotObjects.PlotObject}
 */
public class PlotPath extends GenericLine implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Image of a path for window view.
	 */
	private static String windowPath = "/viewImages/path.png";
	
	private static String plotPath = "/viewImages/plotPath.png";
	
	private static String name = "Path";

	/**
	 * Constructor.
	 * 
	 * @param x			Horizontal position of first point in plot design.
	 * @param y			Vertical position of first point in plot design.
	 */
	public PlotPath(Model model, double x, double y) {
		super(model, x, y, 1.0, new AdjustableLine(), windowPath, plotPath, name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Node render() {
		return render();
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

}
