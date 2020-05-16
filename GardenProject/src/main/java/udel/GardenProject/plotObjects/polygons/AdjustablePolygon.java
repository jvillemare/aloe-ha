package udel.GardenProject.plotObjects.polygons;

import java.io.Serializable;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeType;

/**
 * Arbitrary polygon that can be moved around within a Scene.
 * 
 * @version 1.0
 * @author Team 0
 * @see {@link udel.GardenProject.plotObjects.PlotObject}
 */
public class AdjustablePolygon implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Polygon polygon;
	private ObservableList<Anchor> anchors;
	private boolean visible=true;
	private Button b;
	
	/**
	 * Constructor. Represent an object on a plot in PlotDesign with an 
	 * arbitrary amount of points, each editable.
	 * 
	 * @param color			Background color of the polygon.
	 * @param anchorColor	Color of the anchors.
	 * @param startx		Starting horizontal position.
	 * @param starty		Starting vertical position.
	 * @param height		Height of the polygon
	 * @param width			Width of the polygon
	 */
	public AdjustablePolygon(Color color, Color anchorColor, double startx, double starty,
			double height, double width) {
		b = new Button();
		polygon=new Polygon();
		//set stroke color to black
		polygon.setStroke(Color.BLACK);
		polygon.setStrokeWidth(2);
		polygon.setStrokeLineCap(StrokeLineCap.ROUND);
		//fill the polygon with given color
		if(color==null) {
			color=Color.AQUA;
		}
		polygon.setFill(color);
		//initial a polygon
		polygon.getPoints().addAll(new Double[] {
        	    0.0, 0.0,
        	    0.0, height/2,
        	    0.0, height,
        	    width/2, height,
        	    width, height,
        	    width, height/2,
        	    width, 0.0,
        	    width/2, 0.0
        	    });
		polygon.setLayoutX(startx);
		polygon.setLayoutY(starty);
        polygon.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				double x = mouseEvent.getX();
				double y = mouseEvent.getY();
				//System.out.println(polygon.getLayoutX()+x+":"+polygon.getScene().getWidth());
				if(		polygon.getLayoutX() + x > 0 &&
						polygon.getLayoutX() + x < polygon.getScene().getWidth()) {
					polygon.setLayoutX(polygon.getLayoutX() + x);
				}
				if(		polygon.getLayoutY() + y > 0 &&
						polygon.getLayoutY() + y < polygon.getScene().getHeight()) {
					polygon.setLayoutY(polygon.getLayoutY() + y);
				}
			}
		});
        polygon.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() > 1) {
                	visible = !visible;
                	for(Anchor a:anchors)
                		a.setVisible(visible);
                	if(visible) 
                		b.setText("Stop Editing");
                	else
                		b.setText("Resume Edit");
                }
            }
        });

        //get the anchors list and set the color to given anchorColor
        if(anchorColor == null) {
        	anchorColor = Color.DARKRED;
        }
		anchors = createAnchors(polygon, polygon.getPoints(),anchorColor);
	}
	
	/**
	 * Determines if a double point specified by an X and Y is within this
	 * polygon.
	 * @param x	Horizontal position as a double.
	 * @param y Vertical position as a double.
	 * @return True if the point is within this polygon, false if not.
	 */
	public boolean isPointInPolygon(double x, double y) {
		// TODO: Implement...
		return false;
	}
	
	/**
	 * Getter.
	 * @return The polygon.
	 */
	public Polygon getPolygon() {
		return polygon;
	}
	
	/**
	 * Getter.
	 * @return List of anchors.
	 */
	public ObservableList<Anchor> getAnchors() {
		return anchors;
	}
	
	/**
	 * Generate edit button that toggles the editing of the polygon.
	 * 
	 * @param x	Horizontal position.
	 * @param y Vertical position.
	 * @return Button object.
	 */
	public Button genButton() {

		b.setText("Stop Editing");
		b.setOnAction(new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
            	visible=!visible;
            	for(Anchor a:anchors) {
            		a.setVisible(visible);
            	}
            	if(visible) 
            		b.setText("Stop Editing");
            	else
            		b.setText("Resume Edit");
            } 
        });
		return b;
	}
	
	/**
	 * Getter.
	 * @return The handler that allows you to toggle the editability of a 
	 * 			AdjustablePolygon.
	 */
	public EventHandler<ActionEvent> getAnchorPointHandler() {
		return new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
            	visible=!visible;
            	for(Anchor a:anchors) {
            		a.setVisible(visible);
            	}
            	if(visible) 
            		b.setText("Stop Editing");
            	else
            		b.setText("Resume Edit");
            } 
        };
	}
	
	/**
	 * Generate the list of anchors for drawings.
	 * @param polygon Polygon object to draw the circle on the anchor
	 * @param points All points of polygon
	 * @param color The color of the anchor
	 * @return An ObservableList of anchors
	 */
    private ObservableList<Anchor> createAnchors(Polygon polygon, 
    		ObservableList<Double> points, Color color) {
    	ObservableList<Anchor> anchors = FXCollections.observableArrayList();
    	for (int i = 0; i < points.size(); i += 2) {
    	
            final int idx = i;
            DoubleProperty xProperty = new SimpleDoubleProperty(points.get(i));
            DoubleProperty yProperty = new SimpleDoubleProperty(points.get(i + 1));
            Anchor anchor = new Anchor(color, xProperty, yProperty);
            anchor.layoutXProperty().bindBidirectional(polygon.layoutXProperty());
            anchor.layoutYProperty().bindBidirectional(polygon.layoutYProperty());
            xProperty.addListener(new ChangeListener<Number>() {
                @Override public void changed(
                		ObservableValue<? extends Number> ov, Number oldX, 
                		Number x) {
                  points.set(idx, (double) x);
                }
              });
            yProperty.addListener(new ChangeListener<Number>() {
                @Override public void changed(
                		ObservableValue<? extends Number> ov, Number oldY, 
                		Number y) {
                  points.set(idx + 1, (double) y);
                }
              });
            anchors.add(anchor);
        }
    	return anchors;
    }

    /**
     * Extend anchor from circle class and set the color
     * 
     * @author Team 0
     */
	public class Anchor extends Circle {
    	private final DoubleProperty x, y;

        Anchor(Color color, DoubleProperty x, DoubleProperty y) {
            super(x.get(), y.get(), 5);
            setFill(color.deriveColor(1, 1, 1, 0.5));
            setStroke(color);
            setStrokeWidth(2);
            setStrokeType(StrokeType.OUTSIDE);

            this.x = x;
            this.y = y;

            x.bind(centerXProperty());
            y.bind(centerYProperty());
            Drag();
        }
        
    	private void Drag() {
            final Delta dragDelta = new Delta();
            setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    dragDelta.x = getCenterX() - mouseEvent.getX();
                    dragDelta.y = getCenterY() - mouseEvent.getY();
                    getScene().setCursor(Cursor.MOVE);
                }
            });
            // TODO: What does this do?
            setOnMouseReleased(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    getScene().setCursor(Cursor.HAND);
                }
            });
            // TODO: What does this do?
            setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    double newX = mouseEvent.getX() + dragDelta.x;
                    //System.out.println(getLayoutX()+newX+" ");
                    if (	getLayoutX() + newX >= 0 && 
                    		newX < getParent().getScene().getWidth()) {
                        setCenterX(newX);
                    }
                    double newY = mouseEvent.getY() + dragDelta.y;
                    if (	getLayoutY() + newY >= 0 && 
                    		newY < getParent().getScene().getHeight()) {//getParent().getLayoutBounds
                        setCenterY(newY);
                    }
                }
            });
            // change the cursor to hand when move in range
            setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (!mouseEvent.isPrimaryButtonDown()) {
                        getScene().setCursor(Cursor.HAND);
                    }
                }
            });
            // change the cursor to default when leave
            setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (!mouseEvent.isPrimaryButtonDown()) {
                        getScene().setCursor(Cursor.DEFAULT);
                    }
                }
            });
        }
    	
    	public DoubleProperty getX() {
    		return this.x;
    	}
    	
    	public DoubleProperty getY() {
    		return this.y;
    	}
	}
	
	/**
	 * Helper class.
	 * @author Team 0
	 */
    private class Delta {
    	double x, y;
    }
    
}
