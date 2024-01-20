package roads.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import roads.City;
import roads.Road;
import roads.routing.*;

class RoutingStrategyTest {
	
	City city1 = new City("Amsterdam");
	City city2 = new City("Copenhagen");
	City city3 = new City("Brussels");
	City city4 = new City("London");
	
	Road Am_Cop = new Road(10, city1, city2);
	Road Am_Bxl = new Road(2, city1, city3);
	Road Am_Ldn = new Road(2, city1, city4);
	Road Cop_Bxl = new Road(3, city2, city3);
	Road Cop_Ldn = new Road(5, city2, city4);
	Road Bxl_Ldn = new Road(1, city3, city4);
	
	RoutingStrategy fast = new FastRoutingStrategy();
	RoutingStrategy optimal = new OptimalRoutingStrategy();
	
	@Test
	void testFastRoutingStrategy() {
		assert fast.getRoute(city1, city2).getStartCity().equals(city1) &&
				fast.getRoute(city1, city2).getEndCity().equals(city2);
	}
	
	@Test
	void testOptimalRoutingStrategy() {
		assertEquals(optimal.getRoute(city1, city2).getLength(),5);
	}
}
