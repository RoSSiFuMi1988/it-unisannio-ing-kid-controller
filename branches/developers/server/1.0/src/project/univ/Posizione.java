package project.univ;

public class Posizione {
	private String latitudine;
	private String longitudine;
	private String raggio;
	
	public Posizione(String latitudine, String longitudine, String raggio) {
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

	public String getRaggio() {
		return raggio;
	}

	public void setRaggio(String raggio) {
		this.raggio = raggio;
	}
	
	
}
