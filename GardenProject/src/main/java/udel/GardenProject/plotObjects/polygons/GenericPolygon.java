package udel.GardenProject.plotObjects.polygons;

import java.io.Serializable;

import javafx.collections.ObservableList;
import javafx.scene.shape.Polygon;
import udel.GardenProject.plotObjects.PlotObject;
import udel.GardenProject.plotObjects.polygons.AdjustablePolygon.Anchor;

/**
 * All PlotPolygons need to specify the Polygon and Anchors that will appear on
 * the Plot Design window.
 * 
 * @author Team 0
 */
public abstract class GenericPolygon extends PlotObject implements Serializable {

	private static final long serialVersionUID = 1L;
	private AdjustablePolygon p;
	
	/**
	 * Constructor. Every GenericPolygon is a PlotObject (x, y, height), but
	 * must also specify its changing bounds which is reprsented on the plot as
	 * a AdjustablePolygon.
	 * 
	 * @param x			Horizontal position on plot.
	 * @param y			Vertical position on plot.
	 * @param height	Height in feet.
	 * @param p			AdjustablePolygon that represents what this polygon
	 * 					should look like.
	 * @param imagePath	Plot object's path to image representation
	 */
	public GenericPolygon(double x, double y, double height, AdjustablePolygon p, String imagePath) {
		super(x, y, height, imagePath);
		this.p = p;
	}
	
	/**
	 * Getter.
	 * @return 	The Polygon that should represent this object in the PlotDesign
	 * 			Window.
	 */
	public final Polygon getPolygon() {
		return this.p.getPolygon();
	}
	
	/**
	 * Getter.
	 * @return	The anchors that need to be added individually to the root that
	 * 			move the different points of the polygon.
	 */
	public final ObservableList<Anchor> getAnchors() {
		return this.p.getAnchors();
	}

}
