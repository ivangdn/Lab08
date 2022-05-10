package it.polito.tdp.extflightdelays.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {
	
	private Graph<Airport, DefaultWeightedEdge> grafo;
	
	public void creaGrafo(int distanzaMedia) {
		this.grafo = new SimpleWeightedGraph<Airport, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		ExtFlightDelaysDAO dao = new ExtFlightDelaysDAO();
		List<Airport> airports = dao.loadAllAirports();
		Map<Integer, Airport> airportIdMap = new HashMap<Integer, Airport>();
		for(Airport a : airports) {
			airportIdMap.put(a.getId(), a);
		}
		
		Graphs.addAllVertices(this.grafo, airports);
		
		List<Rotta> rotte = dao.getRotteByDistance(distanzaMedia, airportIdMap);
		for(Rotta rotta : rotte) {
			//siccome è possibile avere la rotta opposta (A->B e B->A) ed essendo il grafo non orientato
			//devo controllare che la rotta non definisca già un arco
			//se esiste già, devo aggiornare il peso facendo la media con quello della rotta opposta
			DefaultWeightedEdge edge = grafo.getEdge(rotta.getOrigin(), rotta.getDestination());
			if(edge == null) {
				Graphs.addEdge(this.grafo, rotta.getOrigin(), rotta.getDestination(), rotta.getPeso());
			} else {
				double peso = grafo.getEdgeWeight(edge);
				double newPeso = (peso + rotta.getPeso())/2;
				grafo.setEdgeWeight(edge, newPeso);
			}
			
		}
		
//		System.out.println("Vertici = "+this.grafo.vertexSet().size());
//		System.out.println("Rotte = "+rotte.size());
//		System.out.println("Archi = "+this.grafo.edgeSet().size());
	}
	
	public int numeroVertici() {
		return this.grafo.vertexSet().size();
	}
	
	public int numeroArchi() {
		return this.grafo.edgeSet().size();
	}
	
	public List<Rotta> getRotte() {
		List<Rotta> rotte = new ArrayList<Rotta>();
		for(DefaultWeightedEdge edge : this.grafo.edgeSet()) {
			rotte.add(new Rotta(this.grafo.getEdgeSource(edge), this.grafo.getEdgeTarget(edge), this.grafo.getEdgeWeight(edge)));
		}
		return rotte;
	}

}
