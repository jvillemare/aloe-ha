package udel.GardenProject.plotObjects;

import javafx.collections.ObservableList;
import javafx.scene.shape.Polygon;
import udel.GardenProject.plotObjects.shapes.AdjustablePolygon;
import udel.GardenProject.plotObjects.shapes.AdjustablePolygon.Anchor;
import udel.GardenProject.plotObjects.shapes.PlotPolygon;

public class PlotShed implements PlotObject, PlotPolygon {
	
	private AdjustablePolygon p;
	
	public PlotShed() {
		// TODO: Define the background and anchor color, and starting position
		// of this polygon
		//this.p = new AdjustablePolygon(null, null, 0, 0);
	}

	@Override
	public double getPlotX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getPlotY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Polygon getPolygon() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObservableList<Anchor> getAnchors() {
		// TODO Auto-generated method stub
		return null;
	}

}
