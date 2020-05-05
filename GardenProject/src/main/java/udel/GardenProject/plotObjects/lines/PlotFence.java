package udel.GardenProject.plotObjects.lines;

import java.io.Serializable;

/**
 * TODO: What is?...
 * 
 * @author Team 0
 */
public class PlotFence extends GenericLine implements Serializable {

	private static final long serialVersionUID = 1L;

	public PlotFence(double x, double y, double height, String path) {
		super(x, y, height, new AdjustableLine(), path);
		// TODO Auto-generated constructor stub
	}

}
