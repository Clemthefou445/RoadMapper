package roads.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import roads.City;
import roads.Road;

/*R0693813*/

class RoadTest {
	
	City city1 = new City("Brussel");
	City city2 = new City("Leuven");
	City city3 = new City("Antwerpen");
	City city4 = new City("Ghent");
	City city5 = new City("Oostende");
	City city6 = new City("Hasselt");
	
	Road Bxl_Leuv = new Road(25, city1, city2);
	Road Antw_Leuv = new Road(40, city3, city2);
	Road Antw_Ghent = new Road(52, city3, city4);
	Road Bxl_Antw = new Road(60, city1, city3);
	Road Oost_Bxl = new Road(42, city5, city1);
	Road Bxl_Ghent = new Road(36, city1, city4);
	Road Hass_Bxl = new Road(59, city6, city1);

	@Test
	void testConstructorAndGetters() {
	//test public constructor
		assert city1.getRoads().contains(Bxl_Leuv);
		assert city1.getRoads().contains(Bxl_Antw);
		assert !(city1.getRoads().contains(Antw_Ghent));
		assert city2.getRoads().contains(Antw_Leuv);
		assert city3.getRoads().contains(Bxl_Antw);
	//test getLength()
		assert Antw_Ghent.getLength() == 52 ;
		assert Antw_Leuv.getLength() == 40 ;
		assertEquals(Hass_Bxl.getLength(),59);
	//test getCities()	
		City[] cities1 = new City[] {city1, city2};
		City[] cities3 = new City[] {city3, city4};
		assertArrayEquals(Bxl_Leuv.getCities(), cities1);
		assertArrayEquals(Antw_Ghent.getCities(), cities3);
	
		assertEquals(Hass_Bxl.getCities()[0].getName(), "Hasselt");
		assertEquals(Hass_Bxl.getCities()[1].getName(), "Brussel");
		assert !(Antw_Leuv.getCities()[0].getName()=="Hasselt");
	}
}










