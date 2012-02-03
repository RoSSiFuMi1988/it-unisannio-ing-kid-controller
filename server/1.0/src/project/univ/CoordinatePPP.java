package project.univ;

public class CoordinatePPP {
	private String latitudine, longitudine, dataPosizione, lat1, lon1, raggio;

	public CoordinatePPP(String latitudine, String longitudine,
			String dataPosizione, String lat1, String lon1, String raggio) {
		super();
		this.latitudine = latitudine;
		this.longitudine = longitudine;
		this.dataPosizione = dataPosizione;
		this.lat1 = lat1;
		this.lon1 = lon1;
		this.raggio = raggio;
	}

	public String getLatitudine() {
		return latitudine;
	}

	public void setLatitudine(String latitudine) {
		this.latitudine = latitudine;
	}

	public String getLongitudine() {
		return longitudine;
	}

	public void setLongitudine(String longitudine) {
		this.longitudine = longitudine;
	}

	public String getDataPosizione() {
		return dataPosizione;
	}

	public void setDataPosizione(String dataPosizione) {
		this.dataPosizione = dataPosizione;
	}

	public String getLat1() {
		return lat1;
	}

	public void setLat1(String lat1) {
		this.lat1 = lat1;
	}

	public String getLon1() {
		return lon1;
	}

	public void setLon1(String lon1) {
		this.lon1 = lon1;
	}

	public String getRaggio() {
		return raggio;
	}

	public void setRaggio(String raggio) {
		this.raggio = raggio;
	}
	
}
