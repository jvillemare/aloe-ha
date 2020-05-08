package udel.GardenProject.plotObjects.special;

import java.io.Serializable;

import javafx.scene.image.Image;
import udel.GardenProject.plotObjects.PlotObject;

/**
 * Critical object. Wards off the evil spirits.
 * 
 * @author Team 0
 */
public class PlotFlamingo extends PlotObject implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Image of a lawn flamingo
	 */
	private static Image flamingo = new Image("/viewImages/flamingo.png", 226, 137, true, false);

	public PlotFlamingo(double x, double y, double height) {
		super(x, y, height, flamingo);
		// TODO Auto-generated constructor stub
	}

}
