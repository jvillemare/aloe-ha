package udel.GardenProject.plotObjects.special;

import java.io.Serializable;

import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Effect;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import udel.GardenProject.garden.Model;
import udel.GardenProject.plotObjects.PlotObject;

/**
 * All other miscellaneous plot objects descend from GenericSpecial.
 * 
 * @version 1.0
 * @author Team 0
 */
public class GenericSpecial extends PlotObject implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * The name of the plot object
	 */
	private String name = "";

	public GenericSpecial(Model model, double x, double y, double height, double radius, String imagePath,
			String plotPath, String name) {
		super(model, x, y, height, radius, imagePath, plotPath, name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Node render() {
		Image genericSpecialImage;
		
		genericSpecialImage = new Image(
				getClass().getResourceAsStream(
						this.getPlotImage()), this.getRenderWidth(), this.getRenderHeight(),true, true);
		ImageView imageView = new ImageView();
		Circle clip = new Circle(genericSpecialImage.getWidth() / 2, genericSpecialImage.getHeight() / 2, Math.max(genericSpecialImage.getWidth(), genericSpecialImage.getHeight()) / 2);
		imageView.setImage(genericSpecialImage);
		imageView.setClip(clip);		
		return imageView;
	}

	@Override
	public double getRenderWidth() {
		int min = 20;
		double width = Math.min(this.getHeight(), min);
		return width/this.getModel().getSession().getWidthOfUserPlot() * 
				this.getModel().getPlotDesignWidth();
	}

	@Override
	public double getRenderHeight() {
		int min = 20;
		double width = Math.min(this.getHeight(), min);
		return width/this.getModel().getSession().getLengthOfUserPlot() * this.getModel().getPlotDesignHeight();
	}

	@Override
	public void windowRender(GraphicsContext gc, GaussianBlur gb, double minScale, int maxDepth, int maxWidth,
			double viewDepth, double viewWidth, double yearScale, Effect e) {
		if (this.getPlotY() / maxDepth > minScale) {
			minScale = this.getPlotY() / maxDepth;
		}
		Image i = new Image(this.getWindowImage());
		gc.setEffect(null);	
		gc.drawImage(i, this.getPlotX() / maxWidth * viewWidth - (i.getWidth() / 2 * minScale),
				this.getPlotY() / maxDepth * (viewDepth / 3) - (i.getHeight() * minScale) + viewDepth / 3 * 2,
				i.getWidth() * minScale, i.getHeight() * minScale);
		
	}

	@Override
	public void setVisible(boolean vis) {
		// TODO Auto-generated method stub
		
	}

}
