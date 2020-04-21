package test.udel.garden;

import static org.junit.Assert.*;

import org.junit.Test;

import javafx.application.Application;
import javafx.stage.Stage;
import main.udel.garden.View;
import main.udel.windows.AllPlants;
import main.udel.windows.Window;

import org.testfx.framework.junit.ApplicationTest;

public class ViewTest extends ApplicationTest {
	
	private Stage stage = null;
	
	/**
     * Will be called with {@code @Before} semantics, i. e. before each test method.
     */
    @Override
    public void start(Stage stage) {
        this.stage = stage;
    }

	@Test
	public void testConstructor() {
		Window w = new AllPlants();
		View v = new View(stage, w);
	}
	
	@Test
	public void testConstructorWithStage() {
		Window w = new AllPlants();
		View v = new View(stage, w);
	}
	
	@Test
	public void testUpdate() {
		//View v = new View();
		//ass
	}

}
