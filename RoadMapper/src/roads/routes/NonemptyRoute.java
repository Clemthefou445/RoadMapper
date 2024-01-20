package roads.routes;

import roads.City;
import roads.Road;

public final class NonemptyRoute extends Route {
	private Road firstLeg;
	
	public NonemptyRoute(City startCity, Road firstLeg, Route continuation) {
		if(!(firstLeg.getCities()[0].equals(continuation.startCity) || 
				firstLeg.getCities()[1].equals(continuation.startCity))) {
			throw new IllegalArgumentException("first leg doesn't connect its start city and "
					+ "the start city of the given continuation");
		}
		this.startCity = startCity;
		this.firstLeg = firstLeg;
		this.endCity = continuation.endCity;
		this.continuation = continuation;
	}
	
	@Override
	public int getLength() {
		int overallLength = this.firstLeg.getLength() + continuation.getLength();
		return overallLength;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((continuation == null) ? 0 : continuation.hashCode());
		result = prime * result + ((firstLeg == null) ? 0 : firstLeg.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		NonemptyRoute other = (NonemptyRoute) obj;
		if (continuation == null) {
			if (other.continuation != null)
				return false;
		} else if (!continuation.equals(other.continuation))
			return false;
		if (firstLeg == null) {
			if (other.firstLeg != null)
				return false;
		} else if (!firstLeg.equals(other.firstLeg))
			return false;
		return true;
	}
}
