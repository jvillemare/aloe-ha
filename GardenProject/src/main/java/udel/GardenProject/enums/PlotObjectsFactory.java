package udel.GardenProject.enums;

import javafx.scene.Node;
import javafx.scene.image.Image;

/**
 * Facilitates the manufacturing of PlotObjects JavaFX Nodes, as they can 
 * display in an accordion. This class is necessary as there no logical (and
 * Object-oriented place) to store this method behavior. This is safe, as if
 * any PlotObjects that need special handling, a switch-case can be easily 
 * implemented, here.
 * 
 * @version 1.0
 * @author Team 0
 * @see {@link udel.GardenProject.enums.PlotObjects}
 */
public class PlotObjectsFactory {
	
	/**
	 * How should the specified PlotObject be rendered in an accordion or any
	 * other UI container.
	 * @param po	PlotObject to render.
	 * @return Rendered PlotObject.
	 */
	public Node renderInAccordion(PlotObjects po) {
		Image objectImage;

		// The following line just holds images of trees
		objectImage = new Image(getClass().getResourceAsStream("/buttonImages/tree.png"), 
				150, 150, true, true);
		// = new Image(p.getImage(), 70, 70, true, true);
		//ImageView imageView = new ImageView();
		//imageView.setImage(objectImage);

		//String name = p.toString();
		//Tooltip.install(imageView, new Tooltip(name));
		//*/
		
		/**
		 * TODO: IMPLEMENT DRAG FOR PLOT OBJECTS
		 */
		/*
		 * plotPlants.put(imageView, new PlotPlant(p,0,0));
		 * imageView.setOnMouseDragged(getHandlerForDrag());
		 * imageView.setOnMouseReleased(getHandlerForRelease());
		 */
		return null;
	}

}
