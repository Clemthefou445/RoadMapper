package roads;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.stream.Stream;
import java.util.Collections;

import roads.routes.EmptyRoute;
import roads.routes.NonemptyRoute;
import roads.routes.Route;
import logicalcollections.LogicalList;

/*abstract state invariant*/
/**
 *  This class (City) works as a blueprint to create cities (objects) in a network (similar to nodes in graph theory)
 * @author R0693813
 * @invar this object has 0 to infinite roads
 * 		| 0 <= getRoads().size() 
 */

public class City {
	
	/*representation invariant*/
	/**
	 * @invar | 0 <= roads.size() 
	 */

	private String name;
	
	/**
	 * 
	 * @representationObject
	 * @peerObjects
	 * 
	 */
	private List<Road> roads;
	
	/**
	 * public constructor for an instance of the class City (object initialization)
	 * @param name
	 * 
	 * @mutates | this
	 * 
	 * @throws  IllegalArgumentException if {@code name} is an empty string
	 * 		| name.isEmpty()
	 * @throws  IllegalArgumentException if {@code name} is a blank string (only whitespaces)
	 * 		| name.isBlank()
	 * @post this object's name is non-empty and not blank 
	 * 		| !( name.isEmpty() || name.isBlank() )
	 */
	public City(String name) {
		if(name.isEmpty()) {
			throw new IllegalArgumentException("argument is empty");
		}
		if(name.isBlank()) {
			throw new IllegalArgumentException("argument is blank");
		}
		this.name = name;
		this.roads = new ArrayList<Road>();
	}
	
	/**
	 * @return this object's name 
	 * @post The result is not {@code null}
     *    | result != null
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * @return the set of roads leaving from this city (object) and connecting it with other cities (objects)
	 * 
	 * @peerObjects
	 * 
	 * @post the set of roads is never null (but 0 instead)
	 * 		| result != null
	 * @post the set of roads never contains a null element
	 * 		| Stream.of(result).allMatch(elem -> elem != null)
	 */
	public List<Road> getRoads() {
			return this.roads;
	}
	
	/**
	 * adds a road to the set of roads connecting this (city-)instance to others (city-)instances
	 * @param road
	 * 
	 * @mutates_properties | this.getRoads()
	 * @inspects | road 
	 * 
	 * @post This object's number of roads equals its old number of roads plus one .
     *     | getRoads().size() == (old(getRoads()).size() + 1 )
	 */
	public void addRoad(Road road) {
			this.roads = LogicalList.plus(this.roads, road );
		}

//----------------------------------------------------------------------------------------------------
	
	private static Route routeBuilder(List<City> cities, Route end) {
		Road leg;
		for(Road road : cities.get(0).getRoads()) {
			if(road.getCities()[0]==cities.get(1) || road.getCities()[1]==cities.get(1) ) {
					leg = road;
					cities.remove(0);
					Route continuation = new NonemptyRoute(cities.get(0),leg,end);
					if(cities.size()!=1) {
						return routeBuilder(cities, continuation);
					}else {
						return continuation;
					}
			}else {
				continue;
			}
		}
		return null;
	}
	
	public Stream<Route> getRoutesTo(City destination) {
		ArrayList<City> toCreateRoutes = new ArrayList<City>();
		Route end = new EmptyRoute(destination);
		this.allPossiblePaths(destination, toCreateRoutes);
		Collections.reverse(toCreateRoutes);
		List<Route> path = new ArrayList<>();
		ArrayList<ArrayList<City>> nested_list = new ArrayList<ArrayList<City>>();
		while(!toCreateRoutes.isEmpty()) {
			ArrayList<City> nested = new ArrayList<City>();
			for(int i=0;i<toCreateRoutes.size();i++) {
				if(!(toCreateRoutes.get(i)==this)) {
					nested.add(toCreateRoutes.get(i));
				}else{
					nested.add(toCreateRoutes.get(i));
					for(int j=0;j<nested.size();j++) {
						toCreateRoutes.remove(0);
					}
					break;
				}
			}
			nested_list.add(nested);
		}
		for(List<City> elem: nested_list) {
			path.add(City.routeBuilder(elem, end));
		}
		return path.stream().flatMap(x -> Stream.of(x));
	}
	
	public void allPossiblePaths(City destination, ArrayList<City> toCreateRoutes) {
		Hashtable<String, String> visited = new Hashtable<>();
		ArrayList<City> itineraries = new ArrayList<>();
		itineraries.add(this);
		allPossiblePaths_util(this, destination, visited, itineraries, toCreateRoutes);
	}
	
	private void allPossiblePaths_util(City source, City destination, Hashtable<String, String> visited , ArrayList<City> itineraries, ArrayList<City> toCreateRoutes) {
		visited.put(source.getName(), "visited");
		
		if(source.equals(destination)) {
			for(City city : itineraries)
				toCreateRoutes.add(city);
			visited.remove(source.getName());
			return;
		}
		List<City> adjacent = new ArrayList<>();
		for(Road road : source.getRoads()) {
			if(road.getCities()[0].equals(source)) {
				adjacent.add(road.getCities()[1]);
			}else {
				adjacent.add(road.getCities()[0]);
			}
		}
		for(City actualCity : adjacent ) {
			if(!(visited.containsKey(actualCity.getName()))) {
				itineraries.add(actualCity);
				allPossiblePaths_util(actualCity, destination, visited, itineraries, toCreateRoutes);
				itineraries.remove(actualCity);
			}
		}
		visited.remove(source.getName());
	}
}