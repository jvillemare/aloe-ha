package udel.GardenProject.windows;

import static org.junit.Assert.*;

import org.junit.Test;

import udel.GardenProject.garden.Model;
import udel.GardenProject.windows.AllPlants;
import udel.GardenProject.windows.Download;
import udel.GardenProject.windows.PlotDesign;

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
		w.load("fake.gardenproject"); // TODO: Generate real GardenProject save
		// -file when we have the save/load functionality working
		assertTrue(w.load("fake.gardenproject") instanceof PlotDesign);
	}
	
	@Test
	public void exitTest() {
		Model m = new Model(0, 1);
		Download w = new Download(m);
		w.exit();
	}
}
