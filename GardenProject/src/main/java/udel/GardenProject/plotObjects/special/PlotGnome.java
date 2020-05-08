package udel.GardenProject.plotObjects.special;

import java.io.Serializable;

import javafx.scene.image.Image;
import udel.GardenProject.plotObjects.PlotObject;

/**
 * Critical object. Wards off the evil spirits.
 * 
 * @author Team 0
 */
public class PlotGnome extends PlotObject implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Image of a garden gnome.
	 */
	private static Image gnome = new Image("/plantImages/gnome.png", 119, 137, true, false);

	public PlotGnome(double x, double y) {
		super(x, y, 1, gnome);
		// TODO Auto-generated constructor stub
	}

}