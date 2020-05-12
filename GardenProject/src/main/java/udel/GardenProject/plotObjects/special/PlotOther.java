package udel.GardenProject.plotObjects.special;

import java.io.Serializable;

import javafx.scene.image.ImageView;
import udel.GardenProject.garden.Model;
import udel.GardenProject.plotObjects.PlotObject;

/**
 * Any other object in a garden.
 * 
 * @author Team 0 
 */
public class PlotOther extends PlotObject implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Path to an image of a raised garden bed for window view.
	 */
	private static String windowPicture = "/viewImages/gardenBed.png";
	
	/**
	 * Path to an image of a raised garden bed for plot design.
	 */
	private static String plotPicture = "/viewImages/plotGardenBed.png";

	/**
	 * Constructor.
	 * @param x	Horizontal position.
	 * @param y	Vertical position.
	 */
	public PlotOther(Model model, double x, double y) {
		super(model, x, y, 3.0, 3.0, windowPicture, plotPicture);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ImageView render() {
		// TODO Auto-generated method stub
		return null;
	}

}
