package udel.GardenProject.plotObjects.polygons;

import java.io.Serializable;

/**
 * Large Stone
 * 
 * @author Team 0
 */
public class PlotRock extends GenericPolygon implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Path to an image of a rock for window view.
	 */
	private static String windowRock = "/plantImages/rock.png";
	
	/**
	 * Path to an image of a rock for plot design.
	 */
	private static String plotRock;
	
	/**
	 * Constructor.
	 * 
	 * @param x	Horizontal position determined by MouseRelease event handler.
	 * @param y	Vertical position determined by MouseRelease event handler.
	 */
	public PlotRock(double x, double y) {
		// TODO: A forest is always 100.0 feet tall?
		super(x, y, 100.0, new AdjustablePolygon(null, null, 0, 0), windowRock, plotRock);
		// TODO: Define the background and anchor color, and starting position
		// of this polygon
	}

}
