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
	 * TODO: ...
	 * 
	 * @param x			...
	 * @param y			...
	 * @param height	...
	 * @param i		...
	 */
	public PlotBirdBath(double x, double y, double height, Image i) {
		super(x, y, 2.0, i); // a bird bath is about 4 feet tall right? 
	}

}
