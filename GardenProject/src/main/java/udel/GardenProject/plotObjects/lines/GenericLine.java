package udel.GardenProject.plotObjects.lines;

import java.io.Serializable;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import udel.GardenProject.garden.Model;
import udel.GardenProject.plotObjects.PlotObject;
import udel.GardenProject.plotObjects.polygons.AdjustablePolygon;
import udel.GardenProject.plotObjects.polygons.AdjustablePolygon.Anchor;

/**
 * A generic line object like a fence or a path that is a flat line with
 * multiple points.
 * 
 * @version 1.0
 * @author Team 0
 * @see {@link udel.GardenProject.plotObjects.PlotObject}
 */
public abstract class GenericLine extends PlotObject implements Serializable {

	private static final long serialVersionUID = 1L;
	private AdjustableLine l;
	private String name = "";

	/**
	 * Constructor. Every GenericLine has PlotObject attributes plus an
	 * AdjustableLine that represents it visually in the plot designer.
	 * 
	 * @param x         Horizontal position on plot.
	 * @param y         Vertical position on plot.
	 * @param height    Height in feet.
	 * @param p         AdjustableLine that represent a line object.
	 * @param imagePath Plot object's path to image.
	 */
	public GenericLine(Model model, double x, double y, double height, AdjustableLine l, String windowPath,
			String plotPath, String name) {
		super(model, x, y, height, 2.0, windowPath, plotPath, name);
		// TODO Auto-generated constructor stub
		// TODO check 2.0 radius
		this.l = l;
		this.name = name;
		this.setUseDefaultDragHandler(true);
	}

	/**
	 * Getter.
	 * @return 	The AdjustablePolygon that should represent this object in the PlotDesign
	 * 			Window.
	 */
	public final AdjustablePolygon getAdjustablePolygon() {
		// TODO: Implement and change return type
		return l.getLine();
	}

	/**
	 * Getter.
	 * 
	 * @return The anchors that need to be added individually to the root that move
	 *         the different points of the polygon.
	 */
	public final ObservableList<Anchor> getAnchors() {
		return this.l.getLine().getAnchors();
	}

}
