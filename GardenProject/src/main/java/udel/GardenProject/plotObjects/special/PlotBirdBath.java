package udel.GardenProject.plotObjects.special;

import java.io.Serializable;

import javafx.scene.image.ImageView;
import udel.GardenProject.garden.Model;
import udel.GardenProject.plotObjects.PlotObject;

/**
 * Bath for birds.
 * 
 * @author Team 0
 */
public class PlotBirdBath extends PlotObject implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Path to an image of a bird bath for window view.
	 */
	private static String windowBirdBath = "/viewImages/birdBath.png";
	
	/**
	 * Path to an image of a bird bath for plot design.
	 */
	private static String plotBirdBath= "/viewImages/plotBirdbath.png";
	
	/**
	 * Constructor.
	 * @param x	Horizontal position.
	 * @param y	Vertical position.		
	 */
	public PlotBirdBath(Model model, double x, double y) {
		super(model, x, y, 2.0, 1.5, windowBirdBath, plotBirdBath); // a bird bath is about 4 feet tall right? 
	}
	
	@Override
	public ImageView render() {
		// TODO Auto-generated method stub
		return null;
	}

}
