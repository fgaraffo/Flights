package it.polito.tdp.flight.model;

import java.util.HashMap;
import java.util.Map;

public class AirlineIdMap {

	private Map <Integer, Airline> map;
	
	public AirlineIdMap (){
		map = new HashMap<>();
	}
	
	public Airline get (int airlineId) {
		return map.get(airlineId);
	}
	
	public Airline get (Airline airline) {
		Airline old = map.get(airline.getAirlineId());
		if (old == null) {
			map.put(airline.getAirlineId(), airline);
			return airline;
		}
		return old;
	}

	public void put (Airline airline, int airlineId) {
		map.put(airlineId, airline);
	}
	
	
	
}
