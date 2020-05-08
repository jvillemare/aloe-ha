package udel.GardenProject.plotObjects.polygons;

import java.io.Serializable;

import javafx.scene.image.Image;

/**
 * Playground.
 * 
 * @author Team 0
 */
public class PlotPlayground extends GenericPolygon implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Image of a playground.
	 */
	private static Image playground = new Image("/plantImages/playground.png", 380, 440, true, false);
	
	public PlotPlayground(double x, double y) {
		// TODO: A forest is always 100.0 feet tall?
		super(x, y, 100.0, new AdjustablePolygon(null, null, 0, 0), playground);
		// TODO Auto-generated constructor stub
	}
	
}
