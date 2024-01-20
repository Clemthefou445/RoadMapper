package roads;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import logicalcollections.LogicalMap;

/*abstract state invariant*/
/**
 * This class (Road) works as a blueprint to create roads (objects) in a network (similar to edges in graph theory)
 * @author R0693813
 * @invar this object has a length greater than 0
 * 		| 0 < getLength()
 * @invar this object has always exactly 2 cities
 * 		| getCities().length == 2
 * @invar | this.getCities()[0].getRoads().contains(this) && this.getCities()[1].getRoads().contains(this)
 */

public class Road {
	
	/*representation invariant*/
	/**
	 * @invar | 0 < length
     * @invar | cities.length == 2
	 */
	
	private final int length;
	/**
	 * @representationObject
	 * @peerObjects
	 */
	private City[] cities;
 	
	/**
	 * public constructor for an instance of the class Road (object initialization)
	 * @param length
	 * @param city1
	 * @param city2
	 * 
	 * @mutates | this
	 * @mutates_properties | this.getCities(), this.getCities()[0].getRoads(), this.getCities()[0].getRoads()
	 * @inspects | city1, city2
	 * 
	 * @throws IllegalArgumentException if {@code length} is less than 1
	 * 		| length <= 0
	 * @post The 2 cities corresponding to this object, have each a number of roads equal to their old number of roads plus one .
     *    | city1.getRoads().size() == (old(city1.getRoads()).size() + 1 ) &&
     *    | city2.getRoads().size() == (old(city2.getRoads()).size() + 1 )
	 * @post {@code city1.getRoads()} and {@code city2.getRoads()} is not null
	 * 		| city1.getRoads() != null && city2.getRoads() != null
	 * @post {@code city1} is not the same city (object) as {@code city2}
	 * 		| !(city1.equals(city2))
	 */
	public Road(int length, City city1, City city2) {
		if(length <= 0) {
			throw new IllegalArgumentException("Length must be greater than 0");
		}
		this.length= length;
		this.cities = new City[]{city1, city2};
		city1.addRoad(this);
		city2.addRoad(this);
	}
	
	/**
	 * @return the length of this object (Road instance)
	 * @post the result is greater than 0
	 * 		| result > 0
	 * @immutable
	 */
	public int getLength() {
		return this.length;
	}
	
	/**
	 * @return exactly the 2 cities corresponding to this object (Road instance)
	 * 
	 * @peerObjects
	 * 
	 * @post the result is equal to an array of exactly 2 City instances
	 * 		| result.length == 2
	 * @post the result cannot be null (road connects necessarily 2 cities)
	 * 		| result != null
	 * @post the City array doesn't contain any null element
	 * 		|Stream.of(result).allMatch(elem -> elem != null)
	 */
	public City[] getCities() {
		return this.cities;
	}
	
	public static LogicalMap<City, List<Road>> getRoadsMap(City[] set_of_cities) {
		LogicalMap<City, List<Road>> roadMap = new LogicalMap<City, List<Road>>();
		for(int j=0;j<set_of_cities.length;j++) {
			List<Road> roads = new ArrayList<>();
			for(int i=0;i<set_of_cities[j].getRoads().size();i++) {
				roads.add(set_of_cities[j].getRoads().get(i));
			}
			roadMap.containsEntry(set_of_cities[j], roads);
		}
		return roadMap ;
	}

}
