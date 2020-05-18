package udel.GardenProject.plotObjects.lines;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import javafx.application.Application;
import javafx.stage.Stage;
import udel.GardenProject.garden.Controller;
import udel.GardenProject.garden.Model;

public class PlotPathTest {
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
	public void testPlotPath() {
		PlotPath p = new PlotPath(null, 0, 0);
		assertTrue(p.getWindowImage().equals("/viewImages/path.png"));
		assertTrue(p.getPlotImage().equals("/viewImages/plotPath.png"));
		assertTrue(p.getPlotX()==0);
		assertTrue(p.getPlotY()==0);
		assertTrue(p.getHeight()==1);
	}

}
