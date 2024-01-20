package roads.routes;

import roads.City;

public final class EmptyRoute extends Route {
	
	public EmptyRoute(City startCity) {
		this.startCity = startCity;
		this.endCity = startCity;
	}
	
	@Override
	public int getLength() {
		return 0;
	}
}
