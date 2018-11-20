package it.polito.tdp.flight.model;

public class Airline {

	private int airlineId;
	private String name;
	private String alias;
	private String iata;
	private String icao;
	private String callsign;
	private String country;
	private String active;

	public Airline(int airlineId, String name, String alias, String iata, String icao, String callsign, String country,
			String active) {
		super();
		this.airlineId = airlineId;
		this.name = name;
		this.alias = alias;
		this.iata = iata;
		this.icao = icao;
		this.callsign = callsign;
		this.country = country;
		this.active = active;
	}

	public int getAirlineId() {
		return airlineId;
	}

	public void setAirlineId(int airlineId) {
		this.airlineId = airlineId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getIata() {
		return iata;
	}

	public void setIata(String iata) {
		this.iata = iata;
	}

	public String getIcao() {
		return icao;
	}

	public void setIcao(String icao) {
		this.icao = icao;
	}

	public String getCallsign() {
		return callsign;
	}

	public void setCallsign(String callsign) {
		this.callsign = callsign;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + airlineId;
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
		Airline other = (Airline) obj;
		if (airlineId != other.airlineId)
			return false;
		return true;
	}

//	@Override
//	public String toString() {
//		return "Airline [airlineId=" + airlineId + ", name=" + name + ", alias=" + alias + ", iata=" + iata + ", icao="
//				+ icao + ", callsign=" + callsign + ", country=" + country + ", active=" + active + "]";
//	}

	@Override
	public String toString() {
		return name;
	}
}
