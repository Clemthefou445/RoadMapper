package roads.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.*;

import roads.routes.Route;
import roads.City;
import roads.Road;
import roads.routes.EmptyRoute;
import roads.routes.NonemptyRoute;

class RouteTest {
	
	City city1 = new City("Brussel");
	City city2 = new City("Leuven");
	City city3 = new City("Antwerpen");
	City city4 = new City("Ghent");
	City city5 = new City("Oostende");
	City city6 = new City("Hasselt");
	
	Road Bxl_Leuv = new Road(25, city1, city2);
	Road Antw_Leuv = new Road(40, city3, city2);
	Road Antw_Ghent = new Road(52, city3, city4);
	Road Bxl_Antw = new Road(59, city1, city3);
	Road Oost_Bxl = new Road(53, city5, city1);
	Road Bxl_Ghent = new Road(61, city1, city4);
	Road Hass_Bxl = new Road(59, city6, city1);
	Road Oost_Antw = new Road(34, city5, city3);
	Road Ghent_Hass = new Road(67, city4, city6);
	Road Ghent_Oost = new Road(92, city4, city5);
	Road Ghent_Leuv = new Road(37, city4, city2);
	
	Route route1 = new EmptyRoute(city2);
	Route route2 = new NonemptyRoute(city4, Ghent_Leuv, route1);
	Route route3 = new EmptyRoute(city6);
	Route route4 = new NonemptyRoute(city3, Antw_Ghent, route2);
	Route route5 = new NonemptyRoute(city1, Bxl_Antw, route4);
	Route route6 = new EmptyRoute(city5);
	Route route7 = new NonemptyRoute(city3, Oost_Antw, route6);
	Route route8 = new NonemptyRoute(city1, Bxl_Antw, route7);
	Route route9 = new NonemptyRoute(city2, Bxl_Leuv, route8);
	Route route10 = new EmptyRoute(city5);
	Route route11 = new NonemptyRoute(city5, Oost_Bxl, route5);
	
	Route route6_B = new EmptyRoute(city5);
	Route route7_B = new NonemptyRoute(city3, Oost_Antw, route6);
	Route route8_B = new NonemptyRoute(city1, Bxl_Antw, route7);
	Route route9_B = new NonemptyRoute(city2, Bxl_Leuv, route8);
	
	Route equiv1= new NonemptyRoute(city1, Oost_Bxl, route10);
	Route equiv2= new NonemptyRoute(city3, Bxl_Antw, equiv1);
	Route equiv3= new NonemptyRoute(city4, Antw_Ghent, equiv2);
	Route equiv4= new NonemptyRoute(city2, Ghent_Leuv, equiv3);
	@Test
	void testConstructorAndGetters() {
	//test public Constructor
		assertArrayEquals(route7.getRouteCalls(route7).toArray(),route7_B.getRouteCalls(route7_B).toArray());
	//test getLength()
		assert route1.getLength() == 0;
		assert route2.getLength() == 37;
		assert route5.getLength() == 148;
		assert route9.getLength() == 118 ;
		assert route11.getLength() == 201;
	//test getEndCity()	
		assert route4.getEndCity().equals(city2);
		assert route5.getEndCity().equals(city2);
		assert route5.getEndCity().equals(route9.getStartCity());
	}
	
	@Test
	void testRouteEquivalence() {
		assert route6.hashCode() == route10.hashCode();
		assert new HashSet<>(List.of(route6)).contains(route6_B);
		assert route8.equals(route8_B);
		assert route7_B.hashCode() == route7.hashCode();
		assert equiv4.getLength() == route11.getLength();
		/*same route, different direction --> not considered as equal*/
		assert !(route11.equals(equiv4));
	}

}
