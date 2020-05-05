package udel.GardenProject.plotObjects.lines;

import java.io.Serializable;

import javafx.collections.ObservableList;
import udel.GardenProject.plotObjects.PlotObject;
import udel.GardenProject.plotObjects.polygons.AdjustablePolygon.Anchor;

public abstract class GenericLine extends PlotObject implements Serializable {

	private static final long serialVersionUID = 1L;
	private AdjustableLine l;
	
	/**
	 * Constructor. Every GenericLine has PlotObject attributes plus an 
	 * AdjustableLine that represents it visually in the plot designer.
	 * 
	 * @param x			Horizontal position on plot.
	 * @param y			Vertical position on plot.
	 * @param height	Height in feet.
	 * @param p			AdjustableLine that represent a line object.
	 * @param path		Path to plot object's image.
	 */
	public GenericLine(double x, double y, double height, AdjustableLine l, String path) {
		super(x, y, height, path);
		// TODO Auto-generated constructor stub
		this.l = l;
	}
	
	/**
	 * Getter.
	 * @return 	The Polygon that should represent this object in the PlotDesign
	 * 			Window.
	 */
	public final Object getPolygon() {
		// TODO: Implement and change return type
		return null;
	}
	
	/**
	 * Getter.
	 * @return	The anchors that need to be added individually to the root that
	 * 			move the different points of the polygon.
	 */
	public final ObservableList<Anchor> getAnchors() {
		// TODO: Implement and change return type
		return null;
	}

}
