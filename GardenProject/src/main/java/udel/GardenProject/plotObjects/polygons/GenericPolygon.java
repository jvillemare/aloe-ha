package udel.GardenProject.plotObjects.polygons;

import java.io.Serializable;

import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Effect;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.shape.Polygon;
import udel.GardenProject.garden.Model;
import udel.GardenProject.plotObjects.PlotObject;
import udel.GardenProject.plotObjects.polygons.AdjustablePolygon.Anchor;

/**
 * All PlotPolygons need to specify the Polygon and Anchors that will appear on
 * the Plot Design window.
 * 
 * @version 1.0
 * @author Team 0
 * @see {@link udel.GardenProject.plotObjects.PlotObject}
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
	public GenericPolygon(Model model, double x, double y, double height, 
			AdjustablePolygon p, String windowPath, String plotPath) {
		super(model, x, y, height, 5.0, windowPath, plotPath);
		this.p = p;
	}
	
	@Override
	public Node render() {
		Group n=new Group();
		n.getChildren().addAll(this.p.getAnchors());
		n.getChildren().add(this.p.getPolygon());
		return n;
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
	
	@Override
	public void windowRender(GraphicsContext gc, GaussianBlur gb, double minScale, int maxDepth, int maxWidth,
			double viewDepth, double viewWidth, double yearScale, Effect e) {
		if (this.getPlotY() / maxDepth > minScale) {
			minScale = this.getPlotY() / maxDepth;
		}
		gc.setEffect(null);
		Image i = new Image(this.getWindowImage());
		gc.drawImage(i, this.getPlotX() / maxWidth * viewWidth - (i.getWidth() / 2 * minScale),
				this.getPlotY() / maxDepth * (viewDepth / 3) - (i.getHeight() * minScale) + viewDepth / 3 * 2,
				i.getWidth() * minScale, i.getHeight() * minScale);
	}

}
