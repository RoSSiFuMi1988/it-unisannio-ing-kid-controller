package project.univ;

public class Location {
	private String latitudine;
	private String longitudine;
	private double raggio;
	
	public Location(String latitudine, String longitudine, double raggio) {
		super();
		this.latitudine = latitudine;
		this.longitudine = longitudine;
		this.raggio=raggio;
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

	public double getRaggio() {
		return raggio;
	}

	public void setRaggio(double raggio) {
		this.raggio = raggio;
	}
	
	
}
