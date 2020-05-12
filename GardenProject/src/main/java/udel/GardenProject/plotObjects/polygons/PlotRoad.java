package udel.GardenProject.plotObjects.polygons;

import java.io.Serializable;

import javafx.scene.image.ImageView;
import udel.GardenProject.garden.Model;

/**
 * Path way for vehicles.
 * 
 * @author Team 0
 */
public class PlotRoad extends GenericPolygon implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Path to an image of a road for window view.
	 */
	private static String windowRoad = "/viewImages/road.png";
	
	/**
	 * Path to an image of a road for plot design.
	 */
	private static String plotRoad = "/viewImages/plotRoad.png";
	
	/**
	 * Constructor.
	 * 
	 * @param x	Horizontal position determined by MouseRelease event handler.
	 * @param y	Vertical position determined by MouseRelease event handler.
	 * @param i Plot object's image
	 */
	public PlotRoad(Model model, double x, double y) {
		// TODO: A forest is always 100.0 feet tall?
		super(model, x, y, 100.0, new AdjustablePolygon(null, null, 0, 0), windowRoad, plotRoad);
		// TODO: Define the background and anchor color, and starting position
		// of this polygon
	}

	@Override
	public ImageView render() {
		// TODO Auto-generated method stub
		return null;
	}

}
