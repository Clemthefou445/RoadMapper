package roads.routes;

import java.util.ArrayList;
import java.util.List;

import roads.City;

public abstract class Route {
	protected int length;
	protected City startCity;
	protected City endCity;
	protected Route continuation;
	List<City> calls = new ArrayList<>();
	
	public int getLength() {
		return this.length;
	}
	
	public City getEndCity() {
		return this.endCity;
	}
	
	public City getStartCity() {
		return this.startCity;
	}
	
	public List<City> getRouteCalls(Route route) {
		if(!(this.continuation.getStartCity().equals(this.continuation.getEndCity()))) {
			route.calls.add(this.continuation.getStartCity());
			return this.continuation.getRouteCalls(route);
		}else {
			route.calls.add(this.continuation.getStartCity());
			return route.calls;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endCity == null) ? 0 : endCity.hashCode());
		result = prime * result + length;
		result = prime * result + ((startCity == null) ? 0 : startCity.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Route other = (Route) obj;
		if (endCity == null) {
			if (other.endCity != null)
				return false;
		} else if (!endCity.equals(other.endCity))
			return false;
		if (length != other.length)
			return false;
		if (startCity == null) {
			if (other.startCity != null)
				return false;
		} else if (!startCity.equals(other.startCity))
			return false;
		return true;
	}
	
}
