package udel.GardenProject.enums;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import javafx.application.Application;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class PlotObjectsFactoryTest {
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
		PlotObjectsFactory pof = new PlotObjectsFactory();
		PlotObjects po=PlotObjects.Bench;
		assertTrue(pof.renderInAccordion(po) instanceof ImageView);
	}

}
