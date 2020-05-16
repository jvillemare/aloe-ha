package udel.GardenProject.plotObjects.polygons;

import java.io.Serializable;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import udel.GardenProject.garden.Model;


/**
 * High density forest.
 * 
 * @version 1.0
 * @author Team 0
 * @see {@link udel.GardenProject.plotObjects.PlotObject}
 */
public class PlotForest extends GenericPolygon implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Path to an image of a forest for window view.
	 */
	private static String windowForest = "/viewImages/forest.png";

	/**
	 * Path to an image of a forest for plot design.
	 */
	private static String plotForest = "/viewImages/plotForest.png";
	
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
	 * @param x	Horizontal position determined by MouseRelease event handler.
	 * @param y	Vertical position determined by MouseRelease event handler.
	 */
	public PlotForest(Model model, double x, double y) {
		// TODO: A forest is always 100.0 feet tall?
		super(model, x, y, 100.0, new AdjustablePolygon(null, null, 0, 0, height, width), 
				windowForest, plotForest);
		// TODO: Define the background and anchor color, and starting position
		// of this polygon
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
