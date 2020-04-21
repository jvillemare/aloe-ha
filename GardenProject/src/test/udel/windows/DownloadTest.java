package test.udel.windows;

import static org.junit.Assert.*;

import org.junit.Test;

import main.udel.garden.Model;
import main.udel.windows.AllPlants;
import main.udel.windows.Download;
import main.udel.windows.PlotDesign;

public class DownloadTest {

	@Test
	public void Downloadtest() {
		Model m = new Model(0, 1);
		Download w = new Download(m);
		assertTrue(w.getTitle().equals("Load or Save Your Plot"));
	}
	
	@Test
	public void savePNGTest() {
		Model m = new Model(0, 1);
		Download w = new Download(m);
		w.savePNG(null);
		assertTrue(w.savePNG(null));
	}

	@Test
	public void saveTest() {
		Model m = new Model(0, 1);
		Download w = new Download(m);
		w.save(null);
		assertTrue(w.save(null));
	}
	
	@Test
	public void loadTest() {
		Model m = new Model(0, 1);
		Download w = new Download(m);
		w.load();
		assertTrue(w.load() instanceof PlotDesign);
	}
	
	@Test
	public void exitTest() {
		Model m = new Model(0, 1);
		Download w = new Download(m);
		w.exit();
	}
}
