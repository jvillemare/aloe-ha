package udel.GardenProject.plotObjects.lines;

import java.io.Serializable;

import javafx.scene.image.Image;

/**
 * TODO: What is?...
 * 
 * @author Team 0
 */
public class PlotFence extends GenericLine implements Serializable {

	private static final long serialVersionUID = 1L;

	public PlotFence(double x, double y, double height, Image i) {
		super(x, y, height, new AdjustableLine(), i);
		// TODO Auto-generated constructor stub
	}

}
