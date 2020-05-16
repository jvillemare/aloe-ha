package udel.GardenProject.plotObjects.lines;

import java.io.Serializable;

import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Effect;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import udel.GardenProject.garden.Model;

/**
 * Fence that outlines a user's plot.
 * 
 * @version 1.0
 * @author Team 0
 * @see {@link udel.GardenProject.plotObjects.PlotObject}
 */
public class PlotFence extends GenericLine implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Path to image of a fence for window view.
	 */
	private static String windowFence = "/viewImages/fence.png";
	
	/**
	 * Path of image of a fence for plot design.
	 */
	private static String plotFence = "/viewImages/plotFence.png";
	
	/**
	 * Constructor.
	 * @param x	Horizontal position of first point of fence.
	 * @param y Vertical position of first point of fence.
	 * @param height	Height in feet of fence.
	 */
	public PlotFence(Model model, double x, double y, double height) {
		super(model, x, y, height, new AdjustableLine(), windowFence, plotFence);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Node render() {
		return null;
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
	public void windowRender(GraphicsContext gc, GaussianBlur gb, double minScale, int maxDepth, int maxWidth, double viewDepth, double viewWidth, double yearScale, Effect e) {
		if (this.getPlotY() / maxDepth > minScale) {
			minScale = this.getPlotY() / maxDepth;
		}
		Image i = new Image(this.getWindowImage());
		gc.drawImage(i, this.getPlotX() / maxWidth * viewWidth - (i.getWidth() / 2 * minScale),
				this.getPlotY() / maxDepth * (viewDepth / 3) - (i.getHeight() * minScale) + viewDepth / 3 * 2,
				i.getWidth() * minScale, i.getHeight() * minScale);
	}

}
