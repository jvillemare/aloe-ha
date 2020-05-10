package udel.GardenProject.enums;

import static org.junit.Assert.*;

import org.junit.Test;

import javafx.scene.paint.Color;

public class ColorsTest {

	@Test
	public void test() {
		Colors c = Colors.ALICEBLUE;
		assertTrue(c.name().toLowerCase().equals("aliceblue"));
		assertTrue(c.getColor().equals(Color.ALICEBLUE));
	}

}
