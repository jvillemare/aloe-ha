package udel.GardenProject.plotObjects.special;

import java.io.Serializable;

import javafx.scene.image.Image;
import udel.GardenProject.plotObjects.PlotObject;

/**
 * TODO: What is this?...
 * 
 * @author Team 0 
 */
public class PlotOther extends PlotObject implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Image of a raised garden bed.
	 */
	private static Image picture = new Image("/viewImages/gardenBed.png", 315, 190, true, false);

	/**
	 * TODO: ?...
	 * 
	 * @param x			...
	 * @param y			...
	 * @param height	...
	 */
	public PlotOther(double x, double y, double height) {
		super(x, y, height, picture);
		// TODO Auto-generated constructor stub
	}

}
