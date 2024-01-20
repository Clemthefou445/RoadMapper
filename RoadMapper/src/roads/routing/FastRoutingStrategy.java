package roads.routing;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;
import java.util.stream.Collectors;

import roads.City;
import roads.routes.*;

public class FastRoutingStrategy implements RoutingStrategy {
	
	/**
	 * @return a route between the 2 given City arguments, or null if there is no route between them
	 * @param city1
	 * @param city2
	 * 
	 * @post result is a Route object (more particularly an Optional<Route> object)
	 * 		| result.getClass()== EmptyRoute.class || result.getClass()== NonemptyRoute.class
	 * @post result is a route connecting {@code city1} to {@code city1}
	 * 		| city1.getRoutesTo(city2).collect(Collectors.toList()).contains(result)
	 * 
	 */
	@Override
	public Route getRoute(City city1, City city2) { 
		Supplier<Stream<Route>> streamSupplier 
		  = () -> city1.getRoutesTo(city2);
		
		if(streamSupplier.get().count()!=0) {
			Optional<Route> result = streamSupplier.get().findAny();
			return result.get();
		}else {
			return null;
		}
	}
}

