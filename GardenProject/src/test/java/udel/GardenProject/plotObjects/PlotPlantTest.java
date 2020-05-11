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
	public void testChooseWindowImage() {
		Plant p=new Plant(null, null, null, null, 0, null, null, null, false, false, null, null);
		assertTrue(PlotPlant.chooseWindowImage(p).equals("/viewImages/sunflower.png"));
		p=new Plant(null, null, null, null, 0, null, null, Canopy.FLOOR, false, false, null, null);
		assertTrue(PlotPlant.chooseWindowImage(p).equals("/viewImages/floor.png"));
		p=new Plant(null, null, null, null, 0, null, null, Canopy.UNDERSTORY, false, false, null, null);
		assertTrue(PlotPlant.chooseWindowImage(p).equals("/viewImages/understory.png"));
		p=new Plant(null, null, null, null, 0, null, null, Canopy.CANOPY, false, false, null, null);
		assertTrue(PlotPlant.chooseWindowImage(p).equals("/viewImages/canopy.png"));
		p=new Plant(null, null, null, null, 0, null, null, Canopy.EMERGENT, false, false, null, null);
		assertTrue(PlotPlant.chooseWindowImage(p).equals("/viewImages/emergent.png"));
	}
	public void choosePlotImage() {
		Plant p=new Plant(null, null, null, null, 0, null, null, null, false, false, null, null);
		assertTrue(PlotPlant.choosePlotImage(p).equals("/viewImages/sunflower.png"));
		p=new Plant(null, null, null, null, 0, null, null, Canopy.FLOOR, false, false, null, null);
		assertTrue(PlotPlant.choosePlotImage(p).equals("/viewImages/plotFloor.png"));
		p=new Plant(null, null, null, null, 0, null, null, Canopy.UNDERSTORY, false, false, null, null);
		assertTrue(PlotPlant.choosePlotImage(p).equals("/viewImages/plotUnderstory.png"));
		p=new Plant(null, null, null, null, 0, null, null, Canopy.CANOPY, false, false, null, null);
		assertTrue(PlotPlant.choosePlotImage(p).equals("/viewImages/plotCanopy.png"));
		p=new Plant(null, null, null, null, 0, null, null, Canopy.EMERGENT, false, false, null, null);
		assertTrue(PlotPlant.choosePlotImage(p).equals("/viewImages/plotEmergent.png"));
	}

}
