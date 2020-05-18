package udel.GardenProject.plotObjects.special;

import static org.junit.Assert.*;

import org.junit.Test;

public class GenericSpecialTest {

	@Test
	public void test() {
		GenericSpecial s=new GenericSpecial(null, 10, 20, 0, 0, "imagePath", "plotPath", "name");
		assertTrue(s.getPlotX()==10);
		assertTrue(s.getPlotY()==20);
		assertTrue(s.getHeight()==0);
		assertTrue(s.getWindowImage().equals("imagePath"));
		assertTrue(s.getPlotImage().equals("plotPath"));
	}

}
