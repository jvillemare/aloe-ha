package udel.GardenProject.plotObjects.polygons;

import java.io.Serializable;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import udel.GardenProject.garden.Model;

/**
 * Chlorine-and-water filled of water.
 * 
 * @version 1.0
 * @author Team 0
 * @see {@link udel.GardenProject.plotObjects.PlotObject}
 */
public class PlotPool extends GenericPolygon implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Path of an image of a pool for window view.
	 */
	private static String windowPool = "/viewImages/pool.png";
	
	/**
	 * Path of an image of a pool for plot design.
	 */
	private static String plotPool = "/viewImages/plotPool.png";
	
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
	 * @param i Plot object's image.
	 */
	public PlotPool(Model model, double x, double y) {
		// TODO: A forest is always 100.0 feet tall?
		super(model, x, y, 100.0, new AdjustablePolygon(null, null, 0, 0, height, width),
				windowPool, plotPool);
		// TODO Auto-generated constructor stub
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
