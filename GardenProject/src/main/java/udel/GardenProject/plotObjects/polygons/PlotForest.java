package udel.GardenProject.plotObjects.polygons;

import java.io.Serializable;

import javafx.scene.image.ImageView;
import udel.GardenProject.garden.Model;


/**
 * TODO: What is?...
 * 
 * @author Team 0
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
	 * Constructor.
	 * 
	 * @param x	Horizontal position determined by MouseRelease event handler.
	 * @param y	Vertical position determined by MouseRelease event handler.
	 */
	public PlotForest(Model model, double x, double y) {
		// TODO: A forest is always 100.0 feet tall?
		super(model, x, y, 100.0, new AdjustablePolygon(null, null, 0, 0), windowForest, plotForest);
		// TODO: Define the background and anchor color, and starting position
		// of this polygon
	}

	@Override
	public ImageView render() {
		// TODO Auto-generated method stub
		return null;
	}

}
