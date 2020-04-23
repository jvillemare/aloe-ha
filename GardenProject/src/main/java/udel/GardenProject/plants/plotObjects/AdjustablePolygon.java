package udel.GardenProject.plants.plotObjects;

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
 * @author Team 0
 */
public class AdjustablePolygon {
	private Polygon polygon;
	private ObservableList<Anchor> anchors;
	private boolean visible=true;
	private Button b;
	public AdjustablePolygon(Color color, Color anchorColor, double startx, double starty) {
		polygon=new Polygon();
		//set stroke color to black
		polygon.setStroke(Color.BLACK);
		polygon.setStrokeWidth(2);
		polygon.setStrokeLineCap(StrokeLineCap.ROUND);
		//fill the polygon with given color
		polygon.setFill(color);
		//initial a polygon
        polygon.getPoints().addAll(new Double[]{
        	    0.0+startx, 0.0+starty,
        	    0.0+startx, 25.0+starty,
        	    0.0+startx, 50.0+starty,
        	    50.0+startx, 50.0+starty,
        	    100.0+startx, 50.0+starty,
        	    100.0+startx, 25.0+starty,
        	    100.0+startx, 0.0+starty,
        	    50.0+startx, 0.0+starty
        	    });
        //get the anchors list and set the color to given anchorColor
		anchors=createAnchors(polygon, polygon.getPoints(),anchorColor);
		
	}
	//return the polygon
	public Polygon getPolygon() {
		return polygon;
	}
	//return the anchors list
	public ObservableList<Anchor> getAnchors() {
		return anchors;
	}
	//Generate edit button
	public Button genButton(double x, double y) {
		
		b.setText("Stop Editing");
		b.setLayoutX(x);
		b.setLayoutY(y);
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
	//generate the list of anchors for drawings
    private ObservableList<Anchor> createAnchors(Polygon polygon, ObservableList<Double> points, Color color) {
    	ObservableList<Anchor> anchors = FXCollections.observableArrayList();
    	for (int i = 0; i < points.size(); i += 2) {
    	
            final int idx = i;
            DoubleProperty xProperty=new SimpleDoubleProperty(points.get(i));
            DoubleProperty yProperty=new SimpleDoubleProperty(points.get(i+1));
            Anchor anchor = new Anchor(color, xProperty, yProperty);
            anchor.layoutXProperty().bindBidirectional(polygon.layoutXProperty());
            anchor.layoutYProperty().bindBidirectional(polygon.layoutYProperty());
            xProperty.addListener(new ChangeListener<Number>() {
                @Override public void changed(ObservableValue<? extends Number> ov, Number oldX, Number x) {
                  points.set(idx, (double) x);
                }
              });
            yProperty.addListener(new ChangeListener<Number>() {
                @Override public void changed(ObservableValue<? extends Number> ov, Number oldY, Number y) {
                  points.set(idx + 1, (double) y);
                }
              });
            anchors.add(anchor);
        }
    	return anchors;
    }
    //Extend anchor from circle class and set the color
	class Anchor extends Circle{
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
            setOnMouseReleased(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    getScene().setCursor(Cursor.HAND);
                }
            });
            setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    double newX = mouseEvent.getX() + dragDelta.x;
                    if (newX > 0 && newX < getParent().getLayoutBounds().getWidth()) {
                        setCenterX(newX);
                    }
                    double newY = mouseEvent.getY() + dragDelta.y;
                    if (newY > 0 && newY < getParent().getLayoutBounds().getHeight()) {//getParent().getLayoutBounds
                        setCenterY(newY);
                    }
                }
            });
            //change the cursor to hand when move in range
            setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (!mouseEvent.isPrimaryButtonDown()) {
                        getScene().setCursor(Cursor.HAND);
                    }
                }
            });
            //change the cursor to default when leave
            setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (!mouseEvent.isPrimaryButtonDown()) {
                        getScene().setCursor(Cursor.DEFAULT);
                    }
                }
            });
        }
	}
	//helper class
    private class Delta{
    	double x, y;
    }
}
