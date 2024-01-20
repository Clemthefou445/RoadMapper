package roads.routing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;
import java.util.stream.Collectors;

import roads.City;
import roads.routes.*;

public class OptimalRoutingStrategy implements RoutingStrategy {
	
	/**
	 * @return a route between the 2 given City arguments, or null if there is no route between them
	 * @param city1
	 * @param city2
	 * 
	 * @post result is a Route object (more particularly an Optional<Route> object)
	 * 		| result.getClass()== EmptyRoute.class || result.getClass()== NonemptyRoute.class
	 * @post result is a route connecting {@code city1} to {@code city1}
	 * 		| city1.getRoutesTo(city2).collect(Collectors.toList()).contains(result)
	 * @post result is shortest path (in length) of all possible paths between {@code city1} and {@code city1}
	 * 		| result.getLength() == Collections.min(city1.getRoutesTo(city2).map(elem -> elem.getLength()).collect(Collectors.toList()))
	 */
	@Override
	public Route getRoute(City city1, City city2) {
		Supplier<Stream<Route>> streamSupplier = () -> city1.getRoutesTo(city2);
		if(streamSupplier.get().count()!=0) {
			List<Integer> lengths = new ArrayList<>();
			streamSupplier.get().forEach(elem -> lengths.add(elem.getLength()));
			Integer min = Collections.min(lengths);
			Stream<Route> routesWithMinimalLength = streamSupplier.get().filter(elem -> elem.getLength()==min);
			return routesWithMinimalLength.findAny().get();
		}else {
			return null;
		}
	}
}
