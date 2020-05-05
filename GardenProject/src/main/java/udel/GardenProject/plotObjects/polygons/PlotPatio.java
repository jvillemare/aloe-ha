package udel.GardenProject.plotObjects.polygons;

import java.io.Serializable;

/**
 * TODO: What is?...
 * 
 * @author Team 0
 */
public class PlotPatio extends GenericPolygon implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 * 
	 * @param x	Horizontal position determined by MouseRelease event handler.
	 * @param y	Vertical position determined by MouseRelease event handler.
	 * @param path Path to plot object's image.
	 */
	public PlotPatio(double x, double y, String path) {
		// TODO: A patio is always 25.0 feet tall?
		super(x, y, 25.0, new AdjustablePolygon(null, null, 0, 0), path);
		// TODO: Define the background and anchor color, and starting position
		// of this polygon
	}

}
