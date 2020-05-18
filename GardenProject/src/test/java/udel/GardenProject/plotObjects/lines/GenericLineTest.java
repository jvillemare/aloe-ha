package udel.GardenProject.plotObjects.lines;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import udel.GardenProject.plotObjects.polygons.AdjustablePolygon;

public class GenericLineTest {
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
		PlotFence p = new PlotFence(null, 0, 0, 10);
		assertTrue(p.getAdjustablePolygon() instanceof AdjustablePolygon);
		assertTrue(p.getAnchors() instanceof ObservableList);
	}

}
