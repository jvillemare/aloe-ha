package udel.GardenProject.plotObjects.lines;

import java.io.Serializable;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
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
	
	/**
	 * Image of a path for plot design.
	 */
	private static String plotPath = "/viewImages/plotPath.png";
	
	/**
	 * Render Width of the object
	 */
	private static double width=40.0;
	
	/**
	 * Render Height of the object
	 */
	private static double height=40.0;
	
	/**
	 * Constructor.
	 * 
	 * @param x			Horizontal position of first point in plot design.
	 * @param y			Vertical position of first point in plot design.
	 */
	public PlotPath(Model model, double x, double y) {
		super(model, x, y, 1.0, new AdjustableLine(Color.RED, height, width),
				windowPath, plotPath);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Node render() {
		Group n=new Group();
		n.getChildren().addAll(this.getAdjustablePolygon().getAnchors());
		n.getChildren().add(this.getAdjustablePolygon().getPolygon());
		return n;
	}

	@Override
	public double getRenderWidth() {
		// TODO Auto-generated method stub
		return width;
	}

	@Override
	public double getRenderHeight() {
		// TODO Auto-generated method stub
		return height;
	}

}
