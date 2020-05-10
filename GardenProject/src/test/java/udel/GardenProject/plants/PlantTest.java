package udel.GardenProject.plants;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.util.HashSet;

import org.junit.Test;

import udel.GardenProject.enums.Canopy;
import udel.GardenProject.enums.Colors;
import udel.GardenProject.enums.Moisture;
import udel.GardenProject.enums.PlantDataSource;
import udel.GardenProject.enums.SoilTypes;

public class PlantTest {

	@Test
	public void testGetCommonNames() {
		String[] comm= {"comm"};
		Plant p=new Plant(comm, null, null, null, 0, null, null, null, false, false, null, null);
		assertTrue(p.getCommonNames().equals(comm));
	}

	@Test
	public void testGetLatinName() {
		Plant p=new Plant(null, "LatinName", null, null, 0, null, null, null, false, false, null, null);
		assertTrue(p.getLatinName().equals("LatinName"));
	}

	@Test
	public void testTrimToLatinName() {
		String name=Plant.trimToLatinName("test test2 test3");
		assertTrue(name.equals("Test test2"));
	}

	@Test
	public void testGetFriendlyName() {
		String[] comm= {"comm"};
		Plant p=new Plant(comm, "LatinName", null, null, 0, null, null, null, false, false, null, null);
		assertTrue(p.getFriendlyName().equals("LatinName (comm)"));
	}

	@Test
	public void testGetDescription() {
		Plant p=new Plant(null, null, "Description", null, 0, null, null, null, false, false, null, null);
		assertTrue(p.getDescription().equals("Description"));
	}

	@Test
	public void testGetBloomTime() {
		boolean[] time= {false,true,false,true,false,true,false,true,false,true,false,true};
		Plant p=new Plant(null, null, null, time, 0, null, null, null, false, false, null, null);
		assertTrue(p.getBloomTime().equals(time));
	}

	@Test
	public void testGetLight() {
		Plant p=new Plant(null, null, null, null, 1.1, null, null, null, false, false, null, null);
		assertTrue(p.getLight()==1.1);
	}

	@Test
	public void testGetMoisture() {
		Plant p=new Plant(null, null, null, null, 0, Moisture.DAMP, null, null, false, false, null, null);
		assertTrue(p.getMoisture().equals(Moisture.DAMP));
	}

	@Test
	public void testGetSoilType() {
		Plant p=new Plant(null, null, null, null, 0, null, SoilTypes.CLAY, null, false, false, null, null);
		assertTrue(p.getSoilType().equals(SoilTypes.CLAY));
	}

	@Test
	public void testGetCanopy() {
		Plant p=new Plant(null, null, null, null, 0, null, null, Canopy.FLOOR, false, false, null, null);
		assertTrue(p.getCanopy().equals(Canopy.FLOOR));
	}

	@Test
	public void testGetDelawareNative() {
		Plant p=new Plant(null, null, null, null, 0, null, null, null, true, false, null, null);
		assertTrue(p.getDelawareNative());
	}

	@Test
	public void testGetInvasive() {
		Plant p=new Plant(null, null, null, null, 0, null, null, null, false, true, null, null);
		assertTrue(p.getInvasive());
	}

	@Test
	public void testGetSource() {
		PlantDataSource[] source = {PlantDataSource.SUNNYEDGE};
		Plant p=new Plant(null, null, null, null, 0, null, null, null, false, false, source, null);
		assertTrue(p.getSource().equals(source));
	}

	@Test
	public void testGetImages() {
		String[] img = {"link"};
		Plant p=new Plant(null, null, null, null, 0, null, null, null, false, false, null, img);
		assertTrue(p.getImages().equals(img));
	}

	@Test
	public void testGetColors() {
		PlantDataSource[] source = {PlantDataSource.UDEL};
		PlantDataSource[] source2 = {PlantDataSource.UDEL, PlantDataSource.NPC};
		Plant p=new Plant(null, null, null, null, 0, null, null, null, false, false, source, null);
		assertTrue(p.getColors()==null);
		String nocolor="Description";
		String color="Description Color: Red blahblahblah\r\n";
		p=new Plant(null, null, nocolor, null, 0, null, null, null, false, false, source2, null);
		assertTrue(p.getColors().isEmpty());
		source[0] = PlantDataSource.NPC;
		p=new Plant(null, null, nocolor, null, 0, null, null, null, false, false, source, null);
		assertTrue(p.getColors().isEmpty());
		p=new Plant(null, null, color, null, 0, null, null, null, false, false, source, null);
		assertTrue(p.getColors().contains(Colors.RED));
		String color2="Description Color: whitish blahblahblah\r\n";
		p=new Plant(null, null, color2, null, 0, null, null, null, false, false, source, null);
		assertTrue(p.getColors().contains(Colors.WHITE));
		String color3="Description Color: evergreen blahblahblah\r\n";
		p=new Plant(null, null, color3, null, 0, null, null, null, false, false, source, null);
		assertTrue(p.getColors().contains(Colors.FORESTGREEN));
		String color4="Description Color: non-existcolor blahblahblah\r\n";
		p=new Plant(null, null, color4, null, 0, null, null, null, false, false, source, null);
		assertTrue(p.getColors().isEmpty());
	}

	@Test
	public void testGetImageSourceDomain() throws MalformedURLException {
		assertTrue(Plant.getImageSourceDomain("https://t.co/test").equals("t.co"));
	}

}
