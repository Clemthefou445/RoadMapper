package roads.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;

import roads.City;
import roads.Road;

class getRouteToTest {
	
	City city1 = new City("Amsterdam");
	City city2 = new City("Copenhagen");
	City city3 = new City("Brussels");
	City city4 = new City("London");
	
	Road Am_Cop = new Road(10, city1, city2);
	Road Am_Bxl = new Road(6, city1, city3);
	Road Am_Ldn = new Road(2, city1, city4);
	Road Cop_Bxl = new Road(6, city2, city3);
	Road Cop_Ldn = new Road(5, city2, city4);
	Road Bxl_Ldn = new Road(1, city3, city4);

	@Test
	void testGetRouteTo() {
	//city4.getRoutesTo(city7);
		
	//There exists "conceptually" 5 routes from city1 to city2
		assertEquals(city1.getRoutesTo(city2).count(), 5);
	//In the constructed network so far, each city is connected with all the others
		assertEquals(city4.getRoutesTo(city1).count(), city2.getRoutesTo(city3).count());
	//test that Roads are totally and unconditionally bidirectional
		City city7 = new City("Berlin");
		City city8 = new City("Barcelona");
		Road Bxl_Berl = new Road(321, city3, city7);
		Road Ldn_Berl = new Road(420, city4, city7);
		Road Berl_Bar = new Road(771, city7, city8);
		Road Bxl_Bar = new Road(602, city3, city8);
		
		assertEquals(city2.getRoutesTo(city1).count(), city1.getRoutesTo(city2).count());
	//now we added 2 cities and only 4 roads (8 roads to have a totally symmetrical network again)
		assert !(city2.getRoutesTo(city1).count() == city8.getRoutesTo(city2).count());
		
	//test that our algorithm doesn't add routes with loops (i.e. 2x via same city)
		List<List<City>> calls_local0 = new ArrayList<>() ;
		city2.getRoutesTo(city4).forEach(elem -> calls_local0.add(elem.getRouteCalls(elem)));
		for(List<City> cities : calls_local0) {
			HashSet<City> set= new HashSet<>(cities);
			assert set.size()==cities.size();
		}
	//same test, different cities
		List<List<City>> calls_local1 = new ArrayList<>() ;
		city8.getRoutesTo(city2).forEach(elem -> calls_local1.add(elem.getRouteCalls(elem)));
		for(List<City> cities : calls_local1) {
			HashSet<City> set= new HashSet<>(cities);
			assert set.size()==cities.size();
		}
	//sames tests, with an extended network
		City city5 = new City("Prague");
		City city6 = new City("Paris");
		City city9 = new City("Athens");
		City city10 = new City("Rome");
		
		Road Cop_Rome = new Road(879, city2, city10);
		Road Bxl_Rome = new Road(499, city3, city10);
		Road Bxl_Ldn = new Road(157, city3, city4);
		Road Bxl_Paris = new Road(104, city3, city6);
		Road Ldn_Paris = new Road(216, city4, city6);
		Road Prag_Paris = new Road(525, city5, city6);
		Road Prag_Ath = new Road(367, city5, city9);
		Road Paris_Berl = new Road(398, city6, city7);
		Road Berl_Rome = new Road(588, city7, city10);
		Road Berl_Prag = new Road(115, city7, city5);
		Road Ath_Paris = new Road(666, city9, city6);
		Road Ath_Rome = new Road(98, city9, city10);
		
		assert city7.getRoutesTo(city4).count()== city4.getRoutesTo(city7).count();
		assert !(city7.getRoutesTo(city4).count()== city4.getRoutesTo(city5).count());
		
		int teller=0;
		List<List<City>> calls_local2 = new ArrayList<>() ;
		city6.getRoutesTo(city10).forEach(elem -> calls_local2.add(elem.getRouteCalls(elem)));
		for(List<City> cities : calls_local2) {
			HashSet<City> set= new HashSet<>(cities);
			assert set.size()==cities.size();
			teller++;
		}
		assert teller==city6.getRoutesTo(city10).count();
	}
}
