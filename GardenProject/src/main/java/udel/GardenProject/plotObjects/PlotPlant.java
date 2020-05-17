package udel.GardenProject.plotObjects;

import java.io.Serializable;

import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Effect;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import udel.GardenProject.enums.Canopy;
import udel.GardenProject.garden.Model;
import udel.GardenProject.plants.Plant;

/**
 * Plant that can appear on the plot in PlotDesign.<br><br>
 * 
 * Special object that stays in the root of the 
 * <code>udel.GardenProject.plotObjects</code> package.<br><br>
 * 
 * This PlotObject is unique in that it does not have an accompanying 
 * PlotObjects enum constant, as the how a plant appears in an accordion is
 * handled by the Plant class, and how it is rendered in the plot is defined by
 * this PlotPlant's <code>render()</code> method, as all other PlotObject have.
 * 
 * @version 1.0
 * @author Team 0
 * @see {@link udel.GardenProject.plotObjects.PlotObject}
 */
public class PlotPlant extends PlotObject implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * The plant this PlotPlant is representing.
	 */
	private Plant p;
	
	/**
	 * Plant that can appear on in the PlotDesign.
	 * 
	 * @param Session	T
	 * @param p			Plant that's appearing on the Plot.
	 * @param x 		Horizontal position in PlotDesign.
	 * @param y 		Vertical position in PlotDesign.
	 */
	public PlotPlant(Model model, Plant p, double x, double y) {
		// TODO: A plant is always about a foot wide? Let's say for simplicity.
		// Fix later. Definitely a helper method in plant that reads the 
		// description or calculates or something
		super(model, x, y, checkIfCanopy(p.getCanopy()), 5.0, chooseWindowImage(p), choosePlotImage(p), p.getLatinName());
		this.p = p;
	}
	
	/**
	 * Getter.
	 * @return	Plant object.
	 */
	public Plant getPlant() {
		return this.p;
	}
	
	/**
	 * Checks to see if the canopy is null.
	 * @param c Canopy of a plant.
	 * @return -1 if the canopy is null. Otherwise it will return the 
	 * getAverageCanopyHeight of the given canopy.
	 */
	public static double checkIfCanopy(Canopy c) {
		if (c == null) {
			return -1;
		}
		else {
			return c.getAverageCanopyHeight();
		}
	}
	
	/**
	 * <b>For Out the Window View:</b>
	 * Determines the image to be used for the window view.
	 * @param p 	The plant object.
	 * @return 		The correct image based on canopy level. If no canopy level 
	 * 				if provided, a sunflower is used.
	 */
	public static String chooseWindowImage(Plant p) {
		if (p.getCanopy() == null) {
			return "/viewImages/sunflower.png";
		}
		switch(p.getCanopy()) {
			case FLOOR:
				return "/viewImages/floor.png";
			case UNDERSTORY:
				return "/viewImages/understory.png";
			case CANOPY:
				return "/viewImages/canopy.png";
			case EMERGENT:
				return "/viewImages/emergent.png";
			default:
				return "/viewImages/sunflower.png";
		}
	}
	
	/**
	 * <b>For Plot Design:</b>
	 * Determines the image to be used for the plot design.
	 * @param p 	The plant object.
	 * @return 		The correct image based on canopy level. If no canopy level 
	 * 				if provided, a young tree is used.
	 */
	public static String choosePlotImage(Plant p) {
		if (p.getCanopy() == null) {
			return "/viewImages/sunflower.png";
		}
		switch(p.getCanopy()) {
			case FLOOR:
				return "/viewImages/plotFloor.png";
			case UNDERSTORY:
				return "/viewImages/plotUnderstory.png";
			case CANOPY:
				return "/viewImages/plotCanopy.png";
			case EMERGENT:
				return "/viewImages/plotEmergent.png";
			default:
				return "/viewImages/babyTree.png";
		}
	}
	
	public Node render(Plant p) {
		String[] plantImg = p.getImages();
		Image plantImage;
		
		// Get the actual image if it exists
		if (plantImg != null && plantImg.length > 0) {
			String path = p.getImages()[0];
			plantImage = new Image(path, getRenderWidth(), getRenderHeight(), true, true);
		} else {
			// get a default image
			plantImage = new Image(getClass().getResourceAsStream(choosePlotImage(p)), 40.0, 40.0,
					true, true);
		}
		
		ImageView imageView = new ImageView();
		Circle clip = new Circle(plantImage.getWidth() / 2, plantImage.getHeight() / 2, Math.min(plantImage.getWidth(), plantImage.getHeight()) / 2);
		imageView.setClip(clip);
		imageView.setImage(plantImage);
				
		return imageView;
	}

	@Override
	public Node render() {
		return render(this.p);
	}

	@Override
	public double getRenderWidth() {
		int min = 20;
		double width = Math.min(this.getHeight() / 8, min);
		return width/this.getModel().getSession().getWidthOfUserPlot() * this.getModel().getPlotDesignWidth();
	}

	@Override
	public double getRenderHeight() {
		int min = 20;
		double width = Math.min(this.getHeight() / 8, min);
		return width/this.getModel().getSession().getLengthOfUserPlot() * this.getModel().getPlotDesignHeight();
		
//		int min = 40;
//		double width = Math.max(min, this.getHeight() * 2);
//		return width * (this.getModel().getSession().getLengthOfUserPlot() / 50);
	}
	
	@Override
	public void windowRender(GraphicsContext gc, GaussianBlur gb, double minScale, int maxDepth, int maxWidth, double viewDepth, double viewWidth, double yearScale, Effect e) {
		if (this.getPlotY() / maxDepth > minScale) {
			minScale = this.getPlotY() / maxDepth;
		}
		Image i = new Image(this.getWindowImage());
		double origDepth = i.getHeight() * minScale;//Height/Width based on depth
		double origWidth = i.getWidth() * minScale;
		double actDepth = i.getHeight() * minScale * yearScale;//Height/Width based on age
		double actWidth = i.getWidth() * minScale * yearScale;
		double addDepth = origDepth - actDepth;
		double addWidth = origWidth - actWidth;
		gc.setEffect(gb);
		gc.fillOval(this.getPlotX() / maxWidth * viewWidth - (i.getWidth() / 2 * minScale) + addWidth / 2,
				this.getPlotY() / maxDepth * (viewDepth / 3) - (i.getHeight() / 3 * minScale * yearScale) + viewDepth / 3 * 2 + 10,
				i.getWidth() * minScale * yearScale, i.getHeight() / 2 * minScale * yearScale);
		gc.setEffect(e);
		gc.drawImage(i, this.getPlotX() / maxWidth * viewWidth - (i.getWidth() / 2 * minScale) + addWidth/2,
				this.getPlotY() / maxDepth * (viewDepth / 3) - (i.getHeight() * minScale) + viewDepth / 3 * 2 + addDepth + 10,
				i.getWidth() * minScale * yearScale, i.getHeight() * minScale * yearScale);
	}

}
