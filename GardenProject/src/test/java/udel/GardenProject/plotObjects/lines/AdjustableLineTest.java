package udel.GardenProject.plotObjects.lines;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import javafx.application.Application;
import javafx.stage.Stage;
import udel.GardenProject.plotObjects.polygons.AdjustablePolygon;

public class AdjustableLineTest {
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
		AdjustableLine l=new AdjustableLine(null, 50, 50);
		assertTrue(l.getLine() instanceof AdjustablePolygon);
	}

}
