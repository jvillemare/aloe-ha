package udel.GardenProject.plotObjects.lines;

import java.io.Serializable;

import udel.GardenProject.plotObjects.polygons.AdjustablePolygon;

/**
 * TODO: What is?...
 * 
 * @author Team 0
 */
public class PlotFence extends GenericLine implements Serializable {

	private static final long serialVersionUID = 1L;

	public PlotFence(double x, double y, double height) {
		super(x, y, height, new AdjustableLine());
		// TODO Auto-generated constructor stub
	}

}
