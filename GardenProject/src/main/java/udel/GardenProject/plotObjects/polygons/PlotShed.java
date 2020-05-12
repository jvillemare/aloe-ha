package udel.GardenProject.plotObjects.polygons;

import java.io.Serializable;

import javafx.scene.image.ImageView;
import udel.GardenProject.garden.Model;

/**
 * Outdoor storage structure.
 * 
 * @author Team 0
 */
public class PlotShed extends GenericPolygon implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Path to an image of a shed for window view.
	 */
	private static String windowShed  = "/viewImages/shed.png";

	/**
	 * Path to an image of a shed for plot design.
	 */
	private static String plotShed = "/viewImages/plotShed.png";
	
	/**
	 * Constructor.
	 * 
	 * @param x	Horizontal position determined by MouseRelease event handler.
	 * @param y	Vertical position determined by MouseRelease event handler.
	 */
	public PlotShed(Model model, double x, double y) {
		// TODO: A forest is always 100.0 feet tall?
		super(model, x, y, 100.0, new AdjustablePolygon(null, null, 0, 0), windowShed, plotShed);
		// TODO: Define the background and anchor color, and starting position
		// of this polygon
		//this.p = new AdjustablePolygon(null, null, 0, 0);
	}

	@Override
	public ImageView render() {
		// TODO Auto-generated method stub
		return null;
	}

}
