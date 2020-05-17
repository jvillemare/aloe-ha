package udel.GardenProject.plotObjects.lines;

import java.io.Serializable;

import javafx.scene.paint.Color;
import udel.GardenProject.plotObjects.polygons.AdjustablePolygon;

/**
 * Lines are like Polygons except they have no interior. They are flat like 
 * fences, wires, etc.
 * 
 * @version 1.0
 * @author Team 0
 * @see {@link udel.GardenProject.plotObjects.PlotObject}
 */
public class AdjustableLine implements Serializable{
	
	/**
	 * The adjustablepolygon object which make the adjustable line.
	 */
	private AdjustablePolygon Line;
	/**
	 * Constructor. Represent an object on a plot in PlotDesign with an 
	 * arbitrary amount of points, each editable without any color inside.
	 * 
	 * @param anchorColor	Color of the anchors.
	 * @param x		Starting horizontal position.
	 * @param y		Starting vertical position.
	 */
	public AdjustableLine(Color anchorColor, double height, double width) {
		Line=new AdjustablePolygon(Color.TRANSPARENT,anchorColor,0,0,height,width);
	}
	/**
	 * Getter.
	 * @return The line as a Adjustable Polygon.
	 */
	public AdjustablePolygon getLine() {
		return Line;
	}
}
