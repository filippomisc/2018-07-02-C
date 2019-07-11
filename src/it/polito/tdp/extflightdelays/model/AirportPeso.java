package it.polito.tdp.extflightdelays.model;

public class AirportPeso implements Comparable<AirportPeso>{
	
	private Airport airport;
	private int peso;
	
	
	public AirportPeso(Airport airport, int peso) {
		super();
		this.airport = airport;
		this.peso = peso;
	}


	public Airport getAirport() {
		return airport;
	}


	public int getPeso() {
		return peso;
	}


	@Override
	public int compareTo(AirportPeso o) {
		return -(this.getPeso()-o.getPeso());
	}
	
	
	

}
