package roads.tests;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

import roads.City;
import roads.Road;

/*R0693813*/

class CityTest {
	
	City city1 = new City("Brussel");
	City city2 = new City("Leuven");
	City city3 = new City("Antwerpen");
	City city4 = new City("Ghent");
	City city5 = new City("Oostende");
	City city6 = new City("Hasselt");
	
	Road Bxl_Leuv = new Road(25, city1, city2);
	Road Antw_Leuv = new Road(40, city3, city2);
	Road Antw_Ghent = new Road(52, city3, city4);
	Road Bxl_Antw = new Road(59, city1, city4);
	Road Oost_Bxl = new Road(42, city5, city1);
	Road Bxl_Ghent = new Road(36, city1, city4);
	Road Hass_Bxl = new Road(59, city6, city1);
	
	@Test
	void testConstructorAndGetters() {
	//test getName()
		assert  city1.getName() == "Brussel";
		assert  city3.getName() == "Antwerpen";
		assert !(city4.getName()==city6.getName());
		
		City city7 = new City("Hasselt");
		
		assertEquals(city6.getName(), city7.getName());
		
	//test addRoad(Road road) & getRoads()
		ArrayList<Road> leuv = new ArrayList<>();
		ArrayList<Road> bxl = new ArrayList<>();
		/*Order within the arrays matters for the assertion*/
		leuv.add(Bxl_Leuv);
		leuv.add(Antw_Leuv);
		bxl.add(Bxl_Leuv);
		bxl.add(Bxl_Antw);
		bxl.add(Oost_Bxl);
		bxl.add(Bxl_Ghent);
		bxl.add(Hass_Bxl);
		
		assertArrayEquals(city2.getRoads().toArray(), leuv.toArray());
		assertArrayEquals(city1.getRoads().toArray(), bxl.toArray());
	}

}