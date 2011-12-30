package project.univ;

import java.sql.Timestamp;

public class Position {
	public Position(String latitudine, String longitudine, java.sql.Timestamp data) {
		super();
		this.latitudine = latitudine;
		this.longitudine = longitudine;
		this.data = data;
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
	public Timestamp getdata() {
		return data;
	}
	public void setdata(Timestamp data) {
		this.data = data;
	}
	
	private String latitudine;
	private String longitudine;
	private Timestamp data;
}
