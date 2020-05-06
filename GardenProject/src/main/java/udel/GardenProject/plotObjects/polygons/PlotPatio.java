package udel.GardenProject.plotObjects.polygons;

import java.io.Serializable;

import javafx.scene.image.Image;

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
	 * @param i Plot object's image.
	 */
	public PlotPatio(double x, double y, Image i) {
		// TODO: A patio is always 25.0 feet tall?
		super(x, y, 25.0, new AdjustablePolygon(null, null, 0, 0), i);
		// TODO: Define the background and anchor color, and starting position
		// of this polygon
	}

}
