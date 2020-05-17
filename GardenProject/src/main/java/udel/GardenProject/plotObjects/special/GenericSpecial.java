package udel.GardenProject.plotObjects.special;

import java.io.Serializable;

import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Effect;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import udel.GardenProject.garden.Model;
import udel.GardenProject.plotObjects.PlotObject;
import udel.GardenProject.plotObjects.PlotPlant;

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
		// TODO Auto-generated method stub
		Image genericSpecialImage;
		
		genericSpecialImage = new Image(
				getClass().getResourceAsStream(
						this.getPlotImage()), 40.0, 40.0,true, true);
		
		ImageView imageView = new ImageView();
		imageView.setImage(genericSpecialImage);
				
		return imageView;
	}

	@Override
	public double getRenderWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getRenderHeight() {
		// TODO Auto-generated method stub
		return 0;
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

}
