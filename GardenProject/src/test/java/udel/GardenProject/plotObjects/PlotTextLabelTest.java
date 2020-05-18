package udel.GardenProject.plotObjects;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlotTextLabelTest {

	@Test
	public void test() {
		PlotTextLabel t=new PlotTextLabel(null,50,50,"test");
		assertTrue(t.getText().equals("test"));
		assertTrue(t.getRenderWidth()==40);
		assertTrue(t.getRenderHeight()==40);
	}

}
