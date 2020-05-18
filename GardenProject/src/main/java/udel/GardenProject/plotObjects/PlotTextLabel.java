package udel.GardenProject.plotObjects;

import java.io.Serializable;
import java.util.Optional;

import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextInputDialog;
import javafx.scene.effect.Effect;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import udel.GardenProject.garden.Model;

/**
 * A customizable text label that a user can put on their Plot.
 * 
 * @version 1.0
 * @author Team 0
 * @see {@link udel.GardenProject.plotObjects.PlotObject}
 */
public class PlotTextLabel extends PlotObject implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String text;
	
	/**
	 * Constructor. Assumes empty default text, and will automatically prompt
	 * the user for text input in a new Stage mini window.
	 * @param model	Local model reference.
	 * @param x Horizontal position.
	 * @param y Vertical position.
	 */
	public PlotTextLabel(Model model, double x, double y) {
		this(model, x, y, "Empty Text Label");
		//super(model, x, y, 0.0, 0.0, "", "", "");
		//this.text = promptUserForLabelText();
		//this(model, x, y, "Empty Text Label");
	}

	/**
	 * Constructor.
	 * @param model	Local model reference.
	 * @param x	Horizontal position.
	 * @param y Vertical position.
	 * @param text	Text to display
	 */
	public PlotTextLabel(Model model, double x, double y, String fallBackText) {
		super(model, x, y, 0.0, 0.0, "", "", "Text");
		this.text = promptUserForLabelText(fallBackText);;
	}
	
	public String getText() {
		return this.text;
	}
	
	/**
	 * Requests from the Model to display a new small Stage dialog box where
	 * the user can enter the text of what they want the label to say.
	 * @return	Unformatted user input text for label.
	 */
	public String promptUserForLabelText(String fallBackText) {
		TextInputDialog td = new TextInputDialog("Put text here..."); 
        td.setHeaderText("Enter what you want this text label to say");
        
        Optional<String> result = td.showAndWait();
		
        if(result.isPresent())
        	return result.get();
        else
        	return fallBackText;
	}

	@Override
	public Node render() {
		return new Text(this.text); // use this.text
	}

	@Override
	public double getRenderWidth() {
		// TODO Auto-generated method stub
		return 40.0; // calculate width of text based on number of characters.
	}

	@Override
	public double getRenderHeight() {
		// TODO Auto-generated method stub
		return 40.0; // calculate height to be font size whatever.
	}

	@Override
	public void windowRender(GraphicsContext gc, GaussianBlur gb, double minScale, int maxDepth, int maxWidth, double viewDepth, double viewWidth, double yearScale, Effect e) {
		gc.setFill(Color.BLACK);
		gc.fillText(this.text, this.getPlotX() / maxWidth * viewWidth - (this.getRenderWidth() / 2), this.getPlotY() / maxDepth * viewDepth - (this.getRenderHeight() / 2));
	}

	@Override
	public void setVisible(boolean vis) {
		// TODO Auto-generated method stub
	}

}
