package udel.GardenProject.plotObjects.polygons;

import java.io.Serializable;

/**
 * Playground.
 * 
 * @author Team 0
 */
public class PlotPlayground extends GenericPolygon implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public PlotPlayground(double x, double y, String path) {
		// TODO: A forest is always 100.0 feet tall?
		super(x, y, 100.0, new AdjustablePolygon(null, null, 0, 0), path);
		// TODO Auto-generated constructor stub
	}
	
}
