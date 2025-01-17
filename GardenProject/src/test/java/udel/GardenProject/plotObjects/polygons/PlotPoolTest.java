package udel.GardenProject.plotObjects.polygons;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import javafx.application.Application;
import javafx.stage.Stage;

public class PlotPoolTest {

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
		
		PlotPatio p=new PlotPatio(null,50,50);
		assertTrue(p.getRenderHeight()==40);
		assertTrue(p.getRenderWidth()==40);
		assertTrue(p.getPlotImage().equals("/viewImages/plotPatio.png"));
		assertTrue(p.getWindowImage().equals("/viewImages/patio.png"));
		assertTrue(p.getName().equals("Patio"));
	}

}
