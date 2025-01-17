package udel.GardenProject.plotObjects;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import javafx.application.Application;
import javafx.stage.Stage;
import udel.GardenProject.plotObjects.polygons.PlotPool;

public class PlotObjectTest {
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
		PlotPool plot = new PlotPool(null, 0, 0);
		assertTrue(plot.getPlotX()==0);
		assertTrue(plot.getPlotY()==0);
		plot.setPlotX(1);
		plot.setPlotY(1);
		assertTrue(plot.getPlotX()==1);
		assertTrue(plot.getPlotY()==1);
		assertTrue(plot.getWindowImage().equals("/viewImages/pool.png"));
		assertTrue(plot.getPlotImage().equals("/viewImages/plotPool.png"));
		assertTrue(plot.getHeight()==100);
	}

}
