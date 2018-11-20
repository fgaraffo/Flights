package it.polito.tdp.flight.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jgrapht.GraphPath;
import org.jgrapht.Graphs;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import it.polito.tdp.flight.db.FlightDAO;

public class Model {

	FlightDAO fdao = null;
	List<Airport> airports;
	List<Airline> airlines;
	List<Route> routes;
	
	AirlineIdMap airlineIdmap;
	AirportIdMap airportIdMap;
	RouteIdMap routeIdMap;
	
	SimpleDirectedWeightedGraph <Airport, DefaultWeightedEdge> grafo;
	
	public Model () {
		this.fdao = new FlightDAO();
	
		this.airlineIdmap = new AirlineIdMap();
		this.airportIdMap = new AirportIdMap();
		this.routeIdMap = new RouteIdMap();
		
		airlines = fdao.getAllAirlines(airlineIdmap);
		System.out.println("Airlines: "+airlines.size());

		airports = fdao.getAllAirports(airportIdMap);
		System.out.println("Airports: "+airports.size());

		routes = fdao.getAllRoutes(airlineIdmap, airportIdMap, routeIdMap);
		System.out.println("Routes: "+routes.size());		
		
	}
	
	public List <Airport> getAirports(){
		if (this.airports == null)
			return new ArrayList<Airport>();
		return this.airports;
	}

	public void createGraph() {
		grafo = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		Graphs.addAllVertices(grafo, this.airports);
		for (Route r : routes) {
			Airport sourceAirport = r.getSourceAirport();
			Airport destinationAirport = r.getDestinationAirport();
			if (!sourceAirport.equals(destinationAirport)) {
				double weight = LatLngTool.distance(
					new LatLng(sourceAirport.getLatitude(), sourceAirport.getLongitude()), 
					new LatLng(destinationAirport.getLatitude(), destinationAirport.getLongitude()), 
					LengthUnit.KILOMETER);
				Graphs.addEdge(grafo, sourceAirport, destinationAirport, weight);
			}
		}
		System.out.println(grafo.vertexSet().size());
		System.out.println(grafo.edgeSet().size());
	}

	public void printStats() {
		if (grafo!=null)
			this.createGraph();
		ConnectivityInspector <Airport, DefaultWeightedEdge> ci = new ConnectivityInspector <> (grafo);	
		System.out.println(ci.connectedSets().size());
	}

	public Set <Airport> getBiggestSCC (){
		ConnectivityInspector <Airport, DefaultWeightedEdge> ci = new ConnectivityInspector <> (grafo);	
		
		Set <Airport> bestSet = null;
		int bestSize = 0;
		
		for (Set <Airport> s : ci.connectedSets()) {
			if (s.size() > bestSize) {
				bestSet = new HashSet(s);
				bestSize = s.size();
			}
		}
		return bestSet;
	}

	public List <Airport> getShortestPath(int id1, int id2) {
		
		Airport nyc = airportIdMap.get(id1);
		Airport bg = airportIdMap.get(id2);
		
		System.out.println(nyc);
		System.out.println(bg);
		
		if (nyc == null || bg == null) {
			throw new RuntimeException("Gli aeroporti selezionati non sono presenti in memoria\n");
		}
		
		ShortestPathAlgorithm <Airport, DefaultWeightedEdge> sp = new DijkstraShortestPath <Airport, DefaultWeightedEdge> (grafo);
		double weight = sp.getPathWeight(nyc, bg);
		System.out.println(weight);
		GraphPath <Airport, DefaultWeightedEdge> gp = sp.getPath(nyc, bg);
		
		return gp.getVertexList();
	}

}
