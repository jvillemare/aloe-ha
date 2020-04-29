package udel.GardenProject.plotObjects.shapes;

import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import udel.GardenProject.enums.AppImages;
import udel.GardenProject.plotObjects.shapes.AdjustablePolygon.Anchor;

/**
 * All PlotPolygons need to specify the Polygon and Anchors that will appear on
 * the Plot Design window.
 * 
 * @author Team 0
 */
public interface PlotPolygon {
	
	/**
	 * Getter.
	 * @return 	The Polygon that should represent this object in the PlotDesign
	 * 			Window.
	 */
	public Polygon getPolygon();
	
	/**
	 * Getter.
	 * @return	The anchors that need to be added individually to the root that
	 * 			move the different points of the polygon.
	 */
	public ObservableList<Anchor> getAnchors();

}
