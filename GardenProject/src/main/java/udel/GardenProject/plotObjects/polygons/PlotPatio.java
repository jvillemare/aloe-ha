package udel.GardenProject.plotObjects.polygons;

import java.io.Serializable;

import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Effect;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import udel.GardenProject.garden.Model;

/**
 * Paved outdoor area adjoining a house.
 * 
 * @version 1.0
 * @author Team 0
 * @see {@link udel.GardenProject.plotObjects.PlotObject}
 */
public class PlotPatio extends GenericPolygon implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Path to an image of patio furniture for window view.
	 */
	private static String windowPatio = "/viewImages/patio.png";
	
	/**
	 * Path to an image of patio furniture for plot design.
	 */
	private static String plotPatio = "/viewImages/plotPatio.png";

	/**
	 * Constructor.
	 * 
	 * @param x	Horizontal position determined by MouseRelease event handler.
	 * @param y	Vertical position determined by MouseRelease event handler.
	 */
	public PlotPatio(Model model, double x, double y) {
		// TODO: A patio is always 25.0 feet tall?
		super(model, x, y, 25.0, new AdjustablePolygon(null, null, 0, 0), windowPatio, plotPatio);
		// TODO: Define the background and anchor color, and starting position
		// of this polygon
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

}
