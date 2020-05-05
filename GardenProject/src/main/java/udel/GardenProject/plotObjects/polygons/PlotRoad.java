package udel.GardenProject.plotObjects.polygons;

import java.io.Serializable;

/**
 * Path way for vehicles.
 * 
 * @author Team 0
 */
public class PlotRoad extends GenericPolygon implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor.
	 * 
	 * @param x	Horizontal position determined by MouseRelease event handler.
	 * @param y	Vertical position determined by MouseRelease event handler.
	 * @param path Path to plot object's image
	 */
	public PlotRoad(double x, double y, String path) {
		// TODO: A forest is always 100.0 feet tall?
		super(x, y, 100.0, new AdjustablePolygon(null, null, 0, 0), path);
		// TODO: Define the background and anchor color, and starting position
		// of this polygon
	}

}
