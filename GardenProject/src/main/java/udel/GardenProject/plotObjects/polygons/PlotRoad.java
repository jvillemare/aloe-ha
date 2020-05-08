package udel.GardenProject.plotObjects.polygons;

import java.io.Serializable;

import javafx.scene.image.Image;

/**
 * Path way for vehicles.
 * 
 * @author Team 0
 */
public class PlotRoad extends GenericPolygon implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Image of a road.
	 */
	private static Image road = new Image("/viewImages/road.png", 656, 958, true, false);
	
	/**
	 * Constructor.
	 * 
	 * @param x	Horizontal position determined by MouseRelease event handler.
	 * @param y	Vertical position determined by MouseRelease event handler.
	 * @param i Plot object's image
	 */
	public PlotRoad(double x, double y) {
		// TODO: A forest is always 100.0 feet tall?
		super(x, y, 100.0, new AdjustablePolygon(null, null, 0, 0), road);
		// TODO: Define the background and anchor color, and starting position
		// of this polygon
	}

}
