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
	 * Path to an image of patio furniture for window view.
	 */
	private static String windowPatio = "/viewImages/patio.png";
	
	/**
	 * Path to an image of patio furniture for plot design.
	 */
	private static String plotPatio = "/viewImages/plotPatio.png";

	/**
	 * Constructor.
	 * 
	 * @param x	Horizontal position determined by MouseRelease event handler.
	 * @param y	Vertical position determined by MouseRelease event handler.
	 */
	public PlotPatio(double x, double y) {
		// TODO: A patio is always 25.0 feet tall?
		super(x, y, 25.0, new AdjustablePolygon(null, null, 0, 0), windowPatio, plotPatio);
		// TODO: Define the background and anchor color, and starting position
		// of this polygon
	}

}
