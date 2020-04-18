package test.udel.windows;

import static org.junit.Assert.*;

import org.junit.Test;

import main.udel.windows.AllPlants;
import main.udel.windows.Download;
import main.udel.windows.PlotDesign;

public class DownloadTest {

	@Test
	public void Downloadtest() {
		Download w = new Download();
		assertTrue(w.getTitle().equals("Load or Save Your Plot"));
	}
	
	@Test
	public void savePNGTest() {
		Download w = new Download();
		w.savePNG();
		assertTrue(w.savePNG());
	}

	@Test
	public void saveTest() {
		Download w = new Download();
		w.save();
		assertTrue(w.save());
	}
	
	@Test
	public void loadTest() {
		Download w = new Download();
		w.load();
		assertTrue(w.load() instanceof PlotDesign);
	}
	
	@Test
	public void exitTest() {
		Download w = new Download();
		w.exit();
	}
}
