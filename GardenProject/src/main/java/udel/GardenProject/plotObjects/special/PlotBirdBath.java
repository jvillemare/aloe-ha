package udel.GardenProject.plotObjects.special;

import java.io.Serializable;

import javafx.scene.image.Image;
import udel.GardenProject.plotObjects.PlotObject;

/**
 * TODO: What is?...
 * 
 * @author Team 0
 */
public class PlotBirdBath extends PlotObject implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Image of a bird bath.
	 */
	private static Image birdBath = new Image("/plantImages/birdBath.png", 252, 152, true, false);
	
	/**
	 * TODO: ...
	 * 
	 * @param x			...
	 * @param y			...
	 */
	public PlotBirdBath(double x, double y) {
		super(x, y, 2.0, birdBath); // a bird bath is about 4 feet tall right? 
	}

}
