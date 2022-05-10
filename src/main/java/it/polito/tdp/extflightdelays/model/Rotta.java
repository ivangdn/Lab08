package it.polito.tdp.extflightdelays.model;

public class Rotta {
	
	private Airport origin;
	private Airport destination;
	private double peso;
	
	public Rotta(Airport origin, Airport destination, double peso) {
		this.origin = origin;
		this.destination = destination;
		this.peso = peso;
	}

	public Airport getOrigin() {
		return origin;
	}

	public void setOrigin(Airport origin) {
		this.origin = origin;
	}

	public Airport getDestination() {
		return destination;
	}

	public void setDestination(Airport destination) {
		this.destination = destination;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((destination == null) ? 0 : destination.hashCode());
		result = prime * result + ((origin == null) ? 0 : origin.hashCode());
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
		Rotta other = (Rotta) obj;
		if (destination == null) {
			if (other.destination != null)
				return false;
		} else if (!destination.equals(other.destination))
			return false;
		if (origin == null) {
			if (other.origin != null)
				return false;
		} else if (!origin.equals(other.origin))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FROM: \""+origin.getAirportName() + "\" - TO: \"" + destination.getAirportName() + "\" - DISTANCE: " + peso;
	}

}
