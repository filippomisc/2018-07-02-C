package it.polito.tdp.extflightdelays.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {
	
	ExtFlightDelaysDAO dao = null;
	List<Airport> airports;

	AirportIdMap airportIdMap;

	SimpleWeightedGraph<Airport, DefaultWeightedEdge> grafo;

	public Model() {
		dao = new ExtFlightDelaysDAO();

		airportIdMap= new AirportIdMap();

		airports= dao.loadAllAirports(airportIdMap);
		System.out.println(airports.size());
	}

//	public List<Airport> getAirports() {
//		if (this.airports == null) {
//			return new ArrayList<Airport>();
//		}
//		return this.airports;
//	}

	public void createGraph(int minCompagnie) {
		grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		Graphs.addAllVertices(grafo, dao.getVertx(minCompagnie, airportIdMap));
		System.out.println("vertici creati: "+grafo.vertexSet().size());
		
		for ( Airport v1 : this.grafo.vertexSet()) {
			for (Airport v2 : this.grafo.vertexSet()) {
				if(!v1.equals(v2)){ 
					if(this.grafo.getEdge(v1, v2)==null){
						if(dao.getPeso(v1,v2)!=0) {
							Graphs.addEdge(this.grafo, v1, v2, dao.getPeso(v1,v2));
						}
					}
				}
				
			}
			
		}

		
		
		
		
		System.out.println("archi creati: "+grafo.edgeSet().size());
	}

	public Set<Airport> getVertex() {
			return this.grafo.vertexSet();
		}
	
	public String outputConnessi(Airport a) {
		
		StringBuilder sb = new StringBuilder();
		
		List<AirportPeso> list =new ArrayList<>();

		
		for (Airport airport : Graphs.neighborListOf(this.grafo, a)) {
			
			AirportPeso ap= new AirportPeso(airport, (int)this.grafo.getEdgeWeight(this.grafo.getEdge(a, airport)));
			
			list.add(ap);
			
		}
		
		Collections.sort(list);
		
		for (AirportPeso airportPeso : list) {
			sb.append(airportPeso.getAirport().getAirportName().toString());
			sb.append("\n");
			sb.append("num voli totali: ");
			sb.append(airportPeso.getPeso());
			sb.append("\n\n");
		}
		
		return sb.toString();
		
	}
	
	}
