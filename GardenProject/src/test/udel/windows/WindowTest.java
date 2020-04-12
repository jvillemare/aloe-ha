package windows;

import static org.junit.Assert.*;

import org.junit.Test;

public class WindowTest {

	@Test
	public void test() {
		PlotDesign w = new PlotDesign();
		assertTrue(w.getTitle() == "this should fail");
	}

}
