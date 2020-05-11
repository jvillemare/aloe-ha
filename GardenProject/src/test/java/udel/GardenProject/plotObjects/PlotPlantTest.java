package udel.GardenProject.plotObjects;

import static org.junit.Assert.*;

import org.junit.Test;

import udel.GardenProject.enums.Canopy;
import udel.GardenProject.plants.Plant;

public class PlotPlantTest {


	@Test
	public void testGetPlant() {
		Plant p=new Plant(null, null, null, null, 0, null, null, Canopy.CANOPY, false, false, null, null);
		PlotPlant plot=new PlotPlant(p,1,2);
		assertTrue(plot.getPlant().equals(p));
	}

	@Test
	public void testCheckIfCanopy() {
		assertTrue(PlotPlant.checkIfCanopy(null)==-1);
		assertTrue(PlotPlant.checkIfCanopy(Canopy.CANOPY)==75);
	}

	@Test
	public void testChooseImage() {
		Plant p=new Plant(null, null, null, null, 0, null, null, null, false, false, null, null);
		assertTrue(PlotPlant.chooseImage(p).equals("/viewImages/sunflower.png"));
		p=new Plant(null, null, null, null, 0, null, null, Canopy.FLOOR, false, false, null, null);
		assertTrue(PlotPlant.chooseImage(p).equals("/viewImages/floor.png"));
		p=new Plant(null, null, null, null, 0, null, null, Canopy.UNDERSTORY, false, false, null, null);
		assertTrue(PlotPlant.chooseImage(p).equals("/viewImages/understory.png"));
		p=new Plant(null, null, null, null, 0, null, null, Canopy.CANOPY, false, false, null, null);
		assertTrue(PlotPlant.chooseImage(p).equals("/viewImages/canopy.png"));
		p=new Plant(null, null, null, null, 0, null, null, Canopy.EMERGENT, false, false, null, null);
		assertTrue(PlotPlant.chooseImage(p).equals("/viewImages/emergent.png"));
	}

}
