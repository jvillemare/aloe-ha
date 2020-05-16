package udel.GardenProject.plotObjects.special;

import java.io.Serializable;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import udel.GardenProject.garden.Model;
import udel.GardenProject.plotObjects.PlotObject;

/**
 * Another Critical object. Wards off the evil spirits.
 * 
 * @version 1.0
 * @author Team 0
 * @see {@link udel.GardenProject.plotObjects.PlotObject}
 */
public class PlotGnome extends GenericSpecial implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Path to an image of a garden gnome for window view.
	 */
	private static String windowGnome = "/viewImages/gnome.png";
	
	/**
	 * Path to an image of a garden gnome for plot design.
	 */
	private static String plotGnome = "/viewImages/plotGnome.png";
	
	/**
	 * Constructor.
	 * @param x	Horizontal position.
	 * @param y Vertical position.
	 */
	public PlotGnome(Model model, double x, double y) {
		super(model, x, y, 1, 1.0, windowGnome, plotGnome);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double getRenderWidth() {
		// TODO Auto-generated method stub
		return 40.0;
	}

	@Override
	public double getRenderHeight() {
		// TODO Auto-generated method stub
		return 40.0;
	}
	
	@Override
	public String getName() {
		return "Gnome"; 
	}

}