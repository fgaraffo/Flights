package it.polito.tdp.flight.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.flight.model.Airline;
import it.polito.tdp.flight.model.AirlineIdMap;
import it.polito.tdp.flight.model.Airport;
import it.polito.tdp.flight.model.AirportIdMap;
import it.polito.tdp.flight.model.Route;
import it.polito.tdp.flight.model.RouteIdMap;

public class FlightDAO {

	public List<Airline> getAllAirlines(AirlineIdMap airlineIdMap) {
		String sql = "SELECT * FROM airline";
		List<Airline> list = new ArrayList<>();
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Airline airline = new Airline(res.getInt("Airline_ID"), res.getString("Name"), res.getString("Alias"),
						res.getString("IATA"), res.getString("ICAO"), res.getString("Callsign"),
						res.getString("Country"), res.getString("Active"));
				list.add(airlineIdMap.get(airline));
			}
			conn.close();
			return list;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	public List<Route> getAllRoutes(AirlineIdMap airlineIdMap, AirportIdMap airportIdMap, RouteIdMap routeIdMap) {
		String sql = "SELECT * FROM route";
		List<Route> list = new ArrayList<>();
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			int counter = 0;
			while (res.next()) {
				Airport sourceAirport = airportIdMap.get(res.getInt("Source_airport_ID"));
				Airport destinationAirport = airportIdMap.get(res.getInt("Destination_airport_ID"));
				Airline airline = airlineIdMap.get(res.getInt("Airline_ID"));
				
				Route route = new Route(counter, airline, sourceAirport, destinationAirport, 
					res.getString("Codeshare"), res.getInt("Stops"), res.getString("Equipment"));
				list.add(routeIdMap.get(route));
				counter++;
				
				sourceAirport.getRoutes().add(routeIdMap.get(route));
				destinationAirport.getRoutes().add(routeIdMap.get(route));
				airline.getRoutes().add(routeIdMap.get(route));
			}
			conn.close();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	public List<Airport> getAllAirports(AirportIdMap airportIdMap) {
		String sql = "SELECT * FROM airport";
		List<Airport> list = new ArrayList<>();
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Airport airport = new Airport(res.getInt("Airport_ID"), res.getString("name"), res.getString("city"),
						res.getString("country"), res.getString("IATA_FAA"), res.getString("ICAO"),
						res.getDouble("Latitude"), res.getDouble("Longitude"), res.getFloat("timezone"),
						res.getString("dst"), res.getString("tz"));
				list.add(airportIdMap.get(airport));
			}
			conn.close();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

/*	public static void main(String args[]) {
		FlightDAO dao = new FlightDAO();

		List<Airline> airlines = dao.getAllAirlines();
		System.out.println("Airlines: "+airlines);

		List<Airport> airports = dao.getAllAirports();
		System.out.println("Airports: "+airports);

		List<Route> routes = dao.getAllRoutes();
		System.out.println("Routes: "+routes);
	}
*/
}
