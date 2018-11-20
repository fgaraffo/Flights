package it.polito.tdp.flight.model;

public class Route {

	private int routeId;
	private Airline airline;
	private Airport sourceAirport;
	private Airport destinationAirport;
	private String codeshare;
	private int stops;
	private String equipment;

	public Route(int routeId, Airline airline, Airport sourceAirport, Airport destinationAirport,
			String codeshare, int stops, String equipment) {
		this.routeId = routeId;
		this.airline = airline;
		this.sourceAirport = sourceAirport;
		this.destinationAirport = destinationAirport;
		this.codeshare = codeshare;
		this.stops = stops;
		this.equipment = equipment;
		
	}

	public int getRouteId() {
		return routeId;
	}

	public void setRouteId(int routeId) {
		this.routeId = routeId;
	}

	public Airline getAirline() {
		return airline;
	}

	public void setAirline(Airline airline) {
		this.airline = airline;
	}

	public Airport getSourceAirport() {
		return sourceAirport;
	}

	public void setSourceAirport(Airport sourceAirport) {
		this.sourceAirport = sourceAirport;
	}

	public Airport getDestinationAirport() {
		return destinationAirport;
	}

	public void setDestinationAirport(Airport destinationAirport) {
		this.destinationAirport = destinationAirport;
	}

	public String getCodeshare() {
		return codeshare;
	}

	public void setCodeshare(String codeshare) {
		this.codeshare = codeshare;
	}

	public int getStops() {
		return stops;
	}

	public void setStops(int stops) {
		this.stops = stops;
	}

	public String getEquipment() {
		return equipment;
	}

	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + routeId;
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
		if (routeId != other.routeId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Route [airline=" + airline + ", sourceAirport=" + sourceAirport + ", destinationAirport="
				+ destinationAirport + "]";
	}

	

}
