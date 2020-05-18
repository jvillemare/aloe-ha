package udel.GardenProject.plotObjects.polygons;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class AdjustablePolygonTest {
	public static class AsNonApp extends Application {
	    @Override
	    public void start(Stage primaryStage) throws Exception {
	        // noop
	    }
	}

	@BeforeClass
	public static void initJFX() {
	    Thread t = new Thread("JavaFX Init Thread") {
	        public void run() {
	            Application.launch(AsNonApp.class, new String[0]);
	        }
	    };
	    t.setDaemon(true);
	    t.start();
	}

	@Test
	public void test() {
		AdjustablePolygon p=new AdjustablePolygon(null, null, 0, 0, 100, 100);
		assertTrue(p.genButton() instanceof Button);
		assertTrue(p.getPolygon() instanceof Polygon);
		assertTrue(p.getAnchors() instanceof ObservableList);
	}

}
