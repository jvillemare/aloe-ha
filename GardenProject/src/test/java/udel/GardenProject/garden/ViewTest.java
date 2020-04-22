package udel.GardenProject.garden;

import static org.junit.Assert.*;

import org.junit.Test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import udel.GardenProject.garden.Model;
import udel.GardenProject.garden.View;
import udel.GardenProject.windows.AllPlants;
import udel.GardenProject.windows.Window;

import org.testfx.api.FxAssert;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.control.LabeledMatchers;

public class ViewTest extends ApplicationTest {
	
	private Model m;
	private View v;
	
	/**
     * Will be called with {@code @Before} semantics, i. e. before each test 
     * method.
     * 
     * <b>NOTE:</b> Do not store stage! Use it only within this start() method.
     */
    @Override
    public void start(Stage stage) {
        stage.setScene(new Scene(new StackPane(), 100, 100));
        stage.show();
        m = new Model(0, 1);
        Window w = new AllPlants(m);
        v = new View(stage, w);
    }
    
	@Test
	public void testConstructor() {
		v.toString();
	}
	
	/*
	
	@Test
	public void testConstructorWithStage() {
		Window w = new AllPlants(m);
		View v = new View(stage, w);
	}
	
	@Test
	public void testUpdate() {
		//View v = new View();
		//ass
	}*/

}
